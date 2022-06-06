import { NumException } from "../../errors/NumException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class SQRT extends AbstractFormula {
  static SELF: SQRT = new SQRT();
  NAME = FormulaName.SQRT;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const value = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])));
    if (value < 0) {
      return new NumException(
        "Function SQRT parameter 1 value is negative. It should be greater than or equal to zero."
      );
    }
    return Math.sqrt(value);
  }
}
