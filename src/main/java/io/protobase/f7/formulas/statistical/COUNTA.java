package io.protobase.f7.formulas.statistical;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Mappers;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class COUNTA extends AbstractFormula {
  public static FormulaName NAME = FormulaName.COUNTA;
  public static COUNTA SELF = new COUNTA();

  public COUNTA() {
    super();
  }

  public COUNTA(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkAtLeastLength(values.length, 1, NAME);
    return ((double) Arrays.stream(values)
        .map(lookup)
        .flatMap(Mappers::toFlatStream)
        .collect(Collectors.toList()).size());
  }
}
