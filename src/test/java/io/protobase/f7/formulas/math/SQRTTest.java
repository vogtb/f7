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

public class SQRTTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(SQRT.SELF.apply(null, 10.0)).isEqualTo(Math.sqrt(10.0));
    assertThat(SQRT.SELF.apply(null, 128731.2)).isEqualTo(Math.sqrt(128731.2));
    assertThat(SQRT.SELF.apply(null, 11.11)).isEqualTo(Math.sqrt(11.11));
    assertThat(SQRT.SELF.apply(null, 0.0)).isEqualTo(Math.sqrt(0.0));
    assertThat(SQRT.SELF.apply(null, 88281.0)).isEqualTo(Math.sqrt(88281));
    assertThat(SQRT.SELF.apply(null, 2.0)).isEqualTo(Math.sqrt(2.0));
    assertThat(SQRT.SELF.apply(null, 4.0)).isEqualTo(Math.sqrt(4.0));
    assertThat(SQRT.SELF.apply(null, -4.0)).isEqualTo(new NumException());
    assertThat(SQRT.SELF.apply(null, -10124.0)).isEqualTo(new NumException());
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(SQRT.SELF.apply(null, "10.0")).isEqualTo(Math.sqrt(10.0));
  }

  @Test
  public void testApply_grid() {
    assertThat(SQRT.SELF.apply(null,
        Grid.builder().add(0, 0, 4.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(Math.sqrt(4.0));
  }

  @Test
  public void test_lookup() {
    SQRT F = new SQRT(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(10.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(Math.sqrt(10.0));
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(SQRT.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(SQRT.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
