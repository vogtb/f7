package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class QUOTIENT extends AbstractFormula {
  public static FormulaName NAME = FormulaName.QUOTIENT;
  public static QUOTIENT SELF = new QUOTIENT();

  public QUOTIENT() {
    super();
  }

  public QUOTIENT(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
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
    double dividend = Converters.toDouble(first);
    double divisor = Converters.toDouble(second);
    if (divisor == 0 || divisor == -0) {
      throw new DivException("Formula DIVIDE parameter 2 cannot be zero.");
    }
    return ROUNDDOWN.SELF.apply(null, dividend / divisor);
  }
}
