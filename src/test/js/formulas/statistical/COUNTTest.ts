import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { RefException } from "../../../../main/js/errors/RefException";
import { COUNT } from "../../../../main/js/formulas/statistical/COUNT";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("COUNT", function () {
  const GRID = Grid.builder()
    .add(0, 0, 22.1)
    .add(0, 1, 324.3)
    .add(0, 2, 22.2312223131232)
    .add(0, 3, 442309.4)
    .add(0, 4, 131289731)
    .build();

  it("should do normal operations", function () {
    assert.deepEqual(COUNT.SELF.run(null, 1, 2, 3, 4, 5, 6, 7, 8), 8);
    assert.deepEqual(COUNT.SELF.run(null, 0), 1);
    assert.deepEqual(COUNT.SELF.run(null, 2984723.99382), 1);
    assert.deepEqual(COUNT.SELF.run(null, 1, 2), 2);
  });

  it("should count only numerics inside and outside of grids", function () {
    assert.deepEqual(
      COUNT.SELF.run(
        null,
        Grid.builder()
          .add(0, 0, 22.1)
          .add(0, 1, 324.3)
          .add(0, 2, "No count.")
          .add(0, 3, 442309.4)
          .add(0, 4, 131289731)
          .build(),
        "No Count",
        1,
        2,
        true
      ),
      7
    );
    assert.deepEqual(
      COUNT.SELF.run(null, Grid.builder().add(0, 0, 22.1).add(0, 1, "No.").build(), "No.", true),
      2
    );
  });

  it("should count booleans", function () {
    assert.deepEqual(COUNT.SELF.run(null, true, false, true, false), 4);
  });

  it("should ignore errors inside of grids", function () {
    assert.deepEqual(COUNT.SELF.run(null, Grid.builder().add(0, 0, new RefException()).build()), 0);
  });

  it("should ignore errors outside of grids", function () {
    assert.deepEqual(
      COUNT.SELF.run(null, Grid.builder().add(0, 0, 1).build(), new RefException(), 2),
      2
    );
  });

  it("should use lookup function for range queries", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new COUNT(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    lookup.withArgs(CommonModels.M22_RANGE).returns(GRID);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 5);
    assert.isTrue(lookup.calledWith(CommonModels.M22_RANGE));
    assert.equal(lookup.callCount, 1);
    assert.isTrue(collateralLookup.notCalled);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((COUNT.SELF.run(null) as NAException).name, F7ExceptionName.NA);
  });
});
