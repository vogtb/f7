package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.DivException;
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

public class LOGTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(LOG.SELF.apply(null, 128.0, 2.0)).isEqualTo(7.0);
    assertThat(LOG.SELF.apply(null, 128.0, 3.0)).isEqualTo(4.416508275000202);
    assertThat(LOG.SELF.apply(null, 1.0, 2.0)).isEqualTo(0.0);
    assertThat(LOG.SELF.apply(null, 2.0, 2.0)).isEqualTo(1.0);
    assertThat(LOG.SELF.apply(null, 3.0, 3.0)).isEqualTo(1.0);
    assertThat(LOG.SELF.apply(null, 2.0, 3.0)).isEqualTo(0.6309297535714574);
  }

  @Test
  public void testApply_defaultBase() {
    assertThat(LOG.SELF.apply(null, 1.0)).isEqualTo(0.0);
    assertThat(LOG.SELF.apply(null, 10.0)).isEqualTo(1.0);
    assertThat(LOG.SELF.apply(null, 100.0)).isEqualTo(2.0);
    assertThat(LOG.SELF.apply(null, 1000.0)).isEqualTo(3.0);
    assertThat(LOG.SELF.apply(null, 10000.0)).isEqualTo(4.0);
    assertThat(LOG.SELF.apply(null, 100000.0)).isEqualTo(5.0);
  }

  @Test
  public void testApply_parameterErrors() {
    assertThat(LOG.SELF.apply(null, 128.0, 1.0)).isEqualTo(new DivException());
    assertThat(LOG.SELF.apply(null, 128.0, 0.0)).isEqualTo(new NumException());
    assertThat(LOG.SELF.apply(null, 0.0, 1.0)).isEqualTo(new NumException());
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(LOG.SELF.apply(null, "1", "2")).isEqualTo(0.0);
  }

  @Test
  public void testApply_grid() {
    assertThat(LOG.SELF.apply(null,
        Grid.builder().add(0, 0, 44.0).add(0, 1, "Don't mind me.").build(),
        Grid.builder().add(0, 0, 2.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(5.459431618637297);
  }

  @Test
  public void test_lookup() {
    LOG F = new LOG(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(128.0);
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn(2.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(7.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, G19_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(LOG.SELF.apply(null, 10.0, new ValueException())).isEqualTo(new ValueException());
    assertThat(LOG.SELF.apply(null, new ValueException(), 10.0)).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(LOG.SELF.apply(null)).isEqualTo(new NAException());
    assertThat(LOG.SELF.apply(null, "A", "B", "C")).isEqualTo(new NAException());
  }
}
