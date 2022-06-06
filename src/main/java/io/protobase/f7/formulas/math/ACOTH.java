package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.NumException;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ACOTH extends AbstractFormula {
  public static FormulaName NAME = FormulaName.ACOTH;
  public static ACOTH SELF = new ACOTH();

  public ACOTH() {
    super();
  }

  public ACOTH(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    double value = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0])));
    if (value <= 1 && value >= -1) {
      throw new NumException(String.format("ACOTH parameter 1 value is %f. Valid values cannot be between -1 and 1 inclusive.", values));
    }
    return 0.5 * Math.log((value + 1) / (value - 1));
  }
}
