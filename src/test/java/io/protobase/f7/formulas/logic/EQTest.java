package io.protobase.f7.formulas.logic;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EQTest extends TestFormula {

  @Test
  public void test_sameType() {
    assertThat(EQ.SELF.apply(null, 10.0, 10.0)).isEqualTo(true);
    assertThat(EQ.SELF.apply(null, 10.0, 0.0)).isEqualTo(false);
    assertThat(EQ.SELF.apply(null, "Same", "Same")).isEqualTo(true);
    assertThat(EQ.SELF.apply(null, "Same", "Different")).isEqualTo(false);
    assertThat(EQ.SELF.apply(null, true, true)).isEqualTo(true);
    assertThat(EQ.SELF.apply(null, true, false)).isEqualTo(false);
    assertThat(EQ.SELF.apply(null, false, false)).isEqualTo(true);
  }

  @Test
  public void test_error() {
    assertThat(EQ.SELF.apply(null, new ValueException(), new ValueException())).isEqualTo(new ValueException());
    assertThat(EQ.SELF.apply(null, new DivException(), new ValueException())).isEqualTo(new DivException());
    assertThat(EQ.SELF.apply(null, 4.4444, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void test_lookup() {
    EQ F = new EQ(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn("A");
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn("B");
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(false);
    verify(collateralLookup).apply(A1, M22_RANGE);
  }

  @Test
  public void test_grids() {
    Grid one = Grid.builder()
        .add(0, 0, 44.0)
        .add(0, 1, "A")
        .build();
    Grid two = Grid.builder()
        .add(0, 0, 44.0)
        .add(0, 1, "B")
        .build();
    assertThat(EQ.SELF.apply(null, one, two)).isEqualTo(true);
  }

  @Test
  public void test_errorFromArgumentsMismatch() {
    assertThat(EQ.SELF.apply(null, "Too few")).isEqualTo(new NAException());
    assertThat(EQ.SELF.apply(null, "A", "B", "Too many")).isEqualTo(new NAException());
  }
}
