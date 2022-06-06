package io.protobase.f7.formulas.text;

import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class LEN extends AbstractFormula {
  public static FormulaName NAME = FormulaName.LEN;
  public static LEN SELF = new LEN();

  public LEN() {
    super();
  }

  public LEN(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    Object value = Converters.first(collateralLookup.apply(origin, values[0]));
    if (value instanceof F7Exception) {
      return value;
    }
    return (double) Converters.toText(value).length();
  }
}
