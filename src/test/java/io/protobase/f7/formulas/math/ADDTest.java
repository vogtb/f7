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

public class ADDTest extends TestFormula {
  @Test
  public void testApply_cleanDivision() {
    assertThat(ADD.SELF.apply(null, 10.0, 10.0)).isEqualTo(20.0);
    assertThat(ADD.SELF.apply(null, 10.0, 2.0)).isEqualTo(12.0);
    assertThat(ADD.SELF.apply(null, 0.0, 1628736813.2)).isEqualTo(1628736813.2);
    assertThat(ADD.SELF.apply(null, 218637221.22, 2876.111)).isEqualTo(218640097.331);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(ADD.SELF.apply(null, "10.0", "10.0")).isEqualTo(20.0);
    assertThat(ADD.SELF.apply(null, "10.0", "2.0")).isEqualTo(12.0);
    assertThat(ADD.SELF.apply(null, "10.0", "3.0")).isEqualTo(13.0);
  }

  @Test
  public void testApply_grid() {
    assertThat(ADD.SELF.apply(null,
        Grid.builder().add(0, 0, 6.0).add(0, 1, "Don't mind me.").build(),
        Grid.builder().add(0, 0, 2.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(8.0);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(ADD.SELF.apply(null, 10.0, new ValueException())).isEqualTo(new ValueException());
    assertThat(ADD.SELF.apply(null, new ValueException(), 10.0)).isEqualTo(new ValueException());
  }

  @Test
  public void test_lookup() {
    ADD F = new ADD(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(10.0);
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn(10.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(20.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, G19_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(ADD.SELF.apply(null, "Too few")).isEqualTo(new NAException());
    assertThat(ADD.SELF.apply(null, "A", "B", "Too many")).isEqualTo(new NAException());
  }
}
