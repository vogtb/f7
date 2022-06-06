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

public class ACOSHTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(ACOSH.SELF.apply(null, 10.0)).isEqualTo(2.993222846126381);
    assertThat(ACOSH.SELF.apply(null, 128731.2)).isEqualTo(12.458628968991492);
    assertThat(ACOSH.SELF.apply(null, 11.11)).isEqualTo(3.098961197908289);
    assertThat(ACOSH.SELF.apply(null, 88281.0)).isEqualTo(12.081427368428402);
  }

  @Test
  public void testApply_numException() {
    assertThat(ACOSH.SELF.apply(null, 0.0)).isEqualTo(new NumException());
    assertThat(ACOSH.SELF.apply(null, 0.0000000001)).isEqualTo(new NumException());
    assertThat(ACOSH.SELF.apply(null, -10.0)).isEqualTo(new NumException());
  }

  @Test
  public void testApply_stringConversion() {
    assertThat(ACOSH.SELF.apply(null, "10.0")).isEqualTo(2.993222846126381);
  }

  @Test
  public void testApply_grid() {
    assertThat(ACOSH.SELF.apply(null,
        Grid.builder().add(0, 0, 4.0).add(0, 1, "Don't mind me.").build()
    )).isEqualTo(2.0634370688955608);
  }

  @Test
  public void test_lookup() {
    ACOSH F = new ACOSH(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(10.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(2.993222846126381);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(ACOSH.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(ACOSH.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
