import { NumException } from "../../errors/NumException";
import { ValueException } from "../../errors/ValueException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class BIN2DEC extends AbstractFormula {
  static SELF: BIN2DEC = new BIN2DEC();
  NAME = FormulaName.BIN2DEC;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const first = Converters.first(this.collateralLookup(origin, values[0]));
    if (typeof first === "boolean") {
      throw new ValueException(
        `Function BIN2DEC parameter 1 expects text values. But '${first}' is a boolean and cannot be coerced to a text.`
      );
    }
    const n = Converters.toText(first);
    if (!/^[01]{1,10}$/.test(n)) {
      throw new NumException(`Input for BIN2DEC ('${n}') is not a valid binary representation.`);
    }
    if (n.length === 10 && n.substring(0, 1) === "1") {
      return parseInt(n.substring(1), 2) - 512;
    }
    return parseInt(n, 2);
  }
}
