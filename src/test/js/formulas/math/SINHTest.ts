import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { SINH } from "../../../../main/js/formulas/math/SINH";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("SINH", function () {
  it("should work with valid numbers", function () {
    assert.deepEqual(SINH.SELF.run(null, 10), Math["sinh"](10));
    assert.deepEqual(SINH.SELF.run(null, 128731.2), Math["sinh"](128731.2));
    assert.deepEqual(SINH.SELF.run(null, 11.11), Math["sinh"](11.11));
    assert.deepEqual(SINH.SELF.run(null, 0), 0);
    assert.deepEqual(SINH.SELF.run(null, 88281), Math["sinh"](88281));
    assert.deepEqual(SINH.SELF.run(null, 2), Math["sinh"](2));
    assert.deepEqual(SINH.SELF.run(null, 4), Math["sinh"](4));
    assert.deepEqual(SINH.SELF.run(null, -4), Math["sinh"](-4));
    assert.deepEqual(SINH.SELF.run(null, -10124), Math["sinh"](-10124));
  });

  it("should work with strings", function () {
    assert.equal(SINH.SELF.run(null, "10"), Math["sinh"](10));
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(SINH.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new SINH(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(10);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), Math["sinh"](10));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 10).add(0, 1, "A").build();
    assert.deepEqual(SINH.SELF.run(null, one), Math["sinh"](10));
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((SINH.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((SINH.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
