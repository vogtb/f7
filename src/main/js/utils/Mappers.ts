import { F7Exception } from "../errors/F7Exception";
import { Grid } from "../models/common/Grid";
import { Complex } from "../models/common/Types";
import { Converters } from "./Converters";
import { Predicates } from "./Predicates";

export class Mappers {
  /**
   * Flatten possibly nested lists into a flat list of objects that are either not a list, or a list of size 0.
   *
   * @param value - to return as a stream.
   * @return - stream of objects that are Complex.
   */
  static toFlatStream(value: Complex): Array<Complex> {
    if (value instanceof Grid) {
      const grid: Grid<Complex> = Converters.castAsGrid(value);
      const values: Array<Complex> = [];
      for (let row = 0; row < grid.getRows(); row++) {
        for (let column = 0; column < grid.getColumns(); column++) {
          values.push(grid.get(column, row));
        }
      }
      return values;
    }
    return [value];
  }

  /**
   * Flatten possibly nested lists into a flat list of objects that are either not a list, or a list of size 0.
   *
   * @param value - to return as a stream.
   * @return - stream of objects that are Complex.
   */
  static flattenGridsToArrays(value: Complex): Array<Complex> | Complex {
    if (value instanceof Grid) {
      const grid: Grid<Complex> = Converters.castAsGrid(value);
      const values: Array<Complex> = [];
      for (let row = 0; row < grid.getRows(); row++) {
        for (let column = 0; column < grid.getColumns(); column++) {
          values.push(grid.get(column, row));
        }
      }
      return values;
    }
    return value;
  }

  /**
   * If the value is an Array, filter to numeric-coercable values, else return value.
   * @param value - value to possibly filter.
   */
  static filterArrayValuesByIsCoercableToNumeric(
    value: Array<Complex> | Complex
  ): Array<Complex> | Complex {
    if (Array.isArray(value)) {
      return (value as Array<Complex>).filter(Predicates.isCoercableToNumeric);
    }
    return value;
  }

  /**
   * If the value is Array, check for errors and throw them where found. Else return value.
   * @param value
   */
  static checkArrayValuesForErrors(value: Array<Complex> | Complex): Array<Complex> | Complex {
    if (Array.isArray(value)) {
      return (value as Array<Complex>).map(Mappers.throwErrors);
    }
    return value;
  }

  /**
   * If the values is an Array, return. If the value sis not an Array, attempt to coerce to number.
   * @param value - to use.
   */
  static coerceNonArrayValuesToNumberOrThrow(
    value: Array<Complex> | Complex
  ): Array<Complex> | Complex {
    if (Array.isArray(value)) {
      return value as Array<Complex>;
    }
    return Converters.toNumber(value);
  }

  /**
   * When we have an array of possible nested arrays (because we treat grids differently), we need to at ensure all
   * values are arrays before reduce them to a single array.
   * @param value - value to ensure.
   */
  static ensureAllAreArrays(value: Array<Complex> | Complex): Array<Complex> {
    if (Array.isArray(value)) {
      return value as Array<Complex>;
    }
    return [value as Complex];
  }

  /**
   * If the value is an error, throw.
   * @param value - value to check.
   */
  static throwErrors(value: Complex): Complex {
    if (value instanceof F7Exception) {
      throw Converters.castAsF7Exception(value);
    }
    return value;
  }
}
