package io.protobase.f7.spreadsheet;

import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralSupportedLookupFormulasTest extends TestExecution {
  @Test
  public void test_CHOOSE() {
    runner().addCell("Alpha", "A1", "=CHOOSE(2, 1, 2, 3)").addExpectedValue("Alpha", "A1", 2.0).run();
  }
}
