package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.NumException;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RANDBETWEEN extends AbstractFormula {
  public static FormulaName NAME = FormulaName.RANDBETWEEN;
  public static RANDBETWEEN SELF = new RANDBETWEEN();

  public RANDBETWEEN() {
    super();
  }

  public RANDBETWEEN(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 2, NAME);
    Double low = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0])));
    Double high = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[1])));
    double lowRounded = Math.ceil(low);
    double highRounded = Math.ceil(high);
    if (lowRounded > highRounded) {
      throw new NumException(String.format("Formula RANDBETWEEN parameter 2 value is %f, but it should be greater than or equal to %f.",
          low, high));
    }
    double diff = Math.abs(lowRounded - highRounded);
    return (double) Math.round(lowRounded + (Math.random() * diff));
  }
}
