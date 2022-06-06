import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { FLOOR } from "../../../../main/js/formulas/math/FLOOR";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";
import { assertF7ExceptionByName } from "../../testutils/TestUtils";

describe("FLOOR", function () {
  it("should work with whole numbers", function () {
    assert.deepEqual(FLOOR.SELF.run(null, 10), 10);
    assert.deepEqual(FLOOR.SELF.run(null, -10), -10);
    assert.deepEqual(FLOOR.SELF.run(null, 0), 0);
  });

  it("should floor decimals to full integers", function () {
    assert.deepEqual(FLOOR.SELF.run(null, 3.218639128), 3);
    assert.deepEqual(FLOOR.SELF.run(null, 7.99), 7);
    assert.deepEqual(FLOOR.SELF.run(null, 7.0000000001), 7);
    assert.deepEqual(FLOOR.SELF.run(null, -4.444444), -5);
  });

  it("should floor to place", function () {
    assert.deepEqual(FLOOR.SELF.run(null, 3.218639128, 4), 0);
    assert.deepEqual(FLOOR.SELF.run(null, 3.218639128, 5), 0);
    assert.deepEqual(FLOOR.SELF.run(null, 9.12, 1), 9);
    assert.deepEqual(FLOOR.SELF.run(null, 9.12, 2), 8);
    assert.deepEqual(FLOOR.SELF.run(null, 9.12, 3), 9);
    assert.deepEqual(FLOOR.SELF.run(null, 9.12, 8), 8);
  });

  it("should return DIV error when second param is zero", function () {
    assertF7ExceptionByName(FLOOR.SELF.run(null, 3341.218639128, 0), F7ExceptionName.DIV);
    assertF7ExceptionByName(FLOOR.SELF.run(null, 9.12, 0), F7ExceptionName.DIV);
  });

  it("should return NUM error when params are not of the same sign (both pos. or both neg.)", function () {
    assertF7ExceptionByName(FLOOR.SELF.run(null, 3341.218639128, -1), F7ExceptionName.NUM);
    assertF7ExceptionByName(FLOOR.SELF.run(null, 3341.218639128, -2), F7ExceptionName.NUM);
    assertF7ExceptionByName(FLOOR.SELF.run(null, 3341.218639128, -3), F7ExceptionName.NUM);
    assertF7ExceptionByName(FLOOR.SELF.run(null, 3341.218639128, -4), F7ExceptionName.NUM);
    assertF7ExceptionByName(FLOOR.SELF.run(null, 3341.218639128, -4.99), F7ExceptionName.NUM);
    assertF7ExceptionByName(FLOOR.SELF.run(null, 3341.218639128, -3.999999), F7ExceptionName.NUM);
    assertF7ExceptionByName(FLOOR.SELF.run(null, 3341.218639128, -2.1), F7ExceptionName.NUM);
    assertF7ExceptionByName(
      FLOOR.SELF.run(null, 3341.218639128, -1.1982371982),
      F7ExceptionName.NUM
    );
  });

  it("should floor to the negative place", function () {
    assert.deepEqual(FLOOR.SELF.run(null, -3341.839749239, -1), -3341);
    assert.deepEqual(FLOOR.SELF.run(null, -3341.839749239, -2), -3340);
    assert.deepEqual(FLOOR.SELF.run(null, -3341.839749239, -3), -3339);
    assert.deepEqual(FLOOR.SELF.run(null, -3341.839749239, -4), -3340);
    assert.deepEqual(FLOOR.SELF.run(null, -3341.839749239, -5), -3340);
    assert.deepEqual(FLOOR.SELF.run(null, -3341.839749239, -6), -3336);
    assert.deepEqual(FLOOR.SELF.run(null, -3341.839749239, -7), -3339);
    assert.deepEqual(FLOOR.SELF.run(null, -3341.839749239, -8), -3336);
  });

  it("should round to non-integer places", function () {
    assert.deepEqual(FLOOR.SELF.run(null, 3341.218639128, 4.99), 3338.31);
    assert.deepEqual(FLOOR.SELF.run(null, 3341.218639128, 3.999999), 3339.9991649999997);
    assert.deepEqual(FLOOR.SELF.run(null, 3341.218639128, 2.1), 3341.1000000000004);
    assert.deepEqual(FLOOR.SELF.run(null, 3341.218639128, 1.1982371982), 3340.6853085816);
    assert.deepEqual(FLOOR.SELF.run(null, 3341.218639128, 0.999), 3340.656);
    assert.deepEqual(FLOOR.SELF.run(null, 3341.218639128, 1.111), 3340.777);
  });

  it("should work with strings", function () {
    assert.deepEqual(FLOOR.SELF.run(null, "9.1"), 9);
  });

  it("should do pass-through errors", function () {
    assertF7ExceptionByName(FLOOR.SELF.run(null, new ValueException()), F7ExceptionName.VALUE);
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new FLOOR(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(1.1);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 1);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 1.1).add(0, 1, "A").build();
    assert.deepEqual(FLOOR.SELF.run(null, one), 1);
  });

  it("should return error when argument lengths are wrong", function () {
    assertF7ExceptionByName(FLOOR.SELF.run(null), F7ExceptionName.NA);
    assertF7ExceptionByName(FLOOR.SELF.run(null, "A", "B", "C"), F7ExceptionName.NA);
  });
});
