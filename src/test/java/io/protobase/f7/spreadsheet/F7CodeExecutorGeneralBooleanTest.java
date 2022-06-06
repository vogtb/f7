package io.protobase.f7.spreadsheet;

import io.protobase.f7.testutils.TestF7CodeExecutor;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class F7CodeExecutorGeneralBooleanTest extends TestF7CodeExecutor {
  @Test
  public void test_True() {
    assertThat(run("= TRUE")).isEqualTo(true);
    assertThat(run("= true")).isEqualTo(true);
    assertThat(run("= True")).isEqualTo(true);
    assertThat(run("= TrUe")).isEqualTo(true);
    assertThat(run("= trUE")).isEqualTo(true);
  }

  @Test
  public void test_False() {
    assertThat(run("= FALSE")).isEqualTo(false);
    assertThat(run("= false")).isEqualTo(false);
    assertThat(run("= False")).isEqualTo(false);
    assertThat(run("= FaLsE")).isEqualTo(false);
    assertThat(run("= fAlSE")).isEqualTo(false);
  }
}
