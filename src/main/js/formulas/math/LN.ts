import { NumException } from "../../errors/NumException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class LN extends AbstractFormula {
  static SELF: LN = new LN();
  NAME = FormulaName.LN;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const value = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])));
    if (value <= 0) {
      return new NumException(`LN parameter 1 value is ${value}. It should be greater than 0.`);
    }
    return Math.log(value);
  }
}
