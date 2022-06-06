package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.RefException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralRangeTest extends TestExecution {
  @Test
  public void test_SingleCellRange() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "Z99", "= SUM(A1)")
        .addExpectedValue("Alpha", "Z99", 1.0).run();
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "Z99", "= A1")
        .addExpectedValue("Alpha", "Z99", 1.0).run();
    runner()
        .addCell("Alpha", "Z99", "= -A1")
        .addExpectedValue("Alpha", "Z99", 0.0).run();
    runner()
        .addCell("Alpha", "Z99", "= A1")
        .addExpectedEmptyComputedValue("Alpha", "Z99").run();
  }

  @Test
  public void test_GridSingleCellRange() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Beta", "Z99", "= SUM(Alpha!A1)")
        .addExpectedValue("Beta", "Z99", 1.0).run();
  }

  @Test
  public void test_BiCellRange() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "Z99", "= SUM(A1:A3)")
        .addExpectedValue("Alpha", "Z99", 6.0).run();
  }

  @Test
  public void test_GridBiCellRange() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Beta", "Z99", "= SUM(Alpha!A1:A3)")
        .addExpectedValue("Beta", "Z99", 6.0).run();
  }

  @Test
  public void test_MultiCellRange() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "A5", "= 5")
        .addCell("Alpha", "A6", "= 6")
        .addCell("Alpha", "B1", "= 1.1")
        .addCell("Alpha", "B2", "= 2.1")
        .addCell("Alpha", "B3", "= 3.1")
        .addCell("Alpha", "B4", "= 4.1")
        .addCell("Alpha", "B5", "= 5.1")
        .addCell("Alpha", "B6", "= 6.1")
        .addCell("Alpha", "C1", "= 1.2")
        .addCell("Alpha", "C2", "= 2.2")
        .addCell("Alpha", "C3", "= 3.2")
        .addCell("Alpha", "C4", "= 4.2")
        .addCell("Alpha", "C5", "= 5.2")
        .addCell("Alpha", "C6", "= 6.2")
        .addCell("Alpha", "Z99", "= SUM(A1:A2:B5:C6)")
        .addExpectedValue("Alpha", "Z99", 64.80000000000001).run();
  }

  @Test
  public void test_MultiCellRange_OutOfOrder() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "A5", "= 5")
        .addCell("Alpha", "A6", "= 6")
        .addCell("Alpha", "B1", "= 1.1")
        .addCell("Alpha", "B2", "= 2.1")
        .addCell("Alpha", "B3", "= 3.1")
        .addCell("Alpha", "B4", "= 4.1")
        .addCell("Alpha", "B5", "= 5.1")
        .addCell("Alpha", "B6", "= 6.1")
        .addCell("Alpha", "C1", "= 1.2")
        .addCell("Alpha", "C2", "= 2.2")
        .addCell("Alpha", "C3", "= 3.2")
        .addCell("Alpha", "C4", "= 4.2")
        .addCell("Alpha", "C5", "= 5.2")
        .addCell("Alpha", "C6", "= 6.2")
        .addCell("Alpha", "Z99", "= SUM(A2:A1:C6:B5)")
        .addExpectedValue("Alpha", "Z99", 64.80000000000001).run();
  }

  @Test
  public void test_GridMultiCellRange() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "A5", "= 5")
        .addCell("Alpha", "A6", "= 6")
        .addCell("Alpha", "B1", "= 1.1")
        .addCell("Alpha", "B2", "= 2.1")
        .addCell("Alpha", "B3", "= 3.1")
        .addCell("Alpha", "B4", "= 4.1")
        .addCell("Alpha", "B5", "= 5.1")
        .addCell("Alpha", "B6", "= 6.1")
        .addCell("Alpha", "C1", "= 1.2")
        .addCell("Alpha", "C2", "= 2.2")
        .addCell("Alpha", "C3", "= 3.2")
        .addCell("Alpha", "C4", "= 4.2")
        .addCell("Alpha", "C5", "= 5.2")
        .addCell("Alpha", "C6", "= 6.2")
        .addCell("Beta", "Z99", "= SUM(Alpha!A1:A2:Alpha!B5:C6)")
        .addCell("Beta", "Z100", "= SUM(Alpha!A1:Alpha!A2:Alpha!B5:Alpha!C6)")
        .addExpectedValue("Beta", "Z99", 64.80000000000001)
        .addExpectedValue("Beta", "Z100", 64.80000000000001)
        .run();
  }

  @Test
  public void test_GridMultiCellRange_OutOfOrder() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "A5", "= 5")
        .addCell("Alpha", "A6", "= 6")
        .addCell("Alpha", "B1", "= 1.1")
        .addCell("Alpha", "B2", "= 2.1")
        .addCell("Alpha", "B3", "= 3.1")
        .addCell("Alpha", "B4", "= 4.1")
        .addCell("Alpha", "B5", "= 5.1")
        .addCell("Alpha", "B6", "= 6.1")
        .addCell("Alpha", "C1", "= 1.2")
        .addCell("Alpha", "C2", "= 2.2")
        .addCell("Alpha", "C3", "= 3.2")
        .addCell("Alpha", "C4", "= 4.2")
        .addCell("Alpha", "C5", "= 5.2")
        .addCell("Alpha", "C6", "= 6.2")
        .addCell("Beta", "Z99", "= SUM(Alpha!A2:A1:Alpha!C6:B5)")
        .addCell("Beta", "Z100", "= SUM(Alpha!A2:Alpha!A1:Alpha!C6:Alpha!B5)")
        .addExpectedValue("Beta", "Z99", 64.80000000000001)
        .addExpectedValue("Beta", "Z100", 64.80000000000001)
        .run();
  }

  @Test
  public void test_ErrorFromCrossGridMultiCellRange() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Beta", "Z99", "= SUM(Alpha!A2:A1:Beta!C6:B5)")
        .addCell("Beta", "Z100", "= SUM(Alpha!A2:Beta!A1:Alpha!C6:Beta!B5)")
        .addExpectedValue("Beta", "Z99", new ValueException())
        .addExpectedValue("Beta", "Z100", new ValueException())
        .run();
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Beta", "Z99", "= SUM(Alpha!A2:A1:Alpha!C6:B5)")
        .addExpectedValue("Beta", "Z99", 1.0)
        .run();
  }

  @Test
  public void test_ErrorFromUnresolvedGridName() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Beta", "Z99", "= SUM(NotFound!A1)")
        .addExpectedValue("Beta", "Z99", new RefException())
        .run();
  }

  @Test
  public void test_PartialBlankRange() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "A5", "= 5")
        .addCell("Alpha", "A6", "= 6")
        .addCell("Alpha", "C1", "= 1.2")
        .addCell("Alpha", "C2", "= 2.2")
        .addCell("Alpha", "C3", "= 3.2")
        .addCell("Alpha", "C4", "= 4.2")
        .addCell("Alpha", "C5", "= 5.2")
        .addCell("Alpha", "C6", "= 6.2")
        .addCell("Alpha", "Z99", "= SUM(A1:C6)")
        .addExpectedValue("Alpha", "Z99", 43.2)
        .run();
  }

  @Test
  public void test_FullBlankRange() {
    runner()
        .addCell("Alpha", "Z99", "= SUM(A1:C6)")
        .addExpectedValue("Alpha", "Z99", 0.0)
        .run();
  }

  @Test
  public void test_ColumnWiseRange() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "A5", "= 5")
        .addCell("Alpha", "A6", "= 6")
        .addCell("Alpha", "B1", "= 1.1")
        .addCell("Alpha", "B2", "= 2.1")
        .addCell("Alpha", "B3", "= 3.1")
        .addCell("Alpha", "B4", "= 4.1")
        .addCell("Alpha", "B5", "= 5.1")
        .addCell("Alpha", "B6", "= 6.1")
        .addCell("Alpha", "C1", "= 1.2")
        .addCell("Alpha", "C2", "= 2.2")
        .addCell("Alpha", "C3", "= 3.2")
        .addCell("Alpha", "C4", "= 4.2")
        .addCell("Alpha", "C5", "= 5.2")
        .addCell("Alpha", "C6", "= 6.2")
        .addCell("Alpha", "Z99", "= SUM(A:C)")
        .addExpectedValue("Alpha", "Z99", 64.80000000000001)
        .run();
  }

  @Test
  public void test_ColumnWiseWithRowOffsetFirst() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "A5", "= 5")
        .addCell("Alpha", "A6", "= 6")
        .addCell("Alpha", "B1", "= 1.1")
        .addCell("Alpha", "B2", "= 2.1")
        .addCell("Alpha", "B3", "= 3.1")
        .addCell("Alpha", "B4", "= 4.1")
        .addCell("Alpha", "B5", "= 5.1")
        .addCell("Alpha", "B6", "= 6.1")
        .addCell("Alpha", "C1", "= 1.2")
        .addCell("Alpha", "C2", "= 2.2")
        .addCell("Alpha", "C3", "= 3.2")
        .addCell("Alpha", "C4", "= 4.2")
        .addCell("Alpha", "C5", "= 5.2")
        .addCell("Alpha", "C6", "= 6.2")
        .addCell("Alpha", "Z99", "= SUM(A3:C)") // TODO/HACK: This is a feature only available in G-Sheets. Excel doesn't allow.
        .addExpectedValue("Alpha", "Z99", 55.2)
        .run();
  }

  @Test
  public void test_ColumnWiseWithRowOffsetLast() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "A5", "= 5")
        .addCell("Alpha", "A6", "= 6")
        .addCell("Alpha", "B1", "= 1.1")
        .addCell("Alpha", "B2", "= 2.1")
        .addCell("Alpha", "B3", "= 3.1")
        .addCell("Alpha", "B4", "= 4.1")
        .addCell("Alpha", "B5", "= 5.1")
        .addCell("Alpha", "B6", "= 6.1")
        .addCell("Alpha", "C1", "= 1.2")
        .addCell("Alpha", "C2", "= 2.2")
        .addCell("Alpha", "C3", "= 3.2")
        .addCell("Alpha", "C4", "= 4.2")
        .addCell("Alpha", "C5", "= 5.2")
        .addCell("Alpha", "C6", "= 6.2")
        .addCell("Alpha", "Z99", "= SUM(A:C3)") // TODO/HACK: This is a feature only available in G-Sheets. Excel doesn't allow.
        .addExpectedValue("Alpha", "Z99", 55.2)
        .run();
  }

  @Test
  public void test_RowWise() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "A5", "= 5")
        .addCell("Alpha", "A6", "= 6")
        .addCell("Alpha", "B1", "= 1.1")
        .addCell("Alpha", "B2", "= 2.1")
        .addCell("Alpha", "B3", "= 3.1")
        .addCell("Alpha", "B4", "= 4.1")
        .addCell("Alpha", "B5", "= 5.1")
        .addCell("Alpha", "B6", "= 6.1")
        .addCell("Alpha", "C1", "= 1.2")
        .addCell("Alpha", "C2", "= 2.2")
        .addCell("Alpha", "C3", "= 3.2")
        .addCell("Alpha", "C4", "= 4.2")
        .addCell("Alpha", "C5", "= 5.2")
        .addCell("Alpha", "C6", "= 6.2")
        .addCell("Alpha", "Z99", "= SUM(1:6)") // TODO/HACK: This is a feature only available in G-Sheets. Excel doesn't allow.
        .addExpectedValue("Alpha", "Z99", 64.80000000000001)
        .run();
  }

  @Test
  public void test_RowWiseWithColumnOffsetFirst() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "A5", "= 5")
        .addCell("Alpha", "A6", "= 6")
        .addCell("Alpha", "B1", "= 1.1")
        .addCell("Alpha", "B2", "= 2.1")
        .addCell("Alpha", "B3", "= 3.1")
        .addCell("Alpha", "B4", "= 4.1")
        .addCell("Alpha", "B5", "= 5.1")
        .addCell("Alpha", "B6", "= 6.1")
        .addCell("Alpha", "C1", "= 1.2")
        .addCell("Alpha", "C2", "= 2.2")
        .addCell("Alpha", "C3", "= 3.2")
        .addCell("Alpha", "C4", "= 4.2")
        .addCell("Alpha", "C5", "= 5.2")
        .addCell("Alpha", "C6", "= 6.2")
        .addCell("Alpha", "Z99", "= SUM(B1:6)") // TODO/HACK: This is a feature only available in G-Sheets. Excel doesn't allow.
        .addExpectedValue("Alpha", "Z99", 43.8)
        .run();
  }

  @Test
  public void test_RowWiseWithColumnOffsetLast() {
    runner()
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "A5", "= 5")
        .addCell("Alpha", "A6", "= 6")
        .addCell("Alpha", "B1", "= 1.1")
        .addCell("Alpha", "B2", "= 2.1")
        .addCell("Alpha", "B3", "= 3.1")
        .addCell("Alpha", "B4", "= 4.1")
        .addCell("Alpha", "B5", "= 5.1")
        .addCell("Alpha", "B6", "= 6.1")
        .addCell("Alpha", "C1", "= 1.2")
        .addCell("Alpha", "C2", "= 2.2")
        .addCell("Alpha", "C3", "= 3.2")
        .addCell("Alpha", "C4", "= 4.2")
        .addCell("Alpha", "C5", "= 5.2")
        .addCell("Alpha", "C6", "= 6.2")
        .addCell("Alpha", "Z99", "= SUM(1:B6)") // TODO/HACK: This is a feature only available in G-Sheets. Excel doesn't allow.
        .addExpectedValue("Alpha", "Z99", 43.8)
        .run();
  }
}
