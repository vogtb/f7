package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.RefException;
import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralTypeCastingTest extends TestExecution {
  @Test
  public void test_StringToNumber_Integer() {
    runner().addCell("Alpha", "A1", "= \"0\" * 1").addExpectedValue("Alpha", "A1", 0.0).run();
    runner().addCell("Alpha", "A1", "= \"1\" * 1").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= \"2738281\" * 1").addExpectedValue("Alpha", "A1", 2738281.0).run();
    runner().addCell("Alpha", "A1", "= \"0001776\" * 1").addExpectedValue("Alpha", "A1", 1776.0).run();
  }

  @Test
  public void test_StringToNumber_Decimal() {
    runner().addCell("Alpha", "A1", "= \"0.0\" * 1").addExpectedValue("Alpha", "A1", 0.0).run();
    runner().addCell("Alpha", "A1", "= \"0.187328\" * 1").addExpectedValue("Alpha", "A1", 0.187328).run();
    runner().addCell("Alpha", "A1", "= \"38133.09128901\" * 1").addExpectedValue("Alpha", "A1", 38133.09128901).run();
    runner().addCell("Alpha", "A1", "= \"4.00000000000001\" * 1").addExpectedValue("Alpha", "A1", 4.00000000000001).run();
    runner().addCell("Alpha", "A1", "= \"33.0000000000000\" * 1").addExpectedValue("Alpha", "A1", 33.0).run();
  }

  @Test
  public void test_StringToNumber_InferredPositiveScientificNotation() {
    runner().addCell("Alpha", "A1", "= \"12387e0\" * 1").addExpectedValue("Alpha", "A1", 12387.0).run();
    runner().addCell("Alpha", "A1", "= \"12387e1\" * 1").addExpectedValue("Alpha", "A1", 123870.0).run();
    runner().addCell("Alpha", "A1", "= \"1.0e4\" * 1").addExpectedValue("Alpha", "A1", 10000.0).run();
    runner().addCell("Alpha", "A1", "= \"0.0009e4\" * 1").addExpectedValue("Alpha", "A1", 9.0).run();
  }

  @Test
  public void test_StringToNumber_PositiveScientificNotation() {
    runner().addCell("Alpha", "A1", "= \"12387e+0\" * 1").addExpectedValue("Alpha", "A1", 12387.0).run();
    runner().addCell("Alpha", "A1", "= \"12387e+1\" * 1").addExpectedValue("Alpha", "A1", 123870.0).run();
    runner().addCell("Alpha", "A1", "= \"1.0e+4\" * 1").addExpectedValue("Alpha", "A1", 10000.0).run();
    runner().addCell("Alpha", "A1", "= \"0.0009e+4\" * 1").addExpectedValue("Alpha", "A1", 9.0).run();
  }

  @Test
  public void test_StringToNumber_NegativeScientificNotation() {
    runner().addCell("Alpha", "A1", "= \"12387e-0\" * 1").addExpectedValue("Alpha", "A1", 12387e-0).run();
    runner().addCell("Alpha", "A1", "= \"12387e-1\" * 1").addExpectedValue("Alpha", "A1", 12387e-1).run();
    runner().addCell("Alpha", "A1", "= \"1.0e-4\" * 1").addExpectedValue("Alpha", "A1", 1.0e-4).run();
    runner().addCell("Alpha", "A1", "= \"0.0009e-4\" * 1").addExpectedValue("Alpha", "A1", 0.0009e-4).run();
  }

  @Test
  public void test_BooleanToNumber() {
    runner().addCell("Alpha", "A1", "= FALSE * 1").addExpectedValue("Alpha", "A1", 0.0).run();
    runner().addCell("Alpha", "A1", "= TRUE * 1").addExpectedValue("Alpha", "A1", 1.0).run();
  }

  @Test
  public void test_NumberToString_Integer() {
    runner().addCell("Alpha", "A1", "= 0 & \"\"").addExpectedValue("Alpha", "A1", "0").run();
    runner().addCell("Alpha", "A1", "= 1 & \"\"").addExpectedValue("Alpha", "A1", "1").run();
    runner().addCell("Alpha", "A1", "= 2738281 & \"\"").addExpectedValue("Alpha", "A1", "2738281").run();
    runner().addCell("Alpha", "A1", "= 0001776 & \"\"").addExpectedValue("Alpha", "A1", "1776").run();
  }

  @Test
  public void test_NumberToString_Decimal() {
    runner().addCell("Alpha", "A1", "= 0.0 & \"\"").addExpectedValue("Alpha", "A1", "0").run();
    runner().addCell("Alpha", "A1", "= 0.187328 & \"\"").addExpectedValue("Alpha", "A1", "0.187328").run();
    runner().addCell("Alpha", "A1", "= 38133.09128901 & \"\"").addExpectedValue("Alpha", "A1", "38133.09128901").run();
    runner().addCell("Alpha", "A1", "= 4.00000000000001 & \"\"").addExpectedValue("Alpha", "A1", "4.00000000000001").run();
    runner().addCell("Alpha", "A1", "= 33.0000000000000 & \"\"").addExpectedValue("Alpha", "A1", "33").run();
  }

  @Test
  public void test_NumberToString_PositiveScientificNotation() {
    runner().addCell("Alpha", "A1", "= 12387183718789125223e0 & \"\"").addExpectedValue("Alpha", "A1", "12387183718789126000").run();
    runner().addCell("Alpha", "A1", "= 31287e42 & \"\"")
        .addExpectedValue("Alpha", "A1", "31287000000000000000000000000000000000000000000").run();
    runner().addCell("Alpha", "A1", "= 1.0e4 & \"\"").addExpectedValue("Alpha", "A1", "10000").run();
    runner().addCell("Alpha", "A1", "= 0.0009e4 & \"\"").addExpectedValue("Alpha", "A1", "9").run();
  }

  @Test
  public void test_NumberToBoolean_False() {
    runner().addCell("Alpha", "A1", "= IF(0, TRUE, FALSE)").addExpectedValue("Alpha", "A1", false).run();
  }

  @Test
  public void test_NumberToBoolean_True() {
    runner().addCell("Alpha", "A1", "= IF(0.00001, TRUE, FALSE)").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= IF(1, TRUE, FALSE)").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= IF(2, TRUE, FALSE)").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= IF(-823178, TRUE, FALSE)").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= IF(31287e42, TRUE, FALSE)").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_ListToNumber() {
    runner().addCell("Alpha", "A1", "= {2, 4, 8} * 1").addExpectedValue("Alpha", "A1", 2.0).run();
    runner().addCell("Alpha", "A1", "= {2, \"Nope.\"} * 1").addExpectedValue("Alpha", "A1", 2.0).run();
    runner().addCell("Alpha", "A1", "= {} * 1").addExpectedValue("Alpha", "A1", new RefException()).run();
  }

  @Test
  public void test_ListToBoolean() {
    runner().addCell("Alpha", "A1", "= IF({2, 4, 8}, TRUE, FALSE)").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= IF({2, \"Nope.\"}, TRUE, FALSE)").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= IF({TRUE, \"Nope.\"}, TRUE, FALSE)").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= IF({}, TRUE, FALSE)").addExpectedValue("Alpha", "A1", new RefException()).run();
  }
}
