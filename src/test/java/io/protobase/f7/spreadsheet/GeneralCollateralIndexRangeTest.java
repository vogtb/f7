package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.ValueException;
import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

/**
 * "Collateral Indexing" is what I call it when you're using a range inside a cell that runs parallel column-wise,
 * parallel row-wise, or both, but is in another grid.
 * <p>
 * Parallel Column-Wise:  C4 using A1:A10 results in a collateral index lookup of A4. The 4 is what's used here.
 * Parallel Row-Wise:  C4 using A1:D1 results in a collateral index lookup of C1. The C is what's used here.
 * Both:  C4 using OtherGrid!A1:C10 results in a collateral lookup of OtherGrid!C4. The C and the 4 are important.
 */
public class GeneralCollateralIndexRangeTest extends TestExecution {
  @Test
  public void test_ColumnWise() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "B1", "= A1:A5")
        .addCell("Alpha", "B2", "= A1:A5")
        .addCell("Alpha", "B3", "= A1:A5")
        .addCell("Alpha", "B4", "= A1:A5")
        .addCell("Alpha", "B5", "= A1:A5")
        .addExpectedValue("Alpha", "B1", 1.0)
        .addExpectedValue("Alpha", "B2", 2.0)
        .addExpectedValue("Alpha", "B3", 3.0)
        .addExpectedValue("Alpha", "B4", 4.0)
        .addExpectedEmptyComputedValue("Alpha", "B5")
        .run();
  }

  @Test
  public void test_RowWise() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "B1", "= 2")
        .addCell("Alpha", "C1", "= 3")
        .addCell("Alpha", "D1", "= 4")
        .addCell("Alpha", "A2", "= A1:E1")
        .addCell("Alpha", "B2", "= A1:E1")
        .addCell("Alpha", "C2", "= A1:E1")
        .addCell("Alpha", "D2", "= A1:E1")
        .addCell("Alpha", "E2", "= A1:E1")
        .addExpectedValue("Alpha", "A2", 1.0)
        .addExpectedValue("Alpha", "B2", 2.0)
        .addExpectedValue("Alpha", "C2", 3.0)
        .addExpectedValue("Alpha", "D2", 4.0)
        .addExpectedEmptyComputedValue("Alpha", "E2")
        .run();
  }

  @Test
  public void test_ColumnWiseWithRowOffsetFirst() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "B1", "= A1:A")
        .addCell("Alpha", "B2", "= A1:A")
        .addCell("Alpha", "B3", "= A1:A")
        .addCell("Alpha", "B4", "= A1:A")
        .addCell("Alpha", "B5", "= A1:A")
        .addExpectedValue("Alpha", "B1", 1.0)
        .addExpectedValue("Alpha", "B2", 2.0)
        .addExpectedValue("Alpha", "B3", 3.0)
        .addExpectedValue("Alpha", "B4", 4.0)
        .addExpectedEmptyComputedValue("Alpha", "B5")
        .run();
  }

  @Test
  public void test_ColumnWiseWithRowOffsetLast() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "B1", "= A:A1")
        .addCell("Alpha", "B2", "= A:A1")
        .addCell("Alpha", "B3", "= A:A1")
        .addCell("Alpha", "B4", "= A:A1")
        .addCell("Alpha", "B5", "= A:A1")
        .addExpectedValue("Alpha", "B1", 1.0)
        .addExpectedValue("Alpha", "B2", 2.0)
        .addExpectedValue("Alpha", "B3", 3.0)
        .addExpectedValue("Alpha", "B4", 4.0)
        .addExpectedEmptyComputedValue("Alpha", "B5")
        .run();
  }

  @Test
  public void test_RowWiseWithColumnOffsetFirst() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "B1", "= 2")
        .addCell("Alpha", "C1", "= 3")
        .addCell("Alpha", "D1", "= 4")
        .addCell("Alpha", "A2", "= A1:1")
        .addCell("Alpha", "B2", "= A1:1")
        .addCell("Alpha", "C2", "= A1:1")
        .addCell("Alpha", "D2", "= A1:1")
        .addCell("Alpha", "E2", "= A1:1")
        .addExpectedValue("Alpha", "A2", 1.0)
        .addExpectedValue("Alpha", "B2", 2.0)
        .addExpectedValue("Alpha", "C2", 3.0)
        .addExpectedValue("Alpha", "D2", 4.0)
        .addExpectedEmptyComputedValue("Alpha", "E2")
        .run();
  }

  @Test
  public void test_RowWiseWithColumnOffsetLast() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "B1", "= 2")
        .addCell("Alpha", "C1", "= 3")
        .addCell("Alpha", "D1", "= 4")
        .addCell("Alpha", "A2", "= 1:A1")
        .addCell("Alpha", "B2", "= 1:A1")
        .addCell("Alpha", "C2", "= 1:A1")
        .addCell("Alpha", "D2", "= 1:A1")
        .addCell("Alpha", "E2", "= 1:A1")
        .addExpectedValue("Alpha", "A2", 1.0)
        .addExpectedValue("Alpha", "B2", 2.0)
        .addExpectedValue("Alpha", "C2", 3.0)
        .addExpectedValue("Alpha", "D2", 4.0)
        .addExpectedEmptyComputedValue("Alpha", "E2")
        .run();
  }

  @Test
  public void test_ColumnAndRowWise_Error() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "B1", "= 3")
        .addCell("Alpha", "B2", "= 4")
        .addCell("Alpha", "K5", "= A1:B2")
        .addExpectedValue("Alpha", "K5", new ValueException())
        .run();
  }

  @Test
  public void test_ColumnAndRowWise_CrossGrid() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "B1", "= 1.1")
        .addCell("Alpha", "B2", "= 2.1")
        .addCell("Alpha", "B3", "= 3.1")
        .addCell("Alpha", "B4", "= 4.1")
        .addCell("Beta", "A1", "=Alpha!A1:B5")
        .addCell("Beta", "A2", "=Alpha!A1:B5")
        .addCell("Beta", "A3", "=Alpha!A1:B5")
        .addCell("Beta", "A4", "=Alpha!A1:B5")
        .addCell("Beta", "A5", "=Alpha!A1:B5")
        .addCell("Beta", "B1", "=Alpha!A1:B5")
        .addCell("Beta", "B2", "=Alpha!A1:B5")
        .addCell("Beta", "B3", "=Alpha!A1:B5")
        .addCell("Beta", "B4", "=Alpha!A1:B5")
        .addCell("Beta", "B5", "=Alpha!A1:B5")
        .addExpectedValue("Beta", "A1", 1.0)
        .addExpectedValue("Beta", "A2", 2.0)
        .addExpectedValue("Beta", "A3", 3.0)
        .addExpectedValue("Beta", "A4", 4.0)
        .addExpectedEmptyComputedValue("Beta", "A5")
        .addExpectedValue("Beta", "B1", 1.1)
        .addExpectedValue("Beta", "B2", 2.1)
        .addExpectedValue("Beta", "B3", 3.1)
        .addExpectedValue("Beta", "B4", 4.1)
        .addExpectedEmptyComputedValue("Beta", "B5")
        .run();
  }

  @Test
  public void test_ArrayNotFound() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Beta", "M14", "=Alpha!A1:B5")
        .addExpectedValue("Beta", "M14", new ValueException())
        .run();
  }

  @Test
  public void test_IndexErasureFromArrayLiteral() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "B1", "= 1.1")
        .addCell("Alpha", "B2", "= 2.1")
        .addCell("Alpha", "B3", "= 3.1")
        .addCell("Alpha", "B4", "= 4.1")
        .addCell("Beta", "A1", "= {Alpha!A1:B5}")
        .addExpectedValue("Beta", "A1", 1.0)
        .addExpectedValue("Beta", "A2", 2.0)
        .addExpectedValue("Beta", "A3", 3.0)
        .addExpectedValue("Beta", "A4", 4.0)
        .addExpectedEmptyComputedValue("Beta", "A5")
        .addExpectedValue("Beta", "B1", 1.1)
        .addExpectedValue("Beta", "B2", 2.1)
        .addExpectedValue("Beta", "B3", 3.1)
        .addExpectedValue("Beta", "B4", 4.1)
        .addExpectedEmptyComputedValue("Beta", "B5")
        .run();
  }

  @Test
  public void test_All_RangeOperationsWithIndex() {
    runner()
        .addCell("Alpha", "A1", "= 10")
        .addCell("Alpha", "A2", "= 20")
        .addCell("Alpha", "A3", "= 30")
        .addCell("Alpha", "B1", "= A1:A4")
        .addCell("Alpha", "B2", "= A1:A4")
        .addCell("Alpha", "B3", "= A1:A4")
        .addCell("Alpha", "B4", "= A1:A4")
        .addCell("Alpha", "B5", "= {A1:A4}")
        .addCell("Alpha", "C10", "= {A1:A4 + 5}")
        .addCell("Alpha", "C11", "= {A1 + 5}")
        .addCell("Alpha", "C12", "= {A1:A1 + 5}")
        .addExpectedValue("Alpha", "A1", 10.0)
        .addExpectedValue("Alpha", "A2", 20.0)
        .addExpectedValue("Alpha", "A3", 30.0)
        .addExpectedValue("Alpha", "B1", 10.0)
        .addExpectedValue("Alpha", "B2", 20.0)
        .addExpectedValue("Alpha", "B3", 30.0)
        .addExpectedEmptyComputedValue("Alpha", "B4")
        .addExpectedValue("Alpha", "B5", 10.0)
        .addExpectedValue("Alpha", "B6", 20.0)
        .addExpectedValue("Alpha", "B7", 30.0)
        .addExpectedValue("Alpha", "C10", new ValueException())
        .addExpectedValue("Alpha", "C11", 15.0)
        .addExpectedValue("Alpha", "C12", 15.0)
        .run();
  }
}
