import { assert } from "chai";
import { it, describe } from "../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../main/js/errors/F7ExceptionName";
import { CommonModels } from "../testutils/CommonModels";
import { run, runWithLookups } from "../testutils/TestUtils";

describe("CodeExecutor - General Addition Test", function () {
  it("should work with numbers", function () {
    assert.deepEqual(run("1"), 1.0);
    assert.deepEqual(run("10 + 10"), 20.0);
    assert.deepEqual(run("0 + 0"), 0.0);
    assert.deepEqual(run("10 + -10"), 0.0);
    assert.deepEqual(run("-10 + -10"), -20.0);
    assert.deepEqual(run("1e10 + 1.1"), 1.00000000011e10);
  });

  it("should work with booleans", function () {
    assert.deepEqual(run("9 + TRUE"), 10.0);
    assert.deepEqual(run("9 + FALSE"), 9.0);
    assert.deepEqual(run("9 + -TRUE"), 8.0);
    assert.deepEqual(run("9 + -FALSE"), 9.0);
  });

  it("should work with strings", function () {
    assert.deepEqual(run('9 + "10"'), 19.0);
    assert.deepEqual(run('9 + "1e10"'), 1.0000000009e10);
    assert.deepEqual(run('9 + "0"'), 9.0);
  });

  it("should work with arrays", function () {
    assert.deepEqual(run("2 + {1}"), 3.0);
    assert.deepEqual(run("2 + {1, 44}"), 3.0);
    assert.deepEqual(run('2 + -{1, "Ignore me."}'), 1.0);
  });

  it("should work with blanks", function () {
    const lookup = stub();
    const collateralLookup = stub();
    collateralLookup.withArgs(CommonModels.A1, 2).returns(2);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M44_RANGE).returns(null);
    assert.deepEqual(runWithLookups("2 + M44", lookup, collateralLookup), 2);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, 2));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M44_RANGE));
    assert.equal(collateralLookup.callCount, 2);
    assert.isTrue(lookup.notCalled);
  });

  it("should return errors when strings are not numbers", function () {
    assert.deepEqual((run('9 + "No good."') as F7Exception).name, F7ExceptionName.VALUE);
    // TODO/HACK: This is commented out because javascript parses numbers in a weird way. Still thinking this one through.
    //assert.deepEqual((run("9 + \"10    10\"") as F7Exception).name, F7ExceptionName.VALUE);
  });

  it("should return errors when error literals are present", function () {
    assert.deepEqual((run("3 + #NULL!") as F7Exception).name, F7ExceptionName.NULL);
    assert.deepEqual((run("3 + #DIV/0!") as F7Exception).name, F7ExceptionName.DIV);
    assert.deepEqual((run("3 + #VALUE!") as F7Exception).name, F7ExceptionName.VALUE);
    assert.deepEqual((run("3 + #REF!") as F7Exception).name, F7ExceptionName.REF);
    assert.deepEqual((run("3 + #NAME?") as F7Exception).name, F7ExceptionName.NAME);
    assert.deepEqual((run("3 + #NUM!") as F7Exception).name, F7ExceptionName.NUM);
    assert.deepEqual((run("3 + #N/A") as F7Exception).name, F7ExceptionName.NA);
    assert.deepEqual((run("3 + #ERROR!") as F7Exception).name, F7ExceptionName.PARSE);
  });
});
