/**
 * Helper classes for use in [].reduce(...).
 */
import { Complex } from "../models/common/Types";

export class Reducers {
  /**
   * Find max of numbers when reducing.
   * @param first - number.
   * @param second - number.
   */
  static max(first: number, second: number) {
    return Math.max(first, second);
  }

  /**
   * Find min of numbers when reducing.
   * @param first - number.
   * @param second - number.
   */
  static min(first: number, second: number) {
    return Math.min(first, second);
  }

  /**
   * Adds two numbers values together as per the + operator.
   *
   * @param first - first value.
   * @param second - second value.
   */
  static sum(first: number, second: number) {
    return first + second;
  }

  /**
   * Multiplies two number values together as per the * operator
   * @param first - first value
   * @param second - second value
   */
  static product(first: number, second: number) {
    return first * second;
  }

  /**
   * Performs logical && operation on two booleans.
   * @param first - first value.
   * @param second - second value.
   */
  static logicalAnd(first: boolean, second: boolean) {
    return first && second;
  }

  /**
   * Performs logical || operation on two booleans.
   * @param first - first value.
   * @param second - second value.
   */
  static logicalOr(first: boolean, second: boolean) {
    return first || second;
  }

  /**
   * Performs logical exclusive or operation on two booleans.
   * @param first - first value.
   * @param second - second value.
   */
  static logicalXOr(first: boolean, second: boolean) {
    return (first && !second) || (!first && second);
  }

  /**
   * Joins the first and second arrays.
   * @param first - first array.
   * @param second - second array.
   */
  static join(first: Array<Complex>, second: Array<Complex>) {
    return [].concat(...first, ...second);
  }
}
