import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class FALSE extends AbstractFormula {
  static SELF: FALSE = new FALSE();
  NAME = FormulaName.FALSE;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 0, this.NAME);
    return false;
  }
}
