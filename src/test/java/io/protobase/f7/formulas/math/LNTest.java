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

public class LNTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(LN.SELF.apply(null, 128.0)).isEqualTo(Math.log(128));
    assertThat(LN.SELF.apply(null, 1.0)).isEqualTo(Math.log(1));
    assertThat(LN.SELF.apply(null, 2.0)).isEqualTo(Math.log(2));
    assertThat(LN.SELF.apply(null, 3.0)).isEqualTo(Math.log(3));
    assertThat(LN.SELF.apply(null, 0.1)).isEqualTo(Math.log(0.1));
  }

  @Test
  public void testApply_parameterErrors() {
    assertThat(LN.SELF.apply(null, 0.0)).isEqualTo(new NumException());
    assertThat(LN.SELF.apply(null, -0.1)).isEqualTo(new NumException());
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(LN.SELF.apply(null, "1")).isEqualTo(Math.log(1));
  }

  @Test
  public void testApply_grid() {
    assertThat(LN.SELF.apply(null,
        Grid.builder().add(0, 0, 44.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(Math.log(44.0));
  }

  @Test
  public void test_lookup() {
    LN F = new LN(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(128.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(Math.log(128));
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(LN.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(LN.SELF.apply(null)).isEqualTo(new NAException());
    assertThat(LN.SELF.apply(null, "A", "B", "C")).isEqualTo(new NAException());
  }
}
