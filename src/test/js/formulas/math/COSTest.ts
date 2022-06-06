import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { COS } from "../../../../main/js/formulas/math/COS";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("COS", function () {
  it("should work with valid numbers", function () {
    assert.deepEqual(COS.SELF.run(null, 10.0), Math.cos(10.0));
    assert.deepEqual(COS.SELF.run(null, 128731.2), Math.cos(128731.2));
    assert.deepEqual(COS.SELF.run(null, 11.11), Math.cos(11.11));
    assert.deepEqual(COS.SELF.run(null, 0.0), 1);
    assert.deepEqual(COS.SELF.run(null, 88281.0), Math.cos(88281));
    assert.deepEqual(COS.SELF.run(null, 2.0), Math.cos(2.0));
    assert.deepEqual(COS.SELF.run(null, 4.0), Math.cos(4.0));
    assert.deepEqual(COS.SELF.run(null, -4.0), Math.cos(-4.0));
    assert.deepEqual(COS.SELF.run(null, -10124.0), Math.cos(-10124.0));
  });

  it("should work with strings", function () {
    assert.equal(COS.SELF.run(null, "10"), Math.cos(10.0));
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(COS.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new COS(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(10);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), Math.cos(10.0));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 10).add(0, 1, "A").build();
    assert.deepEqual(COS.SELF.run(null, one), Math.cos(10.0));
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((COS.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((COS.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
