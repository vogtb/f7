package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.testutils.TestF7CodeExecutor;
import org.junit.Test;
import org.mockito.internal.verification.VerificationModeFactory;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class F7CodeExecutorGeneralConcatenationTest extends TestF7CodeExecutor {
  @Test
  public void test_String() {
    assertThat(run("= \"Hello\" & \"There\"")).isEqualTo("HelloThere");
    assertThat(run("= \"\" & \"\"")).isEqualTo("");
    assertThat(run("= \"   \" & \"    \"")).isEqualTo("       ");
  }

  @Test
  public void test_Number() {
    assertThat(run("= 0 & 1")).isEqualTo("01");
    assertThat(run("= 0 & 0")).isEqualTo("00");
    assertThat(run("= 131238 & 99281")).isEqualTo("13123899281");
    assertThat(run("= 13.1238 & 99281")).isEqualTo("13.123899281");
    assertThat(run("= 0.001 & 1.0")).isEqualTo("0.0011");
  }

  @Test
  public void test_Boolean() {
    assertThat(run("= TRUE & TRUE")).isEqualTo("TRUETRUE");
    assertThat(run("= TRUE & FALSE")).isEqualTo("TRUEFALSE");
    assertThat(run("= FALSE & FALSE")).isEqualTo("FALSEFALSE");
  }

  @Test
  public void test_Error() {
    assertThat(run("= 1 & #DIV/0!")).isEqualTo(new DivException());
  }

  @Test
  public void test_ArrayLiteral() {
    assertThat(run("= {1} & {2}")).isEqualTo("12");
    assertThat(run("= {1, 2, 3} & {4, 5, 6}")).isEqualTo("14");
    assertThat(run("= {1, #NUM!} & {4, #REF!}")).isEqualTo("14");
  }

  @Test
  public void test_Blank() {
    when(collateralLookup.apply(A1, M44_RANGE)).thenReturn(null);
    assertThat(runWithLookup("= M44 & M44")).isEqualTo("");
    verify(collateralLookup, VerificationModeFactory.times(2)).apply(A1, M44_RANGE);
    verifyNoMoreInteractions(lookup);
  }
}
