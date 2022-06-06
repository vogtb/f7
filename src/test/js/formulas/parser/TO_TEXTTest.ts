import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { DivException } from "../../../../main/js/errors/DivException";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { TO_TEXT } from "../../../../main/js/formulas/parser/TO_TEXT";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("TO_TEXT", function () {
  it("should work with numbers", function () {
    assert.deepEqual(TO_TEXT.SELF.run(null, 10), "10");
  });

  it("should work with strings", function () {
    assert.deepEqual(TO_TEXT.SELF.run(null, "String"), "String");
  });

  it("should work with booleans", function () {
    assert.deepEqual(TO_TEXT.SELF.run(null, true), "TRUE");
    assert.deepEqual(TO_TEXT.SELF.run(null, false), "FALSE");
  });

  it("should work with errors", function () {
    assert.deepEqual(
      (TO_TEXT.SELF.run(null, new NAException()) as F7Exception).name,
      F7ExceptionName.NA
    );
    assert.deepEqual(
      (TO_TEXT.SELF.run(null, new DivException()) as F7Exception).name,
      F7ExceptionName.DIV
    );
  });

  it("should work with blank/null/empty", function () {
    assert.deepEqual(TO_TEXT.SELF.run(null, null), "");
  });

  it("should work with lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new TO_TEXT(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(9.14101);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), "9.14101");
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should work with grid", function () {
    assert.deepEqual(TO_TEXT.SELF.run(null, Grid.builder().add(0, 0, "String").build()), "String");
    assert.deepEqual(TO_TEXT.SELF.run(null, Grid.builder().add(0, 0, 22).build()), "22");
    assert.deepEqual(TO_TEXT.SELF.run(null, Grid.builder().add(0, 0, true).build()), "TRUE");
    assert.deepEqual(
      (TO_TEXT.SELF.run(null, Grid.builder().add(0, 0, new DivException()).build()) as F7Exception)
        .name,
      F7ExceptionName.DIV
    );
  });

  it("should return error when arguments are not of the correct length", function () {
    assert.deepEqual((TO_TEXT.SELF.run(null) as F7Exception).name, F7ExceptionName.NA);
    assert.deepEqual((TO_TEXT.SELF.run(null, "A", "B") as F7Exception).name, F7ExceptionName.NA);
  });
});
