import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class NOT extends AbstractFormula {
  static SELF: NOT = new NOT();
  NAME = FormulaName.NOT;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    return !Converters.toBoolean(Converters.first(this.collateralLookup(origin, values[0])));
  }
}
