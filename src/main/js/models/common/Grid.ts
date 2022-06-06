import { Reducers } from "../../utils/Reducers";
import { Computed } from "./Types";
import { InvalidArgumentError } from "../../common/errors/InvalidArgumentError";
import { isUndefined } from "../../common/utils/Types";

/**
 * A grid is the fundamental 2D data structure of F7. It should NOT be nested. Values are either filled with an
 * object, or left null, although the meaning of an intentionally stored null value depends on where this structure is
 * used.
 *
 * @param <T> - type stored.
 */
export class Grid<T> {
  /**
   * Store each value by a row-column key.
   * The thing to remember here is that the major dimension is ROWS.
   */
  private grid: Array<Array<T>>;
  /**
   * A grid can have a column and row size even if there are no values. But there are many places where we need to know
   * how big the grid is, regardless of how full it is. This is the number of columns in this grid.
   */
  private columns = 0;
  /**
   * This is the number of rows in this grid.
   */
  private rows = 0;

  constructor(columns: number, rows: number) {
    if (columns < 0 || rows < 0) {
      throw new InvalidArgumentError("Column size or row size for grid cannot be less than 0.");
    }
    this.columns = columns;
    this.rows = rows;
    this.grid = new Array<Array<T>>(rows).fill(undefined);
    this.grid = this.grid.map((_) => new Array<T>(columns).fill(undefined));
  }

  /**
   * Start a grid builder.
   * @deprecated
   */
  static builder<T = Computed>(): GridBuilder<T> {
    return new GridBuilder<any>();
  }

  /**
   * Create a grid from an array grid, where the major dimension is rows.
   * @param data - to fill new grid with.
   */
  static from<T>(data: Array<Array<T>>): Grid<T> {
    const created = new Grid<T>(0, 0);
    data.forEach((rowArray, row) =>
      rowArray.forEach((value, column) => created.set(column, row, value))
    );
    return created;
  }

  /**
   * Set a value in the grid, expanding the grid if it does not contain the index.
   * @param column - column index to set.
   * @param row - row index to set.
   * @param value - value to set.
   */
  set(column: number, row: number, value: T) {
    this.expand(column + 1, row + 1);
    this.grid[row][column] = value;
  }

  /**
   * Get a value if it exists in the grid, defaulting to null when it does not exist or it is undefined.
   * @param column - column index.
   * @param row - row index.
   */
  get(column: number, row: number) {
    if (column > this.columns - 1 || row > this.rows - 1) {
      return null;
    }
    const value = this.grid[row][column];
    return isUndefined(value) ? null : value;
  }

  /**
   * Get the number of columns in this grid.
   */
  getColumns(): number {
    return this.columns;
  }

  /**
   * Get the number of rows in this grid.
   */
  getRows(): number {
    return this.rows;
  }

  /**
   * Expand number of columns, if columns is larger than current number of columns.
   * @param columns - to possibly expand to.
   */
  setColumns(columns: number) {
    if (columns > this.columns) {
      this.grid = this.grid.map((row) =>
        row.concat(new Array(columns - this.columns).fill(undefined))
      );
      this.columns = columns;
    }
  }

  /**
   * Expand number of rows, if rows is larger than current number of rows.
   * @param rows - to possibly expand to.
   */
  setRows(rows: number) {
    if (rows > this.rows) {
      this.grid = this.grid.concat(
        new Array(rows - this.rows)
          .fill(null)
          .map((_) => new Array<T>(this.columns).fill(undefined))
      );
      this.rows = rows;
    }
  }

  /**
   * Expand this grid to the number of rows and columns, if they are larger than existing rows and columns respectively.
   * @param columns - new column count.
   * @param rows - new column count.
   */
  expand(columns: number, rows: number) {
    this.setRows(rows);
    this.setColumns(columns);
  }

  /**
   * Resize the current grid, retaining values if they fall inside the range of the new grid, removing them if not.
   * If we're expanding the grid, this is basically the same as using the expand method.
   * @param columns - new number of columns.
   * @param rows - new number of rows.
   */
  resizeWithDelete(columns: number, rows: number) {
    const newGrid = new Grid<T>(columns, rows);
    for (let row = rows - 1; row >= 0; row--) {
      for (let column = columns - 1; column >= 0; column--) {
        newGrid.set(column, row, this.get(column, row));
      }
    }
    this.grid = newGrid.raw();
    this.columns = columns;
    this.rows = rows;
  }

  /**
   * Log to a table for debugging.
   */
  log() {
    console.table(this.grid);
  }

  /**
   * Add one grid to this one in the column-wise dimension (right.)
   *
   * @param other grid
   */
  addGridToRight(other: Grid<T>) {
    const oldGridColumnSize = this.getColumns();
    for (let row = other.getRows() - 1; row >= 0; row--) {
      for (let column = other.getColumns() - 1; column >= 0; column--) {
        this.set(column + oldGridColumnSize, row, other.get(column, row));
      }
    }
  }

  /**
   * Add a single value in the 0th row, after the last column, increasing the column count.
   *
   * @param value - to add.
   */
  addOneToRight(value: T) {
    this.set(this.columns, 0, value);
  }

  /**
   * Add one grid to this one in the row-wise dimension (bottom.)
   *
   * @param other grid
   */
  addGridToBottom(other: Grid<T>) {
    const oldGridRowSize = this.getRows();
    for (let row = other.getRows() - 1; row >= 0; row--) {
      for (let column = other.getColumns() - 1; column >= 0; column--) {
        this.set(column, row + oldGridRowSize, other.get(column, row));
      }
    }
  }

  /**
   * Remove a value at a given row and column.
   *
   * @param column - index.
   * @param row    - index.
   */
  remove(column: number, row: number) {
    if (column < this.columns && row < this.rows) {
      this.grid[row][column] = undefined;
    }
  }

  /**
   * Does this grid only have one value?
   * @return true if it just has one value.
   */
  isSingle(): boolean {
    return this.rows === 1 && this.columns === 1;
  }

  /**
   * Set an empty/blank/null value at a row/column.
   *
   * @param column - index.
   * @param row    - index.
   */
  setNull(column: number, row: number) {
    this.set(column, row, null);
  }

  /**
   * Is the grid empty?
   *
   * @return true if it contains no values.
   */
  isEmpty(): boolean {
    return (
      (this.rows === 0 && this.columns === 0) ||
      this.grid.map((row) => row.filter(isUndefined).length).reduce(Reducers.sum, 0) > 0
    );
  }

  /**
   * Has dimensions (is at least 1x1) but all are undefined.
   */
  hasDimensionsButIsAllUndefined() {
    return this.rows > 0 && this.columns > 0 && this.allAreUndefined();
  }

  /**
   * Map each value to a new Grid.
   * @param mapper - maps instance of T to instance of of R.
   */
  map<R>(mapper: (t: T) => R): Grid<R> {
    const toReturn = new Grid<R>(this.columns, this.rows);
    for (let row = this.getRows() - 1; row >= 0; row--) {
      for (let column = this.getColumns() - 1; column >= 0; column--) {
        toReturn.set(column, row, mapper(this.get(column, row)));
      }
    }
    return toReturn;
  }

  /**
   * Get the raw grid.
   */
  raw(): Array<Array<T>> {
    return this.grid;
  }

  /**
   * Are all values undefined?
   */
  private allAreUndefined(): boolean {
    return this.grid.map((row) => row.filter(isUndefined).length).reduce(Reducers.sum, 0) > 0;
  }
}

/**
 * Grid builder. Basically chains "add" so we can do one-statement building.
 * @deprecated
 */
class GridBuilder<T> {
  private internal: Grid<T> = new Grid<T>(0, 0);

  add(columnIndex: number, rowIndex: number, value: T): GridBuilder<T> {
    this.internal.set(columnIndex, rowIndex, value);
    return this;
  }

  build(): Grid<T> {
    return this.internal;
  }
}
