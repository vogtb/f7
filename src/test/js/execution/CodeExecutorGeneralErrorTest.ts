import { assert } from "chai";
import { it, describe } from "../testutils/TestUtils";
import { F7Exception } from "../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../main/js/errors/F7ExceptionName";
import { run } from "../testutils/TestUtils";

describe("CodeExecutor - General Error Test", function () {
  it("should work with NULL error", function () {
    assert.deepEqual((run("#NULL!") as F7Exception).name, F7ExceptionName.NULL);
    assert.deepEqual((run("#null!") as F7Exception).name, F7ExceptionName.NULL);
  });

  it("should work with DIV error", function () {
    assert.deepEqual((run("#DIV/0!") as F7Exception).name, F7ExceptionName.DIV);
    assert.deepEqual((run("#div/0!") as F7Exception).name, F7ExceptionName.DIV);
  });

  it("should work with VALUE error", function () {
    assert.deepEqual((run("#VALUE!") as F7Exception).name, F7ExceptionName.VALUE);
    assert.deepEqual((run("#value!") as F7Exception).name, F7ExceptionName.VALUE);
  });

  it("should work with REF error", function () {
    assert.deepEqual((run("#REF!") as F7Exception).name, F7ExceptionName.REF);
    assert.deepEqual((run("#ref!") as F7Exception).name, F7ExceptionName.REF);
  });

  it("should work with NAME error", function () {
    assert.deepEqual((run("#NAME?") as F7Exception).name, F7ExceptionName.NAME);
    assert.deepEqual((run("#name?") as F7Exception).name, F7ExceptionName.NAME);
  });

  it("should work with NUM error", function () {
    assert.deepEqual((run("#NUM!") as F7Exception).name, F7ExceptionName.NUM);
    assert.deepEqual((run("#num!") as F7Exception).name, F7ExceptionName.NUM);
  });

  it("should work with N/A error", function () {
    assert.deepEqual((run("#N/A") as F7Exception).name, F7ExceptionName.NA);
    assert.deepEqual((run("#n/a") as F7Exception).name, F7ExceptionName.NA);
  });

  it("should work with ERROR error", function () {
    assert.deepEqual((run("#ERROR!") as F7Exception).name, F7ExceptionName.PARSE);
    assert.deepEqual((run("#error!") as F7Exception).name, F7ExceptionName.PARSE);
  });
});
