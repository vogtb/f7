package io.protobase.f7.formulas.statistical;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;
import io.protobase.f7.utils.Mappers;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class MAXA extends AbstractFormula {
  public static FormulaName NAME = FormulaName.MAXA;
  public static MAXA SELF = new MAXA();

  public MAXA() {
    super();
  }

  public MAXA(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkAtLeastLength(values.length, 1, NAME);
    return Stream.of(values)
        .map(lookup)
        .flatMap(Mappers::toFlatStreamFilterOnlyNumeric)
        .map(Converters::toDouble)
        .reduce(Double::max)
        .orElse(0.0);
  }
}
