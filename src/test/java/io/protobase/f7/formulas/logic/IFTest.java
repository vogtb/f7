package io.protobase.f7.formulas.logic;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.NumException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class IFTest extends TestFormula {
  @Test
  public void testApply_simple() {
    assertThat(IF.SELF.apply(null, true, true, false)).isEqualTo(true);
    assertThat(IF.SELF.apply(null, false, true, false)).isEqualTo(false);
    assertThat(IF.SELF.apply(null, 10.0, "A", "B")).isEqualTo("A");
    assertThat(IF.SELF.apply(null, -1716.1, "A", "B")).isEqualTo("A");
    assertThat(IF.SELF.apply(null, "TRUE", "A", "B")).isEqualTo("A");
    assertThat(IF.SELF.apply(null, "", "A", "B")).isEqualTo("B");
  }

  @Test
  public void testApply_error() {
    assertThat(IF.SELF.apply(null, true, 10.1, new DivException())).isEqualTo(10.1);
    assertThat(IF.SELF.apply(null, true, new NumException(), new DivException())).isEqualTo(new NumException());
    assertThat(IF.SELF.apply(null, false, new DivException(), 10.11)).isEqualTo(10.11);
    assertThat(IF.SELF.apply(null, true, new DivException(), 10.11)).isEqualTo(new DivException());
  }

  @Test
  public void test_lookup_first() {
    IF F = new IF(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(true);
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn(2.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE, J6_RANGE)).isEqualTo(2.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, G19_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void test_lookup_second() {
    IF F = new IF(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(false);
    when(collateralLookup.apply(A1, J6_RANGE)).thenReturn(100.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE, J6_RANGE)).isEqualTo(100.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, J6_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_grid() {
    Grid<Object> grid = Grid.builder()
        .add(0, 0, 0.0)
        .add(0, 1, 1.0)
        .add(0, 2, 2.0)
        .add(0, 3, 3.0)
        .build();
    assertThat(IF.SELF.apply(null, true, grid, "Not me.")).isEqualTo(grid);
  }

  @Test
  public void test_errorFromArgumentsMismatch() {
    assertThat(IF.SELF.apply(null, 1.0, 2.0)).isEqualTo(new NAException());
    assertThat(IF.SELF.apply(null, 1.0, 2.0, 3.0, 4.0)).isEqualTo(new NAException());
  }
}
