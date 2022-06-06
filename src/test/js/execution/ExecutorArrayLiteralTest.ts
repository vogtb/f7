import { RefException } from "../../../main/js/errors/RefException";
import { ValueException } from "../../../main/js/errors/ValueException";
import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Array Literal", function () {
  it("should return REF error when given an empty array literal", function () {
    runner()
      .addCell("Alpha", "A1", "= {}")
      .addExpectedValue("Alpha", "A1", new RefException())
      .run();
  });

  it("should handle single value", function () {
    runner().addCell("Alpha", "A1", "= {1}").addExpectedValue("Alpha", "A1", 1).run();
  });

  it("should handle single nested value", function () {
    runner().addCell("Alpha", "A1", "= {{{{{{1}}}}}}").addExpectedValue("Alpha", "A1", 1).run();
  });

  it("should handle column-wise projection where it projects to next columns", function () {
    runner()
      .addCell("Alpha", "A1", "= {1, 2, 3, 4}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 1)
      .addExpectedValue("Alpha", "B1", 2)
      .addExpectedValue("Alpha", "C1", 3)
      .addExpectedValue("Alpha", "D1", 4)
      .run();
  });

  it("should handle column-wise projection with nested columns", function () {
    runner()
      .addCell("Alpha", "A1", "= {1, {2, {3, {4}}}}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 1)
      .addExpectedValue("Alpha", "B1", 2)
      .addExpectedValue("Alpha", "C1", 3)
      .addExpectedValue("Alpha", "D1", 4)
      .run();
  });

  it("should handle row-wise projection where it projects to next rows", function () {
    runner()
      .addCell("Alpha", "A1", "= {1; 2; 3; 4}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 1)
      .addExpectedValue("Alpha", "A2", 2)
      .addExpectedValue("Alpha", "A3", 3)
      .addExpectedValue("Alpha", "A4", 4)
      .run();
    runner()
      .addCell("Alpha", "A1", "= {11;9;5;3;1}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 11)
      .addExpectedValue("Alpha", "A2", 9)
      .addExpectedValue("Alpha", "A3", 5)
      .addExpectedValue("Alpha", "A4", 3)
      .addExpectedValue("Alpha", "A5", 1)
      .run();
  });

  it("should handle row-wise projection with nested rows", function () {
    runner()
      .addCell("Alpha", "A1", "= {1; {2; {3; {4}}}}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 1)
      .addExpectedValue("Alpha", "A2", 2)
      .addExpectedValue("Alpha", "A3", 3)
      .addExpectedValue("Alpha", "A4", 4)
      .run();
    runner()
      .addCell("Alpha", "A1", "= {1;{2;{3;4}};{5}}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 1)
      .addExpectedValue("Alpha", "A2", 2)
      .addExpectedValue("Alpha", "A3", 3)
      .addExpectedValue("Alpha", "A4", 4)
      .addExpectedValue("Alpha", "A5", 5)
      .run();
  });

  it("should handle multi-dimensional projection with row first", function () {
    runner()
      .addCell("Alpha", "A1", "= {1, 2;3, 4}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 1)
      .addExpectedValue("Alpha", "B1", 2)
      .addExpectedValue("Alpha", "A2", 3)
      .addExpectedValue("Alpha", "B2", 4)
      .run();
    runner()
      .addCell("Alpha", "A1", "= {1, 2, 3;4, 5, 6}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 1)
      .addExpectedValue("Alpha", "B1", 2)
      .addExpectedValue("Alpha", "C1", 3)
      .addExpectedValue("Alpha", "A2", 4)
      .addExpectedValue("Alpha", "B2", 5)
      .addExpectedValue("Alpha", "C2", 6)
      .run();
  });

  it("should handle multi-dimensional projection with column first", function () {
    runner()
      .addCell("Alpha", "A1", "= {{1; 2},{3; 4}}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 1)
      .addExpectedValue("Alpha", "B1", 3)
      .addExpectedValue("Alpha", "A2", 2)
      .addExpectedValue("Alpha", "B2", 4)
      .run();
    runner()
      .addCell("Alpha", "A1", "= {{{}; {}},{{}; {}}}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", new RefException())
      .addExpectedValue("Alpha", "B1", new RefException())
      .addExpectedValue("Alpha", "A2", new RefException())
      .addExpectedValue("Alpha", "B2", new RefException())
      .run();
  });

  it("should handle multi-dimensional projection", function () {
    runner()
      .addCell(
        "Alpha",
        "A1",
        "= {{{0.0, 1.0, 2.0};{0.1, 1.1, 2.1}};{{0.2, 1.2, 2.2};{0.3, 1.3, 2.3}};{{0.4, 1.4, 2.4};{0.5, 1.5, 2.5}}}"
      )
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 0)
      .addExpectedValue("Alpha", "A2", 0.1)
      .addExpectedValue("Alpha", "A3", 0.2)
      .addExpectedValue("Alpha", "A4", 0.3)
      .addExpectedValue("Alpha", "A5", 0.4)
      .addExpectedValue("Alpha", "A6", 0.5)
      .addExpectedValue("Alpha", "B1", 1)
      .addExpectedValue("Alpha", "B2", 1.1)
      .addExpectedValue("Alpha", "B3", 1.2)
      .addExpectedValue("Alpha", "B4", 1.3)
      .addExpectedValue("Alpha", "B5", 1.4)
      .addExpectedValue("Alpha", "B6", 1.5)
      .addExpectedValue("Alpha", "C1", 2)
      .addExpectedValue("Alpha", "C2", 2.1)
      .addExpectedValue("Alpha", "C3", 2.2)
      .addExpectedValue("Alpha", "C4", 2.3)
      .addExpectedValue("Alpha", "C5", 2.4)
      .addExpectedValue("Alpha", "C6", 2.5)
      .run();
  });

  it("should return VALUE exception when column/row count do not match", function () {
    runner()
      .addCell("Alpha", "A1", "= {1,2;3}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", new ValueException())
      .run();
    runner()
      .addCell("Alpha", "A1", "= {1,2;3,4,5}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", new ValueException())
      .run();
    runner()
      .addCell("Alpha", "A1", "= {11;9;5;3;1,2}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", new ValueException())
      .run();
    runner()
      .addCell("Alpha", "A1", "= {{};{};{},{}}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", new ValueException())
      .run();
  });
});
