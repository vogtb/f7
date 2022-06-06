import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { QUOTIENT } from "../../../../main/js/formulas/math/QUOTIENT";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("QUOTIENT", function () {
  it("should do normal operations", function () {
    assert.deepEqual(QUOTIENT.SELF.run(null, 10, 10), 1);
    assert.deepEqual(QUOTIENT.SELF.run(null, 10, 2), 5);
    assert.deepEqual(QUOTIENT.SELF.run(null, 10, 3), 3);
    assert.deepEqual(QUOTIENT.SELF.run(null, 0, 1628736813.2), 0);
    assert.deepEqual(QUOTIENT.SELF.run(null, 218637221.22, 2876.111), 76018);
    assert.deepEqual(QUOTIENT.SELF.run(null, 9, -4), -2);
    assert.deepEqual(QUOTIENT.SELF.run(null, -9, -4), 2);
    assert.deepEqual(QUOTIENT.SELF.run(null, -9, 4), -2);
  });

  it("should do string conversion", function () {
    assert.deepEqual(QUOTIENT.SELF.run(null, "10", "10"), 1);
    assert.deepEqual(QUOTIENT.SELF.run(null, "10", "2"), 5);
    assert.deepEqual(QUOTIENT.SELF.run(null, "10", "3"), 3);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(
      (QUOTIENT.SELF.run(null, 10, new ValueException()) as F7Exception).name,
      F7ExceptionName.VALUE
    );
    assert.deepEqual(
      (QUOTIENT.SELF.run(null, new ValueException(), 10) as F7Exception).name,
      F7ExceptionName.VALUE
    );
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new QUOTIENT(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(10);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.G19_RANGE).returns(3);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE, CommonModels.G19_RANGE), 3);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.G19_RANGE));
    assert.equal(collateralLookup.callCount, 2);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 10).add(0, 1, "A").build();
    const two = Grid.builder().add(0, 0, 3).add(0, 1, "B").build();
    assert.deepEqual(QUOTIENT.SELF.run(null, one, two), 3);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((QUOTIENT.SELF.run(null, "Too few") as NAException).name, F7ExceptionName.NA);
    assert.deepEqual(
      (QUOTIENT.SELF.run(null, "A", "B", "Too many") as NAException).name,
      F7ExceptionName.NA
    );
  });
});
