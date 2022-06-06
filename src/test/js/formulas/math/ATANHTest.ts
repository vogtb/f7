import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ATANH } from "../../../../main/js/formulas/math/ATANH";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("ATANH", function () {
  it("should work with valid numbers", function () {
    assert.deepEqual(ATANH.SELF.run(null, 0.99), 2.6466524123622457);
    assert.deepEqual(ATANH.SELF.run(null, 0.489733), 0.5357090350574656);
    assert.deepEqual(ATANH.SELF.run(null, 0.0), 0.0);
    assert.deepEqual(ATANH.SELF.run(null, -0.66152156111), -0.7955143351612654);
    assert.deepEqual(ATANH.SELF.run(null, -0.88), -1.3757676565209744);
  });

  it("should return NUM error when param is out of bounds", function () {
    assert.deepEqual((ATANH.SELF.run(null, 1) as NAException).name, F7ExceptionName.NUM);
    assert.deepEqual((ATANH.SELF.run(null, -1) as NAException).name, F7ExceptionName.NUM);
    assert.deepEqual((ATANH.SELF.run(null, 44) as NAException).name, F7ExceptionName.NUM);
  });

  it("should work with strings", function () {
    assert.equal(ATANH.SELF.run(null, "0.99"), 2.6466524123622457);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(ATANH.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ATANH(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(0.99);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 2.6466524123622457);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 0.99).add(0, 1, "A").build();
    assert.deepEqual(ATANH.SELF.run(null, one), 2.6466524123622457);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ATANH.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((ATANH.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
