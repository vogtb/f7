import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Number", function () {
  it("should work with integers", function () {
    runner().addCell("Alpha", "A1", "= 0").addExpectedValue("Alpha", "A1", 0.0).run();
    runner().addCell("Alpha", "A1", "= 1").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= 2738281").addExpectedValue("Alpha", "A1", 2738281.0).run();
    runner().addCell("Alpha", "A1", "= 0001776").addExpectedValue("Alpha", "A1", 1776.0).run();
  });

  it("should work with decimals", function () {
    runner().addCell("Alpha", "A1", "= 0.0").addExpectedValue("Alpha", "A1", 0.0).run();
    runner().addCell("Alpha", "A1", "= 0.187328").addExpectedValue("Alpha", "A1", 0.187328).run();
    runner()
      .addCell("Alpha", "A1", "= 38133.09128901")
      .addExpectedValue("Alpha", "A1", 38133.09128901)
      .run();
    runner()
      .addCell("Alpha", "A1", "= 4.00000000000001")
      .addExpectedValue("Alpha", "A1", 4.00000000000001)
      .run();
    runner()
      .addCell("Alpha", "A1", "= 33.0000000000000")
      .addExpectedValue("Alpha", "A1", 33.0)
      .run();
  });

  it("should work with inferred positive scientific notation", function () {
    runner().addCell("Alpha", "A1", "= 12387e0").addExpectedValue("Alpha", "A1", 12387.0).run();
    runner().addCell("Alpha", "A1", "= 12387e1").addExpectedValue("Alpha", "A1", 123870.0).run();
    runner().addCell("Alpha", "A1", "= 1.0e4").addExpectedValue("Alpha", "A1", 10000.0).run();
    runner().addCell("Alpha", "A1", "= 0.0009e4").addExpectedValue("Alpha", "A1", 9.0).run();
  });

  it("should work with positive scientific notation", function () {
    runner().addCell("Alpha", "A1", "= 12387e+0").addExpectedValue("Alpha", "A1", 12387.0).run();
    runner().addCell("Alpha", "A1", "= 12387e+1").addExpectedValue("Alpha", "A1", 123870.0).run();
    runner().addCell("Alpha", "A1", "= 1.0e+4").addExpectedValue("Alpha", "A1", 10000.0).run();
    runner().addCell("Alpha", "A1", "= 0.0009e+4").addExpectedValue("Alpha", "A1", 9.0).run();
  });

  it("should work with negative scientific notation", function () {
    runner().addCell("Alpha", "A1", "= 12387e-0").addExpectedValue("Alpha", "A1", 12387).run();
    runner().addCell("Alpha", "A1", "= 12387e-1").addExpectedValue("Alpha", "A1", 12387e-1).run();
    runner().addCell("Alpha", "A1", "= 1.0e-4").addExpectedValue("Alpha", "A1", 1.0e-4).run();
    runner().addCell("Alpha", "A1", "= 0.0009e-4").addExpectedValue("Alpha", "A1", 0.0009e-4).run();
  });
});
