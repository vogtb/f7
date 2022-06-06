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

public class ISEVENTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(ISEVEN.SELF.apply(null, 10.0)).isEqualTo(true);
    assertThat(ISEVEN.SELF.apply(null, -10.0)).isEqualTo(true);
    assertThat(ISEVEN.SELF.apply(null, 0.0)).isEqualTo(true);
    assertThat(ISEVEN.SELF.apply(null, 1.0)).isEqualTo(false);
    assertThat(ISEVEN.SELF.apply(null, 2.0)).isEqualTo(true);
    assertThat(ISEVEN.SELF.apply(null, 3.0)).isEqualTo(false);
    assertThat(ISEVEN.SELF.apply(null, -218637221.0)).isEqualTo(false);
    assertThat(ISEVEN.SELF.apply(null, -218637221.99)).isEqualTo(false);
  }

  @Test
  public void testApply_boolean() {
    assertThat(ISEVEN.SELF.apply(null, true)).isEqualTo(false);
    assertThat(ISEVEN.SELF.apply(null, false)).isEqualTo(true);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(ISEVEN.SELF.apply(null, "9.0")).isEqualTo(false);
  }

  @Test
  public void testApply_grid() {
    assertThat(ISEVEN.SELF.apply(null,
        Grid.builder().add(0, 0, -4.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(true);
  }

  @Test
  public void test_lookup() {
    ISEVEN F = new ISEVEN(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(8.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(true);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(ISEVEN.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(ISEVEN.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
