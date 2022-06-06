import { ParseException } from "../../errors/ParseException";
import { AlphaUtils } from "../../utils/AlphaUtils";
import { ColumnRowKey } from "./ColumnRowKey";

/**
 * A1 key is mostly a helper-class to name cells when testing.
 */
export class A1Key {
  private static CRUDE_A1_PATTERN = /^(\D+)(\d+)$/;
  readonly column: string;
  readonly row: number;

  constructor(column: string, row: number) {
    this.column = column;
    this.row = row;
  }

  /**
   * Create key from A1 string.
   *
   * @param key - key
   * @return A1Key
   */
  static fromString(key: string): A1Key {
    const matches = key.match(A1Key.CRUDE_A1_PATTERN);
    if (matches) {
      return new A1Key(matches[1], AlphaUtils.rowToInt(matches[2]));
    }
    throw new ParseException("Could not parse cell key.");
  }

  toA1String() {
    return `${this.column}${this.row.toString()}`;
  }

  /**
   * Zero-indexed column.
   */
  getColumnIndex() {
    return AlphaUtils.columnToInt(this.column);
  }

  /**
   * Convert to ColumnRowKey, which is what we use almost everywhere.
   */
  toColumnRowKey(): ColumnRowKey {
    return new ColumnRowKey(this.getColumnIndex(), this.row);
  }
}
