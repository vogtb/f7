import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { DivException } from "../../../../main/js/errors/DivException";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { RefException } from "../../../../main/js/errors/RefException";
import { ISERROR } from "../../../../main/js/formulas/info/ISERROR";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("ISERROR", function () {
  it("should work with numbers", function () {
    assert.deepEqual(ISERROR.SELF.run(null, 10), false);
  });

  it("should work with strings", function () {
    assert.deepEqual(ISERROR.SELF.run(null, "String"), false);
  });

  it("should work with booleans", function () {
    assert.deepEqual(ISERROR.SELF.run(null, true), false);
    assert.deepEqual(ISERROR.SELF.run(null, false), false);
  });

  it("should work with errors", function () {
    assert.deepEqual(ISERROR.SELF.run(null, new NAException()), true);
    assert.deepEqual(ISERROR.SELF.run(null, new DivException()), true);
    assert.deepEqual(ISERROR.SELF.run(null, new RefException()), true);
  });

  it("should work with blank/null/empty", function () {
    assert.deepEqual(ISERROR.SELF.run(null, null), false);
  });

  it("should work with lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ISERROR(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(new DivException());
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), true);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should work with grid", function () {
    assert.deepEqual(ISERROR.SELF.run(null, Grid.builder().add(0, 0, "Nope").build()), false);
    assert.deepEqual(ISERROR.SELF.run(null, Grid.builder().add(0, 0, 22000.0).build()), false);
    assert.deepEqual(ISERROR.SELF.run(null, Grid.builder().add(0, 0, true).build()), false);
    assert.deepEqual(
      ISERROR.SELF.run(null, Grid.builder().add(0, 0, new DivException()).build()),
      true
    );
  });

  it("should return error when arguments are not of the correct length", function () {
    assert.deepEqual((ISERROR.SELF.run(null) as F7Exception).name, F7ExceptionName.NA);
    assert.deepEqual((ISERROR.SELF.run(null, "A", "B") as F7Exception).name, F7ExceptionName.NA);
  });
});
