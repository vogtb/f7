import { assert } from "chai";
import { describe, it } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { SIGN } from "../../../../main/js/formulas/math/SIGN";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("SIGN", function () {
  it("should do normal operations", function () {
    assert.equal(SIGN.SELF.run(null, 10), 1);
    assert.equal(SIGN.SELF.run(null, -10), -1);
    assert.equal(SIGN.SELF.run(null, 0), 0);
    assert.equal(SIGN.SELF.run(null, -0), 0);
    assert.equal(SIGN.SELF.run(null, -218637221.22), -1);
    assert.equal(SIGN.SELF.run(null, true), 1);
    assert.equal(SIGN.SELF.run(null, false), 0);
  });

  it("should do string conversion", function () {
    assert.equal(SIGN.SELF.run(null, "10"), 1);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(SIGN.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new SIGN(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(6);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 1);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, -111).add(0, 1, "A").build();
    assert.deepEqual(SIGN.SELF.run(null, one), -1);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((SIGN.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((SIGN.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
