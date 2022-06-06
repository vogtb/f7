package io.protobase.f7.formulas.logic;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.NullException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class NOTTest extends TestFormula {
  @Test
  public void test_boolean() {
    assertThat(NOT.SELF.apply(null, true)).isEqualTo(false);
    assertThat(NOT.SELF.apply(null, false)).isEqualTo(true);
  }

  @Test
  public void test_string() {
    assertThat(NOT.SELF.apply(null, "TRUE")).isEqualTo(false);
    assertThat(NOT.SELF.apply(null, "true")).isEqualTo(false);
    assertThat(NOT.SELF.apply(null, false)).isEqualTo(true);
    assertThat(NOT.SELF.apply(null, "nope no sir this is not a boolean")).isEqualTo(new ValueException());
  }

  @Test
  public void test_number() {
    assertThat(NOT.SELF.apply(null, 1.0)).isEqualTo(false);
    assertThat(NOT.SELF.apply(null, 98722.0)).isEqualTo(false);
    assertThat(NOT.SELF.apply(null, 0.0)).isEqualTo(true);
  }

  @Test
  public void test_error() {
    assertThat(NOT.SELF.apply(null, new DivException())).isEqualTo(new DivException());
    assertThat(NOT.SELF.apply(null, new NullException())).isEqualTo(new NullException());
  }

  @Test
  public void test_lookup() {
    NOT F = new NOT(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(true);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(false);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void test_grid() {
    assertThat(NOT.SELF.apply(null, Grid.builder().add(0, 0, true).build())).isEqualTo(false);
    assertThat(NOT.SELF.apply(null, Grid.builder()
        .add(0, 0, true)
        .add(0, 1, 17821.11)
        .add(0, 2, "does not matter")
        .build())
    ).isEqualTo(false);
  }

  @Test
  public void test_errorFromArgumentsMismatch() {
    assertThat(NOT.SELF.apply(null)).isEqualTo(new NAException());
    assertThat(NOT.SELF.apply(null, true, true)).isEqualTo(new NAException());
  }
}
