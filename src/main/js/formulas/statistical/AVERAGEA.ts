import { isNotNull } from "../../utils/Other";
import { DivException } from "../../errors/DivException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { Counter } from "../../utils/Counter";
import { Mappers } from "../../utils/Mappers";
import { Reducers } from "../../utils/Reducers";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class AVERAGEA extends AbstractFormula {
  static SELF: AVERAGEA = new AVERAGEA();
  NAME = FormulaName.AVERAGEA;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkAtLeastLength(values.length, 1, this.NAME);
    const counter = new Counter<number>();
    const sum = values
      .map(this.lookup)
      .map(Mappers.flattenGridsToArrays) // 1, "2", [null, 1, "99", "Text.", null, #REF!, true]
      .map(Mappers.coerceNonArrayValuesToNumberOrThrow) // 1, 2, [null, 1, "99", "Text.", null, #REF!, true]
      .map(Mappers.ensureAllAreArrays) // [1], [2], [null, 1, "99", "Text.", null, #REF!, true]
      .reduce(Reducers.join) // [1, 2, null, 1, "99", "Text.", null, #REF!, true]
      .filter(isNotNull) // [1, 2, 1, "99", "Text.", #REF!, true]
      .map(Converters.textToZero) // [1, 2, 1, 0, 0, true]
      .map(Converters.toNumber) // [1, 2, 1, 0, 0, 1]
      .map((value) => counter.count(value))
      .reduce(Reducers.sum, 0);
    if (counter.getCount() === 0) {
      return new DivException(
        "Formula AVERAGEA caused an error when attempting to divide by zero."
      );
    }
    return sum / counter.getCount();
  }
}
