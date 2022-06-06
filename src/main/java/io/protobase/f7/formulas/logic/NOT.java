package io.protobase.f7.formulas.logic;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class NOT extends AbstractFormula {
  public static FormulaName NAME = FormulaName.NOT;
  public static NOT SELF = new NOT();

  public NOT() {
    super();
  }

  public NOT(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    Object first = Converters.first(collateralLookup.apply(origin, values[0]));
    return !Converters.toBoolean(first);
  }
}
