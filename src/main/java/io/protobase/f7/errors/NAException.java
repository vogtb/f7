package io.protobase.f7.errors;

/**
 * NA Error represents an error where a formula or function called with the wrong number of arguments.
 */
public class NAException extends F7Exception {
  public NAException(String message) {
    super(F7ExceptionName.NA, message);
  }

  public NAException() {
    super(F7ExceptionName.NA);
  }
}
