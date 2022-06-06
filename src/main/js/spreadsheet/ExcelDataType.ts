import { Computed } from "../models/common/Types";
import { AbstractStandardError } from "../common/errors/StandardError";

// b Boolean, n Number, e Error, s String, d Date, z Empty
export type ExcelDataType = "b" | "n" | "e" | "s" | "d" | "z";

export function excelDataTypeFromActualType(value: Computed) {
  if (typeof value === "boolean") {
    return "b";
  } else if (typeof value === "number") {
    return "n";
  } else if (typeof value === "string") {
    return "s";
  } else if (value instanceof AbstractStandardError) {
    return "e";
  }
  return "z";
}
