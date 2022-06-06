import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { PI } from "../../../../main/js/formulas/math/PI";
import { POW } from "../../../../main/js/formulas/math/POW";

describe("PI", function () {
  it("should return pi", function () {
    assert.deepEqual(PI.SELF.run(null), Math.PI);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((POW.SELF.run(null, "Nope!") as NAException).name, F7ExceptionName.NA);
  });
});
