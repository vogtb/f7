import { NameException } from "../../../main/js/errors/NameException";
import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Formulas", function () {
  it("should call formulas without arguments", function () {
    runner().addCell("Alpha", "A1", "= TRUE()").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= FALSE()").addExpectedValue("Alpha", "A1", false).run();
  });

  it("should call formulas with number arguments", function () {
    runner().addCell("Alpha", "A1", "= ADD(3, 4)").addExpectedValue("Alpha", "A1", 7.0).run();
  });

  it("should call formulas with string arguments", function () {
    runner()
      .addCell("Alpha", "A1", '= CONCAT("3", "4")')
      .addExpectedValue("Alpha", "A1", "34")
      .run();
  });

  it("should call formulas with boolean arguments", function () {
    runner()
      .addCell("Alpha", "A1", "= CONCAT(TRUE, FALSE)")
      .addExpectedValue("Alpha", "A1", "TRUEFALSE")
      .run();
  });

  it("should call formulas with array literal arguments", function () {
    runner()
      .addCell("Alpha", "A1", "= CONCAT({TRUE}, {22.1, 22})")
      .addExpectedValue("Alpha", "A1", "TRUE22.1")
      .run();
  });

  it("should call formulas with range arguments", function () {
    runner().addCell("Alpha", "A1", "= SUM(B1:B4)").addExpectedValue("Alpha", "A1", 0).run();
    runner()
      .addCell("Alpha", "A1", "= SUM(B1:B4)")
      .addCell("Alpha", "B1", "= 3")
      .addCell("Alpha", "B2", "= 4")
      .addCell("Alpha", "B3", "= 5")
      .addCell("Alpha", "B4", "= 6")
      .addExpectedValue("Alpha", "A1", 18)
      .run();
  });

  it("should call formulas with named range arguments", function () {
    runner()
      .addNamedRange("Values", "Alpha!B1:B4")
      .addCell("Alpha", "A1", "= SUM(Values)")
      .addCell("Alpha", "B1", "= 3")
      .addCell("Alpha", "B2", "= 4")
      .addCell("Alpha", "B3", "= 5")
      .addCell("Alpha", "B4", "= 6")
      .addExpectedValue("Alpha", "A1", 18)
      .run();
  });

  it("should allow nested formulas", function () {
    runner()
      .addCell("Alpha", "A1", "= SUM(3, 4, 5, CONCAT(10, 10))")
      .addExpectedValue("Alpha", "A1", 1022.0)
      .run();
  });

  it("should return NAME error when formula does not exist", function () {
    runner()
      .addCell("Alpha", "A1", "= NOTFOUND(10, 20)")
      .addExpectedValue("Alpha", "A1", new NameException())
      .run();
  });
});
