import { F7Exception } from "../../errors/F7Exception";
import { F7ExceptionName } from "../../errors/F7ExceptionName";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class ISNA extends AbstractFormula {
  static SELF: ISNA = new ISNA();
  NAME = FormulaName.ISNA;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const first = Converters.first(this.collateralLookup(origin, values[0]));
    if (first instanceof F7Exception) {
      return Converters.castAsF7Exception(first).name === F7ExceptionName.NA;
    }
    return false;
  }
}
