import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ATAN2 } from "../../../../main/js/formulas/math/ATAN2";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("ATAN2", function () {
  it("should work with valid numbers", function () {
    assert.deepEqual(ATAN2.SELF.run(null, 10.0, 4.0), Math.atan2(10.0, 4.0));
    assert.deepEqual(ATAN2.SELF.run(null, 128731.2, 4.0), Math.atan2(128731.2, 4.0));
    assert.deepEqual(ATAN2.SELF.run(null, 11.11, 4.0), Math.atan2(11.11, 4.0));
    assert.deepEqual(ATAN2.SELF.run(null, 0.0, 4.0), Math.atan2(0.0, 4.0));
    assert.deepEqual(ATAN2.SELF.run(null, 88281.0, 4.0), Math.atan2(88281, 4.0));
    assert.deepEqual(ATAN2.SELF.run(null, 2.0, 4.0), Math.atan2(2.0, 4.0));
    assert.deepEqual(ATAN2.SELF.run(null, 4.0, 4.0), Math.atan2(4.0, 4.0));
    assert.deepEqual(ATAN2.SELF.run(null, -4.0, 4.0), Math.atan2(-4.0, 4.0));
    assert.deepEqual(ATAN2.SELF.run(null, -10124.0, 4.0), Math.atan2(-10124.0, 4.0));
  });

  it("should work with strings", function () {
    assert.equal(ATAN2.SELF.run(null, "10", "4"), Math.atan2(10.0, 4.0));
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(ATAN2.SELF.run(null, new ValueException(), 10), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ATAN2(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(10);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.G19_RANGE).returns(4);
    assert.deepEqual(
      F.run(CommonModels.A1, CommonModels.M22_RANGE, CommonModels.G19_RANGE),
      Math.atan2(10, 4)
    );
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.G19_RANGE));
    assert.equal(collateralLookup.callCount, 2);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 10).add(0, 1, "A").build();
    const two = Grid.builder().add(0, 0, 4).add(0, 1, "B").build();
    assert.deepEqual(ATAN2.SELF.run(null, one, two), Math.atan2(10, 4));
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ATAN2.SELF.run(null, "A") as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((ATAN2.SELF.run(null, "A", "B", "C") as NAException).name, F7ExceptionName.NA);
  });
});
