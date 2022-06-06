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

public class POWTest extends TestFormula {
  @Test
  public void testApply_clean() {
    assertThat(POW.SELF.apply(null, 2.0, 6.0)).isEqualTo(64.0);
    assertThat(POW.SELF.apply(null, -2.0, 6.0)).isEqualTo(64.0);
    assertThat(POW.SELF.apply(null, -2.0, 5.0)).isEqualTo(-32.0);
    assertThat(POW.SELF.apply(null, 2.0, -5.0)).isEqualTo(0.03125);
    assertThat(POW.SELF.apply(null, 3.0, 1.668132)).isEqualTo(6.25030532353381);
  }

  @Test
  public void testApply_NaN() {
    assertThat(POW.SELF.apply(null, -2.0, -0.05)).isEqualTo(new NumException());
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(POW.SELF.apply(null, "2.0", "10.0")).isEqualTo(1024.0);
  }

  @Test
  public void testApply_grid() {
    assertThat(POW.SELF.apply(null,
        Grid.builder().add(0, 0, 6.0).add(0, 1, "Don't mind me.").build(),
        Grid.builder().add(0, 0, 2.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(36.0);
  }

  @Test
  public void test_lookup() {
    POW F = new POW(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(2.0);
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn(4.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(16.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, G19_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(POW.SELF.apply(null, 10.0, new ValueException())).isEqualTo(new ValueException());
    assertThat(POW.SELF.apply(null, new ValueException(), 10.0)).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(POW.SELF.apply(null, "Too few")).isEqualTo(new NAException());
    assertThat(POW.SELF.apply(null, "A", "B", "Too many")).isEqualTo(new NAException());
  }
}
