import { NumException } from "../../errors/NumException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class ACOS extends AbstractFormula {
  static SELF: ACOS = new ACOS();
  NAME = FormulaName.ACOS;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const n = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])));
    if (n < -1 || n > 1) {
      return new NumException(
        `Function ACOS parameter 1 value is ${n}. Valid values are between -1 and 1 inclusive.`
      );
    }
    return Math.acos(n);
  }
}
