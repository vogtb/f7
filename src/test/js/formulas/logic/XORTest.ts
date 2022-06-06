import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { DivException } from "../../../../main/js/errors/DivException";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { XOR } from "../../../../main/js/formulas/logic/XOR";
import { Grid } from "../../../../main/js/models/common/Grid";
import { CollateralLookupFunction, LookupFunction } from "../../../../main/js/models/common/Types";
import { Converters } from "../../../../main/js/utils/Converters";
import { CommonModels } from "../../testutils/CommonModels";

describe("XOR", function () {
  it("should work with numbers", function () {
    assert.equal(XOR.SELF.run(null, 10, 10), false);
    assert.equal(XOR.SELF.run(null, 10, 0), true);
    assert.equal(XOR.SELF.run(null, 1, 1, 1, 1, 1, 1, 0), false);
    assert.equal(XOR.SELF.run(null, 0, 0, 0, 1, 0), true);
    assert.equal(XOR.SELF.run(null, 0, 0, 0, 1, 1), false);
    assert.equal(XOR.SELF.run(null, 0, 0, 0, 0, 0), false);
  });

  it("should work with strings", function () {
    assert.equal(XOR.SELF.run(null, "TRUE", true), false);
    assert.equal(XOR.SELF.run(null, "true", true), false);
    assert.equal(XOR.SELF.run(null, "FALSE", true), true);
    assert.equal(XOR.SELF.run(null, "false", false), false);
  });

  it("should work with booleans", function () {
    assert.equal(XOR.SELF.run(null, true, true, true), true);
    assert.equal(XOR.SELF.run(null, true, true, false), false);
    assert.equal(XOR.SELF.run(null, false, false, false), false);
    assert.equal(XOR.SELF.run(null, false, false, true), true);
  });

  it("should work with null/blank/empty", function () {
    assert.equal(
      Converters.castAsF7Exception(XOR.SELF.run(null, null, null)).name,
      F7ExceptionName.VALUE
    );
    assert.equal(XOR.SELF.run(null, null, null, false), false);
    assert.equal(XOR.SELF.run(null, null, null, true), true);
  });

  it("should do pass-through errors", function () {
    assert.equal(
      (XOR.SELF.run(null, 4, new DivException()) as F7Exception).name,
      F7ExceptionName.DIV
    );
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new XOR(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    lookup.withArgs(CommonModels.M22_RANGE).returns(true);
    lookup.withArgs(CommonModels.G19_RANGE).returns(true);
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE, CommonModels.G19_RANGE), false);
    assert.isTrue(lookup.calledWith(CommonModels.M22_RANGE));
    assert.isTrue(lookup.calledWith(CommonModels.G19_RANGE));
    assert.equal(lookup.callCount, 2);
    assert.isTrue(collateralLookup.notCalled);
  });

  it("should handle grids", function () {
    const one = Grid.from([[false], [true]]);
    const two = Grid.from([[false], [false]]);
    const three = Grid.from([[false], [false]]);
    assert.deepEqual(XOR.SELF.run(null, one, two), true);
    assert.deepEqual(XOR.SELF.run(null, two, three), false);
    assert.deepEqual(XOR.SELF.run(null, three), false);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((XOR.SELF.run(null) as NAException).name, F7ExceptionName.NA);
  });
});
