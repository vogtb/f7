package io.protobase.f7.formulas;

import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.models.GridColumnRowKey;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Abstract formula that has some defaults to ensure the safe execution of formulas.
 */
public abstract class AbstractFormula implements Formula {
  private static final Function<Object, Object> DEFAULT_LOOKUP = (value) -> value;
  private static final BiFunction<GridColumnRowKey, Object, Object> DEFAULT_COLLATERAL_LOOKUP = (origin, value) -> value;

  protected Function<Object, Object> lookup;
  protected BiFunction<GridColumnRowKey, Object, Object> collateralLookup;

  public AbstractFormula() {
    lookup = DEFAULT_LOOKUP;
    collateralLookup = DEFAULT_COLLATERAL_LOOKUP;
  }

  public AbstractFormula(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    this.lookup = lookup;
    this.collateralLookup = collateralLookup;
  }

  /**
   * Ensure that argument lengths are equal. If not, throw {@link NAException}.
   *
   * @param actualLength   - actual length of arguments.
   * @param expectedLength - expected length of arguments.
   * @param caller         - name of formula calling this.
   */
  public static void checkLength(int actualLength, int expectedLength, FormulaName caller) {
    if (actualLength != expectedLength) {
      throw new NAException(String.format("Wrong number of arguments to %s. Expected %d arguments, but got %d arguments",
          caller, expectedLength, actualLength));
    }
  }

  /**
   * Ensure that arguments are at least a certain length. If not, throw {@link NAException}.
   *
   * @param actualLength      - actual length of arguments.
   * @param minExpectedLength - minimum expected length of arguments.
   * @param caller            - name of formula calling this.
   */
  public static void checkAtLeastLength(int actualLength, int minExpectedLength, FormulaName caller) {
    if (actualLength < minExpectedLength) {
      throw new NAException(String.format("Wrong number of arguments to %s. Expected at least %d arguments, but got %d arguments",
          caller, minExpectedLength, actualLength));
    }
  }

  /**
   * Ensure that arguments are between a minimum and maximum length. If not, throw {@link NAException}.
   *
   * @param actualLength - actual length of arguments.
   * @param min          - minimum expected length of arguments, inclusively
   * @param max          - maximum expected length of arguments, inclusively
   * @param caller       - name of formula being called.
   */
  public static void checkLengthBetween(int actualLength, int min, int max, FormulaName caller) {
    if (actualLength < min || actualLength > max) {
      throw new NAException(String.format("Wrong number of arguments to %s. Expected between %d and %d arguments, but got %d arguments",
          caller, min, max, actualLength));
    }
  }

  /**
   * By default, when we execute a formula, we want to catch the errors and return them as the resulting value.
   *
   * @param origin - origin cell that is running this formula.
   * @param values - to use when calling the formula.
   * @return the result of the formula implemented by the sub-class, or the error thrown by the formula implemented by
   * the sub-class.
   */
  public Object apply(GridColumnRowKey origin, Object... values) {
    try {
      return this.internal(origin, values);
    } catch (F7Exception error) {
      return error;
    }
  }
}