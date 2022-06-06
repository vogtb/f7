import { assert } from "chai";
import { it, describe } from "../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../main/js/errors/F7ExceptionName";
import { CommonModels } from "../testutils/CommonModels";
import { run, runWithLookups } from "../testutils/TestUtils";

describe("CodeExecutor - General Division Test", function () {
  it("should work with numbers", function () {
    assert.deepEqual(run("10 / 5"), 2.0);
    assert.deepEqual(run("1 / 1"), 1.0);
    assert.deepEqual(run("10 / -10"), -1.0);
    assert.deepEqual(run("-10 / -2"), 5.0);
    assert.deepEqual(run("1e10 / 1.1"), 9.09090909090909e9);
  });

  it("should work with booleans", function () {
    assert.deepEqual(run("9 / TRUE"), 9.0);
    assert.deepEqual((run("9 / FALSE") as F7Exception).name, F7ExceptionName.DIV);
  });

  it("should work with strings", function () {
    assert.deepEqual(run('12 / "3"'), 4.0);
    assert.deepEqual(run('12 / "3e1"'), 0.4);
    assert.deepEqual(run('12 / "2"'), 6.0);
  });

  it("should work with arrays", function () {
    assert.deepEqual(run("12 / {2}"), 6.0);
    assert.deepEqual(run("12 / {2, 44}"), 6.0);
    assert.deepEqual(run('12 / -{2, "Ignore me."}'), -6.0);
  });

  it("should return errors when strings are not numbers", function () {
    assert.deepEqual((run('9 / "No good."') as F7Exception).name, F7ExceptionName.VALUE);
  });

  it("should work with blanks", function () {
    const lookup = stub();
    const collateralLookup = stub();
    collateralLookup.withArgs(CommonModels.A1, 2).returns(2);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M44_RANGE).returns(null);
    assert.deepEqual(
      (runWithLookups("2 / M44", lookup, collateralLookup) as F7Exception).name,
      F7ExceptionName.DIV
    );
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, 2));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M44_RANGE));
    assert.equal(collateralLookup.callCount, 2);
    assert.isTrue(lookup.notCalled);
  });

  it("should return errors when error literals are present", function () {
    assert.deepEqual((run("3 / #NULL!") as F7Exception).name, F7ExceptionName.NULL);
    assert.deepEqual((run("3 / #DIV/0!") as F7Exception).name, F7ExceptionName.DIV);
    assert.deepEqual((run("3 / #VALUE!") as F7Exception).name, F7ExceptionName.VALUE);
    assert.deepEqual((run("3 / #REF!") as F7Exception).name, F7ExceptionName.REF);
    assert.deepEqual((run("3 / #NAME?") as F7Exception).name, F7ExceptionName.NAME);
    assert.deepEqual((run("3 / #NUM!") as F7Exception).name, F7ExceptionName.NUM);
    assert.deepEqual((run("3 / #N/A") as F7Exception).name, F7ExceptionName.NA);
    assert.deepEqual((run("3 / #ERROR!") as F7Exception).name, F7ExceptionName.PARSE);
  });
});
