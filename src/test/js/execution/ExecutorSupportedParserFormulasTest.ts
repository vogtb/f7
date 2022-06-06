import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Parser Formulas", function () {
  it("should support TO_TEXT", function () {
    runner().addCell("Alpha", "A1", "=TO_TEXT(1/2)").addExpectedValue("Alpha", "A1", "0.5").run();
  });

  it("should support TO_PERCENT", function () {
    runner()
      .addCell("Alpha", "A1", "=TO_PERCENT(2.11)")
      .addExpectedValue("Alpha", "A1", 2.11)
      .run();
  });
});
