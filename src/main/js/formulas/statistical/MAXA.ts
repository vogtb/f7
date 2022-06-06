import { isNotNull } from "../../utils/Other";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { Mappers } from "../../utils/Mappers";
import { Reducers } from "../../utils/Reducers";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class MAXA extends AbstractFormula {
  static SELF: MAXA = new MAXA();
  NAME = FormulaName.MAXA;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkAtLeastLength(values.length, 1, this.NAME);
    const constructedValues = values
      .map(this.lookup)
      .map(Mappers.flattenGridsToArrays) // 1, "2", [null, 1, "99", "Text.", null, #REF!, true]
      .map(Mappers.coerceNonArrayValuesToNumberOrThrow) // 1, 2, [null, 1, "99", "Text.", null, #REF!, true]
      .map(Mappers.ensureAllAreArrays) // [1], [2], [null, 1, "99", "Text.", null, #REF!, true]
      .reduce(Reducers.join) // [1, 2, null, 1, "99", "Text.", null, #REF!, true]
      .filter(isNotNull) // [1, 2, 1, "99", "Text.", #REF!, true]
      .map(Converters.textToZero) // [1, 2, 1, 0, 0, true]
      .map(Converters.toNumber); // [1, 2, 1, 0, 0, 1]
    if (constructedValues.length === 0) {
      return 0;
    }
    return constructedValues.reduce(Reducers.max);
  }
}
