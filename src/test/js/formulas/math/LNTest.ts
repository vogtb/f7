import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { LN } from "../../../../main/js/formulas/math/LN";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("LN", function () {
  it("should work with numbers", function () {
    assert.deepEqual(LN.SELF.run(null, 128), Math.log(128));
    assert.deepEqual(LN.SELF.run(null, 1), Math.log(1));
    assert.deepEqual(LN.SELF.run(null, 2), Math.log(2));
    assert.deepEqual(LN.SELF.run(null, 3), Math.log(3));
    assert.deepEqual(LN.SELF.run(null, 0.1), Math.log(0.1));
  });

  it("should require parameter 1 to be greater than 0", function () {
    assert.deepEqual((LN.SELF.run(null, 0) as NAException).name, F7ExceptionName.NUM);
  });

  it("should work with strings", function () {
    assert.deepEqual(LN.SELF.run(null, "128"), Math.log(128));
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(LN.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new LN(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(128);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), Math.log(128));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 128).add(0, 1, "A").build();
    assert.deepEqual(LN.SELF.run(null, one), Math.log(128));
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((LN.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((LN.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
