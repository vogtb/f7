import { ColumnRowKey } from "./ColumnRowKey";

/**
 * Represents a single location in a 3D grid: {column=x, row=y, grid=z}.
 */
export class SheetColumnRowKey extends ColumnRowKey {
  readonly sheet: string;

  constructor(sheet: string, column: number, row: number) {
    super(column, row);
    this.sheet = sheet;
  }

  get hashKey(): string {
    return JSON.stringify([this.sheet, this.column, this.row]);
  }

  static from(grid: string, key: ColumnRowKey): SheetColumnRowKey {
    return new SheetColumnRowKey(grid, key.column, key.row);
  }

  toColumnRowKey(): ColumnRowKey {
    return new ColumnRowKey(this.column, this.row);
  }
}
