package io.protobase.f7.formulas.lookup;

import io.protobase.f7.errors.NumException;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.formulas.math.ROUNDDOWN;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class CHOOSE extends AbstractFormula {
  public static FormulaName NAME = FormulaName.CHOOSE;
  public static CHOOSE SELF = new CHOOSE();

  public CHOOSE() {
    super();
  }

  public CHOOSE(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkAtLeastLength(values.length, 2, NAME);
    int index = Converters.toDouble(ROUNDDOWN.SELF.apply(null,
        Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[0]))))).intValue();
    if (index < 1 || index > values.length - 1) {
      return new NumException(String.format("CHOOSE parameter 1 value is %d. Valid values are between 1 and %d inclusive.",
          index, values.length - 1));
    }
    return Converters.first(lookup.apply(values[index]));
  }
}
