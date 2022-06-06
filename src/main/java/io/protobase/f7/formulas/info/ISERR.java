package io.protobase.f7.formulas.info;

import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.errors.F7ExceptionName;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ISERR extends AbstractFormula {
  public static FormulaName NAME = FormulaName.ISERR;
  public static ISERR SELF = new ISERR();

  public ISERR() {
    super();
  }

  public ISERR(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... args) {
    checkLength(args.length, 1, NAME);
    try {
      Object first = Converters.first(collateralLookup.apply(origin, args[0]));
      if (first instanceof F7Exception) {
        return !((F7Exception) first).getName().equals(F7ExceptionName.NA);
      }
      return false;
    } catch (F7Exception exception) {
      return !exception.getName().equals(F7ExceptionName.NA);
    }
  }
}
