package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.NumException;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class SQRTPI extends AbstractFormula {
  public static FormulaName NAME = FormulaName.SQRTPI;
  public static SQRTPI SELF = new SQRTPI();

  public SQRTPI() {
    super();
  }

  public SQRTPI(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    double value = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0])));
    if (value < 0) {
      return new NumException("Function SQRTPI parameter 1 value is negative. It should be greater than or equal to zero.");
    }
    return Math.sqrt(value * Math.PI);
  }
}
