import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class ISBLANK extends AbstractFormula {
  static SELF: ISBLANK = new ISBLANK();
  NAME = FormulaName.ISBLANK;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    return Converters.first(this.collateralLookup(origin, values[0])) === null;
  }
}
