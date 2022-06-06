package io.protobase.f7.formulas.lookup;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.NumException;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class CHOOSETest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(CHOOSE.SELF.apply(null, 1.0, "A")).isEqualTo("A");
    assertThat(CHOOSE.SELF.apply(null, 1.0, "A", "B", "C")).isEqualTo("A");
    assertThat(CHOOSE.SELF.apply(null, 2.0, "A", "B", "C")).isEqualTo("B");
    assertThat(CHOOSE.SELF.apply(null, 3.0, "A", "B", "C")).isEqualTo("C");
    assertThat(CHOOSE.SELF.apply(null, 3.9999999, "A", "B", "C")).isEqualTo("C");
    assertThat(CHOOSE.SELF.apply(null, 3.000011, "A", "B", "C")).isEqualTo("C");
    assertThat(CHOOSE.SELF.apply(null, 2.999999, "A", "B", "C")).isEqualTo("B");
  }

  @Test
  public void testApply_indexOutOfBounds() {
    assertThat(CHOOSE.SELF.apply(null, 2.0, "A")).isEqualTo(new NumException());
    assertThat(CHOOSE.SELF.apply(null, 4.0, "A", "B", "C")).isEqualTo(new NumException());
    assertThat(CHOOSE.SELF.apply(null, 0.0, "A", "B", "C")).isEqualTo(new NumException());
    assertThat(CHOOSE.SELF.apply(null, -1.0, "A", "B", "C")).isEqualTo(new NumException());
    assertThat(CHOOSE.SELF.apply(null, -99.0, "A", "B", "C")).isEqualTo(new NumException());
  }

  @Test
  public void testApply_chooseError() {
    assertThat(CHOOSE.SELF.apply(null, 1.0, new DivException())).isEqualTo(new DivException());
    assertThat(CHOOSE.SELF.apply(null, 1.0, "A", new DivException())).isEqualTo("A");
  }

  @Test
  public void test_lookup() {
    CHOOSE F = new CHOOSE(lookup, collateralLookup);
    when(collateralLookup.apply(A1, 1.0)).thenReturn(1.0);
    when(lookup.apply(J6_RANGE)).thenReturn(10.0);
    assertThat(F.apply(A1, 1.0, J6_RANGE)).isEqualTo(10.0);
    verify(lookup).apply(J6_RANGE);
    verify(collateralLookup).apply(A1, 1.0);
    verifyNoMoreInteractions(lookup);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void test_errorFromArgumentsMismatch() {
    assertThat(CHOOSE.SELF.apply(null, 1.0)).isEqualTo(new NAException());
  }
}