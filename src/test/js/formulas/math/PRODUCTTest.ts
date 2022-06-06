import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { RefException } from "../../../../main/js/errors/RefException";
import { PRODUCT } from "../../../../main/js/formulas/math/PRODUCT";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("PRODUCT", function () {
  const GRID = Grid.builder()
    .add(0, 0, 22.1)
    .add(0, 1, 324.3)
    .add(0, 2, 22.2312223131232)
    .add(0, 3, 442309.4)
    .add(0, 4, 13)
    .build();

  it("should do normal operations", function () {
    assert.deepEqual(PRODUCT.SELF.run(null, 10, 10.0), 100);
    assert.deepEqual(PRODUCT.SELF.run(null, 10, 2), 20);
    assert.deepEqual(PRODUCT.SELF.run(null, 10, 3), 30);
    assert.deepEqual(PRODUCT.SELF.run(null, 0, 1628736813.2), 0);
    assert.deepEqual(PRODUCT.SELF.run(null, 21.22, 2876.111), 61031.075419999994);
    assert.deepEqual(PRODUCT.SELF.run(null, 1, 2, 3, 4, 5, 6, 7, 8), 40320);
    assert.deepEqual(PRODUCT.SELF.run(null, 1, 2, 3, 4, 5, 6, 7, 8, -1), -40320);
  });

  it("should do string conversion", function () {
    assert.equal(PRODUCT.SELF.run(null, "10", "10"), 100);
    assert.equal(PRODUCT.SELF.run(null, "10", "2"), 20);
    assert.equal(PRODUCT.SELF.run(null, "10", "3"), 30);
    assert.deepEqual(PRODUCT.SELF.run(null, "1", "2", "3", "4", "5", "6", "7", "8"), 40320);
    assert.deepEqual(PRODUCT.SELF.run(null, true, false, true, false, true), 0);
    assert.deepEqual(PRODUCT.SELF.run(null, GRID), 916161601382.0216);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(
      (PRODUCT.SELF.run(null, 1, new RefException()) as F7Exception).name,
      F7ExceptionName.REF
    );
    assert.deepEqual(
      (
        PRODUCT.SELF.run(
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
    const F = new PRODUCT(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    lookup.withArgs(CommonModels.M22_RANGE).returns(GRID);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 916161601382.0216);
    assert.isTrue(lookup.calledWith(CommonModels.M22_RANGE));
    assert.equal(lookup.callCount, 1);
    assert.isTrue(collateralLookup.notCalled);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((PRODUCT.SELF.run(null) as NAException).name, F7ExceptionName.NA);
  });
});
