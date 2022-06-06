package io.protobase.f7.utils;

public class Filters {
  /**
   * Used to filter lists to values that are numeric: they are a Boolean, or a Double.
   *
   * @param value - to check
   * @return - true if value is instance of Double, or Boolean. Otherwise false.
   */
  public static boolean isNumeric(Object value) {
    return value instanceof Double || value instanceof Boolean;
  }

  /**
   * Is the value a numeric value (Double or Boolean) or a coercable string?
   *
   * @param value - to check
   * @return - true if value is instance of Double, or Boolean, or is a String that can be converted to Double.
   */
  public static boolean isCoercableToNumeric(Object value) {
    return isNumeric(value) || isCoercableString(value);
  }

  public static boolean isLiteralNumber(Object value) {
    return value instanceof Double;
  }

  /**
   * Can we coerce this string to a number value?
   *
   * @param value - to test.
   * @return true if we can get a Double from this value.
   */
  public static boolean isCoercableString(Object value) {
    if (value instanceof String) {
      try {
        Double.parseDouble(Converters.castAsString(value));
        return true;
      } catch (NumberFormatException e) {
        return false;
      }
    }
    return false;
  }
}
