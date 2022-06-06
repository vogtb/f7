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

public class ATANTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(ATAN.SELF.apply(null, 10.0)).isEqualTo(Math.atan(10.0));
    assertThat(ATAN.SELF.apply(null, 128731.2)).isEqualTo(Math.atan(128731.2));
    assertThat(ATAN.SELF.apply(null, 11.11)).isEqualTo(Math.atan(11.11));
    assertThat(ATAN.SELF.apply(null, 0.0)).isEqualTo(Math.atan(0.0));
    assertThat(ATAN.SELF.apply(null, 88281.0)).isEqualTo(Math.atan(88281));
    assertThat(ATAN.SELF.apply(null, 2.0)).isEqualTo(Math.atan(2.0));
    assertThat(ATAN.SELF.apply(null, 4.0)).isEqualTo(Math.atan(4.0));
    assertThat(ATAN.SELF.apply(null, -4.0)).isEqualTo(Math.atan(-4.0));
    assertThat(ATAN.SELF.apply(null, -10124.0)).isEqualTo(Math.atan(-10124.0));
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(ATAN.SELF.apply(null, "10.0")).isEqualTo(Math.atan(10.0));
  }

  @Test
  public void testApply_grid() {
    assertThat(ATAN.SELF.apply(null,
        Grid.builder().add(0, 0, -4.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(Math.atan(-4.0));
  }

  @Test
  public void test_lookup() {
    ATAN F = new ATAN(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(10.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(Math.atan(10.0));
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(ATAN.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(ATAN.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
