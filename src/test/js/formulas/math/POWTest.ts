import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { POW } from "../../../../main/js/formulas/math/POW";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("POW", function () {
  it("should do normal operations", function () {
    assert.deepEqual(POW.SELF.run(null, 2.0, 6.0), 64);
    assert.deepEqual(POW.SELF.run(null, -2.0, 6.0), 64);
    assert.deepEqual(POW.SELF.run(null, -2.0, 5.0), -32);
    assert.deepEqual(POW.SELF.run(null, 2.0, -5.0), 0.03125);
    assert.deepEqual(POW.SELF.run(null, 3.0, 1.668132), 6.25030532353381);
  });

  it("should do handle NaN as NumException", function () {
    assert.deepEqual((POW.SELF.run(null, -2.0, -0.05) as F7Exception).name, F7ExceptionName.NUM);
  });

  it("should do string conversion", function () {
    assert.deepEqual(POW.SELF.run(null, "10", "2"), 100);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(
      (POW.SELF.run(null, 10, new ValueException()) as F7Exception).name,
      F7ExceptionName.VALUE
    );
    assert.deepEqual(
      (POW.SELF.run(null, new ValueException(), 10) as F7Exception).name,
      F7ExceptionName.VALUE
    );
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new POW(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(2);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.G19_RANGE).returns(5);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE, CommonModels.G19_RANGE), 32);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.G19_RANGE));
    assert.equal(collateralLookup.callCount, 2);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 2).add(0, 1, "A").build();
    const two = Grid.builder().add(0, 0, 5).add(0, 1, "B").build();
    assert.deepEqual(POW.SELF.run(null, one, two), 32);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((POW.SELF.run(null, "Too few") as NAException).name, F7ExceptionName.NA);
    assert.deepEqual(
      (POW.SELF.run(null, "A", "B", "Too many") as NAException).name,
      F7ExceptionName.NA
    );
  });
});
