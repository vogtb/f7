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
import static org.mockito.Mockito.when;

public class ISERRORTest extends TestFormula {
  @Test
  public void test_number() {
    assertThat(ISERROR.SELF.apply(null, 10.0)).isEqualTo(false);
  }

  @Test
  public void test_string() {
    assertThat(ISERROR.SELF.apply(null, "String")).isEqualTo(false);
  }

  @Test
  public void test_boolean() {
    assertThat(ISERROR.SELF.apply(null, true)).isEqualTo(false);
    assertThat(ISERROR.SELF.apply(null, false)).isEqualTo(false);
  }

  @Test
  public void test_error() {
    assertThat(ISERROR.SELF.apply(null, new NullException())).isEqualTo(true);
    assertThat(ISERROR.SELF.apply(null, new DivException())).isEqualTo(true);
    assertThat(ISERROR.SELF.apply(null, new ValueException())).isEqualTo(true);
    assertThat(ISERROR.SELF.apply(null, new RefException())).isEqualTo(true);
    assertThat(ISERROR.SELF.apply(null, new NameException())).isEqualTo(true);
    assertThat(ISERROR.SELF.apply(null, new NumException())).isEqualTo(true);
    assertThat(ISERROR.SELF.apply(null, new NAException())).isEqualTo(true);
    assertThat(ISERROR.SELF.apply(null, new ParseException())).isEqualTo(true);
  }

  @Test
  public void test_lookup() {
    ISERROR F = new ISERROR(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(new DivException());
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(true);
    verify(collateralLookup).apply(A1, M22_RANGE);
  }

  @Test
  public void test_grid() {
    assertThat(ISERROR.SELF.apply(null, Grid.builder().add(0, 0, "Still no.").build())).isEqualTo(false);
    assertThat(ISERROR.SELF.apply(null, Grid.builder().add(0, 0, new NAException()).build())).isEqualTo(true);
    assertThat(ISERROR.SELF.apply(null, Grid.builder()
        .add(0, 0, new NAException())
        .add(0, 1, "Previous one is, this isn't.")
        .build())).isEqualTo(true);
  }

  @Test
  public void testApply_errorFromArgumentsMismatch() {
    assertThat(ISERROR.SELF.apply(null)).isEqualTo(new NAException());
    assertThat(ISERROR.SELF.apply(null, "A", "B")).isEqualTo(new NAException());
  }
}
