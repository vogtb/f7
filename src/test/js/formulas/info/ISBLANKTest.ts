import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ISBLANK } from "../../../../main/js/formulas/info/ISBLANK";
import { Grid } from "../../../../main/js/models/common/Grid";
import {
  CollateralLookupFunction,
  Computed,
  LookupFunction,
} from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("ISBLANK", function () {
  it("should work with numbers", function () {
    assert.deepEqual(ISBLANK.SELF.run(null, 10), false);
  });

  it("should work with strings", function () {
    assert.deepEqual(ISBLANK.SELF.run(null, "String"), false);
  });

  it("should work with booleans", function () {
    assert.deepEqual(ISBLANK.SELF.run(null, true), false);
    assert.deepEqual(ISBLANK.SELF.run(null, false), false);
  });

  it("should work with errors", function () {
    assert.deepEqual(ISBLANK.SELF.run(null, new NAException()), false);
  });

  it("should work with blank/null/empty", function () {
    assert.deepEqual(ISBLANK.SELF.run(null, null), true);
  });

  it("should work with lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ISBLANK(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(null);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), true);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should work with grid", function () {
    assert.deepEqual(ISBLANK.SELF.run(null, Grid.from<Computed>([["Yes"]])), false);
    assert.deepEqual(ISBLANK.SELF.run(null, Grid.from<Computed>([[22000]])), false);
    assert.deepEqual(ISBLANK.SELF.run(null, Grid.from<Computed>([[true]])), false);
    assert.deepEqual(ISBLANK.SELF.run(null, Grid.from<Computed>([["Nope"], [false]])), false);
    assert.deepEqual(ISBLANK.SELF.run(null, Grid.from<Computed>([[new NAException()]])), false);
  });

  it("should return error when arguments are not of the correct length", function () {
    assert.deepEqual((ISBLANK.SELF.run(null) as F7Exception).name, F7ExceptionName.NA);
    assert.deepEqual((ISBLANK.SELF.run(null, "A", "B") as F7Exception).name, F7ExceptionName.NA);
  });
});
