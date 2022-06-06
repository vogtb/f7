import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { SQRTPI } from "../../../../main/js/formulas/math/SQRTPI";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("SQRTPI", function () {
  it("should work with valid numbers", function () {
    assert.deepEqual(SQRTPI.SELF.run(null, 10), 5.604991216397929);
    assert.deepEqual(SQRTPI.SELF.run(null, 128731.2), 635.9410288759473);
    assert.deepEqual(SQRTPI.SELF.run(null, 11.11), 5.907884086657642);
    assert.deepEqual(SQRTPI.SELF.run(null, 0), 0);
    assert.deepEqual(SQRTPI.SELF.run(null, 88281), 526.6335927868261);
    assert.deepEqual(SQRTPI.SELF.run(null, 2), 2.5066282746310002);
    assert.deepEqual(SQRTPI.SELF.run(null, 4), 3.5449077018110318);
  });

  it("should return error when the parameter is less than 0", function () {
    assert.deepEqual((SQRTPI.SELF.run(null, -10) as NAException).name, F7ExceptionName.NUM);
  });

  it("should work with strings", function () {
    assert.equal(SQRTPI.SELF.run(null, "4"), 3.5449077018110318);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(SQRTPI.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new SQRTPI(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(4);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 3.5449077018110318);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 4).add(0, 1, "A").build();
    assert.deepEqual(SQRTPI.SELF.run(null, one), 3.5449077018110318);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((SQRTPI.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((SQRTPI.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
