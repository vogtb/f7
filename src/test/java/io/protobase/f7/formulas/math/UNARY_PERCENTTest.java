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

public class UNARY_PERCENTTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(UNARY_PERCENT.SELF.apply(null, 10.0)).isEqualTo(0.1);
    assertThat(UNARY_PERCENT.SELF.apply(null, -10.0)).isEqualTo(-0.1);
    assertThat(UNARY_PERCENT.SELF.apply(null, 0.0)).isEqualTo(0.0);
    assertThat(UNARY_PERCENT.SELF.apply(null, 8278.28687)).isEqualTo(82.7828687);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(UNARY_PERCENT.SELF.apply(null, "10.0")).isEqualTo(0.1);
  }

  @Test
  public void testApply_grid() {
    assertThat(UNARY_PERCENT.SELF.apply(null,
        Grid.builder().add(0, 0, -4.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(-0.04);
  }

  @Test
  public void test_lookup() {
    UNARY_PERCENT F = new UNARY_PERCENT(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(10.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(0.1);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(UNARY_PERCENT.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(UNARY_PERCENT.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
