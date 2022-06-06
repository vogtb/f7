import { ExcelDataType, excelDataTypeFromActualType } from "./ExcelDataType";

/** Worksheet Cell Object */
export interface CellObject {
  /** The raw value of the cell.  Can be omitted if a formula is specified */
  v?: string | number | boolean;

  /** Formatted text (if applicable) */
  w?: string;

  /**
   * The Excel Data Type of the cell.
   * b Boolean, n Number, e Error, s String, d Date, z Empty
   */
  t: ExcelDataType;

  /** Cell formula (if applicable) */
  f?: string;

  /** Range of enclosing array if formula is array formula (if applicable) */
  F?: string;
}

export function cellObjectFromProjection(v: string | number | boolean): CellObject {
  return {
    v,
    t: excelDataTypeFromActualType(v),
  };
}
