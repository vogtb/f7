package io.protobase.f7.formulas.logic;

import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class IFERROR extends AbstractFormula {
  public static FormulaName NAME = FormulaName.IFERROR;
  public static IFERROR SELF = new IFERROR();

  public IFERROR() {
    super();
  }

  public IFERROR(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 2, NAME);
    Object value = Converters.first(collateralLookup.apply(origin, values[0]));
    return value instanceof F7Exception ? Converters.first(collateralLookup.apply(origin, values[1])) : value;
  }
}
