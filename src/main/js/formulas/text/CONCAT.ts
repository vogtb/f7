import { F7Exception } from "../../errors/F7Exception";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class CONCAT extends AbstractFormula {
  static SELF: CONCAT = new CONCAT();
  NAME = FormulaName.CONCAT;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 2, this.NAME);
    const first = Converters.first(this.collateralLookup(origin, values[0]));
    const second = Converters.first(this.collateralLookup(origin, values[1]));
    if (first instanceof F7Exception) {
      return first;
    }
    if (second instanceof F7Exception) {
      return second;
    }
    return `${Converters.toText(first)}${Converters.toText(second)}`;
  }
}
