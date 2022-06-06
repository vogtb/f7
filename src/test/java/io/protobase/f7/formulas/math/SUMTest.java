package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.NullException;
import io.protobase.f7.errors.RefException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class SUMTest extends TestFormula {
  private static final Grid GRID = Grid.builder()
      .add(0, 0, 22.1)
      .add(0, 1, 324.3)
      .add(0, 2, 22.2312223131232)
      .add(0, 3, 442309.4)
      .add(0, 4, 131289731.0)
      .build();

  @Test
  public void testApply_normalAddition() {
    assertThat(SUM.SELF.apply(null, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)).isEqualTo(36.0);
    assertThat(SUM.SELF.apply(null, 0.0)).isEqualTo(0.0);
    assertThat(SUM.SELF.apply(null, 2984723.99382)).isEqualTo(2984723.99382);
    assertThat(SUM.SELF.apply(null, 2984723e3)).isEqualTo(2984723e3);
  }

  @Test
  public void testApply_conversion() {
    assertThat(SUM.SELF.apply(null, "1.0", "2.0", "3.0", "4.0", "5.0", "6.0", "7.0", "8")).isEqualTo(36.0);
    assertThat(SUM.SELF.apply(null, true, false, true, false, true)).isEqualTo(3.0);
    assertThat(SUM.SELF.apply(null,
        GRID
    )).isEqualTo(1.3173240903122231E8);
  }

  @Test
  public void testApply_error() {
    assertThat(SUM.SELF.apply(null, 1.0, new RefException())).isEqualTo(new RefException());
    assertThat(SUM.SELF.apply(null, 1.0, new NullException(), new RefException())).isEqualTo(new NullException());
    assertThat(SUM.SELF.apply(null,
        Grid.builder()
            .add(0, 0, 22.1)
            .add(0, 1, 324.3)
            .add(0, 2, new RefException())
            .add(0, 3, 442309.4)
            .add(0, 4, 131289731.0)
            .build()
    )).isEqualTo(new RefException());
  }

  @Test
  public void test_lookup() {
    SUM F = new SUM(lookup, collateralLookup);
    when(lookup.apply(M22_RANGE)).thenReturn(GRID);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(1.3173240903122231E8);
    verify(lookup).apply(M22_RANGE);
    verifyNoMoreInteractions(lookup);
  }

  @Test
  public void testApply_errorFromArgumentsMismatch() {
    assertThat(SUM.SELF.apply(null)).isEqualTo(new NAException());
  }
}