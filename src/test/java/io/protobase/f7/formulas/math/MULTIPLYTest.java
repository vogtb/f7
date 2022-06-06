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

public class MULTIPLYTest extends TestFormula {
  @Test
  public void testApply_cleanDivision() {
    assertThat(MULTIPLY.SELF.apply(null, 10.0, 10.0)).isEqualTo(100.0);
    assertThat(MULTIPLY.SELF.apply(null, 10.0, 2.0)).isEqualTo(20.0);
    assertThat(MULTIPLY.SELF.apply(null, 10.0, 3.0)).isEqualTo(30.0);
    assertThat(MULTIPLY.SELF.apply(null, 0.0, 1628736813.2)).isEqualTo(0.0);
    assertThat(MULTIPLY.SELF.apply(null, 21.22, 2876.111)).isEqualTo(61031.075419999994);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(MULTIPLY.SELF.apply(null, "10.0", "10.0")).isEqualTo(100.0);
  }

  @Test
  public void testApply_grid() {
    assertThat(MULTIPLY.SELF.apply(null,
        Grid.builder().add(0, 0, 6.0).add(0, 1, "Don't mind me.").build(),
        Grid.builder().add(0, 0, 2.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(12.0);
  }

  @Test
  public void test_lookup() {
    MULTIPLY F = new MULTIPLY(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(2.0);
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn(10.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(20.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, G19_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(MULTIPLY.SELF.apply(null, 10.0, new ValueException())).isEqualTo(new ValueException());
    assertThat(MULTIPLY.SELF.apply(null, new ValueException(), 10.0)).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(MULTIPLY.SELF.apply(null, "Too few")).isEqualTo(new NAException());
    assertThat(MULTIPLY.SELF.apply(null, "A", "B", "Too many")).isEqualTo(new NAException());
  }
}
