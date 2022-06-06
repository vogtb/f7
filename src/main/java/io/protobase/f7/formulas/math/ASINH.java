package io.protobase.f7.formulas.math;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ASINH extends AbstractFormula {
  public static FormulaName NAME = FormulaName.ASINH;
  public static ASINH SELF = new ASINH();

  public ASINH() {
    super();
  }

  public ASINH(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    double value = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0])));
    return Math.log(value + Math.sqrt(value * value + 1));
  }
}
