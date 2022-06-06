import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class RAND extends AbstractFormula {
  static SELF: RAND = new RAND();
  NAME = FormulaName.RAND;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 0, this.NAME);
    return Math.random();
  }
}
