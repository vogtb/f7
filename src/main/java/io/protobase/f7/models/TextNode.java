package io.protobase.f7.models;

import com.google.common.base.MoreObjects;

/**
 * Text node is just a wrapper for String.
 */
public class TextNode extends BaseObject implements Node {
  private String value;

  /**
   * Create new text node from string.
   *
   * @param value string.
   */
  public TextNode(String value) {
    this.value = value;
  }

  /**
   * Get the un-altered, un-wrapped value.
   *
   * @return string.
   */
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("value", "\"" + value + "\"")
        .toString();
  }

  @Override
  public Object[] significantAttributes() {
    return new Object[]{
        value
    };
  }
}
