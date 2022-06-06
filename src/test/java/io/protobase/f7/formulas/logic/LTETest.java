package io.protobase.f7.formulas.logic;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class LTETest extends TestFormula {
  @Test
  public void test_string() {
    assertThat(LTE.SELF.apply(null, "Hello", "Diff")).isEqualTo(false);
    assertThat(LTE.SELF.apply(null, "a", "a")).isEqualTo(true);
    assertThat(LTE.SELF.apply(null, "a", "aa")).isEqualTo(true);
    assertThat(LTE.SELF.apply(null, "aa", "a")).isEqualTo(false);
    assertThat(LTE.SELF.apply(null, "a", "A")).isEqualTo(true);
    assertThat(LTE.SELF.apply(null, "A", "a")).isEqualTo(true);
    assertThat(LTE.SELF.apply(null, "A", "A")).isEqualTo(true);
    assertThat(LTE.SELF.apply(null, "Aa", "A")).isEqualTo(false);
    assertThat(LTE.SELF.apply(null, "AA", "a")).isEqualTo(false);
    assertThat(LTE.SELF.apply(null, "aA", "a")).isEqualTo(false);
    assertThat(LTE.SELF.apply(null, "aA", "A")).isEqualTo(false);
    assertThat(LTE.SELF.apply(null, "押", "し")).isEqualTo(false);
    assertThat(LTE.SELF.apply(null, "し", "押")).isEqualTo(true);
    assertThat(LTE.SELF.apply(null, "String", 129321321.0)).isEqualTo(false);
    assertThat(LTE.SELF.apply(null, 129321321.0, "String")).isEqualTo(true);
  }

  @Test
  public void test_number() {
    assertThat(LTE.SELF.apply(null, 1.0, 1.0)).isEqualTo(true);
    assertThat(LTE.SELF.apply(null, 1.0, 0.0)).isEqualTo(false);
    assertThat(LTE.SELF.apply(null, 0.0, 1.0)).isEqualTo(true);
  }

  @Test
  public void test_boolean() {
    assertThat(LTE.SELF.apply(null, true, false)).isEqualTo(false);
    assertThat(LTE.SELF.apply(null, false, true)).isEqualTo(true);
    assertThat(LTE.SELF.apply(null, true, true)).isEqualTo(true);
    assertThat(LTE.SELF.apply(null, false, false)).isEqualTo(true);
    assertThat(LTE.SELF.apply(null, true, "String")).isEqualTo(false);
    assertThat(LTE.SELF.apply(null, "String", true)).isEqualTo(true);
  }

  @Test
  public void test_error() {
    assertThat(LTE.SELF.apply(null, new ValueException(), new ValueException())).isEqualTo(new ValueException());
    assertThat(LTE.SELF.apply(null, new DivException(), new ValueException())).isEqualTo(new DivException());
    assertThat(LTE.SELF.apply(null, 4.4444, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void test_typePrecedence() {
    assertThat(LTE.SELF.apply(null, "a", 0.0)).isEqualTo(false);
    assertThat(LTE.SELF.apply(null, 0.0, "a")).isEqualTo(true);
    assertThat(LTE.SELF.apply(null, true, 0.0)).isEqualTo(false);
    assertThat(LTE.SELF.apply(null, false, 0.0)).isEqualTo(false);
    assertThat(LTE.SELF.apply(null, 0.0, true)).isEqualTo(true);
    assertThat(LTE.SELF.apply(null, 0.0, false)).isEqualTo(true);
    assertThat(LTE.SELF.apply(null, true, "a")).isEqualTo(false);
    assertThat(LTE.SELF.apply(null, false, "a")).isEqualTo(false);
    assertThat(LTE.SELF.apply(null, "a", true)).isEqualTo(true);
    assertThat(LTE.SELF.apply(null, "a", false)).isEqualTo(true);
  }

  @Test
  public void test_lookup() {
    LTE F = new LTE(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(1.0);
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn(2.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(true);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, G19_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void test_grid() {
    assertThat(LTE.SELF.apply(null,
        Grid.builder().add(0, 0, 1.0).build(),
        Grid.builder().add(0, 0, 0.0).build()
    )).isEqualTo(false);
    assertThat(LTE.SELF.apply(null,
        Grid.builder().add(0, 0, 1.0).build(),
        Grid.builder().add(0, 0, 1.0).build()
    )).isEqualTo(true);
  }

  @Test
  public void test_errorFromArgumentsMismatch() {
    assertThat(LTE.SELF.apply(null, "Too few")).isEqualTo(new NAException());
    assertThat(LTE.SELF.apply(null, "A", "B", "Too many")).isEqualTo(new NAException());
  }
}
