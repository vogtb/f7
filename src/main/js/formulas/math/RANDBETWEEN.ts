import { NumException } from "../../errors/NumException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class RANDBETWEEN extends AbstractFormula {
  static SELF: RANDBETWEEN = new RANDBETWEEN();
  NAME = FormulaName.RANDBETWEEN;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 2, this.NAME);
    const low = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])));
    const high = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[1])));
    const lowRounded = Math.ceil(low);
    const highRounded = Math.ceil(high);
    if (lowRounded > highRounded) {
      return new NumException(
        `Formula RANDBETWEEN parameter 2 value is ${low}, but it should be greater than or equal to ${high}.`
      );
    }
    const diff = Math.abs(lowRounded - highRounded);
    return Math.round(lowRounded + Math.random() * diff);
  }
}
