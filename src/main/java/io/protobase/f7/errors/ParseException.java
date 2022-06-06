package io.protobase.f7.errors;

/**
 * Error represents something went wrong when parsing, and (in very rare cases) executing.
 */
public class ParseException extends F7Exception {
  public ParseException(String message) {
    super(F7ExceptionName.PARSE, message);
  }

  public ParseException() {
    super(F7ExceptionName.PARSE);
  }
}
