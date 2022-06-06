import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { DELTA } from "../../../../main/js/formulas/engineering/DELTA";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";
import { assertF7ExceptionByName } from "../../testutils/TestUtils";

describe("DELTA", function () {
  it("should work with plain numbers", function () {
    assert.deepEqual(DELTA.SELF.run(null, 1, 2), 0);
    assert.deepEqual(DELTA.SELF.run(null, 2, 2), 1);
    assert.deepEqual(DELTA.SELF.run(null, 8762, 8762), 1);
  });

  it("should work with string representations of numbers", function () {
    assert.deepEqual(DELTA.SELF.run(null, 1, "2"), 0);
    assert.deepEqual(DELTA.SELF.run(null, 2, "2"), 1);
  });

  it("should work with booleans", function () {
    assert.deepEqual(DELTA.SELF.run(null, true, true), 1);
    assert.deepEqual(DELTA.SELF.run(null, false, false), 1);
    assert.deepEqual(DELTA.SELF.run(null, false, true), 0);
  });

  it("should return VALUE error when called with strings that are not numbers", function () {
    assertF7ExceptionByName(
      DELTA.SELF.run(null, 1, "You have no good car ideas."),
      F7ExceptionName.VALUE
    );
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new DELTA(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(8762);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.G19_RANGE).returns(8762);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE, CommonModels.G19_RANGE), 1);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.G19_RANGE));
    assert.equal(collateralLookup.callCount, 2);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    assert.deepEqual(DELTA.SELF.run(null, Grid.from([[8762], [10]]), 8762), 1);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((DELTA.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((DELTA.SELF.run(null, "A", "B", "C") as NAException).name, F7ExceptionName.NA);
  });
});
