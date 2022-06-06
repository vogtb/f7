import { F7Exception } from "../../errors/F7Exception";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class IFERROR extends AbstractFormula {
  static SELF: IFERROR = new IFERROR();
  NAME = FormulaName.IFERROR;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 2, this.NAME);
    const value = this.collateralLookup(origin, values[0]);
    if (Converters.first(value) instanceof F7Exception) {
      return this.collateralLookup(origin, values[1]);
    }
    return value;
  }
}
