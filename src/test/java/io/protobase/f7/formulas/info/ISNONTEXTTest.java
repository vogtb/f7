package io.protobase.f7.formulas.info;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ISNONTEXTTest extends TestFormula {
  @Test
  public void test_number() {
    assertThat(ISNONTEXT.SELF.apply(null, 10.0)).isEqualTo(true);
  }

  @Test
  public void test_string() {
    assertThat(ISNONTEXT.SELF.apply(null, "String")).isEqualTo(false);
  }

  @Test
  public void test_boolean() {
    assertThat(ISNONTEXT.SELF.apply(null, true)).isEqualTo(true);
    assertThat(ISNONTEXT.SELF.apply(null, false)).isEqualTo(true);
  }

  @Test
  public void test_error() {
    assertThat(ISNONTEXT.SELF.apply(null, new NAException())).isEqualTo(true);
    assertThat(ISNONTEXT.SELF.apply(null, new DivException())).isEqualTo(true);
  }

  @Test
  public void test_lookup() {
    ISNONTEXT F = new ISNONTEXT(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(100.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(true);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void test_grid() {
    assertThat(ISNONTEXT.SELF.apply(null, Grid.builder().add(0, 0, "Yes").build())).isEqualTo(false);
    assertThat(ISNONTEXT.SELF.apply(null, Grid.builder().add(0, 0, 22000.000).build())).isEqualTo(true);
    assertThat(ISNONTEXT.SELF.apply(null, Grid.builder().add(0, 0, true).build())).isEqualTo(true);
    assertThat(ISNONTEXT.SELF.apply(null,
        Grid.builder()
            .add(0, 0, true)
            .add(0, 1, "Yes, because previous is logical.")
            .build()))
        .isEqualTo(true);
    assertThat(ISNONTEXT.SELF.apply(null, Grid.builder().add(0, 0, new NAException()).build())).isEqualTo(true);
  }

  @Test
  public void testApply_errorFromArgumentsMismatch() {
    assertThat(ISNONTEXT.SELF.apply(null)).isEqualTo(new NAException());
    assertThat(ISNONTEXT.SELF.apply(null, "A", "B")).isEqualTo(new NAException());
  }
}
