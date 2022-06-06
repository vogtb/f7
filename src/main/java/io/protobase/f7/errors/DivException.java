package io.protobase.f7.errors;

/**
 * Represents attempted division where the divisor is 0, regardless of dividend.
 */
public class DivException extends F7Exception {
  public DivException(String message) {
    super(F7ExceptionName.DIV, message);
  }

  public DivException() {
    super(F7ExceptionName.DIV);
  }
}
