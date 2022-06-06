package io.protobase.f7.formulas.statistical;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Mappers;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The count of number values in a set of data.
 */
public class COUNTBLANK extends AbstractFormula {
  public static FormulaName NAME = FormulaName.COUNTBLANK;
  public static COUNTBLANK SELF = new COUNTBLANK();

  public COUNTBLANK() {
    super();
  }

  public COUNTBLANK(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkAtLeastLength(values.length, 1, NAME);
    return ((double) Stream.of(values)
        .map(lookup)
        .flatMap(Mappers::toFlatStream)
        .filter(Objects::isNull)
        .collect(Collectors.toList()).size());
  }
}
