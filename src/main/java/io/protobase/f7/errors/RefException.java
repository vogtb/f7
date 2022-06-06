package io.protobase.f7.errors;

/**
 * Ref error represents a reference or range that is empty, out of bounds, or does not exist.
 */
public class RefException extends F7Exception {
  public RefException(String message) {
    super(F7ExceptionName.REF, message);
  }

  public RefException() {
    super(F7ExceptionName.REF);
  }
}
