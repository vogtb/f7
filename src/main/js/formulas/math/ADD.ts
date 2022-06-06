import { F7Exception } from "../../errors/F7Exception";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class ADD extends AbstractFormula {
  static SELF: ADD = new ADD();
  NAME = FormulaName.ADD;

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
    const firstNumber = Converters.toNumber(first);
    const secondNumber = Converters.toNumber(second);
    return firstNumber + secondNumber;
  }
}
