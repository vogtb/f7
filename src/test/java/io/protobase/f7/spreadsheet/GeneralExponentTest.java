package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.NumException;
import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralExponentTest extends TestExecution {
  @Test
  public void test_Numbers_PositiveNumeratorPositiveExponent() {
    runner().addCell("Alpha", "A1", "= 3^5").addExpectedValue("Alpha", "A1", 243.0).run();
    runner().addCell("Alpha", "A1", "= 4^5").addExpectedValue("Alpha", "A1", 1024.0).run();
    runner().addCell("Alpha", "A1", "= 1^1").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= 1^5").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= 2^0").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= 0^10").addExpectedValue("Alpha", "A1", 0.0).run();
  }

  @Test
  public void test_Numbers_PositiveNumeratorNegativeExponent() {
    runner().addCell("Alpha", "A1", "= 3^-5").addExpectedValue("Alpha", "A1", 0.00411522633744856).run();
    runner().addCell("Alpha", "A1", "= 4^-5").addExpectedValue("Alpha", "A1", 9.765625E-4).run();
    runner().addCell("Alpha", "A1", "= 1^-1").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= 1^-5").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= 2^-0").addExpectedValue("Alpha", "A1", 1.0).run();
    // TODO/HACK: This is different in Excel and Google Sheets.
    runner().addCell("Alpha", "A1", "= 0^-10").addExpectedValue("Alpha", "A1", new NumException()).run();
  }

  @Test
  public void test_Numbers_NegativeNumeratorNegativeExponent() {
    runner().addCell("Alpha", "A1", "= -3^-1").addExpectedValue("Alpha", "A1", -0.3333333333333333).run();
    runner().addCell("Alpha", "A1", "= -3^-2").addExpectedValue("Alpha", "A1", 0.1111111111111111).run();
    runner().addCell("Alpha", "A1", "= -3^-3").addExpectedValue("Alpha", "A1", -0.037037037037037035).run();
    runner().addCell("Alpha", "A1", "= -2^-3").addExpectedValue("Alpha", "A1", -0.125).run();
    runner().addCell("Alpha", "A1", "= -1^-3").addExpectedValue("Alpha", "A1", -1.0).run();
    runner().addCell("Alpha", "A1", "= -1^-2").addExpectedValue("Alpha", "A1", 1.0).run();
  }

  @Test
  public void test_Numbers_NegativeNumeratorPositiveExponent() {
    runner().addCell("Alpha", "A1", "= -3^1").addExpectedValue("Alpha", "A1", -3.0).run();
    runner().addCell("Alpha", "A1", "= -3^2").addExpectedValue("Alpha", "A1", 9.0).run();
    runner().addCell("Alpha", "A1", "= -3^3").addExpectedValue("Alpha", "A1", -27.0).run();
    runner().addCell("Alpha", "A1", "= -2^3").addExpectedValue("Alpha", "A1", -8.0).run();
    runner().addCell("Alpha", "A1", "= -1^3").addExpectedValue("Alpha", "A1", -1.0).run();
    runner().addCell("Alpha", "A1", "= -1^2").addExpectedValue("Alpha", "A1", 1.0).run();
  }

  @Test
  public void test_Order() {
    runner().addCell("Alpha", "A1", "= 2 ^ 3 ^ 4").addExpectedValue("Alpha", "A1", 4096.0).run();
    runner().addCell("Alpha", "A1", "= 3 ^ 2 ^ 4").addExpectedValue("Alpha", "A1", 6561.0).run();
    runner().addCell("Alpha", "A1", "= 4 ^ 2 ^ 3").addExpectedValue("Alpha", "A1", 4096.0).run();
  }

  @Test
  public void test_Boolean() {
    runner().addCell("Alpha", "A1", "= 9 ^ TRUE").addExpectedValue("Alpha", "A1", 9.0).run();
    runner().addCell("Alpha", "A1", "= 9 ^ FALSE").addExpectedValue("Alpha", "A1", 1.0).run();
  }

  @Test
  public void test_String() {
    runner().addCell("Alpha", "A1", "= 2 ^ \"3\"").addExpectedValue("Alpha", "A1", 8.0).run();
    runner().addCell("Alpha", "A1", "= 2 ^ \"3e1\"").addExpectedValue("Alpha", "A1", 1.073741824E9).run();
    runner().addCell("Alpha", "A1", "= 2 ^ \"2\"").addExpectedValue("Alpha", "A1", 4.0).run();
  }
}
