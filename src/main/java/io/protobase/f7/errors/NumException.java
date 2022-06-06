package io.protobase.f7.errors;

/**
 * Represents an error where a number is not valid, not a in a specific range, or in a subset of number values.
 */
public class NumException extends F7Exception {
  public NumException(String message) {
    super(F7ExceptionName.NUM, message);
  }

  public NumException() {
    super(F7ExceptionName.NUM);
  }
}
