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

public class EVENTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(EVEN.SELF.apply(null, 2.0)).isEqualTo(2.0);
    assertThat(EVEN.SELF.apply(null, 1.0)).isEqualTo(2.0);
    assertThat(EVEN.SELF.apply(null, 1.1)).isEqualTo(2.0);
    assertThat(EVEN.SELF.apply(null, 2.1)).isEqualTo(4.0);
    assertThat(EVEN.SELF.apply(null, 3.0)).isEqualTo(4.0);
    assertThat(EVEN.SELF.apply(null, 0.0)).isEqualTo(0.0);
    assertThat(EVEN.SELF.apply(null, -0.0)).isEqualTo(0.0);
    assertThat(EVEN.SELF.apply(null, 0.1298738912)).isEqualTo(2.0);
    assertThat(EVEN.SELF.apply(null, 1.1298738912)).isEqualTo(2.0);
    assertThat(EVEN.SELF.apply(null, 2.1298738912)).isEqualTo(4.0);
    assertThat(EVEN.SELF.apply(null, true)).isEqualTo(2.0);
    assertThat(EVEN.SELF.apply(null, false)).isEqualTo(0.0);
  }

  @Test
  public void testApply_negativeValue() {
    assertThat(EVEN.SELF.apply(null, -2.0)).isEqualTo(-2.0);
    assertThat(EVEN.SELF.apply(null, -1.0)).isEqualTo(-2.0);
    assertThat(EVEN.SELF.apply(null, -1.1)).isEqualTo(-2.0);
    assertThat(EVEN.SELF.apply(null, -2.1)).isEqualTo(-4.0);
    assertThat(EVEN.SELF.apply(null, -3.0)).isEqualTo(-4.0);
    assertThat(EVEN.SELF.apply(null, 0.0)).isEqualTo(0.0);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(EVEN.SELF.apply(null, "9.9")).isEqualTo(10.0);
  }

  @Test
  public void test_lookup() {
    EVEN F = new EVEN(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(9.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(10.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_grid() {
    assertThat(EVEN.SELF.apply(null,
        Grid.builder().add(0, 0, -4.4).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(-6.0);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(EVEN.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(EVEN.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
