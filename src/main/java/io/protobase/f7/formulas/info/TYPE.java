package io.protobase.f7.formulas.info;

import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TYPE extends AbstractFormula {
  public static FormulaName NAME = FormulaName.TYPE;
  public static TYPE SELF = new TYPE();

  public TYPE() {
    super();
  }

  public TYPE(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... args) {
    checkLength(args.length, 1, NAME);
    Object first = args[0];
    if (first instanceof Double) {
      return 1;
    }
    if (first instanceof String) {
      return 2;
    }
    if (first instanceof Boolean) {
      return 4;
    }
    if (first instanceof F7Exception) {
      return 16;
    }
    if (first instanceof List) {
      return 64;
    }
    return 128;
  }
}
