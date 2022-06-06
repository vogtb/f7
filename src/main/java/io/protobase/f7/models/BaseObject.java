package io.protobase.f7.models;

import java.util.Arrays;

/**
 * Base object for all other classes. Gives us the basic equality, comparision, toString, and hash-code capabilities.
 */
public abstract class BaseObject {
  public Object[] significantAttributes() {
    return new Object[]{};
  }

  @Override
  public String toString() {
    return super.toString();
  }

  /**
   * Objects are considered equal if and only if they are of the same class, and their significant attributes are the
   * same.
   *
   * @param other object
   * @return true if equal.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof BaseObject) {
      BaseObject basic = ((BaseObject) other);
      return Arrays.equals(basic.significantAttributes(), significantAttributes());
    }
    return super.equals(other);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(significantAttributes());
  }
}
