package io.protobase.f7.utils;

public class AlphaUtils {
  /**
   * Convert a column in A1 notation (where column is A, B, C... ZA, ZB... etc.) to integer (A=1, B=2)
   *
   * @param column to convert to zero-indexed int.
   * @return int greater than or equal to 1.
   */
  public static int columnToInt(String column) {
    int result = -1;
    for (char character : column.toLowerCase().toCharArray()) {
      int pos = character - 'a';
      if (result != -1) {
        result = result + 26 + pos;
      } else {
        result = pos;
      }
    }
    return result;
  }

  public static int rowToInt(String row) {
    return Integer.parseInt(row) - 1;
  }
}
