import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Engineering Formulas", function () {
  it("should support BIN2DEC", function () {
    runner()
      .addCell("Alpha", "A1", `=BIN2DEC("1010101010")`)
      .addExpectedValue("Alpha", "A1", -342)
      .run();
  });

  it("should support BIN2HEX", function () {
    runner()
      .addCell("Alpha", "A1", `=BIN2HEX("1010101010")`)
      .addExpectedValue("Alpha", "A1", "FFFFFFFEAA")
      .run();
  });

  it("should support DELTA", function () {
    runner().addCell("Alpha", "A1", `=DELTA(1, 2)`).addExpectedValue("Alpha", "A1", 0).run();
  });
});
