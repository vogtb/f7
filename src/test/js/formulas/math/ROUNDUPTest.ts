import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ROUNDUP } from "../../../../main/js/formulas/math/ROUNDUP";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("ROUNDUP", function () {
  it("should work with whole numbers with places defaulting to 0", function () {
    assert.deepEqual(ROUNDUP.SELF.run(null, 10), 10);
    assert.deepEqual(ROUNDUP.SELF.run(null, -10), -10);
    assert.deepEqual(ROUNDUP.SELF.run(null, 0), 0);
  });

  it("should round down decimals to full with places defaulting to 0", function () {
    assert.deepEqual(ROUNDUP.SELF.run(null, 3.218639128), 4);
    assert.deepEqual(ROUNDUP.SELF.run(null, 7.99), 8);
    assert.deepEqual(ROUNDUP.SELF.run(null, 7.0000000001), 8);
    assert.deepEqual(ROUNDUP.SELF.run(null, -4.444444), -5);
  });

  it("should round up to place", function () {
    assert.deepEqual(ROUNDUP.SELF.run(null, 3.218639128, 4), 3.2187);
    assert.deepEqual(ROUNDUP.SELF.run(null, 3.218639128, 5), 3.21864);
    assert.deepEqual(ROUNDUP.SELF.run(null, 9.12, 0), 10);
    assert.deepEqual(ROUNDUP.SELF.run(null, 9.12, 1), 9.2);
    assert.deepEqual(ROUNDUP.SELF.run(null, 9.12, 2), 9.12);
    assert.deepEqual(ROUNDUP.SELF.run(null, 9.12, 3), 9.12);
    assert.deepEqual(ROUNDUP.SELF.run(null, 9.12, 8), 9.12);
  });

  it("should round up to the negative place", function () {
    assert.deepEqual(ROUNDUP.SELF.run(null, 3341.218639128, 0), 3342);
    assert.deepEqual(ROUNDUP.SELF.run(null, 3341.218639128, -1), 3350);
    assert.deepEqual(ROUNDUP.SELF.run(null, 3341.218639128, -2), 3400);
    assert.deepEqual(ROUNDUP.SELF.run(null, 3341.218639128, -3), 4000);
    assert.deepEqual(ROUNDUP.SELF.run(null, 3341.218639128, -4), 10000);
    assert.deepEqual(ROUNDUP.SELF.run(null, -3341.839749239, 0), -3342);
    assert.deepEqual(ROUNDUP.SELF.run(null, -3341.839749239, -1), -3350);
    assert.deepEqual(ROUNDUP.SELF.run(null, -3341.839749239, -2), -3400);
    assert.deepEqual(ROUNDUP.SELF.run(null, -3341.839749239, -3), -4000);
    assert.deepEqual(ROUNDUP.SELF.run(null, -3341.839749239, -4), -10000);
  });

  it("should round to non-integer places", function () {
    assert.deepEqual(ROUNDUP.SELF.run(null, 3341.218639128, -4.99), 10000.0);
    assert.deepEqual(ROUNDUP.SELF.run(null, 3341.218639128, -3.999999), 4000.0);
    assert.deepEqual(ROUNDUP.SELF.run(null, 3341.218639128, -2.1), 3400.0);
    assert.deepEqual(ROUNDUP.SELF.run(null, 3341.218639128, -1.1982371982), 3350.0);
    assert.deepEqual(ROUNDUP.SELF.run(null, 3341.218639128, 0.0), 3342.0);
    assert.deepEqual(ROUNDUP.SELF.run(null, 3341.218639128, 0.999), 3342.0);
    assert.deepEqual(ROUNDUP.SELF.run(null, 3341.218639128, -0.999), 3342.0);
    assert.deepEqual(ROUNDUP.SELF.run(null, 3341.218639128, 1.111), 3341.3);
    assert.deepEqual(ROUNDUP.SELF.run(null, 3341.218639128, -1.111), 3350.0);
  });

  it("should work with strings", function () {
    assert.deepEqual(ROUNDUP.SELF.run(null, "9"), 9);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(ROUNDUP.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ROUNDUP(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(1.1);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 2);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 1.1).add(0, 1, "A").build();
    assert.deepEqual(ROUNDUP.SELF.run(null, one), 2);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ROUNDUP.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual(
      (ROUNDUP.SELF.run(null, "A", "B", "C") as NAException).name,
      F7ExceptionName.NA
    );
  });
});
