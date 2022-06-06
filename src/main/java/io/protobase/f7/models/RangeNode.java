package io.protobase.f7.models;

import com.google.common.base.MoreObjects;

/**
 * A range node represents a range of cells with a starting cell and ending cell, possibly by infering that the start or
 * end cells are the max-cell or min-cell in the column or row.
 */
public class RangeNode extends BaseObject implements Node {
  private CellQuery cellQuery;

  public RangeNode(CellQuery cellQuery) {
    this.cellQuery = cellQuery;
  }

  public CellQuery getCellQuery() {
    return cellQuery;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("cellQuery", cellQuery)
        .toString();
  }

  @Override
  public Object[] significantAttributes() {
    return new Object[]{
        cellQuery
    };
  }
}
