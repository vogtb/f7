package io.protobase.f7.models;

import io.protobase.f7.errors.ValueException;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class CellQueryTest {
  private static final CellQuery A1 = CellQuery.builder()
      .grid("Alpha")
      .columnsBetween(0, 0)
      .rowsBetween(0, 0)
      .build();
  private static final CellQuery B1 = CellQuery.builder()
      .grid("Alpha")
      .columnsBetween(1, 1)
      .rowsBetween(0, 0)
      .build();
  private static final CellQuery B2 = CellQuery.builder()
      .grid("Alpha")
      .columnsBetween(1, 1)
      .rowsBetween(1, 1)
      .build();
  private static final CellQuery B99 = CellQuery.builder()
      .grid("Alpha")
      .columnsBetween(1, 1)
      .rowsBetween(98, 98)
      .build();
  private static final CellQuery C14 = CellQuery.builder()
      .grid("Alpha")
      .columnsBetween(2, 2)
      .rowsBetween(13, 13)
      .build();
  private static final CellQuery A1toB1 = CellQuery.builder()
      .grid("Alpha")
      .columnsBetween(0, 1)
      .rowsBetween(0, 0)
      .build();
  private static final CellQuery AtoB = CellQuery.builder()
      .grid("Alpha")
      .columnsBetween(0, 1)
      .openRowsStartingAtZero()
      .build();

  @Test
  public void testIntersects() {
    assertThat(A1.intersects(B1)).isFalse();
    assertThat(B1.intersects(A1)).isFalse();
    assertThat(B2.intersects(B2)).isTrue();
    assertThat(B99.intersects(B2)).isFalse();
    assertThat(A1toB1.intersects(A1)).isTrue();
    assertThat(A1toB1.intersects(B1)).isTrue();
    assertThat(AtoB.intersects(B1)).isTrue();
    assertThat(AtoB.intersects(A1)).isTrue();
    assertThat(AtoB.intersects(B2)).isTrue();
    assertThat(AtoB.intersects(B99)).isTrue();
    assertThat(AtoB.intersects(C14)).isFalse();
  }

  @Test
  public void testToBounded() {
    CellQuery unboundedRows = CellQuery.builder()
        .columnsBetween(0, 1)
        .openRowsStartingAtZero()
        .build();
    CellQuery expectedBoundedRows = CellQuery.builder()
        .columnsBetween(0, 1)
        .rowsBetween(0, 22)
        .build();
    assertThat(unboundedRows.toBounded(10, 22)).isEqualTo(expectedBoundedRows);
    CellQuery unboundedColumns = CellQuery.builder()
        .openColumnsStartingAtZero()
        .rowsBetween(0, 20)
        .build();
    CellQuery expectedBoundedColumns = CellQuery.builder()
        .columnsBetween(0, 44)
        .rowsBetween(0, 20)
        .build();
    assertThat(unboundedColumns.toBounded(44, 30)).isEqualTo(expectedBoundedColumns);
  }

  @Test
  public void testToBounded_unchangedBoundsTooHigh() {
    CellQuery alreadyBounded = CellQuery.builder()
        .columnsBetween(0, 1)
        .rowsBetween(0, 22)
        .build();
    assertThat(alreadyBounded.toBounded(100, 100)).isEqualTo(alreadyBounded);
  }

  @Test
  public void testToBounded_subset() {
    CellQuery alreadyBounded = CellQuery.builder()
        .columnsBetween(0, 44)
        .rowsBetween(0, 22)
        .build();
    CellQuery subset = CellQuery.builder()
        .columnsBetween(0, 10)
        .rowsBetween(0, 12)
        .build();
    assertThat(alreadyBounded.toBounded(10, 12)).isEqualTo(subset);
  }

  @Test
  public void testBuilderExpand() {
    CellQuery expanded = CellQuery.builder(B2)
        .expand(A1)
        .expand(A1toB1)
        .expand(B99)
        .expand(C14)
        .build();
    CellQuery expected = CellQuery.builder()
        .grid("Alpha")
        .columnsBetween(0, 2)
        .rowsBetween(0, 98)
        .build();
    assertThat(expanded).isEqualTo(expected);
  }

  @Test
  public void testBuilderExpand_differentGrids() {
    ValueException exception = null;
    try {
      CellQuery.builder(A1).expand(CellQuery.builder(A1).grid("DifferentGrid").build()).build();
    } catch (ValueException e) {
      exception = e;
    }
    assertThat(exception).isNotNull();
  }
}
