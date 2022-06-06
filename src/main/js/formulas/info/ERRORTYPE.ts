import { F7Exception } from "../../errors/F7Exception";
import { getExceptionCode } from "../../errors/F7ExceptionName";
import { NAException } from "../../errors/NAException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class ERRORTYPE extends AbstractFormula {
  static SELF: ERRORTYPE = new ERRORTYPE();
  NAME = FormulaName.ERRORTYPE;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    try {
      const first = Converters.first(this.collateralLookup(origin, values[0]));
      if (first instanceof F7Exception) {
        return getExceptionCode(Converters.castAsF7Exception(first).name);
      }
      return new NAException("Formula ERRORTYPE parameter 1 is not an error.");
    } catch (error) {
      return getExceptionCode(Converters.castAsF7Exception(error as F7Exception).name);
    }
  }
}
