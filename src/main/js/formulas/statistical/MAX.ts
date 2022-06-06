import { isNotNull } from "../../utils/Other";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { Mappers } from "../../utils/Mappers";
import { Reducers } from "../../utils/Reducers";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class MAX extends AbstractFormula {
  static SELF: MAX = new MAX();
  NAME = FormulaName.MAX;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkAtLeastLength(values.length, 1, this.NAME);
    const constructedValues = values
      .map(this.lookup)
      .map(Mappers.flattenGridsToArrays) // 1, 2, [1, "0", "Nope.", null]
      .map(Mappers.filterArrayValuesByIsCoercableToNumeric) // Eg: 1, 2, [1, "0"]
      .map(Mappers.ensureAllAreArrays) // [1], [2], [1, "0"]
      .reduce(Reducers.join) // [1, 2, 1, "0"]
      .filter(isNotNull) // [1, 2, 1, "0"]
      .map(Converters.toNumber); // [1, 2, 1, 0]
    if (constructedValues.length === 0) {
      return 0;
    }
    return constructedValues.reduce(Reducers.max);
  }
}
