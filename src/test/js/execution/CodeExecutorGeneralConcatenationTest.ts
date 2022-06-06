import { assert } from "chai";
import { it, describe } from "../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../main/js/errors/F7ExceptionName";
import { CommonModels } from "../testutils/CommonModels";
import { run, runWithLookups } from "../testutils/TestUtils";

describe("CodeExecutor - General Concatenation Test", function () {
  it("should work with strings", function () {
    assert.deepEqual(run('"Hello" & "There"'), "HelloThere");
    assert.deepEqual(run('"" & ""'), "");
    assert.deepEqual(run('"   " & "    "'), "       ");
  });

  it("should work with numbers", function () {
    assert.deepEqual(run("0 & 1"), "01");
    assert.deepEqual(run("0 & 0"), "00");
    assert.deepEqual(run("131238 & 99281"), "13123899281");
    assert.deepEqual(run("13.1238 & 99281"), "13.123899281");
    assert.deepEqual(run("0.001 & 1.0"), "0.0011");
  });

  it("should work with booleans", function () {
    assert.deepEqual(run("TRUE & TRUE"), "TRUETRUE");
    assert.deepEqual(run("TRUE & FALSE"), "TRUEFALSE");
    assert.deepEqual(run("FALSE & FALSE"), "FALSEFALSE");
  });

  it("should not work with errors", function () {
    assert.deepEqual((run("1 & #DIV/0!") as F7Exception).name, F7ExceptionName.DIV);
  });

  it("should work with blanks", function () {
    const lookup = stub();
    const collateralLookup = stub();
    collateralLookup.withArgs(CommonModels.A1, 2).returns(2);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M44_RANGE).returns(null);
    assert.deepEqual(runWithLookups("2 & M44", lookup, collateralLookup), "2");
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, 2));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M44_RANGE));
    assert.equal(collateralLookup.callCount, 2);
    assert.isTrue(lookup.notCalled);
  });

  it("should work with array literals", function () {
    assert.deepEqual(run("{1} & {2}"), "12");
    assert.deepEqual(run("{1, 2, 3} & {4, 5, 6}"), "14");
    assert.deepEqual(run("{1, #NUM!} & {4, #REF!}"), "14");
  });
});
