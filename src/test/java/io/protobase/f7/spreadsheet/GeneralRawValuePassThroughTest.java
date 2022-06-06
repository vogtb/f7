package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NullException;
import io.protobase.f7.errors.RefException;
import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralRawValuePassThroughTest extends TestExecution {
  @Test
  public void test_PassThrough() {
    runner()
        .addCell("Alpha", "A1", "1")
        .addCell("Alpha", "A2", "2")
        .addCell("Alpha", "A3", "4")
        .addCell("Alpha", "A4", "8")
        .addCell("Alpha", "A5", "16")
        .addCell("Alpha", "A6", "32")
        .addCell("Alpha", "A7", "= SUM(A1:A6)")
        .addExpectedValue("Alpha", "A1", "1")
        .addExpectedValue("Alpha", "A2", "2")
        .addExpectedValue("Alpha", "A3", "4")
        .addExpectedValue("Alpha", "A4", "8")
        .addExpectedValue("Alpha", "A5", "16")
        .addExpectedValue("Alpha", "A6", "32")
        .addExpectedValue("Alpha", "A7", 63.0)
        .run();
    runner()
        .addCell("Alpha", "A1", "91287319")
        .addCell("Alpha", "A2", "Text Value Here")
        .addCell("Alpha", "A3", "0.1832773e10")
        .addExpectedValue("Alpha", "A1", "91287319")
        .addExpectedValue("Alpha", "A2", "Text Value Here")
        .addExpectedValue("Alpha", "A3", "0.1832773e10")
        .run();
  }

  @Test
  public void test_ErrorAsStringShouldBeCastToError() {
    runner()
        .addCell("Alpha", "A1", "#DIV/0!")
        .addCell("Alpha", "A2", "#NULL!")
        .addCell("Alpha", "A3", "#REF!")
        .addExpectedValue("Alpha", "A1", new DivException())
        .addExpectedValue("Alpha", "A2", new NullException())
        .addExpectedValue("Alpha", "A3", new RefException())
        .run();
  }
}
