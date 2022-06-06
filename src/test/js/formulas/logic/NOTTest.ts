import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { NOT } from "../../../../main/js/formulas/logic/NOT";
import { Grid } from "../../../../main/js/models/common/Grid";
import {
  CollateralLookupFunction,
  LookupFunction,
  Primitive,
} from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("NOT", function () {
  it("should work with numbers", function () {
    assert.equal(NOT.SELF.run(null, 10), false);
    assert.equal(NOT.SELF.run(null, 0), true);
  });

  it("should work with strings", function () {
    assert.equal(NOT.SELF.run(null, "TRUE"), false);
    assert.equal(NOT.SELF.run(null, "fAlsE"), true);
  });

  it("should work with booleans", function () {
    assert.equal(NOT.SELF.run(null, true), false);
    assert.equal(NOT.SELF.run(null, false), true);
  });

  it("should work with null/blank/empty", function () {
    assert.equal(NOT.SELF.run(null, null), true);
  });

  it("should do pass-through errors", function () {
    assert.equal(
      (NOT.SELF.run(null, new ValueException()) as F7Exception).name,
      F7ExceptionName.VALUE
    );
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new NOT(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(true);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), false);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.from<Primitive>([[44], [true]]);
    assert.deepEqual(NOT.SELF.run(null, one), false);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((NOT.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((NOT.SELF.run(null, 10, 10, 10) as NAException).name, F7ExceptionName.NA);
  });
});
