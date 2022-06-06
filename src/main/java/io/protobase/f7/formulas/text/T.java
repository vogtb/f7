package io.protobase.f7.formulas.text;

import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class T extends AbstractFormula {
  public static FormulaName NAME = FormulaName.CONCAT;
  public static T SELF = new T();

  public T() {
    super();
  }

  public T(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    Object first = Converters.first(collateralLookup.apply(origin, values[0]));
    if (first instanceof String || first instanceof F7Exception) {
      return first;
    }
    return "";
  }
}
