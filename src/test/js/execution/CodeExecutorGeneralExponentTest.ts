import { assert } from "chai";
import { it, describe } from "../testutils/TestUtils";
import { F7Exception } from "../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../main/js/errors/F7ExceptionName";
import { run } from "../testutils/TestUtils";

describe("CodeExecutor - General Exponent Test", function () {
  it("should work with positive number and positive exponent", function () {
    assert.deepEqual(run("3^5"), 243);
    assert.deepEqual(run("4^5"), 1024);
    assert.deepEqual(run("1^1"), 1);
    assert.deepEqual(run("1^5"), 1);
    assert.deepEqual(run("2^0"), 1);
    assert.deepEqual(run("0^10"), 0);
  });

  it("should with positive number and negative exponent", function () {
    assert.deepEqual(run("3^-5"), 0.00411522633744856);
    assert.deepEqual(run("4^-5"), 9.765625e-4);
    assert.deepEqual(run("1^-1"), 1);
    assert.deepEqual(run("1^-5"), 1);
    assert.deepEqual(run("2^-0"), 1);
    // TODO/HACK: This is different in Excel and Google Sheets.
    assert.deepEqual((run("0^-10") as F7Exception).name, F7ExceptionName.NUM);
  });

  it("should work with negative number and negative exponent", function () {
    assert.deepEqual(run("-3^-1"), -0.3333333333333333);
    assert.deepEqual(run("-3^-2"), 0.1111111111111111);
    assert.deepEqual(run("-3^-3"), -0.037037037037037035);
    assert.deepEqual(run("-2^-3"), -0.125);
    assert.deepEqual(run("-1^-3"), -1);
    assert.deepEqual(run("-1^-2"), 1);
  });

  it("should work with negative number and positive exponent", function () {
    assert.deepEqual(run("-3^1"), -3);
    assert.deepEqual(run("-3^2"), 9);
    assert.deepEqual(run("-3^3"), -27);
    assert.deepEqual(run("-2^3"), -8);
    assert.deepEqual(run("-1^3"), -1);
    assert.deepEqual(run("-1^2"), 1);
  });

  it("should perform with proper associative property", function () {
    assert.deepEqual(run("2 ^ 3 ^ 4"), 4096);
    assert.deepEqual(run("3 ^ 2 ^ 4"), 6561);
    assert.deepEqual(run("4 ^ 2 ^ 3"), 4096);
  });

  it("should work with booleans", function () {
    assert.deepEqual(run("9 ^ TRUE"), 9);
    assert.deepEqual(run("9 ^ FALSE"), 1);
  });

  it("should work with strings", function () {
    assert.deepEqual(run('2 ^ "3"'), 8);
    assert.deepEqual(run('2 ^ "3e1"'), 1.073741824e9);
    assert.deepEqual(run('2 ^ "2"'), 4);
  });
});
