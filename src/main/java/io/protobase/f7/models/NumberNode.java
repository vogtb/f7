package io.protobase.f7.models;

import com.google.common.base.MoreObjects;

/**
 * Number node is a wrapper for a number as a Double.
 */
public class NumberNode extends BaseObject implements Node {
  private Double value;

  /**
   * New node from double.
   *
   * @param value to wrap.
   */
  public NumberNode(Double value) {
    this.value = value;
  }

  /**
   * Get the value.
   *
   * @return double.
   */
  public Double getValue() {
    return value;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("value", value)
        .toString();
  }

  @Override
  public Object[] significantAttributes() {
    return new Object[]{
        value
    };
  }
}
