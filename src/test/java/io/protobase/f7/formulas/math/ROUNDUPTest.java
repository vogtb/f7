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

public class ROUNDUPTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(ROUNDUP.SELF.apply(null, 10.0)).isEqualTo(10.0);
    assertThat(ROUNDUP.SELF.apply(null, -10.0)).isEqualTo(-10.0);
    assertThat(ROUNDUP.SELF.apply(null, 0.0)).isEqualTo(0.0);
  }

  @Test
  public void testApply_ROUNDUPUPingFullInteger() {
    assertThat(ROUNDUP.SELF.apply(null, 3.218639128)).isEqualTo(4.0);
    assertThat(ROUNDUP.SELF.apply(null, 7.99)).isEqualTo(8.0);
    assertThat(ROUNDUP.SELF.apply(null, 7.0000000001)).isEqualTo(8.0);
    assertThat(ROUNDUP.SELF.apply(null, -4.444444)).isEqualTo(-5.0);
  }

  @Test
  public void testApply_toPlace() {
    assertThat(ROUNDUP.SELF.apply(null, 3.218639128, 4.0)).isEqualTo(3.2187);
    assertThat(ROUNDUP.SELF.apply(null, 3.218639128, 5.0)).isEqualTo(3.21864);
    assertThat(ROUNDUP.SELF.apply(null, 9.12, 0.0)).isEqualTo(10.0);
    assertThat(ROUNDUP.SELF.apply(null, 9.12, 1.0)).isEqualTo(9.2);
    assertThat(ROUNDUP.SELF.apply(null, 9.12, 2.0)).isEqualTo(9.12);
    assertThat(ROUNDUP.SELF.apply(null, 9.12, 3.0)).isEqualTo(9.12);
    assertThat(ROUNDUP.SELF.apply(null, 9.12, 8.0)).isEqualTo(9.12);
  }

  @Test
  public void testApply_toNegativePlace() {
    assertThat(ROUNDUP.SELF.apply(null, 3341.218639128, -4.0)).isEqualTo(10000.0);
    assertThat(ROUNDUP.SELF.apply(null, 3341.218639128, -3.0)).isEqualTo(4000.0);
    assertThat(ROUNDUP.SELF.apply(null, 3341.218639128, -2.0)).isEqualTo(3400.0);
    assertThat(ROUNDUP.SELF.apply(null, 3341.218639128, -1.0)).isEqualTo(3350.0);
    assertThat(ROUNDUP.SELF.apply(null, 3341.218639128, 0.0)).isEqualTo(3342.0);
  }

  @Test
  public void testApply_withNonIntegerPlaces() {
    assertThat(ROUNDUP.SELF.apply(null, 3341.218639128, -4.99)).isEqualTo(10000.0);
    assertThat(ROUNDUP.SELF.apply(null, 3341.218639128, -3.999999)).isEqualTo(4000.0);
    assertThat(ROUNDUP.SELF.apply(null, 3341.218639128, -2.1)).isEqualTo(3400.0);
    assertThat(ROUNDUP.SELF.apply(null, 3341.218639128, -1.1982371982)).isEqualTo(3350.0);
    assertThat(ROUNDUP.SELF.apply(null, 3341.218639128, 0.0)).isEqualTo(3342.0);
    assertThat(ROUNDUP.SELF.apply(null, 3341.218639128, 0.999)).isEqualTo(3342.0);
    assertThat(ROUNDUP.SELF.apply(null, 3341.218639128, -0.999)).isEqualTo(3342.0);
    assertThat(ROUNDUP.SELF.apply(null, 3341.218639128, 1.111)).isEqualTo(3341.3);
    assertThat(ROUNDUP.SELF.apply(null, 3341.218639128, -1.111)).isEqualTo(3350.0);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(ROUNDUP.SELF.apply(null, "10.4")).isEqualTo(11.0);
  }

  @Test
  public void testApply_grid() {
    assertThat(ROUNDUP.SELF.apply(null,
        Grid.builder().add(0, 0, -4.4444444).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(-5.0);
  }

  @Test
  public void test_lookup() {
    ROUNDUP F = new ROUNDUP(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(3341.218639128);
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn(0.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(3342.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, G19_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(ROUNDUP.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(ROUNDUP.SELF.apply(null)).isEqualTo(new NAException());
    assertThat(ROUNDUP.SELF.apply(null, "A", "B", "Too many")).isEqualTo(new NAException());
  }
}
