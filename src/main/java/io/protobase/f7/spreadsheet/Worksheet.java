package io.protobase.f7.spreadsheet;

import com.google.common.base.MoreObjects;
import io.protobase.f7.models.BaseObject;
import io.protobase.f7.models.Cell;
import io.protobase.f7.models.ColumnRowKey;
import io.protobase.f7.models.Grid;

import java.util.Objects;

public class Worksheet extends BaseObject {
  private String name;
  private Grid<Cell> cells;

  public Worksheet(String name, Grid<Cell> cells) {
    this.name = name;
    this.cells = cells;
  }

  public String getName() {
    return name;
  }

  public Grid<Cell> getCells() {
    return cells;
  }

  public Cell getCell(ColumnRowKey key) {
    return cells.get(key);
  }

  public void setCell(ColumnRowKey key, Cell cell) {
    cells.set(key, cell);
  }

  public void setCellComputedValue(ColumnRowKey key, Object computedValue) {
    Cell cell = getCell(key);
    if (Objects.nonNull(cell)) {
      cell.setComputedValue(computedValue);
      cells.set(key, cell);
    }
  }

  @Override
  public Object[] significantAttributes() {
    return new Object[]{
        name,
        cells
    };
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("name", name)
        .add("cells", cells)
        .toString();
  }
}
