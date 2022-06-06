package io.protobase.f7.spreadsheet;

import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralVariablesTest extends TestExecution {
  @Test
  public void test_True() {
    runner().addCell("Alpha", "A1", "= TRUE").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= true").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= True").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= TrUe").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= trUE").addExpectedValue("Alpha", "A1", true).run();
  }

  @Test
  public void test_False() {
    runner().addCell("Alpha", "A1", "= FALSE").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= false").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= False").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= FaLsE").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= fAlSE").addExpectedValue("Alpha", "A1", false).run();
  }
}
