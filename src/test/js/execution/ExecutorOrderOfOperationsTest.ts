import { DivException } from "../../../main/js/errors/DivException";
import { NullException } from "../../../main/js/errors/NullException";
import { RefException } from "../../../main/js/errors/RefException";
import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Order of Operations", function () {
  it("should perform unary minus operation in correct order", function () {
    runner()
      .addCell("Alpha", "A1", "= -1 + -2 + -(-3 * -4) + (-5 * -6)")
      .addExpectedValue("Alpha", "A1", 15.0)
      .run();
  });

  it("should perform unary percent operation in correct order", function () {
    runner()
      .addCell("Alpha", "A1", "= 1% + -2% + -(-3% * -4)% + (-5% * -6%)%")
      .addExpectedValue("Alpha", "A1", -0.01117)
      .run();
  });

  it("should perform parentheses operations in correct order", function () {
    runner()
      .addCell("Alpha", "A1", "= 1 + 2 + (3 * 4) + (5 * 6)")
      .addExpectedValue("Alpha", "A1", 45.0)
      .run();
    runner()
      .addCell("Alpha", "A1", "= 1 * 2 * (3 + 4) * (5 + 6)")
      .addExpectedValue("Alpha", "A1", 154.0)
      .run();
    runner().addCell("Alpha", "A1", "= (6 + 3) * 3").addExpectedValue("Alpha", "A1", 27.0).run();
    runner().addCell("Alpha", "A1", "= 7 * (5 + 8)").addExpectedValue("Alpha", "A1", 91.0).run();
    runner().addCell("Alpha", "A1", "= (4 + 3) * 2").addExpectedValue("Alpha", "A1", 14.0).run();
  });

  it("should perform exponent operations in correct order", function () {
    runner().addCell("Alpha", "A1", "= 1 + 2 * 3 ^ 4").addExpectedValue("Alpha", "A1", 163.0).run();
    runner()
      .addCell("Alpha", "A1", "= 1 + 2 * ((3 ^ 4) ^ 2)")
      .addExpectedValue("Alpha", "A1", 13123.0)
      .run();
    // TODO:HACK: In Google Sheets we get 86093443 for this one. Google Sheets does order of operations differently... which is wild.
    runner()
      .addCell("Alpha", "A1", "= 1 + 2 * 3 ^ 4 ^ 2")
      .addExpectedValue("Alpha", "A1", 13123.0)
      .run();
    runner()
      .addCell("Alpha", "A1", "= 1 + 2 * 3 ^ 4 ^ 2 / 5 * 10 ^ 2")
      .addExpectedValue("Alpha", "A1", 262441.0)
      .run();
  });

  it("should perform multiplication operations in correct order", function () {
    runner()
      .addCell("Alpha", "A1", "= 2 * 3 ^ 4 * 2 + 1 / 4 * 2")
      .addExpectedValue("Alpha", "A1", 324.5)
      .run();
  });

  it("should perform division operations in correct order", function () {
    runner()
      .addCell("Alpha", "A1", "= 2 / 4 * 2 / 6")
      .addExpectedValue("Alpha", "A1", 0.16666666666666666)
      .run();
  });

  it("should perform addition operations in correct order", function () {
    runner()
      .addCell("Alpha", "A1", "= 2 + 2 / 4 + 45 * 2 + 22 / 6 + 1")
      .addExpectedValue("Alpha", "A1", 97.1666666666666)
      .run();
  });

  it("should perform subtraction operations in correct order", function () {
    runner()
      .addCell("Alpha", "A1", "= 2 - 2 / 4 - 45 * 2 - 22 / 6 - 1")
      .addExpectedValue("Alpha", "A1", -93.16666666666666)
      .run();
  });

  it("should do all operations in correct order", function () {
    runner()
      .addCell("Alpha", "A1", "= SUM(POW(2, 6), {1, 2, 3, {66 / 2} / 2} * 1 + 440)")
      .addExpectedValue("Alpha", "A1", 505.0)
      .run();
    runner()
      .addCell("Alpha", "A1", "= (11 -- 22 -- 4 / -(-2^5*1) + 2 * 1 + 2^2^2^2)")
      .addExpectedValue("Alpha", "A1", 291.125)
      .run();
    runner()
      .addCell("Alpha", "A1", "= 2 *  -3 ^ 2  + (1 / 32.571) + 2")
      .addExpectedValue("Alpha", "A1", 20.030702158361734)
      .run();
    runner()
      .addCell("Alpha", "A1", "= 10 ----3 ^ 11")
      .addExpectedValue("Alpha", "A1", 177157.0)
      .run();
    runner()
      .addCell("Alpha", "A1", "= 2 ^ 4  -(1 + 33) / 45 * 0.1111 + SUM(10, 20, 30) / DIVIDE(2, 1)")
      .addExpectedValue("Alpha", "A1", 45.91605777777778)
      .run();
    runner()
      .addCell(
        "Alpha",
        "A1",
        "= 2 ^ 4%  -(1 + 33) / 45 * 0.1111 % + SUM(10, 20, 30) / DIVIDE(2, 1)%"
      )
      .addExpectedValue("Alpha", "A1", 3001.0272744044337)
      .run();
    runner()
      .addCell("Alpha", "A1", "= 1 * 3 / 3 -9 -19 / 2 > 8 * 1.62 / -2 ^ 2")
      .addExpectedValue("Alpha", "A1", false)
      .run();
  });

  it("should handle error propagation left-to-right", function () {
    runner()
      .addCell("Alpha", "A1", "= (199 / 0) + #NULL!")
      .addExpectedValue("Alpha", "A1", new DivException())
      .run();
    runner()
      .addCell("Alpha", "A1", "= #NULL! + #NAME?")
      .addExpectedValue("Alpha", "A1", new NullException())
      .run();
    runner()
      .addCell("Alpha", "A1", "= #REF! / 0")
      .addExpectedValue("Alpha", "A1", new RefException())
      .run();
    runner()
      .addCell("Alpha", "A1", "= {1, 2, #REF!}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 1.0)
      .addExpectedValue("Alpha", "B1", 2.0)
      .addExpectedValue("Alpha", "C1", new RefException())
      .run();
  });
});
