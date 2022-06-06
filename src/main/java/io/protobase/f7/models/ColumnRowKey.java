package io.protobase.f7.models;

import com.google.common.base.MoreObjects;

/**
 * Represents a single location in a 2D grid - usually {@link Grid}.
 */
public class ColumnRowKey extends BaseObject {
  private Integer column;
  private Integer row;

  /**
   * Create a new key from 0-indexed column and 0-indexed row.
   *
   * @param column int greater than or equal to 0.
   * @param row    int greater than or equal to 0.
   */
  public ColumnRowKey(Integer column, Integer row) {
    this.column = column;
    this.row = row;
  }

  /**
   * Column index.
   *
   * @return column index.
   */
  public Integer getColumnIndex() {
    return column;
  }

  /**
   * Row index.
   *
   * @return row index.
   */
  public Integer getRowIndex() {
    return row;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("column", column)
        .add("row", row)
        .toString();
  }

  @Override
  public Object[] significantAttributes() {
    return new Object[]{
        column,
        row
    };
  }

  public int compareTo(ColumnRowKey other) {
    if (this.equals(other)) {
      return 0;
    }
    if (this.column.equals(other.getColumnIndex())) {
      return this.row.compareTo(other.getRowIndex());
    }
    return this.column.compareTo(other.getColumnIndex());
  }
}
