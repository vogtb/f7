package io.protobase.f7.utils;

import com.google.common.collect.ImmutableList;
import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.errors.NumException;
import io.protobase.f7.errors.ValueException;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ConvertersTest {

  @Test
  public void test_compareDouble() {
    assertThat(Converters.compare(0.0, 0.0)).isEqualTo(ComparisonResult.EQUAL);
    assertThat(Converters.compare(1.0, 1.0)).isEqualTo(ComparisonResult.EQUAL);
    assertThat(Converters.compare(1.0, 0.0)).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare(2.0, 1.0)).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare(0.0, 1.0)).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare(1.0, 2.0)).isEqualTo(ComparisonResult.LESS_THAN);
  }

  @Test
  public void test_compareString() {
    assertThat(Converters.compare("a", "a")).isEqualTo(ComparisonResult.EQUAL);
    assertThat(Converters.compare("b", "b")).isEqualTo(ComparisonResult.EQUAL);
    assertThat(Converters.compare("b", "a")).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare("c", "b")).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare("a", "b")).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare("b", "c")).isEqualTo(ComparisonResult.LESS_THAN);
  }

  @Test
  public void test_compareBoolean() {
    assertThat(Converters.compare(true, true)).isEqualTo(ComparisonResult.EQUAL);
    assertThat(Converters.compare(false, false)).isEqualTo(ComparisonResult.EQUAL);
    assertThat(Converters.compare(true, false)).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare(false, true)).isEqualTo(ComparisonResult.LESS_THAN);
  }

  @Test
  public void test_compareDoubleAndString() {
    assertThat(Converters.compare(1.0, "a")).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare(-10.0, "a")).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare("a", 1.0)).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare("a", -10.0)).isEqualTo(ComparisonResult.GREATER_THAN);
  }

  @Test
  public void test_compareDoubleAndBoolean() {
    assertThat(Converters.compare(1.0, true)).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare(-10.0, true)).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare(1.0, false)).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare(-10.0, false)).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare(true, 1.0)).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare(true, -10.0)).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare(false, 1.0)).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare(false, -10.0)).isEqualTo(ComparisonResult.GREATER_THAN);
  }

  @Test
  public void test_compareBooleanString() {
    assertThat(Converters.compare("a", true)).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare("a", false)).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare("", true)).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare("", false)).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare(true, "a")).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare(false, "a")).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare(true, "")).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare(false, "")).isEqualTo(ComparisonResult.GREATER_THAN);
  }

  @Test
  public void test_compareNull() {
    assertThat(Converters.compare(null, null)).isEqualTo(ComparisonResult.EQUAL);
  }

  @Test
  public void test_compareNullAndBoolean() {
    assertThat(Converters.compare(null, true)).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare(null, false)).isEqualTo(ComparisonResult.EQUAL);
    assertThat(Converters.compare(true, null)).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare(false, null)).isEqualTo(ComparisonResult.EQUAL);
  }

  @Test
  public void test_compareNullAndNumber() {
    assertThat(Converters.compare(null, 1.0)).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare(null, 1000.0)).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare(null, 0.0)).isEqualTo(ComparisonResult.EQUAL);
    assertThat(Converters.compare(1.0, null)).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare(1000.0, null)).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare(0.0, null)).isEqualTo(ComparisonResult.EQUAL);
  }

  @Test
  public void test_compareNullAndString() {
    assertThat(Converters.compare(null, "a")).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare(null, "b")).isEqualTo(ComparisonResult.LESS_THAN);
    assertThat(Converters.compare(null, "")).isEqualTo(ComparisonResult.EQUAL);
    assertThat(Converters.compare("a", null)).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare("b", null)).isEqualTo(ComparisonResult.GREATER_THAN);
    assertThat(Converters.compare("", null)).isEqualTo(ComparisonResult.EQUAL);
  }

  @Test
  public void testConvertToDouble_fromDouble() {
    assertThat(Converters.toDouble(10.1)).isEqualTo(10.1);
    assertThat(Converters.toDouble(10.0)).isEqualTo(10.0);
  }

  @Test
  public void testConvertToDouble_fromString() {
    assertThat(Converters.toDouble("10.1")).isEqualTo(10.1);
    assertThat(Converters.toDouble("10.1e4")).isEqualTo(10.1e4);
  }

  @Test
  public void testConvertToDouble_fromBoolean() {
    assertThat(Converters.toDouble(true)).isEqualTo(1.0);
    assertThat(Converters.toDouble(false)).isEqualTo(0.0);
  }

  @Test
  public void testConvertToDouble_fromError() {
    assertThat(tryToConvertToDoubleAndCatch(new DivException())).isEqualTo(new DivException());
    assertThat(tryToConvertToDoubleAndCatch(new NumException())).isEqualTo(new NumException());
  }

  @Test
  public void testConvertToDouble_fromAnythingElse() {
    assertThat(tryToConvertToDoubleAndCatch(10)).isEqualTo(new ValueException());
    assertThat(tryToConvertToDoubleAndCatch(ImmutableList.of())).isEqualTo(new ValueException());
    assertThat(tryToConvertToDoubleAndCatch(new Object())).isEqualTo(new ValueException());
  }

  private Object tryToConvertToDoubleAndCatch(Object any) {
    try {
      return Converters.toDouble(any);
    } catch (F7Exception error) {
      return error;
    }
  }
}
