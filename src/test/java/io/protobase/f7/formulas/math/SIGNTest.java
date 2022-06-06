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

public class SIGNTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(SIGN.SELF.apply(null, 10.0)).isEqualTo(1.0);
    assertThat(SIGN.SELF.apply(null, -10.0)).isEqualTo(-1.0);
    assertThat(SIGN.SELF.apply(null, 0.0)).isEqualTo(0.0);
    assertThat(SIGN.SELF.apply(null, -0.0)).isEqualTo(0.0);
    assertThat(SIGN.SELF.apply(null, -218637221.22)).isEqualTo(-1.0);
    assertThat(SIGN.SELF.apply(null, true)).isEqualTo(1.0);
    assertThat(SIGN.SELF.apply(null, false)).isEqualTo(0.0);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(SIGN.SELF.apply(null, "10.0")).isEqualTo(1.0);
  }

  @Test
  public void testApply_grid() {
    assertThat(SIGN.SELF.apply(null,
        Grid.builder().add(0, 0, -4.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(-1.0);
  }

  @Test
  public void test_lookup() {
    SIGN F = new SIGN(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(10.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(1.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(SIGN.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(SIGN.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
