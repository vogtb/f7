package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.NameException;
import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralFormulaTest extends TestExecution {
  @Test
  public void test_WithoutArguments() {
    runner().addCell("Alpha", "A1", "= TRUE()").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= FALSE()").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= PI()").addExpectedValue("Alpha", "A1", Math.PI).run();
  }

  @Test
  public void test_NumberArguments() {
    runner().addCell("Alpha", "A1", "= ABS(-1)").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= ADD(3, 4)").addExpectedValue("Alpha", "A1", 7.0).run();
    runner().addCell("Alpha", "A1", "= SUM(3, 4, 5, 6, -1)").addExpectedValue("Alpha", "A1", 17.0).run();
  }

  @Test
  public void test_StringArguments() {
    runner().addCell("Alpha", "A1", "= ABS(\"-1\")").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= CONCAT(\"3\", \"4\")").addExpectedValue("Alpha", "A1", "34").run();
    runner().addCell("Alpha", "A1", "= SUM(\"3\", \"4\", \"5\", \"6\")").addExpectedValue("Alpha", "A1", 18.0).run();
  }

  @Test
  public void test_BooleanArguments() {
    runner().addCell("Alpha", "A1", "= ABS(TRUE)").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= CONCAT(TRUE, FALSE)").addExpectedValue("Alpha", "A1", "TRUEFALSE").run();
    runner().addCell("Alpha", "A1", "= SUM(TRUE, TRUE, TRUE)").addExpectedValue("Alpha", "A1", 3.0).run();
  }

  @Test
  public void test_ArrayLiteralArguments() {
    runner().addCell("Alpha", "A1", "= ABS({-1, 2, 3})").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= CONCAT({TRUE}, {22.1, 22})").addExpectedValue("Alpha", "A1", "TRUE22.1").run();
    runner().addCell("Alpha", "A1", "= SUM({TRUE, {TRUE, {TRUE}}})").addExpectedValue("Alpha", "A1", 3.0).run();
  }

  @Test
  public void test_RangeArguments() {
    runner()
        .addCell("Alpha", "A1", "= SUM(B1:B4)")
        .addExpectedValue("Alpha", "A1", 0.0).run();
    runner()
        .addCell("Alpha", "A1", "= SUM(B1:B4)")
        .addCell("Alpha", "B1", "= 3")
        .addCell("Alpha", "B2", "= 4")
        .addCell("Alpha", "B3", "= 5")
        .addCell("Alpha", "B4", "= 6")
        .addExpectedValue("Alpha", "A1", 18.0).run();
  }

  @Test
  public void test_NamedRangeArguments() {
    runner()
        .addNamedRange("Values", "Alpha!B1:B4")
        .addCell("Alpha", "A1", "= SUM(Values)")
        .addCell("Alpha", "B1", "= 3")
        .addCell("Alpha", "B2", "= 4")
        .addCell("Alpha", "B3", "= 5")
        .addCell("Alpha", "B4", "= 6")
        .addExpectedValue("Alpha", "A1", 18.0).run();
  }

  @Test
  public void test_NestedFormulas() {
    runner()
        .addCell("Alpha", "A1", "= SUM(3, 4, 5, CONCAT(10, 10))")
        .addExpectedValue("Alpha", "A1", 1022.0).run();
  }

  @Test
  public void test_FormulaNotFound() {
    runner()
        .addCell("Alpha", "A1", "= NOTFOUND(10, 20)")
        .addExpectedValue("Alpha", "A1", new NameException()).run();
  }
}
