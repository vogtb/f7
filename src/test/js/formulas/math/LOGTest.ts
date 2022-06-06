import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { LOG } from "../../../../main/js/formulas/math/LOG";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { Converters } from "../../../../main/js/utils/Converters";
import { CommonModels } from "../../testutils/CommonModels";

describe("LOG", function () {
  it("should work with numbers", function () {
    assert.deepEqual(LOG.SELF.run(null, 128, 2), 7);
    assert.deepEqual(LOG.SELF.run(null, 128, 3), 4.4165082750002025);
    assert.deepEqual(LOG.SELF.run(null, 1, 2), 0);
    assert.deepEqual(LOG.SELF.run(null, 2, 2), 1);
    assert.deepEqual(LOG.SELF.run(null, 3, 3), 1);
    assert.deepEqual(LOG.SELF.run(null, 2, 3), 0.6309297535714575);
  });

  it("should work with default base", function () {
    assert.deepEqual(LOG.SELF.run(null, 1), 0);
    assert.deepEqual(LOG.SELF.run(null, 10), 1);
    assert.deepEqual(LOG.SELF.run(null, 100), 2);
    assert.deepEqual(LOG.SELF.run(null, 1000), 3);
    assert.deepEqual(LOG.SELF.run(null, 10000), 4);
    assert.deepEqual(LOG.SELF.run(null, 100000), 5);
  });

  it("should return errors when parameters are out of bounds", function () {
    assert.deepEqual(
      Converters.castAsF7Exception(LOG.SELF.run(null, 128, 1)).name,
      F7ExceptionName.DIV
    );
    assert.deepEqual(
      Converters.castAsF7Exception(LOG.SELF.run(null, 128, 0)).name,
      F7ExceptionName.NUM
    );
    assert.deepEqual(
      Converters.castAsF7Exception(LOG.SELF.run(null, 0, 1)).name,
      F7ExceptionName.NUM
    );
  });

  it("should work with strings", function () {
    assert.deepEqual(LOG.SELF.run(null, "100000"), 5);
  });

  it("should do pass-through errors", function () {
    assert.deepEqual(LOG.SELF.run(null, new ValueException()), new ValueException());
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new LOG(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(10000);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 4);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.builder().add(0, 0, 10000).add(0, 1, "A").build();
    assert.deepEqual(LOG.SELF.run(null, one), 4);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((LOG.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual((LOG.SELF.run(null, "A", "B", "C") as NAException).name, F7ExceptionName.NA);
  });
});
