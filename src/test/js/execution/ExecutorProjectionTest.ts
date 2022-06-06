import { RefException } from "../../../main/js/errors/RefException";
import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Projection", function () {
  it("should project row of size 1", function () {
    runner().addCell("Alpha", "A1", "= {1}").addExpectedValue("Alpha", "A1", 1.0).run();
  });

  it("should project row of size 2", function () {
    runner()
      .addCell("Alpha", "A1", "= {1, 2}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 1.0)
      .addExpectedValue("Alpha", "B1", 2.0)
      .run();
  });

  it("should project row of size N", function () {
    runner()
      .addCell("Alpha", "A1", "= {1, 2, 3, 4, 5, 6}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 1.0)
      .addExpectedValue("Alpha", "B1", 2.0)
      .addExpectedValue("Alpha", "C1", 3.0)
      .addExpectedValue("Alpha", "D1", 4.0)
      .addExpectedValue("Alpha", "E1", 5.0)
      .run();
  });

  it("should project column of size 1", function () {
    runner().addCell("Alpha", "A1", "= {1}").addExpectedValue("Alpha", "A1", 1.0).run();
  });

  it("should project column of size 2", function () {
    runner()
      .addCell("Alpha", "A1", "= {1;2}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 1.0)
      .addExpectedValue("Alpha", "A2", 2.0)
      .run();
  });

  it("should project column of size N", function () {
    runner()
      .addCell("Alpha", "A1", "= {1;2;3;4;5;6}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 1.0)
      .addExpectedValue("Alpha", "A2", 2.0)
      .addExpectedValue("Alpha", "A3", 3.0)
      .addExpectedValue("Alpha", "A4", 4.0)
      .addExpectedValue("Alpha", "A5", 5.0)
      .addExpectedValue("Alpha", "A6", 6.0)
      .run();
  });

  it("should project column and row, full grid, column by row", function () {
    runner()
      .addCell("Alpha", "A1", "= {{1;2;3},{4;5;6},{7;8;9}}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 1.0)
      .addExpectedValue("Alpha", "A2", 2.0)
      .addExpectedValue("Alpha", "A3", 3.0)
      .addExpectedValue("Alpha", "B1", 4.0)
      .addExpectedValue("Alpha", "B2", 5.0)
      .addExpectedValue("Alpha", "B3", 6.0)
      .addExpectedValue("Alpha", "C1", 7.0)
      .addExpectedValue("Alpha", "C2", 8.0)
      .addExpectedValue("Alpha", "C3", 9.0)
      .run();
  });

  it("should project column and row, full grid, row by column", function () {
    runner()
      .addCell("Alpha", "A1", "= {{1,2,3};{4,5,6};{7,8,9}}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", 1.0)
      .addExpectedValue("Alpha", "A2", 4.0)
      .addExpectedValue("Alpha", "A3", 7.0)
      .addExpectedValue("Alpha", "B1", 2.0)
      .addExpectedValue("Alpha", "B2", 5.0)
      .addExpectedValue("Alpha", "B3", 8.0)
      .addExpectedValue("Alpha", "C1", 3.0)
      .addExpectedValue("Alpha", "C2", 6.0)
      .addExpectedValue("Alpha", "C3", 9.0)
      .run();
  });

  it("should do projection from a range", function () {
    runner()
      .addCell("Alpha", "A1", "= 11.1")
      .addCell("Alpha", "A2", "= 22.2")
      .addCell("Alpha", "A3", "= 33.3")
      .addCell("Alpha", "M1", "= {A1:A3}")
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "M1", 11.1)
      .addExpectedValue("Alpha", "M2", 22.2)
      .addExpectedValue("Alpha", "M3", 33.3)
      .run();
  });

  it("should return REF error if projection would collide with existing values", function () {
    runner()
      .addCell("Alpha", "A1", "= {1, 2}")
      .addCell("Alpha", "B1", '= "Don\'t tread on me."')
      .addExpectedValue("Alpha", "A1", new RefException())
      .addExpectedValue("Alpha", "B1", "Don't tread on me.")
      .run();
    runner()
      .addCell("Alpha", "A1", "= {{1,2,3};{4,5,6};{7,8,9}}")
      .addCell("Alpha", "B2", '= "Don\'t tread on me."')
      .addCell("Alpha", "Z99", "Setting grid size to be large enough.")
      .addExpectedValue("Alpha", "A1", new RefException())
      .addExpectedEmptyValue("Alpha", "A2")
      .addExpectedEmptyValue("Alpha", "A3")
      .addExpectedEmptyValue("Alpha", "B1")
      .addExpectedValue("Alpha", "B2", "Don't tread on me.")
      .addExpectedEmptyValue("Alpha", "B3")
      .addExpectedEmptyValue("Alpha", "C1")
      .addExpectedEmptyValue("Alpha", "C2")
      .addExpectedEmptyValue("Alpha", "c3")
      .run();
  });
});
