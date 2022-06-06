import { assert } from "chai";
import { it, describe } from "../testutils/TestUtils";
import { run } from "../testutils/TestUtils";

describe("CodeExecutor - General Expression Test", function () {
  it("should handle parenthesis appropriately", function () {
    assert.equal(run("(TRUE)"), true);
    assert.equal(run("(10)"), 10);
    assert.equal(run("(2 + 1) * 4"), 12);
  });
});
