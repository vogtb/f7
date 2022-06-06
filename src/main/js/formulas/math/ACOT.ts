import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class ACOT extends AbstractFormula {
  static SELF: ACOT = new ACOT();
  NAME = FormulaName.ACOT;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const divisor = Converters.toNumber(this.collateralLookup(origin, Converters.first(values[0])));
    if (divisor === 0) {
      return 1.570796327;
    }
    return Math.atan(1 / divisor);
  }
}
