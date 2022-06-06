package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.NameException;
import io.protobase.f7.errors.NullException;
import io.protobase.f7.errors.NumException;
import io.protobase.f7.errors.ParseException;
import io.protobase.f7.errors.RefException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralErrorTest extends TestExecution {
  @Test
  public void test_NullError() {
    runner().addCell("Alpha", "A1", "= #NULL!").addExpectedValue("Alpha", "A1", new NullException()).run();
    runner().addCell("Alpha", "A1", "= #null!").addExpectedValue("Alpha", "A1", new NullException()).run();
  }

  @Test
  public void test_DivError() {
    runner().addCell("Alpha", "A1", "= #DIV/0!").addExpectedValue("Alpha", "A1", new DivException()).run();
    runner().addCell("Alpha", "A1", "= #div/0!").addExpectedValue("Alpha", "A1", new DivException()).run();
  }

  @Test
  public void test_ValueError() {
    runner().addCell("Alpha", "A1", "= #VALUE!").addExpectedValue("Alpha", "A1", new ValueException()).run();
    runner().addCell("Alpha", "A1", "= #value!").addExpectedValue("Alpha", "A1", new ValueException()).run();
  }

  @Test
  public void test_RefError() {
    runner().addCell("Alpha", "A1", "= #REF!").addExpectedValue("Alpha", "A1", new RefException()).run();
    runner().addCell("Alpha", "A1", "= #ref!").addExpectedValue("Alpha", "A1", new RefException()).run();
  }

  @Test
  public void test_NameError() {
    runner().addCell("Alpha", "A1", "= #NAME?").addExpectedValue("Alpha", "A1", new NameException()).run();
    runner().addCell("Alpha", "A1", "= #name?").addExpectedValue("Alpha", "A1", new NameException()).run();
  }

  @Test
  public void test_NumError() {
    runner().addCell("Alpha", "A1", "= #NUM!").addExpectedValue("Alpha", "A1", new NumException()).run();
    runner().addCell("Alpha", "A1", "= #num!").addExpectedValue("Alpha", "A1", new NumException()).run();
  }

  @Test
  public void test_NAError() {
    runner().addCell("Alpha", "A1", "= #N/A").addExpectedValue("Alpha", "A1", new NAException()).run();
    runner().addCell("Alpha", "A1", "= #n/a").addExpectedValue("Alpha", "A1", new NAException()).run();
  }

  @Test
  public void test_ParseError() {
    runner().addCell("Alpha", "A1", "= #ERROR!").addExpectedValue("Alpha", "A1", new ParseException()).run();
    runner().addCell("Alpha", "A1", "= #error!").addExpectedValue("Alpha", "A1", new ParseException()).run();
  }
}
