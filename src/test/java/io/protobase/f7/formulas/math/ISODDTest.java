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

public class ISODDTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(ISODD.SELF.apply(null, 10.0)).isEqualTo(false);
    assertThat(ISODD.SELF.apply(null, -10.0)).isEqualTo(false);
    assertThat(ISODD.SELF.apply(null, 0.0)).isEqualTo(false);
    assertThat(ISODD.SELF.apply(null, 1.0)).isEqualTo(true);
    assertThat(ISODD.SELF.apply(null, 2.0)).isEqualTo(false);
    assertThat(ISODD.SELF.apply(null, 3.0)).isEqualTo(true);
    assertThat(ISODD.SELF.apply(null, -218637221.0)).isEqualTo(true);
    assertThat(ISODD.SELF.apply(null, -218637221.99)).isEqualTo(true);
  }

  @Test
  public void testApply_boolean() {
    assertThat(ISODD.SELF.apply(null, true)).isEqualTo(true);
    assertThat(ISODD.SELF.apply(null, false)).isEqualTo(false);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(ISODD.SELF.apply(null, "9.0")).isEqualTo(true);
  }

  @Test
  public void testApply_grid() {
    assertThat(ISODD.SELF.apply(null,
        Grid.builder().add(0, 0, -4.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(false);
  }

  @Test
  public void test_lookup() {
    ISODD F = new ISODD(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(8.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(false);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(ISODD.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(ISODD.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
