import { assert } from "chai";
import { it, describe } from "../testutils/TestUtils";
import { runWithCustomFormulas } from "../testutils/TestUtils";

describe("CodeExecutor - Custom Formula Test", function () {
  it("should work without arguments", function () {
    assert.equal(runWithCustomFormulas("CUSTOM()", { CUSTOM: () => 22 }), 22);
    assert.equal(runWithCustomFormulas("ANOTHER()", { ANOTHER: () => true }), true);
  });

  it("should work with number arguments", function () {
    assert.equal(
      runWithCustomFormulas("MULTIPLY_AND_ADD_ONE(2, 4)", {
        MULTIPLY_AND_ADD_ONE: (x: any, y: any) => x * y + 1,
      }),
      9
    );
  });

  it("should work with string arguments", function () {
    assert.equal(
      runWithCustomFormulas("CUSTOM_CONCAT(2, 4)", {
        CUSTOM_CONCAT: (x: any, y: any) => x.toString() + y.toString(),
      }),
      "24"
    );
  });

  it("should work with boolean arguments", function () {
    assert.equal(
      runWithCustomFormulas("BOTH_YES(TRUE, TRUE)", { BOTH_YES: (x: any, y: any) => x && y }),
      true
    );
  });
});
