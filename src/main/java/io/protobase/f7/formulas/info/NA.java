package io.protobase.f7.formulas.info;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;

import java.util.function.BiFunction;
import java.util.function.Function;

public class NA extends AbstractFormula {
  public static FormulaName NAME = FormulaName.NA;
  public static NA SELF = new NA();

  public NA() {
    super();
  }

  public NA(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... args) {
    checkLength(args.length, 0, NAME);
    return new NAException();
  }
}
