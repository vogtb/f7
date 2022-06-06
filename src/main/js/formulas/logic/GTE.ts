import { F7Exception } from "../../errors/F7Exception";
import { ComparisonResult } from "../../models/common/ComparisonResults";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Compare } from "../../utils/Compare";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class GTE extends AbstractFormula {
  static SELF: GTE = new GTE();
  NAME = FormulaName.GTE;

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
    const comparison = Compare.compare(first, second);
    return comparison === ComparisonResult.GREATER_THAN || comparison === ComparisonResult.EQUAL;
  }
}
