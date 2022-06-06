package io.protobase.f7.models;

import com.google.common.base.MoreObjects;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ListNode extends BaseObject implements Node {
  private Grid<Node> grid;

  public ListNode() {
    grid = new Grid<>(0, 0);
  }

  public ListNode(Grid<Node> grid) {
    this.grid = grid;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Grid<Node> getGrid() {
    return grid;
  }

  public boolean isEmpty() {
    return grid.isEmpty();
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("grid", grid)
        .toString();
  }

  @Override
  public Object[] significantAttributes() {
    return new Object[]{
        grid
    };
  }

  public static class Builder {
    private Map<ColumnRowKey, Node> values = new HashMap<>();

    public Builder value(int columnIndex, int rowIndex, Node value) {
      values.put(new ColumnRowKey(columnIndex, rowIndex), value);
      return this;
    }

    public ListNode build() {
      int columnSize = 1 + values.keySet().stream().map(ColumnRowKey::getColumnIndex).reduce(Integer::max).get();
      int rowSize = 1 + values.keySet().stream().map(ColumnRowKey::getRowIndex).reduce(Integer::max).get();
      Grid<Node> grid = new Grid<>(columnSize, rowSize);
      for (Map.Entry<ColumnRowKey, Node> entry : values.entrySet()) {
        Node value = entry.getValue();
        if (Objects.nonNull(value)) {
          grid.set(entry.getKey().getColumnIndex(), entry.getKey().getRowIndex(), entry.getValue());
        } else {
          grid.setEmpty(entry.getKey().getColumnIndex(), entry.getKey().getRowIndex());
        }
      }
      return new ListNode(grid);
    }
  }
}
