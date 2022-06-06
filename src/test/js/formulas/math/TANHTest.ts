import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { TANH } from "../../../../main/js/formulas/math/TANH";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("TANH", function () {
  it("should work with valid numbers", function () {
    assert.deepEqual(TANH.SELF.run(null, 10), Math["tanh"](10));
    assert.deepEqual(TANH.SELF.run(null, 128731.2), Math["tanh"](128731.2));
    assert.deepEqual(TANH.SELF.run(null, 11.11), Math["tanh"](11.11));
    assert.deepEqual(TANH.SELF.run(null, 0), 0);
    assert.deepEqual(TANH.SELF.run(null, 88281.0), Math["tanh"](88281));
    assert.deepEqual(TANH.SELF.run(null, 2), Math["tanh"](2));
    assert.deepEqual(TANH.SELF.run(null, 4), Math["tanh"](4));
    assert.deepEqual(TANH.SELF.run(null, -4), Math["tanh"](-4));
    assert.deepEqual(TANH.SELF.run(null, -10124), Math["tanh"](-10124));
  });

  it("should work with strings", function () {
    assert.equal(TANH.SELF.run(null, "10"), Math["tanh"](10));
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(TANH.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new TANH(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(10);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), Math["tanh"](10));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 10).add(0, 1, "A").build();
    assert.deepEqual(TANH.SELF.run(null, one), Math["tanh"](10));
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((TANH.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((TANH.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
