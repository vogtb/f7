import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { RAND } from "../../../../main/js/formulas/math/RAND";

describe("RAND", function () {
  it("should return random number between 0 and 1", function () {
    assert.isNotNull(RAND.SELF.run(null));
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((RAND.SELF.run(null, "A") as NAException).name, F7ExceptionName.NA);
  });
});
