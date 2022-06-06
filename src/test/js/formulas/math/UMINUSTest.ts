import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { UMINUS } from "../../../../main/js/formulas/math/UMINUS";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("UMINUS", function () {
  it("should do normal operations", function () {
    assert.deepEqual(UMINUS.SELF.run(null, 10), -10);
    assert.deepEqual(UMINUS.SELF.run(null, -10), 10);
    assert.deepEqual(UMINUS.SELF.run(null, 0), 0);
    assert.deepEqual(UMINUS.SELF.run(null, 8278.28687), -8278.28687);
  });

  it("should do string conversion", function () {
    assert.deepEqual(UMINUS.SELF.run(null, "10"), -10);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(
      (UMINUS.SELF.run(null, new ValueException()) as F7Exception).name,
      F7ExceptionName.VALUE
    );
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new UMINUS(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(2);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), -2);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 2).add(0, 1, "A").build();
    assert.deepEqual(UMINUS.SELF.run(null, one), -2);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual(
      (UMINUS.SELF.run(null, "A", "Too many") as NAException).name,
      F7ExceptionName.NA
    );
  });
});
