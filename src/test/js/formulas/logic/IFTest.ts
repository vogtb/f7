import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { DivException } from "../../../../main/js/errors/DivException";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { NumException } from "../../../../main/js/errors/NumException";
import { IF } from "../../../../main/js/formulas/logic/IF";
import { Grid } from "../../../../main/js/models/common/Grid";
import {
  CollateralLookupFunction,
  LookupFunction,
  Primitive,
} from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("IF", function () {
  it("should work with simple comparisons", function () {
    assert.deepEqual(IF.SELF.run(null, true, true, false), true);
    assert.deepEqual(IF.SELF.run(null, false, true, false), false);
    assert.deepEqual(IF.SELF.run(null, 10.0, "A", "B"), "A");
    assert.deepEqual(IF.SELF.run(null, -1716.1, "A", "B"), "A");
    assert.deepEqual(IF.SELF.run(null, "TRUE", "A", "B"), "A");
    assert.deepEqual(IF.SELF.run(null, "", "A", "B"), "B");
  });

  it("should work with errors as two return arguments", function () {
    assert.deepEqual(IF.SELF.run(null, true, 10.1, new DivException()), 10.1);
    assert.deepEqual(
      (IF.SELF.run(null, true, new NumException(), new DivException()) as F7Exception).name,
      F7ExceptionName.NUM
    );
    assert.deepEqual(IF.SELF.run(null, false, new DivException(), 10.11), 10.11);
    assert.deepEqual(
      (IF.SELF.run(null, true, new DivException(), 10.11) as F7Exception).name,
      F7ExceptionName.DIV
    );
  });

  it("should use lookup for first value if true", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new IF(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, true).returns(true);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(1000);
    assert.deepEqual(
      F.run(CommonModels.A1, true, CommonModels.M22_RANGE, CommonModels.G19_RANGE),
      1000
    );
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, true));
    assert.equal(collateralLookup.callCount, 2);
    assert.isTrue(lookup.notCalled);
  });

  it("should use lookup for second value if false", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new IF(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, false).returns(false);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.G19_RANGE).returns(1000);
    assert.deepEqual(
      F.run(CommonModels.A1, false, CommonModels.M22_RANGE, CommonModels.G19_RANGE),
      1000
    );
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.G19_RANGE));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, false));
    assert.equal(collateralLookup.callCount, 2);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.from<Primitive>([[44], ["A"]]);
    const two = Grid.from<Primitive>([[44], ["B"]]);
    assert.deepEqual(IF.SELF.run(null, true, one, two), 44);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((IF.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
    assert.deepEqual(
      (IF.SELF.run(null, "A", "B", "C", "D") as NAException).name,
      F7ExceptionName.NA
    );
  });
});
