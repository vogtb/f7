import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { LOG10 } from "../../../../main/js/formulas/math/LOG10";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { Converters } from "../../../../main/js/utils/Converters";
import { CommonModels } from "../../testutils/CommonModels";

describe("LOG10", function () {
  it("should work with numbers", function () {
    assert.deepEqual(LOG10.SELF.run(null, 128), Math["log10"](128));
    assert.deepEqual(LOG10.SELF.run(null, 1), Math["log10"](1));
    assert.deepEqual(LOG10.SELF.run(null, 2), Math["log10"](2));
    assert.deepEqual(LOG10.SELF.run(null, 3), Math["log10"](3));
    assert.deepEqual(LOG10.SELF.run(null, 10), Math["log10"](10));
    assert.deepEqual(LOG10.SELF.run(null, 100), Math["log10"](100));
    assert.deepEqual(LOG10.SELF.run(null, 1000), Math["log10"](1000));
    assert.deepEqual(LOG10.SELF.run(null, 10000), Math["log10"](10000));
    assert.deepEqual(LOG10.SELF.run(null, 100000), Math["log10"](100000));
  });

  it("should return errors when parameters are out of bounds", function () {
    assert.deepEqual(
      Converters.castAsF7Exception(LOG10.SELF.run(null, 0)).name,
      F7ExceptionName.NUM
    );
    assert.deepEqual(
      Converters.castAsF7Exception(LOG10.SELF.run(null, -10)).name,
      F7ExceptionName.NUM
    );
  });

  it("should work with strings", function () {
    assert.deepEqual(LOG10.SELF.run(null, "128"), Math["log10"](128));
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(LOG10.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new LOG10(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(128);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), Math["log10"](128));
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 128).add(0, 1, "A").build();
    assert.deepEqual(LOG10.SELF.run(null, one), Math["log10"](128));
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((LOG10.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((LOG10.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
