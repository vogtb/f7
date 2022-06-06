import { ComparisonResult, comparisonResultFromNumber } from "../models/common/ComparisonResults";
import { Converters } from "./Converters";
import { isNull } from "../common/utils/Types";

export class Compare {
  /**
   * Compare two objects using their type precedence first, and then defaulting to their default class compare methods
   * if they are of the same type. If one is null, use a default depending on type.
   *
   * @param first  - object to compare. Should be Double, Boolean, String, or null.
   * @param second - second object to compare.  Should be Double, Boolean, String, or null.
   * @return ComparisonResult indicating less-than, greater-than, equal-to.
   */
  static compare(first: any, second: any): ComparisonResult {
    const firstPrecedence = Compare.getTypePrecedence(first);
    const secondPrecedence = Compare.getTypePrecedence(second);
    if (firstPrecedence === secondPrecedence) {
      if (isNull(first)) {
        return ComparisonResult.EQUAL;
      }
      if (typeof first === "number") {
        const firstNumber = Converters.toNumber(first);
        const secondNumber = Converters.toNumber(second);
        return comparisonResultFromNumber(Compare.numberComparison(firstNumber, secondNumber));
      }
      if (typeof first === "boolean") {
        const firstBoolean = Converters.toBoolean(first);
        const secondBoolean = Converters.toBoolean(second);
        return comparisonResultFromNumber(Compare.booleanComparison(firstBoolean, secondBoolean));
      }
      if (typeof first === "string") {
        const firstString = Converters.toText(first).toLowerCase();
        const secondString = Converters.toText(second).toLowerCase();
        return comparisonResultFromNumber(Compare.stringComparison(firstString, secondString));
      }
    }
    // If the first is null, the second must be a number. First falls back to default.
    if (isNull(first)) {
      if (typeof second === "number") {
        const firstNumber = 0;
        const secondNumber = Converters.toNumber(second);
        return comparisonResultFromNumber(Compare.numberComparison(firstNumber, secondNumber));
      }
      if (typeof second === "boolean") {
        const firstBoolean = false;
        const secondBoolean = Converters.toBoolean(second);
        return comparisonResultFromNumber(Compare.booleanComparison(firstBoolean, secondBoolean));
      }
      if (typeof second === "string") {
        const firstString = "";
        const secondString = Converters.toText(second).toLowerCase();
        return comparisonResultFromNumber(Compare.stringComparison(firstString, secondString));
      }
    }
    // If the second is null, the first must be a number. Second falls back to default.
    if (isNull(second)) {
      if (typeof first === "number") {
        const firstNumber = Converters.toNumber(first);
        const secondNumber = 0;
        return comparisonResultFromNumber(Compare.numberComparison(firstNumber, secondNumber));
      }
      if (typeof first === "boolean") {
        const firstBoolean = Converters.toBoolean(first);
        const secondBoolean = false;
        return comparisonResultFromNumber(Compare.booleanComparison(firstBoolean, secondBoolean));
      }
      if (typeof first === "string") {
        const firstString = Converters.toText(first).toLowerCase();
        const secondString = "";
        return comparisonResultFromNumber(Compare.stringComparison(firstString, secondString));
      }
    }
    return comparisonResultFromNumber(Compare.numberComparison(firstPrecedence, secondPrecedence));
  }

  /**
   * Strings, Numbers, and Booleans have type precedence that is used when comparing across types.
   *
   * @param value to get precedence of.
   * @return {@code 1} if Number,{@code 2} if String, {@code 3} if Boolean.
   */
  static getTypePrecedence(value: any): number {
    if (isNull(value)) {
      return 0;
    }
    if (typeof value === "number") {
      return 1;
    }
    if (typeof value === "string") {
      return 2;
    }
    return 3;
  }

  /**
   * Compare numbers by subtracting one from the other.
   * @param first number
   * @param second number
   */
  static numberComparison(first: number, second: number): number {
    return first - second;
  }

  /**
   * Compare boolean values, returning 0, 1, -1
   * @param first boolean.
   * @param second boolean.
   */
  static booleanComparison(first: boolean, second: boolean): number {
    return (first ? 1 : 0) - (second ? 1 : 0);
  }

  /**
   * Compare strings, returning 0, 1, -1;
   * @param first
   * @param second
   */
  static stringComparison(first: string, second: string): number {
    if (first === second) {
      return 0;
    }
    if (first > second) {
      return 1;
    }
    return -1;
  }
}
