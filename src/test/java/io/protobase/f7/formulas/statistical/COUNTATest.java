package io.protobase.f7.formulas.statistical;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.RefException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class COUNTATest extends TestFormula {
  @Test
  public void testApply_normal() {
    assertThat(COUNTA.SELF.apply(null, 1.0, 2.0, "Abc", 4.0, "", 6.0, 7.0, true)).isEqualTo(8.0);
    assertThat(COUNTA.SELF.apply(null, 1.0, 2.0, 3.0, 4.0, -5.0, -6.0, 7.0, 8.982374928)).isEqualTo(8.0);
    assertThat(COUNTA.SELF.apply(null, 0.0)).isEqualTo(1.0);
    assertThat(COUNTA.SELF.apply(null, 2984723.99382)).isEqualTo(1.0);
    assertThat(COUNTA.SELF.apply(null, 2984723e3)).isEqualTo(1.0);
    assertThat(COUNTA.SELF.apply(null, 2984723e3, new DivException(), new RefException(), new NAException())).isEqualTo(4.0);
  }

  @Test
  public void testApply_keyDifferenceWithCOUNT() {
    Grid<Object> grid = Grid.builder()
        .add(0, 0, -11.0)
        .add(0, 1, "100") // In both COUNT and COUNTA this is ignored.
        .add(0, 2, -2.0)
        .add(0, 3, true) // In COUNTA this is converted to 1, but in COUNT it is ignored.
        .build();
    assertThat(COUNTA.SELF.apply(null, -1.0, grid, -11.0, "-100")).isEqualTo(7.0);
    assertThat(COUNT.SELF.apply(null, -1.0, grid, -11.0, "-100")).isEqualTo(5.0);
  }

  @Test
  public void testApply_grid() {
    assertThat(COUNTA.SELF.apply(null,
        Grid.builder()
            .add(0, 0, new RefException())
            .build()
    )).isEqualTo(1.0);
    assertThat(COUNTA.SELF.apply(null,
        Grid.builder()
            .add(0, 0, 1.0)
            .add(0, 1, "String")
            .add(0, 2, "Another")
            .add(0, 3, 1.0)
            .build()
    )).isEqualTo(4.0);
  }

  @Test
  public void test_lookup() {
    Grid grid = Grid.builder()
        .add(0, 0, 1.0)
        .add(0, 1, 2.0)
        .add(0, 2, 3.0)
        .add(0, 3, 4.0)
        .add(0, 4, 5.0)
        .build();
    COUNTA F = new COUNTA(lookup, collateralLookup);
    when(lookup.apply(D1_TO_D5_RANGE)).thenReturn(grid);
    assertThat(F.apply(A1, D1_TO_D5_RANGE)).isEqualTo(5.0);
    verify(lookup).apply(D1_TO_D5_RANGE);
    verifyNoMoreInteractions(lookup);
  }

  @Test
  public void testApply_errorFromArgumentsMismatch() {
    assertThat(COUNTA.SELF.apply(null)).isEqualTo(new NAException());
  }
}