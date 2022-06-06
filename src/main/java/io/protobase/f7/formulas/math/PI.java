package io.protobase.f7.formulas.math;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;

import java.util.function.BiFunction;
import java.util.function.Function;

public class PI extends AbstractFormula {
  public static FormulaName NAME = FormulaName.PI;
  public static PI SELF = new PI();

  public PI() {
    super();
  }

  public PI(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 0, NAME);
    return Math.PI;
  }
}
