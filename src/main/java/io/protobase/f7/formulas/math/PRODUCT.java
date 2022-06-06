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

public class PRODUCT extends AbstractFormula {
  public static FormulaName NAME = FormulaName.PRODUCT;
  public static PRODUCT SELF = new PRODUCT();

  public PRODUCT() {
    super();
  }

  public PRODUCT(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkAtLeastLength(values.length, 1, NAME);
    return Arrays.stream(values)
        .map(lookup)
        .flatMap(Mappers::toFlatStream)
        .filter(Objects::nonNull)
        .map(Converters::toDouble)
        .reduce((first, second) -> {
          if (Objects.nonNull(second)) {
            return first * second;
          }
          return first;
        })
        .orElse(0.0);
  }
}
