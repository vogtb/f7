import { ParseException } from "../../../main/js/errors/ParseException";
import { runner, describe, it } from "../testutils/TestUtils";

describe("Executor.execute - Unary Percent", function () {
  it("should work with number", function () {
    runner().addCell("Alpha", "A1", "= 0%").addExpectedValue("Alpha", "A1", 0.0).run();
    runner().addCell("Alpha", "A1", "= 1%").addExpectedValue("Alpha", "A1", 0.01).run();
    runner().addCell("Alpha", "A1", "= 10%").addExpectedValue("Alpha", "A1", 0.1).run();
    runner().addCell("Alpha", "A1", "= 100%").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= 1000%").addExpectedValue("Alpha", "A1", 10.0).run();
    runner()
      .addCell("Alpha", "A1", "= 0.2318937%")
      .addExpectedValue("Alpha", "A1", 0.002318937)
      .run();
    runner()
      .addCell("Alpha", "A1", "= 10.167531%")
      .addExpectedValue("Alpha", "A1", 0.10167531)
      .run();
  });

  it("should work with string", function () {
    runner().addCell("Alpha", "A1", '= "1"%').addExpectedValue("Alpha", "A1", 0.01).run();
    runner().addCell("Alpha", "A1", '= "10"%').addExpectedValue("Alpha", "A1", 0.1).run();
    runner().addCell("Alpha", "A1", '= "100"%').addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", '= "1000"%').addExpectedValue("Alpha", "A1", 10.0).run();
    runner()
      .addCell("Alpha", "A1", '= "0.2318937"%')
      .addExpectedValue("Alpha", "A1", 0.002318937)
      .run();
    runner()
      .addCell("Alpha", "A1", '= "10.167531"%')
      .addExpectedValue("Alpha", "A1", 0.10167531)
      .run();
  });

  it("should work with boolean", function () {
    runner().addCell("Alpha", "A1", "= TRUE%").addExpectedValue("Alpha", "A1", 0.01).run();
    runner().addCell("Alpha", "A1", "= FALSE%").addExpectedValue("Alpha", "A1", 0.0).run();
  });

  it("should work with array literal", function () {
    runner().addCell("Alpha", "A1", "= {1, 2, 3}%").addExpectedValue("Alpha", "A1", 0.01).run();
  });

  it("should work with multiple minus symbols", function () {
    // TODO/HACK: Multiple percent postfixes are allowed in Excel, but not in Google Sheets. Another one for the books.
    runner()
      .addCell("Alpha", "A1", "= 1%%")
      .addExpectedValue("Alpha", "A1", new ParseException())
      .run();
  });
});
