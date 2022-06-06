package io.protobase.f7.formulas.info;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ISBLANK extends AbstractFormula {
  public static FormulaName NAME = FormulaName.ISBLANK;
  public static ISBLANK SELF = new ISBLANK();

  public ISBLANK() {
    super();
  }

  public ISBLANK(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    return Objects.isNull(Converters.first(collateralLookup.apply(origin, values[0])));
  }
}
