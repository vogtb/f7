import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class ABS extends AbstractFormula {
  static SELF: ABS = new ABS();
  NAME = FormulaName.ABS;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const first = Converters.first(this.collateralLookup(origin, values[0]));
    return Math.abs(Converters.toNumber(first));
  }
}
