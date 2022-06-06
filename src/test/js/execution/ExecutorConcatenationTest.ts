import { DivException } from "../../../main/js/errors/DivException";
import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Concatenation", function () {
  it("should work with strings", function () {
    runner()
      .addCell("Alpha", "A1", '= "Hello" & "There"')
      .addExpectedValue("Alpha", "A1", "HelloThere")
      .run();
    runner()
      .addCell("Alpha", "A1", '= "Hello" & ""')
      .addExpectedValue("Alpha", "A1", "Hello")
      .run();
    runner().addCell("Alpha", "A1", '= "" & ""').addExpectedValue("Alpha", "A1", "").run();
    runner()
      .addCell("Alpha", "A1", '= "   " & "   "')
      .addExpectedValue("Alpha", "A1", "      ")
      .run();
  });

  it("should work with numbers", function () {
    runner().addCell("Alpha", "A1", "= 0 & 1").addExpectedValue("Alpha", "A1", "01").run();
    runner().addCell("Alpha", "A1", "= 0 & 0").addExpectedValue("Alpha", "A1", "00").run();
    runner()
      .addCell("Alpha", "A1", "= 131238 & 99281")
      .addExpectedValue("Alpha", "A1", "13123899281")
      .run();
    runner()
      .addCell("Alpha", "A1", "= 13.1238 & 99281")
      .addExpectedValue("Alpha", "A1", "13.123899281")
      .run();
    runner()
      .addCell("Alpha", "A1", "= 0.001 & 1.0")
      .addExpectedValue("Alpha", "A1", "0.0011")
      .run();
  });

  it("should work with booleans", function () {
    runner()
      .addCell("Alpha", "A1", "= TRUE & TRUE")
      .addExpectedValue("Alpha", "A1", "TRUETRUE")
      .run();
    runner()
      .addCell("Alpha", "A1", "= TRUE & FALSE")
      .addExpectedValue("Alpha", "A1", "TRUEFALSE")
      .run();
    runner()
      .addCell("Alpha", "A1", "= FALSE & FALSE")
      .addExpectedValue("Alpha", "A1", "FALSEFALSE")
      .run();
  });

  it("should return errors when encountered", function () {
    runner()
      .addCell("Alpha", "A1", "= 1 & #DIV/0!")
      .addExpectedValue("Alpha", "A1", new DivException())
      .run();
  });

  it("should work with array literals", function () {
    runner().addCell("Alpha", "A1", "= {1} & {2}").addExpectedValue("Alpha", "A1", "12").run();
    runner()
      .addCell("Alpha", "A1", "= {1, 2, 3} & {4, 5, 6}")
      .addExpectedValue("Alpha", "A1", "14")
      .run();
    runner()
      .addCell("Alpha", "A1", "= {1, #NUM!} & {4, #REF!}")
      .addExpectedValue("Alpha", "A1", "14")
      .run();
  });
});
