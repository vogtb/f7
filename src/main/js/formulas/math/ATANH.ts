import { NumException } from "../../errors/NumException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class ATANH extends AbstractFormula {
  static SELF: ATANH = new ATANH();
  NAME = FormulaName.ATANH;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const value = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])));
    if (value <= -1 || value >= 1) {
      return new NumException(
        `ATANH parameter 1 value is ${value}. Valid values are between -1 and 1 exclusively.`
      );
    }
    return (Math.log(1 + value) - Math.log(1 - value)) / 2;
  }
}
