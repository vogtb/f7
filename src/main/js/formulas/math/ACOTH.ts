import { NumException } from "../../errors/NumException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class ACOTH extends AbstractFormula {
  static SELF: ACOTH = new ACOTH();
  NAME = FormulaName.ACOTH;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const value = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])));
    if (value <= 1 && value >= -1) {
      throw new NumException(
        `ACOTH parameter 1 value is ${value}. Valid values cannot be between -1 and 1 inclusive.`
      );
    }
    return 0.5 * Math.log((value + 1) / (value - 1));
  }
}
