import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ASINH } from "../../../../main/js/formulas/math/ASINH";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("ASINH", function () {
  it("should work with valid numbers", function () {
    assert.deepEqual(ASINH.SELF.run(null, 10.0), 2.99822295029797);
    assert.deepEqual(ASINH.SELF.run(null, 128731.2), 12.458628969021666);
    assert.deepEqual(ASINH.SELF.run(null, 11.11), 3.1030120634231873);
    assert.deepEqual(ASINH.SELF.run(null, 0.0), 0.0);
    assert.deepEqual(ASINH.SELF.run(null, 88281.0), 12.081427368492559);
    assert.deepEqual(ASINH.SELF.run(null, 2.0), 1.4436354751788103);
    assert.deepEqual(ASINH.SELF.run(null, 4.0), 2.0947125472611012);
    assert.deepEqual(ASINH.SELF.run(null, -4.0), -2.094712547261101);
    assert.deepEqual(ASINH.SELF.run(null, -10124.0), -9.91581129653516);
  });

  it("should work with strings", function () {
    assert.equal(ASINH.SELF.run(null, "10"), 2.99822295029797);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(ASINH.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ASINH(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(10);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 2.99822295029797);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 10).add(0, 1, "A").build();
    assert.deepEqual(ASINH.SELF.run(null, one), 2.99822295029797);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ASINH.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((ASINH.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
