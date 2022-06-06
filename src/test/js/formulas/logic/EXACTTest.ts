import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { EXACT } from "../../../../main/js/formulas/logic/EXACT";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("EXACT", function () {
  it("should do same type equality", function () {
    assert.equal(EXACT.SELF.run(null, 10.0, 10.0), true);
    assert.equal(EXACT.SELF.run(null, 10.0, 0.0), false);
    assert.equal(EXACT.SELF.run(null, "Same", "Same"), true);
    assert.equal(EXACT.SELF.run(null, "Same", "Different"), false);
    assert.equal(EXACT.SELF.run(null, true, true), true);
    assert.equal(EXACT.SELF.run(null, true, false), false);
    assert.equal(EXACT.SELF.run(null, false, false), true);
  });

  it("should do pass-through errors", function () {
    assert.equal(
      (EXACT.SELF.run(null, 4.4444, new ValueException()) as F7Exception).name,
      F7ExceptionName.VALUE
    );
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new EXACT(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns("A");
    collateralLookup.withArgs(CommonModels.A1, CommonModels.G19_RANGE).returns("B");
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE, CommonModels.G19_RANGE), false);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.G19_RANGE));
    assert.equal(collateralLookup.callCount, 2);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 44.0).add(0, 1, "A").build();
    const two = Grid.builder().add(0, 0, 44.0).add(0, 1, "B").build();
    assert.deepEqual(EXACT.SELF.run(null, one, two), true);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((EXACT.SELF.run(null, "Too few") as NAException).name, F7ExceptionName.NA);
    assert.deepEqual(
      (EXACT.SELF.run(null, "A", "B", "Too many") as NAException).name,
      F7ExceptionName.NA
    );
  });
});
