package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.NumException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ACOTHTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(ACOTH.SELF.apply(null, 10.0)).isEqualTo(0.10033534773107562);
    assertThat(ACOTH.SELF.apply(null, 128731.2)).isEqualTo(7.768124588460712E-6);
    assertThat(ACOTH.SELF.apply(null, 11.11)).isEqualTo(0.09025326226631046);
    assertThat(ACOTH.SELF.apply(null, 88281.0)).isEqualTo(1.132746570655777E-5);
    assertThat(ACOTH.SELF.apply(null, 2.0)).isEqualTo(0.5493061443340549);
    assertThat(ACOTH.SELF.apply(null, 4.0)).isEqualTo(0.25541281188299536);
    assertThat(ACOTH.SELF.apply(null, -4.0)).isEqualTo(-0.25541281188299536);
    assertThat(ACOTH.SELF.apply(null, -10124.0)).isEqualTo(-9.87751879940721E-5);
  }

  @Test
  public void testApply_numExceptions() {
    assertThat(ACOTH.SELF.apply(null, 0.0)).isEqualTo(new NumException());
    assertThat(ACOTH.SELF.apply(null, 0.999)).isEqualTo(new NumException());
    assertThat(ACOTH.SELF.apply(null, -0.999)).isEqualTo(new NumException());
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(ACOTH.SELF.apply(null, "88281")).isEqualTo(1.132746570655777E-5);
  }

  @Test
  public void testApply_grid() {
    assertThat(ACOTH.SELF.apply(null,
        Grid.builder().add(0, 0, 88281.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(1.132746570655777E-5);
  }

  @Test
  public void test_lookup() {
    ACOTH F = new ACOTH(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(10.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(0.10033534773107562);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(ACOTH.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(ACOTH.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
