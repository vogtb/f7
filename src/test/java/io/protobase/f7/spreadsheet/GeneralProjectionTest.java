package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.RefException;
import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralProjectionTest extends TestExecution {
  @Test
  public void test_RowSizeOne() {
    runner()
        .addCell("Alpha", "A1", "= {1}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .run();
  }

  @Test
  public void test_RowSizeTwo() {
    runner()
        .addCell("Alpha", "A1", "= {1, 2}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .addExpectedValue("Alpha", "B1", 2.0)
        .run();
  }

  @Test
  public void test_RowSizeN() {
    runner()
        .addCell("Alpha", "A1", "= {1, 2, 3, 4, 5, 6}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .addExpectedValue("Alpha", "B1", 2.0)
        .addExpectedValue("Alpha", "C1", 3.0)
        .addExpectedValue("Alpha", "D1", 4.0)
        .addExpectedValue("Alpha", "E1", 5.0)
        .run();
  }

  @Test
  public void test_ColumnSizeOne() {
    runner()
        .addCell("Alpha", "A1", "= {1}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .run();
  }

  @Test
  public void test_ColumnSizeTwo() {
    runner()
        .addCell("Alpha", "A1", "= {1;2}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .addExpectedValue("Alpha", "A2", 2.0)
        .run();
  }

  @Test
  public void test_ColumnSizeN() {
    runner()
        .addCell("Alpha", "A1", "= {1;2;3;4;5;6}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .addExpectedValue("Alpha", "A2", 2.0)
        .addExpectedValue("Alpha", "A3", 3.0)
        .addExpectedValue("Alpha", "A4", 4.0)
        .addExpectedValue("Alpha", "A5", 5.0)
        .addExpectedValue("Alpha", "A6", 6.0)
        .run();
  }

  @Test
  public void test_ColumnAndRow_FullGrid_ColumnByRow() {
    runner()
        .addCell("Alpha", "A1", "= {{1;2;3},{4;5;6},{7;8;9}}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .addExpectedValue("Alpha", "A2", 2.0)
        .addExpectedValue("Alpha", "A3", 3.0)
        .addExpectedValue("Alpha", "B1", 4.0)
        .addExpectedValue("Alpha", "B2", 5.0)
        .addExpectedValue("Alpha", "B3", 6.0)
        .addExpectedValue("Alpha", "C1", 7.0)
        .addExpectedValue("Alpha", "C2", 8.0)
        .addExpectedValue("Alpha", "c3", 9.0)
        .run();
  }

  @Test
  public void test_ColumnAndRow_FullGrid_RowByColumn() {
    runner()
        .addCell("Alpha", "A1", "= {{1,2,3};{4,5,6};{7,8,9}}")
        .addExpectedValue("Alpha", "A1", 1.0)
        .addExpectedValue("Alpha", "A2", 4.0)
        .addExpectedValue("Alpha", "A3", 7.0)
        .addExpectedValue("Alpha", "B1", 2.0)
        .addExpectedValue("Alpha", "B2", 5.0)
        .addExpectedValue("Alpha", "B3", 8.0)
        .addExpectedValue("Alpha", "C1", 3.0)
        .addExpectedValue("Alpha", "C2", 6.0)
        .addExpectedValue("Alpha", "c3", 9.0)
        .run();
  }

  @Test
  public void test_RangeProjection() {
    runner()
        .addCell("Alpha", "A1", "= 11.1")
        .addCell("Alpha", "A2", "= 22.2")
        .addCell("Alpha", "A3", "= 33.3")
        .addCell("Alpha", "M1", "= {A1:A3}")
        .addExpectedValue("Alpha", "M1", 11.1)
        .addExpectedValue("Alpha", "M2", 22.2)
        .addExpectedValue("Alpha", "M3", 33.3)
        .run();
  }

  @Test
  public void test_RefErrorProjectionIntoExistingValues() {
    runner()
        .addCell("Alpha", "A1", "= {1, 2}")
        .addCell("Alpha", "B1", "= \"Don't tread on me.\"")
        .addExpectedValue("Alpha", "A1", new RefException())
        .addExpectedValue("Alpha", "B1", "Don't tread on me.")
        .run();
    runner()
        .addCell("Alpha", "A1", "= {{1,2,3};{4,5,6};{7,8,9}}")
        .addCell("Alpha", "B2", "= \"Don't tread on me.\"")
        .addExpectedValue("Alpha", "A1", new RefException())
        .addExpectedEmptyValue("Alpha", "A2")
        .addExpectedEmptyValue("Alpha", "A3")
        .addExpectedEmptyValue("Alpha", "B1")
        .addExpectedValue("Alpha", "B2", "Don't tread on me.")
        .addExpectedEmptyValue("Alpha", "B3")
        .addExpectedEmptyValue("Alpha", "C1")
        .addExpectedEmptyValue("Alpha", "C2")
        .addExpectedEmptyValue("Alpha", "c3")
        .run();
  }
}
