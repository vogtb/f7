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

public class INTTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(INT.SELF.apply(null, 99.9)).isEqualTo(99.0);
    assertThat(INT.SELF.apply(null, -10.4)).isEqualTo(-11.0);
    assertThat(INT.SELF.apply(null, 0.0)).isEqualTo(0.0);
    assertThat(INT.SELF.apply(null, 0.111)).isEqualTo(0.0);
    assertThat(INT.SELF.apply(null, -0.0)).isEqualTo(0.0);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(INT.SELF.apply(null, "9.9")).isEqualTo(9.0);
  }

  @Test
  public void testApply_grid() {
    assertThat(INT.SELF.apply(null,
        Grid.builder().add(0, 0, -4.4).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(-5.0);
  }

  @Test
  public void test_lookup() {
    INT F = new INT(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(9.9);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(9.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(INT.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(INT.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
