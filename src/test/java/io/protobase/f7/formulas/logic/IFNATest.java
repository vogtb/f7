package io.protobase.f7.formulas.logic;

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

public class IFNATest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(IFNA.SELF.apply(null, "Not NA.", "Not returned.")).isEqualTo("Not NA.");
    assertThat(IFNA.SELF.apply(null, 10.1, "Not returned.")).isEqualTo(10.1);
    assertThat(IFNA.SELF.apply(null, true, "Not returned.")).isEqualTo(true);
    assertThat(IFNA.SELF.apply(null, false, "Not returned.")).isEqualTo(false);
    assertThat(IFNA.SELF.apply(null, 99.1, new ValueException())).isEqualTo(99.1);
  }

  @Test
  public void testApply_error() {
    assertThat(IFNA.SELF.apply(null, new NAException(), "Hello")).isEqualTo("Hello");
    assertThat(IFNA.SELF.apply(null, new NumException(), "Hello")).isEqualTo(new NumException());
    assertThat(IFNA.SELF.apply(null, new ValueException(), 10.11)).isEqualTo(new ValueException());
  }

  @Test
  public void test_lookup_first() {
    IFNA F = new IFNA(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(2.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(2.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void test_lookup_second() {
    IFNA F = new IFNA(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(new NAException());
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn(2.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(2.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, G19_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_gird() {
    Grid<Object> grid = Grid.builder()
        .add(0, 0, new NAException())
        .add(0, 1, 1.0)
        .add(0, 2, 2.0)
        .add(0, 3, 3.0)
        .build();
    assertThat(IFNA.SELF.apply(null, grid, "Yes NA.")).isEqualTo("Yes NA.");
  }

  @Test
  public void test_errorFromArgumentsMismatch() {
    assertThat(IFNA.SELF.apply(null, 1.0)).isEqualTo(new NAException());
    assertThat(IFNA.SELF.apply(null, 1.0, 2.0, 3.0)).isEqualTo(new NAException());
  }
}
