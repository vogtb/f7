import { assert } from "chai";
import { it, describe } from "../testutils/TestUtils";
import { run } from "../testutils/TestUtils";

describe("CodeExecutor - General Formula Test", function () {
  it("should work without arguments", function () {
    assert.equal(run("TRUE()"), true);
    assert.equal(run("FALSE()"), false);
  });

  it("should work with number arguments", function () {
    assert.equal(run("ADD(3, 4)"), 7);
  });

  it("should work with string arguments", function () {
    assert.equal(run('ADD("3", "4")'), 7);
  });

  it("should work with boolean arguments", function () {
    assert.equal(run("ADD(TRUE, TRUE)"), 2);
  });

  it("should work with array literal arguments", function () {
    assert.equal(run("ADD({44}, {1})"), 45);
  });
});
