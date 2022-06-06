import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { EVEN } from "../../../../main/js/formulas/math/EVEN";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("EVEN", function () {
  it("should work with positive numbers", function () {
    assert.deepEqual(EVEN.SELF.run(null, 2), 2);
    assert.deepEqual(EVEN.SELF.run(null, 1), 2);
    assert.deepEqual(EVEN.SELF.run(null, 1.1), 2);
    assert.deepEqual(EVEN.SELF.run(null, 2.1), 4);
    assert.deepEqual(EVEN.SELF.run(null, 3), 4);
    assert.deepEqual(EVEN.SELF.run(null, 0), 0);
    assert.deepEqual(EVEN.SELF.run(null, -0), 0);
    assert.deepEqual(EVEN.SELF.run(null, 0.1298738912), 2);
    assert.deepEqual(EVEN.SELF.run(null, 1.1298738912), 2);
    assert.deepEqual(EVEN.SELF.run(null, 2.1298738912), 4);
    assert.deepEqual(EVEN.SELF.run(null, true), 2);
    assert.deepEqual(EVEN.SELF.run(null, false), 0);
  });

  it("should work with negative numbers", function () {
    assert.deepEqual(EVEN.SELF.run(null, -2.0), -2.0);
    assert.deepEqual(EVEN.SELF.run(null, -1.0), -2.0);
    assert.deepEqual(EVEN.SELF.run(null, -1.1), -2.0);
    assert.deepEqual(EVEN.SELF.run(null, -2.1), -4.0);
    assert.deepEqual(EVEN.SELF.run(null, -3.0), -4.0);
    assert.deepEqual(EVEN.SELF.run(null, 0.0), 0.0);
  });

  it("should work with strings", function () {
    assert.deepEqual(EVEN.SELF.run(null, "9.9"), 10.0);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(EVEN.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new EVEN(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(1.1);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 2);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 1.1).add(0, 1, "A").build();
    assert.deepEqual(EVEN.SELF.run(null, one), 2);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((EVEN.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((EVEN.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
