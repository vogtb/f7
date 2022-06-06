import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class ATAN2 extends AbstractFormula {
  static SELF: ATAN2 = new ATAN2();
  NAME = FormulaName.ATAN2;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 2, this.NAME);
    const first = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])));
    const second = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[1])));
    return Math.atan2(first, second);
  }
}
