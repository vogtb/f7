import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { RefException } from "../../../../main/js/errors/RefException";
import { SUM } from "../../../../main/js/formulas/math/SUM";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("SUM", function () {
  const GRID = Grid.builder()
    .add(0, 0, 22.1)
    .add(0, 1, 324.3)
    .add(0, 2, 22.2312223131232)
    .add(0, 3, 442309.4)
    .add(0, 4, 131289731)
    .build();

  it("should do normal operations", function () {
    assert.deepEqual(SUM.SELF.run(null, 1, 2, 3, 4, 5, 6, 7, 8), 36);
    assert.deepEqual(SUM.SELF.run(null, 0), 0);
    assert.deepEqual(SUM.SELF.run(null, 2984723.99382), 2984723.99382);
    assert.deepEqual(SUM.SELF.run(null, 2984723e3), 2984723e3);
  });

  it("should do string conversion", function () {
    assert.equal(SUM.SELF.run(null, "10", "10"), 20);
    assert.equal(SUM.SELF.run(null, "10", "2"), 12);
    assert.equal(SUM.SELF.run(null, "10", "3"), 13);
    assert.deepEqual(SUM.SELF.run(null, "1", "2", "3", "4", "5", "6", "7", "8"), 36);
    assert.deepEqual(SUM.SELF.run(null, true, false, true, false, true), 3);
    assert.deepEqual(SUM.SELF.run(null, GRID), 1.3173240903122231e8);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(
      (SUM.SELF.run(null, 1, new RefException()) as F7Exception).name,
      F7ExceptionName.REF
    );
    assert.deepEqual(
      (
        SUM.SELF.run(
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
    const F = new SUM(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    lookup.withArgs(CommonModels.M22_RANGE).returns(GRID);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 1.3173240903122231e8);
    assert.isTrue(lookup.calledWith(CommonModels.M22_RANGE));
    assert.equal(lookup.callCount, 1);
    assert.isTrue(collateralLookup.notCalled);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((SUM.SELF.run(null) as NAException).name, F7ExceptionName.NA);
  });
});
