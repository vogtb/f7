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

public class ASINHTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(ASINH.SELF.apply(null, 10.0)).isEqualTo(2.99822295029797);
    assertThat(ASINH.SELF.apply(null, 128731.2)).isEqualTo(12.458628969021666);
    assertThat(ASINH.SELF.apply(null, 11.11)).isEqualTo(3.1030120634231873);
    assertThat(ASINH.SELF.apply(null, 0.0)).isEqualTo(0.0);
    assertThat(ASINH.SELF.apply(null, 88281.0)).isEqualTo(12.081427368492559);
    assertThat(ASINH.SELF.apply(null, 2.0)).isEqualTo(1.4436354751788103);
    assertThat(ASINH.SELF.apply(null, 4.0)).isEqualTo(2.0947125472611012);
    assertThat(ASINH.SELF.apply(null, -4.0)).isEqualTo(-2.094712547261101);
    assertThat(ASINH.SELF.apply(null, -10124.0)).isEqualTo(-9.91581129653516);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(ASINH.SELF.apply(null, "-10124.0")).isEqualTo(-9.91581129653516);
  }

  @Test
  public void testApply_grid() {
    assertThat(ASINH.SELF.apply(null,
        Grid.builder().add(0, 0, 4.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(2.0947125472611012);
  }

  @Test
  public void test_lookup() {
    ASINH F = new ASINH(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(10.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(2.99822295029797);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(ASINH.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(ASINH.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
