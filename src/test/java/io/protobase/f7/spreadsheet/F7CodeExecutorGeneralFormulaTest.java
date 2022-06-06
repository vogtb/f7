package io.protobase.f7.spreadsheet;

import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestF7CodeExecutor;
import org.junit.Test;
import org.mockito.internal.verification.VerificationModeFactory;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class F7CodeExecutorGeneralFormulaTest extends TestF7CodeExecutor {
  @Test
  public void test_WithoutArguments() {
    assertThat(run("= TRUE()")).isEqualTo(true);
    assertThat(run("= FALSE()")).isEqualTo(false);
    assertThat(run("= PI()")).isEqualTo(Math.PI);
  }

  @Test
  public void test_NumberArguments() {
    assertThat(run("= ABS(-1)")).isEqualTo(1.0);
    assertThat(run("= ADD(3, 4)")).isEqualTo(7.0);
    assertThat(run("= SUM(3, 4, 5, 6, -1)")).isEqualTo(17.0);
  }

  @Test
  public void test_StringArguments() {
    assertThat(run("= ABS(\"-1\")")).isEqualTo(1.0);
    assertThat(run("= CONCAT(\"3\", \"4\")")).isEqualTo("34");
    assertThat(run("= SUM(\"3\", \"4\", \"5\", \"6\")")).isEqualTo(18.0);
  }

  @Test
  public void test_BooleanArguments() {
    assertThat(run("= ABS(TRUE)")).isEqualTo(1.0);
    assertThat(run("= CONCAT(TRUE, FALSE)")).isEqualTo("TRUEFALSE");
    assertThat(run("= SUM(TRUE, TRUE, TRUE)")).isEqualTo(3.0);
  }

  @Test
  public void test_ArrayLiteralArguments() {
    assertThat(run("= ABS({-1, 2, 3})")).isEqualTo(1.0);
    assertThat(run("= CONCAT({TRUE}, {22.1, 22})")).isEqualTo("TRUE22.1");
    assertThat(run("= SUM({TRUE, {TRUE, {TRUE}}})")).isEqualTo(3.0);
  }

  @Test
  public void test_RangeArguments_ReturnsBlankValues() {
    when(lookup.apply(B1_B4_RANGE)).thenReturn(Grid.builder()
        .add(0, 0, null)
        .add(0, 1, null)
        .add(0, 2, null)
        .add(0, 3, null)
        .build());
    assertThat(runWithLookup("= SUM(B1:B4)")).isEqualTo(0.0);
    verify(lookup, VerificationModeFactory.times(1)).apply(B1_B4_RANGE);
  }

  @Test
  public void test_RangeArguments_ReturnsNonBlankValues() {
    when(lookup.apply(B1_B4_RANGE)).thenReturn(Grid.builder()
        .add(0, 0, 3.0)
        .add(0, 1, 4.0)
        .add(0, 2, 5.0)
        .add(0, 3, 6.0)
        .build());
    assertThat(runWithLookup("= SUM(B1:B4)")).isEqualTo(18.0);
    verify(lookup, VerificationModeFactory.times(1)).apply(B1_B4_RANGE);
  }
}
