package io.protobase.f7.formulas.math;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class SINH extends AbstractFormula {
  public static FormulaName NAME = FormulaName.SINH;
  public static SINH SELF = new SINH();

  public SINH() {
    super();
  }

  public SINH(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    return Math.sinh(Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0]))));
  }
}
