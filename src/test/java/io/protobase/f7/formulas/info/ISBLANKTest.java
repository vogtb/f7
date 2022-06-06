package io.protobase.f7.formulas.info;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ISBLANKTest extends TestFormula {
  @Test
  public void test_number() {
    assertThat(ISBLANK.SELF.apply(null, 10.0)).isEqualTo(false);
  }

  @Test
  public void test_string() {
    assertThat(ISBLANK.SELF.apply(null, "String")).isEqualTo(false);
  }

  @Test
  public void test_boolean() {
    assertThat(ISBLANK.SELF.apply(null, true)).isEqualTo(false);
    assertThat(ISBLANK.SELF.apply(null, false)).isEqualTo(false);
  }

  @Test
  public void test_error() {
    assertThat(ISBLANK.SELF.apply(null, new NAException())).isEqualTo(false);
  }

  @Test
  public void test_blankAKANull() {
    String NULL = null;
    assertThat(ISBLANK.SELF.apply(null, NULL)).isEqualTo(true);
  }

  @Test
  public void test_lookup() {
    ISBLANK F = new ISBLANK(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(Grid.builder().build());
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(true);
    verify(collateralLookup).apply(A1, M22_RANGE);
  }

  @Test
  public void test_grid() {
    assertThat(ISBLANK.SELF.apply(null, Grid.builder().add(0, 0, "Yes").build())).isEqualTo(false);
    assertThat(ISBLANK.SELF.apply(null, Grid.builder().add(0, 0, 22000.000).build())).isEqualTo(false);
    assertThat(ISBLANK.SELF.apply(null, Grid.builder().add(0, 0, true).build())).isEqualTo(false);
    assertThat(ISBLANK.SELF.apply(null,
        Grid.builder()
            .add(0, 0, "Nope")
            .add(0, 1, false)
            .build()))
        .isEqualTo(false);
    assertThat(ISBLANK.SELF.apply(null, Grid.builder().add(0, 0, new NAException()).build())).isEqualTo(false);
  }

  @Test
  public void test_errorFromArgumentsMismatch() {
    assertThat(ISBLANK.SELF.apply(null)).isEqualTo(new NAException());
    assertThat(ISBLANK.SELF.apply(null, "A", "B")).isEqualTo(new NAException());
  }
}
