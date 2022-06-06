package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class MULTIPLY extends AbstractFormula {
  public static FormulaName NAME = FormulaName.MULTIPLY;
  public static MULTIPLY SELF = new MULTIPLY();

  public MULTIPLY() {
    super();
  }

  public MULTIPLY(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }


  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 2, NAME);
    Object first = Converters.first(collateralLookup.apply(origin, values[0]));
    Object second = Converters.first(collateralLookup.apply(origin, values[1]));
    if (first instanceof F7Exception) {
      return first;
    }
    if (second instanceof F7Exception) {
      return second;
    }
    return Converters.toDouble(first) * Converters.toDouble(second);
  }
}
