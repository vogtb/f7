package io.protobase.f7.models;

import com.google.common.base.MoreObjects;

/**
 * Represents a single location in a 3D grid: {column=x, row=y, grid=z}.
 */
public class GridColumnRowKey extends BaseObject {
  private String grid;
  private ColumnRowKey subKey;

  /**
   * Create a new key from column row key and grid.
   *
   * @param grid         - name of the grid
   * @param columnRowKey - column row key.
   */
  public GridColumnRowKey(String grid, ColumnRowKey columnRowKey) {
    this.grid = grid;
    subKey = columnRowKey;
  }

  /**
   * Grid index.
   *
   * @return grid index.
   */
  public String getGridIndex() {
    return grid;
  }

  /**
   * Column index.
   *
   * @return column index.
   */
  public Integer getColumnIndex() {
    return subKey.getColumnIndex();
  }

  /**
   * Row index.
   *
   * @return row index.
   */
  public Integer getRowIndex() {
    return subKey.getRowIndex();
  }

  /**
   * Get the column row key.
   *
   * @return column row key.
   */
  public ColumnRowKey toColumnRowKey() {
    return new ColumnRowKey(getColumnIndex(), getRowIndex());
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("grid", grid)
        .add("column", subKey.getColumnIndex())
        .add("row", subKey.getRowIndex())
        .toString();
  }

  @Override
  public Object[] significantAttributes() {
    return new Object[]{
        grid,
        subKey.getColumnIndex(),
        subKey.getRowIndex()
    };
  }
}
