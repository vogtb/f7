package io.protobase.f7.formulas.info;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ISTEXTTest extends TestFormula {
  @Test
  public void test_number() {
    assertThat(ISTEXT.SELF.apply(null, 10.0)).isEqualTo(false);
  }

  @Test
  public void test_string() {
    assertThat(ISTEXT.SELF.apply(null, "String")).isEqualTo(true);
  }

  @Test
  public void test_boolean() {
    assertThat(ISTEXT.SELF.apply(null, true)).isEqualTo(false);
    assertThat(ISTEXT.SELF.apply(null, false)).isEqualTo(false);
  }

  @Test
  public void test_error() {
    assertThat(ISTEXT.SELF.apply(null, new NAException())).isEqualTo(false);
  }

  @Test
  public void test_lookup() {
    ISTEXT F = new ISTEXT(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn("Nope");
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(true);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void test_grid() {
    assertThat(ISTEXT.SELF.apply(null, Grid.builder().add(0, 0, "Yes").build())).isEqualTo(true);
    assertThat(ISTEXT.SELF.apply(null, Grid.builder().add(0, 0, 22000.000).build())).isEqualTo(false);
    assertThat(ISTEXT.SELF.apply(null, Grid.builder().add(0, 0, true).build())).isEqualTo(false);
    assertThat(ISTEXT.SELF.apply(null,
        Grid.builder()
            .add(0, 0, "Yes")
            .add(0, 1, false)
            .build()))
        .isEqualTo(true);
    assertThat(ISTEXT.SELF.apply(null, Grid.builder().add(0, 0, new NAException()).build())).isEqualTo(false);
  }

  @Test
  public void test_errorFromArgumentsMismatch() {
    assertThat(ISTEXT.SELF.apply(null)).isEqualTo(new NAException());
    assertThat(ISTEXT.SELF.apply(null, "A", "B")).isEqualTo(new NAException());
  }
}
