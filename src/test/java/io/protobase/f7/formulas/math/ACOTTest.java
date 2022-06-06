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

public class ACOTTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(ACOT.SELF.apply(null, 10.0)).isEqualTo(0.09966865249116204);
    assertThat(ACOT.SELF.apply(null, 128731.2)).isEqualTo(7.768124588133144E-6);
    assertThat(ACOT.SELF.apply(null, 11.11)).isEqualTo(0.08976710276137885);
    assertThat(ACOT.SELF.apply(null, 0.0)).isEqualTo(1.570796327);
    assertThat(ACOT.SELF.apply(null, 88281.0)).isEqualTo(1.1327465705613094E-5);
    assertThat(ACOT.SELF.apply(null, 2.0)).isEqualTo(0.4636476090008061);
    assertThat(ACOT.SELF.apply(null, 4.0)).isEqualTo(0.24497866312686414);
    assertThat(ACOT.SELF.apply(null, -4.0)).isEqualTo(-0.24497866312686414);
    assertThat(ACOT.SELF.apply(null, -10124.0)).isEqualTo(-9.877518735162196E-5);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(ACOT.SELF.apply(null, "88281")).isEqualTo(1.1327465705613094E-5);
  }

  @Test
  public void testApply_grid() {
    assertThat(ACOT.SELF.apply(null,
        Grid.builder().add(0, 0, 88281.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(1.1327465705613094E-5);
  }

  @Test
  public void test_lookup() {
    ACOT F = new ACOT(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(10.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(0.09966865249116204);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(ACOT.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(ACOT.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
