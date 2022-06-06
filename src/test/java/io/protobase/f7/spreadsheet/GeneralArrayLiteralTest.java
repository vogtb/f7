package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.RefException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralArrayLiteralTest extends TestExecution {
  @Test
  public void test_EmptyArrayLiteralCausesRefError() {
    runner().addCell("Alpha", "A1", "= {}").addExpectedValue("Alpha", "A1", new RefException()).run();
  }

  @Test
  public void test_SingleValue() {
    runner().addCell("Alpha", "A1", "= {1}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .run();
  }

  @Test
  public void test_SingleValue_Nested() {
    runner().addCell("Alpha", "A1", "= {{{{{{1}}}}}}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .run();
  }

  @Test
  public void test_ColumnWise_ProjectsIntoNextColumns() {
    runner().addCell("Alpha", "A1", "= {1, 2, 3, 4}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .addExpectedValue("Alpha", "B1", 2.0)
        .addExpectedValue("Alpha", "C1", 3.0)
        .addExpectedValue("Alpha", "D1", 4.0)
        .run();
  }

  @Test
  public void test_ColumnWise_Nested() {
    runner().addCell("Alpha", "A1", "= {1, {2, {3, {4}}}}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .addExpectedValue("Alpha", "B1", 2.0)
        .addExpectedValue("Alpha", "C1", 3.0)
        .addExpectedValue("Alpha", "D1", 4.0)
        .run();
  }

  @Test
  public void test_RowWise_ProjectsIntoNextRows() {
    runner().addCell("Alpha", "A1", "= {1; 2; 3; 4}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .addExpectedValue("Alpha", "A2", 2.0)
        .addExpectedValue("Alpha", "A3", 3.0)
        .addExpectedValue("Alpha", "A4", 4.0)
        .run();
    runner()
        .addCell("Alpha", "A1", "= {11;9;5;3;1}")
        .addExpectedValue("Alpha", "A1", 11.0)
        .addExpectedValue("Alpha", "A2", 9.0)
        .addExpectedValue("Alpha", "A3", 5.0)
        .addExpectedValue("Alpha", "A4", 3.0)
        .addExpectedValue("Alpha", "A5", 1.0)
        .run();
  }

  @Test
  public void test_RowWise_Nested() {
    runner().addCell("Alpha", "A1", "= {1; {2; {3; {4}}}}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .addExpectedValue("Alpha", "A2", 2.0)
        .addExpectedValue("Alpha", "A3", 3.0)
        .addExpectedValue("Alpha", "A4", 4.0)
        .run();
    runner()
        .addCell("Alpha", "A1", "= {1;{2;{3;4}};{5}}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .addExpectedValue("Alpha", "A2", 2.0)
        .addExpectedValue("Alpha", "A3", 3.0)
        .addExpectedValue("Alpha", "A4", 4.0)
        .addExpectedValue("Alpha", "A5", 5.0)
        .run();
  }

  @Test
  public void test_ColumnAndRow_Row() {
    runner().addCell("Alpha", "A1", "= {1, 2;3, 4}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .addExpectedValue("Alpha", "B1", 2.0)
        .addExpectedValue("Alpha", "A2", 3.0)
        .addExpectedValue("Alpha", "B2", 4.0)
        .run();
    runner().addCell("Alpha", "A1", "= {1, 2, 3;4, 5, 6}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .addExpectedValue("Alpha", "B1", 2.0)
        .addExpectedValue("Alpha", "C1", 3.0)
        .addExpectedValue("Alpha", "A2", 4.0)
        .addExpectedValue("Alpha", "B2", 5.0)
        .addExpectedValue("Alpha", "C2", 6.0)
        .run();
  }

  @Test
  public void test_ColumnAndRow_Column() {
    runner().addCell("Alpha", "A1", "= {{1; 2},{3; 4}}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .addExpectedValue("Alpha", "B1", 3.0)
        .addExpectedValue("Alpha", "A2", 2.0)
        .addExpectedValue("Alpha", "B2", 4.0)
        .run();
    runner().addCell("Alpha", "A1", "= {{{}; {}},{{}; {}}}")
        .addExpectedValue("Alpha", "A1", new RefException())
        .addExpectedValue("Alpha", "B1", new RefException())
        .addExpectedValue("Alpha", "A2", new RefException())
        .addExpectedValue("Alpha", "B2", new RefException())
        .run();
    runner()
        .addCell("Alpha", "A1", "= {{{0.0, 1.0, 2.0};{0.1, 1.1, 2.1}};{{0.2, 1.2, 2.2};{0.3, 1.3, 2.3}};{{0.4, 1.4, 2.4};{0.5, 1.5, 2.5}}}")
        .addExpectedValue("Alpha", "A1", 0.0)
        .addExpectedValue("Alpha", "A2", 0.1)
        .addExpectedValue("Alpha", "A3", 0.2)
        .addExpectedValue("Alpha", "A4", 0.3)
        .addExpectedValue("Alpha", "A5", 0.4)
        .addExpectedValue("Alpha", "A6", 0.5)
        .addExpectedValue("Alpha", "B1", 1.0)
        .addExpectedValue("Alpha", "B2", 1.1)
        .addExpectedValue("Alpha", "B3", 1.2)
        .addExpectedValue("Alpha", "B4", 1.3)
        .addExpectedValue("Alpha", "B5", 1.4)
        .addExpectedValue("Alpha", "B6", 1.5)
        .addExpectedValue("Alpha", "C1", 2.0)
        .addExpectedValue("Alpha", "C2", 2.1)
        .addExpectedValue("Alpha", "C3", 2.2)
        .addExpectedValue("Alpha", "C4", 2.3)
        .addExpectedValue("Alpha", "C5", 2.4)
        .addExpectedValue("Alpha", "C6", 2.5)
        .run();
  }

  @Test
  public void test_ColumnAndRow_ErrorFromArrayDimensionMismatch() {
    runner().addCell("Alpha", "A1", "= {1,2;3}").addExpectedValue("Alpha", "A1", new ValueException()).run();
    runner().addCell("Alpha", "A1", "= {1,2;3,4,5}").addExpectedValue("Alpha", "A1", new ValueException()).run();
    runner().addCell("Alpha", "A1", "= {11;9;5;3;1,2}")
        .addExpectedValue("Alpha", "A1", new ValueException()).run();
    runner().addCell("Alpha", "A1", "= {{};{};{},{}}")
        .addExpectedValue("Alpha", "A1", new ValueException()).run();
  }
}
