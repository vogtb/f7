package io.protobase.f7.formulas.statistical;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.utils.Converters;
import io.protobase.f7.utils.Mappers;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AVERAGE extends AbstractFormula {
  public static FormulaName NAME = FormulaName.AVERAGE;
  public static AVERAGE SELF = new AVERAGE();

  public AVERAGE() {
    super();
  }

  public AVERAGE(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkAtLeastLength(values.length, 1, NAME);
    List<Double> numbers = Arrays.stream(values)
        .map(lookup)
        .flatMap(Mappers::toFlatStream)
        .map(Converters::toDouble)
        .collect(Collectors.toList());
    if (numbers.size() == 0) {
      return new DivException("Formula AVERAGE caused an error when attempting to divide by zero.");
    }
    double sum = 0;
    for (Double number : numbers) {
      sum += number;
    }
    return sum / numbers.size();
  }
}
