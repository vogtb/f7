package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralConcatenationTest extends TestExecution {
  @Test
  public void test_String() {
    runner().addCell("Alpha", "A1", "= \"Hello\" & \"There\"").addExpectedValue("Alpha", "A1", "HelloThere").run();
    runner().addCell("Alpha", "A1", "= \"Hello\" & \"\"").addExpectedValue("Alpha", "A1", "Hello").run();
    runner().addCell("Alpha", "A1", "= \"\" & \"\"").addExpectedValue("Alpha", "A1", "").run();
    runner().addCell("Alpha", "A1", "= \"   \" & \"   \"").addExpectedValue("Alpha", "A1", "      ").run();
  }

  @Test
  public void test_Number() {
    runner().addCell("Alpha", "A1", "= 0 & 1").addExpectedValue("Alpha", "A1", "01").run();
    runner().addCell("Alpha", "A1", "= 0 & 0").addExpectedValue("Alpha", "A1", "00").run();
    runner().addCell("Alpha", "A1", "= 131238 & 99281").addExpectedValue("Alpha", "A1", "13123899281").run();
    runner().addCell("Alpha", "A1", "= 13.1238 & 99281").addExpectedValue("Alpha", "A1", "13.123899281").run();
    runner().addCell("Alpha", "A1", "= 0.001 & 1.0").addExpectedValue("Alpha", "A1", "0.0011").run();
  }

  @Test
  public void test_Boolean() {
    runner().addCell("Alpha", "A1", "= TRUE & TRUE").addExpectedValue("Alpha", "A1", "TRUETRUE").run();
    runner().addCell("Alpha", "A1", "= TRUE & FALSE").addExpectedValue("Alpha", "A1", "TRUEFALSE").run();
    runner().addCell("Alpha", "A1", "= FALSE & FALSE").addExpectedValue("Alpha", "A1", "FALSEFALSE").run();
  }

  @Test
  public void test_Error() {
    runner().addCell("Alpha", "A1", "= 1 & #DIV/0!").addExpectedValue("Alpha", "A1", new DivException()).run();
  }

  @Test
  public void test_ArrayLiteral() {
    runner().addCell("Alpha", "A1", "= {1} & {2}").addExpectedValue("Alpha", "A1", "12").run();
    runner().addCell("Alpha", "A1", "= {1, 2, 3} & {4, 5, 6}").addExpectedValue("Alpha", "A1", "14").run();
    runner().addCell("Alpha", "A1", "= {1, #NUM!} & {4, #REF!}").addExpectedValue("Alpha", "A1", "14").run();
  }

  @Test
  public void test_Blank() {
    runner().addCell("Alpha", "A1", "= {B1} & {B2}").addExpectedValue("Alpha", "A1", "").run();
    runner().addCell("Alpha", "A1", "= {B1} & \"\"").addExpectedValue("Alpha", "A1", "").run();
  }
}
