package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class UPLUS extends AbstractFormula {
  public static FormulaName NAME = FormulaName.UPLUS;
  public static UPLUS SELF = new UPLUS();

  public UPLUS() {
    super();
  }

  public UPLUS(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    Object value = Converters.first(collateralLookup.apply(origin, values[0]));
    if (value instanceof F7Exception) {
      return value;
    }
    Double number = Converters.toDouble(value);
    if (number.equals(0.0) || number.equals(-0.0)) {
      return 0.0;
    }
    return number * 1;
  }
}
