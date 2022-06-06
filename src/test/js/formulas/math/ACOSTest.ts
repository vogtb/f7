import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ACOS } from "../../../../main/js/formulas/math/ACOS";
import { Grid } from "../../../../main/js/models/common/Grid";
import {
  CollateralLookupFunction,
  LookupFunction,
  Primitive,
} from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("ACOS", function () {
  it("should work with numbers", function () {
    assert.equal(ACOS.SELF.run(null, 0), Math.acos(0));
    assert.equal(ACOS.SELF.run(null, 0.99), Math.acos(0.99));
    assert.equal(ACOS.SELF.run(null, 0.55), Math.acos(0.55));
    assert.equal(ACOS.SELF.run(null, -0.55), Math.acos(-0.55));
  });

  it("should work with strings", function () {
    assert.equal(ACOS.SELF.run(null, "0.99"), Math.acos(0.99));
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(ACOS.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ACOS(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(0.99);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), Math.acos(0.99));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.from<Primitive>([[0.99], ["A"]]);
    assert.deepEqual(ACOS.SELF.run(null, one), Math.acos(0.99));
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ACOS.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((ACOS.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
