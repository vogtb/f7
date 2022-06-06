package io.protobase.f7.models;

import com.google.common.base.MoreObjects;
import io.protobase.f7.errors.ParseException;
import io.protobase.f7.utils.AlphaUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A1 key is mostly a helper-class to name cells when testing.
 */
public class A1Key extends BaseObject {
  private static final Pattern CRUDE_A1_PATTERN = Pattern.compile("^(\\D+)(\\d+)$");
  private String column;
  private Integer row;

  public A1Key(String column, Integer row) {
    this.column = column;
    this.row = row;
  }

  /**
   * Create key from A1 string.
   *
   * @param a1Key - key
   * @return A1Key
   */
  public static A1Key fromString(String a1Key) {
    Matcher matcher = CRUDE_A1_PATTERN.matcher(a1Key);
    if (matcher.matches()) {
      return new A1Key(matcher.group(1), Integer.parseInt(matcher.group(2)));
    }
    throw new ParseException("Could not parse cell key.");
  }

  /**
   * Get the string representation of column.
   *
   * @return string.
   */
  public String getColumn() {
    return column;
  }

  /**
   * Get the 1-indexed row.
   *
   * @return int greater than or equal to 1.
   */
  public Integer getRow() {
    return row;
  }

  /**
   * Get the 0-indexed column.
   *
   * @return int greater than or equal to 0.
   */
  public Integer getColumnIndex() {
    return AlphaUtils.columnToInt(column);
  }

  /**
   * Get the 0-indexed row.
   *
   * @return int greater than or equal to 0.
   */
  public Integer getRowIndex() {
    return row - 1;
  }

  /**
   * Convert to zero-indexed ColumnRowKey.
   *
   * @return key
   */
  public ColumnRowKey toColumnRowKey() {
    return new ColumnRowKey(getColumnIndex(), getRowIndex());
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("column", column)
        .add("row", row)
        .toString();
  }

  @Override
  public Object[] significantAttributes() {
    return new Object[]{
        column,
        row
    };
  }
}
