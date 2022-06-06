import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ADD } from "../../../../main/js/formulas/math/ADD";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("ADD", function () {
  it("should do normal operations", function () {
    assert.equal(ADD.SELF.run(null, 10, 10), 20);
    assert.equal(ADD.SELF.run(null, 10, 2), 12);
    assert.equal(ADD.SELF.run(null, 0, 1628736813.2), 1628736813.2);
    assert.equal(ADD.SELF.run(null, 218637221.22, 2876.111), 218640097.331);
  });

  it("should do string conversion", function () {
    assert.equal(ADD.SELF.run(null, "10", "10"), 20);
    assert.equal(ADD.SELF.run(null, "10", "2"), 12);
    assert.equal(ADD.SELF.run(null, "10", "3"), 13);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(ADD.SELF.run(null, 10, new ValueException()), new ValueException());
    assert.deepEqual(ADD.SELF.run(null, new ValueException(), 10), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ADD(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(2);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.G19_RANGE).returns(4);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE, CommonModels.G19_RANGE), 6);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.G19_RANGE));
    assert.equal(collateralLookup.callCount, 2);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 4).add(0, 1, "A").build();
    const two = Grid.builder().add(0, 0, 10).add(0, 1, "B").build();
    assert.deepEqual(ADD.SELF.run(null, one, two), 14);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ADD.SELF.run(null, "Too few") as NAException).name, F7ExceptionName.NA);
    assert.deepEqual(
      (ADD.SELF.run(null, "A", "B", "Too many") as NAException).name,
      F7ExceptionName.NA
    );
  });
});
