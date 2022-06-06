import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class ISNONTEXT extends AbstractFormula {
  static SELF: ISNONTEXT = new ISNONTEXT();
  NAME = FormulaName.ISNONTEXT;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const first = Converters.first(this.collateralLookup(origin, values[0]));
    return typeof first !== "string";
  }
}
