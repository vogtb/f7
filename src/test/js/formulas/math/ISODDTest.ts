import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ISODD } from "../../../../main/js/formulas/math/ISODD";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("ISODD", function () {
  it("should work with numbers", function () {
    assert.deepEqual(ISODD.SELF.run(null, 10), false);
    assert.deepEqual(ISODD.SELF.run(null, -10), false);
    assert.deepEqual(ISODD.SELF.run(null, 0), false);
    assert.deepEqual(ISODD.SELF.run(null, 1), true);
    assert.deepEqual(ISODD.SELF.run(null, 2), false);
    assert.deepEqual(ISODD.SELF.run(null, 3), true);
    assert.deepEqual(ISODD.SELF.run(null, -218637221), true);
    assert.deepEqual(ISODD.SELF.run(null, -218637221.99), true);
  });

  it("should work with strings", function () {
    assert.deepEqual(ISODD.SELF.run(null, "9.9"), true);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(ISODD.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ISODD(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(1.1);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), true);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 1.1).add(0, 1, "A").build();
    assert.deepEqual(ISODD.SELF.run(null, one), true);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ISODD.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((ISODD.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
