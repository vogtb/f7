import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Boolean", function () {
  it("should work with TRUE", function () {
    runner().addCell("Alpha", "A1", "= TRUE").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= true").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= True").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= TrUe").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= trUE").addExpectedValue("Alpha", "A1", true).run();
  });

  it("should work with FALSE", function () {
    runner().addCell("Alpha", "A1", "= FALSE").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= false").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= False").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= FaLsE").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= fAlSE").addExpectedValue("Alpha", "A1", false).run();
  });
});
