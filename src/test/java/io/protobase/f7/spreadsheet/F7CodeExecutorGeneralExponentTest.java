package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.NumException;
import io.protobase.f7.testutils.TestF7CodeExecutor;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class F7CodeExecutorGeneralExponentTest extends TestF7CodeExecutor {
  @Test
  public void test_Numbers_PositiveNumeratorPositiveExponent() {
    assertThat(run("= 3^5")).isEqualTo(243.0);
    assertThat(run("= 4^5")).isEqualTo(1024.0);
    assertThat(run("= 1^1")).isEqualTo(1.0);
    assertThat(run("= 1^5")).isEqualTo(1.0);
    assertThat(run("= 2^0")).isEqualTo(1.0);
    assertThat(run("= 0^10")).isEqualTo(0.0);
  }

  @Test
  public void test_Numbers_PositiveNumeratorNegativeExponent() {
    assertThat(run("= 3^-5")).isEqualTo(0.00411522633744856);
    assertThat(run("= 4^-5")).isEqualTo(9.765625E-4);
    assertThat(run("= 1^-1")).isEqualTo(1.0);
    assertThat(run("= 1^-5")).isEqualTo(1.0);
    assertThat(run("= 2^-0")).isEqualTo(1.0);
    // TODO/HACK: This is different in Excel and Google Sheets.
    assertThat(run("= 0^-10")).isEqualTo(new NumException());
  }

  @Test
  public void test_Numbers_NegativeNumeratorNegativeExponent() {
    assertThat(run("= -3^-1")).isEqualTo(-0.3333333333333333);
    assertThat(run("= -3^-2")).isEqualTo(0.1111111111111111);
    assertThat(run("= -3^-3")).isEqualTo(-0.037037037037037035);
    assertThat(run("= -2^-3")).isEqualTo(-0.125);
    assertThat(run("= -1^-3")).isEqualTo(-1.0);
    assertThat(run("= -1^-2")).isEqualTo(1.0);
  }

  @Test
  public void test_Numbers_NegativeNumeratorPositiveExponent() {
    assertThat(run("= -3^1")).isEqualTo(-3.0);
    assertThat(run("= -3^2")).isEqualTo(9.0);
    assertThat(run("= -3^3")).isEqualTo(-27.0);
    assertThat(run("= -2^3")).isEqualTo(-8.0);
    assertThat(run("= -1^3")).isEqualTo(-1.0);
    assertThat(run("= -1^2")).isEqualTo(1.0);
  }

  @Test
  public void test_Order() {
    assertThat(run("= 2 ^ 3 ^ 4")).isEqualTo(4096.0);
    assertThat(run("= 3 ^ 2 ^ 4")).isEqualTo(6561.0);
    assertThat(run("= 4 ^ 2 ^ 3")).isEqualTo(4096.0);
  }

  @Test
  public void test_Boolean() {
    assertThat(run("= 9 ^ TRUE")).isEqualTo(9.0);
    assertThat(run("= 9 ^ FALSE")).isEqualTo(1.0);
  }

  @Test
  public void test_String() {
    assertThat(run("= 2 ^ \"3\"")).isEqualTo(8.0);
    assertThat(run("= 2 ^ \"3e1\"")).isEqualTo(1.073741824E9);
    assertThat(run("= 2 ^ \"2\"")).isEqualTo(4.0);
  }
}
