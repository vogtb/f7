package io.protobase.f7.formulas.math;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ODD extends AbstractFormula {
  public static FormulaName NAME = FormulaName.ODD;
  public static ODD SELF = new ODD();

  public ODD() {
    super();
  }

  public ODD(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    double value = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0])));
    double rounded;
    if (value < 0) {
      rounded = Math.floor(value);
      if (rounded % 2 == 0) {
        return rounded - 1.0;
      }
    } else {
      rounded = Math.ceil(value);
      if (rounded % 2 == 0) {
        return rounded + 1.0;
      }
    }
    return rounded;
  }
}
