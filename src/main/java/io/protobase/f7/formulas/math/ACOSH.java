package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.NumException;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ACOSH extends AbstractFormula {
  public static FormulaName NAME = FormulaName.ACOS;
  public static ACOSH SELF = new ACOSH();

  public ACOSH() {
    super();
  }

  public ACOSH(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    double value = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0])));
    if (value < 1.0) {
      return new NumException("ACOSH parameter 1 value is 0. It should be greater than or equal to 1.");
    }
    return Math.log(value + Math.sqrt(value * value - 1));
  }
}
