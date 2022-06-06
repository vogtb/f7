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

public class AVEDEV extends AbstractFormula {
  public static FormulaName NAME = FormulaName.AVEDEV;
  public static AVEDEV SELF = new AVEDEV();

  public AVEDEV() {
    super();
  }

  public AVEDEV(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
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
    if (numbers.size() == 0) {
      return new DivException("Formula AVEDEV caused an error when attempting to divide by zero.");
    }
    double sum = 0;
    for (Double number : numbers) {
      sum += number;
    }
    double mean = sum / numbers.size();
    for (int i = 0; i < numbers.size(); i++) {
      numbers.set(i, Math.abs(numbers.get(i) - mean));
    }
    double meanSum = 0;
    for (Double number : numbers) {
      meanSum += number;
    }
    return meanSum / numbers.size();
  }
}
