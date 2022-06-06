package io.protobase.f7.formulas.math;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ROUNDUP extends AbstractFormula {
  public static FormulaName NAME = FormulaName.ROUNDUP;
  public static ROUNDUP SELF = new ROUNDUP();

  public ROUNDUP() {
    super();
  }

  public ROUNDUP(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLengthBetween(values.length, 1, 2, NAME);
    Object value = Converters.first(collateralLookup.apply(origin, values[0]));
    Double placesRaw;
    if (values.length == 1) {
      placesRaw = 0.0;
    } else {
      placesRaw = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[1])));
    }
    Double number = Converters.toDouble(value);
    BigDecimal bd = new BigDecimal(number.toString());
    int places;
    if (placesRaw > 0) {
      places = new Double(Math.floor(placesRaw.floatValue())).intValue();
    } else {
      places = new Double(Math.ceil(placesRaw.floatValue())).intValue();
    }
    if (number < 0) {
      bd = bd.setScale(places, RoundingMode.FLOOR);
    } else {
      bd = bd.setScale(places, RoundingMode.CEILING);
    }
    return bd.doubleValue();
  }
}
