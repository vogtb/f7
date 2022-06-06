import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { RefException } from "../../../../main/js/errors/RefException";
import { COUNTBLANK } from "../../../../main/js/formulas/statistical/COUNTBLANK";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("COUNTBLANK", function () {
  const GRID = Grid.builder()
    .add(0, 0, 22.1)
    .add(0, 1, 324.3)
    .add(0, 3, 442309.4)
    .add(0, 4, 131289731)
    .build();

  it("should not count numbers", function () {
    assert.deepEqual(COUNTBLANK.SELF.run(null, 1, 2, 3, 4, 5, 6, 7, 8), 0);
    assert.deepEqual(COUNTBLANK.SELF.run(null, 0), 0);
    assert.deepEqual(COUNTBLANK.SELF.run(null, 2984723.99382), 0);
    assert.deepEqual(COUNTBLANK.SELF.run(null, 1, 2), 0);
  });

  it("should count all values inside and outside of grids", function () {
    assert.deepEqual(
      COUNTBLANK.SELF.run(
        null,
        Grid.builder()
          .add(0, 0, 1)
          .add(0, 1, 1)
          .add(0, 2, "String")
          .add(0, 3, 1)
          .add(0, 4, 1)
          // gap of 3 nulls
          .add(0, 8, 1)
          .build(),
        "String",
        1,
        2,
        true
      ),
      3
    );
    assert.deepEqual(
      COUNTBLANK.SELF.run(null, Grid.builder().add(0, 0, 1).add(3, 3, "No.").build(), "No.", true),
      14
    );
  });

  it("should not count booleans", function () {
    assert.deepEqual(COUNTBLANK.SELF.run(null, true, false, true, false), 0);
  });

  it("should ignore errors inside and outside of grids", function () {
    assert.deepEqual(
      COUNTBLANK.SELF.run(null, Grid.builder().add(0, 0, new RefException()).build()),
      0
    );
    assert.deepEqual(
      COUNTBLANK.SELF.run(null, Grid.builder().add(0, 0, 1).build(), new RefException(), 2),
      0
    );
  });

  it("should use lookup function for range queries", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new COUNTBLANK(
      lookup as LookupFunction,
      collateralLookup as CollateralLookupFunction
    );
    // Grid with value at A5, so A1:A4 should be blank.
    lookup.withArgs(CommonModels.B1_B4_RANGE).returns(Grid.builder().add(0, 4, 1).build());
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.B1_B4_RANGE), 4);
    assert.isTrue(lookup.calledWith(CommonModels.B1_B4_RANGE));
    assert.equal(lookup.callCount, 1);
    assert.isTrue(collateralLookup.notCalled);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((COUNTBLANK.SELF.run(null) as NAException).name, F7ExceptionName.NA);
  });
});
