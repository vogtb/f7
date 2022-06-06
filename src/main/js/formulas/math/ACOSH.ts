import { NumException } from "../../errors/NumException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class ACOSH extends AbstractFormula {
  static SELF: ACOSH = new ACOSH();
  NAME = FormulaName.ACOSH;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const value = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])));
    if (value < 1.0) {
      return new NumException(
        "ACOSH parameter 1 value is 0. It should be greater than or equal to 1."
      );
    }
    return Math.log(value + Math.sqrt(value * value - 1));
  }
}
