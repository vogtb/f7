package io.protobase.f7.formulas.logic;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class IF extends AbstractFormula {
  public static FormulaName NAME = FormulaName.IF;
  public static IF SELF = new IF();

  public IF() {
    super();
  }

  public IF(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 3, NAME);
    Boolean logicalExpression = Converters.toBoolean(Converters.first(collateralLookup.apply(origin, values[0])));
    if (logicalExpression) {
      return collateralLookup.apply(origin, values[1]);
    }
    return collateralLookup.apply(origin, values[2]);
  }
}
