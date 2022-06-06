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

public class LTTest extends TestFormula {
  @Test
  public void test_string() {
    assertThat(LT.SELF.apply(null, "Hello", "Diff")).isEqualTo(false);
    assertThat(LT.SELF.apply(null, "a", "a")).isEqualTo(false);
    assertThat(LT.SELF.apply(null, "a", "aa")).isEqualTo(true);
    assertThat(LT.SELF.apply(null, "aa", "a")).isEqualTo(false);
    assertThat(LT.SELF.apply(null, "a", "A")).isEqualTo(false);
    assertThat(LT.SELF.apply(null, "A", "a")).isEqualTo(false);
    assertThat(LT.SELF.apply(null, "A", "A")).isEqualTo(false);
    assertThat(LT.SELF.apply(null, "Aa", "A")).isEqualTo(false);
    assertThat(LT.SELF.apply(null, "AA", "a")).isEqualTo(false);
    assertThat(LT.SELF.apply(null, "aA", "a")).isEqualTo(false);
    assertThat(LT.SELF.apply(null, "aA", "A")).isEqualTo(false);
    assertThat(LT.SELF.apply(null, "押", "し")).isEqualTo(false);
    assertThat(LT.SELF.apply(null, "し", "押")).isEqualTo(true);
    assertThat(LT.SELF.apply(null, "String", 129321321.0)).isEqualTo(false);
    assertThat(LT.SELF.apply(null, 129321321.0, "String")).isEqualTo(true);
  }

  @Test
  public void test_number() {
    assertThat(LT.SELF.apply(null, 1.0, 1.0)).isEqualTo(false);
    assertThat(LT.SELF.apply(null, 1.0, 0.0)).isEqualTo(false);
    assertThat(LT.SELF.apply(null, 0.0, 1.0)).isEqualTo(true);
  }

  @Test
  public void test_boolean() {
    assertThat(LT.SELF.apply(null, true, false)).isEqualTo(false);
    assertThat(LT.SELF.apply(null, false, true)).isEqualTo(true);
    assertThat(LT.SELF.apply(null, true, true)).isEqualTo(false);
    assertThat(LT.SELF.apply(null, false, false)).isEqualTo(false);
    assertThat(LT.SELF.apply(null, true, "String")).isEqualTo(false);
    assertThat(LT.SELF.apply(null, "String", true)).isEqualTo(true);
  }

  @Test
  public void test_error() {
    assertThat(LT.SELF.apply(null, new ValueException(), new ValueException())).isEqualTo(new ValueException());
    assertThat(LT.SELF.apply(null, new DivException(), new ValueException())).isEqualTo(new DivException());
    assertThat(LT.SELF.apply(null, 4.4444, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void test_typePrecedence() {
    assertThat(LT.SELF.apply(null, "a", 0.0)).isEqualTo(false);
    assertThat(LT.SELF.apply(null, 0.0, "a")).isEqualTo(true);
    assertThat(LT.SELF.apply(null, true, 0.0)).isEqualTo(false);
    assertThat(LT.SELF.apply(null, false, 0.0)).isEqualTo(false);
    assertThat(LT.SELF.apply(null, 0.0, true)).isEqualTo(true);
    assertThat(LT.SELF.apply(null, 0.0, false)).isEqualTo(true);
    assertThat(LT.SELF.apply(null, true, "a")).isEqualTo(false);
    assertThat(LT.SELF.apply(null, false, "a")).isEqualTo(false);
    assertThat(LT.SELF.apply(null, "a", true)).isEqualTo(true);
    assertThat(LT.SELF.apply(null, "a", false)).isEqualTo(true);
  }

  @Test
  public void test_lookup() {
    LT F = new LT(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(1.0);
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn(2.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(true);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, G19_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void test_grid() {
    assertThat(LT.SELF.apply(null,
        Grid.builder().add(0, 0, 1.0).build(),
        Grid.builder().add(0, 0, 0.0).build()
    )).isEqualTo(false);
    assertThat(LT.SELF.apply(null,
        Grid.builder().add(0, 0, 1.0).build(),
        Grid.builder().add(0, 0, 1.0).build()
    )).isEqualTo(false);
  }

  @Test
  public void test_errorFromArgumentsMismatch() {
    assertThat(LT.SELF.apply(null, "Too few")).isEqualTo(new NAException());
    assertThat(LT.SELF.apply(null, "A", "B", "Too many")).isEqualTo(new NAException());
  }
}
