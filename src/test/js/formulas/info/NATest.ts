import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { F7Exception } from "../../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { N } from "../../../../main/js/formulas/info/N";

describe("N", function () {
  it("should return value error", function () {
    assert.deepEqual(N.SELF.run(null, 19), 19);
  });

  it("should return error when arguments are not of the correct length", function () {
    assert.deepEqual((N.SELF.run(null) as F7Exception).name, F7ExceptionName.NA);
  });
});
