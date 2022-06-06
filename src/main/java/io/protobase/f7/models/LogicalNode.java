package io.protobase.f7.models;

import com.google.common.base.MoreObjects;

/**
 * Logic node is a wrapper for a boolean.
 */
public class LogicalNode extends BaseObject implements Node {
  public static final LogicalNode TRUE = new LogicalNode(true);
  public static final LogicalNode FALSE = new LogicalNode(false);
  private Boolean value;

  /**
   * New node from boolean.
   *
   * @param value to wrap.
   */
  private LogicalNode(Boolean value) {
    this.value = value;
  }

  /**
   * Get the value.
   *
   * @return boolean.
   */
  public Boolean getValue() {
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
