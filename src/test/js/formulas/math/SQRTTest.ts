import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { SQRT } from "../../../../main/js/formulas/math/SQRT";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("SQRT", function () {
  it("should work with valid numbers", function () {
    assert.deepEqual(SQRT.SELF.run(null, 10), Math.sqrt(10));
    assert.deepEqual(SQRT.SELF.run(null, 128731.2), Math.sqrt(128731.2));
    assert.deepEqual(SQRT.SELF.run(null, 11.11), Math.sqrt(11.11));
    assert.deepEqual(SQRT.SELF.run(null, 0), 0);
    assert.deepEqual(SQRT.SELF.run(null, 88281), Math.sqrt(88281));
    assert.deepEqual(SQRT.SELF.run(null, 2), Math.sqrt(2));
    assert.deepEqual(SQRT.SELF.run(null, 4), Math.sqrt(4));
  });

  it("should return error when the parameter is less than 0", function () {
    assert.deepEqual((SQRT.SELF.run(null, -10) as NAException).name, F7ExceptionName.NUM);
  });

  it("should work with strings", function () {
    assert.equal(SQRT.SELF.run(null, "10"), Math.sqrt(10));
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(SQRT.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new SQRT(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(10);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), Math.sqrt(10));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 10).add(0, 1, "A").build();
    assert.deepEqual(SQRT.SELF.run(null, one), Math.sqrt(10));
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((SQRT.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((SQRT.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
