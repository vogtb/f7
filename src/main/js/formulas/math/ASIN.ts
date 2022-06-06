import { NumException } from "../../errors/NumException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class ASIN extends AbstractFormula {
  static SELF: ASIN = new ASIN();
  NAME = FormulaName.ASIN;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const value = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])));
    if (value < -1 || value > 1) {
      return new NumException(
        `ASIN parameter 1 value is ${value}. Valid values should be between -1 and 1, inclusively`
      );
    }
    return Math.asin(value);
  }
}
