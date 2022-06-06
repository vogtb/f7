package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.ParseException;
import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralUnaryPercentTest extends TestExecution {
  @Test
  public void test_Number() {
    runner().addCell("Alpha", "A1", "= 0%").addExpectedValue("Alpha", "A1", 0.0).run();
    runner().addCell("Alpha", "A1", "= 1%").addExpectedValue("Alpha", "A1", 0.01).run();
    runner().addCell("Alpha", "A1", "= 10%").addExpectedValue("Alpha", "A1", 0.1).run();
    runner().addCell("Alpha", "A1", "= 100%").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= 1000%").addExpectedValue("Alpha", "A1", 10.0).run();
    runner().addCell("Alpha", "A1", "= 0.2318937%").addExpectedValue("Alpha", "A1", 0.002318937).run();
    runner().addCell("Alpha", "A1", "= 10.167531%").addExpectedValue("Alpha", "A1", 0.10167531).run();
  }

  @Test
  public void test_String() {
    runner().addCell("Alpha", "A1", "= \"1\"%").addExpectedValue("Alpha", "A1", 0.01).run();
    runner().addCell("Alpha", "A1", "= \"10\"%").addExpectedValue("Alpha", "A1", 0.1).run();
    runner().addCell("Alpha", "A1", "= \"100\"%").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= \"1000\"%").addExpectedValue("Alpha", "A1", 10.0).run();
    runner().addCell("Alpha", "A1", "= \"0.2318937\"%").addExpectedValue("Alpha", "A1", 0.002318937).run();
    runner().addCell("Alpha", "A1", "= \"10.167531\"%").addExpectedValue("Alpha", "A1", 0.10167531).run();
  }

  @Test
  public void test_Boolean() {
    runner().addCell("Alpha", "A1", "= TRUE%").addExpectedValue("Alpha", "A1", 0.01).run();
    runner().addCell("Alpha", "A1", "= FALSE%").addExpectedValue("Alpha", "A1", 0.0).run();
  }

  @Test
  public void test_ArrayLiteral() {
    runner().addCell("Alpha", "A1", "= {1, 2, 3}%").addExpectedValue("Alpha", "A1", 0.01).run();
  }

  @Test
  public void test_MultiplePercentReturnsParseError() {
    // TODO/HACK: Multiple percent postfixes are allowed in Excel, but not in Google Sheets. Another one for the books.
    runner().addCell("Alpha", "A1", "= 1%%").addExpectedValue("Alpha", "A1", new ParseException()).run();
  }
}