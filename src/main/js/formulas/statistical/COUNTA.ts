import { isNotNull } from "../../utils/Other";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Counter } from "../../utils/Counter";
import { Mappers } from "../../utils/Mappers";
import { Predicates } from "../../utils/Predicates";
import { Reducers } from "../../utils/Reducers";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class COUNTA extends AbstractFormula {
  static SELF: COUNTA = new COUNTA();
  NAME = FormulaName.COUNTA;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkAtLeastLength(values.length, 1, this.NAME);
    const counter = new Counter<Complex>();
    values
      .map(this.lookup)
      .map(Mappers.flattenGridsToArrays) // 1, 2, [null, 1, "0", "Text.", null, #REF!]
      .map(Mappers.ensureAllAreArrays) // [1], [2], [null, 1, "0", "Text.", null, #REF!]
      .reduce(Reducers.join) // [1, 2, null, 1, "0", "Text.", null, #REF!]
      .filter(isNotNull) // [1, 2, 1, "0", "Text.", #REF!]
      .filter(Predicates.isNonError) // [1, 2, 1, "0", "Text."]
      .map((value) => counter.count(value));
    return counter.getCount();
  }
}
