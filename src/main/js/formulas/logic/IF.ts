import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class IF extends AbstractFormula {
  static SELF: IF = new IF();
  NAME = FormulaName.LT;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 3, this.NAME);
    const logicalExpression = Converters.toBoolean(
      Converters.first(this.collateralLookup(origin, values[0]))
    );
    if (logicalExpression === true) {
      return Converters.first(this.collateralLookup(origin, values[1]));
    }
    return Converters.first(this.collateralLookup(origin, values[2]));
  }
}
