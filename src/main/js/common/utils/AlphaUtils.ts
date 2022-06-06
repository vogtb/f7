export class AlphaUtils {
  /**
   * Convert a column in A1 notation (where column is A, B, C... ZA, ZB... etc.) to integer (A=1, B=2)
   *
   * @param column to convert to zero-indexed int.
   * @return int greater than or equal to 1.
   */
  static columnToInt(column: string): number {
    const base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    let i,
      j,
      result = 0;
    for (i = 0, j = column.length - 1; i < column.length; i += 1, j -= 1) {
      result += Math.pow(base.length, j) * (base.indexOf(column[i]) + 1);
    }
    if (result) {
      --result;
    }
    return result;
  }

  /**
   * Convert a row string to row index, which is zero indexed. This is simple, but I don't want to repeat it everywhere.
   * @param row - string integer greater than or equal to 1.
   */
  static rowToInt(row: string): number {
    return parseInt(row) - 1;
  }

  /**
   * Take a one-indexed number, and return the letter. Eg: 1=A, 2=B, etc.
   * @param num
   */
  static oneIndexedNumberToLetter(num: number) {
    let ret, a, b;
    for (ret = "", a = 1, b = 26; (num -= a) >= 0; a = b, b *= 26) {
      ret = String.fromCharCode(parseInt(((num % b) / a).toString()) + 65) + ret;
    }
    return ret;
  }
}
