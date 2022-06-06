import { RefException } from "../../../main/js/errors/RefException";
import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Dependency Checking", function () {
  it("should return REF error for cycles that occur from self-dependency", function () {
    runner()
      .addCell("Alpha", "J2", "= J2")
      .addExpectedValue("Alpha", "J2", new RefException())
      .run();
    runner()
      .addCell("Alpha", "J2", "= J2 + 1")
      .addExpectedValue("Alpha", "J2", new RefException())
      .run();
  });

  it("should return REF error for cycles that occur when two cells reference each other", function () {
    runner()
      .addCell("Alpha", "A1", "= J2")
      .addCell("Alpha", "J2", "= A1")
      .addExpectedValue("Alpha", "A1", new RefException())
      .addExpectedValue("Alpha", "J2", new RefException())
      .run();
    runner()
      .addCell("Alpha", "A1", "= J2 + 1")
      .addCell("Alpha", "J2", "= A1 + 1")
      .addExpectedValue("Alpha", "A1", new RefException())
      .addExpectedValue("Alpha", "J2", new RefException())
      .run();
  });

  it("should return REF error for cycles that occurred from indirect references (A -> B -> C -> A)", function () {
    runner()
      .addCell("Alpha", "A1", "= J2 + 1")
      .addCell("Alpha", "J2", "= D8 + 1")
      .addCell("Alpha", "D8", "= A1 + 1")
      .addExpectedValue("Alpha", "A1", new RefException())
      .addExpectedValue("Alpha", "J2", new RefException())
      .addExpectedValue("Alpha", "D8", new RefException())
      .run();
    runner()
      .addCell("Alpha", "D4", "= A1 + 1")
      .addCell("Alpha", "A2", "= D4 + 1")
      .addCell("Alpha", "A1", "= A2 + 1")
      .addExpectedValue("Alpha", "D4", new RefException())
      .addExpectedValue("Alpha", "A2", new RefException())
      .addExpectedValue("Alpha", "A1", new RefException())
      .run();
  });

  it("should return REF error for cycles that occur when two cells reference each other across grids", function () {
    runner()
      .addCell("Alpha", "A1", "= Beta!J2 + 1")
      .addCell("Beta", "J2", "= Alpha!A1 + 1")
      .addExpectedValue("Alpha", "A1", new RefException())
      .addExpectedValue("Beta", "J2", new RefException())
      .run();
  });

  it("should return REF error for cycles that occurred from indirect references (A -> B -> C -> A) across grids", function () {
    runner()
      .addCell("Alpha", "A1", "= Beta!J2 + 1")
      .addCell("Beta", "J2", "= Charlie!D8 + 1")
      .addCell("Charlie", "D8", "= Alpha!A1 + 1")
      .addExpectedValue("Alpha", "A1", new RefException())
      .addExpectedValue("Beta", "J2", new RefException())
      .addExpectedValue("Charlie", "D8", new RefException())
      .run();
  });

  it("should return REF error for cycles that occur when a range query contains itself", function () {
    runner()
      .addCell("Alpha", "A4", "= {A1:A8}")
      .addExpectedValue("Alpha", "A4", new RefException())
      .run();
    runner()
      .addCell("Alpha", "A4", "= A1:A8")
      .addExpectedValue("Alpha", "A4", new RefException())
      .run();
  });

  it("should return REF error for cycles that occur when a range query contains indirect references", function () {
    runner()
      .addCell("Alpha", "A1", "= D10")
      .addCell("Alpha", "D10", "= {A1:A2}")
      .addExpectedValue("Alpha", "A1", new RefException())
      .addExpectedValue("Alpha", "D10", new RefException())
      .run();
  });
});
