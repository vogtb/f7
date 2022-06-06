import { isNotNull, isNull, isUndefined } from "../utils/Other";
import { F7Exception } from "../errors/F7Exception";
import { RefException } from "../errors/RefException";
import { ValueException } from "../errors/ValueException";
import { FormulaCaller } from "../formulas/FormulaCaller";
import { ColumnRowKey } from "../models/common/ColumnRowKey";
import { Grid } from "../models/common/Grid";
import { SheetColumnRowKey } from "../models/common/SheetColumnRowKey";
import {
  CollateralLookupFunction,
  Complex,
  Computed,
  LookupFunction,
} from "../models/common/Types";
import { CellQuery } from "../models/nodes/CellQuery";
import { Node } from "../models/nodes/Node";
import { CellObject, cellObjectFromProjection } from "../spreadsheet/CellObject";
import { excelDataTypeFromActualType } from "../spreadsheet/ExcelDataType";
import { Ref } from "../spreadsheet/Ref";
import { Spreadsheet } from "../spreadsheet/Spreadsheet";
import { AlphaUtils } from "../utils/AlphaUtils";
import { Converters } from "../utils/Converters";
import { Parsers } from "../utils/Parsers";
import { CodeExecutor } from "./CodeExecutor";

type ComputationMarkerMap = { [a1Key: string]: boolean };

/**
 * Executes F7 code in the context of a spreadsheet.
 */
export class Executor {
  /**
   * Formula caller is the simplest way to bind a lot of formulas to the executor.
   */
  readonly formulaCaller: FormulaCaller;
  /**
   * Used to keep track of the computation order, so when we look up a cell, and find that it's not
   * been computed, we can compute it. As we repeat this process, if we're ever referencing a cell
   * in this stack, we know that there is a circular reference.
   */
  private computationOrderKeyStack: Array<SheetColumnRowKey> = [];
  /**
   * Spreadsheet that we're operating on. All interactions go through this.
   */
  private spreadsheet: Spreadsheet;
  /**
   * Variables available. Each variable should be represented by a node.
   */
  private variables: { [index: string]: Node } = {};

  /**
   * Marks off computation of cells, so we don't have to store it on the cell itself.
   */
  private computationMarker: { [sheet: string]: ComputationMarkerMap } = {};

  /**
   * Construct an executor.
   */
  constructor(spreadsheet: Spreadsheet, customFormulas: { [name: string]: any } = {}) {
    this.spreadsheet = spreadsheet;
    Object.keys(this.spreadsheet.sheets).forEach((name) => {
      const sheet = this.spreadsheet.sheets[name];
      const ref = Parsers.parseReferencePair(sheet["!ref"]);
      this.computationMarker[name] = Executor.generateMarkerMap(ref);
    });
    Object.keys(this.spreadsheet.names).forEach((name) => {
      const ref = this.spreadsheet.names[name].ref;
      this.variables[name] = Parsers.parseNamedRange(ref);
    });
    this.formulaCaller = new FormulaCaller(this.lookup, this.collateralLookup, customFormulas);
  }

  private static generateMarkerMap(ref: Ref): ComputationMarkerMap {
    const toReturn: ComputationMarkerMap = {};
    for (let row = 0; row < ref.endRowIndex; row++) {
      for (let column = 0; column < ref.endColumnIndex; column++) {
        toReturn[new ColumnRowKey(column, row).toA1()] = false;
      }
    }
    return toReturn;
  }

  /**
   * Run the spreadsheet.
   */
  execute() {
    for (const name of Object.keys(this.spreadsheet.sheets)) {
      const sheet = this.spreadsheet.sheets[name];
      const ref = Parsers.parseReferencePair(sheet["!ref"]);
      for (let row = ref.endRowIndex; row >= 0; row--) {
        for (let column = ref.endColumnIndex; column >= 0; column--) {
          this.processCell(new SheetColumnRowKey(name, column, row));
        }
      }
    }
  }

  /**
   * Get a cell from the grid.
   *
   * @param sheetName - name of the grid that holds the cell.
   * @param key - grid index of the cell.
   * @return cell.
   */
  getCell(sheetName: string, key: string): CellObject {
    return this.spreadsheet.getSheetByName(sheetName).getCell(key);
  }

  getSheet(name: string) {
    return this.spreadsheet.getSheetByName(name);
  }

  /**
   * If the value passed in is a CellQuery, lookup the value. If not, return value unchanged.
   *
   * @param queryOrValue - value that is possibly a range, and should be resolved/looked-up.
   * @return resolved value or given range if not.
   */
  private lookup: LookupFunction = (queryOrValue: Complex) => {
    if (queryOrValue instanceof CellQuery) {
      return this.performQuery(queryOrValue as CellQuery);
    }
    return queryOrValue;
  };

  /**
   * If the value passed in is a CellQuery, perform collateral lookup. If not return value unchanged.
   *
   * @param origin       - origin to use in potential collateral lookup.
   * @param queryOrValue - query or value.
   * @return
   */
  private collateralLookup: CollateralLookupFunction = (
    origin: SheetColumnRowKey,
    queryOrValue: Complex
  ) => {
    if (queryOrValue instanceof CellQuery) {
      return this.performCollateralLookup(origin, queryOrValue as CellQuery);
    }
    return queryOrValue;
  };

  /**
   * Process a single cell, setting the user entered value if the cell does not contain a formula, or computing the
   * cell's formula if it has one.
   *
   * @param key - where this cell will be stored in the grid.
   */
  private processCell(key: SheetColumnRowKey) {
    const a1Key = AlphaUtils.oneIndexedNumberToLetter(key.column + 1) + `${key.row + 1}`;
    const cell = this.getCell(key.sheet, a1Key);
    if (isNotNull(cell) && !this.hasCellBeenComputed(key)) {
      this.computationOrderKeyStack.push(key);
      this.computeCell(key, cell);
      this.computationOrderKeyStack.pop();
    }
  }

  /**
   * Has the cell at this location been computed?
   * @param key of cell
   */
  private hasCellBeenComputed(key: SheetColumnRowKey) {
    return this.computationMarker[key.sheet][key.toA1()] || false;
  }

  /**
   * Mark the cell at this location as computed.
   * @param key of cell.
   */
  private markCellAsComputed(key: SheetColumnRowKey) {
    return (this.computationMarker[key.sheet][key.toA1()] = true);
  }

  /**
   * Compute a single cell, recursively computing other cells if necessary, ultimately storing the final cell in the
   * grid.
   *
   * @param key - where this cell will be stored in the grid.
   * @param cell - cell to compute.
   */
  private computeCell(key: SheetColumnRowKey, cell: CellObject) {
    const sheet = this.spreadsheet.getSheetByName(key.sheet);
    const columnRowKey = key.toColumnRowKey();
    const a1 = columnRowKey.toA1();
    const codeExecutor = new CodeExecutor(
      this.variables,
      this.lookup,
      this.collateralLookup,
      this.formulaCaller
    );
    try {
      const result = codeExecutor.execute(key, cell);
      if (result instanceof Grid) {
        try {
          const gridToProject = <Grid<Computed>>result;
          this.checkProjectionValidity(key, gridToProject.getColumns(), gridToProject.getRows());
          this.performProjection(key, gridToProject);
        } catch (exception) {
          sheet[a1] = {
            ...sheet[a1],
            t: "e",
            v: Converters.castAsF7Exception(exception),
          };
          this.markCellAsComputed(key);
        }
      } else {
        sheet[a1] = {
          ...sheet[a1],
          t: excelDataTypeFromActualType(result),
          v: result,
        };
        this.markCellAsComputed(key);
      }
    } catch (exception) {
      sheet[a1] = {
        ...sheet[a1],
        t: "e",
        v: Converters.castAsF7Exception(exception),
      };
      this.markCellAsComputed(key);
    }
  }

  /**
   * For a given key, project the values onto the grid. This should only be called after the projection validity has
   * been checked.
   *
   * @param origin - starting value of computed grid.
   * @param computedGrid - computed grid to project, starting at key.
   */
  private performProjection(origin: SheetColumnRowKey, computedGrid: Grid<Computed>) {
    const sheet = this.spreadsheet.getSheetByName(origin.sheet);
    for (let row = computedGrid.getRows() - 1; row >= 0; row--) {
      for (let column = computedGrid.getColumns() - 1; column >= 0; column--) {
        const projectedValue = computedGrid.get(column, row);
        const projectedKey = new ColumnRowKey(origin.column + column, origin.row + row);
        sheet.setCell(projectedKey.toA1(), cellObjectFromProjection(projectedValue as any));
        this.markCellAsComputed(SheetColumnRowKey.from(origin.sheet, projectedKey));
      }
    }
  }

  /**
   * At a key, for a number of columns and rows, check to ensure that the values we're potentially projecting won't
   * overwrite existing cell values.
   *
   * @param origin - to start at
   * @param columns - number of columns forward to project. If it's 1, then we're just projecting to the current key.
   * @param rows - number of rows forward to project. If it's 1, then we're just projecting to the current key.
   */
  private checkProjectionValidity(origin: SheetColumnRowKey, columns: number, rows: number) {
    const highColumnIndex = origin.column + columns - 1;
    const lowColumnIndex = origin.column;
    const highRowIndex = origin.row + rows - 1;
    const lowRowIndex = origin.row;
    const sheet = this.spreadsheet.getSheetByName(origin.sheet);
    const gridBoundaries = Parsers.parseReferencePair(sheet["!ref"]);
    if (
      highColumnIndex > gridBoundaries.endColumnIndex ||
      highRowIndex > gridBoundaries.endRowIndex
    ) {
      throw new RefException(`Result was not projected because it is out of range.`);
    }
    for (let row = highRowIndex; row >= lowRowIndex; row--) {
      for (let column = highColumnIndex; column >= lowColumnIndex; column--) {
        const key = new ColumnRowKey(column, row);
        if (!key.equals(origin.toColumnRowKey())) {
          const a1Key = AlphaUtils.oneIndexedNumberToLetter(key.column + 1) + `${key.row + 1}`;
          const found = this.getCell(origin.sheet, a1Key);
          if (isNotNull(found) && isNotNull(found.v) && isNotNull(found.w)) {
            throw new RefException(
              `Result was not projected because it would overwrite data in ${a1Key}`
            );
          }
        }
      }
    }
  }

  /**
   * Ensure that a sheet exists, throwing a #REF! error if it does not.
   *
   * @param sheetTitle to check for.
   */
  private checkSheetExistence(sheetTitle: string) {
    const sheet = this.spreadsheet.getSheetByName(sheetTitle);
    if (isUndefined(sheet)) {
      throw new RefException(`Unresolved sheet name '${sheet}'.`);
    }
  }

  /**
   * Given a query, traverse the entire range, collecting values into a grid. This is a little complex, so here's the
   * outline of how it works:
   *
   * 1) Get the grid from the query. If we don't have a grid we can't proceed.
   * 2) Get the grid, and build a query that represents the boundaries of the grid.
   * 3) If the grid boundaries don't intersect the query boundaries, they are disjoint sets. Return empty grid.
   * 4) Take the input query and constrict it to the size of the grid we're querying.
   * 5) We now know we're going to be iterating over something. Start iterator, create grid that we'll be building.
   * 6) For each key of the iterator, make sure we're not in a cycle.
   * 7) Process the cell - possibly computing it's value if we've not done that yet.
   * 8) Add the cell to the return grid.
   * 9) Return the grid.
   *
   * @param query - to resolve.
   * @return grid of values, or single value if we're using a collateral index.
   */
  private performQuery(query: CellQuery): string | number | CellQuery | F7Exception | Grid<any> {
    const sheetName = query.getFormattedSheetName();
    if (isNull(sheetName) || isUndefined(sheetName)) {
      throw new Error("CellQuery does not have name.");
    }
    this.checkSheetExistence(sheetName);

    const sheet = this.spreadsheet.getSheetByName(sheetName);
    const gridBoundaries = Parsers.parseReferencePair(sheet["!ref"]);
    const boundedQuery = query.toBounded(gridBoundaries.endColumnIndex, gridBoundaries.endRowIndex);

    const endColumn = boundedQuery.columns.upperEndpoint();
    const endRow = boundedQuery.rows.upperEndpoint();
    const startColumn = boundedQuery.columns.lowerEndpoint();
    const startRow = boundedQuery.rows.lowerEndpoint();
    const rangeGrid = new Grid(0, 0);

    for (let row = endRow; row >= startRow; row--) {
      for (let column = endColumn; column >= startColumn; column--) {
        const key = new ColumnRowKey(column, row);
        const sheetKey = SheetColumnRowKey.from(sheetName, key);
        if (this.computationOrderKeyStack.findIndex((i) => sheetKey.equals(i)) > -1) {
          throw new RefException("Cycle detected.");
        }
        this.processCell(SheetColumnRowKey.from(sheetName, key));
        const a1Key = AlphaUtils.oneIndexedNumberToLetter(key.column + 1) + `${key.row + 1}`;
        const cell = this.getCell(sheetName, a1Key);
        if (isNotNull(cell)) {
          rangeGrid.set(key.column - startColumn, key.row - startRow, cell.v);
        } else {
          rangeGrid.setNull(key.column - startColumn, key.row - startRow);
        }
      }
    }

    return rangeGrid;
  }

  /**
   * A collateral lookup is when a cell uses a query of a parallel range in only one dimension.
   * @param origin - origin cell.
   * @param query - cell query.
   */
  private performCollateralLookup(origin: SheetColumnRowKey, query: CellQuery): any {
    const sheetName = query.getFormattedSheetName();
    if (isNull(sheetName) || isUndefined(sheetName)) {
      throw new Error("CellQuery does not have name.");
    }
    this.checkSheetExistence(sheetName);
    const sheet = this.spreadsheet.getSheetByName(sheetName);
    const gridBoundaries = Parsers.parseReferencePair(sheet["!ref"]);
    const boundedQuery = query.toBounded(gridBoundaries.endColumnIndex, gridBoundaries.endRowIndex);

    const endColumn = boundedQuery.columns.upperEndpoint();
    const endRow = boundedQuery.rows.upperEndpoint();
    const startColumn = boundedQuery.columns.lowerEndpoint();
    const startRow = boundedQuery.rows.lowerEndpoint();

    // Case 0: Single value, just return it.
    if (startColumn == endColumn && startRow == endRow) {
      const key = SheetColumnRowKey.from(sheetName, new ColumnRowKey(startColumn, startRow));
      return this.performSingleCellLookup(key);
    }

    // Case 1: Row-wise - query is a single row. Use column as collateral index.
    if (
      startColumn != endColumn &&
      startRow == endRow &&
      origin.column >= startColumn &&
      origin.column <= endColumn
    ) {
      const key = SheetColumnRowKey.from(sheetName, new ColumnRowKey(origin.column, startRow));
      return this.performSingleCellLookup(key);
    }

    // Case 2: Column-wise - query is single column. Use row as collateral index.
    if (startColumn == endColumn && origin.row >= startRow && origin.row <= endRow) {
      const key = SheetColumnRowKey.from(sheetName, new ColumnRowKey(startColumn, origin.row));
      return this.performSingleCellLookup(key);
    }

    // Case 3: Other grid - query is multi-row, multi-column, and other grid. Use both as collateral index.
    if (
      origin.column >= startColumn &&
      origin.column <= endColumn &&
      origin.row >= startRow &&
      origin.row <= endRow &&
      origin.sheet !== query.getFormattedSheetName()
    ) {
      const key = SheetColumnRowKey.from(sheetName, new ColumnRowKey(origin.column, origin.row));
      return this.performSingleCellLookup(key);
    }

    // Case 4: This grid, and exists. #REF! error - we're looking up the origin.
    if (
      origin.column >= startColumn &&
      origin.column <= endColumn &&
      origin.row >= startRow &&
      origin.row <= endRow &&
      origin.sheet === query.getFormattedSheetName()
    ) {
      return new RefException("Cycle detected");
    }

    // Case 5: This grid, not contained. #VALUE! error.
    return new ValueException("Array value could not be found");
  }

  /**
   * Lookup a single cell by key, potentially processing it.
   * @param key - of cell.
   */
  private performSingleCellLookup(key: SheetColumnRowKey) {
    if (this.computationOrderKeyStack.findIndex((i) => key.equals(i)) > -1) {
      throw new RefException("Cycle detected.");
    }
    this.processCell(key);
    const a1Key = AlphaUtils.oneIndexedNumberToLetter(key.column + 1) + `${key.row + 1}`;
    const cell = this.getCell(key.sheet, a1Key);
    if (isNotNull(cell)) {
      return cell.v;
    }
    return null;
  }
}
