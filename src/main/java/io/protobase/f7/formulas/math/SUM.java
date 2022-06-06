package io.protobase.f7.formulas.math;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;
import io.protobase.f7.utils.Mappers;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Sum values together.
 */
public class SUM extends AbstractFormula {
  public static FormulaName NAME = FormulaName.SUM;
  public static SUM SELF = new SUM();

  public SUM() {
    super();
  }

  public SUM(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  public Object internal(GridColumnRowKey origin, Object... values) {
    checkAtLeastLength(values.length, 1, NAME);
    return Arrays.stream(values)
        .map(this.lookup)
        .flatMap(Mappers::toFlatStream)
        .filter(Objects::nonNull)
        .map(Converters::toDouble)
        .reduce(Double::sum)
        .orElse(0.0);
  }
}
