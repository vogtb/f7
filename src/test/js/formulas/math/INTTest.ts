import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { INT } from "../../../../main/js/formulas/math/INT";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("INT", function () {
  it("should work with positive numbers", function () {
    assert.deepEqual(INT.SELF.run(null, 99.9), 99);
    assert.deepEqual(INT.SELF.run(null, -10.4), -11);
    assert.deepEqual(INT.SELF.run(null, 0), 0);
    assert.deepEqual(INT.SELF.run(null, 0.111), 0);
    assert.deepEqual(INT.SELF.run(null, -0), 0);
  });

  it("should work with strings", function () {
    assert.deepEqual(INT.SELF.run(null, "0.111"), 0);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(INT.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new INT(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(0.111);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 0);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 0.111).add(0, 1, "A").build();
    assert.deepEqual(INT.SELF.run(null, one), 0);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((INT.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((INT.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
