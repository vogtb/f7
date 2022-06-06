import { INamedRange, NamedRange } from "./NamedRange";
import { ISheet, Sheet } from "./Sheet";

export interface ISpreadsheet {
  /**
   * The named ranges defined in a spreadsheet.
   */
  names: { [name: string]: INamedRange };

  /**
   * The sheets that are part of a spreadsheet.
   */
  sheets: { [name: string]: ISheet };
}

export class Spreadsheet implements ISpreadsheet {
  names: { [name: string]: NamedRange };
  sheets: { [name: string]: Sheet };

  /**
   * Standard constructor.
   */
  constructor(names: { [name: string]: NamedRange }, sheets: { [name: string]: Sheet }) {
    this.names = names;
    this.sheets = sheets;
  }

  /**
   * Create an empty spreadsheet based on title and spreadsheet id.
   */
  static empty(): Spreadsheet {
    return new Spreadsheet({}, {});
  }

  /**
   * Get a Sheet by name by looking through the sheets.
   * @param sheetName to find.
   */
  getSheetByName(sheetName: string) {
    return this.sheets[sheetName];
  }
}
