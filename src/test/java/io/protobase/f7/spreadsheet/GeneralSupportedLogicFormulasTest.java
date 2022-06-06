package io.protobase.f7.spreadsheet;

import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralSupportedLogicFormulasTest extends TestExecution {
  @Test
  public void test_AND() {
    runner().addCell("Alpha", "A1", "=AND(TRUE, 1, 1)").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_EQ() {
    runner().addCell("Alpha", "A1", "=EQ(10, 298132.222)").addExpectedValue("Alpha", "A1", false).run();
  }

  @Test
  public void test_EXACT() {
    runner().addCell("Alpha", "A1", "=EXACT(\"One\", \"Two\")").addExpectedValue("Alpha", "A1", false).run();
  }

  @Test
  public void test_FALSE() {
    runner().addCell("Alpha", "A1", "=FALSE()").addExpectedValue("Alpha", "A1", false).run();
  }

  @Test
  public void test_GTE() {
    runner().addCell("Alpha", "A1", "=GTE(10.4, 0.11111)").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_GT() {
    runner().addCell("Alpha", "A1", "=GT(10.4, 0.11111)").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_IFERROR() {
    runner().addCell("Alpha", "A1", "=IFERROR(#DIV/0!, 1)").addExpectedValue("Alpha", "A1", 1.0).run();
  }

  @Test
  public void test_IFNA() {
    runner().addCell("Alpha", "A1", "=IFNA(#N/A, 1)").addExpectedValue("Alpha", "A1", 1.0).run();
  }

  @Test
  public void test_IF() {
    runner().addCell("Alpha", "A1", "=IF(TRUE, 99, 1)").addExpectedValue("Alpha", "A1", 99.0).run();
  }

  @Test
  public void test_LTE() {
    runner().addCell("Alpha", "A1", "=LTE(10.4, 0.11111)").addExpectedValue("Alpha", "A1", false).run();
  }

  @Test
  public void test_LT() {
    runner().addCell("Alpha", "A1", "=LT(10.4, 0.11111)").addExpectedValue("Alpha", "A1", false).run();
  }

  @Test
  public void test_NE() {
    runner().addCell("Alpha", "A1", "=NE(10.4, 0.11111)").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_NOT() {
    runner().addCell("Alpha", "A1", "=NOT(TRUE())").addExpectedValue("Alpha", "A1", false).run();
  }

  @Test
  public void test_OR() {
    runner().addCell("Alpha", "A1", "=OR(TRUE, 1, 1)").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_TRUE() {
    runner().addCell("Alpha", "A1", "=TRUE()").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_XOR() {
    runner().addCell("Alpha", "A1", "=XOR(TRUE, 1, 1)").addExpectedValue("Alpha", "A1", true).run();
  }
}
