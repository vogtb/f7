import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class TAN extends AbstractFormula {
  static SELF: TAN = new TAN();
  NAME = FormulaName.TAN;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    return Math.tan(
      Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])))
    );
  }
}
