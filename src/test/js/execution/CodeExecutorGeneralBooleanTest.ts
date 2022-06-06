import { assert } from "chai";
import { it, describe } from "../testutils/TestUtils";
import { run } from "../testutils/TestUtils";

describe("CodeExecutor - General Boolean Test", function () {
  it("should work with true", function () {
    assert.equal(run("TRUE"), true);
    assert.equal(run("true"), true);
    assert.equal(run("True"), true);
    assert.equal(run("TrUe"), true);
    assert.equal(run("trUE"), true);
  });

  it("should work with false", function () {
    assert.equal(run("FALSE"), false);
    assert.equal(run("false"), false);
    assert.equal(run("False"), false);
    assert.equal(run("FaLsE"), false);
    assert.equal(run("fAlSE"), false);
  });
});
