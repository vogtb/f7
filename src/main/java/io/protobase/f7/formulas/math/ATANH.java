package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.NumException;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ATANH extends AbstractFormula {
  public static FormulaName NAME = FormulaName.ATANH;
  public static ATANH SELF = new ATANH();

  public ATANH() {
    super();
  }

  public ATANH(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    double value = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0])));
    if (value <= -1 || value >= 1) {
      return new NumException(String.format("ATANH parameter 1 value is %f. Valid values are between -1 and 1 exclusively.", values));
    }
    return (Math.log(1 + value) - Math.log(1 - value)) / 2;
  }
}
