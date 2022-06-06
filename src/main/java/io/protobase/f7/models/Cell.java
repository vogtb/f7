package io.protobase.f7.models;

import com.google.common.base.MoreObjects;

/**
 * A cell is a stored value in a sheet or grid. It contains a raw value, and a computed value. The computed value will
 * only be set if the raw value is evaluated as a formula, or otherwise forced/coerced into bing a typed object. In the
 * event that we are creating a cell that has a value set by projection (which means that another cell before it is
 * returning a grid that pushes a value into this cell) the computed value will be set, but not the raw value.
 */
public class Cell extends BaseObject {
  /**
   * The raw value that is possibly F7 code.
   */
  private String rawValue;
  /**
   * The computed value from F7 code, or an interpreted value.
   */
  private Object computedValue = null;
  /**
   * Is this computed?
   */
  private boolean isComputed = false;

  /**
   * If a cell is blank, it can be populated with a value from a previous (above, or to the left) cell that returns a
   * grid of cells. This is called projection.
   */
  private ColumnRowKey projectionOriginKey = null;

  /**
   * Create a new cell with a raw and computed value.
   *
   * @param rawValue      - unchanged raw value.
   * @param computedValue - computed value from the raw value.
   */
  public Cell(String rawValue, Object computedValue) {
    this.rawValue = rawValue;
    this.computedValue = computedValue;
    this.isComputed = true;
  }

  /**
   * Create an un-computed cell.
   *
   * @param rawValue - unchanged raw value.
   */
  public Cell(String rawValue) {
    this.rawValue = rawValue;
  }

  /**
   * Create a cell that has a computed value, but no raw value because it is being created via a projection.
   *
   * @param computedValue       - value computed from the origin.
   * @param projectionOriginKey - the origin key.
   */
  public Cell(Object computedValue, ColumnRowKey projectionOriginKey) {
    this.rawValue = null;
    this.computedValue = computedValue;
    this.projectionOriginKey = projectionOriginKey;
    this.isComputed = true;
  }

  /**
   * Get the raw value.
   *
   * @return - string.
   */
  public String getRawValue() {
    return rawValue;
  }

  public void setRawValue(String rawValue) {
    this.rawValue = rawValue;
  }

  /**
   * Get the computed value.
   *
   * @return - any object, but probably String, Boolean, Error, Double, or List.
   */
  public Object getComputedValue() {
    return computedValue;
  }

  public void setComputedValue(Object computedValue) {
    this.computedValue = computedValue;
  }

  /**
   * Have we computed this yet or are we still holding the raw value?
   *
   * @return - true or false.
   */
  public boolean isComputed() {
    return isComputed;
  }

  public void setComputed(boolean computed) {
    isComputed = computed;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("rawValue", rawValue)
        .add("computedValue", computedValue)
        .add("isComputed", isComputed)
        .toString();
  }

  @Override
  public Object[] significantAttributes() {
    return new Object[]{
        rawValue,
        computedValue,
        isComputed
    };
  }
}
