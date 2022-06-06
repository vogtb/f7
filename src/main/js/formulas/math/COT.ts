import { DivException } from "../../errors/DivException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { Numbers } from "../../utils/Numbers";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class COT extends AbstractFormula {
  static SELF: COT = new COT();
  NAME = FormulaName.COT;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const divisor = Math.tan(
      Converters.toNumber(Converters.first(this.collateralLookup(origin, values[0])))
    );
    if (Numbers.isZero(divisor)) {
      return new DivException("Evaluation of COT caused a divide by zero error.");
    }
    return 1 / divisor;
  }
}
