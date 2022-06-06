package io.protobase.f7.spreadsheet;

import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralUnaryPlusTest extends TestExecution {
  @Test
  public void test_Number() {
    runner().addCell("Alpha", "A1", "= +0").addExpectedValue("Alpha", "A1", 0.0).run();
    runner().addCell("Alpha", "A1", "= +1").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= +2792").addExpectedValue("Alpha", "A1", 2792.0).run();
    runner().addCell("Alpha", "A1", "= +1.32e10").addExpectedValue("Alpha", "A1", 1.32e10).run();
    runner().addCell("Alpha", "A1", "= +0.2318937").addExpectedValue("Alpha", "A1", 0.2318937).run();
    runner().addCell("Alpha", "A1", "= +10.167531").addExpectedValue("Alpha", "A1", 10.167531).run();
    runner().addCell("Alpha", "A1", "= +-10.167531").addExpectedValue("Alpha", "A1", -10.167531).run();
  }

  @Test
  public void test_String() {
    runner().addCell("Alpha", "A1", "= +\"0\"").addExpectedValue("Alpha", "A1", 0.0).run();
    runner().addCell("Alpha", "A1", "= +\"1\"").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= +\"2792\"").addExpectedValue("Alpha", "A1", 2792.0).run();
    runner().addCell("Alpha", "A1", "= +\"1.32e10\"").addExpectedValue("Alpha", "A1", 1.32e10).run();
    runner().addCell("Alpha", "A1", "= +\"0.2318937\"").addExpectedValue("Alpha", "A1", 0.2318937).run();
    runner().addCell("Alpha", "A1", "= +\"10.167531\"").addExpectedValue("Alpha", "A1", 10.167531).run();
  }

  @Test
  public void test_Boolean() {
    runner().addCell("Alpha", "A1", "= +TRUE").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= +FALSE").addExpectedValue("Alpha", "A1", 0.0).run();
  }

  @Test
  public void test_ArrayLiteral() {
    runner().addCell("Alpha", "A1", "= +{1, 2, 3}").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= +{1, #REF!}").addExpectedValue("Alpha", "A1", 1.0).run();
  }

  @Test
  public void test_MultipleUnaryPlus() {
    runner().addCell("Alpha", "A1", "= +1").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= +++1").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= ++++1").addExpectedValue("Alpha", "A1", 1.0).run();
  }

  @Test
  public void test_UnaryPlusAndUnaryMinus() {
    runner().addCell("Alpha", "A1", "= +-1").addExpectedValue("Alpha", "A1", -1.0).run();
    runner().addCell("Alpha", "A1", "= +-+1").addExpectedValue("Alpha", "A1", -1.0).run();
    runner().addCell("Alpha", "A1", "= +---+-----+-+1").addExpectedValue("Alpha", "A1", -1.0).run();
    runner().addCell("Alpha", "A1", "= +---+----+-+1").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= +---+---+-+1").addExpectedValue("Alpha", "A1", -1.0).run();
    runner().addCell("Alpha", "A1", "= +---+--+-+1").addExpectedValue("Alpha", "A1", 1.0).run();
  }
}