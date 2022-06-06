package io.protobase.f7.formulas.info;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.NameException;
import io.protobase.f7.errors.NullException;
import io.protobase.f7.errors.NumException;
import io.protobase.f7.errors.ParseException;
import io.protobase.f7.errors.RefException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ISNATest extends TestFormula {
  @Test
  public void test_number() {
    assertThat(ISNA.SELF.apply(null, 10.0)).isEqualTo(false);
  }

  @Test
  public void test_string() {
    assertThat(ISNA.SELF.apply(null, "String")).isEqualTo(false);
  }

  @Test
  public void test_boolean() {
    assertThat(ISNA.SELF.apply(null, true)).isEqualTo(false);
    assertThat(ISNA.SELF.apply(null, false)).isEqualTo(false);
  }

  @Test
  public void test_error() {
    assertThat(ISNA.SELF.apply(null, new NullException())).isEqualTo(false);
    assertThat(ISNA.SELF.apply(null, new DivException())).isEqualTo(false);
    assertThat(ISNA.SELF.apply(null, new ValueException())).isEqualTo(false);
    assertThat(ISNA.SELF.apply(null, new RefException())).isEqualTo(false);
    assertThat(ISNA.SELF.apply(null, new NameException())).isEqualTo(false);
    assertThat(ISNA.SELF.apply(null, new NumException())).isEqualTo(false);
    assertThat(ISNA.SELF.apply(null, new NAException())).isEqualTo(true);
    assertThat(ISNA.SELF.apply(null, new ParseException())).isEqualTo(false);
  }

  @Test
  public void test_lookup() {
    ISNA F = new ISNA(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(new NAException());
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(true);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void test_grid() {
    assertThat(ISNA.SELF.apply(null, Grid.builder().add(0, 0, "Still no.").build())).isEqualTo(false);
    assertThat(ISNA.SELF.apply(null, Grid.builder().add(0, 0, new NAException()).build())).isEqualTo(true);
    assertThat(ISNA.SELF.apply(null, Grid.builder()
        .add(0, 0, new NAException())
        .add(0, 1, "Previous one is, this isn't.")
        .build())).isEqualTo(true);
  }

  @Test
  public void testApply_errorFromArgumentsMismatch() {
    assertThat(ISNA.SELF.apply(null)).isEqualTo(new NAException());
    assertThat(ISNA.SELF.apply(null, "A", "B")).isEqualTo(new NAException());
  }
}
