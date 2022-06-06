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

public class SQRTPITest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(SQRTPI.SELF.apply(null, 10.0)).isEqualTo(5.604991216397929);
    assertThat(SQRTPI.SELF.apply(null, 128731.2)).isEqualTo(635.9410288759473);
    assertThat(SQRTPI.SELF.apply(null, 11.11)).isEqualTo(5.907884086657642);
    assertThat(SQRTPI.SELF.apply(null, 0.0)).isEqualTo(0.0);
    assertThat(SQRTPI.SELF.apply(null, 88281.0)).isEqualTo(526.6335927868261);
    assertThat(SQRTPI.SELF.apply(null, 2.0)).isEqualTo(2.5066282746310002);
    assertThat(SQRTPI.SELF.apply(null, 4.0)).isEqualTo(3.5449077018110318);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(SQRTPI.SELF.apply(null, "88281.0")).isEqualTo(526.6335927868261);
  }

  @Test
  public void testApply_numExceptions() {
    assertThat(SQRTPI.SELF.apply(null, -0.0001)).isEqualTo(new NumException());
    assertThat(SQRTPI.SELF.apply(null, -10124.0)).isEqualTo(new NumException());
  }

  @Test
  public void testApply_grid() {
    assertThat(SQRTPI.SELF.apply(null,
        Grid.builder().add(0, 0, 4.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(3.5449077018110318);
  }

  @Test
  public void test_lookup() {
    SQRTPI F = new SQRTPI(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(10.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(5.604991216397929);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(SQRTPI.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(SQRTPI.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
