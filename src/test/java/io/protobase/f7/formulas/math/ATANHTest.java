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

public class ATANHTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(ATANH.SELF.apply(null, 0.99)).isEqualTo(2.646652412362246);
    assertThat(ATANH.SELF.apply(null, 0.489733)).isEqualTo(0.5357090350574656);
    assertThat(ATANH.SELF.apply(null, 0.0)).isEqualTo(0.0);
    assertThat(ATANH.SELF.apply(null, -0.66152156111)).isEqualTo(-0.7955143351612654);
    assertThat(ATANH.SELF.apply(null, -0.88)).isEqualTo(-1.3757676565209744);
  }

  @Test
  public void testApply_numException() {
    assertThat(ATANH.SELF.apply(null, 1.0)).isEqualTo(new NumException());
    assertThat(ATANH.SELF.apply(null, 44.4)).isEqualTo(new NumException());
    assertThat(ATANH.SELF.apply(null, -1.0)).isEqualTo(new NumException());
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(ATANH.SELF.apply(null, "0.99")).isEqualTo(2.646652412362246);
  }

  @Test
  public void testApply_grid() {
    assertThat(ATANH.SELF.apply(null,
        Grid.builder().add(0, 0, 0.489733).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(0.5357090350574656);
  }

  @Test
  public void test_lookup() {
    ATANH F = new ATANH(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(0.99);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(2.646652412362246);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(ATANH.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(ATANH.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
