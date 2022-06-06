package io.protobase.f7.spreadsheet;

import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralSupportedTextFormulasTest extends TestExecution {
  @Test
  public void test_CONCAT() {
    runner().addCell("Alpha", "A1", "=CONCAT(\"One\", \"Two\")").addExpectedValue("Alpha", "A1", "OneTwo").run();
  }

  @Test
  public void test_CONCATENATE() {
    runner().addCell("Alpha", "A1", "=CONCATENATE(\"One\", \"Two\")").addExpectedValue("Alpha", "A1", "OneTwo").run();
  }

  @Test
  public void test_T() {
    runner().addCell("Alpha", "A1", "=T(\"One\")").addExpectedValue("Alpha", "A1", "One").run();
  }

  @Test
  public void test_LEN() {
    runner().addCell("Alpha", "A1", "=LEN(\"One\")").addExpectedValue("Alpha", "A1", 3.0).run();
  }
}
