package io.protobase.f7.spreadsheet;

import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralSupportedMathFormulasTest extends TestExecution {
  @Test
  public void test_ASIN() {
    runner().addCell("Alpha", "A1", "=ASIN(1)").addExpectedValue("Alpha", "A1", Math.asin(1)).run();
  }

  @Test
  public void test_ABS() {
    runner().addCell("Alpha", "A1", "=ABS(-1.0)").addExpectedValue("Alpha", "A1", 1.0).run();
  }

  @Test
  public void test_ACOS() {
    runner().addCell("Alpha", "A1", "=ACOS(4)").addExpectedValue("Alpha", "A1", Math.acos(4)).run();
  }

  @Test
  public void test_ACOSH() {
    runner().addCell("Alpha", "A1", "=ACOSH(1.1111)").addExpectedValue("Alpha", "A1", 0.4671223659261196).run();
  }

  @Test
  public void test_ACOT() {
    runner().addCell("Alpha", "A1", "=ACOT(1.1111)").addExpectedValue("Alpha", "A1", 0.732820074189669).run();
  }

  @Test
  public void test_ACOTH() {
    runner().addCell("Alpha", "A1", "=ACOTH(1.1111)").addExpectedValue("Alpha", "A1", 1.4722668604975144).run();
  }

  @Test
  public void test_ADD() {
    runner().addCell("Alpha", "A1", "=ADD(1.0, 2.0)").addExpectedValue("Alpha", "A1", 3.0).run();
  }

  @Test
  public void test_ASINH() {
    runner().addCell("Alpha", "A1", "=ASINH(1)").addExpectedValue("Alpha", "A1", 0.8813735870195429).run();
  }

  @Test
  public void test_ATAN() {
    runner().addCell("Alpha", "A1", "=ATAN(4)").addExpectedValue("Alpha", "A1", Math.atan(4)).run();
  }

  @Test
  public void test_ATAN2() {
    runner().addCell("Alpha", "A1", "=ATAN2(4, 3)").addExpectedValue("Alpha", "A1", Math.atan2(4, 3)).run();
  }

  @Test
  public void test_ATANH() {
    runner().addCell("Alpha", "A1", "=ATANH(0.489733)").addExpectedValue("Alpha", "A1", 0.5357090350574656).run();
  }

  @Test
  public void test_COS() {
    runner().addCell("Alpha", "A1", "=COS(4)").addExpectedValue("Alpha", "A1", Math.cos(4)).run();
  }

  @Test
  public void test_COSH() {
    runner().addCell("Alpha", "A1", "=COSH(4)").addExpectedValue("Alpha", "A1", Math.cosh(4)).run();
  }

  @Test
  public void test_COT() {
    runner().addCell("Alpha", "A1", "=COT(4)").addExpectedValue("Alpha", "A1", 1 / Math.tan(4)).run();
  }

  @Test
  public void test_DIVIDE() {
    runner().addCell("Alpha", "A1", "=DIVIDE(1.0, 2.0)").addExpectedValue("Alpha", "A1", 0.5).run();
  }

  @Test
  public void test_EVEN() {
    runner().addCell("Alpha", "A1", "=EVEN(3.0)").addExpectedValue("Alpha", "A1", 4.0).run();
  }

  @Test
  public void test_INT() {
    runner().addCell("Alpha", "A1", "=INT(16.129821)").addExpectedValue("Alpha", "A1", 16.0).run();
  }

  @Test
  public void test_ISEVEN() {
    runner().addCell("Alpha", "A1", "=ISEVEN(9)").addExpectedValue("Alpha", "A1", false).run();
  }

  @Test
  public void test_ISODD() {
    runner().addCell("Alpha", "A1", "=ISODD(9)").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_LN() {
    runner().addCell("Alpha", "A1", "=LN(128)").addExpectedValue("Alpha", "A1", 4.852030263919617).run();
  }

  @Test
  public void test_LOG10() {
    runner().addCell("Alpha", "A1", "=LOG10(100)").addExpectedValue("Alpha", "A1", 2.0).run();
  }

  @Test
  public void test_LOG() {
    runner().addCell("Alpha", "A1", "=LOG(128, 2)").addExpectedValue("Alpha", "A1", 7.0).run();
  }

  @Test
  public void test_MINUS() {
    runner().addCell("Alpha", "A1", "=MINUS(1.0, 2.0)").addExpectedValue("Alpha", "A1", -1.0).run();
  }

  @Test
  public void test_MOD() {
    runner().addCell("Alpha", "A1", "=MOD(128, 44)").addExpectedValue("Alpha", "A1", 40.0).run();
  }

  @Test
  public void test_MULTIPLY() {
    runner().addCell("Alpha", "A1", "=MULTIPLY(2.0, 4.0)").addExpectedValue("Alpha", "A1", 8.0).run();
  }

  @Test
  public void test_ODD() {
    runner().addCell("Alpha", "A1", "=ODD(2.0)").addExpectedValue("Alpha", "A1", 3.0).run();
  }

  @Test
  public void test_PI() {
    runner().addCell("Alpha", "A1", "=PI()").addExpectedValue("Alpha", "A1", Math.PI).run();
  }

  @Test
  public void test_POW() {
    runner().addCell("Alpha", "A1", "=POW(2.0, 6.0)").addExpectedValue("Alpha", "A1", 64.0).run();
  }

  @Test
  public void test_POWER() {
    runner().addCell("Alpha", "A1", "=POWER(2.0, 6.0)").addExpectedValue("Alpha", "A1", 64.0).run();
  }

  @Test
  public void test_PRODUCT() {
    runner().addCell("Alpha", "A1", "=PRODUCT(2.0, 6.0, 4.0)").addExpectedValue("Alpha", "A1", 48.0).run();
  }

  @Test
  public void test_QUOTIENT() {
    runner().addCell("Alpha", "A1", "=QUOTIENT(7.0, 2.0)").addExpectedValue("Alpha", "A1", 3.0).run();
  }

  @Test
  public void test_RANDBETWEEN() {
    // TODO:
  }

  @Test
  public void test_RAND() {
    // TODO:
  }

  @Test
  public void test_ROUND() {
    runner().addCell("Alpha", "A1", "=ROUND(16.129821, 3.0)").addExpectedValue("Alpha", "A1", 16.13).run();
  }

  @Test
  public void test_ROUNDDOWN() {
    runner().addCell("Alpha", "A1", "=ROUNDDOWN(16.129821)").addExpectedValue("Alpha", "A1", 16.0).run();
  }

  @Test
  public void test_ROUNDUP() {
    runner().addCell("Alpha", "A1", "=ROUNDUP(16.129821)").addExpectedValue("Alpha", "A1", 17.0).run();
  }

  @Test
  public void test_SIGN() {
    runner().addCell("Alpha", "A1", "=SIGN(128)").addExpectedValue("Alpha", "A1", 1.0).run();
  }

  @Test
  public void test_SIN() {
    runner().addCell("Alpha", "A1", "=SIN(1)").addExpectedValue("Alpha", "A1", Math.sin(1)).run();
  }

  @Test
  public void test_SINH() {
    runner().addCell("Alpha", "A1", "=SINH(1)").addExpectedValue("Alpha", "A1", Math.sinh(1)).run();
  }

  @Test
  public void test_SQRT() {
    runner().addCell("Alpha", "A1", "=SQRT(4)").addExpectedValue("Alpha", "A1", Math.sqrt(4)).run();
  }

  @Test
  public void test_SQRTPI() {
    runner().addCell("Alpha", "A1", "=SQRTPI(4)").addExpectedValue("Alpha", "A1", 3.5449077018110318).run();
  }

  @Test
  public void test_TAN() {
    runner().addCell("Alpha", "A1", "=TAN(4)").addExpectedValue("Alpha", "A1", Math.tan(4)).run();
  }

  @Test
  public void test_TANH() {
    runner().addCell("Alpha", "A1", "=TANH(4)").addExpectedValue("Alpha", "A1", Math.tanh(4)).run();
  }

  @Test
  public void test_UMINUS() {
    runner().addCell("Alpha", "A1", "=UMINUS(7.0)").addExpectedValue("Alpha", "A1", -7.0).run();
  }

  @Test
  public void test_UPLUS() {
    runner().addCell("Alpha", "A1", "=UPLUS(\"7.0\")").addExpectedValue("Alpha", "A1", 7.0).run();
  }

  @Test
  public void test_UNARY_PERCENT() {
    runner().addCell("Alpha", "A1", "=UNARY_PERCENT(17.0)").addExpectedValue("Alpha", "A1", 0.17).run();
  }
}
