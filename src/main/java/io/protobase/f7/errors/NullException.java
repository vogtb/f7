package io.protobase.f7.errors;

/**
 * Something is null.
 */
public class NullException extends F7Exception {
  public NullException(String message) {
    super(F7ExceptionName.NULL, message);
  }

  public NullException() {
    super(F7ExceptionName.NULL);
  }
}
