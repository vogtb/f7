package io.protobase.f7.utils;

import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.models.Grid;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Mappers {
  /**
   * Flatten possibly nested lists into a flat stream of objects that are either not a list, or a list of size 0.
   *
   * @param value - to return as a stream.
   * @return - stream of objects that are Number, Boolean, String, F7Exception, or List of size 0.
   */
  public static Stream<Object> toFlatStream(Object value) {
    if (value instanceof Grid) {
      return StreamSupport.stream(((Grid<Object>) value).spliterator(), false);
    }
    return Stream.of(value);
  }

  /**
   * Flatten possibly nested lists/grids into a flat stream of objects that are either not a list, or a list of size 0,
   * along the way convert second-degree streams/lists to streams/lists with ONLY number literals. No booleans,
   * coercable strings, or errors.
   * <p>
   * Examples:
   * 6, 7, {1, 2, "3"} would become {6}, {7}, {1, 2}.
   * {1, 2, "NA"} would become {1, 2}.
   * {1, 2, true} would become {1, 2}.
   *
   * @param value - to return as stream.
   * @return stream of objects.
   */
  public static Stream<Object> toFlatStreamGridFilterOnlyNumberLiterals(Object value) {
    if (value instanceof Grid) {
      return StreamSupport.stream(((Grid<Object>) value).spliterator(), false)
          .filter(Filters::isLiteralNumber);
    }
    return Stream.of(value);
  }

  /**
   * Flatten possibly nested lists/grids into a flat stream of objects that are either not a list, or a list of size 0,
   * along the way convert second-degree streams/lists to streams/lists with ONLY number literals and boolean literals.
   * No strings.
   * <p>
   * Examples:
   * 6, 7, {1, 2, "3"} would become {6}, {7}, {1, 2}.
   * {1, 2, "NA"} would become {1, 2}.
   * {1, 2, true} would become {1, 2, true}.
   *
   * @param value - to return as stream.
   * @return stream of objects.
   */
  public static Stream<Object> toFlatStreamFilterOnlyNumeric(Object value) {
    if (value instanceof Grid) {
      return StreamSupport.stream(((Grid<Object>) value).spliterator(), false)
          .map(Converters::textToZero)
          .filter(Filters::isNumeric);
    }
    return Stream.of(value);
  }

  /**
   * Return object if it's not an exception. Throw the exception if it exists.
   *
   * @param value - to check.
   * @return object.
   */
  public static Object throwIfException(Object value) {
    if (value instanceof F7Exception) {
      throw ((F7Exception) value);
    }
    return value;
  }

}
