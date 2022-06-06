import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ACOTH } from "../../../../main/js/formulas/math/ACOTH";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { Converters } from "../../../../main/js/utils/Converters";
import { CommonModels } from "../../testutils/CommonModels";

describe("ACOTH", function () {
  it("should work with numbers", function () {
    assert.deepEqual(ACOTH.SELF.run(null, 10.0), 0.10033534773107562);
    assert.deepEqual(ACOTH.SELF.run(null, 128731.2), 7.768124588460712e-6);
    assert.deepEqual(ACOTH.SELF.run(null, 11.11), 0.09025326226631046);
    assert.deepEqual(ACOTH.SELF.run(null, 88281.0), 1.132746570655777e-5);
    assert.deepEqual(ACOTH.SELF.run(null, 2.0), 0.5493061443340548);
    assert.deepEqual(ACOTH.SELF.run(null, 4.0), 0.25541281188299536);
    assert.deepEqual(ACOTH.SELF.run(null, -4.0), -0.25541281188299536);
    assert.deepEqual(ACOTH.SELF.run(null, -10124.0), -9.87751879940721e-5);
  });

  it("should throw NUM error", function () {
    assert.deepEqual(
      Converters.castAsF7Exception(ACOTH.SELF.run(null, 0.0)).name,
      F7ExceptionName.NUM
    );
    assert.deepEqual(
      Converters.castAsF7Exception(ACOTH.SELF.run(null, 0.999)).name,
      F7ExceptionName.NUM
    );
    assert.deepEqual(
      Converters.castAsF7Exception(ACOTH.SELF.run(null, -0.999)).name,
      F7ExceptionName.NUM
    );
  });

  it("should work with strings", function () {
    assert.equal(ACOTH.SELF.run(null, "10"), 0.10033534773107562);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(ACOTH.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ACOTH(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(10);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 0.10033534773107562);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 10).add(0, 1, "A").build();
    assert.deepEqual(ACOTH.SELF.run(null, one), 0.10033534773107562);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ACOTH.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((ACOTH.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
