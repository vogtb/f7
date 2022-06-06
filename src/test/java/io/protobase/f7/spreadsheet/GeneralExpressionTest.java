package io.protobase.f7.spreadsheet;

import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralExpressionTest extends TestExecution {
  @Test
  public void test_Parentheses() {
    runner().addCell("Alpha", "A1", "= (TRUE)").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= (2 + 1) * 4").addExpectedValue("Alpha", "A1", 12.0).run();
  }
}
