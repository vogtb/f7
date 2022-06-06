import { assert } from "chai";
import { it, describe } from "../testutils/TestUtils";
import { F7Exception } from "../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../main/js/errors/F7ExceptionName";
import { RefException } from "../../../main/js/errors/RefException";
import { Grid } from "../../../main/js/models/common/Grid";
import { run } from "../testutils/TestUtils";

describe("CodeExecutor -  General Array Literal Test", function () {
  it("should work return REF error for empty array literal", function () {
    assert.deepEqual((run("{}") as F7Exception).name, F7ExceptionName.REF);
  });

  it("should work with single strings", function () {
    assert.deepEqual(run("{1}"), Grid.builder().add(0, 0, 1.0).build());
  });

  it("should work with nested single strings", function () {
    assert.deepEqual(run("{{{{{{1}}}}}}"), Grid.builder().add(0, 0, 1.0).build());
  });

  it("should work with column-wise projections", function () {
    assert.deepEqual(
      run("{1, 2, 3, 4}"),
      Grid.builder().add(0, 0, 1.0).add(1, 0, 2.0).add(2, 0, 3.0).add(3, 0, 4.0).build()
    );
  });

  it("should work with nested column-wise projections", function () {
    assert.deepEqual(
      run("{1, {2, {3, {4}}}}"),
      Grid.builder().add(0, 0, 1.0).add(1, 0, 2.0).add(2, 0, 3.0).add(3, 0, 4.0).build()
    );
  });

  it("should work with row-wise projections", function () {
    assert.deepEqual(
      run("{1; 2; 3; 4}"),
      Grid.builder().add(0, 0, 1.0).add(0, 1, 2.0).add(0, 2, 3.0).add(0, 3, 4.0).build()
    );
    assert.deepEqual(
      run("{11; 9; 5; 3; 1}"),
      Grid.builder()
        .add(0, 0, 11.0)
        .add(0, 1, 9.0)
        .add(0, 2, 5.0)
        .add(0, 3, 3.0)
        .add(0, 4, 1.0)
        .build()
    );
  });

  it("should work with nested row-wise projections", function () {
    assert.deepEqual(
      run("{1; {2; {3; {4}}}}"),
      Grid.builder().add(0, 0, 1.0).add(0, 1, 2.0).add(0, 2, 3.0).add(0, 3, 4.0).build()
    );
    assert.deepEqual(
      run("{1;{2;{3;4}};{5}}"),
      Grid.builder()
        .add(0, 0, 1.0)
        .add(0, 1, 2.0)
        .add(0, 2, 3.0)
        .add(0, 3, 4.0)
        .add(0, 4, 5.0)
        .build()
    );
  });

  it("should work with column and row projections - row first", function () {
    assert.deepEqual(
      run("{1, 2; 3, 4}"),
      Grid.builder().add(0, 0, 1.0).add(1, 0, 2.0).add(0, 1, 3.0).add(1, 1, 4.0).build()
    );
    assert.deepEqual(
      run("{1, 2, 3;4, 5, 6}"),
      Grid.builder()
        .add(0, 0, 1.0)
        .add(1, 0, 2.0)
        .add(2, 0, 3.0)
        .add(0, 1, 4.0)
        .add(1, 1, 5.0)
        .add(2, 1, 6.0)
        .build()
    );
  });

  it("should work with column and row projections - column first", function () {
    assert.deepEqual(
      run("{{1; 2},{3; 4}}"),
      Grid.builder().add(0, 0, 1.0).add(1, 0, 3.0).add(0, 1, 2.0).add(1, 1, 4.0).build()
    );
    assert.deepEqual(
      run("{{{}; {}},{{}; {}}}"),
      Grid.builder()
        .add(0, 0, new RefException("Range does not exist."))
        .add(1, 0, new RefException("Range does not exist."))
        .add(0, 1, new RefException("Range does not exist."))
        .add(1, 1, new RefException("Range does not exist."))
        .build()
    );
    assert.deepEqual(
      run(
        "{{{0.0, 1.0, 2.0};{0.1, 1.1, 2.1}};{{0.2, 1.2, 2.2};{0.3, 1.3, 2.3}};{{0.4, 1.4, 2.4};{0.5, 1.5, 2.5}}}"
      ),
      Grid.builder()
        .add(0, 0, 0.0)
        .add(0, 1, 0.1)
        .add(0, 2, 0.2)
        .add(0, 3, 0.3)
        .add(0, 4, 0.4)
        .add(0, 5, 0.5)
        .add(1, 0, 1.0)
        .add(1, 1, 1.1)
        .add(1, 2, 1.2)
        .add(1, 3, 1.3)
        .add(1, 4, 1.4)
        .add(1, 5, 1.5)
        .add(2, 0, 2.0)
        .add(2, 1, 2.1)
        .add(2, 2, 2.2)
        .add(2, 3, 2.3)
        .add(2, 4, 2.4)
        .add(2, 5, 2.5)
        .build()
    );
  });

  it("should return error when columns and rows result in empty values for the projected range", function () {
    assert.deepEqual((run("{1,2;3}") as F7Exception).name, F7ExceptionName.VALUE);
    assert.deepEqual((run("{1,2;3,4,5}") as F7Exception).name, F7ExceptionName.VALUE);
    assert.deepEqual((run("{11;9;5;3;1,2}") as F7Exception).name, F7ExceptionName.VALUE);
    assert.deepEqual((run("{{};{};{},{}}") as F7Exception).name, F7ExceptionName.VALUE);
  });
});
