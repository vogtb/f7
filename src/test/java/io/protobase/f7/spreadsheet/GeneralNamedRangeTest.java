package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.NameException;
import io.protobase.f7.errors.ParseException;
import io.protobase.f7.testutils.TestExecution;
import org.junit.Ignore;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class GeneralNamedRangeTest extends TestExecution {
  @Test
  public void test_AllowedCharacter() {
    runner()
        .addNamedRange("Super.Name_Here10Here..Stuff", "Alpha!A1:A1")
        .addCell("Alpha", "A1", "= 10")
        .addCell("Alpha", "A2", "= Super.Name_Here10Here..Stuff")
        .addExpectedValue("Alpha", "A2", 10.0)
        .run();
  }

  @Test
  public void test_NamedRangeGridRequired() {
    IllegalStateException expected = null;
    try {
      runner()
          .addNamedRange("Super.Name_Here10Here..Stuff", "A1:A1")
          .addCell("Alpha", "A1", "= 10")
          .addCell("Alpha", "A2", "= Super.Name_Here10Here..Stuff")
          .run();

    } catch (IllegalStateException ex) {
      expected = ex;
    }
    assertThat(expected).isNotNull();
  }

  @Test
  public void test_Length() {
    runner()
        .addCell("Alpha", "A1", "= NamedRangeIsTooLongNamedRangeIsTooLongNamedRangeIsTooLongNamedRangeIsTooLong" +
            "NamedRangeIsTooLongNamedRangeIsTooLongNamedRangeIsTooLongNamedRangeIsTooLongNamedRangeIsTooLong" +
            "NamedRangeIsTooLongNamedRangeIsTooLongNamedRangeIsTooLongNamedRangeIsTooLongTooLongOk")
        .addExpectedValue("Alpha", "A1", new ParseException())
        .run();
    runner()
        .addNamedRange("NamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLong" +
            "NamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLong" +
            "NamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongABCDE", "Alpha!A1:A1")
        .addCell("Alpha", "A1", "= 10")
        .addCell("Alpha", "A2", "= NamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLong" +
            "NamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLong" +
            "NamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongABCDE")
        .addExpectedValue("Alpha", "A1", 10.0)
        .addExpectedValue("Alpha", "A2", 10.0)
        .run();
  }

  @Test
  public void test_ErrorWhenNotFound() {
    runner()
        .addCell("Alpha", "A1", "= NotFoundRange")
        .addExpectedValue("Alpha", "A1", new NameException())
        .run();
  }

  @Test
  @Ignore
  public void test_NamedRangeAndRegularRange() {
    runner()
        .addNamedRange("MyRange", "Alpha!A1:A4")
        .addCell("Alpha", "A1", "= 1")
        .addCell("Alpha", "A2", "= 2")
        .addCell("Alpha", "A3", "= 3")
        .addCell("Alpha", "A4", "= 4")
        .addCell("Alpha", "A5", "= 5")
        .addCell("Alpha", "A6", "= 6")
        .addCell("Alpha", "B1", "= SUM(MyRange)")
        .addCell("Alpha", "B2", "= SUM(MyRange:A6)") // TODO: Fix. MyRange is being parsed as a column by F7.g4
        .addExpectedValue("Alpha", "B1", 10.0)
        .addExpectedValue("Alpha", "B2", 26.0)
        .run();
  }
}
