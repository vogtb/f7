import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { GT } from "../../../../main/js/formulas/logic/GT";
import { Grid } from "../../../../main/js/models/common/Grid";
import {
  CollateralLookupFunction,
  LookupFunction,
  Primitive,
} from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("GT", function () {
  it("should compare strings", function () {
    assert.deepEqual(GT.SELF.run(null, "Hello", "Diff"), true);
    assert.deepEqual(GT.SELF.run(null, "a", "a"), false);
    assert.deepEqual(GT.SELF.run(null, "a", "aa"), false);
    assert.deepEqual(GT.SELF.run(null, "aa", "a"), true);
    assert.deepEqual(GT.SELF.run(null, "a", "A"), false);
    assert.deepEqual(GT.SELF.run(null, "A", "a"), false);
    assert.deepEqual(GT.SELF.run(null, "A", "A"), false);
    assert.deepEqual(GT.SELF.run(null, "Aa", "A"), true);
    assert.deepEqual(GT.SELF.run(null, "AA", "a"), true);
    assert.deepEqual(GT.SELF.run(null, "aA", "a"), true);
    assert.deepEqual(GT.SELF.run(null, "aA", "A"), true);
    assert.deepEqual(GT.SELF.run(null, "押", "し"), true);
    assert.deepEqual(GT.SELF.run(null, "し", "押"), false);
    assert.deepEqual(GT.SELF.run(null, "String", 129321321.0), true);
    assert.deepEqual(GT.SELF.run(null, 129321321.0, "String"), false);
  });

  it("should compare numbers", function () {
    assert.deepEqual(GT.SELF.run(null, 1.0, 1.0), false);
    assert.deepEqual(GT.SELF.run(null, 1.0, 0.0), true);
    assert.deepEqual(GT.SELF.run(null, 0.0, 1.0), false);
  });

  it("should compare booleans", function () {
    assert.deepEqual(GT.SELF.run(null, true, false), true);
    assert.deepEqual(GT.SELF.run(null, false, true), false);
    assert.deepEqual(GT.SELF.run(null, true, true), false);
    assert.deepEqual(GT.SELF.run(null, false, false), false);
    assert.deepEqual(GT.SELF.run(null, true, "String"), true);
    assert.deepEqual(GT.SELF.run(null, "String", true), false);
  });

  it("should do pass-through errors", function () {
    assert.equal(
      (GT.SELF.run(null, 4.4444, new ValueException()) as F7Exception).name,
      F7ExceptionName.VALUE
    );
  });

  it("should compare across types using type precedence", function () {
    assert.deepEqual(GT.SELF.run(null, "a", 0.0), true);
    assert.deepEqual(GT.SELF.run(null, 0.0, "a"), false);
    assert.deepEqual(GT.SELF.run(null, true, 0.0), true);
    assert.deepEqual(GT.SELF.run(null, false, 0.0), true);
    assert.deepEqual(GT.SELF.run(null, 0.0, true), false);
    assert.deepEqual(GT.SELF.run(null, 0.0, false), false);
    assert.deepEqual(GT.SELF.run(null, true, "a"), true);
    assert.deepEqual(GT.SELF.run(null, false, "a"), true);
    assert.deepEqual(GT.SELF.run(null, "a", true), false);
    assert.deepEqual(GT.SELF.run(null, "a", false), false);
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new GT(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(10);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.G19_RANGE).returns(2);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE, CommonModels.G19_RANGE), true);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.G19_RANGE));
    assert.equal(collateralLookup.callCount, 2);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.from<Primitive>([[44], ["A"]]);
    const two = Grid.from<Primitive>([[4], ["A"]]);
    assert.deepEqual(GT.SELF.run(null, one, two), true);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((GT.SELF.run(null, "Too few") as NAException).name, F7ExceptionName.NA);
    assert.deepEqual(
      (GT.SELF.run(null, "A", "B", "Too many") as NAException).name,
      F7ExceptionName.NA
    );
  });
});
