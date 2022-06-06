package io.protobase.f7.formulas.info;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ISLOGICALTest extends TestFormula {
  @Test
  public void test_number() {
    assertThat(ISLOGICAL.SELF.apply(null, 10.0)).isEqualTo(false);
  }

  @Test
  public void test_string() {
    assertThat(ISLOGICAL.SELF.apply(null, "String")).isEqualTo(false);
  }

  @Test
  public void test_boolean() {
    assertThat(ISLOGICAL.SELF.apply(null, true)).isEqualTo(true);
    assertThat(ISLOGICAL.SELF.apply(null, false)).isEqualTo(true);
  }

  @Test
  public void test_error() {
    assertThat(ISLOGICAL.SELF.apply(null, new NAException())).isEqualTo(false);
  }

  @Test
  public void test_lookup() {
    ISLOGICAL F = new ISLOGICAL(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(10.1);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(false);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void test_grid() {
    assertThat(ISLOGICAL.SELF.apply(null, Grid.builder().add(0, 0, "Still no.").build())).isEqualTo(false);
    assertThat(ISLOGICAL.SELF.apply(null, Grid.builder().add(0, 0, 22000.000).build())).isEqualTo(false);
    assertThat(ISLOGICAL.SELF.apply(null, Grid.builder().add(0, 0, true).build())).isEqualTo(true);
    assertThat(ISLOGICAL.SELF.apply(null,
        Grid.builder()
            .add(0, 0, true)
            .add(0, 1, "No, but previous is logical.")
            .build()))
        .isEqualTo(true);
    assertThat(ISLOGICAL.SELF.apply(null, Grid.builder().add(0, 0, new NAException()).build())).isEqualTo(false);
  }

  @Test
  public void testApply_errorFromArgumentsMismatch() {
    assertThat(ISLOGICAL.SELF.apply(null)).isEqualTo(new NAException());
    assertThat(ISLOGICAL.SELF.apply(null, "A", "B")).isEqualTo(new NAException());
  }
}
