import { runner, it, describe } from "../testutils/TestUtils";

describe("Executor.execute - String", function () {
  it("should execute empty string", function () {
    runner().addCell("Alpha", "A1", '= ""').addExpectedValue("Alpha", "A1", "").run();
  });

  it("should execute non-empty string", function () {
    runner()
      .addCell("Alpha", "A1", '= "I am the very model of a modern major general."')
      .addExpectedValue("Alpha", "A1", "I am the very model of a modern major general.")
      .run();
    runner()
      .addCell("Alpha", "A1", '= "32879834"')
      .addExpectedValue("Alpha", "A1", "32879834")
      .run();
    runner()
      .addCell("Alpha", "A1", "= \"'Weird.'\"")
      .addExpectedValue("Alpha", "A1", "'Weird.'")
      .run();
  });
});
