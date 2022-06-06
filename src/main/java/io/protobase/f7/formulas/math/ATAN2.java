package io.protobase.f7.formulas.math;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ATAN2 extends AbstractFormula {
  public static FormulaName NAME = FormulaName.ATAN2;
  public static ATAN2 SELF = new ATAN2();

  public ATAN2() {
    super();
  }

  public ATAN2(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 2, NAME);
    Double first = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0])));
    Double second = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[1])));
    return Math.atan2(first, second);
  }
}
