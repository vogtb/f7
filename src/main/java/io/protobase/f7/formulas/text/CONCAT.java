package io.protobase.f7.formulas.text;

import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * TODO/HACK
 * In Google Sheets: =CONCAT({"Hello","One"}, {"There","Two"}) results in "HelloThere"
 * In Excel:         =CONCAT({"Hello","One"}, {"There","Two"}) results in "HelloOneThereTwo"
 * <p>
 * "Fixing" this is tricky. Which one should be considered correct? Are we integrating with both?
 */
public class CONCAT extends AbstractFormula {
  public static FormulaName NAME = FormulaName.CONCAT;
  public static CONCAT SELF = new CONCAT();

  public CONCAT() {
    super();
  }

  public CONCAT(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 2, NAME);
    Object first = Converters.first(collateralLookup.apply(origin, values[0]));
    Object second = Converters.first(collateralLookup.apply(origin, values[1]));
    if (first instanceof F7Exception) {
      return first;
    }
    if (second instanceof F7Exception) {
      return second;
    }
    return Converters.toText(first) + Converters.toText(second);
  }
}
