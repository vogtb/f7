package io.protobase.f7.formulas.info;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ISNUMBERTest extends TestFormula {
  @Test
  public void test_number() {
    assertThat(ISNUMBER.SELF.apply(null, 10.0)).isEqualTo(true);
  }

  @Test
  public void test_string() {
    assertThat(ISNUMBER.SELF.apply(null, "String")).isEqualTo(false);
  }

  @Test
  public void test_boolean() {
    assertThat(ISNUMBER.SELF.apply(null, true)).isEqualTo(false);
    assertThat(ISNUMBER.SELF.apply(null, false)).isEqualTo(false);
  }

  @Test
  public void test_error() {
    assertThat(ISNUMBER.SELF.apply(null, new NAException())).isEqualTo(false);
  }

  @Test
  public void test_lookup() {
    ISNUMBER F = new ISNUMBER(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn("Nope");
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(false);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void test_grid() {
    assertThat(ISNUMBER.SELF.apply(null, Grid.builder().add(0, 0, "No").build())).isEqualTo(false);
    assertThat(ISNUMBER.SELF.apply(null, Grid.builder().add(0, 0, 22000.000).build())).isEqualTo(true);
    assertThat(ISNUMBER.SELF.apply(null, Grid.builder().add(0, 0, true).build())).isEqualTo(false);
    assertThat(ISNUMBER.SELF.apply(null,
        Grid.builder()
            .add(0, 0, 100.0)
            .add(0, 1, "Yes, because previous is number.")
            .build()))
        .isEqualTo(true);
    assertThat(ISNUMBER.SELF.apply(null, Grid.builder().add(0, 0, new NAException()).build())).isEqualTo(false);
  }

  @Test
  public void testApply_errorFromArgumentsMismatch() {
    assertThat(ISNUMBER.SELF.apply(null)).isEqualTo(new NAException());
    assertThat(ISNUMBER.SELF.apply(null, "A", "B")).isEqualTo(new NAException());
  }
}
