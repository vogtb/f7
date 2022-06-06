import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Expressions", function () {
  it("should work with parentheses", function () {
    runner().addCell("Alpha", "A1", "= (TRUE)").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= (2 + 1) * 4").addExpectedValue("Alpha", "A1", 12.0).run();
  });
});
