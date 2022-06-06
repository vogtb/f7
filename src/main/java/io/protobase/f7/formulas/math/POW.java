package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.errors.NumException;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class POW extends AbstractFormula {
  public static FormulaName NAME = FormulaName.POW;
  public static POW SELF = new POW();

  public POW() {
    super();
  }

  public POW(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 2, NAME);
    Object first = Converters.first(collateralLookup.apply(origin, values[0]));
    Object second = Converters.first(collateralLookup.apply(origin, values[1]));
    if (first instanceof F7Exception) {
      return first;
    }
    if (second instanceof F7Exception) {
      return second;
    }
    double result = Math.pow(Converters.toDouble(first), Converters.toDouble(second));
    if (Double.isNaN(result) || Double.isInfinite(result)) {
      return new NumException("POW evaluates to an imaginary number.");
    }
    return result;
  }
}
