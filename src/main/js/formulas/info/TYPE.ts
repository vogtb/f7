import { isNull } from "../../utils/Other";
import { F7Exception } from "../../errors/F7Exception";
import { Grid } from "../../models/common/Grid";
import { SheetColumnRowKey } from "../../models/common/SheetColumnRowKey";
import { Complex } from "../../models/common/Types";
import { Converters } from "../../utils/Converters";
import { AbstractFormula } from "../AbstractFormula";
import { FormulaName } from "../FormulaName";

export class TYPE extends AbstractFormula {
  static SELF: TYPE = new TYPE();
  NAME = FormulaName.TYPE;

  internal(origin: SheetColumnRowKey, ...values: Array<Complex>) {
    AbstractFormula.checkLength(values.length, 1, this.NAME);
    const value = this.collateralLookup(origin, values[0]);
    if (isNull(value) || typeof value === "number") {
      return 1;
    }
    if (typeof value === "string") {
      return 2;
    }
    if (typeof value === "boolean") {
      return 4;
    }
    if (value instanceof F7Exception) {
      return 16;
    }
    if (value instanceof Grid) {
      const grid = Converters.castAsGrid(value);
      if (grid.isSingle()) {
        return TYPE.SELF.run(origin, grid.get(0, 0));
      }
      return 64;
    }
    return 128;
  }
}
