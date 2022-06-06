package io.protobase.f7.spreadsheet;

import com.google.common.collect.ImmutableMap;
import io.protobase.f7.antlr.F7Lexer;
import io.protobase.f7.antlr.F7Parser;
import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.errors.ParseException;
import io.protobase.f7.errors.RefException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.formulas.FormulaCaller;
import io.protobase.f7.models.Cell;
import io.protobase.f7.models.CellQuery;
import io.protobase.f7.models.ColumnRowKey;
import io.protobase.f7.models.ErrorNode;
import io.protobase.f7.models.Grid;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.models.Node;
import io.protobase.f7.transpiler.TranspilationVisitor;
import io.protobase.f7.utils.Converters;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;

/**
 * Execute F7 code in the context of a spreadsheet, storing values in a grid.
 */
public class SpreadsheetExecutor {
  /**
   * All bound formulas.
   */
  private FormulaCaller formulaCaller;

  /**
   * Stores the cells that we've looked up so we can keep track of cyclical dependencies between cells.
   */
  private Stack<GridColumnRowKey> computationOrderKeyStack = new Stack<>();

  /**
   * Named grids that will store cells.
   */
  private Map<String, Worksheet> worksheets;

  /**
   * Named variables available to the spreadsheet.
   */
  private Map<String, Node> variables;

  /**
   * Private constructor that accepts worksheets.
   *
   * @param worksheets - of raw cell values.
   */
  private SpreadsheetExecutor(Map<String, Worksheet> worksheets, Map<String, String> namedRanges) {
    // Set worksheets. These are what we'll be running.
    this.worksheets = worksheets;

    // Build variables, overriding with defaults.
    ImmutableMap.Builder<String, Node> variablesBuilder = ImmutableMap.builder();
    for (Map.Entry<String, String> namedRangeEntry : namedRanges.entrySet()) {
      String name = namedRangeEntry.getKey().toUpperCase();
      String rangeString = namedRangeEntry.getValue();
      variablesBuilder.put(name, this.parseNamedRange(rangeString));
    }
    variables = variablesBuilder.build();

    // Bind formula caller.
    this.formulaCaller = new FormulaCaller(this::lookup, this::collateralLookup);
  }

  /**
   * Utility to convert a string input to tokens that the parser can read.
   *
   * @param input - F7 code.
   * @return token stream.
   * @throws IOException - if there's a problem reading the stream.
   */
  private static CommonTokenStream stringToTokens(String input) throws IOException {
    return new CommonTokenStream(new F7Lexer(CharStreams.fromStream(
        new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8)));
  }

  /**
   * Start a builder.
   *
   * @return builder.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Parse a range to node for execution.
   *
   * @param range - range string
   * @return range node.
   */
  private Node parseNamedRange(String range) {
    try {
      CommonTokenStream tokens = stringToTokens(range);
      try {
        F7Parser parser = new F7Parser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new ParseErrorListener());
        return new TranspilationVisitor().visit(parser.range());
      } catch (ParseCancellationException parseException) {
        return new ErrorNode(new ParseException("Parse error"));
      } catch (F7Exception f7Exception) {
        return new ErrorNode(f7Exception);
      }
    } catch (IOException io) {
      return new ErrorNode(new ParseException("Parse error"));
    }
  }

  /**
   * If the value passed in is a CellQuery, lookup the value. If not, return value unchanged.
   *
   * @param queryOrValue - value that is possibly a range, and should be resolved/looked-up.
   * @return resolved value or given range if not.
   */
  private Object lookup(Object queryOrValue) {
    if (queryOrValue instanceof CellQuery) {
      return this.performQuery(((CellQuery) queryOrValue));
    }
    return queryOrValue;
  }

  /**
   * If the value passed in is a CellQuery, perform collateral lookup. If not return value unchanged.
   *
   * @param origin       - origin to use in potential collateral lookup.
   * @param queryOrValue - query or value.
   * @return
   */
  private Object collateralLookup(GridColumnRowKey origin, Object queryOrValue) {
    if (queryOrValue instanceof CellQuery) {
      return this.performCollateralLookup(origin, ((CellQuery) queryOrValue));
    }
    return queryOrValue;
  }

  /**
   * Run the spreadsheet.
   */
  public void execute() {
    // For each worksheet, iterate through the cells in reverse (bottom to top, right to left), computing the cells.
    for (Map.Entry<String, Worksheet> entry : worksheets.entrySet()) {
      String gridName = entry.getKey();
      Grid<Cell> currentGrid = entry.getValue().getCells();
      currentGrid.reverseIndexIterator().forEachRemaining(index -> processCell(new GridColumnRowKey(gridName, index)));
    }
  }

  /**
   * Process a single cell, setting the raw value if the cell does not contain a formula, or computing the cell's
   * formula if it has one.
   *
   * @param key - where this cell will be stored in the grid.
   */
  private void processCell(GridColumnRowKey key) {
    Optional<Cell> cell = getCell(key.getGridIndex(), key.toColumnRowKey());
    if (cell.isPresent() && !cell.get().isComputed()) {
      computationOrderKeyStack.push(key);
      computeCell(key, cell.get().getRawValue());
      computationOrderKeyStack.pop();
    }
  }

  /**
   * Compute a single cell, recursively computing other cells if necessary, ultimately storing the final cell in the
   * grid.
   *
   * @param key      - where this cell will be stored in the grid.
   * @param rawInput - cell raw value.
   */
  private void computeCell(GridColumnRowKey key, String rawInput) {
    Worksheet worksheet = worksheets.get(key.getGridIndex());
    ColumnRowKey columnRowKey = key.toColumnRowKey();
    F7CodeExecutor codeExecutor = new F7CodeExecutor(variables, this::lookup, this::collateralLookup, formulaCaller);
    try {
      Object result = codeExecutor.execute(key, rawInput);
      if (result instanceof Grid) {
        try {
          Grid<Object> gridToProject = Converters.castAsDataGrid(result);
          checkProjectionValidity(key, gridToProject.getColumnSize(), gridToProject.getRowSize());
          performProjection(key, gridToProject);
        } catch (F7Exception exception) {
          worksheet.setCellComputedValue(columnRowKey, exception);
        }
      } else {
        worksheet.setCellComputedValue(columnRowKey, result);
      }
    } catch (F7Exception exception) {
      worksheet.setCellComputedValue(columnRowKey, exception);
    }
  }

  /**
   * For a given key, project the values onto the grid. This should only be called after the projection validity has
   * been checked.
   *
   * @param origin       - starting value of computed grid.
   * @param computedGrid - computed grid to project, starting at key.
   */
  private void performProjection(GridColumnRowKey origin, Grid<Object> computedGrid) {
    Worksheet worksheet = worksheets.get(origin.getGridIndex());
    Iterator<ColumnRowKey> iterator = computedGrid.indexIterator();
    while (iterator.hasNext()) {
      ColumnRowKey localKey = iterator.next();
      Object projectedValue = computedGrid.get(localKey);
      ColumnRowKey projectedKey = new ColumnRowKey(origin.getColumnIndex() + localKey.getColumnIndex(),
          origin.getRowIndex() + localKey.getRowIndex());
      worksheet.setCell(projectedKey, new Cell(projectedValue, origin.toColumnRowKey()));
    }
  }

  /**
   * At a key, for a number of columns and rows, check to ensure that the values we're potentially projecting won't
   * overwrite existing cell values.
   *
   * @param origin  - to start at
   * @param columns - number of columns forward to project. If it's 1, then we're just projecting to the current key.
   * @param rows    - number of rows forward to project. If it's 1, then we're just projecting to the current key.
   */
  private void checkProjectionValidity(GridColumnRowKey origin, int columns, int rows) {
    Iterator<ColumnRowKey> iterator = worksheets.get(origin.getGridIndex()).getCells().reverseIndexIterator(
        origin.getColumnIndex() + columns - 1, origin.getColumnIndex(), origin.getRowIndex() + rows - 1,
        origin.getRowIndex());
    while (iterator.hasNext()) {
      ColumnRowKey scanKey = iterator.next();
      if (!scanKey.equals(origin.toColumnRowKey())) {
        Optional<Cell> found = getCell(origin.getGridIndex(), scanKey);
        if (found.isPresent()) {
          throw new RefException(String.format("Grid result was not projected because it would overwrite data in %s",
              scanKey.toString()));
        }
      }
    }
  }

  /**
   * Get a cell from the grid.
   *
   * @param gridName - name of the grid that holds the cell.
   * @param key      - grid index of the cell.
   * @return cell.
   */
  public Optional<Cell> getCell(String gridName, ColumnRowKey key) {
    return Optional.ofNullable(worksheets.get(gridName).getCell(key));
  }

  /**
   * Ensure that a worksheet exists, throwing a #REF! error if it does not.
   *
   * @param worksheet to check for.
   */
  private void checkWorksheetExistence(String worksheet) {
    if (!worksheets.containsKey(worksheet)) {
      throw new RefException(String.format("Unresolved grid name '%s'.", worksheet));
    }
  }

  /**
   * Given a query, traverse the entire range, collecting values into a grid. This is a little complex, so here's the
   * outline of how it works:
   * <p>
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
  private Grid performQuery(CellQuery query) {
    String gridName = query.getGrid().orElseThrow(() -> new IllegalStateException("CellQuery does not have name."));
    checkWorksheetExistence(gridName);

    Grid<Cell> grid = worksheets.get(gridName).getCells();
    CellQuery gridBoundaries = CellQuery.builder()
        .columnsBetween(0, grid.getColumnSize())
        .rowsBetween(0, grid.getRowSize())
        .build();
    if (!gridBoundaries.intersects(query)) {
      return Grid.builder().build();
    }
    CellQuery boundedQuery = query.toBounded(grid.getColumnSize(), grid.getRowSize());

    int endColumn = boundedQuery.getEndColumn();
    int endRow = boundedQuery.getEndRow();
    int startColumn = boundedQuery.getStartColumn();
    int startRow = boundedQuery.getStartRow();

    Iterator<ColumnRowKey> iterator = worksheets.get(gridName).getCells().reverseIndexIterator(endColumn,
        startColumn, endRow, startRow);
    Grid<Object> rangeGrid = new Grid<>();

    while (iterator.hasNext()) {
      ColumnRowKey key = iterator.next();
      if (computationOrderKeyStack.contains(new GridColumnRowKey(gridName, key))) {
        throw new RefException("Cycle detected.");
      }
      processCell(new GridColumnRowKey(gridName, key));
      Optional<Cell> cell = getCell(gridName, key);
      if (cell.isPresent()) {
        rangeGrid.set(key.getColumnIndex() - startColumn, key.getRowIndex() - startRow, cell.get().getComputedValue());
      } else {
        rangeGrid.setEmpty(key.getColumnIndex() - startColumn, key.getRowIndex() - startRow);
      }
    }

    return rangeGrid;
  }

  private Object performCollateralLookup(GridColumnRowKey origin, CellQuery query) {
    String gridName = query.getGrid().orElseThrow(() -> new IllegalStateException("CellQuery does not have name."));
    checkWorksheetExistence(gridName);

    Grid<Cell> grid = worksheets.get(gridName).getCells();
    CellQuery gridBoundaries = CellQuery.builder()
        .columnsBetween(0, grid.getColumnSize())
        .rowsBetween(0, grid.getRowSize())
        .build();
    if (!gridBoundaries.intersects(query)) {
      return Grid.builder().build();
    }
    CellQuery boundedQuery = query.toBounded(grid.getColumnSize(), grid.getRowSize());

    int endColumn = boundedQuery.getEndColumn();
    int endRow = boundedQuery.getEndRow();
    int startColumn = boundedQuery.getStartColumn();
    int startRow = boundedQuery.getStartRow();

    // Case 0: Single value, just return it.
    if (startColumn == endColumn && startRow == endRow) {
      GridColumnRowKey key = new GridColumnRowKey(gridName, new ColumnRowKey(startColumn, startRow));
      return performSingleCellLookup(key);
    }

    // Case 1: Row-wise - query is a single row. Use column as collateral index.
    if (startColumn != endColumn && startRow == endRow &&
        origin.getColumnIndex() >= startColumn && origin.getColumnIndex() <= endColumn) {
      GridColumnRowKey key = new GridColumnRowKey(gridName, new ColumnRowKey(origin.getColumnIndex(), startRow));
      return performSingleCellLookup(key);
    }

    // Case 2: Column-wise - query is single column. Use row as collateral index.
    if (startColumn == endColumn &&
        origin.getRowIndex() >= startRow && origin.getRowIndex() <= endRow) {
      GridColumnRowKey key = new GridColumnRowKey(gridName, new ColumnRowKey(startColumn, origin.getRowIndex()));
      return performSingleCellLookup(key);
    }

    // Case 3: Other grid - query is multi-row, multi-column, and other grid. Use both as collateral index.
    if (origin.getColumnIndex() >= startColumn && origin.getColumnIndex() <= endColumn &&
        origin.getRowIndex() >= startRow && origin.getRowIndex() <= endRow &&
        !origin.getGridIndex().equals(query.getGrid().get())) {
      GridColumnRowKey key = new GridColumnRowKey(gridName, new ColumnRowKey(origin.getColumnIndex(), origin.getRowIndex()));
      return performSingleCellLookup(key);
    }

    // Case 4: This grid, and exists. #REF! error - we're looking up the origin.
    if (origin.getColumnIndex() >= startColumn && origin.getColumnIndex() <= endColumn &&
        origin.getRowIndex() >= startRow && origin.getRowIndex() <= endRow &&
        origin.getGridIndex().equals(query.getGrid().get())) {
      return new RefException("Cycle detected");
    }

    // Case 5: This grid, not contained. #VALUE! error.
    return new ValueException("Array value could not be found");
  }

  private Object performSingleCellLookup(GridColumnRowKey key) {
    if (computationOrderKeyStack.contains(key)) {
      throw new RefException("Cycle detected.");
    }
    processCell(key);
    Optional<Cell> cell = getCell(key.getGridIndex(), key.toColumnRowKey());
    if (cell.isPresent()) {
      return cell.get().getComputedValue();
    }
    return null;
  }

  /**
   * Simple way to build an executor.
   */
  public static class Builder {
    Map<String, Grid<Cell>> grids = new HashMap<>();
    Map<String, String> variables = new HashMap<>();

    public Builder addGrids(Map<String, Grid<Cell>> toAdd) {
      grids.putAll(toAdd);
      return this;
    }

    public Builder addGrid(String name, Map<ColumnRowKey, String> values) {
      Grid<Cell> grid = new Grid<>();
      values.forEach((key, value) -> grid.set(key, new Cell(value)));
      grids.put(name, grid);
      return this;
    }

    public Builder addNamedRanges(Map<String, String> ranges) {
      variables.putAll(ranges);
      return this;
    }

    public SpreadsheetExecutor build() {
      Map<String, Worksheet> worksheets = new HashMap<>();
      for (Map.Entry<String, Grid<Cell>> entry : grids.entrySet()) {
        worksheets.put(entry.getKey(), new Worksheet(entry.getKey(), entry.getValue()));
      }
      return new SpreadsheetExecutor(worksheets, variables);
    }
  }
}
