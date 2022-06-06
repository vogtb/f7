package io.protobase.f7.formulas.logic;

import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.errors.F7ExceptionName;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class IFNA extends AbstractFormula {
  public static FormulaName NAME = FormulaName.IFERROR;
  public static IFNA SELF = new IFNA();

  public IFNA() {
    super();
  }

  public IFNA(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 2, NAME);
    Object value = Converters.first(collateralLookup.apply(origin, values[0]));
    if ((value instanceof F7Exception && ((F7Exception) value).getName().equals(F7ExceptionName.NA))) {
      return Converters.first(collateralLookup.apply(origin, values[1]));
    }
    return value;
  }
}
