import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class ATAN extends AbstractFormula {
  static SELF: ATAN = new ATAN();
  NAME = FormulaName.ATAN;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    return Math.atan(
      Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])))
    );
  }
}
