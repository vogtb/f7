import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ACOSH } from "../../../../main/js/formulas/math/ACOSH";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { Converters } from "../../../../main/js/utils/Converters";
import { CommonModels } from "../../testutils/CommonModels";

describe("ACOSH", function () {
  it("should work", function () {
    assert.equal(ACOSH.SELF.run(null, 10), 2.993222846126381);
    assert.equal(ACOSH.SELF.run(null, 128731.2), 12.458628968991492);
    assert.equal(ACOSH.SELF.run(null, 11.11), 3.098961197908289);
    assert.equal(ACOSH.SELF.run(null, 88281), 12.081427368428402);
  });

  it("should throw NUM error", function () {
    assert.equal(Converters.castAsF7Exception(ACOSH.SELF.run(null, 0)).name, F7ExceptionName.NUM);
    assert.equal(
      Converters.castAsF7Exception(ACOSH.SELF.run(null, 0.0000001)).name,
      F7ExceptionName.NUM
    );
    assert.equal(Converters.castAsF7Exception(ACOSH.SELF.run(null, -10)).name, F7ExceptionName.NUM);
  });

  it("should work with strings", function () {
    assert.equal(ACOSH.SELF.run(null, "10"), 2.993222846126381);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(ACOSH.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ACOSH(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(10);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 2.993222846126381);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 10).add(0, 1, "A").build();
    assert.deepEqual(ACOSH.SELF.run(null, one), 2.993222846126381);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ACOSH.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((ACOSH.SELF.run(null, "A", "B") as NAException).name, F7ExceptionName.NA);
  });
});
