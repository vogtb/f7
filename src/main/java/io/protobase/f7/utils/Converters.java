package io.protobase.f7.utils;

import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.models.Grid;

import java.text.DecimalFormat;
import java.util.Objects;

public class Converters {

  /**
   * Compare two objects using their type precedence first, and then defaulting to their default class compare methods
   * if they are of the same type. If one is null, use a default depending on type.
   *
   * @param first  - object to compare. Should be Double, Boolean, String, or null.
   * @param second - second object to compare.  Should be Double, Boolean, String, or null.
   * @return ComparisonResult indicating less-than, greater-than, equal-to.
   */
  public static ComparisonResult compare(Object first, Object second) {
    Integer firstTypePrecedence = Converters.getTypePrecedence(first);
    Integer secondTypePrecedence = Converters.getTypePrecedence(second);
    if (firstTypePrecedence.equals(secondTypePrecedence)) {
      if (Objects.isNull(first)) {
        return ComparisonResult.EQUAL;
      }
      if (first instanceof Double) {
        Double firstAsDouble = Converters.castAsDouble(first);
        Double secondAsDouble = Converters.castAsDouble(second);
        return ComparisonResult.fromComparison(firstAsDouble.compareTo(secondAsDouble));
      }
      if (first instanceof Boolean) {
        Boolean firstAsBoolean = Converters.castAsBoolean(first);
        Boolean secondAsBoolean = Converters.castAsBoolean(second);
        return ComparisonResult.fromComparison(firstAsBoolean.compareTo(secondAsBoolean));
      }
      if (first instanceof String) {
        String firstAsString = Converters.castAsString(first).toLowerCase();
        String secondAsString = Converters.castAsString(second).toLowerCase();
        return ComparisonResult.fromComparison(firstAsString.compareTo(secondAsString));
      }
    }
    if (Objects.isNull(first)) {
      if (second instanceof Double) {
        Double firstAsDouble = 0.0;
        Double secondAsDouble = Converters.toPositiveZero(Converters.castAsDouble(second));
        return ComparisonResult.fromComparison(firstAsDouble.compareTo(secondAsDouble));
      }
      if (second instanceof Boolean) {
        Boolean firstAsBoolean = false;
        Boolean secondAsBoolean = Converters.castAsBoolean(second);
        return ComparisonResult.fromComparison(firstAsBoolean.compareTo(secondAsBoolean));
      }
      if (second instanceof String) {
        String firstAsString = "";
        String secondAsString = Converters.castAsString(second).toLowerCase();
        return ComparisonResult.fromComparison(firstAsString.compareTo(secondAsString));
      }
    }
    if (Objects.isNull(second)) {
      if (first instanceof Double) {
        Double firstAsDouble = Converters.toPositiveZero(Converters.castAsDouble(first));
        Double secondAsDouble = 0.0;
        return ComparisonResult.fromComparison(firstAsDouble.compareTo(secondAsDouble));
      }
      if (first instanceof Boolean) {
        Boolean firstAsBoolean = Converters.castAsBoolean(first);
        Boolean secondAsBoolean = false;
        return ComparisonResult.fromComparison(firstAsBoolean.compareTo(secondAsBoolean));
      }
      if (first instanceof String) {
        String firstAsString = Converters.castAsString(first).toLowerCase();
        String secondAsString = "";
        return ComparisonResult.fromComparison(firstAsString.compareTo(secondAsString));
      }
    }
    return ComparisonResult.fromComparison(firstTypePrecedence.compareTo(secondTypePrecedence));
  }

  protected static Object textToZero(Object value) {
    if (value instanceof Grid) {
      Grid grid = Converters.castAsDataGrid(value);
      if (grid.getColumnSize() == 1 && grid.getRowSize() == 1) {
        return textToZero(grid.get(0, 0));
      } else {
        throw new ValueException("Grid value could not be found.");
      }
    }
    if (value instanceof String) {
      return 0.0;
    }
    return value;
  }

  /**
   * Convert double to +0.0, only if it is -0.0.
   *
   * @param value to ensure is positive zero.
   * @return value or +0.0 if value is -0.0.
   */
  public static Double toPositiveZero(double value) {
    if (value == -0.0) {
      return 0.0;
    }
    return value;
  }

  /**
   * Convert a Double, String or Boolean to a Double. If F7Exception is the value, then throw it. For all other classes,
   * a {@link ValueException} will be thrown. If the value is null, 0.0 is returned.
   *
   * @param value - to convert to Double.
   * @return Double for sure, or exception.
   */
  public static Double toDouble(Object value) {
    if (value instanceof Double) {
      return Converters.toPositiveZero(Converters.castAsDouble(value));
    }
    if (value instanceof String) {
      try {
        return Double.parseDouble(Converters.castAsString(value));
      } catch (NumberFormatException e) {
        throw new ValueException("Cannot coerce to number");
      }
    }
    if (value instanceof Boolean) {
      return Converters.castAsBoolean(value) ? 1.0 : 0.0;
    }
    if (value instanceof F7Exception) {
      throw Converters.castAsF7Error(value);
    }
    if (value instanceof Grid) {
      Grid grid = Converters.castAsDataGrid(value);
      if (grid.getColumnSize() == 1 && grid.getRowSize() == 1) {
        return toDouble(grid.get(0, 0));
      } else {
        return 0.0;
      }
    }
    if (Objects.isNull(value)) {
      return 0.0;
    }
    throw new ValueException("Cannot coerce to number");
  }

  /**
   * Convert a value to a string.
   *
   * @param value - to convert to text.
   * @return String for sure.
   */
  public static String toText(Object value) {
    if (value instanceof Boolean) {
      return Converters.castAsBoolean(value) ? "TRUE" : "FALSE";
    }
    if (value instanceof String) {
      return value.toString();
    }
    if (value instanceof Double) {
      Double number = Converters.castAsDouble(value);
      double remainder = number % 1;
      if (Converters.toPositiveZero(remainder).equals(0.0)) {
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(0);
        format.setGroupingUsed(false);
        return format.format(number);
      }
      return value.toString();
    }
    if (value instanceof F7Exception) {
      return Converters.castAsF7Error(value).toString();
    }
    if (value instanceof Grid) {
      Grid grid = Converters.castAsDataGrid(value);
      if (grid.getColumnSize() == 1 && grid.getRowSize() == 1) {
        return toText(grid.get(0, 0));
      } else {
        return "";
      }
    }
    if (Objects.isNull(value)) {
      return "";
    }
    throw new ValueException("Cannot coerce to text");
  }

  /**
   * Convert a value to a boolean.
   *
   * @param value - to convert to a boolean.
   * @return Boolean for sure, or throw ValueException.
   */
  public static Boolean toBoolean(Object value) {
    if (value instanceof Boolean) {
      return Converters.castAsBoolean(value);
    }
    if (value instanceof String) {
      String rawStringValue = ((String) value);
      if (rawStringValue.toLowerCase().equals("true")) {
        return true;
      }
      if (rawStringValue.toLowerCase().equals("false")) {
        return false;
      }
      if (rawStringValue.equals("")) {
        return false;
      }
      throw new ValueException("Cannot coerce to boolean");
    }
    if (value instanceof F7Exception) {
      throw Converters.castAsF7Error(value);
    }
    if (value instanceof Grid) {
      Grid grid = Converters.castAsDataGrid(value);
      if (grid.getColumnSize() == 1 && grid.getRowSize() == 1) {
        return toBoolean(grid.get(0, 0));
      } else {
        return false;
      }
    }
    if (value instanceof Double) {
      return !(value).equals(0.0);
    }
    throw new ValueException("Cannot coerce to boolean");
  }

  /**
   * Get the first value, if it's a grid, or just pass-through.
   *
   * @param value - to get first of.
   * @return - single value.
   */
  public static Object first(Object value) {
    if (value instanceof Grid) {
      return ((Grid) value).get(0, 0);
    }
    return value;
  }

  /**
   * Cast value to Boolean.
   *
   * @param value - to cast.
   * @return Boolean.
   */
  public static Boolean castAsBoolean(Object value) {
    return (Boolean) value;
  }

  /**
   * Cast value to Double.
   *
   * @param value - to cast.
   * @return Double.
   */
  public static Double castAsDouble(Object value) {
    return (Double) value;
  }

  /**
   * Cast value to String.
   *
   * @param value - to cast.
   * @return String.
   */
  public static String castAsString(Object value) {
    return (String) value;
  }

  /**
   * Cast value to F7Exception.
   *
   * @param value - to cast.
   * @return F7Exception.
   */
  public static F7Exception castAsF7Error(Object value) {
    return (F7Exception) value;
  }

  /**
   * Cast value to Grid.
   *
   * @param value - to cast.
   * @return Grid.
   */
  public static Grid<Object> castAsDataGrid(Object value) {
    return (Grid<Object>) value;
  }

  /**
   * Strings, Numbers, and Booleans have type precedence that is used when comparing across types.
   *
   * @param value to get precedence of.
   * @return {@code 1} if Number,{@code 2} if String, {@code 3} if Boolean.
   */
  public static Integer getTypePrecedence(Object value) {
    if (Objects.isNull(value)) {
      return 0;
    }
    if (value instanceof Number) {
      return 1;
    }
    if (value instanceof String) {
      return 2;
    }
    return 3;
  }
}
