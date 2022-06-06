import { assert } from "chai";
import { it, describe } from "../../testutils/TestUtils";
import { F7ExceptionName } from "../../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../../main/js/errors/NAException";
import { RANDBETWEEN } from "../../../../main/js/formulas/math/RANDBETWEEN";
import { Converters } from "../../../../main/js/utils/Converters";

describe("RANDBETWEEN", function () {
  it("should return random number between X and Y", function () {
    for (let i = 0; i < 1000; i++) {
      const result = Converters.castAsNumber(RANDBETWEEN.SELF.run(null, 1, 10));
      assert.isAtLeast(result, 1);
      assert.isAtMost(result, 10);
    }
  });

  it("should return an error when the low is greater than the high", function () {
    assert.deepEqual((RANDBETWEEN.SELF.run(null, 10, 1) as NAException).name, F7ExceptionName.NUM);
  });

  it("should return error when argument lengths are wrong", function () {
    assert.deepEqual((RANDBETWEEN.SELF.run(null) as NAException).name, F7ExceptionName.NA);
    assert.deepEqual(
      (RANDBETWEEN.SELF.run(null, "A", "B", "C") as NAException).name,
      F7ExceptionName.NA
    );
  });
});
