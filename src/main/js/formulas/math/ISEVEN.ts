import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { Numbers } from "../../utils/Numbers";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";
import { ROUNDDOWN } from "./ROUNDDOWN";

export class ISEVEN extends AbstractFormula {
  static SELF: ISEVEN = new ISEVEN();
  NAME = FormulaName.ISEVEN;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const value = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])));
    const rounded = Converters.toNumber(ROUNDDOWN.SELF.run(null, value));
    return Numbers.isZero(rounded % 2);
  }
}
