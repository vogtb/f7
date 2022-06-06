import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { DivException } from "../../../../main/js/errors/DivException";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { IFERROR } from "../../../../main/js/formulas/logic/IFERROR";
import { Grid } from "../../../../main/js/models/common/Grid";
import {
  CollateralLookupFunction,
  LookupFunction,
  Primitive,
} from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("IFERROR", function () {
  it("should work with plain types", function () {
    assert.deepEqual(IFERROR.SELF.run(null, true, false), true);
    assert.deepEqual(IFERROR.SELF.run(null, false, true), false);
    assert.deepEqual(IFERROR.SELF.run(null, 10, "A"), 10);
    assert.deepEqual(IFERROR.SELF.run(null, "TRUE", "A"), "TRUE");
    assert.deepEqual(IFERROR.SELF.run(null, "", "A"), "");
  });

  it("should work with error", function () {
    assert.deepEqual(IFERROR.SELF.run(null, new DivException(), 10.1), 10.1);
  });

  it("should use lookup if error", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new IFERROR(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, new DivException()).returns(new DivException());
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(1000);
    assert.deepEqual(F.run(CommonModels.A1, new DivException(), CommonModels.M22_RANGE), 1000);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, new DivException()));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 2);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.from<Primitive>([[44], ["A"]]);
    assert.deepEqual(IFERROR.SELF.run(null, new DivException(), one), one);
    assert.deepEqual(IFERROR.SELF.run(null, one, false), one);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((IFERROR.SELF.run(null, "A") as NAException).name, F7ExceptionName.NA);
    assert.deepEqual(
      (IFERROR.SELF.run(null, "A", "B", "C", "D") as NAException).name,
      F7ExceptionName.NA
    );
  });
});
