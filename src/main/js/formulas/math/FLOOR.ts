import { isNotUndefined } from "../../utils/Other";
import { DivException } from "../../errors/DivException";
import { NumException } from "../../errors/NumException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class FLOOR extends AbstractFormula {
  static SELF: FLOOR = new FLOOR();
  NAME = FormulaName.FLOOR;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLengthBetween(values.length, 1, 2, this.NAME);
    const value = Converters.first(this.collateralLookup(origin, values[0]));
    const places = isNotUndefined(values[1])
      ? Converters.toNumber(Converters.first(this.collateralLookup(origin, values[1])))
      : 1;
    if (Converters.toPositiveZero(places) === 0) {
      return new DivException("Function FLOOR parameter 2 cannot be zero.");
    }
    if (value > 0 && places < 0) {
      return new NumException("Parameters of FLOOR must have same signs.");
    }
    const n = Converters.toNumber(value);
    // If we're just rounding to integer, just floor it no matter what.
    if (Converters.toPositiveZero(places) === 1) {
      return Math.floor(n);
    }
    return n - (n % places);
  }
}
