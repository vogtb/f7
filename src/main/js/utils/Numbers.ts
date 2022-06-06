/**
 * Static class for number utilities.
 */
import { isNotNull } from "../common/utils/Types";

export class Numbers {
  /**
   * Roughly taken from java.lang.Double.valueOf().
   * This is how we stop javascript from parsing stuff like "10    10" as a 10, and so on.
   */
  static NUMBER_REG_EX =
    /^[\x00-\x20]*[+-]?(([0-9]+)(\.)?(([0-9]+)?)([eE][+-]?([0-9]+))?)|(\.([0-9]+)([eE][+-]?([0-9]+))?)|[\x00-\x20]*$/;

  /**
   * Is positive or negative zero.
   * @param value
   */
  static isZero(value: number): boolean {
    return value === 0;
  }

  /**
   * Convert the value to a number if it matches our regular expression. If not, return null.
   * @param value - to possible convert.
   */
  static toNumberOrNull(value: string): number | null {
    const matches = value.match(Numbers.NUMBER_REG_EX);
    if (isNotNull(matches) && matches.length > 0 && matches[0] !== "") {
      return parseFloat(value);
    }
    return null;
  }
}
