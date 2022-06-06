import { RefException } from "../../../main/js/errors/RefException";
import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Type Casting", function () {
  it("should cast string to int number", function () {
    runner().addCell("Alpha", "A1", '= "0" * 1').addExpectedValue("Alpha", "A1", 0.0).run();
    runner().addCell("Alpha", "A1", '= "1" * 1').addExpectedValue("Alpha", "A1", 1.0).run();
    runner()
      .addCell("Alpha", "A1", '= "2738281" * 1')
      .addExpectedValue("Alpha", "A1", 2738281.0)
      .run();
    runner()
      .addCell("Alpha", "A1", '= "0001776" * 1')
      .addExpectedValue("Alpha", "A1", 1776.0)
      .run();
  });

  it("should cast string to decimal number", function () {
    runner().addCell("Alpha", "A1", '= "0.0" * 1').addExpectedValue("Alpha", "A1", 0.0).run();
    runner()
      .addCell("Alpha", "A1", '= "0.187328" * 1')
      .addExpectedValue("Alpha", "A1", 0.187328)
      .run();
    runner()
      .addCell("Alpha", "A1", '= "38133.09128901" * 1')
      .addExpectedValue("Alpha", "A1", 38133.09128901)
      .run();
    runner()
      .addCell("Alpha", "A1", '= "4.00000000000001" * 1')
      .addExpectedValue("Alpha", "A1", 4.00000000000001)
      .run();
    runner()
      .addCell("Alpha", "A1", '= "33.0000000000000" * 1')
      .addExpectedValue("Alpha", "A1", 33.0)
      .run();
  });

  it("should cast string to number (inferred positive sci-notation)", function () {
    runner()
      .addCell("Alpha", "A1", '= "12387e0" * 1')
      .addExpectedValue("Alpha", "A1", 12387.0)
      .run();
    runner()
      .addCell("Alpha", "A1", '= "12387e1" * 1')
      .addExpectedValue("Alpha", "A1", 123870.0)
      .run();
    runner().addCell("Alpha", "A1", '= "1.0e4" * 1').addExpectedValue("Alpha", "A1", 10000.0).run();
    runner().addCell("Alpha", "A1", '= "0.0009e4" * 1').addExpectedValue("Alpha", "A1", 9.0).run();
  });

  it("should cast string to number (positive sci-notation)", function () {
    runner()
      .addCell("Alpha", "A1", '= "12387e+0" * 1')
      .addExpectedValue("Alpha", "A1", 12387.0)
      .run();
    runner()
      .addCell("Alpha", "A1", '= "12387e+1" * 1')
      .addExpectedValue("Alpha", "A1", 123870.0)
      .run();
    runner()
      .addCell("Alpha", "A1", '= "1.0e+4" * 1')
      .addExpectedValue("Alpha", "A1", 10000.0)
      .run();
    runner().addCell("Alpha", "A1", '= "0.0009e+4" * 1').addExpectedValue("Alpha", "A1", 9.0).run();
  });

  it("should cast string to number (negative sci-notation)", function () {
    runner()
      .addCell("Alpha", "A1", '= "12387e-0" * 1')
      .addExpectedValue("Alpha", "A1", 12387)
      .run();
    runner()
      .addCell("Alpha", "A1", '= "12387e-1" * 1')
      .addExpectedValue("Alpha", "A1", 12387e-1)
      .run();
    runner().addCell("Alpha", "A1", '= "1.0e-4" * 1').addExpectedValue("Alpha", "A1", 1.0e-4).run();
    runner()
      .addCell("Alpha", "A1", '= "0.0009e-4" * 1')
      .addExpectedValue("Alpha", "A1", 0.0009e-4)
      .run();
  });

  it("should cast boolean to number", function () {
    runner().addCell("Alpha", "A1", "= FALSE * 1").addExpectedValue("Alpha", "A1", 0.0).run();
    runner().addCell("Alpha", "A1", "= TRUE * 1").addExpectedValue("Alpha", "A1", 1.0).run();
  });

  it("should cast number to int string", function () {
    runner().addCell("Alpha", "A1", '= 0 & ""').addExpectedValue("Alpha", "A1", "0").run();
    runner().addCell("Alpha", "A1", '= 1 & ""').addExpectedValue("Alpha", "A1", "1").run();
    runner()
      .addCell("Alpha", "A1", '= 2738281 & ""')
      .addExpectedValue("Alpha", "A1", "2738281")
      .run();
    runner().addCell("Alpha", "A1", '= 0001776 & ""').addExpectedValue("Alpha", "A1", "1776").run();
  });

  it("should cast number to decimal string", function () {
    runner().addCell("Alpha", "A1", '= 0.0 & ""').addExpectedValue("Alpha", "A1", "0").run();
    runner()
      .addCell("Alpha", "A1", '= 0.187328 & ""')
      .addExpectedValue("Alpha", "A1", "0.187328")
      .run();
    runner()
      .addCell("Alpha", "A1", '= 38133.09128901 & ""')
      .addExpectedValue("Alpha", "A1", "38133.09128901")
      .run();
    runner()
      .addCell("Alpha", "A1", '= 4.00000000000001 & ""')
      .addExpectedValue("Alpha", "A1", "4.00000000000001")
      .run();
    runner()
      .addCell("Alpha", "A1", '= 33.0000000000000 & ""')
      .addExpectedValue("Alpha", "A1", "33")
      .run();
  });

  it("should cast number to string with positive scientific notation", function () {
    runner()
      .addCell("Alpha", "A1", '= 12387183718789125223e0 & ""')
      .addExpectedValue("Alpha", "A1", "12387183718789126000")
      .run();
    runner()
      .addCell("Alpha", "A1", '= 31287e42 & ""')
      .addExpectedValue("Alpha", "A1", "3.1287e+46")
      .run();
    runner().addCell("Alpha", "A1", '= 1.0e4 & ""').addExpectedValue("Alpha", "A1", "10000").run();
    runner().addCell("Alpha", "A1", '= 0.0009e4 & ""').addExpectedValue("Alpha", "A1", "9").run();
  });

  it("should cast number to boolean false", function () {
    runner()
      .addCell("Alpha", "A1", "= IF(0, TRUE, FALSE)")
      .addExpectedValue("Alpha", "A1", false)
      .run();
  });

  it("should cast number to boolean true", function () {
    runner()
      .addCell("Alpha", "A1", "= IF(0.00001, TRUE, FALSE)")
      .addExpectedValue("Alpha", "A1", true)
      .run();
    runner()
      .addCell("Alpha", "A1", "= IF(1, TRUE, FALSE)")
      .addExpectedValue("Alpha", "A1", true)
      .run();
    runner()
      .addCell("Alpha", "A1", "= IF(2, TRUE, FALSE)")
      .addExpectedValue("Alpha", "A1", true)
      .run();
    runner()
      .addCell("Alpha", "A1", "= IF(-823178, TRUE, FALSE)")
      .addExpectedValue("Alpha", "A1", true)
      .run();
    runner()
      .addCell("Alpha", "A1", "= IF(31287e42, TRUE, FALSE)")
      .addExpectedValue("Alpha", "A1", true)
      .run();
  });

  it("should cast array literal to number", function () {
    runner().addCell("Alpha", "A1", "= {2, 4, 8} * 1").addExpectedValue("Alpha", "A1", 2.0).run();
    runner()
      .addCell("Alpha", "A1", '= {2, "Nope."} * 1')
      .addExpectedValue("Alpha", "A1", 2.0)
      .run();
    runner()
      .addCell("Alpha", "A1", "= {} * 1")
      .addExpectedValue("Alpha", "A1", new RefException())
      .run();
  });

  it("should cast array literal to boolean", function () {
    runner()
      .addCell("Alpha", "A1", "= IF({1000, TRUE}, TRUE, FALSE)")
      .addExpectedValue("Alpha", "A1", true)
      .run();
  });
});
