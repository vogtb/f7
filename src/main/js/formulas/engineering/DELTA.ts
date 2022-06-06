import { isNotUndefined } from "../../utils/Other";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class DELTA extends AbstractFormula {
  static SELF: DELTA = new DELTA();
  NAME = FormulaName.DELTA;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLengthBetween(values.length, 1, 2, this.NAME);
    const first = Converters.first(this.collateralLookup(origin, values[0]));
    const second = isNotUndefined(values[1])
      ? Converters.first(this.collateralLookup(origin, values[1]))
      : 0;
    if (Converters.toNumber(first) === Converters.toNumber(second)) {
      return 1;
    }
    return 0;
  }
}
