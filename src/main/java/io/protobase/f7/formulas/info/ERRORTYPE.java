package io.protobase.f7.formulas.info;

import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ERRORTYPE extends AbstractFormula {
  public static FormulaName NAME = FormulaName.ERRORTYPE;
  public static ERRORTYPE SELF = new ERRORTYPE();

  public ERRORTYPE() {
    super();
  }

  public ERRORTYPE(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 1, NAME);
    try {
      Object first = Converters.first(collateralLookup.apply(origin, values[0]));
      if (first instanceof F7Exception) {
        return (double) Converters.castAsF7Error(first).getName().getCode();
      }
      return new NAException("Formula ERRORTYPE parameter 1 is not an error.");
    } catch (F7Exception exception) {
      return (double) exception.getName().getCode();
    }
  }
}
