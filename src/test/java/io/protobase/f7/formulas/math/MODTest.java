package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class MODTest extends TestFormula {
  @Test
  public void testApply_cleanDivision() {
    assertThat(MOD.SELF.apply(null, 10.0, 10.0)).isEqualTo(0.0);
    assertThat(MOD.SELF.apply(null, 10.0, 2.0)).isEqualTo(0.0);
    assertThat(MOD.SELF.apply(null, 10.0, 3.0)).isEqualTo(1.0);
    assertThat(MOD.SELF.apply(null, 0.0, 1628736813.2)).isEqualTo(0.0);
    assertThat(MOD.SELF.apply(null, 218637221.22, 2876.111)).isEqualTo(1015.2220000082107);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(MOD.SELF.apply(null, "10.0", "10.0")).isEqualTo(0.0);
  }

  @Test
  public void testApply_grid() {
    assertThat(MOD.SELF.apply(null,
        Grid.builder().add(0, 0, 6.0).add(0, 1, "Don't mind me.").build(),
        Grid.builder().add(0, 0, 4.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(2.0);
  }

  @Test
  public void test_lookup() {
    MOD F = new MOD(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(128.0);
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn(10.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(8.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, G19_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(MOD.SELF.apply(null, 10.0, new ValueException())).isEqualTo(new ValueException());
    assertThat(MOD.SELF.apply(null, new ValueException(), 10.0)).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(MOD.SELF.apply(null, "Too few")).isEqualTo(new NAException());
    assertThat(MOD.SELF.apply(null, "A", "B", "Too many")).isEqualTo(new NAException());
  }

  @Test
  public void testApply_errorDivideByZero() {
    assertThat(MOD.SELF.apply(null, 328467.0, 0.0)).isEqualTo(new DivException());
    assertThat(MOD.SELF.apply(null, 0.0, 0.0)).isEqualTo(new DivException());
  }
}
