import { NAException } from "../errors/NAException";
import { SheetColumnRowKey } from "../models/common/SheetColumnRowKey";
import { CollateralLookupFunction, Complex, LookupFunction } from "../models/common/Types";
import { Formula } from "./Formula";
import { FormulaName } from "./FormulaName";

/**
 * Abstract formula that has some defaults to ensure the safe execution of formulas.
 */
export class AbstractFormula implements Formula {
  NAME: FormulaName = null;
  lookup: LookupFunction;
  collateralLookup: CollateralLookupFunction;

  constructor(lookup?: LookupFunction, collateralLookup?: CollateralLookupFunction) {
    this.lookup = lookup ? lookup : AbstractFormula.DEFAULT_LOOKUP;
    this.collateralLookup = collateralLookup
      ? collateralLookup
      : AbstractFormula.DEFAULT_COLLATERAL_LOOKUP;
  }

  protected static checkLength(actualLength: number, expectedLength: number, caller: FormulaName) {
    if (actualLength != expectedLength) {
      throw new NAException(
        `Wrong number of arguments to ${caller}. Expected ${expectedLength} arguments, but got ${actualLength} arguments`
      );
    }
  }

  protected static checkAtLeastLength(
    actualLength: number,
    minExpectedLength: number,
    caller: FormulaName
  ) {
    if (actualLength < minExpectedLength) {
      throw new NAException(
        `Wrong number of arguments to ${caller}. Expected at least ${minExpectedLength} arguments, but got ${actualLength} arguments`
      );
    }
  }

  /**
   * Ensure that number of arguments is between a minmum and maximum, inclusively.
   * @param actualLength - actual length of arguments.
   * @param min - minimum expected length of arguments, inclusively
   * @param max - maximum expected length of arguments, inclusively
   * @param caller - NAME of formula being called.
   */
  protected static checkLengthBetween(
    actualLength: number,
    min: number,
    max: number,
    caller: FormulaName
  ) {
    if (actualLength < min || actualLength > max) {
      throw new NAException(
        `Wrong number of arguments to ${caller}. Expected between ${min} and ${max} arguments, but got ${actualLength} arguments`
      );
    }
  }

  /**
   * Default lookup just passes value through.
   * @param value - value to return.
   * @constructor
   */
  private static DEFAULT_LOOKUP: LookupFunction = (value) => value;

  /**
   * Default collateral lookup just passes value through as well.
   * @param origin - doesn't matter.
   * @param value - value to return.
   * @constructor
   */
  private static DEFAULT_COLLATERAL_LOOKUP: CollateralLookupFunction = (origin, value) => value;

  /**
   * This should be implemented by the actual formula.
   * @param origin - of caller.
   * @param values - arguments for formula.
   */
  internal(origin: SheetColumnRowKey, ...values: Array<Complex>): unknown {
    return null;
  }

  /**
   * Call internal, catching and returning error as value.
   * @param origin - of caller.
   * @param values - arguments for formula.
   */
  run(origin: SheetColumnRowKey, ...values: Array<Complex>): unknown {
    try {
      return this.internal(origin, ...values);
    } catch (error) {
      return error;
    }
  }
}
