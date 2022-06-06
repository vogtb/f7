import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ROUND } from "../../../../main/js/formulas/math/ROUND";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("ROUND", function () {
  it("should work with whole numbers with places defaulting to 0", function () {
    assert.deepEqual(ROUND.SELF.run(null, 10), 10);
    assert.deepEqual(ROUND.SELF.run(null, -10), -10);
    assert.deepEqual(ROUND.SELF.run(null, 0), 0);
  });

  it("should round down decimals to full with places defaulting to 0", function () {
    assert.deepEqual(ROUND.SELF.run(null, 3.218639128), 3);
    assert.deepEqual(ROUND.SELF.run(null, 7.99), 8);
    assert.deepEqual(ROUND.SELF.run(null, 7.0000000001), 7);
    assert.deepEqual(ROUND.SELF.run(null, -4.444444), -4);
  });

  it("should round up to place", function () {
    assert.deepEqual(ROUND.SELF.run(null, 3.218639128, 4), 3.2186);
    assert.deepEqual(ROUND.SELF.run(null, 3.218639128, 5), 3.21864);
    assert.deepEqual(ROUND.SELF.run(null, 9.12, 0), 9);
    assert.deepEqual(ROUND.SELF.run(null, 9.12, 1), 9.1);
    assert.deepEqual(ROUND.SELF.run(null, 9.12, 2), 9.12);
    assert.deepEqual(ROUND.SELF.run(null, 9.12, 3), 9.12);
    assert.deepEqual(ROUND.SELF.run(null, 9.12, 8), 9.12);
  });

  it("should round up to the negative place", function () {
    assert.deepEqual(ROUND.SELF.run(null, 3341.218639128, 0), 3341);
    assert.deepEqual(ROUND.SELF.run(null, 3341.218639128, -1), 3340);
    assert.deepEqual(ROUND.SELF.run(null, 3341.218639128, -2), 3300);
    assert.deepEqual(ROUND.SELF.run(null, 3341.218639128, -3), 3000);
    assert.deepEqual(ROUND.SELF.run(null, 3341.218639128, -4), 0);
    assert.deepEqual(ROUND.SELF.run(null, -3341.839749239, 0), -3342);
    assert.deepEqual(ROUND.SELF.run(null, -3341.839749239, -1), -3340);
    assert.deepEqual(ROUND.SELF.run(null, -3341.839749239, -2), -3300);
    assert.deepEqual(ROUND.SELF.run(null, -3341.839749239, -3), -3000);
    assert.deepEqual(ROUND.SELF.run(null, -3341.839749239, -4), 0);
  });

  it("should round to non-integer places", function () {
    assert.deepEqual(ROUND.SELF.run(null, 3341.218639128, -4.99), 0);
    assert.deepEqual(ROUND.SELF.run(null, 3341.218639128, -3.999999), 3000);
    assert.deepEqual(ROUND.SELF.run(null, 3341.218639128, -2.1), 3300);
    assert.deepEqual(ROUND.SELF.run(null, 3341.218639128, -1.1982371982), 3340);
    assert.deepEqual(ROUND.SELF.run(null, 3341.218639128, 0), 3341);
    assert.deepEqual(ROUND.SELF.run(null, 3341.218639128, 0.999), 3341);
    assert.deepEqual(ROUND.SELF.run(null, 3341.218639128, -0.999), 3341);
    assert.deepEqual(ROUND.SELF.run(null, 3341.218639128, 1.111), 3341.2);
    assert.deepEqual(ROUND.SELF.run(null, 3341.218639128, -1.111), 3340);
  });

  it("should work with strings", function () {
    assert.deepEqual(ROUND.SELF.run(null, "9"), 9);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(ROUND.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ROUND(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(1.1);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 1);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 1.1).add(0, 1, "A").build();
    assert.deepEqual(ROUND.SELF.run(null, one), 1);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ROUND.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((ROUND.SELF.run(null, "A", "B", "C") as NAException).name, F7ExceptionName.NA);
  });
});
