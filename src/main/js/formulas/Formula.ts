import { SheetColumnRowKey } from "../models/common/SheetColumnRowKey";
import { Complex } from "../models/common/Types";
import { FormulaName } from "./FormulaName";

/**
 * All formulas are similar to the functional interface, accepting any number of CommonTypes and returning a single
 * CommonTypes, which could be a list containing many more objects.
 * <p>
 * In order to keep these formulas simple, clean, and concise, we have two methods, one calling the other, but catching
 * F7 errors/exceptions and returning them as objects. See {@link AbstractFormula} for more info.
 */
export interface Formula {
  NAME: FormulaName;

  run(origin: SheetColumnRowKey, ...args: Array<Complex>): unknown;

  internal(origin: SheetColumnRowKey, ...args: Array<Complex>): unknown;
}
