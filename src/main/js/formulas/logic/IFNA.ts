import { F7Exception } from "../../errors/F7Exception";
import { F7ExceptionName } from "../../errors/F7ExceptionName";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class IFNA extends AbstractFormula {
  static SELF: IFNA = new IFNA();
  NAME = FormulaName.IFNA;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 2, this.NAME);
    const value = this.collateralLookup(origin, values[0]);
    const first = Converters.first(value);
    if (
      first instanceof F7Exception &&
      Converters.castAsF7Exception(first).name === F7ExceptionName.NA
    ) {
      return this.collateralLookup(origin, values[1]);
    }
    return value;
  }
}
