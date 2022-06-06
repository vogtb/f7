import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Math Formulas", function () {
  it("should support ABS", function () {
    runner().addCell("Alpha", "A1", "=ABS(-1)").addExpectedValue("Alpha", "A1", 1).run();
  });

  it("should support ACOS", function () {
    runner().addCell("Alpha", "A1", "=ACOS(0)").addExpectedValue("Alpha", "A1", Math.acos(0)).run();
  });

  it("should support ACOSH", function () {
    runner()
      .addCell("Alpha", "A1", "=ACOSH(2)")
      .addExpectedValue("Alpha", "A1", 1.3169578969248166)
      .run();
  });

  it("should support ACOT", function () {
    runner()
      .addCell("Alpha", "A1", "=ACOT(10)")
      .addExpectedValue("Alpha", "A1", 0.09966865249116204)
      .run();
  });

  it("should support ACOTH", function () {
    runner()
      .addCell("Alpha", "A1", "=ACOTH(10)")
      .addExpectedValue("Alpha", "A1", 0.10033534773107562)
      .run();
  });

  it("should support ASIN", function () {
    runner()
      .addCell("Alpha", "A1", "=ASIN(0.1)")
      .addExpectedValue("Alpha", "A1", Math.asin(0.1))
      .run();
  });

  it("should support ASINH", function () {
    runner()
      .addCell("Alpha", "A1", "=ASINH(10)")
      .addExpectedValue("Alpha", "A1", 2.99822295029797)
      .run();
  });

  it("should support ATAN", function () {
    runner()
      .addCell("Alpha", "A1", "=ATAN(10)")
      .addExpectedValue("Alpha", "A1", Math.atan(10))
      .run();
  });

  it("should support ATAN2", function () {
    runner()
      .addCell("Alpha", "A1", "=ATAN2(10, 4)")
      .addExpectedValue("Alpha", "A1", Math.atan2(10, 4))
      .run();
  });

  it("should support ATANH", function () {
    runner()
      .addCell("Alpha", "A1", "=ATANH(0.489733)")
      .addExpectedValue("Alpha", "A1", 0.5357090350574656)
      .run();
  });

  it("should support COS", function () {
    runner()
      .addCell("Alpha", "A1", "=COS(0.489733)")
      .addExpectedValue("Alpha", "A1", Math.cos(0.489733))
      .run();
  });

  it("should support COSH", function () {
    runner()
      .addCell("Alpha", "A1", "=COSH(0.489733)")
      .addExpectedValue("Alpha", "A1", Math["cosh"](0.489733))
      .run();
  });

  it("should support COT", function () {
    runner()
      .addCell("Alpha", "A1", "=COT(1)")
      .addExpectedValue("Alpha", "A1", 0.6420926159343306)
      .run();
  });

  it("should support ADD", function () {
    runner().addCell("Alpha", "A1", "=ADD(1.0, 2)").addExpectedValue("Alpha", "A1", 3).run();
  });

  it("should support DIVIDE", function () {
    runner().addCell("Alpha", "A1", "=DIVIDE(1.0, 2)").addExpectedValue("Alpha", "A1", 0.5).run();
  });

  it("should support EVEN", function () {
    runner().addCell("Alpha", "A1", "=EVEN(1.1)").addExpectedValue("Alpha", "A1", 2).run();
  });

  it("should support FLOOR", function () {
    runner().addCell("Alpha", "A1", "=FLOOR(1.9)").addExpectedValue("Alpha", "A1", 1).run();
  });

  it("should support INT", function () {
    runner().addCell("Alpha", "A1", "=INT(1.1)").addExpectedValue("Alpha", "A1", 1).run();
  });

  it("should support ISEVEN", function () {
    runner().addCell("Alpha", "A1", "=ISEVEN(4)").addExpectedValue("Alpha", "A1", true).run();
  });

  it("should support ISODD", function () {
    runner().addCell("Alpha", "A1", "=ISODD(1)").addExpectedValue("Alpha", "A1", true).run();
  });

  it("should support LN", function () {
    runner()
      .addCell("Alpha", "A1", "=LN(2)")
      .addExpectedValue("Alpha", "A1", 0.6931471805599453)
      .run();
  });

  it("should support LOG", function () {
    runner()
      .addCell("Alpha", "A1", "=LOG(10, 2)")
      .addExpectedValue("Alpha", "A1", 3.3219280948873626)
      .run();
  });

  it("should support LOG10", function () {
    runner()
      .addCell("Alpha", "A1", "=LOG10(128)")
      .addExpectedValue("Alpha", "A1", Math["log10"](128))
      .run();
  });

  it("should support MINUS", function () {
    runner().addCell("Alpha", "A1", "=MINUS(1, 2)").addExpectedValue("Alpha", "A1", -1).run();
  });

  it("should support MOD", function () {
    runner().addCell("Alpha", "A1", "=MOD(10, 3)").addExpectedValue("Alpha", "A1", 1).run();
  });

  it("should support MULTIPLY", function () {
    runner().addCell("Alpha", "A1", "=MULTIPLY(2.0, 4)").addExpectedValue("Alpha", "A1", 8).run();
  });

  it("should support ODD", function () {
    runner().addCell("Alpha", "A1", "=ODD(2)").addExpectedValue("Alpha", "A1", 3).run();
  });

  it("should support PI", function () {
    runner().addCell("Alpha", "A1", "=PI()").addExpectedValue("Alpha", "A1", Math.PI).run();
  });

  it("should support POW", function () {
    runner().addCell("Alpha", "A1", "=POW(2.0, 6)").addExpectedValue("Alpha", "A1", 64).run();
  });

  it("should support POWER", function () {
    runner().addCell("Alpha", "A1", "=POWER(2, 6)").addExpectedValue("Alpha", "A1", 64).run();
  });

  it("should support PRODUCT", function () {
    runner().addCell("Alpha", "A1", "=PRODUCT(2, 6, 2)").addExpectedValue("Alpha", "A1", 24).run();
  });

  it("should support QUOTIENT", function () {
    runner().addCell("Alpha", "A1", "=QUOTIENT(10, 3)").addExpectedValue("Alpha", "A1", 3).run();
  });

  it("should support ROUNDDOWN", function () {
    runner().addCell("Alpha", "A1", "=ROUNDDOWN(2.11)").addExpectedValue("Alpha", "A1", 2).run();
  });

  it("should support ROUNDUP", function () {
    runner().addCell("Alpha", "A1", "=ROUNDUP(2.11)").addExpectedValue("Alpha", "A1", 3).run();
  });

  it("should support ROUND", function () {
    runner().addCell("Alpha", "A1", "=ROUND(2.11)").addExpectedValue("Alpha", "A1", 2).run();
  });

  it("should support SIGN", function () {
    runner().addCell("Alpha", "A1", "=SIGN(8)").addExpectedValue("Alpha", "A1", 1).run();
  });

  it("should support SIN", function () {
    runner()
      .addCell("Alpha", "A1", "=SIN(2)")
      .addExpectedValue("Alpha", "A1", 0.9092974268256817)
      .run();
  });

  it("should support SINH", function () {
    runner()
      .addCell("Alpha", "A1", "=SINH(2)")
      .addExpectedValue("Alpha", "A1", Math["sinh"](2))
      .run();
  });

  it("should support SQRT", function () {
    runner().addCell("Alpha", "A1", "=SQRT(2)").addExpectedValue("Alpha", "A1", Math.sqrt(2)).run();
  });

  it("should support SQRTPI", function () {
    runner()
      .addCell("Alpha", "A1", "=SQRTPI(2)")
      .addExpectedValue("Alpha", "A1", 2.5066282746310002)
      .run();
  });

  it("should support SUM", function () {
    runner().addCell("Alpha", "A1", "=SUM(2, 6)").addExpectedValue("Alpha", "A1", 8).run();
  });

  it("should support TAN", function () {
    runner().addCell("Alpha", "A1", "=TAN(2)").addExpectedValue("Alpha", "A1", Math.tan(2)).run();
  });

  it("should support TANH", function () {
    runner()
      .addCell("Alpha", "A1", "=TANH(2)")
      .addExpectedValue("Alpha", "A1", Math["tanh"](2))
      .run();
  });

  it("should support UMINUS", function () {
    runner().addCell("Alpha", "A1", "=UMINUS(2)").addExpectedValue("Alpha", "A1", -2).run();
  });

  it("should support UNARY_PERCENT", function () {
    runner()
      .addCell("Alpha", "A1", "=UNARY_PERCENT(2)")
      .addExpectedValue("Alpha", "A1", 0.02)
      .run();
  });

  it("should support UPLUS", function () {
    runner().addCell("Alpha", "A1", "=UPLUS(2)").addExpectedValue("Alpha", "A1", 2).run();
  });
});
