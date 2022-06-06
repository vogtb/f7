package io.protobase.f7.formulas.text;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;
import io.protobase.f7.utils.Mappers;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CONCATENATE extends AbstractFormula {
  public static FormulaName NAME = FormulaName.CONCATENATE;
  public static CONCATENATE SELF = new CONCATENATE();

  public CONCATENATE() {
    super();
  }

  public CONCATENATE(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkAtLeastLength(values.length, 1, NAME);
    return Arrays.stream(values)
        .map(lookup)
        .flatMap(Mappers::toFlatStream)
        .map(Mappers::throwIfException)
        .filter(Objects::nonNull)
        .map(Converters::toText)
        .reduce(String::concat)
        .orElse("");
  }
}
