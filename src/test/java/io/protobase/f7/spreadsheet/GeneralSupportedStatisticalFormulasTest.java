package io.protobase.f7.spreadsheet;

import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralSupportedStatisticalFormulasTest extends TestExecution {
  @Test
  public void test_AVEDEV() {
    runner().addCell("Alpha", "A1", "= AVEDEV(1, 3, 5, 7, 11)").addExpectedValue("Alpha", "A1", 2.88).run();
  }

  @Test
  public void test_AVERAGE() {
    runner().addCell("Alpha", "A1", "= AVERAGE(1.0, 2.0, 3.0, 4.4)").addExpectedValue("Alpha", "A1", 2.6).run();
  }

  @Test
  public void test_AVERAGEA() {
    runner()
        .addCell("Alpha", "A1", "= AVERAGEA(1.0, 2.0, 3.0, 4.4, {\"ToZero\"})")
        .addExpectedValue("Alpha", "A1", 2.08)
        .run();
  }

  @Test
  public void test_COUNTA() {
    runner().addCell("Alpha", "A1", "= COUNTA(1.0, 2.0)").addExpectedValue("Alpha", "A1", 2.0).run();
  }

  @Test
  public void test_COUNTBLANK() {
    runner().addCell("Alpha", "C22", "= COUNTBLANK(B1:B10)").addExpectedValue("Alpha", "C22", 10.0).run();
    runner().addCell("Alpha", "A1", "= COUNTBLANK(M1:M10)").addExpectedValue("Alpha", "A1", 0.0).run();
  }

  @Test
  public void test_COUNT() {
    runner().addCell("Alpha", "A1", "= COUNT(1.0, 2.0)").addExpectedValue("Alpha", "A1", 2.0).run();
  }

  @Test
  public void test_MAXA() {
    runner().addCell("Alpha", "A1", "= MAXA(1.0, 2.0, 6.0)").addExpectedValue("Alpha", "A1", 6.0).run();
  }

  @Test
  public void test_MAX() {
    runner().addCell("Alpha", "A1", "= MAX(1.0, 2.0, 6.0)").addExpectedValue("Alpha", "A1", 6.0).run();
  }

  @Test
  public void test_MINA() {
    runner().addCell("Alpha", "A1", "= MINA(1.0, 2.0, 6.0)").addExpectedValue("Alpha", "A1", 1.0).run();
  }

  @Test
  public void test_MIN() {
    runner().addCell("Alpha", "A1", "= MIN(1.0, 2.0, 6.0)").addExpectedValue("Alpha", "A1", 1.0).run();
  }

  @Test
  public void test_STDEV() {
    runner().addCell("Alpha", "A1", "= STDEV(1.0, 2.0, 6.0)").addExpectedValue("Alpha", "A1", 2.6457513110645907).run();
  }
}
