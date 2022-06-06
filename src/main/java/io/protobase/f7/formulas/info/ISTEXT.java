package io.protobase.f7.formulas.info;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ISTEXT extends AbstractFormula {
  public static FormulaName NAME = FormulaName.ISTEXT;
  public static ISTEXT SELF = new ISTEXT();

  public ISTEXT() {
    super();
  }

  public ISTEXT(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    return Converters.first(collateralLookup.apply(origin, values[0])) instanceof String;
  }
}
