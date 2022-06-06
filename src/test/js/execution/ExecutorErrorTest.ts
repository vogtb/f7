import { DivException } from "../../../main/js/errors/DivException";
import { NAException } from "../../../main/js/errors/NAException";
import { NameException } from "../../../main/js/errors/NameException";
import { NullException } from "../../../main/js/errors/NullException";
import { NumException } from "../../../main/js/errors/NumException";
import { ParseException } from "../../../main/js/errors/ParseException";
import { RefException } from "../../../main/js/errors/RefException";
import { ValueException } from "../../../main/js/errors/ValueException";
import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Errors", function () {
  it("should return NULL error", function () {
    runner()
      .addCell("Alpha", "A1", "= #NULL!")
      .addExpectedValue("Alpha", "A1", new NullException())
      .run();
    runner()
      .addCell("Alpha", "A1", "= #null!")
      .addExpectedValue("Alpha", "A1", new NullException())
      .run();
  });

  it("should return DIV error", function () {
    runner()
      .addCell("Alpha", "A1", "= #DIV/0!")
      .addExpectedValue("Alpha", "A1", new DivException())
      .run();
    runner()
      .addCell("Alpha", "A1", "= #div/0!")
      .addExpectedValue("Alpha", "A1", new DivException())
      .run();
  });

  it("should return VALUE error", function () {
    runner()
      .addCell("Alpha", "A1", "= #VALUE!")
      .addExpectedValue("Alpha", "A1", new ValueException())
      .run();
    runner()
      .addCell("Alpha", "A1", "= #value!")
      .addExpectedValue("Alpha", "A1", new ValueException())
      .run();
  });

  it("should return REF error", function () {
    runner()
      .addCell("Alpha", "A1", "= #REF!")
      .addExpectedValue("Alpha", "A1", new RefException())
      .run();
    runner()
      .addCell("Alpha", "A1", "= #ref!")
      .addExpectedValue("Alpha", "A1", new RefException())
      .run();
  });

  it("should return NAME error", function () {
    runner()
      .addCell("Alpha", "A1", "= #NAME?")
      .addExpectedValue("Alpha", "A1", new NameException())
      .run();
    runner()
      .addCell("Alpha", "A1", "= #name?")
      .addExpectedValue("Alpha", "A1", new NameException())
      .run();
  });

  it("should return NUM error", function () {
    runner()
      .addCell("Alpha", "A1", "= #NUM!")
      .addExpectedValue("Alpha", "A1", new NumException())
      .run();
    runner()
      .addCell("Alpha", "A1", "= #num!")
      .addExpectedValue("Alpha", "A1", new NumException())
      .run();
  });

  it("should return NA error", function () {
    runner()
      .addCell("Alpha", "A1", "= #N/A")
      .addExpectedValue("Alpha", "A1", new NAException())
      .run();
    runner()
      .addCell("Alpha", "A1", "= #n/a")
      .addExpectedValue("Alpha", "A1", new NAException())
      .run();
  });

  it("should return ERROR error", function () {
    runner()
      .addCell("Alpha", "A1", "= #ERROR!")
      .addExpectedValue("Alpha", "A1", new ParseException())
      .run();
    runner()
      .addCell("Alpha", "A1", "= #error!")
      .addExpectedValue("Alpha", "A1", new ParseException())
      .run();
  });
});
