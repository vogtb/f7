import { F7Exception } from "../../errors/F7Exception";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class UMINUS extends AbstractFormula {
  static SELF: UMINUS = new UMINUS();
  NAME = FormulaName.UMINUS;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const value = Converters.first(this.collateralLookup(origin, values[0]));
    if (value instanceof F7Exception) {
      return value;
    }
    const n = Converters.toNumber(value);
    return Converters.toPositiveZero(n * -1);
  }
}
