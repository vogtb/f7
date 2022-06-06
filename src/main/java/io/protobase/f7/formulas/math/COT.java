package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;
import io.protobase.f7.utils.NumberUtils;

import java.util.function.BiFunction;
import java.util.function.Function;

public class COT extends AbstractFormula {
  public static FormulaName NAME = FormulaName.COT;
  public static COT SELF = new COT();

  public COT() {
    super();
  }

  public COT(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    double divisor = Math.tan(Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0]))));
    if (NumberUtils.isZero(divisor)) {
      return new DivException("Evaluation of COT caused a divide by zero error.");
    }
    return 1 / divisor;
  }
}
