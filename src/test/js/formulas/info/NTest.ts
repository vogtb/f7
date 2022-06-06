import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { DivException } from "../../../../main/js/errors/DivException";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { N } from "../../../../main/js/formulas/info/N";
import { Grid } from "../../../../main/js/models/common/Grid";
import {
  CollateralLookupFunction,
  Computed,
  LookupFunction,
} from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("N", function () {
  it("should work with numbers", function () {
    assert.deepEqual(N.SELF.run(null, 10), 10);
  });

  it("should work with strings", function () {
    assert.deepEqual(N.SELF.run(null, "String"), 0);
  });

  it("should work with booleans", function () {
    assert.deepEqual(N.SELF.run(null, true), 1);
    assert.deepEqual(N.SELF.run(null, false), 0);
  });

  it("should work with errors", function () {
    assert.deepEqual((N.SELF.run(null, new NAException()) as F7Exception).name, F7ExceptionName.NA);
    assert.deepEqual(
      (N.SELF.run(null, new DivException()) as F7Exception).name,
      F7ExceptionName.DIV
    );
  });

  it("should work with blank/null/empty", function () {
    assert.deepEqual(N.SELF.run(null, null), 0);
  });

  it("should work with lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new N(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(10);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 10);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should work with grid", function () {
    assert.deepEqual(N.SELF.run(null, Grid.from<Computed>([["Nope"]])), 0);
    assert.deepEqual(N.SELF.run(null, Grid.from<Computed>([[22]])), 22);
    assert.deepEqual(N.SELF.run(null, Grid.from<Computed>([[true]])), 1);
    assert.deepEqual(
      (N.SELF.run(null, Grid.from<Computed>([[new DivException()]])) as F7Exception).name,
      F7ExceptionName.DIV
    );
  });

  it("should return error when arguments are not of the correct length", function () {
    assert.deepEqual((N.SELF.run(null) as F7Exception).name, F7ExceptionName.NA);
    assert.deepEqual((N.SELF.run(null, "A", "B") as F7Exception).name, F7ExceptionName.NA);
  });
});
