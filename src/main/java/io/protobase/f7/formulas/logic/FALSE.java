package io.protobase.f7.formulas.logic;

import io.protobase.f7.formulas.AbstractFormula;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.GridColumnRowKey;

public class FALSE extends AbstractFormula {
  public static FormulaName NAME = FormulaName.FALSE;
  public static FALSE SELF = new FALSE();

  @Override
  public Object internal(GridColumnRowKey origin, Object... arguments) {
    checkLength(arguments.length, 0, NAME);
    return false;
  }
}
