import { DivException } from "../../errors/DivException";
import { F7Exception } from "../../errors/F7Exception";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { Numbers } from "../../utils/Numbers";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";
import { ROUNDDOWN } from "./ROUNDDOWN";

export class QUOTIENT extends AbstractFormula {
  static SELF: QUOTIENT = new QUOTIENT();
  NAME = FormulaName.QUOTIENT;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 2, this.NAME);
    const first = Converters.first(this.collateralLookup(origin, values[0]));
    const second = Converters.first(this.collateralLookup(origin, values[1]));
    if (first instanceof F7Exception) {
      return first;
    }
    if (second instanceof F7Exception) {
      return second;
    }
    const dividend = Converters.toNumber(first);
    const divisor = Converters.toNumber(second);
    if (Numbers.isZero(divisor)) {
      throw new DivException("Formula DIVIDE parameter 2 cannot be zero.");
    }
    return ROUNDDOWN.SELF.run(null, dividend / divisor);
  }
}
