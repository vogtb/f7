package io.protobase.f7.formulas.statistical;

import io.protobase.f7.errors.DivException;
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

public class STDEVTest extends TestFormula {
  private final static Grid GRID = Grid.builder()
      .add(0, 0, 22.1)
      .add(0, 1, 324.3)
      .add(0, 2, 22.22)
      .add(0, 3, 44.0)
      .add(0, 4, 1311.0)
      .build();
  ;

  @Test
  public void testApply_normal() {
    assertThat(STDEV.SELF.apply(null, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)).isEqualTo(2.449489742783178);
    assertThat(STDEV.SELF.apply(null, 132.0, 110.0, 103.0, 132.0, 130.1, 131.972, 111.0, 143.43)).isEqualTo(14.202644037039626);
    assertThat(STDEV.SELF.apply(null, 2.0, 4.0, -3.0, -2.0, -0.1, 1.972, 1.0, 3.43)).isEqualTo(2.4784437369330896);
    assertThat(STDEV.SELF.apply(null, 2.0, 2.0)).isEqualTo(0.0);
    assertThat(STDEV.SELF.apply(null, 2.0)).isEqualTo(new DivException());
  }

  @Test
  public void testApply_failOnNonCoercableValues() {
    assertThat(STDEV.SELF.apply(null, 1.0, 2.0, 3.0, "Bad.")).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_skipNonCoercableNestedValues() {
    assertThat(STDEV.SELF.apply(null, 1.0, 2.0, 3.0, Grid.builder().add(0, 0, "Bad").build())).isEqualTo(1.0);
  }

  @Test
  public void testApply_grid() {
    assertThat(STDEV.SELF.apply(null, GRID)).isEqualTo(555.1208083651702);
  }

  @Test
  public void testApply_conversion() {
    assertThat(STDEV.SELF.apply(null, "0.1", "0.1", "0.1", "0.34")).isEqualTo(0.12000000000000001);
  }

  @Test
  public void test_lookup() {
    STDEV F = new STDEV(lookup, collateralLookup);
    when(lookup.apply(D1_TO_D5_RANGE)).thenReturn(GRID);
    assertThat(F.apply(A1, D1_TO_D5_RANGE)).isEqualTo(555.1208083651702);
    verify(lookup).apply(D1_TO_D5_RANGE);
    verifyNoMoreInteractions(lookup);
  }

  @Test
  public void testApply_errors() {
    assertThat(STDEV.SELF.apply(null, 91872.1, new RefException())).isEqualTo(new RefException());
    assertThat(STDEV.SELF.apply(null, 91872.1, new ValueException(), new RefException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_errorFromArgumentsMismatch() {
    assertThat(STDEV.SELF.apply(null)).isEqualTo(new NAException());
  }
}
