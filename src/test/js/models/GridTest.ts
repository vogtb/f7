import { assert } from "chai";
import { it, describe, beforeEach } from "../testutils/TestUtils";
import { Grid } from "../../../main/js/models/common/Grid";

let grid: Grid<any>;

describe("Grid.constructor", function () {
  beforeEach(() => {
    grid = new Grid(4, 6);
  });

  it("should have a valid constructor", function () {
    assert.equal(grid.getColumns(), 4);
    assert.equal(grid.getRows(), 6);
  });

  it("should fail to construct when given negative column sizes", function () {
    grid = null;
    try {
      grid = new Grid(-1, 4);
      assert.isNull(grid);
    } catch (e) {
      assert.isNotNull(e);
    }
    assert.isNull(grid);
  });

  it("should fail to construct when given negative row sizes", function () {
    grid = null;
    try {
      grid = new Grid(1, -4);
      assert.isNull(grid);
    } catch (e) {
      assert.isNotNull(e);
    }
    assert.isNull(grid);
  });
});

describe("Grid.set", function () {
  beforeEach(() => (grid = new Grid(4, 6)));

  it("should set values", function () {
    grid.set(0, 0, "0-0");
    assert.deepEqual(grid.get(0, 0), "0-0");
    grid.set(0, 1, "0-1");
    assert.deepEqual(grid.get(0, 1), "0-1");
    grid.set(1, 1, "1-1");
    assert.deepEqual(grid.get(1, 1), "1-1");
    grid.set(1, 0, "1-0");
    assert.deepEqual(grid.get(1, 0), "1-0");
  });

  it("should bump column and row sizes", function () {
    assert.deepEqual(grid.getColumns(), 4);
    assert.deepEqual(grid.getRows(), 6);
    grid.set(10, 21, "10-21");
    assert.deepEqual(grid.get(10, 21), "10-21");
    assert.deepEqual(grid.getColumns(), 11);
    assert.deepEqual(grid.getRows(), 22);
  });
});

describe("Grid.addGridToBottom", function () {
  beforeEach(() => (grid = new Grid(4, 6)));

  it("should add grid to bottom, as row(s)", function () {
    const first = new Grid<string>(2, 2);
    first.set(0, 0, "A");
    first.set(0, 1, "B");
    first.set(1, 1, "C");
    first.set(1, 0, "D");
    const second = new Grid<string>(2, 2);
    second.set(0, 0, "E");
    second.set(0, 1, "F");
    second.set(1, 1, "G");
    second.set(1, 0, "H");
    first.addGridToBottom(second);
    assert.deepEqual(first.getColumns(), 2);
    assert.deepEqual(first.getRows(), 4);
    assert.deepEqual(first.get(0, 0), "A");
    assert.deepEqual(first.get(0, 1), "B");
    assert.deepEqual(first.get(1, 1), "C");
    assert.deepEqual(first.get(1, 0), "D");
    assert.deepEqual(first.get(1, 2), "H");
    assert.deepEqual(first.get(1, 3), "G");
    assert.deepEqual(first.get(0, 2), "E");
    assert.deepEqual(first.get(0, 3), "F");
  });
});

describe("Grid.addGridToRight", function () {
  beforeEach(() => (grid = new Grid(4, 6)));

  it("should add grid to right, as columns(s)", function () {
    const first = new Grid<string>(2, 2);
    first.set(0, 0, "A");
    first.set(0, 1, "B");
    first.set(1, 1, "C");
    first.set(1, 0, "D");
    const second = new Grid<string>(2, 2);
    second.set(0, 0, "E");
    second.set(0, 1, "F");
    second.set(1, 1, "G");
    second.set(1, 0, "H");
    first.addGridToRight(second);
    assert.deepEqual(first.getColumns(), 4);
    assert.deepEqual(first.getRows(), 2);
    assert.deepEqual(first.get(0, 0), "A");
    assert.deepEqual(first.get(0, 1), "B");
    assert.deepEqual(first.get(1, 1), "C");
    assert.deepEqual(first.get(1, 0), "D");
    assert.deepEqual(first.get(2, 0), "E");
    assert.deepEqual(first.get(3, 0), "H");
    assert.deepEqual(first.get(2, 1), "F");
    assert.deepEqual(first.get(3, 1), "G");
  });
});

describe("Grid.raw", function () {
  beforeEach(() => (grid = new Grid(2, 3)));

  it("should return raw values", function () {
    grid.set(1, 2, "A");
    assert.deepEqual(grid.raw(), [
      [undefined, undefined],
      [undefined, undefined],
      [undefined, "A"],
    ]);
  });
});

describe("Grid.map", function () {
  beforeEach(() => {
    grid = Grid.from([
      [1, 2, 3],
      [4, 5, 6],
      [7, 8, 9],
    ]);
  });

  it("should map all values", function () {
    const expected = Grid.from([
      [10, 20, 30],
      [40, 50, 60],
      [70, 80, 90],
    ]);
    assert.deepEqual(
      grid.map((v) => v * 10),
      expected
    );
  });
});
