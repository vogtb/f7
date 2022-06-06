package io.protobase.f7.models;

import com.google.common.base.MoreObjects;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A grid is the fundamental 2D data structure of F7. It should NOT be nested. Values are either filled with an
 * object, or left null, although the meaning of an intentionally stored null value depends on where this structure is
 * used.
 *
 * @param <V> - type stored.
 */
public class Grid<V> extends BaseObject implements Iterable<V> {
  /**
   * Store each value by a column-row key (basically x and y dimensions).
   */
  private Map<ColumnRowKey, V> grid = new HashMap<>();
  /**
   * A grid can have a column and row size even if there are no values. But there are many places where we need to know
   * how big the grid is, regardless of how full it is. This is the number of columns in this grid.
   */
  private int columnSize;
  /**
   * This is the number of rows in this grid.
   */
  private int rowSize;

  /**
   * Create a new grid of a specific size.
   *
   * @param columnSize - greater than 0.
   * @param rowSize    - greater that 0.
   */
  public Grid(int columnSize, int rowSize) {
    if (columnSize < 0 || rowSize < 0) {
      throw new IndexOutOfBoundsException();
    }
    this.columnSize = columnSize;
    this.rowSize = rowSize;
  }

  /**
   * Construct a grid of 0x0 dimensions.
   */
  public Grid() {
    this.columnSize = 0;
    this.rowSize = 0;
  }

  /**
   * Create a grid builder.
   *
   * @return grid builder.
   */
  public static Builder<Object> builder() {
    return new Builder<>();
  }

  /**
   * Set a value by column and row.
   *
   * @param column - column index.
   * @param row    - row index.
   * @param value  - value to set.
   */
  public void set(int column, int row, V value) {
    bumpIndices(column, row);
    grid.put(new ColumnRowKey(column, row), value);
  }

  /**
   * Set a value by key.
   *
   * @param key   - index.
   * @param value - to store
   */
  public void set(ColumnRowKey key, V value) {
    bumpIndices(key.getColumnIndex(), key.getRowIndex());
    grid.put(key, value);
  }

  /**
   * Get a value at a given row or column.
   *
   * @param column - index.
   * @param row    - index.
   * @return value at that index or null.
   */
  public V get(int column, int row) {
    return grid.get(new ColumnRowKey(column, row));
  }

  /**
   * Get a value by key.
   *
   * @param key - index.
   * @return value if found, null if not.
   */
  public V get(ColumnRowKey key) {
    return grid.get(key);
  }

  /**
   * Remove a value at a given row and column.
   *
   * @param column - index.
   * @param row    - index.
   */
  public void remove(int column, int row) {
    grid.remove(new ColumnRowKey(column, row));
  }

  /**
   * Remove a value by index.
   *
   * @param key - index.
   */
  public void remove(ColumnRowKey key) {
    grid.remove(key);
  }

  /**
   * Does this grid only have one value?
   *
   * @return true if it just has one value.
   */
  public boolean isSingle() {
    return getRowSize() == 1 && getColumnSize() == 1;
  }

  /**
   * Set an empty/blank/null value at a row/column.
   *
   * @param column - index.
   * @param row    - index.
   */
  public void setEmpty(int column, int row) {
    set(column, row, null);
  }

  /**
   * Get column size.
   *
   * @return column size.
   */
  public int getColumnSize() {
    return columnSize;
  }

  /**
   * Get row size.
   *
   * @return row size.
   */
  public int getRowSize() {
    return rowSize;
  }

  /**
   * Add one grid to this one in the column-wise dimension (right.)
   *
   * @param other grid
   */
  public void addGridToRight(Grid<V> other) {
    int oldGridColumnSize = columnSize;
    columnSize = columnSize + other.getColumnSize();
    rowSize = Math.max(rowSize, other.getRowSize());
    other.reverseIndexIterator().forEachRemaining(key -> {
      V value = other.get(key.getColumnIndex(), key.getRowIndex());
      set(key.getColumnIndex() + oldGridColumnSize, key.getRowIndex(), value);
    });
  }

  /**
   * Add a single value in the 0th row, after the last column, increasing the column count.
   *
   * @param value - to add.
   */
  public void addOneToRight(V value) {
    columnSize++;
    set(columnSize - 1, 0, value);
  }

  /**
   * Add one grid to this one in the row-wise dimension (bottom.)
   *
   * @param other grid
   */
  public void addGridToBottom(Grid<V> other) {
    int oldGridRowSize = getRowSize();
    rowSize = rowSize + other.getRowSize();
    other.reverseIndexIterator().forEachRemaining(key -> {
      V value = other.get(key.getColumnIndex(), key.getRowIndex());
      set(key.getColumnIndex(), key.getRowIndex() + oldGridRowSize, value);
    });
  }

  /**
   * Ensure indices are within bounds, throwing exception if they're not.
   *
   * @param columnIndex - between 0 and size-1.
   * @param rowIndex    - between 0 and size-1.
   */
  private void bumpIndices(int columnIndex, int rowIndex) {
    columnSize = Math.max(columnIndex + 1, columnSize);
    rowSize = Math.max(rowIndex + 1, rowSize);
  }

  /**
   * Is the grid empty?
   *
   * @return true if it contains no values.
   */
  public boolean isEmpty() {
    return grid.isEmpty();
  }

  /**
   * Is the grid complete?
   *
   * @return true if an only if the number of keys is equal to columns * rows.
   */
  public boolean isComplete() {
    return grid.keySet().size() == (columnSize * rowSize);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("columnSize", columnSize)
        .add("rowSize", rowSize)
        .add("grid", grid)
        .toString();
  }

  @Override
  public Object[] significantAttributes() {
    return new Object[]{
        columnSize,
        rowSize,
        grid
    };
  }

  /**
   * Iterate through grid in no guaranteed order.
   *
   * @return natural iterator.
   */
  public Iterator<V> iterator() {
    return this.reverseIterator();
  }

  /**
   * Column first iterator going column by column, row by row in reverse order. Bottom to top, right to left.
   * then top to bottom.
   *
   * @return reverse iterator.
   */
  public Iterator<V> reverseIterator() {
    return new DataGridIterator<>(this, new DataGridIndexIterator(getColumnSize() - 1, getRowSize() - 1));
  }

  /**
   * Column first iterator going column by column, row by row. Eg: column=0,row=0 then column=1,row=0. Left to right,
   * then top to bottom.
   *
   * @return natural iterator.
   */
  public Iterator<ColumnRowKey> indexIterator() {
    return new DataGridIndexIterator(getColumnSize() - 1, getRowSize() - 1);
  }

  /**
   * Reverse iterator going column by column, row by row, in reverse order.
   *
   * @return natural iterator.
   */
  public Iterator<ColumnRowKey> reverseIndexIterator() {
    return new DataGridReverseIndexIterator(getColumnSize() - 1, getRowSize() - 1);
  }

  /**
   * Reverse iterator starting at a high column and row, going to a low column and row.
   *
   * @param highColumnIndex - high column index to start at, inclusively.
   * @param lowColumnIndex  - low column index to end at, inclusively.
   * @param highRowIndex    - high row index to start at, inclusively.
   * @param lowRowIndex     - low row index to start at, inclusively.
   * @return iterator.
   */
  public Iterator<ColumnRowKey> reverseIndexIterator(int highColumnIndex, int lowColumnIndex, int highRowIndex,
      int lowRowIndex) {
    return new DataGridReverseIndexIterator(highColumnIndex, lowColumnIndex, highRowIndex, lowRowIndex);
  }

  /**
   * Iterates from low to high column indices, left to right, top to bottom.
   */
  private static class DataGridIndexIterator implements Iterator<ColumnRowKey> {
    private int lowColumnIndex;
    private int lowRowIndex;
    private int highColumnIndex;
    private int highRowIndex;
    private int currentColumnIndex;
    private int currentRowIndex;

    private DataGridIndexIterator(int highColumnIndex, int highRowIndex) {
      this.highColumnIndex = highColumnIndex;
      this.highRowIndex = highRowIndex;
      this.currentColumnIndex = lowColumnIndex - 1;
      this.currentRowIndex = lowRowIndex;
    }

    /**
     * Has next if we've not reached the last row index and last column index.
     *
     * @return true if we've not reached the last row index and last column index.
     */
    public boolean hasNext() {
      return (currentColumnIndex < highColumnIndex || currentRowIndex < highRowIndex);
    }

    /**
     * Get the index pair.
     *
     * @return next index pair.
     */
    public ColumnRowKey next() {
      currentColumnIndex++;
      if (currentColumnIndex == highColumnIndex + 1) {
        currentColumnIndex = lowColumnIndex;
        currentRowIndex++;
      }
      return new ColumnRowKey(currentColumnIndex, currentRowIndex);
    }
  }

  /**
   * Iterates from high to low column indices, right to left, bottom to top.
   */
  private static class DataGridReverseIndexIterator implements Iterator<ColumnRowKey> {
    private int lowColumnIndex;
    private int lowRowIndex;
    private int highColumnIndex;
    private int highRowIndex;
    private int currentColumnIndex;
    private int currentRowIndex;

    private DataGridReverseIndexIterator(int highColumnIndex, int lowColumnIndex, int highRowIndex, int lowRowIndex) {
      this.highColumnIndex = highColumnIndex;
      this.lowColumnIndex = lowColumnIndex;
      this.highRowIndex = highRowIndex;
      this.lowRowIndex = lowRowIndex;
      this.currentColumnIndex = highColumnIndex + 1;
      this.currentRowIndex = highRowIndex;
    }

    private DataGridReverseIndexIterator(int highColumnIndex, int highRowIndex) {
      this.highColumnIndex = highColumnIndex;
      this.highRowIndex = highRowIndex;
      this.currentColumnIndex = highColumnIndex + 1;
      this.currentRowIndex = highRowIndex;
    }

    /**
     * Has next if we've not reached the last row index and last column index.
     *
     * @return true if we've not reached the last row index and last column index.
     */
    public boolean hasNext() {
      return (currentColumnIndex > lowColumnIndex || currentRowIndex > lowRowIndex);
    }

    /**
     * Get the index pair.
     *
     * @return next index pair.
     */
    public ColumnRowKey next() {
      currentColumnIndex--;
      if (currentColumnIndex == lowColumnIndex - 1) {
        currentColumnIndex = highColumnIndex;
        currentRowIndex--;
      }
      return new ColumnRowKey(currentColumnIndex, currentRowIndex);
    }
  }

  /**
   * Iterator helps us go through values.
   *
   * @param <V> type of value stored and iterated through.
   */
  private static class DataGridIterator<V> implements Iterator<V> {
    private Grid<V> grid;
    private Iterator<ColumnRowKey> internalIterator;


    private DataGridIterator(Grid<V> grid, Iterator<ColumnRowKey> internalIterator) {
      this.grid = grid;
      this.internalIterator = internalIterator;
    }

    /**
     * Has next if we've not reached the last row index and last column index.
     *
     * @return true if we've not reached the last row index and last column index.
     */
    public boolean hasNext() {
      return internalIterator.hasNext();
    }

    /**
     * Get the next value.
     *
     * @return next value.
     */
    public V next() {
      ColumnRowKey key = internalIterator.next();
      return grid.get(key.getColumnIndex(), key.getRowIndex());
    }
  }

  /**
   * Simple builder class to create grids. Mostly used for testing.
   *
   * @param <V>
   */
  public static class Builder<V> {
    private Grid<V> grid = new Grid<>(0, 0);

    public Builder<V> add(int columnIndex, int rowIndex, V value) {
      grid.set(columnIndex, rowIndex, value);
      return this;
    }

    public Grid<V> build() {
      return grid;
    }
  }
}