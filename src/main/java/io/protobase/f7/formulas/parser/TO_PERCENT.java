package io.protobase.f7.formulas.parser;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class TO_PERCENT extends AbstractFormula {
  public static FormulaName NAME = FormulaName.TO_PERCENT;
  public static TO_PERCENT SELF = new TO_PERCENT();

  public TO_PERCENT() {
    super();
  }

  public TO_PERCENT(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    return Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0])));
  }
}
