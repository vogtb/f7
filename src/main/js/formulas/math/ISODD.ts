import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";
import { ROUNDDOWN } from "./ROUNDDOWN";

export class ISODD extends AbstractFormula {
  static SELF: ISODD = new ISODD();
  NAME = FormulaName.ISODD;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const value = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])));
    const rounded = Converters.toNumber(ROUNDDOWN.SELF.run(null, value));
    return Math.abs(rounded % 2) === 1;
  }
}
