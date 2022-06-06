import { NumException } from "../../errors/NumException";
import { ValueException } from "../../errors/ValueException";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class BIN2HEX extends AbstractFormula {
  static SELF: BIN2HEX = new BIN2HEX();
  NAME = FormulaName.BIN2HEX;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLengthBetween(values.length, 1, 2, this.NAME);
    const signedBinaryNumber = Converters.first(this.collateralLookup(origin, values[0]));
    let significantDigits = 10;
    if (values.length === 2) {
      significantDigits = Converters.toNumber(
        Converters.first(this.collateralLookup(origin, values[1]))
      );
    }

    if (typeof signedBinaryNumber === "boolean") {
      throw new ValueException(
        `Function BIN2HEX parameter 1 expects text values. But '${signedBinaryNumber}' is a boolean and cannot be coerced to a text.`
      );
    }
    const n = Converters.toText(signedBinaryNumber);
    if (!/^[01]{1,10}$/.test(n)) {
      throw new NumException(`Input for BIN2HEX ('${n}') is not a valid binary representation.`);
    }

    if (n.length === 10 && n.substring(0, 1) === "1") {
      return (1099511627264 + parseInt(n.substring(1), 2)).toString(16).toUpperCase();
    }

    if (significantDigits < 1 || significantDigits > 10) {
      throw new NumException(
        `Function BIN2HEX parameter 2 value is ${significantDigits}. Valid values are between 1 and 10 inclusive.`
      );
    }
    significantDigits = Math.floor(significantDigits);
    // Convert decimal number to hexadecimal
    const result = parseInt(n.toString(), 2).toString(16).toUpperCase();
    if (significantDigits === 10) {
      return result;
    }
    let str = "";
    for (let i = 0; i < significantDigits - result.length; i++) {
      str += "0";
    }
    return str + result;
  }
}
