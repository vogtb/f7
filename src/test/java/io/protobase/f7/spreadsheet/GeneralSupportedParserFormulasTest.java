package io.protobase.f7.spreadsheet;

import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralSupportedParserFormulasTest extends TestExecution {
  @Test
  public void test_TO_PERCENT() {
    runner().addCell("Alpha", "A1", "=TO_PERCENT(0.1111)").addExpectedValue("Alpha", "A1", 0.1111).run();
  }

  @Test
  public void test_TO_TEXT() {
    runner().addCell("Alpha", "A1", "=TO_TEXT(0.1111)").addExpectedValue("Alpha", "A1", "0.1111").run();
  }
}
