package io.protobase.f7.formulas.math;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;

public class RAND extends AbstractFormula {
  public static FormulaName NAME = FormulaName.RAND;
  public static RAND SELF = new RAND();

  @Override
  public Object internal(GridColumnRowKey origin, Object... values) {
    checkLength(values.length, 0, NAME);
    return Math.random();
  }
}
