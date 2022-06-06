import { DivException } from "../../../main/js/errors/DivException";
import { NAException } from "../../../main/js/errors/NAException";
import { NameException } from "../../../main/js/errors/NameException";
import { NullException } from "../../../main/js/errors/NullException";
import { NumException } from "../../../main/js/errors/NumException";
import { ParseException } from "../../../main/js/errors/ParseException";
import { RefException } from "../../../main/js/errors/RefException";
import { ValueException } from "../../../main/js/errors/ValueException";
import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Subtraction", function () {
  it("should work with numbers", function () {
    runner().addCell("Alpha", "A1", "= 10 - 4").addExpectedValue("Alpha", "A1", 6.0).run();
    runner().addCell("Alpha", "A1", "= 0 - 0").addExpectedValue("Alpha", "A1", 0.0).run();
    runner().addCell("Alpha", "A1", "= 10 - -10").addExpectedValue("Alpha", "A1", 20.0).run();
    runner().addCell("Alpha", "A1", "= -10 - -4").addExpectedValue("Alpha", "A1", -6.0).run();
    runner()
      .addCell("Alpha", "A1", "= 1e10 - 1.1")
      .addExpectedValue("Alpha", "A1", 9.9999999989e9)
      .run();
  });

  it("should work with booleans", function () {
    runner().addCell("Alpha", "A1", "= 9 - TRUE").addExpectedValue("Alpha", "A1", 8.0).run();
    runner().addCell("Alpha", "A1", "= 9 - FALSE").addExpectedValue("Alpha", "A1", 9.0).run();
    runner().addCell("Alpha", "A1", "= 9 - -TRUE").addExpectedValue("Alpha", "A1", 10.0).run();
    runner().addCell("Alpha", "A1", "= 9 - -FALSE").addExpectedValue("Alpha", "A1", 9.0).run();
  });

  it("should work with strings", function () {
    runner().addCell("Alpha", "A1", '= 9 - "10"').addExpectedValue("Alpha", "A1", -1.0).run();
    runner()
      .addCell("Alpha", "A1", '= 9 - "1e10"')
      .addExpectedValue("Alpha", "A1", -9.999999991e9)
      .run();
    runner().addCell("Alpha", "A1", '= 9 - "0"').addExpectedValue("Alpha", "A1", 9.0).run();
  });

  it("should not work with strings that are not numbers", function () {
    runner()
      .addCell("Alpha", "A1", '= 9 - "No good."')
      .addExpectedValue("Alpha", "A1", new ValueException())
      .run();
  });

  it("should work with array literals", function () {
    runner().addCell("Alpha", "A1", "= 3 - {1}").addExpectedValue("Alpha", "A1", 2.0).run();
    runner().addCell("Alpha", "A1", "= 3 - {1, 44}").addExpectedValue("Alpha", "A1", 2.0).run();
    runner()
      .addCell("Alpha", "A1", '= 3 - -{1, "Ignore me."}')
      .addExpectedValue("Alpha", "A1", 4.0)
      .run();
  });

  it("should work with blank values", function () {
    runner().addCell("Alpha", "A1", "= 2 - M44").addExpectedValue("Alpha", "A1", 2.0).run();
  });

  it("should return errors as they are encountered", function () {
    runner()
      .addCell("Alpha", "A1", "= 3 - #NULL!")
      .addCell("Alpha", "A2", "= 3 - #DIV/0!")
      .addCell("Alpha", "A3", "= 3 - #VALUE!")
      .addCell("Alpha", "A4", "= 3 - #REF!")
      .addCell("Alpha", "A5", "= 3 - #NAME?")
      .addCell("Alpha", "A6", "= 3 - #NUM!")
      .addCell("Alpha", "A7", "= 3 - #N/A")
      .addCell("Alpha", "A8", "= 3 - #ERROR!")
      .addExpectedValue("Alpha", "A1", new NullException())
      .addExpectedValue("Alpha", "A2", new DivException())
      .addExpectedValue("Alpha", "A3", new ValueException())
      .addExpectedValue("Alpha", "A4", new RefException())
      .addExpectedValue("Alpha", "A5", new NameException())
      .addExpectedValue("Alpha", "A6", new NumException())
      .addExpectedValue("Alpha", "A7", new NAException())
      .addExpectedValue("Alpha", "A8", new ParseException())
      .run();
  });
});
