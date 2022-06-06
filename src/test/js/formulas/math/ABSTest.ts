import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ABS } from "../../../../main/js/formulas/math/ABS";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("ABS", function () {
  it("should work with numbers", function () {
    assert.equal(ABS.SELF.run(null, 10), 10);
    assert.equal(ABS.SELF.run(null, -10), 10);
    assert.equal(ABS.SELF.run(null, -0), 0);
  });

  it("should work with strings", function () {
    assert.equal(ABS.SELF.run(null, "10"), 10);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(ABS.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ABS(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(-2);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 2);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, -4).add(0, 1, "A").build();
    assert.deepEqual(ABS.SELF.run(null, one), 4);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ABS.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((ABS.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
