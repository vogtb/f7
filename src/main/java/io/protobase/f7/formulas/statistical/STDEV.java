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

public class STDEV extends AbstractFormula {
  public static FormulaName NAME = FormulaName.STDEV;
  public static STDEV SELF = new STDEV();

  public STDEV() {
    super();
  }

  public STDEV(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    super(lookup, collateralLookup);
  }

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkAtLeastLength(values.length, 1, NAME);
    List<Double> numbers = Arrays.stream(values)
        .map(lookup)
        .flatMap(Mappers::toFlatStreamGridFilterOnlyNumberLiterals)
        .map(Converters::toDouble)
        .collect(Collectors.toList());
    if (numbers.size() == 1) {
      return new DivException("Formula STDEV caused an error when attempting to divide by zero.");
    }
    double sum = 0.0;
    double standardDev = 0.0;
    int length = numbers.size();
    for (double number : numbers) {
      sum += number;
    }
    double mean = sum / length;
    for (double number : numbers) {
      standardDev += Math.pow(number - mean, 2);
    }
    return Math.sqrt(standardDev / (length - 1));
  }
}
