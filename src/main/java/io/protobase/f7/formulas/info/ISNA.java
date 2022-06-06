package io.protobase.f7.formulas.info;

import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.errors.F7ExceptionName;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ISNA extends AbstractFormula {
  public static FormulaName NAME = FormulaName.ISNA;
  public static ISNA SELF = new ISNA();

  public ISNA() {
    super();
  }

  public ISNA(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    Object value = Converters.first(collateralLookup.apply(origin, values[0]));
    if (value instanceof F7Exception) {
      return ((F7Exception) value).getName().equals(F7ExceptionName.NA);
    }
    return false;
  }
}
