import { describe, it, runner } from "../testutils/TestUtils";

describe("Executor.execute - Unary Plus", function () {
  it("should work with number", function () {
    runner().addCell("Alpha", "A1", "= +0").addExpectedValue("Alpha", "A1", 0.0).run();
    runner().addCell("Alpha", "A1", "= +1").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= +2792").addExpectedValue("Alpha", "A1", 2792.0).run();
    runner().addCell("Alpha", "A1", "= +1.32e10").addExpectedValue("Alpha", "A1", 1.32e10).run();
    runner()
      .addCell("Alpha", "A1", "= +0.2318937")
      .addExpectedValue("Alpha", "A1", 0.2318937)
      .run();
    runner()
      .addCell("Alpha", "A1", "= +10.167531")
      .addExpectedValue("Alpha", "A1", 10.167531)
      .run();
    runner()
      .addCell("Alpha", "A1", "= +-10.167531")
      .addExpectedValue("Alpha", "A1", -10.167531)
      .run();
  });

  it("should work with string", function () {
    runner().addCell("Alpha", "A1", '= +"0"').addExpectedValue("Alpha", "A1", 0.0).run();
    runner().addCell("Alpha", "A1", '= +"1"').addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", '= +"2792"').addExpectedValue("Alpha", "A1", 2792.0).run();
    runner().addCell("Alpha", "A1", '= +"1.32e10"').addExpectedValue("Alpha", "A1", 1.32e10).run();
    runner()
      .addCell("Alpha", "A1", '= +"0.2318937"')
      .addExpectedValue("Alpha", "A1", 0.2318937)
      .run();
    runner()
      .addCell("Alpha", "A1", '= +"10.167531"')
      .addExpectedValue("Alpha", "A1", 10.167531)
      .run();
  });

  it("should work with boolean", function () {
    runner().addCell("Alpha", "A1", "= +TRUE").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= +FALSE").addExpectedValue("Alpha", "A1", 0.0).run();
  });

  it("should work with array literal", function () {
    runner().addCell("Alpha", "A1", "= +{1, 2, 3}").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= +{1, #REF!}").addExpectedValue("Alpha", "A1", 1.0).run();
  });

  it("should work with multiple symbols", function () {
    runner().addCell("Alpha", "A1", "= +1").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= +++1").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= ++++1").addExpectedValue("Alpha", "A1", 1.0).run();
  });

  it("should work with unary minus", function () {
    runner().addCell("Alpha", "A1", "= +-1").addExpectedValue("Alpha", "A1", -1.0).run();
    runner().addCell("Alpha", "A1", "= +-+1").addExpectedValue("Alpha", "A1", -1.0).run();
    runner().addCell("Alpha", "A1", "= +---+-----+-+1").addExpectedValue("Alpha", "A1", -1.0).run();
    runner().addCell("Alpha", "A1", "= +---+----+-+1").addExpectedValue("Alpha", "A1", 1.0).run();
    runner().addCell("Alpha", "A1", "= +---+---+-+1").addExpectedValue("Alpha", "A1", -1.0).run();
    runner().addCell("Alpha", "A1", "= +---+--+-+1").addExpectedValue("Alpha", "A1", 1.0).run();
  });
});
