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

public class GTTest extends TestFormula {
  @Test
  public void test_string() {
    assertThat(GT.SELF.apply(null, "Hello", "Diff")).isEqualTo(true);
    assertThat(GT.SELF.apply(null, "a", "a")).isEqualTo(false);
    assertThat(GT.SELF.apply(null, "a", "aa")).isEqualTo(false);
    assertThat(GT.SELF.apply(null, "aa", "a")).isEqualTo(true);
    assertThat(GT.SELF.apply(null, "a", "A")).isEqualTo(false);
    assertThat(GT.SELF.apply(null, "A", "a")).isEqualTo(false);
    assertThat(GT.SELF.apply(null, "A", "A")).isEqualTo(false);
    assertThat(GT.SELF.apply(null, "Aa", "A")).isEqualTo(true);
    assertThat(GT.SELF.apply(null, "AA", "a")).isEqualTo(true);
    assertThat(GT.SELF.apply(null, "aA", "a")).isEqualTo(true);
    assertThat(GT.SELF.apply(null, "aA", "A")).isEqualTo(true);
    assertThat(GT.SELF.apply(null, "押", "し")).isEqualTo(true);
    assertThat(GT.SELF.apply(null, "し", "押")).isEqualTo(false);
    assertThat(GT.SELF.apply(null, "String", 129321321.0)).isEqualTo(true);
    assertThat(GT.SELF.apply(null, 129321321.0, "String")).isEqualTo(false);
  }

  @Test
  public void test_number() {
    assertThat(GT.SELF.apply(null, 1.0, 1.0)).isEqualTo(false);
    assertThat(GT.SELF.apply(null, 1.0, 0.0)).isEqualTo(true);
    assertThat(GT.SELF.apply(null, 0.0, 1.0)).isEqualTo(false);
  }

  @Test
  public void test_boolean() {
    assertThat(GT.SELF.apply(null, true, false)).isEqualTo(true);
    assertThat(GT.SELF.apply(null, false, true)).isEqualTo(false);
    assertThat(GT.SELF.apply(null, true, true)).isEqualTo(false);
    assertThat(GT.SELF.apply(null, false, false)).isEqualTo(false);
    assertThat(GT.SELF.apply(null, true, "String")).isEqualTo(true);
    assertThat(GT.SELF.apply(null, "String", true)).isEqualTo(false);
  }

  @Test
  public void test_error() {
    assertThat(LT.SELF.apply(null, new ValueException(), new ValueException())).isEqualTo(new ValueException());
    assertThat(LT.SELF.apply(null, new DivException(), new ValueException())).isEqualTo(new DivException());
    assertThat(LT.SELF.apply(null, 4.4444, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void test_typePrecedence() {
    assertThat(GT.SELF.apply(null, "a", 0.0)).isEqualTo(true);
    assertThat(GT.SELF.apply(null, 0.0, "a")).isEqualTo(false);
    assertThat(GT.SELF.apply(null, true, 0.0)).isEqualTo(true);
    assertThat(GT.SELF.apply(null, false, 0.0)).isEqualTo(true);
    assertThat(GT.SELF.apply(null, 0.0, true)).isEqualTo(false);
    assertThat(GT.SELF.apply(null, 0.0, false)).isEqualTo(false);
    assertThat(GT.SELF.apply(null, true, "a")).isEqualTo(true);
    assertThat(GT.SELF.apply(null, false, "a")).isEqualTo(true);
    assertThat(GT.SELF.apply(null, "a", true)).isEqualTo(false);
    assertThat(GT.SELF.apply(null, "a", false)).isEqualTo(false);
  }

  @Test
  public void test_lookup() {
    GT F = new GT(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(2.0);
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn(1.0);
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo(true);
    verify(collateralLookup).apply(A1, M22_RANGE);
  }

  @Test
  public void test_grid() {
    assertThat(GT.SELF.apply(null,
        Grid.builder().add(0, 0, 1.0).build(),
        Grid.builder().add(0, 0, 0.0).build()
    )).isEqualTo(true);
    assertThat(GT.SELF.apply(null,
        Grid.builder().add(0, 0, 1.0).build(),
        Grid.builder().add(0, 0, 1.0).build()
    )).isEqualTo(false);
  }

  @Test
  public void test_errorFromArgumentsMismatch() {
    assertThat(GT.SELF.apply(null, "Too few")).isEqualTo(new NAException());
    assertThat(GT.SELF.apply(null, "A", "B", "Too many")).isEqualTo(new NAException());
  }
}
