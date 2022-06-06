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

public class AVERAGEA extends AbstractFormula {
  public static FormulaName NAME = FormulaName.AVERAGEA;
  public static AVERAGEA SELF = new AVERAGEA();

  public AVERAGEA() {
    super();
  }

  public AVERAGEA(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkAtLeastLength(values.length, 1, NAME);
    List<Double> numbers = Arrays.stream(values)
        .map(lookup)
        .flatMap(Mappers::toFlatStreamFilterOnlyNumeric)
        .map(Converters::toDouble)
        .collect(Collectors.toList());
    if (numbers.size() == 0) {
      return new DivException("Formula AVERAGEA caused an error when attempting to divide by zero.");
    }
    double sum = 0;
    for (Double number : numbers) {
      sum += number;
    }
    return sum / numbers.size();
  }
}
