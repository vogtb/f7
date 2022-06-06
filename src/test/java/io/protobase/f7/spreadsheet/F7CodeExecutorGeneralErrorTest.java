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

public class F7CodeExecutorGeneralErrorTest extends TestF7CodeExecutor {
  @Test
  public void test_NullError() {
    assertThat(run("= #NULL!")).isEqualTo(new NullException());
    assertThat(run("= #null!")).isEqualTo(new NullException());
  }

  @Test
  public void test_DivError() {
    assertThat(run("= #DIV/0!")).isEqualTo(new DivException());
    assertThat(run("= #div/0!")).isEqualTo(new DivException());
  }

  @Test
  public void test_ValueError() {
    assertThat(run("= #VALUE!")).isEqualTo(new ValueException());
    assertThat(run("= #value!")).isEqualTo(new ValueException());
  }

  @Test
  public void test_RefError() {
    assertThat(run("= #REF!")).isEqualTo(new RefException());
    assertThat(run("= #ref!")).isEqualTo(new RefException());
  }

  @Test
  public void test_NameError() {
    assertThat(run("= #NAME?")).isEqualTo(new NameException());
    assertThat(run("= #name?")).isEqualTo(new NameException());
  }

  @Test
  public void test_NumError() {
    assertThat(run("= #NUM!")).isEqualTo(new NumException());
    assertThat(run("= #num!")).isEqualTo(new NumException());
  }

  @Test
  public void test_NAError() {
    assertThat(run("= #N/A")).isEqualTo(new NAException());
    assertThat(run("= #n/a")).isEqualTo(new NAException());
  }

  @Test
  public void test_ParseError() {
    assertThat(run("= #ERROR!")).isEqualTo(new ParseException());
    assertThat(run("= #error!")).isEqualTo(new ParseException());
  }
}
