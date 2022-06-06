import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ISEVEN } from "../../../../main/js/formulas/math/ISEVEN";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("ISEVEN", function () {
  it("should work with numbers", function () {
    assert.deepEqual(ISEVEN.SELF.run(null, 10), true);
    assert.deepEqual(ISEVEN.SELF.run(null, -10), true);
    assert.deepEqual(ISEVEN.SELF.run(null, 0), true);
    assert.deepEqual(ISEVEN.SELF.run(null, 1), false);
    assert.deepEqual(ISEVEN.SELF.run(null, 2), true);
    assert.deepEqual(ISEVEN.SELF.run(null, 3), false);
    assert.deepEqual(ISEVEN.SELF.run(null, -218637221), false);
    assert.deepEqual(ISEVEN.SELF.run(null, -218637221.99), false);
  });

  it("should work with strings", function () {
    assert.deepEqual(ISEVEN.SELF.run(null, "9.9"), false);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(ISEVEN.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ISEVEN(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(1.1);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), false);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 1.1).add(0, 1, "A").build();
    assert.deepEqual(ISEVEN.SELF.run(null, one), false);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ISEVEN.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((ISEVEN.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
