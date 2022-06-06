import { runner, describe, it } from "../testutils/TestUtils";

describe("Executor.execute - Statistical Formulas", function () {
  it("should support AVERAGE", function () {
    runner()
      .addCell("Alpha", "A1", "=AVERAGE(1, 2, 3, 4, 5, 6)")
      .addExpectedValue("Alpha", "A1", 3.5)
      .run();
  });

  it("should support AVERAGEA", function () {
    runner()
      .addCell("Alpha", "A1", `=AVERAGEA({1, 2, 3, 4, 5, 6, "Zero", "Zer"})`)
      .addExpectedValue("Alpha", "A1", 2.625)
      .run();
  });

  it("should support COUNT", function () {
    runner().addCell("Alpha", "A1", `=COUNT(1, 2, 3, 4)`).addExpectedValue("Alpha", "A1", 4).run();
  });

  it("should support COUNTA", function () {
    runner()
      .addCell("Alpha", "A1", `=COUNTA(1, 2, 3, 4, 1/0)`)
      .addExpectedValue("Alpha", "A1", 4)
      .run();
  });

  it("should support COUNTBLANK", function () {
    runner()
      .addCell("Alpha", "A1", `=COUNTBLANK(B1:B10)`)
      .addCell("Alpha", "C88", `Pushing out grid.`)
      .addExpectedValue("Alpha", "A1", 10)
      .run();
  });

  it("should support MIN", function () {
    runner().addCell("Alpha", "A1", `=MIN(2, 4, 8)`).addExpectedValue("Alpha", "A1", 2).run();
  });

  it("should support MINA", function () {
    runner()
      .addCell("Alpha", "A1", `=MINA(2, 4, 8, {1, 2, "Nope"})`)
      .addExpectedValue("Alpha", "A1", 0)
      .run();
  });

  it("should support MAX", function () {
    runner().addCell("Alpha", "A1", `=MAX(2, 4, 8)`).addExpectedValue("Alpha", "A1", 8).run();
  });

  it("should support MAXA", function () {
    runner()
      .addCell("Alpha", "A1", `=MAXA(2, 4, 8, {1, 2, "Nope"})`)
      .addExpectedValue("Alpha", "A1", 8)
      .run();
  });
});
