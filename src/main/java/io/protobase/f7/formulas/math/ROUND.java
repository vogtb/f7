package io.protobase.f7.formulas.math;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ROUND extends AbstractFormula {
  public static FormulaName NAME = FormulaName.ROUND;
  public static ROUND SELF = new ROUND();

  public ROUND() {
    super();
  }

  public ROUND(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLengthBetween(values.length, 1, 2, NAME);
    Object value = Converters.first(collateralLookup.apply(origin, values[0]));
    if (values.length == 1) {
      return ((double) Math.round(Converters.toDouble(value)));
    }
    Double placesRaw = Converters.toDouble(Converters.first(collateralLookup.apply(origin, values[1])));
    int places;
    if (placesRaw > 0) {
      places = new Double(Math.floor(placesRaw.floatValue())).intValue();
    } else {
      places = new Double(Math.ceil(placesRaw.floatValue())).intValue();
    }
    BigDecimal bd = new BigDecimal(Converters.toDouble(value));
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}
