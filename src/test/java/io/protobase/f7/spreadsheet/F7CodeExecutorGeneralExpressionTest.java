package io.protobase.f7.spreadsheet;

import io.protobase.f7.testutils.TestF7CodeExecutor;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class F7CodeExecutorGeneralExpressionTest extends TestF7CodeExecutor {
  @Test
  public void test_Parentheses() {
    assertThat(run("= (TRUE)")).isEqualTo(true);
    assertThat(run("= (2 + 1) * 4")).isEqualTo(12.0);
  }
}
