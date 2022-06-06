import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { stub } from "sinon";
import { DivException } from "../../../../main/js/errors/DivException";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { NameException } from "../../../../main/js/errors/NameException";
import { NullException } from "../../../../main/js/errors/NullException";
import { NumException } from "../../../../main/js/errors/NumException";
import { ParseException } from "../../../../main/js/errors/ParseException";
import { RefException } from "../../../../main/js/errors/RefException";
import { ValueException } from "../../../../main/js/errors/ValueException";
import { ERRORTYPE } from "../../../../main/js/formulas/info/ERRORTYPE";
import { Grid } from "../../../../main/js/models/common/Grid";
import {
  CollateralLookupFunction,
  Computed,
  LookupFunction,
} from "../../../../main/js/models/common/Types";
import { CommonModels } from "../../testutils/CommonModels";

describe("ERRORTYPE", function () {
  it("should return code for errors", function () {
    assert.deepEqual(ERRORTYPE.SELF.run(null, new NullException()), 1);
    assert.deepEqual(ERRORTYPE.SELF.run(null, new DivException()), 2);
    assert.deepEqual(ERRORTYPE.SELF.run(null, new ValueException()), 3);
    assert.deepEqual(ERRORTYPE.SELF.run(null, new RefException()), 4);
    assert.deepEqual(ERRORTYPE.SELF.run(null, new NameException()), 5);
    assert.deepEqual(ERRORTYPE.SELF.run(null, new NumException()), 6);
    assert.deepEqual(ERRORTYPE.SELF.run(null, new NAException()), 7);
    assert.deepEqual(ERRORTYPE.SELF.run(null, new ParseException()), 8);
  });

  it("should use lookup", function () {
    const lookup = stub();
    const collateralLookup = stub();
    const F = new ERRORTYPE(lookup as LookupFunction, collateralLookup as CollateralLookupFunction);
    collateralLookup.withArgs(CommonModels.A1, CommonModels.M22_RANGE).returns(new DivException());
    assert.deepEqual(F.run(CommonModels.A1, CommonModels.M22_RANGE), 2);
    assert.isTrue(collateralLookup.calledWith(CommonModels.A1, CommonModels.M22_RANGE));
    assert.equal(collateralLookup.callCount, 1);
    assert.isTrue(lookup.notCalled);
  });

  it("should handle grids", function () {
    assert.deepEqual(
      ERRORTYPE.SELF.run(null, Grid.from<Computed>([[new NullException()], [10]])),
      1
    );
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((ERRORTYPE.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual(
      (ERRORTYPE.SELF.run(null, "Too many") as NAException).name,
      F7ExceptionName.NA
    );
  });
});
