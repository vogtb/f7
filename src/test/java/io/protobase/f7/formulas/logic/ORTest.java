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

public class ORTest extends TestFormula {
  @Test
  public void test_boolean() {
    assertThat(OR.SELF.apply(null, true)).isEqualTo(true);
    assertThat(OR.SELF.apply(null, true, true)).isEqualTo(true);
    assertThat(OR.SELF.apply(null, true, true, true)).isEqualTo(true);
    assertThat(OR.SELF.apply(null, true, true, false)).isEqualTo(true);
    assertThat(OR.SELF.apply(null, false)).isEqualTo(false);
    assertThat(OR.SELF.apply(null, false, false)).isEqualTo(false);
    assertThat(OR.SELF.apply(null, false, false, true)).isEqualTo(true);
  }

  @Test
  public void test_string() {
    assertThat(OR.SELF.apply(null, "TRUE")).isEqualTo(true);
    assertThat(OR.SELF.apply(null, "true")).isEqualTo(true);
    assertThat(OR.SELF.apply(null, "true", "false")).isEqualTo(true);
    assertThat(OR.SELF.apply(null, "true", "nope no sir this is not a boolean")).isEqualTo(new ValueException());
  }

  @Test
  public void test_number() {
    assertThat(OR.SELF.apply(null, 1.0)).isEqualTo(true);
    assertThat(OR.SELF.apply(null, 1.0, 2.2)).isEqualTo(true);
    assertThat(OR.SELF.apply(null, 1.0, 2.2, 11.11)).isEqualTo(true);
    assertThat(OR.SELF.apply(null, 1.0, 2.2, 11.11, 0.0)).isEqualTo(true);
    assertThat(OR.SELF.apply(null, 0.0, 0.0, 0.0)).isEqualTo(false);
    assertThat(OR.SELF.apply(null, 0.0, 0.0, 0.0, 0.0, 0.0, 99.99)).isEqualTo(true);
  }

  @Test
  public void test_error() {
    assertThat(OR.SELF.apply(null, 0.0, 0.0, new DivException())).isEqualTo(new DivException());
    assertThat(OR.SELF.apply(null, 0.0, 0.0, new NullException())).isEqualTo(new NullException());
  }

  @Test
  public void test_lookup() {
    OR F = new OR(lookup, collateralLookup);
    Grid D1_D3_grid = Grid.builder()
        .add(0, 0, true)
        .add(0, 1, true)
        .add(0, 2, false)
        .build();
    when(lookup.apply(D1_TO_D3_RANGE)).thenReturn(D1_D3_grid);
    assertThat(F.apply(A1, D1_TO_D3_RANGE)).isEqualTo(true);
    verify(lookup).apply(D1_TO_D3_RANGE);
    verifyNoMoreInteractions(lookup);
  }

  @Test
  public void test_grid() {
    assertThat(OR.SELF.apply(null, Grid.builder().add(0, 0, true).build())).isEqualTo(true);
    assertThat(OR.SELF.apply(null, Grid.builder()
        .add(0, 0, true)
        .add(0, 1, true)
        .add(0, 2, true)
        .build())
    ).isEqualTo(true);
    assertThat(OR.SELF.apply(null, Grid.builder()
        .add(0, 0, true)
        .add(0, 1, true)
        .add(0, 2, false)
        .build())
    ).isEqualTo(true);
    assertThat(OR.SELF.apply(null, Grid.builder()
        .add(0, 0, true)
        .add(0, 1, true)
        .add(0, 2, null)
        .add(0, 3, false)
        .build())
    ).isEqualTo(true);
    assertThat(OR.SELF.apply(null, Grid.builder()
        .add(0, 0, false)
        .add(0, 1, false)
        .add(0, 2, null)
        .add(0, 3, false)
        .build())
    ).isEqualTo(false);
  }

  @Test
  public void test_errorFromArgumentsMismatch() {
    assertThat(OR.SELF.apply(null)).isEqualTo(new NAException());
  }
}
