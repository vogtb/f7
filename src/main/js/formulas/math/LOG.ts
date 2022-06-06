import { DivException } from "../../errors/DivException";
import { NumException } from "../../errors/NumException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class LOG extends AbstractFormula {
  static SELF: LOG = new LOG();
  NAME = FormulaName.LOG;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLengthBetween(values.length, 1, 2, this.NAME);
    const first = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])));
    let second = 10.0;
    if (values.length > 1) {
      second = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[1])));
    }
    if (first <= 0) {
      return new NumException(`LOG parameter 1 value is ${first}. It should be greater than 0.`);
    }
    if (second <= 0) {
      return new NumException(`LOG parameter 2 value is ${second}. It should be greater than 0.`);
    }
    if (second == 10.0) {
      return Math["log10"](first);
    }
    const numerator = Math.log(first);
    const denominator = Math.log(second);
    if (denominator == 0) {
      throw new DivException("LOG caused a divide by zero error.");
    }
    return numerator / denominator;
  }
}
