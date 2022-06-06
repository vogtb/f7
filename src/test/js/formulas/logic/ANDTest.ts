import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { AND } from "../../../../main/js/formulas/logic/AND";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { Converters } from "../../../../main/js/utils/Converters";
import { CommonModels } from "../../testutils/CommonModels";

describe("AND", function () {
  it("should work with numbers", function () {
    assert.equal(AND.SELF.run(null, 10, 10), true);
    assert.equal(AND.SELF.run(null, 10, 0), false);
    assert.equal(AND.SELF.run(null, 1, 1, 1, 1, 1, 1), true);
  });

  it("should work with strings", function () {
    assert.equal(AND.SELF.run(null, 10, "TRUE", true), true);
    assert.equal(AND.SELF.run(null, 10, "true", true), true);
    assert.equal(AND.SELF.run(null, 10, "FALSE", true), false);
    assert.equal(AND.SELF.run(null, 10, "false", true), false);
  });

  it("should work with booleans", function () {
    assert.equal(AND.SELF.run(null, true, true, true), true);
    assert.equal(AND.SELF.run(null, true, true, false), false);
    assert.equal(AND.SELF.run(null, false, false, false), false);
  });

  it("should work with null/blank/empty", function () {
    assert.equal(
      Converters.castAsF7Exception(AND.SELF.run(null, null, null)).name,
      F7ExceptionName.VALUE
    );
    assert.equal(AND.SELF.run(null, null, null, true), true);
  });

  it("should do pass-through errors", function () {
    assert.equal(
      (AND.SELF.run(null, 4.4444, new ValueException()) as F7Exception).name,
      F7ExceptionName.VALUE
    );
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new AND(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    lookup.withArgs(CommonModels.M22_RANGE).returns(true);
    lookup.withArgs(CommonModels.G19_RANGE).returns(false);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE, CommonModels.G19_RANGE), false);
    assert.isTrue(lookup.calledWith(CommonModels.M22_RANGE));
    assert.isTrue(lookup.calledWith(CommonModels.G19_RANGE));
    assert.equal(lookup.callCount, 2);
    assert.isTrue(collateralLookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 44.0).add(0, 1, true).build();
    const two = Grid.builder().add(0, 0, 44.0).add(0, 1, true).build();
    const three = Grid.builder().add(0, 0, 44.0).add(0, 1, false).build();
    assert.deepEqual(AND.SELF.run(null, one, two), true);
    assert.deepEqual(AND.SELF.run(null, two, three), false);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((AND.SELF.run(null) as NAException).name, F7ExceptionName.NA);
  });
});
