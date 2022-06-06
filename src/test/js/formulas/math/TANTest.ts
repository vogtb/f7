import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { TAN } from "../../../../main/js/formulas/math/TAN";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("TAN", function () {
  it("should work with valid numbers", function () {
    assert.deepEqual(TAN.SELF.run(null, 10), Math.tan(10));
    assert.deepEqual(TAN.SELF.run(null, 128731.2), Math.tan(128731.2));
    assert.deepEqual(TAN.SELF.run(null, 11.11), Math.tan(11.11));
    assert.deepEqual(TAN.SELF.run(null, 0), 0);
    assert.deepEqual(TAN.SELF.run(null, 88281.0), Math.tan(88281));
    assert.deepEqual(TAN.SELF.run(null, 2), Math.tan(2));
    assert.deepEqual(TAN.SELF.run(null, 4), Math.tan(4));
    assert.deepEqual(TAN.SELF.run(null, -4), Math.tan(-4));
    assert.deepEqual(TAN.SELF.run(null, -10124), Math.tan(-10124));
  });

  it("should work with strings", function () {
    assert.equal(TAN.SELF.run(null, "10"), Math.tan(10));
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(TAN.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new TAN(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(10);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), Math.tan(10));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 10).add(0, 1, "A").build();
    assert.deepEqual(TAN.SELF.run(null, one), Math.tan(10));
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((TAN.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((TAN.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
