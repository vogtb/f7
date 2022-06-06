import { AlphaUtils } from "../../utils/AlphaUtils";
import { Compare } from "../../utils/Compare";
import { A1Key } from "./A1Key";
import { Hashable } from "../../common/standard/Hashable";

/**
 * Represents a single location in a 2D grid - usually {@link Grid}.
 */
export class ColumnRowKey implements Hashable {
  readonly column: number;
  readonly row: number;

  constructor(column: number, row: number) {
    this.column = column;
    this.row = row;
  }

  get hashKey(): string {
    return JSON.stringify([this.column, this.row]);
  }

  static fromA1(a1: string) {
    const key = A1Key.fromString(a1);
    return new ColumnRowKey(key.getColumnIndex(), key.row);
  }

  toA1(): string {
    return `${AlphaUtils.oneIndexedNumberToLetter(this.column + 1)}${this.row + 1}`;
  }

  compareTo(other: ColumnRowKey): number {
    if (this.row === other.row && this.column === other.column) {
      return 0;
    }
    if (this.column === other.column) {
      return Compare.numberComparison(this.row, other.row);
    }
    return Compare.numberComparison(this.column, other.column);
  }

  equals(other: unknown): boolean {
    return other instanceof ColumnRowKey && this.hashKey === other.hashKey;
  }
}
