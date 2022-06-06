package io.protobase.f7.formulas;

import io.protobase.f7.errors.ValueException;
import io.protobase.f7.models.GridColumnRowKey;

/**
 * All formulas are similar to the functional interface, accepting any number of Objects and returning a single Object,
 * which could be a list containing many more objects.
 * <p>
 * In order to keep these formulas simple, clean, and concise, we have two methods, one calling the other, but catching
 * F7 errors/exceptions and returning them as objects. See {@link AbstractFormula} for more info.
 * <p>
 * All formulas accept a variable number of Object arguments, and return an Object. While in Java-Land this means we
 * could accept anything that extends an Object, in practice we only use the following types:
 * Boolean - Wraps primitive, can be converted to Double or String without error.
 * Double - Can be converted to Boolean, String without error.
 * String - Can be converted to Boolean, Double, but may throw {@link ValueException}.
 * List\Object - Depending on contents, may be able to be converted to other types.
 * <p>
 * WARNING: These are the only types that Formulas should work with, and convert to/from. Even if an Object that can be
 * easily cast to one of these is passed in, there's no guarantee that it will be cast, and will probably result in
 * error.
 */
public interface Formula {
  FormulaName NAME = null;

  /**
   * Primary method that will execute this formula with the provided args.
   *
   * @param origin - origin key of the cell that is running this formula. Can be null.
   * @param args   - variables to use in this formula's execution.
   * @return error or result.
   */
  Object apply(GridColumnRowKey origin, Object... args);

  /**
   * Where the main logic of the formula should reside. Will attempt to execute the formula logic, but may throw a
   * F7Exception.
   *
   * @param args - variables to use in this formula's execution.
   * @return error or result.
   */
  Object internal(GridColumnRowKey origin, Object... args);
}