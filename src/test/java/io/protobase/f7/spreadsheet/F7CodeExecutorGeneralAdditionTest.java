package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.NameException;
import io.protobase.f7.errors.NullException;
import io.protobase.f7.errors.NumException;
import io.protobase.f7.errors.ParseException;
import io.protobase.f7.errors.RefException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.testutils.TestF7CodeExecutor;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class F7CodeExecutorGeneralAdditionTest extends TestF7CodeExecutor {

  @Test
  public void test_Numbers() {
    assertThat(run("= 1")).isEqualTo(1.0);
    assertThat(run("= 10 + 10")).isEqualTo(20.0);
    assertThat(run("= 0 + 0")).isEqualTo(0.0);
    assertThat(run("= 10 + -10")).isEqualTo(0.0);
    assertThat(run("= -10 + -10")).isEqualTo(-20.0);
    assertThat(run("= 1e10 + 1.1")).isEqualTo(1.00000000011E10);
  }

  @Test
  public void test_Boolean() {
    assertThat(run("= 9 + TRUE")).isEqualTo(10.0);
    assertThat(run("= 9 + FALSE")).isEqualTo(9.0);
    assertThat(run("= 9 + -TRUE")).isEqualTo(8.0);
    assertThat(run("= 9 + -FALSE")).isEqualTo(9.0);
  }

  @Test
  public void test_String() {
    assertThat(run("= 9 + \"10\"")).isEqualTo(19.0);
    assertThat(run("= 9 + \"1e10\"")).isEqualTo(1.0000000009E10);
    assertThat(run("= 9 + \"0\"")).isEqualTo(9.0);
  }

  @Test
  public void test_StringNotNumber() {
    assertThat(run("= 9 + \"No good.\"")).isEqualTo(new ValueException());
    assertThat(run("= 9 + \"10    10\"")).isEqualTo(new ValueException());
  }

  @Test
  public void test_Array() {
    assertThat(run("= 2 + {1}")).isEqualTo(3.0);
    assertThat(run("= 2 + {1, 44}")).isEqualTo(3.0);
    assertThat(run("= 2 + -{1, \"Ignore me.\"}")).isEqualTo(1.0);
  }

  @Test
  public void test_Blank() {
    when(collateralLookup.apply(A1, 2.0)).thenReturn(2.0);
    when(collateralLookup.apply(A1, M44_RANGE)).thenReturn(null);
    assertThat(runWithLookup("= 2 + M44")).isEqualTo(2.0);
    verify(collateralLookup).apply(A1, 2.0);
    verify(collateralLookup).apply(A1, M44_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void test_Errors() {
    assertThat(run("= 3 + #NULL!")).isEqualTo(new NullException());
    assertThat(run("= 3 + #DIV/0!")).isEqualTo(new DivException());
    assertThat(run("= 3 + #VALUE!")).isEqualTo(new ValueException());
    assertThat(run("= 3 + #REF!")).isEqualTo(new RefException());
    assertThat(run("= 3 + #NAME?")).isEqualTo(new NameException());
    assertThat(run("= 3 + #NUM!")).isEqualTo(new NumException());
    assertThat(run("= 3 + #N/A")).isEqualTo(new NAException());
    assertThat(run("= 3 + #ERROR!")).isEqualTo(new ParseException());
  }
}
