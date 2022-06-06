import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class TRUE extends AbstractFormula {
  static SELF: TRUE = new TRUE();
  NAME = FormulaName.TRUE;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 0, this.NAME);
    return true;
  }
}
