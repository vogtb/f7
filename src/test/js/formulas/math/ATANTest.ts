import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ATAN } from "../../../../main/js/formulas/math/ATAN";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("ATAN", function () {
  it("should work with valid numbers", function () {
    assert.deepEqual(ATAN.SELF.run(null, 10.0), Math.atan(10.0));
    assert.deepEqual(ATAN.SELF.run(null, 128731.2), Math.atan(128731.2));
    assert.deepEqual(ATAN.SELF.run(null, 11.11), Math.atan(11.11));
    assert.deepEqual(ATAN.SELF.run(null, 0.0), Math.atan(0.0));
    assert.deepEqual(ATAN.SELF.run(null, 88281.0), Math.atan(88281));
    assert.deepEqual(ATAN.SELF.run(null, 2.0), Math.atan(2.0));
    assert.deepEqual(ATAN.SELF.run(null, 4.0), Math.atan(4.0));
    assert.deepEqual(ATAN.SELF.run(null, -4.0), Math.atan(-4.0));
    assert.deepEqual(ATAN.SELF.run(null, -10124.0), Math.atan(-10124.0));
  });

  it("should work with strings", function () {
    assert.equal(ATAN.SELF.run(null, "1"), Math.atan(1));
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(ATAN.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ATAN(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(0.99);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), Math.atan(0.99));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 0.99).add(0, 1, "A").build();
    assert.deepEqual(ATAN.SELF.run(null, one), Math.atan(0.99));
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ATAN.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((ATAN.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
