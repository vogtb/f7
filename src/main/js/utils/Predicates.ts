import { F7Exception } from "../errors/F7Exception";
import { Complex } from "../models/common/Types";
import { Numbers } from "./Numbers";

export class Predicates {
  /**
   * Used to filter lists to values that are numeric: they are a Boolean, or a Double.
   *
   * @param value - to check
   * @return - true if value is instance of Double, or Boolean. Otherwise false.
   */
  static isNumeric(value: any): boolean {
    return typeof value === "number" || typeof value === "boolean";
  }

  /**
   * Is the value a numeric value (Double or Boolean) or a coercable string?
   *
   * @param value - to check
   * @return - true if value is instance of Double, or Boolean, or is a String that can be converted to Double.
   */
  static isCoercableToNumeric(value: any): boolean {
    return Predicates.isNumeric(value) || Predicates.isCoercableString(value);
  }

  /**
   * Is it straight-up a number?
   * @param value - to check.
   */
  static isLiteralNumber(value: any): boolean {
    return typeof value === "number";
  }

  /**
   * Can we coerce this string to a number value?
   *
   * @param value - to test.
   * @return true if we can get a Double from this value.
   */
  static isCoercableString(value: any): boolean {
    if (typeof value === "string") {
      if (Numbers.toNumberOrNull(value) !== null) {
        return true;
      }
    }
    return false;
  }

  /**
   * Is the value NOT an error.
   * @param value - value to check
   */
  static isNonError(value: Complex) {
    return !(value instanceof F7Exception);
  }
}
