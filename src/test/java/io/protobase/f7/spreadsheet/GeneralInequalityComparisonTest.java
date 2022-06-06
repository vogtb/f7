package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.RefException;
import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralInequalityComparisonTest extends TestExecution {
  @Test
  public void test_Number() {
    runner().addCell("Alpha", "A1", "= 1 <> 1").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= 1 <> 2").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= 1.1928731 <> 1.1928731").addExpectedValue("Alpha", "A1", false).run();
  }

  @Test
  public void test_String() {
    runner().addCell("Alpha", "A1", "= \"Yes\" <> \"Yes\"").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= \"Yes\" <> \"No\"").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= \"\" <> \"\"").addExpectedValue("Alpha", "A1", false).run();
  }

  @Test
  public void test_Boolean() {
    runner().addCell("Alpha", "A1", "= TRUE <> TRUE").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= FALSE <> FALSE").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= TRUE <> FALSE").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_ArrayLiteral() {
    runner().addCell("Alpha", "A1", "= {1, 2, 3} <> {1, 2, 3}").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= {1, 2, 3} <> {44}").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= {44, #REF!} <> {44, #REF!}").addExpectedValue("Alpha", "A1", false).run();
  }

  @Test
  public void test_NumberToBoolean() {
    runner().addCell("Alpha", "A1", "= 0 <> TRUE").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= 1 <> TRUE").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= 1 <> FALSE").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= 0 <> FALSE").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_NumberToString() {
    runner().addCell("Alpha", "A1", "= 0 <> \"\"").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= 0 <> \"0\"").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= 1 <> \"0\"").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= 1 <> \"1\"").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_NumberToArrayLiteral() {
    runner().addCell("Alpha", "A1", "= 0 <> {0, 1, 2}").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= 1 <> {0, 1, 2}").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_NumberToBlank() {
    runner().addCell("Alpha", "A1", "= 0 <> M9").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= 1 <> M9").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= M9 <> 0").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= M9 <> 1").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= -1 <> M9").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= M9 <> -1").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_StringToBoolean() {
    runner().addCell("Alpha", "A1", "= \"TRUE\" <> TRUE").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= \"FALSE\" <> FALSE").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_StringToBlank() {
    runner().addCell("Alpha", "A1", "= \"\" <> M10").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= M10 <> \"\"").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= \" \" <> M10").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= M10 <> \" \"").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= \"One\" <> M10").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= M10 <> \"One\"").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_BooleanToBlank() {
    runner().addCell("Alpha", "A1", "= TRUE <> M10").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= FALSE <> M10").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= M10 <> TRUE").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= M10 <> FALSE").addExpectedValue("Alpha", "A1", false).run();
  }

  @Test
  public void test_ArrayLiteralToString() {
    runner().addCell("Alpha", "A1", "= {\"A\", \"B\"} <> \"A\"").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= \"A\" <> {\"A\", \"B\"}").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= \"B\" <> {\"A\", \"B\"}").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_ArrayLiteralToBoolean() {
    runner().addCell("Alpha", "A1", "= {TRUE, FALSE} <> TRUE").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= TRUE <> {TRUE, FALSE}").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= {FALSE, FALSE} <> TRUE").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= TRUE <> {FALSE, FALSE}").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_Error() {
    runner().addCell("Alpha", "A1", "= 10 <> #REF!").addExpectedValue("Alpha", "A1", new RefException()).run();
    runner().addCell("Alpha", "A1", "= #REF! <> TRUE").addExpectedValue("Alpha", "A1", new RefException()).run();
  }
}
