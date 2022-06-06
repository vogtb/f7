import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ACOT } from "../../../../main/js/formulas/math/ACOT";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("ACOT", function () {
  it("should work with numbers", function () {
    assert.deepEqual(ACOT.SELF.run(null, 10), 0.09966865249116204);
    assert.deepEqual(ACOT.SELF.run(null, 128731.2), 7.768124588133144e-6);
    assert.deepEqual(ACOT.SELF.run(null, 11.11), 0.08976710276137885);
    assert.deepEqual(ACOT.SELF.run(null, 0), 1.570796327);
    assert.deepEqual(ACOT.SELF.run(null, 88281), 1.1327465705613094e-5);
    assert.deepEqual(ACOT.SELF.run(null, 2), 0.4636476090008061);
    assert.deepEqual(ACOT.SELF.run(null, 4), 0.24497866312686414);
    assert.deepEqual(ACOT.SELF.run(null, -4), -0.24497866312686414);
    assert.deepEqual(ACOT.SELF.run(null, -10124), -9.877518735162196e-5);
  });

  it("should work with strings", function () {
    assert.equal(ACOT.SELF.run(null, "10"), 0.09966865249116204);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(ACOT.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ACOT(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(10);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 0.09966865249116204);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 10).add(0, 1, "A").build();
    assert.deepEqual(ACOT.SELF.run(null, one), 0.09966865249116204);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ACOT.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((ACOT.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
