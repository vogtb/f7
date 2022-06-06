import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ODD } from "../../../../main/js/formulas/math/ODD";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("ODD", function () {
  it("should work with positive numbers", function () {
    assert.deepEqual(ODD.SELF.run(null, 2), 3);
    assert.deepEqual(ODD.SELF.run(null, 1), 1);
    assert.deepEqual(ODD.SELF.run(null, 1.1), 3);
    assert.deepEqual(ODD.SELF.run(null, 2.1), 3);
    assert.deepEqual(ODD.SELF.run(null, 3), 3);
    assert.deepEqual(ODD.SELF.run(null, 0), 1);
    assert.deepEqual(ODD.SELF.run(null, -0), 1);
    assert.deepEqual(ODD.SELF.run(null, 0.1298738912), 1);
    assert.deepEqual(ODD.SELF.run(null, 1.1298738912), 3);
    assert.deepEqual(ODD.SELF.run(null, 2.1298738912), 3);
    assert.deepEqual(ODD.SELF.run(null, true), 1);
    assert.deepEqual(ODD.SELF.run(null, false), 1);
  });

  it("should work with negative numbers", function () {
    assert.deepEqual(ODD.SELF.run(null, -2), -3);
    assert.deepEqual(ODD.SELF.run(null, -1), -1);
    assert.deepEqual(ODD.SELF.run(null, -1.1), -3);
    assert.deepEqual(ODD.SELF.run(null, -2.1), -3);
    assert.deepEqual(ODD.SELF.run(null, -3), -3);
    assert.deepEqual(ODD.SELF.run(null, 0), 1);
  });

  it("should work with strings", function () {
    assert.deepEqual(ODD.SELF.run(null, "2"), 3);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(ODD.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ODD(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(0.1298738912);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 1);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 0.1298738).add(0, 1, "A").build();
    assert.deepEqual(ODD.SELF.run(null, one), 1);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ODD.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((ODD.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
