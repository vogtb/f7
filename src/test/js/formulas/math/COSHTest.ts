import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { COSH } from "../../../../main/js/formulas/math/COSH";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("COSH", function () {
  it("should work with valid numbers", function () {
    assert.deepEqual(COSH.SELF.run(null, 10.0), Math["cosh"](10.0));
    assert.deepEqual(COSH.SELF.run(null, 128731.2), Math["cosh"](128731.2));
    assert.deepEqual(COSH.SELF.run(null, 11.11), Math["cosh"](11.11));
    assert.deepEqual(COSH.SELF.run(null, 0.0), 1);
    assert.deepEqual(COSH.SELF.run(null, 88281.0), Math["cosh"](88281));
    assert.deepEqual(COSH.SELF.run(null, 2.0), Math["cosh"](2.0));
    assert.deepEqual(COSH.SELF.run(null, 4.0), Math["cosh"](4.0));
    assert.deepEqual(COSH.SELF.run(null, -4.0), Math["cosh"](-4.0));
    assert.deepEqual(COSH.SELF.run(null, -10124.0), Math["cosh"](-10124.0));
  });

  it("should work with strings", function () {
    assert.equal(COSH.SELF.run(null, "10"), Math["cosh"](10.0));
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(COSH.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new COSH(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(10);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), Math["cosh"](10.0));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 10).add(0, 1, "A").build();
    assert.deepEqual(COSH.SELF.run(null, one), Math["cosh"](10.0));
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((COSH.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((COSH.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
