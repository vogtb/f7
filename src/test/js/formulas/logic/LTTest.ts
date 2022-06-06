import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { LT } from "../../../../main/js/formulas/logic/LT";
import { Grid } from "../../../../main/js/models/common/Grid";
import {
  CollateralLookupFunction,
  LookupFunction,
  Primitive,
} from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("LT", function () {
  it("should compare strings", function () {
    assert.deepEqual(LT.SELF.run(null, "Hello", "Diff"), false);
    assert.deepEqual(LT.SELF.run(null, "a", "a"), false);
    assert.deepEqual(LT.SELF.run(null, "a", "aa"), true);
    assert.deepEqual(LT.SELF.run(null, "aa", "a"), false);
    assert.deepEqual(LT.SELF.run(null, "a", "A"), false);
    assert.deepEqual(LT.SELF.run(null, "A", "a"), false);
    assert.deepEqual(LT.SELF.run(null, "A", "A"), false);
    assert.deepEqual(LT.SELF.run(null, "Aa", "A"), false);
    assert.deepEqual(LT.SELF.run(null, "AA", "a"), false);
    assert.deepEqual(LT.SELF.run(null, "aA", "a"), false);
    assert.deepEqual(LT.SELF.run(null, "aA", "A"), false);
    assert.deepEqual(LT.SELF.run(null, "押", "し"), false);
    assert.deepEqual(LT.SELF.run(null, "し", "押"), true);
    assert.deepEqual(LT.SELF.run(null, "String", 129321321.0), false);
    assert.deepEqual(LT.SELF.run(null, 129321321.0, "String"), true);
  });

  it("should compare numbers", function () {
    assert.deepEqual(LT.SELF.run(null, 1.0, 1.0), false);
    assert.deepEqual(LT.SELF.run(null, 1.0, 0.0), false);
    assert.deepEqual(LT.SELF.run(null, 0.0, 1.0), true);
  });

  it("should compare booleans", function () {
    assert.deepEqual(LT.SELF.run(null, true, false), false);
    assert.deepEqual(LT.SELF.run(null, false, true), true);
    assert.deepEqual(LT.SELF.run(null, true, true), false);
    assert.deepEqual(LT.SELF.run(null, false, false), false);
    assert.deepEqual(LT.SELF.run(null, true, "String"), false);
    assert.deepEqual(LT.SELF.run(null, "String", true), true);
  });

  it("should do pass-through errors", function () {
    assert.equal(
      (LT.SELF.run(null, 4.4444, new ValueException()) as F7Exception).name,
      F7ExceptionName.VALUE
    );
  });

  it("should compare across types using type precedence", function () {
    assert.deepEqual(LT.SELF.run(null, "a", 0.0), false);
    assert.deepEqual(LT.SELF.run(null, 0.0, "a"), true);
    assert.deepEqual(LT.SELF.run(null, true, 0.0), false);
    assert.deepEqual(LT.SELF.run(null, false, 0.0), false);
    assert.deepEqual(LT.SELF.run(null, 0.0, true), true);
    assert.deepEqual(LT.SELF.run(null, 0.0, false), true);
    assert.deepEqual(LT.SELF.run(null, true, "a"), false);
    assert.deepEqual(LT.SELF.run(null, false, "a"), false);
    assert.deepEqual(LT.SELF.run(null, "a", true), true);
    assert.deepEqual(LT.SELF.run(null, "a", false), true);
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new LT(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(2);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.G19_RANGE).returns(4);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE, CommonModels.G19_RANGE), true);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.G19_RANGE));
    assert.equal(collateralLookup.callCount, 2);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.from<Primitive>([[4], ["A"]]);
    const two = Grid.from<Primitive>([[10], ["B"]]);
    assert.deepEqual(LT.SELF.run(null, one, two), true);
  });

  it("should return error when argument lenLThs are wrong", function () {
    assert.deepEqual((LT.SELF.run(null, "Too few") as NAException).name, F7ExceptionName.NA);
    assert.deepEqual(
      (LT.SELF.run(null, "A", "B", "Too many") as NAException).name,
      F7ExceptionName.NA
    );
  });
});
