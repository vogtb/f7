package io.protobase.f7.formulas.statistical;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.RefException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class AVERAGETest extends TestFormula {
  @Test
  public void testApply_normalAverage() {
    assertThat(AVERAGE.SELF.apply(null, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)).isEqualTo(4.5);
    assertThat(AVERAGE.SELF.apply(null, 1.0, 2.0, 3.0, 4.0, -5.0, -6.0, 7.0, 8.982374928)).isEqualTo(1.872796866);
    assertThat(AVERAGE.SELF.apply(null, 0.0)).isEqualTo(0.0);
    assertThat(AVERAGE.SELF.apply(null, 2984723.99382)).isEqualTo(2984723.99382);
    assertThat(AVERAGE.SELF.apply(null, 2984723e3)).isEqualTo(2984723e3);
  }

  @Test
  public void testApply_grid() {
    assertThat(AVERAGE.SELF.apply(null,
        Grid.builder()
            .add(0, 0, 22.1)
            .add(0, 1, 324.3)
            .add(0, 2, 22.2312223131232)
            .add(0, 3, 442309.4)
            .add(0, 4, 1311.0)
            .build()
    )).isEqualTo(88797.80624446263);
  }

  @Test
  public void testApply_conversion() {
    assertThat(AVERAGE.SELF.apply(null, true, false)).isEqualTo(0.5);
    assertThat(AVERAGE.SELF.apply(null, "192837", false)).isEqualTo(96418.5);
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
    AVERAGE F = new AVERAGE(lookup, collateralLookup);
    when(lookup.apply(D1_TO_D5_RANGE)).thenReturn(grid);
    assertThat(F.apply(A1, D1_TO_D5_RANGE)).isEqualTo(3.0);
    verify(lookup).apply(D1_TO_D5_RANGE);
    verifyNoMoreInteractions(lookup);
  }

  @Test
  public void testApply_errors() {
    assertThat(AVERAGE.SELF.apply(null, 91872.1, new RefException())).isEqualTo(new RefException());
    assertThat(AVERAGE.SELF.apply(null, 91872.1, new ValueException(), new RefException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_errorFromArgumentsMismatch() {
    assertThat(AVERAGE.SELF.apply(null)).isEqualTo(new NAException());
  }
}
