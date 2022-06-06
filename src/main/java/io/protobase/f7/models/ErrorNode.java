package io.protobase.f7.models;

import com.google.common.base.MoreObjects;
import io.protobase.f7.errors.F7Exception;

/**
 * Error node is a literal representation of an F7 error. For now, this wraps an {@link F7Exception}.
 */
public class ErrorNode extends BaseObject implements Node {
  private F7Exception value;

  /**
   * Create a new error node from an exception.
   *
   * @param value exception.
   */
  public ErrorNode(F7Exception value) {
    this.value = value;
  }

  /**
   * Get the exception value.
   *
   * @return exception.
   */
  public F7Exception getValue() {
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
