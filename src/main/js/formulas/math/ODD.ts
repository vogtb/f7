import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class ODD extends AbstractFormula {
  static SELF: ODD = new ODD();
  NAME = FormulaName.ODD;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const value = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])));
    let rounded;
    if (value < 0) {
      rounded = Math.floor(value);
      if (rounded % 2 == 0) {
        return rounded - 1.0;
      }
    } else {
      rounded = Math.ceil(value);
      if (rounded % 2 == 0) {
        return rounded + 1.0;
      }
    }
    return rounded;
  }
}
