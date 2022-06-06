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

public class GeneralDivisionTest extends TestExecution {
  @Test
  public void test_Numbers() {
    runner().addCell("Alpha", "A1", "= 10 / 5").addExpectedValue("Alpha", "A1", 2.0).run();
    runner().addCell("Alpha", "A1", "= 1 / 1").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= 10 / -10").addExpectedValue("Alpha", "A1", -1.0).run();
    runner().addCell("Alpha", "A1", "= -10 / -2").addExpectedValue("Alpha", "A1", 5.0).run();
    runner().addCell("Alpha", "A1", "= 1e10 / 1.1").addExpectedValue("Alpha", "A1", 9.09090909090909E9).run();
  }

  @Test
  public void test_Boolean() {
    runner().addCell("Alpha", "A1", "= 9 / TRUE").addExpectedValue("Alpha", "A1", 9.0).run();
    runner().addCell("Alpha", "A1", "= 9 / FALSE").addExpectedValue("Alpha", "A1", new DivException()).run();
  }

  @Test
  public void test_String() {
    runner().addCell("Alpha", "A1", "= 12 / \"3\"").addExpectedValue("Alpha", "A1", 4.0).run();
    runner().addCell("Alpha", "A1", "= 12 / \"3e1\"").addExpectedValue("Alpha", "A1", 0.4).run();
    runner().addCell("Alpha", "A1", "= 12 / \"2\"").addExpectedValue("Alpha", "A1", 6.0).run();
  }

  @Test
  public void test_String_NotNumber() {
    runner().addCell("Alpha", "A1", "= 9 / \"No good.\"").addExpectedValue("Alpha", "A1", new ValueException()).run();
    runner().addCell("Alpha", "A1", "= 9 / \"10    10\"").addExpectedValue("Alpha", "A1", new ValueException()).run();
  }

  @Test
  public void test_Array() {
    runner().addCell("Alpha", "A1", "= 12 / {2}").addExpectedValue("Alpha", "A1", 6.0).run();
    runner().addCell("Alpha", "A1", "= 12 / {2, 44}").addExpectedValue("Alpha", "A1", 6.0).run();
    runner().addCell("Alpha", "A1", "= 12 / -{2, \"Ignore me.\"}").addExpectedValue("Alpha", "A1", -6.0).run();
  }

  @Test
  public void test_Blank() {
    runner().addCell("Alpha", "A1", "= 2 / M44").addExpectedValue("Alpha", "A1", new DivException()).run();
  }

  @Test
  public void test_Errors() {
    runner()
        .addCell("Alpha", "A1", "= 3 / #NULL!")
        .addCell("Alpha", "A2", "= 3 / #DIV/0!")
        .addCell("Alpha", "A3", "= 3 / #VALUE!")
        .addCell("Alpha", "A4", "= 3 / #REF!")
        .addCell("Alpha", "A5", "= 3 / #NAME?")
        .addCell("Alpha", "A6", "= 3 / #NUM!")
        .addCell("Alpha", "A7", "= 3 / #N/A")
        .addCell("Alpha", "A8", "= 3 / #ERROR!")
        .addExpectedValue("Alpha", "A1", new NullException())
        .addExpectedValue("Alpha", "A2", new DivException())
        .addExpectedValue("Alpha", "A3", new ValueException())
        .addExpectedValue("Alpha", "A4", new RefException())
        .addExpectedValue("Alpha", "A5", new NameException())
        .addExpectedValue("Alpha", "A6", new NumException())
        .addExpectedValue("Alpha", "A7", new NAException())
        .addExpectedValue("Alpha", "A8", new ParseException())
        .run();
  }
}
