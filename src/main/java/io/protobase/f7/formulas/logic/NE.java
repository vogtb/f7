package io.protobase.f7.formulas.logic;

import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class NE extends AbstractFormula {
  public static FormulaName NAME = FormulaName.NE;
  public static NE SELF = new NE();

  public NE() {
    super();
  }

  public NE(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 2, NAME);
    // Just use logic from EQ to see if they're equal, and invert.
    Object equal = new EQ(lookup, collateralLookup).apply(origin, values);
    if (equal instanceof F7Exception) {
      return equal;
    }
    return !Converters.toBoolean(equal);
  }
}
