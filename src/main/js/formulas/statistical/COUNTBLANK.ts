import { isNull } from "../../utils/Other";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Counter } from "../../utils/Counter";
import { Mappers } from "../../utils/Mappers";
import { Reducers } from "../../utils/Reducers";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class COUNTBLANK extends AbstractFormula {
  static SELF: COUNTBLANK = new COUNTBLANK();
  NAME = FormulaName.COUNTBLANK;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkAtLeastLength(values.length, 1, this.NAME);
    const counter = new Counter<Complex>();
    values
      .map(this.lookup)
      .map(Mappers.flattenGridsToArrays) // 1, 2, [null, 1, "0", "Nope.", null]
      .map(Mappers.ensureAllAreArrays) // [1], [2], [null, 1, "0", null]
      .reduce(Reducers.join) // [1, 2, null, 1, "0", null]
      .filter(isNull) // [null, null]
      .map((value) => counter.count(value)); // [null, null]
    return counter.getCount();
  }
}
