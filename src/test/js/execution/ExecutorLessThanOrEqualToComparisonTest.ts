import { RefException } from "../../../main/js/errors/RefException";
import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - Less Than Or Equal To Comparision", function () {
  it("should work with numbers", function () {
    runner().addCell("Alpha", "A1", "= 1 <= 1").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= 1 <= 2").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= -10 <= 0").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= 2 <= 1").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= 0 <= -10").addExpectedValue("Alpha", "A1", false).run();
    runner()
      .addCell("Alpha", "A1", "= 1.1928731 <= 1.1928731")
      .addExpectedValue("Alpha", "A1", true)
      .run();
  });

  it("should work with strings", function () {
    runner().addCell("Alpha", "A1", '= "Yes" <= "Yes"').addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", '= "Yes" <= "No"').addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", '= "No"<= "Yes"').addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", '= "" <= ""').addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", '= " " <= ""').addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", '= "" <= " "').addExpectedValue("Alpha", "A1", true).run();
  });

  it("should work with booleans", function () {
    runner().addCell("Alpha", "A1", "= TRUE <= TRUE").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= FALSE <= FALSE").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= TRUE <= FALSE").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= FALSE <= TRUE").addExpectedValue("Alpha", "A1", true).run();
  });

  it("should work with array literals", function () {
    runner()
      .addCell("Alpha", "A1", "= {1, 2, 3} <= {1, 2, 3}")
      .addExpectedValue("Alpha", "A1", true)
      .run();
    runner()
      .addCell("Alpha", "A1", "= {1, 2, 3} <= {44}")
      .addExpectedValue("Alpha", "A1", true)
      .run();
    runner()
      .addCell("Alpha", "A1", "= {44, 2, 3} <= {1}")
      .addExpectedValue("Alpha", "A1", false)
      .run();
    runner()
      .addCell("Alpha", "A1", "= {44, #REF!} <= {44, #REF!}")
      .addExpectedValue("Alpha", "A1", true)
      .run();
  });

  it("should work with number to boolean comparison", function () {
    runner().addCell("Alpha", "A1", "= 0 <= TRUE").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= 1 <= TRUE").addExpectedValue("Alpha", "A1", true).run(); // Important.
    runner().addCell("Alpha", "A1", "= 1 <= FALSE").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= 0 <= FALSE").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= -1 <= TRUE").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= -1 <= FALSE").addExpectedValue("Alpha", "A1", true).run();
  });

  it("should work with number to string comparison", function () {
    runner().addCell("Alpha", "A1", '= 0 <= ""').addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", '= 0 <= "0"').addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", '= 1 <= "0"').addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", '= 1 <= "1"').addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", '= -1 <= "1"').addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", '= 1000 <= "-100"').addExpectedValue("Alpha", "A1", true).run();
    runner()
      .addCell("Alpha", "A1", '= 1000 <= "Anything in the world."')
      .addExpectedValue("Alpha", "A1", true)
      .run();
  });

  it("should with number to array literal comparison", function () {
    runner()
      .addCell("Alpha", "A1", "= -1 <= {0, 1, 2}")
      .addExpectedValue("Alpha", "A1", true)
      .run();
    runner().addCell("Alpha", "A1", "= 0 <= {0, 1, 2}").addExpectedValue("Alpha", "A1", true).run();
    runner()
      .addCell("Alpha", "A1", "= 1 <= {0, 1, 2}")
      .addExpectedValue("Alpha", "A1", false)
      .run();
    runner().addCell("Alpha", "A1", "= 0 <= {1, 1, 2}").addExpectedValue("Alpha", "A1", true).run();
    runner()
      .addCell("Alpha", "A1", '= 0 <= {1, "Ignore me."}')
      .addExpectedValue("Alpha", "A1", true)
      .run();
  });

  it("should work with number to blank comparison", function () {
    runner().addCell("Alpha", "A1", "= 0 <= M9").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= 1 <= M9").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= M9 <= 0").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= M9 <= 1").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= -1 <= M9").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= M9 <= -1").addExpectedValue("Alpha", "A1", false).run();
  });

  it("should work with string to boolean comparision", function () {
    runner().addCell("Alpha", "A1", '= "TRUE" <= TRUE').addExpectedValue("Alpha", "A1", true).run();
    runner()
      .addCell("Alpha", "A1", '= "FALSE" <= FALSE')
      .addExpectedValue("Alpha", "A1", true)
      .run();
    runner().addCell("Alpha", "A1", '= "TRUE" <= TRUE').addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", '= "" <= TRUE').addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", '= "" <= FALSE').addExpectedValue("Alpha", "A1", true).run();
    runner()
      .addCell("Alpha", "A1", '= "Anything" <= FALSE')
      .addExpectedValue("Alpha", "A1", true)
      .run();
  });

  it("should work with string to blank comparison", function () {
    runner().addCell("Alpha", "A1", '= "" <= M10').addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", '= M10 <= ""').addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", '= " " <= M10').addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", '= M10 <= " "').addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", '= "One" <= M10').addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", '= M10 <= "One"').addExpectedValue("Alpha", "A1", true).run();
  });

  it("should work with boolean to blank comparison", function () {
    runner().addCell("Alpha", "A1", "= TRUE <= M10").addExpectedValue("Alpha", "A1", false).run();
    runner().addCell("Alpha", "A1", "= FALSE <= M10").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= M10 <= TRUE").addExpectedValue("Alpha", "A1", true).run();
    runner().addCell("Alpha", "A1", "= M10 <= FALSE").addExpectedValue("Alpha", "A1", true).run();
  });

  it("should work with array literal to string comparison", function () {
    runner()
      .addCell("Alpha", "A1", '= {"A", "B"} <= "A"')
      .addExpectedValue("Alpha", "A1", true)
      .run();
    runner()
      .addCell("Alpha", "A1", '= {"A", "B"} <= "B"')
      .addExpectedValue("Alpha", "A1", true)
      .run();
    runner()
      .addCell("Alpha", "A1", '= "A" <= {"A", "B"}')
      .addExpectedValue("Alpha", "A1", true)
      .run();
    runner()
      .addCell("Alpha", "A1", '= "B" <= {"A", "B"}')
      .addExpectedValue("Alpha", "A1", false)
      .run();
  });

  it("should work with array literal to boolean comparison", function () {
    runner()
      .addCell("Alpha", "A1", "= {TRUE, FALSE} <= TRUE")
      .addExpectedValue("Alpha", "A1", true)
      .run();
    runner()
      .addCell("Alpha", "A1", "= TRUE <= {TRUE, FALSE}")
      .addExpectedValue("Alpha", "A1", true)
      .run();
    runner()
      .addCell("Alpha", "A1", "= {FALSE, FALSE} <= TRUE")
      .addExpectedValue("Alpha", "A1", true)
      .run();
    runner()
      .addCell("Alpha", "A1", "= TRUE <= {FALSE, FALSE}")
      .addExpectedValue("Alpha", "A1", false)
      .run();
  });

  it("should return errors when encountering them", function () {
    runner()
      .addCell("Alpha", "A1", "= 10 <= #REF!")
      .addExpectedValue("Alpha", "A1", new RefException())
      .run();
    runner()
      .addCell("Alpha", "A1", "= #REF! <= TRUE")
      .addExpectedValue("Alpha", "A1", new RefException())
      .run();
  });
});
