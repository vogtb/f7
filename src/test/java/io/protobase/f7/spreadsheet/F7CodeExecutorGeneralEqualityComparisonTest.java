package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.RefException;
import io.protobase.f7.testutils.TestF7CodeExecutor;
import org.junit.Test;
import org.mockito.internal.verification.VerificationModeFactory;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class F7CodeExecutorGeneralEqualityComparisonTest extends TestF7CodeExecutor {
  @Test
  public void test_Number() {
    assertThat(run("= 1 = 1")).isEqualTo(true);
    assertThat(run("= 1 = 2")).isEqualTo(false);
    assertThat(run("= 1.1928731 = 1.1928731")).isEqualTo(true);
  }

  @Test
  public void test_String() {
    assertThat(run("= \"Yes\" = \"Yes\"")).isEqualTo(true);
    assertThat(run("= \"Yes\" = \"No\"")).isEqualTo(false);
    assertThat(run("= \"\" = \"\"")).isEqualTo(true);
  }

  @Test
  public void test_Boolean() {
    assertThat(run("= TRUE = TRUE")).isEqualTo(true);
    assertThat(run("= FALSE = FALSE")).isEqualTo(true);
    assertThat(run("= TRUE = FALSE")).isEqualTo(false);
  }

  @Test
  public void test_ArrayLiteral() {
    assertThat(run("= {1, 2, 3} = {1, 2, 3}")).isEqualTo(true);
    assertThat(run("= {1, 2, 3} = {44}")).isEqualTo(false);
    assertThat(run("= {44, #REF!} = {44, #REF!}")).isEqualTo(true);
  }

  @Test
  public void test_NumberToBoolean() {
    assertThat(run("= 0 = TRUE")).isEqualTo(false);
    assertThat(run("= 1 = TRUE")).isEqualTo(false);
    assertThat(run("= 1 = FALSE")).isEqualTo(false);
    assertThat(run("= 0 = FALSE")).isEqualTo(false);
  }

  @Test
  public void test_NumberToString() {
    assertThat(run("= 0 = \"\"")).isEqualTo(false);
    assertThat(run("= 0 = \"0\"")).isEqualTo(false);
    assertThat(run("= 1 = \"0\"")).isEqualTo(false);
    assertThat(run("= 1 = \"1\"")).isEqualTo(false);
  }

  @Test
  public void test_NumberToArrayLiteral() {
    assertThat(run("= 0 = {0, 1, 2}")).isEqualTo(true);
    assertThat(run("= 1 = {0, 1, 2}")).isEqualTo(false);
  }

  @Test
  public void test_NumberToBlank() {
    when(collateralLookup.apply(A1, M44_RANGE)).thenReturn(null);
    when(collateralLookup.apply(A1, 0.0)).thenReturn(0.0);
    when(collateralLookup.apply(A1, 1.0)).thenReturn(1.0);
    when(collateralLookup.apply(A1, -1.0)).thenReturn(-1.0);
    assertThat(runWithLookup("= 0 = M44")).isEqualTo(true);
    assertThat(runWithLookup("= 1 = M44")).isEqualTo(false);
    assertThat(runWithLookup("= M44 = 0")).isEqualTo(true);
    assertThat(runWithLookup("= M44 = 1")).isEqualTo(false);
    assertThat(runWithLookup("= -1 = M44")).isEqualTo(false);
    assertThat(runWithLookup("= M44 = -1")).isEqualTo(false);
    verify(collateralLookup, VerificationModeFactory.times(6)).apply(A1, M44_RANGE);
    verify(collateralLookup, VerificationModeFactory.times(2)).apply(A1, 0.0);
    verify(collateralLookup, VerificationModeFactory.times(4)).apply(A1, 1.0);
    verify(collateralLookup, VerificationModeFactory.times(2)).apply(A1, -1.0);
  }

  @Test
  public void test_StringToBoolean() {
    assertThat(run("= \"TRUE\" = TRUE")).isEqualTo(false);
    assertThat(run("= \"FALSE\" = FALSE")).isEqualTo(false);
  }

  @Test
  public void test_StringToBlank() {
    when(collateralLookup.apply(A1, M44_RANGE)).thenReturn(null);
    when(collateralLookup.apply(A1, "")).thenReturn("");
    when(collateralLookup.apply(A1, "One")).thenReturn("One");
    when(collateralLookup.apply(A1, " ")).thenReturn(" ");
    assertThat(runWithLookup("= \"\" = M44")).isEqualTo(true);
    assertThat(runWithLookup("= M44 = \"\"")).isEqualTo(true);
    assertThat(runWithLookup("= \" \" = M44")).isEqualTo(false);
    assertThat(runWithLookup("= M44 = \" \"")).isEqualTo(false);
    assertThat(runWithLookup("= \"One\" = M44")).isEqualTo(false);
    assertThat(runWithLookup("= M44 = \"One\"")).isEqualTo(false);
    verify(collateralLookup, VerificationModeFactory.times(6)).apply(A1, M44_RANGE);
    verify(collateralLookup, VerificationModeFactory.times(2)).apply(A1, "");
    verify(collateralLookup, VerificationModeFactory.times(2)).apply(A1, " ");
    verify(collateralLookup, VerificationModeFactory.times(2)).apply(A1, "One");
  }

  @Test
  public void test_BooleanToBlank() {
    when(collateralLookup.apply(A1, M44_RANGE)).thenReturn(null);
    when(collateralLookup.apply(A1, true)).thenReturn(true);
    when(collateralLookup.apply(A1, false)).thenReturn(false);
    assertThat(runWithLookup("= TRUE = M44")).isEqualTo(false);
    assertThat(runWithLookup("= FALSE = M44")).isEqualTo(true);
    assertThat(runWithLookup("= M44 = TRUE")).isEqualTo(false);
    assertThat(runWithLookup("= M44 = FALSE")).isEqualTo(true);
    verify(collateralLookup, VerificationModeFactory.times(4)).apply(A1, M44_RANGE);
    verify(collateralLookup, VerificationModeFactory.times(2)).apply(A1, true);
    verify(collateralLookup, VerificationModeFactory.times(2)).apply(A1, false);
  }

  @Test
  public void test_ArrayLiteralToString() {
    assertThat(run("= {\"A\", \"B\"} = \"A\"")).isEqualTo(true);
    assertThat(run("= \"A\" = {\"A\", \"B\"}")).isEqualTo(true);
    assertThat(run("= \"B\" = {\"A\", \"B\"}")).isEqualTo(false);
  }

  @Test
  public void test_ArrayLiteralToBoolean() {
    assertThat(run("= {TRUE, FALSE} = TRUE")).isEqualTo(true);
    assertThat(run("= TRUE = {TRUE, FALSE}")).isEqualTo(true);
    assertThat(run("= {FALSE, FALSE} = TRUE")).isEqualTo(false);
    assertThat(run("= TRUE = {FALSE, FALSE}")).isEqualTo(false);
  }

  @Test
  public void test_Error() {
    assertThat(run("= TRUE = #REF!")).isEqualTo(new RefException());
    assertThat(run("= #REF! = TRUE")).isEqualTo(new RefException());
  }
}
