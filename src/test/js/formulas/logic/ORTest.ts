import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { OR } from "../../../../main/js/formulas/logic/OR";
import { Grid } from "../../../../main/js/models/common/Grid";
import {
  CollateralLookupFunction,
  Computed,
  LookupFunction,
} from "../../../../main/js/models/common/Types";
import { Converters } from "../../../../main/js/utils/Converters";
import { CommonModels } from "../../testutils/CommonModels";

describe("OR", function () {
  it("should work with numbers", function () {
    assert.equal(OR.SELF.run(null, 10, 10), true);
    assert.equal(OR.SELF.run(null, 10, 0), true);
    assert.equal(OR.SELF.run(null, 1, 1, 1, 1, 1, 1, 0), true);
    assert.equal(OR.SELF.run(null, 0, 0, 0, 1, 0), true);
    assert.equal(OR.SELF.run(null, 0, 0, 0, 0, 0), false);
  });

  it("should work with strings", function () {
    assert.equal(OR.SELF.run(null, "TRUE", true), true);
    assert.equal(OR.SELF.run(null, "true", true), true);
    assert.equal(OR.SELF.run(null, "FALSE", true), true);
    assert.equal(OR.SELF.run(null, "false", false), false);
  });

  it("should work with booleans", function () {
    assert.equal(OR.SELF.run(null, true, true, true), true);
    assert.equal(OR.SELF.run(null, true, true, false), true);
    assert.equal(OR.SELF.run(null, false, false, false), false);
  });

  it("should work with null/blank/empty", function () {
    assert.equal(
      Converters.castAsF7Exception(OR.SELF.run(null, null, null)).name,
      F7ExceptionName.VALUE
    );
    assert.equal(OR.SELF.run(null, null, null, false), false);
    assert.equal(OR.SELF.run(null, null, null, true), true);
  });

  it("should do pass-through errors", function () {
    assert.equal(
      (OR.SELF.run(null, 4.4444, new ValueException()) as F7Exception).name,
      F7ExceptionName.VALUE
    );
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new OR(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    lookup.withArgs(CommonModels.M22_RANGE).returns(true);
    lookup.withArgs(CommonModels.G19_RANGE).returns(false);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE, CommonModels.G19_RANGE), true);
    assert.isTrue(lookup.calledWith(CommonModels.M22_RANGE));
    assert.isTrue(lookup.calledWith(CommonModels.G19_RANGE));
    assert.equal(lookup.callCount, 2);
    assert.isTrue(collateralLookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.from<Computed>([[44], [true]]);
    const two = Grid.from<Computed>([[44], [true]]);
    const three = Grid.from<Computed>([[false], [false]]);
    assert.deepEqual(OR.SELF.run(null, one, two), true);
    assert.deepEqual(OR.SELF.run(null, two, three), true);
    assert.deepEqual(OR.SELF.run(null, three), false);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((OR.SELF.run(null) as NAException).name, F7ExceptionName.NA);
  });
});
