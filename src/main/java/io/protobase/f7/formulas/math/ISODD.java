package io.protobase.f7.formulas.math;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ISODD extends AbstractFormula {
  public static FormulaName NAME = FormulaName.ISODD;
  public static ISODD SELF = new ISODD();

  public ISODD() {
    super();
  }

  public ISODD(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    double number = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0])));
    double rounded = Converters.toDouble(ROUNDDOWN.SELF.apply(null, number));
    double remainder = rounded % 2;
    return remainder == 1 || remainder == -1;
  }
}
