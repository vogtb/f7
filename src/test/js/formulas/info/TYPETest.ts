import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { DivException } from "../../../../main/js/errors/DivException";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { TYPE } from "../../../../main/js/formulas/info/TYPE";
import { Grid } from "../../../../main/js/models/common/Grid";
import {
  CollateralLookupFunction,
  Computed,
  LookupFunction,
  Primitive,
} from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("TYPE", function () {
  it("should work with numbers", function () {
    assert.deepEqual(TYPE.SELF.run(null, 10), 1);
  });

  it("should work with strings", function () {
    assert.deepEqual(TYPE.SELF.run(null, "String"), 2);
  });

  it("should work with booleans", function () {
    assert.deepEqual(TYPE.SELF.run(null, true), 4);
    assert.deepEqual(TYPE.SELF.run(null, false), 4);
  });

  it("should work with errors", function () {
    assert.deepEqual(TYPE.SELF.run(null, new NAException()), 16);
    assert.deepEqual(TYPE.SELF.run(null, new DivException()), 16);
  });

  it("should work with blank/null/empty", function () {
    assert.deepEqual(TYPE.SELF.run(null, null), 1);
  });

  it("should work with lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new TYPE(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(10);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 1);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should work with grid", function () {
    assert.deepEqual(TYPE.SELF.run(null, Grid.from<Primitive>([["Nope"]])), 2);
    assert.deepEqual(TYPE.SELF.run(null, Grid.from<Primitive>([[22]])), 1);
    assert.deepEqual(TYPE.SELF.run(null, Grid.from<Primitive>([[true]])), 4);
    assert.deepEqual(TYPE.SELF.run(null, Grid.from<Computed>([[new DivException()]])), 16);
    const multiGrid = Grid.from<Primitive>([[true], ["Other"]]);
    assert.deepEqual(TYPE.SELF.run(null, multiGrid), 64);
  });

  it("should return error when arguments are not of the correct length", function () {
    assert.deepEqual((TYPE.SELF.run(null) as F7Exception).name, F7ExceptionName.NA);
    assert.deepEqual((TYPE.SELF.run(null, "A", "B") as F7Exception).name, F7ExceptionName.NA);
  });
});
