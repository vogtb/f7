package io.protobase.f7.formulas.parser;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class TO_PERCENTTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(TO_PERCENT.SELF.apply(null, 0.99)).isEqualTo(0.99);
    assertThat(TO_PERCENT.SELF.apply(null, 0.489733)).isEqualTo(0.489733);
    assertThat(TO_PERCENT.SELF.apply(null, 0.0)).isEqualTo(0.0);
    assertThat(TO_PERCENT.SELF.apply(null, -0.66152156111)).isEqualTo(-0.66152156111);
    assertThat(TO_PERCENT.SELF.apply(null, -0.88)).isEqualTo(-0.88);
    assertThat(TO_PERCENT.SELF.apply(null, 19873218.11)).isEqualTo(19873218.11);
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(TO_PERCENT.SELF.apply(null, "2.646652412362246")).isEqualTo(2.646652412362246);
  }

  @Test
  public void testApply_grid() {
    assertThat(TO_PERCENT.SELF.apply(null,
        Grid.builder().add(0, 0, 19873218.11).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(19873218.11);
  }

  @Test
  public void test_lookup() {
    TO_PERCENT F = new TO_PERCENT(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(10.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(10.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(TO_PERCENT.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(TO_PERCENT.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
