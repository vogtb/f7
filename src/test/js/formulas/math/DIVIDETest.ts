import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { DIVIDE } from "../../../../main/js/formulas/math/DIVIDE";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("DIVIDE", function () {
  it("should do normal operations", function () {
    assert.deepEqual(DIVIDE.SELF.run(null, 10, 10), 1);
    assert.deepEqual(DIVIDE.SELF.run(null, 10, 2), 5);
    assert.deepEqual(DIVIDE.SELF.run(null, 10, 3), 3.3333333333333335);
    assert.deepEqual(DIVIDE.SELF.run(null, 0, 1628736813.2), 0);
    assert.deepEqual(DIVIDE.SELF.run(null, 218637221.22, 2876.111), 76018.35298429025);
  });

  it("should do string conversion", function () {
    assert.deepEqual(DIVIDE.SELF.run(null, "10", "10"), 1);
    assert.deepEqual(DIVIDE.SELF.run(null, "10", "2"), 5);
    assert.deepEqual(DIVIDE.SELF.run(null, "10", "3"), 3.3333333333333335);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(
      (DIVIDE.SELF.run(null, 10, new ValueException()) as F7Exception).name,
      F7ExceptionName.VALUE
    );
    assert.deepEqual(
      (DIVIDE.SELF.run(null, new ValueException(), 10) as F7Exception).name,
      F7ExceptionName.VALUE
    );
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new DIVIDE(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(6);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.G19_RANGE).returns(2);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE, CommonModels.G19_RANGE), 3);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.G19_RANGE));
    assert.equal(collateralLookup.callCount, 2);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 6).add(0, 1, "A").build();
    const two = Grid.builder().add(0, 0, 3).add(0, 1, "B").build();
    assert.deepEqual(DIVIDE.SELF.run(null, one, two), 2);
  });

  it("should throw error when dividing by zero", function () {
    assert.deepEqual((DIVIDE.SELF.run(null, 328467, 0) as F7Exception).name, F7ExceptionName.DIV);
    assert.deepEqual((DIVIDE.SELF.run(null, 0, 0) as F7Exception).name, F7ExceptionName.DIV);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((DIVIDE.SELF.run(null, "Too few") as NAException).name, F7ExceptionName.NA);
    assert.deepEqual(
      (DIVIDE.SELF.run(null, "A", "B", "Too many") as NAException).name,
      F7ExceptionName.NA
    );
  });
});
