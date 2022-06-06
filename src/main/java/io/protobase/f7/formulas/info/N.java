package io.protobase.f7.formulas.info;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class N extends AbstractFormula {
  public static FormulaName NAME = FormulaName.N;

  public N(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... args) {
    checkLength(args.length, 1, NAME);
    return Converters.first(args[0]);
  }
}
