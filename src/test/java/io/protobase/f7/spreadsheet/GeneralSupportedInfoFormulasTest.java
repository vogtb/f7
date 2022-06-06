package io.protobase.f7.spreadsheet;

import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralSupportedInfoFormulasTest extends TestExecution {
  @Test
  public void test_ERRORTYPE() {
    runner().addCell("Alpha", "A1", "=ERRORTYPE(#NULL!)").addExpectedValue("Alpha", "A1", 1.0).run();
  }

  @Test
  public void test_ISBLANK() {
    runner().addCell("Alpha", "A1", "=ISBLANK(M88)").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_ISERROR() {
    runner().addCell("Alpha", "A1", "=ISERROR(#N/A)").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_ISERR() {
    runner().addCell("Alpha", "A1", "=ISERR(#NULL!)").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_ISLOGICAL() {
    runner().addCell("Alpha", "A1", "=ISLOGICAL(false)").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_ISNA() {
    runner().addCell("Alpha", "A1", "=ISNA(#N/A)").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_ISNONTEXT() {
    runner().addCell("Alpha", "A1", "=ISNONTEXT(false)").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_ISNUMBER() {
    runner().addCell("Alpha", "A1", "=ISNUMBER(false)").addExpectedValue("Alpha", "A1", false).run();
  }

  @Test
  public void test_ISTEXT() {
    runner().addCell("Alpha", "A1", "=ISTEXT(true)").addExpectedValue("Alpha", "A1", false).run();
  }
}
