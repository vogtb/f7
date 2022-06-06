import { A1Key } from "../models/common/A1Key";
import { ColumnRowKey } from "../models/common/ColumnRowKey";
import { Parsers } from "../utils/Parsers";
import { CellObject } from "./CellObject";

/**
 * A sheet in a spreadsheet, or spreadsheet-like object.
 */
export interface ISheet {
  /**
   * Unique identifier for the sheet. 7 characters, first three are 'she'.
   */
  id: string;
  /**
   * Unique name of the sheet.
   */
  name: string;

  /** Sheet Range */
  "!ref"?: string;

  /**
   * Indexing with a cell address string maps to a cell object
   * Special keys start with '!'
   */
  [cell: string]: CellObject | string;
}

export class Sheet implements ISheet {
  static properties = new Set(["id", "name", "!ref"]);

  id: string;
  name: string;

  [cell: string]: CellObject | any;

  "!ref"?: string;

  constructor(sheet: ISheet) {
    const ref = Parsers.parseReferencePair(sheet["!ref"] || "A1:A1");
    let highestColumn = ref.endColumnIndex;
    let highestRow = ref.endRowIndex;
    Object.keys(sheet).map((key) => {
      if (!Sheet.properties.has(key)) {
        const a1 = A1Key.fromString(key);
        highestColumn = Math.max(highestColumn, a1.getColumnIndex());
        highestRow = Math.max(highestRow, a1.row);
      }
      this[key] = sheet[key];
    });
    const start = new ColumnRowKey(ref.startColumnIndex, ref.startRowIndex).toA1();
    const end = new ColumnRowKey(highestColumn, highestRow).toA1();
    this["!ref"] = sheet["!ref"] || `${start}:${end}`;
  }

  setCell(a1: string, cell: CellObject) {
    const ref = Parsers.parseReferencePair(this["!ref"] || "A1:A1");
    let highestColumn = ref.endColumnIndex;
    let highestRow = ref.endRowIndex;
    const key = A1Key.fromString(a1);
    highestColumn = Math.max(highestColumn, key.getColumnIndex());
    highestRow = Math.max(highestRow, key.row);
    const start = new ColumnRowKey(ref.startColumnIndex, ref.startRowIndex).toA1();
    const end = new ColumnRowKey(highestColumn, highestRow).toA1();
    this[a1] = cell;
    this["!ref"] = `${start}:${end}`;
  }

  getCell(a1: string): CellObject | null {
    const value = this[a1];
    return value ? value : null;
  }
}
