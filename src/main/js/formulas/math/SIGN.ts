import { F7Exception } from "../../errors/F7Exception";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { Numbers } from "../../utils/Numbers";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class SIGN extends AbstractFormula {
  static SELF: SIGN = new SIGN();
  NAME = FormulaName.SIGN;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const value = Converters.first(this.collateralLookup(origin, values[0]));
    if (value instanceof F7Exception) {
      return value;
    }
    const number = Converters.toNumber(value);
    if (Numbers.isZero(number)) {
      return 0;
    }
    return number > 0 ? 1 : -1;
  }
}
