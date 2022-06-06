package io.protobase.f7.models;

import com.google.common.base.MoreObjects;

/**
 * Variable node is a named variable, like TRUE, FALSE, or MY_SUPER_NAMED_RANGE. It is resolved to an actual object at
 * run-time.
 */
public class VariableNode extends BaseObject implements Node {
  private String name;

  /**
   * Construct node from name.
   *
   * @param name variable name.
   */
  public VariableNode(String name) {
    this.name = name;
  }

  /**
   * Get the variable name.
   *
   * @return - name
   */
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("name", name)
        .toString();
  }

  @Override
  public Object[] significantAttributes() {
    return new Object[]{
        name
    };
  }
}
