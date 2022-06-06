import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class PI extends AbstractFormula {
  static SELF: PI = new PI();
  NAME = FormulaName.PI;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 0, this.NAME);
    return Math.PI;
  }
}
