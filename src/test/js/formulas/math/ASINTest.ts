import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ASIN } from "../../../../main/js/formulas/math/ASIN";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { Converters } from "../../../../main/js/utils/Converters";
import { CommonModels } from "../../testutils/CommonModels";

describe("ASIN", function () {
  it("should work with valid numbers", function () {
    assert.equal(ASIN.SELF.run(null, 0), Math.asin(0));
    assert.equal(ASIN.SELF.run(null, 0.99), Math.asin(0.99));
    assert.equal(ASIN.SELF.run(null, 1), Math.asin(1));
    assert.equal(ASIN.SELF.run(null, 0.55), Math.asin(0.55));
    assert.equal(ASIN.SELF.run(null, -0.55), Math.asin(-0.55));
  });

  it("should throw NUM error", function () {
    assert.deepEqual(
      Converters.castAsF7Exception(ASIN.SELF.run(null, 1.1)).name,
      F7ExceptionName.NUM
    );
    assert.deepEqual(
      Converters.castAsF7Exception(ASIN.SELF.run(null, -1.1)).name,
      F7ExceptionName.NUM
    );
  });

  it("should work with strings", function () {
    assert.equal(ASIN.SELF.run(null, "1"), Math.asin(1));
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(ASIN.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ASIN(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(0.99);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), Math.asin(0.99));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 0.99).add(0, 1, "A").build();
    assert.deepEqual(ASIN.SELF.run(null, one), Math.asin(0.99));
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ASIN.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((ASIN.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
