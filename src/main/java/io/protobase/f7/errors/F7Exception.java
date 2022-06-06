package io.protobase.f7.errors;

/**
 * Abstract F7 parsing or execution error.
 */
public abstract class F7Exception extends RuntimeException {
  private F7ExceptionName name;
  private String message = "";

  public F7Exception(F7ExceptionName name, String message) {
    this.name = name;
    this.message = message;
  }

  public F7Exception(F7ExceptionName name) {
    this.name = name;
  }

  /**
   * Sometimes we want to construct an exception from the error name, which is the same as the enumeration name.
   *
   * @param errorName of the exception.
   * @return new exception.
   */
  public static F7Exception fromString(String errorName) {
    switch (errorName) {
      case "#NULL!":
        return new NullException("");
      case "#DIV/0!":
        return new DivException("");
      case "#VALUE!":
        return new ValueException("");
      case "#REF!":
        return new RefException("");
      case "#NAME?":
        return new NameException("");
      case "#NUM!":
        return new NumException("");
      case "#N/A":
        return new NAException("");
      case "#ERROR!":
        return new ParseException("");
      default:
        throw new ParseException("");
    }
  }

  /**
   * Get the name of this exception.
   *
   * @return name.
   */
  public F7ExceptionName getName() {
    return name;
  }

  /**
   * Get the message for this exception.
   *
   * @return message.
   */
  @Override
  public String getMessage() {
    return message;
  }

  /**
   * Two F7Errors are considered equal if and only if their names are the same.
   *
   * @param other - to compare to.
   * @return true if names are equal.
   */
  @Override
  public boolean equals(Object other) {
    return other instanceof F7Exception && ((F7Exception) other).getName().toString().equals(this.getName().toString());
  }
}
