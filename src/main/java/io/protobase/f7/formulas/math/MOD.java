package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class MOD extends AbstractFormula {
  public static FormulaName NAME = FormulaName.MOD;
  public static MOD SELF = new MOD();

  public MOD() {
    super();
  }

  public MOD(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 2, NAME);
    Double first = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0])));
    Double second = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[1])));
    if (second == -0 || second == 0) {
      return new DivException("MOD parameter 2 cannot be zero.");
    }
    return first % second;
  }
}
