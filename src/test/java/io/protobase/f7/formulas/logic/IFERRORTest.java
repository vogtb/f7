package io.protobase.f7.formulas.logic;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.NumException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class IFERRORTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(IFERROR.SELF.apply(null, "Not error.", "Not returned.")).isEqualTo("Not error.");
    assertThat(IFERROR.SELF.apply(null, 10.1, "Not returned.")).isEqualTo(10.1);
    assertThat(IFERROR.SELF.apply(null, true, "Not returned.")).isEqualTo(true);
    assertThat(IFERROR.SELF.apply(null, false, "Not returned.")).isEqualTo(false);
    assertThat(IFERROR.SELF.apply(null, 99.1, new ValueException())).isEqualTo(99.1);
  }

  @Test
  public void testApply_error() {
    assertThat(IFERROR.SELF.apply(null, new DivException(), "Hello")).isEqualTo("Hello");
    assertThat(IFERROR.SELF.apply(null, new NumException(), "Hello")).isEqualTo("Hello");
    assertThat(IFERROR.SELF.apply(null, new ValueException(), 10.11)).isEqualTo(10.11);
  }

  @Test
  public void test_lookup_first() {
    IFERROR F = new IFERROR(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(2.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(2.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void test_lookup_second() {
    IFERROR F = new IFERROR(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(new DivException());
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn(2.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(2.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, G19_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_gird() {
    Grid<Object> grid = Grid.builder()
        .add(0, 0, new ValueException())
        .add(0, 1, 1.0)
        .add(0, 2, 2.0)
        .add(0, 3, 3.0)
        .build();
    assertThat(IFERROR.SELF.apply(null, grid, "Yes error.")).isEqualTo("Yes error.");
  }

  @Test
  public void test_errorFromArgumentsMismatch() {
    assertThat(IFERROR.SELF.apply(null, 1.0)).isEqualTo(new NAException());
    assertThat(IFERROR.SELF.apply(null, 1.0, 2.0, 3.0)).isEqualTo(new NAException());
  }
}
