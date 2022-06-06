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

public class DIVIDETest extends TestFormula {
  @Test
  public void testApply_cleanDivision() {
    assertThat(DIVIDE.SELF.apply(null, 10.0, 10.0)).isEqualTo(1.0);
    assertThat(DIVIDE.SELF.apply(null, 10.0, 2.0)).isEqualTo(5.0);
    assertThat(DIVIDE.SELF.apply(null, 10.0, 3.0)).isEqualTo(3.3333333333333335);
    assertThat(DIVIDE.SELF.apply(null, 0.0, 1628736813.2)).isEqualTo(0.0);
    assertThat(DIVIDE.SELF.apply(null, 218637221.22, 2876.111)).isEqualTo(76018.35298429025);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(DIVIDE.SELF.apply(null, "10.0", "10.0")).isEqualTo(1.0);
    assertThat(DIVIDE.SELF.apply(null, "10.0", "2.0")).isEqualTo(5.0);
    assertThat(DIVIDE.SELF.apply(null, "10.0", "3.0")).isEqualTo(3.3333333333333335);
  }

  @Test
  public void testApply_grid() {
    assertThat(DIVIDE.SELF.apply(null,
        Grid.builder().add(0, 0, 6.0).add(0, 1, "Don't mind me.").build(),
        Grid.builder().add(0, 0, 2.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(3.0);
  }

  @Test
  public void test_lookup() {
    DIVIDE F = new DIVIDE(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(10.0);
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn(2.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(5.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, G19_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(DIVIDE.SELF.apply(null, 10.0, new ValueException())).isEqualTo(new ValueException());
    assertThat(DIVIDE.SELF.apply(null, new ValueException(), 10.0)).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(DIVIDE.SELF.apply(null, "Too few")).isEqualTo(new NAException());
    assertThat(DIVIDE.SELF.apply(null, "A", "B", "Too many")).isEqualTo(new NAException());
  }

  @Test
  public void testApply_errorDivideByZero() {
    assertThat(DIVIDE.SELF.apply(null, 328467.0, 0.0)).isEqualTo(new DivException());
    assertThat(DIVIDE.SELF.apply(null, 0.0, 0.0)).isEqualTo(new DivException());
  }
}
