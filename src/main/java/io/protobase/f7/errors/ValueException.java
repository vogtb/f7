package io.protobase.f7.errors;

/**
 * Value error represents a failed attempt to coerce a value of one type to another. Usually string to boolean or
 * string to number.
 */
public class ValueException extends F7Exception {
  public ValueException(String message) {
    super(F7ExceptionName.VALUE, message);
  }

  public ValueException() {
    super(F7ExceptionName.VALUE);
  }
}
