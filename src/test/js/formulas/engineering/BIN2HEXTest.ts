import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { BIN2HEX } from "../../../../main/js/formulas/engineering/BIN2HEX";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("BIN2HEX", function () {
  it("should return correct hex code", function () {
    assert.deepEqual(BIN2HEX.SELF.run(null, "1010101010"), "FFFFFFFEAA");
    assert.deepEqual(BIN2HEX.SELF.run(null, "10"), "2");
    assert.deepEqual(BIN2HEX.SELF.run(null, "10101010"), "AA");
    assert.deepEqual(BIN2HEX.SELF.run(null, "10101010", 4), "00AA");
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new BIN2HEX(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns("1010101010");
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), "FFFFFFFEAA");
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    assert.deepEqual(
      BIN2HEX.SELF.run(null, Grid.builder().add(0, 0, "1010101010").add(0, 1, 10).build()),
      "FFFFFFFEAA"
    );
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((BIN2HEX.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual(
      (BIN2HEX.SELF.run(null, "Too many", "Too many", "Too many") as NAException).name,
      F7ExceptionName.NA
    );
  });
});
