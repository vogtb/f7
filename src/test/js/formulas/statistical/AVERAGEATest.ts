import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { RefException } from "../../../../main/js/errors/RefException";
import { AVERAGEA } from "../../../../main/js/formulas/statistical/AVERAGEA";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { Converters } from "../../../../main/js/utils/Converters";
import { CommonModels } from "../../testutils/CommonModels";

describe("AVERAGEA", function () {
  const GRID = Grid.builder()
    .add(0, 0, 22.1)
    .add(0, 1, 324.3)
    .add(0, 2, 22.2312223131232)
    .add(0, 3, 442309.4)
    .add(0, 4, 131289731)
    .build();
  const GRID_CONTAINING_STRINGS = Grid.builder()
    .add(0, 0, 1)
    .add(0, 1, 2)
    .add(0, 2, 3)
    .add(0, 3, 4)
    .add(0, 4, 5)
    .add(0, 5, 6)
    .add(0, 6, "6.0")
    .add(0, 7, "To Zero Like Above")
    .build();
  const GRID_CONTAINING_ZEROS = Grid.builder()
    .add(0, 0, 1)
    .add(0, 1, 2)
    .add(0, 2, 3)
    .add(0, 3, 4)
    .add(0, 4, 5)
    .add(0, 5, 6)
    .add(0, 6, 0)
    .add(0, 7, 0)
    .build();

  it("should do normal operations", function () {
    assert.deepEqual(AVERAGEA.SELF.run(null, 1, 2, 3, 4, 5, 6, 7, 8), 4.5);
    assert.deepEqual(AVERAGEA.SELF.run(null, 1, 2, 3, 4, 5, 6, 0, 0), 2.625);
    assert.deepEqual(AVERAGEA.SELF.run(null, 0), 0);
    assert.deepEqual(AVERAGEA.SELF.run(null, 2984723.99382), 2984723.99382);
    assert.deepEqual(AVERAGEA.SELF.run(null, 2984723e3), 2984723e3);
  });

  it("should not filter values when they are plain parameters", function () {
    assert.deepEqual(
      Converters.castAsF7Exception(AVERAGEA.SELF.run(null, 1, 2, 3, 4, 5, 6, "6.o", "ToZero")).name,
      F7ExceptionName.VALUE
    );
  });

  it("should filter out zeros when they are plain parameters", function () {
    assert.deepEqual(AVERAGEA.SELF.run(null, 1, 2, 3, 4, 5, 6, 0, 0), 2.625);
    assert.deepEqual(AVERAGEA.SELF.run(null, 1, 2, 3, 4, 5, 6), 3.5);
  });

  it("should filter values when they are inside a range", function () {
    assert.deepEqual(AVERAGEA.SELF.run(null, GRID_CONTAINING_STRINGS), 2.625);
    assert.deepEqual(AVERAGEA.SELF.run(null, GRID_CONTAINING_ZEROS), 2.625);
  });

  it("should do string conversion outside of grid", function () {
    assert.equal(AVERAGEA.SELF.run(null, "10", "10"), 10);
    assert.equal(AVERAGEA.SELF.run(null, "10", "2"), 6);
    assert.equal(AVERAGEA.SELF.run(null, "10", "3"), 6.5);
    assert.deepEqual(AVERAGEA.SELF.run(null, "1", "2", "3", "4", "5", "6", "7", "8"), 4.5);
    assert.deepEqual(AVERAGEA.SELF.run(null, true, false, true, false, true), 0.6);
    assert.deepEqual(AVERAGEA.SELF.run(null, GRID), 1.3173240903122231e8 / 5);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(
      (AVERAGEA.SELF.run(null, 1, new RefException()) as F7Exception).name,
      F7ExceptionName.REF
    );
    assert.deepEqual(
      (
        AVERAGEA.SELF.run(
          null,
          Grid.builder()
            .add(0, 0, 22.1)
            .add(0, 1, 324.3)
            .add(0, 2, new RefException())
            .add(0, 3, 442309.4)
            .add(0, 4, 131289731)
            .build()
        ) as F7Exception
      ).name,
      F7ExceptionName.REF
    );
  });

  it("should use lookup function for range queries", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new AVERAGEA(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    lookup.withArgs(CommonModels.M22_RANGE).returns(GRID);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 1.3173240903122231e8 / 5);
    assert.isTrue(lookup.calledWith(CommonModels.M22_RANGE));
    assert.equal(lookup.callCount, 1);
    assert.isTrue(collateralLookup.notCalled);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((AVERAGEA.SELF.run(null) as NAException).name, F7ExceptionName.NA);
  });
});
