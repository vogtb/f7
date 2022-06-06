import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { GTE } from "../../../../main/js/formulas/logic/GTE";
import { Grid } from "../../../../main/js/models/common/Grid";
import {
  CollateralLookupFunction,
  Computed,
  LookupFunction,
} from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("GTE", function () {
  it("should compare strings", function () {
    assert.deepEqual(GTE.SELF.run(null, "Hello", "Diff"), true);
    assert.deepEqual(GTE.SELF.run(null, "a", "a"), true);
    assert.deepEqual(GTE.SELF.run(null, "a", "aa"), false);
    assert.deepEqual(GTE.SELF.run(null, "aa", "a"), true);
    assert.deepEqual(GTE.SELF.run(null, "a", "A"), true);
    assert.deepEqual(GTE.SELF.run(null, "A", "a"), true);
    assert.deepEqual(GTE.SELF.run(null, "A", "A"), true);
    assert.deepEqual(GTE.SELF.run(null, "Aa", "A"), true);
    assert.deepEqual(GTE.SELF.run(null, "AA", "a"), true);
    assert.deepEqual(GTE.SELF.run(null, "aA", "a"), true);
    assert.deepEqual(GTE.SELF.run(null, "aA", "A"), true);
    assert.deepEqual(GTE.SELF.run(null, "押", "し"), true);
    assert.deepEqual(GTE.SELF.run(null, "し", "押"), false);
    assert.deepEqual(GTE.SELF.run(null, "String", 129321321.0), true);
    assert.deepEqual(GTE.SELF.run(null, 129321321.0, "String"), false);
  });

  it("should compare numbers", function () {
    assert.deepEqual(GTE.SELF.run(null, 1.0, 1.0), true);
    assert.deepEqual(GTE.SELF.run(null, 1.0, 0.0), true);
    assert.deepEqual(GTE.SELF.run(null, 0.0, 1.0), false);
  });

  it("should compare booleans", function () {
    assert.deepEqual(GTE.SELF.run(null, true, false), true);
    assert.deepEqual(GTE.SELF.run(null, false, true), false);
    assert.deepEqual(GTE.SELF.run(null, true, true), true);
    assert.deepEqual(GTE.SELF.run(null, false, false), true);
    assert.deepEqual(GTE.SELF.run(null, true, "String"), true);
    assert.deepEqual(GTE.SELF.run(null, "String", true), false);
  });

  it("should do pass-through errors", function () {
    assert.equal(
      (GTE.SELF.run(null, 4.4444, new ValueException()) as F7Exception).name,
      F7ExceptionName.VALUE
    );
  });

  it("should compare across types using type precedence", function () {
    assert.deepEqual(GTE.SELF.run(null, "a", 0.0), true);
    assert.deepEqual(GTE.SELF.run(null, 0.0, "a"), false);
    assert.deepEqual(GTE.SELF.run(null, true, 0.0), true);
    assert.deepEqual(GTE.SELF.run(null, false, 0.0), true);
    assert.deepEqual(GTE.SELF.run(null, 0.0, true), false);
    assert.deepEqual(GTE.SELF.run(null, 0.0, false), false);
    assert.deepEqual(GTE.SELF.run(null, true, "a"), true);
    assert.deepEqual(GTE.SELF.run(null, false, "a"), true);
    assert.deepEqual(GTE.SELF.run(null, "a", true), false);
    assert.deepEqual(GTE.SELF.run(null, "a", false), false);
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new GTE(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(4);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.G19_RANGE).returns(10);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE, CommonModels.G19_RANGE), false);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.G19_RANGE));
    assert.equal(collateralLookup.callCount, 2);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.from<Computed>([[44], ["A"]]);
    const two = Grid.from<Computed>([[44], ["B"]]);
    assert.deepEqual(GTE.SELF.run(null, one, two), true);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((GTE.SELF.run(null, "Too few") as NAException).name, F7ExceptionName.NA);
    assert.deepEqual(
      (GTE.SELF.run(null, "A", "B", "Too many") as NAException).name,
      F7ExceptionName.NA
    );
  });
});
