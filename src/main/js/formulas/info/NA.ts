import { NAException } from "../../errors/NAException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class NA extends AbstractFormula {
  static SELF: NA = new NA();
  NAME = FormulaName.NA;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 0, this.NAME);
    return new NAException();
  }
}
