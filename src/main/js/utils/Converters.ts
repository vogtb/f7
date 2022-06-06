import { F7Exception } from "../errors/F7Exception";
import { ValueException } from "../errors/ValueException";
import { Grid } from "../models/common/Grid";
import { Complex } from "../models/common/Types";
import { Numbers } from "./Numbers";
import { isNull } from "./Other";

export class Converters {
  /**
   * Convert a value to a number.
   *
   * @param value - to convert to number.
   * @return number for sure, or exception thrown.
   */
  static toNumber(value: any): number {
    if (typeof value === "number") {
      return Converters.toPositiveZero(Converters.castAsNumber(value));
    }
    if (typeof value === "string") {
      const converted = Numbers.toNumberOrNull(Converters.castAsString(value));
      if (isNull(converted)) {
        throw new ValueException("Cannot coerce to number");
      }
      return converted;
    }
    if (typeof value === "boolean") {
      return Converters.castAsBoolean(value) ? 1 : 0;
    }
    if (value instanceof F7Exception) {
      throw Converters.castAsF7Exception(value);
    }
    if (isNull(value)) {
      return 0;
    }
    throw new ValueException("Cannot coerce to number");
  }

  /**
   * Convert a value to a string.
   *
   * @param value - to convert to text.
   * @return string for sure, or exception thrown.
   */
  static toText(value: any): string {
    if (typeof value === "number") {
      return Converters.castAsNumber(value).toString();
    }
    if (typeof value === "string") {
      return Converters.castAsString(value);
    }
    if (typeof value === "boolean") {
      return Converters.castAsBoolean(value) ? "TRUE" : "FALSE";
    }
    if (value instanceof F7Exception) {
      throw Converters.castAsF7Exception(value);
    }
    if (isNull(value)) {
      return "";
    }
    throw new ValueException("Cannot coerce to text");
  }

  /**
   * Convert a value to a boolean.
   *
   * @param value - to convert to boolean.
   * @return boolean for sure, or exception thrown.
   */
  static toBoolean(value: any): boolean {
    if (typeof value === "number") {
      return Converters.castAsNumber(value) !== 0;
    }
    if (typeof value === "string") {
      const rawValue = Converters.castAsString(value).toLowerCase();
      if (rawValue === "true") {
        return true;
      }
      if (rawValue === "false" || rawValue === "") {
        return false;
      }
      throw new ValueException("Cannot coerce to boolean");
    }
    if (typeof value === "boolean") {
      return Converters.castAsBoolean(value);
    }
    if (value instanceof F7Exception) {
      throw Converters.castAsF7Exception(value);
    }
    if (isNull(value)) {
      return false;
    }
    throw new ValueException("Cannot coerce to boolean");
  }

  /**
   * Convert string to zero. Return value if not string.
   * @param value to possibly convert, or pass through.
   */
  static textToZero(value: Complex): Complex {
    if (value instanceof Grid) {
      const grid = Converters.castAsGrid(value);
      if (grid.getColumns() == 1 && grid.getRows() == 1) {
        return Converters.textToZero(grid.get(0, 0));
      } else {
        throw new ValueException("Grid value could not be found.");
      }
    }
    if (typeof value === "string") {
      return 0;
    }
    return value;
  }

  /**
   * Pull first value.
   * @param value - one or more values.
   */
  static first(value: any) {
    if (value instanceof Grid) {
      return Converters.castAsGrid(value).get(0, 0);
    }
    return value;
  }

  /**
   * Convert negative zero to positive zero, if the value is zero. If not, just pass through.
   * @param value - to convert maybe.
   */
  static toPositiveZero(value: any) {
    if (typeof value === "number" && Numbers.isZero(value)) {
      return 0;
    }
    return value;
  }

  /**
   * Clean helper to cast a value as a number.
   * @param value to cast.
   */
  static castAsNumber(value: any) {
    return <number>value;
  }

  /**
   * Clean helper to cast a value as an F7Exception.
   * @param value to cast.
   */
  static castAsF7Exception(value: any) {
    return <F7Exception>value;
  }

  /**
   * Clean helper to cast a value as a Grid.
   * @param value to cast.
   */
  static castAsGrid(value: Complex) {
    return <Grid<Complex>>value;
  }

  /**
   * Clean helper to cast a value as a string.
   * @param value to cast.
   */
  static castAsString(value: any) {
    return <string>value;
  }

  /**
   * Clean helper to cast a value as a boolean.
   * @param value to cast.
   */
  static castAsBoolean(value: any) {
    return <boolean>value;
  }
}
