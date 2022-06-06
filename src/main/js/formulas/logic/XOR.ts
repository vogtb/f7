import { isNotNull } from "../../utils/Other";
import { ValueException } from "../../errors/ValueException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { Mappers } from "../../utils/Mappers";
import { Reducers } from "../../utils/Reducers";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class XOR extends AbstractFormula {
  static SELF: XOR = new XOR();
  NAME = FormulaName.OR;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkAtLeastLength(values.length, 1, this.NAME);
    const filteredValues = values
      .map(this.lookup)
      .map(Mappers.toFlatStream)
      .reduce(Reducers.join)
      .filter(isNotNull);
    if (filteredValues.length === 0) {
      return new ValueException("OR has no valid input data.");
    }
    return filteredValues.map(Converters.toBoolean).reduce(Reducers.logicalXOr, false);
  }
}
