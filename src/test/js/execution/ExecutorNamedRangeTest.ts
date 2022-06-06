import { NameException } from "../../../main/js/errors/NameException";
import { ParseException } from "../../../main/js/errors/ParseException";
import { describe, it, runner } from "../testutils/TestUtils";

describe("Executor.execute - Named Range", function () {
  it("should allow alpha-numeric characters, numbers, underscores, and periods", function () {
    runner()
      .addNamedRange("Super.Name_Here10Here..Stuff", "Alpha!A1:A1")
      .addCell("Alpha", "A1", "= 10")
      .addCell("Alpha", "A2", "= Super.Name_Here10Here..Stuff")
      .addExpectedValue("Alpha", "A2", 10)
      .run();
  });

  it("should have a max length of 255 characters", function () {
    runner()
      .addCell(
        "Alpha",
        "A1",
        "= NamedRangeIsTooLongNamedRangeIsTooLongNamedRangeIsTooLongNamedRangeIsTooLong" +
          "NamedRangeIsTooLongNamedRangeIsTooLongNamedRangeIsTooLongNamedRangeIsTooLongNamedRangeIsTooLong" +
          "NamedRangeIsTooLongNamedRangeIsTooLongNamedRangeIsTooLongNamedRangeIsTooLongTooLongOk"
      )
      .addExpectedValue("Alpha", "A1", new ParseException())
      .run();
    runner()
      .addNamedRange(
        "NamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLong" +
          "NamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLong" +
          "NamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongABCDE",
        "Alpha!A1:A1"
      )
      .addCell("Alpha", "A1", "= 10")
      .addCell(
        "Alpha",
        "A2",
        "= NamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLong" +
          "NamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLong" +
          "NamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongNamedRangeIsAlmostTooLongABCDE"
      )
      .addExpectedValue("Alpha", "A1", 10)
      .addExpectedValue("Alpha", "A2", 10)
      .run();
  });

  it("should return NAME exception when named range is not found", function () {
    runner()
      .addCell("Alpha", "A1", "= NotFoundRange")
      .addExpectedValue("Alpha", "A1", new NameException())
      .run();
  });
});
