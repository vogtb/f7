import { isNotNull } from "../../utils/Other";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { Mappers } from "../../utils/Mappers";
import { Reducers } from "../../utils/Reducers";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class PRODUCT extends AbstractFormula {
  static SELF: PRODUCT = new PRODUCT();
  NAME = FormulaName.SUM;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkAtLeastLength(values.length, 1, this.NAME);
    return values
      .map(this.lookup)
      .map(Mappers.toFlatStream)
      .reduce(Reducers.join)
      .filter(isNotNull)
      .map(Converters.toNumber)
      .reduce(Reducers.product, 1);
  }
}
