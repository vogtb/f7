package io.protobase.f7.formulas.math;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ACOT extends AbstractFormula {
  public static FormulaName NAME = FormulaName.ACOT;
  public static ACOT SELF = new ACOT();

  public ACOT() {
    super();
  }

  public ACOT(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    double divisor = Converters.toDouble(collateralLookup.apply(origin, Converters.first(values[0])));
    if (divisor == 0 || divisor == -0) {
      return 1.570796327;
    }
    return Math.atan(1 / divisor);
  }
}
