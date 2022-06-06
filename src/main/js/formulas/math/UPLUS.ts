import { F7Exception } from "../../errors/F7Exception";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class UPLUS extends AbstractFormula {
  static SELF: UPLUS = new UPLUS();
  NAME = FormulaName.UPLUS;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const value = Converters.first(this.collateralLookup(origin, values[0]));
    if (value instanceof F7Exception) {
      return value;
    }
    return Converters.toNumber(value);
  }
}
