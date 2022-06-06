import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class ROUND extends AbstractFormula {
  static SELF: ROUND = new ROUND();
  NAME = FormulaName.ROUND;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLengthBetween(values.length, 1, 2, this.NAME);
    const value = Converters.first(this.collateralLookup(origin, values[0]));
    let placesRaw;
    if (values.length === 1) {
      placesRaw = 0.0;
    } else {
      placesRaw = Converters.toNumber(Converters.first(this.collateralLookup(origin, values[1])));
    }
    const n = Converters.toNumber(value);
    const places = placesRaw > 0 ? Math.floor(placesRaw) : Math.ceil(placesRaw);
    if (n < 0) {
      if (places < 0) {
        return Number(Math.round(parseFloat(n + "e-" + places * -1)) + "e" + places * -1);
      }
      return Number(Math.round(parseFloat(n + "e" + places)) + "e-" + places);
    }
    if (places < 0) {
      return Number(Math.round(parseFloat(n + "e-" + places * -1)) + "e" + places * -1);
    }
    return Number(Math.round(parseFloat(n + "e" + places)) + "e-" + places);
  }
}
