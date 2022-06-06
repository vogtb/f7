package io.protobase.f7.antlr;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.F7ExceptionName;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.NameException;
import io.protobase.f7.errors.NullException;
import io.protobase.f7.errors.RefException;
import io.protobase.f7.errors.ValueException;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class F7ExceptionTest {
  private static String ERROR_MESSAGE = "ERROR_MESSAGE";

  @Test
  public void testDivError() {
    DivException error = new DivException(ERROR_MESSAGE);
    assertThat(error.getName()).isEqualTo(F7ExceptionName.DIV);
    assertThat(error.getMessage()).isEqualTo(ERROR_MESSAGE);
  }

  @Test
  public void testNAError() {
    NAException error = new NAException(ERROR_MESSAGE);
    assertThat(error.getName()).isEqualTo(F7ExceptionName.NA);
    assertThat(error.getMessage()).isEqualTo(ERROR_MESSAGE);
  }

  @Test
  public void testNameError() {
    NameException error = new NameException(ERROR_MESSAGE);
    assertThat(error.getName()).isEqualTo(F7ExceptionName.NAME);
    assertThat(error.getMessage()).isEqualTo(ERROR_MESSAGE);
  }

  @Test
  public void testNullError() {
    NullException error = new NullException(ERROR_MESSAGE);
    assertThat(error.getName()).isEqualTo(F7ExceptionName.NULL);
    assertThat(error.getMessage()).isEqualTo(ERROR_MESSAGE);
  }

  @Test
  public void testRefError() {
    RefException error = new RefException(ERROR_MESSAGE);
    assertThat(error.getName()).isEqualTo(F7ExceptionName.REF);
    assertThat(error.getMessage()).isEqualTo(ERROR_MESSAGE);
  }

  @Test
  public void testValueError() {
    ValueException error = new ValueException(ERROR_MESSAGE);
    assertThat(error.getName()).isEqualTo(F7ExceptionName.VALUE);
    assertThat(error.getMessage()).isEqualTo(ERROR_MESSAGE);
  }
}
