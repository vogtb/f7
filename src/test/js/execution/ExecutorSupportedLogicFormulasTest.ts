import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Logic Formulas", function () {
  it("should support AND", function () {
    runner().addCell("Alpha", "A1", "=AND(TRUE, TRUE)").addExpectedValue("Alpha", "A1", true).run();
  });

  it("should support EQ", function () {
    runner()
      .addCell("Alpha", "A1", "=EQ(10, 298132.222)")
      .addExpectedValue("Alpha", "A1", false)
      .run();
  });

  it("should support EXACT", function () {
    runner().addCell("Alpha", "A1", '=EXACT("A", "A")').addExpectedValue("Alpha", "A1", true).run();
  });

  it("should support FALSE", function () {
    runner().addCell("Alpha", "A1", "=FALSE()").addExpectedValue("Alpha", "A1", false).run();
  });

  it("should support GTE", function () {
    runner()
      .addCell("Alpha", "A1", "=GTE(10.4, 0.11111)")
      .addExpectedValue("Alpha", "A1", true)
      .run();
  });

  it("should support GT", function () {
    runner()
      .addCell("Alpha", "A1", "=GT(10.4, 0.11111)")
      .addExpectedValue("Alpha", "A1", true)
      .run();
  });

  it("should support IF", function () {
    runner().addCell("Alpha", "A1", "=IF(true, 1, 0)").addExpectedValue("Alpha", "A1", 1).run();
  });

  it("should support IFERROR", function () {
    runner().addCell("Alpha", "A1", "=IFERROR(#NULL!, 1)").addExpectedValue("Alpha", "A1", 1).run();
  });

  it("should support IFNA", function () {
    runner().addCell("Alpha", "A1", "=IFNA(#N/A, 1)").addExpectedValue("Alpha", "A1", 1).run();
  });

  it("should support LTE", function () {
    runner()
      .addCell("Alpha", "A1", "=LTE(10.4, 0.11111)")
      .addExpectedValue("Alpha", "A1", false)
      .run();
  });

  it("should support LT", function () {
    runner()
      .addCell("Alpha", "A1", "=LT(10.4, 0.11111)")
      .addExpectedValue("Alpha", "A1", false)
      .run();
  });

  it("should support NE", function () {
    runner()
      .addCell("Alpha", "A1", "=NE(10.4, 0.11111)")
      .addExpectedValue("Alpha", "A1", true)
      .run();
  });

  it("should support NOT", function () {
    runner().addCell("Alpha", "A1", "=NOT(false)").addExpectedValue("Alpha", "A1", true).run();
  });

  it("should support OR", function () {
    runner().addCell("Alpha", "A1", "=OR(0, 0, 1)").addExpectedValue("Alpha", "A1", true).run();
  });

  it("should support OR", function () {
    runner().addCell("Alpha", "A1", "=XOR(0, 0, 1)").addExpectedValue("Alpha", "A1", true).run();
  });

  it("should support TRUE", function () {
    runner().addCell("Alpha", "A1", "=TRUE()").addExpectedValue("Alpha", "A1", true).run();
  });
});
