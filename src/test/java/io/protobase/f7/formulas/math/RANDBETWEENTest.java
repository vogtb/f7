package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.NumException;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class RANDBETWEENTest extends TestFormula {

  @Test
  public void testApply_integers() {
    for (int i = 0; i < 100000; i++) {
      Double one = ((Double) RANDBETWEEN.SELF.apply(null, 1.0, 3.0));
      assertThat(one).isAtLeast(1.0);
      assertThat(one).isAtMost(3.0);
    }
    for (int i = 0; i < 100000; i++) {
      Double one = ((Double) RANDBETWEEN.SELF.apply(null, 4.0, 5.0));
      assertThat(one).isAtLeast(4.0);
      assertThat(one).isAtMost(5.0);
    }
    for (int i = 0; i < 100000; i++) {
      Double one = ((Double) RANDBETWEEN.SELF.apply(null, -1.0, 2.0));
      assertThat(one).isAtLeast(-1.0);
      assertThat(one).isAtMost(2.0);
    }
  }

  @Test
  public void testApply_doubles() {
    for (int i = 0; i < 100000; i++) {
      Double one = ((Double) RANDBETWEEN.SELF.apply(null, 1.1, 2.1));
      assertThat(one).isAtLeast(1.0);
      assertThat(one).isAtMost(3.0);
    }
    for (int i = 0; i < 100000; i++) {
      Double one = ((Double) RANDBETWEEN.SELF.apply(null, 4.9, 4.9));
      assertThat(one).isAtLeast(4.0);
      assertThat(one).isAtMost(5.0);
    }
    for (int i = 0; i < 100000; i++) {
      assertThat(RANDBETWEEN.SELF.apply(null, 22.29348794, 24.0)).isAnyOf(23.0, 24.0);
    }
    assertThat(RANDBETWEEN.SELF.apply(null, 0.0, 0.0)).isEqualTo(0.0);
    assertThat(RANDBETWEEN.SELF.apply(null, 22.29348794, 22.29348794)).isEqualTo(23.0);
  }

  @Test
  public void testApply_booleans() {
    for (int i = 0; i < 100000; i++) {
      Double one = ((Double) RANDBETWEEN.SELF.apply(null, false, true));
      assertThat(one).isAtLeast(0.0);
      assertThat(one).isAtMost(1.0);
    }
  }

  @Test
  public void test_lookup() {
    RANDBETWEEN F = new RANDBETWEEN(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(1.0);
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn(10.0);
    F.apply(A1, M22_RANGE, G19_RANGE);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, G19_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorFromMisalignedHighAndLow() {
    assertThat(RANDBETWEEN.SELF.apply(null, 10.0, 1.0)).isEqualTo(new NumException());
  }

  @Test
  public void testApply_errorFromArgumentsMismatch() {
    assertThat(RANDBETWEEN.SELF.apply(null, "Too few")).isEqualTo(new NAException());
    assertThat(RANDBETWEEN.SELF.apply(null, "A", "B", "Too many")).isEqualTo(new NAException());
  }
}