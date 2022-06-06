import { F7Exception } from "../../errors/F7Exception";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class N extends AbstractFormula {
  static SELF: N = new N();
  NAME = FormulaName.N;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const first = Converters.first(this.collateralLookup(origin, values[0]));
    if (first instanceof F7Exception) {
      return first;
    }
    return Converters.toNumber(Converters.textToZero(first));
  }
}
