package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.NumException;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class LN extends AbstractFormula {
  public static FormulaName NAME = FormulaName.LN;
  public static LN SELF = new LN();

  public LN() {
    super();
  }

  public LN(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    Double value = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0])));
    if (value <= 0) {
      return new NumException(String.format("LN parameter 1 value is %f. It should be greater than 0.", values));
    }
    return Math.log(value);
  }
}
