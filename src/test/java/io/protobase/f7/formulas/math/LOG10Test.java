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

public class LOG10Test extends TestFormula {
  @Test
  public void testApply() {
    assertThat(LOG10.SELF.apply(null, 128.0)).isEqualTo(Math.log10(128));
    assertThat(LOG10.SELF.apply(null, 1.0)).isEqualTo(Math.log10(1.0));
    assertThat(LOG10.SELF.apply(null, 2.0)).isEqualTo(Math.log10(2.0));
    assertThat(LOG10.SELF.apply(null, 3.0)).isEqualTo(Math.log10(3.0));
    assertThat(LOG10.SELF.apply(null, 10.0)).isEqualTo(Math.log10(10.0));
    assertThat(LOG10.SELF.apply(null, 100.0)).isEqualTo(Math.log10(100.0));
    assertThat(LOG10.SELF.apply(null, 1000.0)).isEqualTo(Math.log10(1000));
    assertThat(LOG10.SELF.apply(null, 10000.0)).isEqualTo(Math.log10(10000));
    assertThat(LOG10.SELF.apply(null, 100000.0)).isEqualTo(Math.log10(100000));
  }

  @Test
  public void testApply_parameterErrors() {
    assertThat(LOG10.SELF.apply(null, 0.0)).isEqualTo(new NumException());
    assertThat(LOG10.SELF.apply(null, -10.1)).isEqualTo(new NumException());
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(LOG10.SELF.apply(null, "10000")).isEqualTo(Math.log10(10000));
  }

  @Test
  public void testApply_grid() {
    assertThat(LOG10.SELF.apply(null,
        Grid.builder().add(0, 0, 44.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(Math.log10(44));
  }

  @Test
  public void test_lookup() {
    LOG10 F = new LOG10(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(128.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(Math.log10(128));
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(LOG10.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(LOG10.SELF.apply(null)).isEqualTo(new NAException());
    assertThat(LOG10.SELF.apply(null, "A", "B")).isEqualTo(new NAException());
  }
}
