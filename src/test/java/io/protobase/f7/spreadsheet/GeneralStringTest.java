package io.protobase.f7.spreadsheet;

import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralStringTest extends TestExecution {
  @Test
  public void test_Empty() {
    runner().addCell("Alpha", "A1", "= \"\"").addExpectedValue("Alpha", "A1", "").run();
  }

  @Test
  public void test_Filled() {
    runner().addCell("Alpha", "A1", "= \"I am the very model of a modern major general.\"")
        .addExpectedValue("Alpha", "A1", "I am the very model of a modern major general.")
        .run();
    runner().addCell("Alpha", "A1", "= \"32879834\"").addExpectedValue("Alpha", "A1", "32879834").run();
    runner().addCell("Alpha", "A1", "= \"'Weird.'\"").addExpectedValue("Alpha", "A1", "'Weird.'").run();
  }
}
