package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NumException;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class LOG extends AbstractFormula {
  public static FormulaName NAME = FormulaName.LOG;
  public static LOG SELF = new LOG();

  public LOG() {
    super();
  }

  public LOG(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLengthBetween(values.length, 1, 2, NAME);
    Double first = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0])));
    Double second = 10.0;
    if (values.length > 1) {
      second = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[1])));
    }
    if (first <= 0) {
      return new NumException(String.format("LOG parameter 1 value is %f. It should be greater than 0.", first));
    }
    if (second <= 0) {
      return new NumException(String.format("LOG parameter 2 value is %f. It should be greater than 0.", second));
    }
    if (second == 10.0) {
      return Math.log10(first);
    }
    Double numerator = Math.log(first);
    Double denominator = Math.log(second);
    if (denominator == 0) {
      throw new DivException("LOG caused a divide by zero error.");
    }
    return numerator / denominator;
  }
}
