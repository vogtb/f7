import { NAException } from "../../../main/js/errors/NAException";
import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Info Formulas", function () {
  it("should support ERRORTYPE", function () {
    runner().addCell("Alpha", "A1", "=ERRORTYPE(#DIV/0!)").addExpectedValue("Alpha", "A1", 2).run();
  });

  it("should support ISBLANK", function () {
    runner().addCell("Alpha", "A1", "=ISBLANK(G20)").addExpectedValue("Alpha", "A1", true).run();
  });

  it("should support ISERR", function () {
    runner().addCell("Alpha", "A1", "=ISERR(1/0)").addExpectedValue("Alpha", "A1", true).run();
  });

  it("should support ISERROR", function () {
    runner().addCell("Alpha", "A1", "=ISERROR(1/0)").addExpectedValue("Alpha", "A1", true).run();
  });

  it("should support ISLOGICAL", function () {
    runner().addCell("Alpha", "A1", "=ISLOGICAL(1)").addExpectedValue("Alpha", "A1", false).run();
  });

  it("should support ISNA", function () {
    runner().addCell("Alpha", "A1", "=ISNA(#N/A)").addExpectedValue("Alpha", "A1", true).run();
  });

  it("should support ISNONTEXT", function () {
    runner()
      .addCell("Alpha", "A1", "=ISNONTEXT(TRUE())")
      .addExpectedValue("Alpha", "A1", true)
      .run();
  });

  it("should support ISNUMBER", function () {
    runner().addCell("Alpha", "A1", "=ISNUMBER(1)").addExpectedValue("Alpha", "A1", true).run();
  });

  it("should support ISTEXT", function () {
    runner()
      .addCell("Alpha", "A1", '=ISTEXT("Text Here.")')
      .addExpectedValue("Alpha", "A1", true)
      .run();
  });

  it("should support N", function () {
    runner().addCell("Alpha", "A1", "=N(1)").addExpectedValue("Alpha", "A1", 1).run();
  });

  it("should support NA", function () {
    runner()
      .addCell("Alpha", "A1", "=NA()")
      .addExpectedValue("Alpha", "A1", new NAException())
      .run();
  });

  it("should support TYPE", function () {
    runner().addCell("Alpha", "A1", "=TYPE(99)").addExpectedValue("Alpha", "A1", 1).run();
  });
});
