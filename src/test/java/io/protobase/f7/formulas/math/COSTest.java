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

public class COSTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(COS.SELF.apply(null, 10.0)).isEqualTo(Math.cos(10.0));
    assertThat(COS.SELF.apply(null, 128731.2)).isEqualTo(Math.cos(128731.2));
    assertThat(COS.SELF.apply(null, 11.11)).isEqualTo(Math.cos(11.11));
    assertThat(COS.SELF.apply(null, 0.0)).isEqualTo(Math.cos(0.0));
    assertThat(COS.SELF.apply(null, 88281.0)).isEqualTo(Math.cos(88281));
    assertThat(COS.SELF.apply(null, 2.0)).isEqualTo(Math.cos(2.0));
    assertThat(COS.SELF.apply(null, 4.0)).isEqualTo(Math.cos(4.0));
    assertThat(COS.SELF.apply(null, -4.0)).isEqualTo(Math.cos(-4.0));
    assertThat(COS.SELF.apply(null, -10124.0)).isEqualTo(Math.cos(-10124.0));
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(COS.SELF.apply(null, "10.0")).isEqualTo(Math.cos(10.0));
  }

  @Test
  public void testApply_grid() {
    assertThat(COS.SELF.apply(null,
        Grid.builder().add(0, 0, -4.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(Math.cos(-4.0));
  }

  @Test
  public void test_lookup() {
    COS F = new COS(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(10.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(Math.cos(10.0));
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(COS.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(COS.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
