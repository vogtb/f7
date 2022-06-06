package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.NumException;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class LOG10 extends AbstractFormula {
  public static FormulaName NAME = FormulaName.LOG10;
  public static LOG10 SELF = new LOG10();

  public LOG10() {
    super();
  }

  public LOG10(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    Double value = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0])));
    if (value <= 0) {
      return new NumException(String.format("LOG10 parameter 1 value is %f. It should be greater than 0.", value));
    }
    return Math.log10(value);
  }
}
