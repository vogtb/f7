package io.protobase.f7.formulas.math;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ADD extends AbstractFormula {
  public static FormulaName NAME = FormulaName.ADD;
  public static ADD SELF = new ADD();

  public ADD() {
    super();
  }

  public ADD(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 2, NAME);
    Object first = collateralLookup.apply(origin, Converters.first(values[0]));
    Object second = collateralLookup.apply(origin, Converters.first(values[1]));
    return Converters.toDouble(first) + Converters.toDouble(second);
  }
}
