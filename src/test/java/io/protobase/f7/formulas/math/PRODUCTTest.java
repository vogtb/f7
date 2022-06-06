package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class PRODUCTTest extends TestFormula {
  @Test
  public void testApply_cleanDivision() {
    assertThat(PRODUCT.SELF.apply(null, 10.0, 10.0)).isEqualTo(100.0);
    assertThat(PRODUCT.SELF.apply(null, 10.0, 2.0)).isEqualTo(20.0);
    assertThat(PRODUCT.SELF.apply(null, 10.0, 3.0)).isEqualTo(30.0);
    assertThat(PRODUCT.SELF.apply(null, 0.0, 1628736813.2)).isEqualTo(0.0);
    assertThat(PRODUCT.SELF.apply(null, 21.22, 2876.111)).isEqualTo(61031.075419999994);
    assertThat(PRODUCT.SELF.apply(null, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)).isEqualTo(40320.0);
    assertThat(PRODUCT.SELF.apply(null, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, -1.0)).isEqualTo(-40320.0);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(PRODUCT.SELF.apply(null, "10.0", "10.0")).isEqualTo(100.0);
    assertThat(PRODUCT.SELF.apply(null, "10.0", "10.0", "No sir!")).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_grid() {
    assertThat(PRODUCT.SELF.apply(null,
        Grid.builder().add(0, 0, 6.0).add(0, 1, 2.3).build(),
        Grid.builder().add(0, 0, 2.0).add(0, 1, 1.11).build()
    )).isEqualTo(30.636);
  }

  @Test
  public void test_lookup() {
    Grid grid = Grid.builder()
        .add(0, 0, 6.0)
        .add(0, 1, 2.3)
        .add(0, 2, 44.1)
        .build();
    PRODUCT F = new PRODUCT(lookup, collateralLookup);
    when(lookup.apply(M22_RANGE)).thenReturn(grid);
    when(lookup.apply(G19_RANGE)).thenReturn(grid);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(370369.61639999994);
    verify(lookup).apply(M22_RANGE);
    verify(lookup).apply(G19_RANGE);
    verifyNoMoreInteractions(lookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(PRODUCT.SELF.apply(null, 10.0, new ValueException())).isEqualTo(new ValueException());
    assertThat(PRODUCT.SELF.apply(null, new ValueException(), 10.0)).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(PRODUCT.SELF.apply(null)).isEqualTo(new NAException());
  }
}
