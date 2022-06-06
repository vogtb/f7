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

public class ABSTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(ABS.SELF.apply(null, 10.0)).isEqualTo(10.0);
    assertThat(ABS.SELF.apply(null, -10.0)).isEqualTo(10.0);
    assertThat(ABS.SELF.apply(null, 0.0)).isEqualTo(0.0);
    assertThat(ABS.SELF.apply(null, -218637221.22)).isEqualTo(218637221.22);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(ABS.SELF.apply(null, "10.0")).isEqualTo(10.0);
  }

  @Test
  public void testApply_grid() {
    assertThat(ABS.SELF.apply(null,
        Grid.builder().add(0, 0, -4.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(4.0);
  }

  @Test
  public void test_lookup() {
    ABS F = new ABS(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(-2.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(2.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(ABS.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(ABS.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
