import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class TANH extends AbstractFormula {
  static SELF: TANH = new TANH();
  NAME = FormulaName.TANH;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    return Math["tanh"](
      Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])))
    );
  }
}
