package io.protobase.f7.formulas.statistical;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class COUNTBLANKTest extends TestFormula {
  @Test
  public void testApply_nonGrid() {
    assertThat(COUNTBLANK.SELF.apply(null, 1.0, 2.0, true, false, "String")).isEqualTo(0.0);
  }

  @Test
  public void testApply_grid() {
    assertThat(COUNTBLANK.SELF.apply(null,
        Grid.builder()
            .add(0, 0, null)
            .add(0, 1, null)
            .add(0, 2, null)
            .add(0, 3, null)
            .add(0, 4, null)
            .add(0, 5, null)
            .add(0, 6, null)
            .build()
    )).isEqualTo(7.0);
    assertThat(COUNTBLANK.SELF.apply(null,
        Grid.builder()
            .add(0, 0, null)
            .add(0, 1, null)
            .add(0, 2, null)
            .add(0, 3, 22.1)
            .add(0, 4, null)
            .add(0, 5, true)
            .add(0, 6, "Filled")
            .build()
    )).isEqualTo(4.0);
    assertThat(COUNTBLANK.SELF.apply(null, Grid.builder().add(0, 0, 1.0).build())).isEqualTo(0.0);
  }

  @Test
  public void test_lookup() {
    Grid grid = Grid.builder()
        .add(0, 0, null)
        .add(0, 1, 2.0)
        .add(0, 2, null)
        .add(0, 3, 4.0)
        .add(0, 4, null)
        .build();
    COUNTBLANK F = new COUNTBLANK(lookup, collateralLookup);
    when(lookup.apply(D1_TO_D5_RANGE)).thenReturn(grid);
    assertThat(F.apply(A1, D1_TO_D5_RANGE)).isEqualTo(3.0);
    verify(lookup).apply(D1_TO_D5_RANGE);
    verifyNoMoreInteractions(lookup);
  }

  @Test
  public void testApply_errorFromArgumentsMismatch() {
    assertThat(COUNTBLANK.SELF.apply(null)).isEqualTo(new NAException());
  }
}
