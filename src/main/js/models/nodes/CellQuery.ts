import { ValueException } from "../../errors/ValueException";
import { AlphaUtils } from "../../utils/AlphaUtils";
import { Range } from "../common/Range";
import { isNull } from "../../common/utils/Types";

/**
 * A cell query represents a query of one or more cells over a single sheet. It is composed of
 * two-dimensional ranges: rows and columns, both of which can be unbounded at the lower, or
 * upper ends of the range.
 */
export class CellQuery extends Object {
  /**
   * Range of columns.
   */
  readonly columns: Range;
  /**
   * Range of rows.
   */
  readonly rows: Range;
  /**
   * Name of the sheet being queried.
   */
  private sheet: string = null;

  constructor(sheet: string, columns: Range, rows: Range) {
    super();
    this.sheet = sheet;
    this.columns = columns;
    this.rows = rows;
  }

  static builder(query?: CellQuery): CellQueryBuilder {
    return new CellQueryBuilder(query);
  }

  getFormattedSheetName(): string {
    if (this.sheet && this.sheet.startsWith("'")) {
      return this.sheet.substr(1, this.sheet.length - 2);
    }
    return this.sheet;
  }

  /**
   * Does this query intersect with the other one?
   *
   * @param other - other query.
   * @return true if at least one cell overlaps.
   */
  intersects(other: CellQuery): boolean {
    return other.columns.isConnected(this.columns) && other.rows.isConnected(this.rows);
  }

  /**
   * Bound this query using an upper column and upper row. Will default to the lower of the upper column passed in,
   * and the upper column bound existing on the columns range. Same for rows.
   *
   * @param upperColumn - upper endpoint for columns to default to.
   * @param upperRow    - upper endpoint of rows to default to.
   * @return bounded query.
   */
  toBounded(upperColumn: number, upperRow: number): CellQuery {
    if (
      !Range.closed(0, upperColumn).isConnected(this.columns) &&
      !Range.closed(0, upperRow).isConnected(this.rows)
    ) {
      return this;
    }
    return CellQuery.builder(this)
      .columnsBetween(
        this.columns.lowerEndpoint(),
        Math.min(
          upperColumn,
          this.columns.hasUpperBound() ? this.columns.upperEndpoint() : upperColumn
        )
      )
      .rowsBetween(
        this.rows.lowerEndpoint(),
        Math.min(upperRow, this.rows.hasUpperBound() ? this.rows.upperEndpoint() : upperRow)
      )
      .build();
  }

  toString() {
    const sheet = isNull(this.sheet) ? "" : `${this.sheet}!`;
    const upperColumn = this.columns.hasUpperBound()
      ? AlphaUtils.oneIndexedNumberToLetter(this.columns.upper + 1)
      : "";
    const lowerColumn = this.columns.hasLowerBound()
      ? AlphaUtils.oneIndexedNumberToLetter(this.columns.lower + 1)
      : "";
    const upperRow = this.rows.hasUpperBound() ? (this.rows.upper + 1).toString() : "";
    const lowerRow = this.rows.hasLowerBound() ? (this.rows.lower + 1).toString() : "";
    return `${sheet}${lowerColumn}${lowerRow}:${upperColumn}${upperRow}`;
  }
}

/**
 * Easier way to build a query.
 */
class CellQueryBuilder {
  private sheet: string = null;
  private columns: Range;
  private rows: Range;

  constructor(cellQuery?: CellQuery) {
    if (cellQuery) {
      this.sheet = cellQuery.getFormattedSheetName();
      this.columns = cellQuery.columns;
      this.rows = cellQuery.rows;
    }
  }

  setSheet(sheet: string): CellQueryBuilder {
    this.sheet = sheet;
    return this;
  }

  columnsBetween(low: number, high: number): CellQueryBuilder {
    this.columns = Range.closed(low, high);
    return this;
  }

  columnsBetweenAlpha(low: string, high: string): CellQueryBuilder {
    this.columns = Range.closed(AlphaUtils.columnToInt(low), AlphaUtils.columnToInt(high));
    return this;
  }

  rowsBetween(low: number, high: number): CellQueryBuilder {
    this.rows = Range.closed(low, high);
    return this;
  }

  rowsBetweenAlpha(low: string, high: string): CellQueryBuilder {
    this.rows = Range.closed(parseInt(low) - 1, parseInt(high) - 1);
    return this;
  }

  columnsStartingAt(low: number): CellQueryBuilder {
    this.columns = Range.atLeast(low);
    return this;
  }

  rowsStartingAt(low: number): CellQueryBuilder {
    this.rows = Range.atLeast(low);
    return this;
  }

  openColumnsStartingAtZero(): CellQueryBuilder {
    this.columns = Range.atLeast(0);
    return this;
  }

  openColumnsStartingAt(columnString: string): CellQueryBuilder {
    this.columns = Range.atLeast(AlphaUtils.columnToInt(columnString));
    return this;
  }

  openColumnsStartingAtNumber(column: number): CellQueryBuilder {
    this.columns = Range.atLeast(column);
    return this;
  }

  openRowsStartingAtZero(): CellQueryBuilder {
    this.rows = Range.atLeast(0);
    return this;
  }

  openRowsStartingAtNumber(row: number): CellQueryBuilder {
    this.rows = Range.atLeast(row);
    return this;
  }

  openRowsStartingAt(rowString: string): CellQueryBuilder {
    this.rows = Range.atLeast(AlphaUtils.rowToInt(rowString));
    return this;
  }

  expand(query: CellQuery): CellQueryBuilder {
    if (this.columns == null) {
      this.columns = query.columns;
    } else {
      this.columns = this.columns.span(query.columns);
    }
    if (this.rows == null) {
      this.rows = query.rows;
    } else {
      this.rows = this.rows.span(query.rows);
    }
    if (this.sheet !== null && this.sheet !== query.getFormattedSheetName()) {
      throw new ValueException("Different sheet names.");
    } else {
      this.sheet = query.getFormattedSheetName();
    }

    return this;
  }

  build(): CellQuery {
    return new CellQuery(this.sheet, this.columns, this.rows);
  }
}
