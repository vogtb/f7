package io.protobase.f7.spreadsheet;

import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralNumberTest extends TestExecution {
  @Test
  public void test_Integer() {
    runner().addCell("Alpha", "A1", "= 0").addExpectedValue("Alpha", "A1", 0.0).run();
    runner().addCell("Alpha", "A1", "= 1").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= 2738281").addExpectedValue("Alpha", "A1", 2738281.0).run();
    runner().addCell("Alpha", "A1", "= 0001776").addExpectedValue("Alpha", "A1", 1776.0).run();
  }

  @Test
  public void test_Decimal() {
    runner().addCell("Alpha", "A1", "= 0.0").addExpectedValue("Alpha", "A1", 0.0).run();
    runner().addCell("Alpha", "A1", "= 0.187328").addExpectedValue("Alpha", "A1", 0.187328).run();
    runner().addCell("Alpha", "A1", "= 38133.09128901").addExpectedValue("Alpha", "A1", 38133.09128901).run();
    runner().addCell("Alpha", "A1", "= 4.00000000000001").addExpectedValue("Alpha", "A1", 4.00000000000001).run();
    runner().addCell("Alpha", "A1", "= 33.0000000000000").addExpectedValue("Alpha", "A1", 33.0).run();
  }

  @Test
  public void test_InferredPositiveScientificNotation() {
    runner().addCell("Alpha", "A1", "= 12387e0").addExpectedValue("Alpha", "A1", 12387.0).run();
    runner().addCell("Alpha", "A1", "= 12387e1").addExpectedValue("Alpha", "A1", 123870.0).run();
    runner().addCell("Alpha", "A1", "= 1.0e4").addExpectedValue("Alpha", "A1", 10000.0).run();
    runner().addCell("Alpha", "A1", "= 0.0009e4").addExpectedValue("Alpha", "A1", 9.0).run();
  }

  @Test
  public void test_PositiveScientificNotation() {
    runner().addCell("Alpha", "A1", "= 12387e+0").addExpectedValue("Alpha", "A1", 12387.0).run();
    runner().addCell("Alpha", "A1", "= 12387e+1").addExpectedValue("Alpha", "A1", 123870.0).run();
    runner().addCell("Alpha", "A1", "= 1.0e+4").addExpectedValue("Alpha", "A1", 10000.0).run();
    runner().addCell("Alpha", "A1", "= 0.0009e+4").addExpectedValue("Alpha", "A1", 9.0).run();
  }

  @Test
  public void test_NegativeScientificNotation() {
    runner().addCell("Alpha", "A1", "= 12387e-0").addExpectedValue("Alpha", "A1", 12387e-0).run();
    runner().addCell("Alpha", "A1", "= 12387e-1").addExpectedValue("Alpha", "A1", 12387e-1).run();
    runner().addCell("Alpha", "A1", "= 1.0e-4").addExpectedValue("Alpha", "A1", 1.0e-4).run();
    runner().addCell("Alpha", "A1", "= 0.0009e-4").addExpectedValue("Alpha", "A1", 0.0009e-4).run();
  }
}
