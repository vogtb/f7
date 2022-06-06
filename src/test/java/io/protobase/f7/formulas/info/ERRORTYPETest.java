package io.protobase.f7.formulas.info;

import com.google.common.truth.Truth;
import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.NameException;
import io.protobase.f7.errors.NullException;
import io.protobase.f7.errors.NumException;
import io.protobase.f7.errors.ParseException;
import io.protobase.f7.errors.RefException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.formulas.logic.EQ;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ERRORTYPETest extends TestFormula {
  @Test
  public void test() {
    assertThat(ERRORTYPE.SELF.apply(null, new NullException())).isEqualTo(1.0);
    assertThat(ERRORTYPE.SELF.apply(null, new DivException())).isEqualTo(2.0);
    assertThat(ERRORTYPE.SELF.apply(null, new ValueException())).isEqualTo(3.0);
    assertThat(ERRORTYPE.SELF.apply(null, new RefException())).isEqualTo(4.0);
    assertThat(ERRORTYPE.SELF.apply(null, new NameException())).isEqualTo(5.0);
    assertThat(ERRORTYPE.SELF.apply(null, new NumException())).isEqualTo(6.0);
    assertThat(ERRORTYPE.SELF.apply(null, new NAException())).isEqualTo(7.0);
    assertThat(ERRORTYPE.SELF.apply(null, new ParseException())).isEqualTo(8.0);
  }

  @Test
  public void test_grid() {
    assertThat(ERRORTYPE.SELF.apply(null,
        Grid.builder()
            .add(0, 0, new NullException())
            .add(0, 1, 10)
            .build())
    ).isEqualTo(1.0);
  }

  @Test
  public void test_lookup() {
    ERRORTYPE F = new ERRORTYPE(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(new DivException());
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(2.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
  }

  @Test
  public void test_nonError() {
    assertThat(ERRORTYPE.SELF.apply(null, 10.0)).isEqualTo(new NAException());
    assertThat(ERRORTYPE.SELF.apply(null, true)).isEqualTo(new NAException());
    assertThat(ERRORTYPE.SELF.apply(null, "Not an error.")).isEqualTo(new NAException());
  }

  @Test
  public void test_errorFromArgumentsMismatch() {
    Truth.assertThat(EQ.SELF.apply(null, "Too few")).isEqualTo(new NAException());
    assertThat(EQ.SELF.apply(null, "A", "B", "Too many")).isEqualTo(new NAException());
  }
}
