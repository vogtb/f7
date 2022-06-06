import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Sheet", function () {
  it("should allow normal sheet names with alpha-numeric identifiers", function () {
    runner()
      .addCell("SheetNameHere", "A1", "= 10")
      .addCell("Sheet.Name.Here", "A1", "= 10")
      .addExpectedValue("SheetNameHere", "A1", 10)
      .addExpectedValue("Sheet.Name.Here", "A1", 10)
      .run();
  });

  it("should allow sheet names that are more complex", function () {
    runner()
      .addCell("This    Is     A   Sheet.Name", "A1", "= 10")
      .addCell("Alpha", "A1", "= 'This    Is     A   Sheet.Name'!A1")
      .addExpectedValue("This    Is     A   Sheet.Name", "A1", 10)
      .addExpectedValue("Alpha", "A1", 10)
      .run();
  });
});
