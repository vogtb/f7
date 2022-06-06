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

public class ATAN2Test extends TestFormula {
  @Test
  public void testApply() {
    assertThat(ATAN2.SELF.apply(null, 10.0, 4.0)).isEqualTo(Math.atan2(10.0, 4.0));
    assertThat(ATAN2.SELF.apply(null, 128731.2, 4.0)).isEqualTo(Math.atan2(128731.2, 4.0));
    assertThat(ATAN2.SELF.apply(null, 11.11, 4.0)).isEqualTo(Math.atan2(11.11, 4.0));
    assertThat(ATAN2.SELF.apply(null, 0.0, 4.0)).isEqualTo(Math.atan2(0.0, 4.0));
    assertThat(ATAN2.SELF.apply(null, 88281.0, 4.0)).isEqualTo(Math.atan2(88281, 4.0));
    assertThat(ATAN2.SELF.apply(null, 2.0, 4.0)).isEqualTo(Math.atan2(2.0, 4.0));
    assertThat(ATAN2.SELF.apply(null, 4.0, 4.0)).isEqualTo(Math.atan2(4.0, 4.0));
    assertThat(ATAN2.SELF.apply(null, -4.0, 4.0)).isEqualTo(Math.atan2(-4.0, 4.0));
    assertThat(ATAN2.SELF.apply(null, -10124.0, 4.0)).isEqualTo(Math.atan2(-10124.0, 4.0));
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(ATAN2.SELF.apply(null, "10.0", 2.0)).isEqualTo(Math.atan2(10.0, 2));
  }

  @Test
  public void testApply_grid() {
    assertThat(ATAN2.SELF.apply(null,
        Grid.builder().add(0, 0, -4.0).add(0, 1, "Don't mind me.").build(),
        4.0
    )).isEqualTo(Math.atan2(-4.0, 4.0));
  }

  @Test
  public void test_lookup() {
    ATAN2 F = new ATAN2(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(10.0);
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn(4.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(Math.atan2(10.0, 4.0));
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, G19_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(ATAN2.SELF.apply(null, new ValueException(), new DivException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(ATAN2.SELF.apply(null, "A", "B", "C")).isEqualTo(new NAException());
  }
}
