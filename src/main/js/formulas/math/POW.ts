import { F7Exception } from "../../errors/F7Exception";
import { NumException } from "../../errors/NumException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class POW extends AbstractFormula {
  static SELF: POW = new POW();
  NAME = FormulaName.POW;

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
    const result = Math.pow(firstNumber, secondNumber);
    if (isNaN(result) || !Number.isFinite(result)) {
      return new NumException("POW evaluates to an imaginary number.");
    }
    return result;
  }
}
