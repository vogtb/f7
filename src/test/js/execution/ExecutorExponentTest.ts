import { NumException } from "../../../main/js/errors/NumException";
import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Exponents", function () {
  it("should work with positive number, positive exponent", function () {
    runner().addCell("Alpha", "A1", "= 3^5").addExpectedValue("Alpha", "A1", 243.0).run();
    runner().addCell("Alpha", "A1", "= 4^5").addExpectedValue("Alpha", "A1", 1024.0).run();
    runner().addCell("Alpha", "A1", "= 1^1").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= 1^5").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= 2^0").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= 0^10").addExpectedValue("Alpha", "A1", 0.0).run();
  });

  it("should work with positive number, negative exponent", function () {
    runner()
      .addCell("Alpha", "A1", "= 3^-5")
      .addExpectedValue("Alpha", "A1", 0.00411522633744856)
      .run();
    runner().addCell("Alpha", "A1", "= 4^-5").addExpectedValue("Alpha", "A1", 9.765625e-4).run();
    runner().addCell("Alpha", "A1", "= 1^-1").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= 1^-5").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= 2^-0").addExpectedValue("Alpha", "A1", 1.0).run();
    // TODO/HACK: This is different in Excel and Google Sheets.
    runner()
      .addCell("Alpha", "A1", "= 0^-10")
      .addExpectedValue("Alpha", "A1", new NumException())
      .run();
  });

  it("should work with negative number, negative exponent", function () {
    runner()
      .addCell("Alpha", "A1", "= -3^-1")
      .addExpectedValue("Alpha", "A1", -0.3333333333333333)
      .run();
    runner()
      .addCell("Alpha", "A1", "= -3^-2")
      .addExpectedValue("Alpha", "A1", 0.1111111111111111)
      .run();
    runner()
      .addCell("Alpha", "A1", "= -3^-3")
      .addExpectedValue("Alpha", "A1", -0.037037037037037035)
      .run();
    runner().addCell("Alpha", "A1", "= -2^-3").addExpectedValue("Alpha", "A1", -0.125).run();
    runner().addCell("Alpha", "A1", "= -1^-3").addExpectedValue("Alpha", "A1", -1.0).run();
    runner().addCell("Alpha", "A1", "= -1^-2").addExpectedValue("Alpha", "A1", 1.0).run();
  });

  it("should work with negative number, positive exponent", function () {
    runner().addCell("Alpha", "A1", "= -3^1").addExpectedValue("Alpha", "A1", -3.0).run();
    runner().addCell("Alpha", "A1", "= -3^2").addExpectedValue("Alpha", "A1", 9.0).run();
    runner().addCell("Alpha", "A1", "= -3^3").addExpectedValue("Alpha", "A1", -27.0).run();
    runner().addCell("Alpha", "A1", "= -2^3").addExpectedValue("Alpha", "A1", -8.0).run();
    runner().addCell("Alpha", "A1", "= -1^3").addExpectedValue("Alpha", "A1", -1.0).run();
    runner().addCell("Alpha", "A1", "= -1^2").addExpectedValue("Alpha", "A1", 1.0).run();
  });

  it("should follow the order of operations correctly with respect to left-association", function () {
    runner().addCell("Alpha", "A1", "= 2 ^ 3 ^ 4").addExpectedValue("Alpha", "A1", 4096.0).run();
    runner().addCell("Alpha", "A1", "= 3 ^ 2 ^ 4").addExpectedValue("Alpha", "A1", 6561.0).run();
    runner().addCell("Alpha", "A1", "= 4 ^ 2 ^ 3").addExpectedValue("Alpha", "A1", 4096.0).run();
  });

  it("should work with booleans", function () {
    runner().addCell("Alpha", "A1", "= 9 ^ TRUE").addExpectedValue("Alpha", "A1", 9.0).run();
    runner().addCell("Alpha", "A1", "= 9 ^ FALSE").addExpectedValue("Alpha", "A1", 1.0).run();
  });

  it("should work with strings", function () {
    runner().addCell("Alpha", "A1", '= 2 ^ "3"').addExpectedValue("Alpha", "A1", 8.0).run();
    runner()
      .addCell("Alpha", "A1", '= 2 ^ "3e1"')
      .addExpectedValue("Alpha", "A1", 1.073741824e9)
      .run();
    runner().addCell("Alpha", "A1", '= 2 ^ "2"').addExpectedValue("Alpha", "A1", 4.0).run();
  });
});
