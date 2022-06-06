package io.protobase.f7.formulas.statistical;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class MINATest extends TestFormula {

  @Test
  public void testApply_normal() {
    assertThat(MINA.SELF.apply(null, 1.0, 2.0, 3.0)).isEqualTo(1.0);
    assertThat(MINA.SELF.apply(null, 3.0, 1.0, 2.0)).isEqualTo(1.0);
    assertThat(MINA.SELF.apply(null, 3.0, 2.0, 1.0)).isEqualTo(1.0);
    assertThat(MINA.SELF.apply(null, 0.0)).isEqualTo(0.0);
    assertThat(MINA.SELF.apply(null, 0.0, -1000.0, -0.0111)).isEqualTo(-1000.0);
    assertThat(MINA.SELF.apply(null, 261822.0, 991741987.1, 1991.0e2)).isEqualTo(1991.0e2);
  }

  @Test
  public void testApply_keyDifferenceWithMINA() {
    Grid<Object> grid = Grid.builder()
        .add(0, 0, 11.0)
        .add(0, 1, "100") // In both MIN and MINA this is converted to 0.
        .add(0, 2, 2.0)
        .add(0, 3, true) // In MINA this is converted to 1, but in MIN it is ignored.
        .build();
    assertThat(MINA.SELF.apply(null, 2.0, grid, 11.0, "200")).isEqualTo(0.0);
    assertThat(MIN.SELF.apply(null, 2.0, grid, 11.0, "200")).isEqualTo(2.0);
  }

  @Test
  public void testApply_numeric() {
    assertThat(MINA.SELF.apply(null, 1.0, 2.0, "0.0")).isEqualTo(0.0);
    assertThat(MINA.SELF.apply(null, 2.0, true)).isEqualTo(1.0);
    assertThat(MINA.SELF.apply(null, 2.0, false)).isEqualTo(0.0);
  }

  @Test
  public void testApply_grid() {
    // =MINA({1, "Bad"}, 44.0")
    assertThat(MINA.SELF.apply(null, Grid.builder().add(0, 0, 1.0).add(0, 1, "ToZero").build(), 44.0)).isEqualTo(0.0);
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
    MINA F = new MINA(lookup, collateralLookup);
    when(lookup.apply(D1_TO_D5_RANGE)).thenReturn(grid);
    assertThat(F.apply(A1, D1_TO_D5_RANGE)).isEqualTo(1.0);
    verify(lookup).apply(D1_TO_D5_RANGE);
    verifyNoMoreInteractions(lookup);
  }

  @Test
  public void testApply_nonCoercableValuesInGridShouldBeIgnoreButNotInArgs() {
    // =MINA({1, "Bad"}, "Bad")
    assertThat(MINA.SELF.apply(null, Grid.builder().add(0, 0, 1.0).add(0, 1, "ToZero").build(), "Bad")).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_errorFromArgumentsMismatch() {
    assertThat(MINA.SELF.apply(null)).isEqualTo(new NAException());
  }
}