import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { RefException } from "../../../../main/js/errors/RefException";
import { MINA } from "../../../../main/js/formulas/statistical/MINA";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";
import { assertF7ExceptionByName } from "../../testutils/TestUtils";

describe("MINA", function () {
  const GRID = Grid.builder()
    .add(0, 0, 22.1)
    .add(0, 1, 324.3)
    .add(0, 2, 22.2312223131232)
    .add(0, 3, 442309.4)
    .add(0, 4, 131289731)
    .build();
  const GRID_WITH_TEXT_VALUE = Grid.builder()
    .add(0, 0, 22.1)
    .add(0, 1, "To zero.")
    .add(0, 2, 10)
    .build();
  const GRID_WITH_BLANK_VALUES = Grid.builder()
    .add(0, 0, 2)
    .add(0, 1, 3)
    .add(0, 2, 2)
    // Leaving gap in range to simulate null/blank values.
    .add(0, 100, 4)
    .build();

  it("should do min value for numbers", function () {
    assert.deepEqual(MINA.SELF.run(null, 1, 2, 3, 4, 5, 6, 7, 8), 1);
    assert.deepEqual(MINA.SELF.run(null, 1, 2, 3, 4, 5, 0, 7, 8), 0);
    assert.deepEqual(MINA.SELF.run(null, 0), 0);
    assert.deepEqual(MINA.SELF.run(null, 2984723.99382), 2984723.99382);
    assert.deepEqual(MINA.SELF.run(null, 1, 2, -1000), -1000);
  });

  it("should return VALUE error when encountering non-coercable text values outside ranges", function () {
    assertF7ExceptionByName(MINA.SELF.run(null, 1, 2, "Bad"), F7ExceptionName.VALUE);
    assert.deepEqual(MINA.SELF.run(null, 0, "-10"), -10);
  });

  it("should convert to text values to zero inside of grids", function () {
    assert.deepEqual(MINA.SELF.run(null, GRID_WITH_TEXT_VALUE), 0);
  });

  it("should return 0 when the resulting filter range is all null", function () {
    assert.deepEqual(MINA.SELF.run(null, Grid.builder().build()), 0);
  });

  it("should consider booleans", function () {
    assert.deepEqual(MINA.SELF.run(null, true, false, 10), 0);
  });

  it("should throw errors inside and outside of grids", function () {
    assertF7ExceptionByName(MINA.SELF.run(null, 1, 2, new RefException()), F7ExceptionName.REF);
    assertF7ExceptionByName(
      MINA.SELF.run(null, Grid.builder().add(0, 0, new RefException()).build()),
      F7ExceptionName.REF
    );
  });

  it("should ignore blank cells (null values)", function () {
    assert.deepEqual(MINA.SELF.run(null, GRID_WITH_BLANK_VALUES), 2);
  });

  it("should use lookup function for range queries", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new MINA(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    lookup.withArgs(CommonModels.M22_RANGE).returns(GRID);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 22.1);
    assert.isTrue(lookup.calledWith(CommonModels.M22_RANGE));
    assert.equal(lookup.callCount, 1);
    assert.isTrue(collateralLookup.notCalled);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((MINA.SELF.run(null) as NAException).name, F7ExceptionName.NA);
  });
});
