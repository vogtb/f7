import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { COT } from "../../../../main/js/formulas/math/COT";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { Converters } from "../../../../main/js/utils/Converters";
import { CommonModels } from "../../testutils/CommonModels";

describe("COT", function () {
  it("should work with valid numbers", function () {
    assert.deepEqual(COT.SELF.run(null, 10.0), 1 / Math.tan(10.0));
    assert.deepEqual(COT.SELF.run(null, 128731.2), 1 / Math.tan(128731.2));
    assert.deepEqual(COT.SELF.run(null, 11.11), 1 / Math.tan(11.11));
    assert.deepEqual(
      Converters.castAsF7Exception(COT.SELF.run(null, 0.0)).name,
      F7ExceptionName.DIV
    );
    assert.deepEqual(COT.SELF.run(null, 88281.0), 1 / Math.tan(88281));
    assert.deepEqual(COT.SELF.run(null, 2.0), 1 / Math.tan(2.0));
    assert.deepEqual(COT.SELF.run(null, 4.0), 1 / Math.tan(4.0));
    assert.deepEqual(COT.SELF.run(null, -4.0), 1 / Math.tan(-4.0));
    assert.deepEqual(COT.SELF.run(null, -10124.0), 1 / Math.tan(-10124.0));
  });

  it("should work with strings", function () {
    assert.equal(COT.SELF.run(null, "1"), 1 / Math.tan(1));
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(COT.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new COT(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(0.99);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 1 / Math.tan(0.99));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 0.99).add(0, 1, "A").build();
    assert.deepEqual(COT.SELF.run(null, one), 1 / Math.tan(0.99));
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((COT.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((COT.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
