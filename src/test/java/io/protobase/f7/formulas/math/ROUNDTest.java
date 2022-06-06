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

public class ROUNDTest extends TestFormula {
  @Test
  public void testApply_alreadyRounded() {
    assertThat(ROUND.SELF.apply(null, 10.0)).isEqualTo(10.0);
    assertThat(ROUND.SELF.apply(null, -10.0)).isEqualTo(-10.0);
    assertThat(ROUND.SELF.apply(null, 0.0)).isEqualTo(0.0);
  }

  @Test
  public void testApply_roundingFullInteger() {
    assertThat(ROUND.SELF.apply(null, 3.218639128)).isEqualTo(3.0);
    assertThat(ROUND.SELF.apply(null, 7.99)).isEqualTo(8.0);
    assertThat(ROUND.SELF.apply(null, 7.0000000001)).isEqualTo(7.0);
  }

  @Test
  public void testApply_toPlace() {
    assertThat(ROUND.SELF.apply(null, 3.218639128, 4.0)).isEqualTo(3.2186);
    assertThat(ROUND.SELF.apply(null, 3.218639128, 5.0)).isEqualTo(3.21864);
    assertThat(ROUND.SELF.apply(null, 9.12, 0.0)).isEqualTo(9.0);
    assertThat(ROUND.SELF.apply(null, 9.12, 1.0)).isEqualTo(9.1);
    assertThat(ROUND.SELF.apply(null, 9.12, 2.0)).isEqualTo(9.12);
    assertThat(ROUND.SELF.apply(null, 9.12, 3.0)).isEqualTo(9.12);
    assertThat(ROUND.SELF.apply(null, 9.12, 8.0)).isEqualTo(9.12);
  }

  @Test
  public void testApply_toNegativePlace() {
    assertThat(ROUND.SELF.apply(null, 3341.218639128, -4.0)).isEqualTo(0.0);
    assertThat(ROUND.SELF.apply(null, 3341.218639128, -3.0)).isEqualTo(3000.0);
    assertThat(ROUND.SELF.apply(null, 3341.218639128, -2.0)).isEqualTo(3300.0);
    assertThat(ROUND.SELF.apply(null, 3341.218639128, -1.0)).isEqualTo(3340.0);
    assertThat(ROUND.SELF.apply(null, 3341.218639128, 0.0)).isEqualTo(3341.0);
  }

  @Test
  public void testApply_withNonIntegerPlaces() {
    assertThat(ROUND.SELF.apply(null, 3341.218639128, -4.99)).isEqualTo(0.0);
    assertThat(ROUND.SELF.apply(null, 3341.218639128, -3.999999)).isEqualTo(3000.0);
    assertThat(ROUND.SELF.apply(null, 3341.218639128, -2.1)).isEqualTo(3300.0);
    assertThat(ROUND.SELF.apply(null, 3341.218639128, -1.1982371982)).isEqualTo(3340.0);
    assertThat(ROUND.SELF.apply(null, 3341.218639128, 0.0)).isEqualTo(3341.0);
    assertThat(ROUND.SELF.apply(null, 3341.218639128, 0.999)).isEqualTo(3341.0);
    assertThat(ROUND.SELF.apply(null, 3341.218639128, -0.999)).isEqualTo(3341.0);
    assertThat(ROUND.SELF.apply(null, 3341.218639128, 1.111)).isEqualTo(3341.2);
    assertThat(ROUND.SELF.apply(null, 3341.218639128, -1.111)).isEqualTo(3340.0);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(ROUND.SELF.apply(null, "10.4")).isEqualTo(10.0);
  }

  @Test
  public void testApply_grid() {
    assertThat(ROUND.SELF.apply(null,
        Grid.builder().add(0, 0, -4.4444444).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(-4.0);
  }

  @Test
  public void test_lookup() {
    ROUND F = new ROUND(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(3.218639128);
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn(4.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(3.2186);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, G19_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(ROUND.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(ROUND.SELF.apply(null)).isEqualTo(new NAException());
    assertThat(ROUND.SELF.apply(null, "A", "B", "Too many")).isEqualTo(new NAException());
  }
}
