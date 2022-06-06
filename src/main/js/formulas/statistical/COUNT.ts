import { isNotNull } from "../../utils/Other";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { Counter } from "../../utils/Counter";
import { Mappers } from "../../utils/Mappers";
import { Predicates } from "../../utils/Predicates";
import { Reducers } from "../../utils/Reducers";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class COUNT extends AbstractFormula {
  static SELF: COUNT = new COUNT();
  NAME = FormulaName.COUNT;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkAtLeastLength(values.length, 1, this.NAME);
    const counter = new Counter<number>();
    values
      .map(this.lookup)
      .map(Mappers.flattenGridsToArrays) // 1, 2, [null, 1, "0", "Text.", null]
      .map(Mappers.ensureAllAreArrays) // [1], [2], [null, 1, "0", null]
      .reduce(Reducers.join) // [1, 2, null, 1, "0", null]
      .filter(isNotNull) // [1, 2, 1, "0"]
      .filter(Predicates.isNumeric)
      .map(Converters.toNumber)
      .map((value) => counter.count(value)); // [1, 2, 1]
    return counter.getCount();
  }
}
