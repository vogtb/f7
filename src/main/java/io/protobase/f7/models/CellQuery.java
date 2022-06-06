package io.protobase.f7.models;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Range;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.utils.AlphaUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * A range query represents a query of one or more cells over a single grid. It is composed of two dimensional ranges:
 * rows and columns, both of which can be unbounded at the lower, or upper ends of the range.
 */
public class CellQuery extends BaseObject {
  /**
   * Name of the grid being queried.
   */
  private String grid;
  /**
   * Range of columns.
   */
  private Range<Integer> columns;
  /**
   * Range of rows.
   */
  private Range<Integer> rows;

  public CellQuery(String grid, Range<Integer> columns, Range<Integer> rows) {
    this.grid = grid;
    this.columns = columns;
    this.rows = rows;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(CellQuery query) {
    return new Builder(query);
  }

  public Optional<String> getGrid() {
    return Optional.ofNullable(grid);
  }

  public Integer getStartColumn() {
    return columns.lowerEndpoint();
  }

  public Integer getEndColumn() {
    return columns.upperEndpoint();
  }

  public Integer getStartRow() {
    return rows.lowerEndpoint();
  }

  public Integer getEndRow() {
    return rows.upperEndpoint();
  }

  /**
   * Does this query intersect with the other one?
   *
   * @param other - other query.
   * @return true if at least one cell overlaps.
   */
  public boolean intersects(CellQuery other) {
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
  public CellQuery toBounded(int upperColumn, int upperRow) {
    if (!Range.closed(0, upperColumn).isConnected(columns) && !Range.closed(0, upperRow).isConnected(rows)) {
      return this;
    }
    return CellQuery.builder(this)
        .columnsBetween(this.columns.lowerEndpoint(),
            Math.min(upperColumn, columns.hasUpperBound() ? columns.upperEndpoint() : upperColumn))
        .rowsBetween(this.rows.lowerEndpoint(),
            Math.min(upperRow, rows.hasUpperBound() ? rows.upperEndpoint() : upperRow))
        .build();
  }

  @Override
  public Object[] significantAttributes() {
    return new Object[]{
        grid,
        columns,
        rows
    };
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("grid", grid)
        .add("columns", columns)
        .add("rows", rows)
        .toString();
  }

  public static class Builder {
    private String gridName;
    private Range<Integer> columns;
    private Range<Integer> rows;

    public Builder() {
    }

    public Builder(CellQuery query) {
      this.gridName = query.grid;
      this.columns = query.columns;
      this.rows = query.rows;
    }

    public Builder grid(String gridName) {
      this.gridName = gridName;
      return this;
    }

    public Builder columnsBetween(String low, String high) {
      columns = Range.closed(AlphaUtils.columnToInt(low), AlphaUtils.columnToInt(high));
      return this;
    }

    public Builder columnsBetween(int low, int high) {
      columns = Range.closed(low, high);
      return this;
    }

    public Builder rowsBetween(String low, String high) {
      rows = Range.closed(Integer.parseInt(low) - 1, Integer.parseInt(high) - 1);
      return this;
    }

    public Builder rowsBetween(int low, int high) {
      rows = Range.closed(low, high);
      return this;
    }

    public Builder openColumnsStartingAt(String startColumn) {
      columns = Range.atLeast(AlphaUtils.columnToInt(startColumn));
      return this;
    }

    public Builder openColumnsStartingAt(int startColumn) {
      columns = Range.atLeast(startColumn);
      return this;
    }

    public Builder openColumnsStartingAtZero() {
      columns = Range.atLeast(0);
      return this;
    }

    public Builder openRowsStartingAt(String startRow) {
      rows = Range.atLeast(Integer.parseInt(startRow) - 1);
      return this;
    }

    public Builder openRowsStartingAt(int startRow) {
      rows = Range.atLeast(startRow);
      return this;
    }

    public Builder openRowsStartingAtZero() {
      rows = Range.atLeast(0);
      return this;
    }

    public Builder expand(CellQuery query) {
      if (Objects.isNull(columns)) {
        columns = query.columns;
      } else {
        columns = columns.span(query.columns);
      }
      if (Objects.isNull(rows)) {
        rows = query.rows;
      } else {
        rows = rows.span(query.rows);
      }
      if (Objects.nonNull(gridName) && !gridName.equals(query.getGrid().get())) {
        throw new ValueException("Different grid names.");
      } else {
        gridName = query.getGrid().get();
      }
      return this;
    }

    public CellQuery build() {
      return new CellQuery(gridName, Objects.requireNonNull(columns), Objects.requireNonNull(rows));
    }
  }
}
