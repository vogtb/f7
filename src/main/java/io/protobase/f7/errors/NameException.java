package io.protobase.f7.errors;

/**
 * A variable or function name does not exist.
 */
public class NameException extends F7Exception {
  public NameException(String message) {
    super(F7ExceptionName.NAME, message);
  }

  public NameException() {
    super(F7ExceptionName.NAME);
  }
}
