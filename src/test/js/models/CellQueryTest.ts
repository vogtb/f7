import { assert } from "chai";
import { it, describe } from "../testutils/TestUtils";
import { F7Exception } from "../../../main/js/errors/F7Exception";
import { CellQuery } from "../../../main/js/models/nodes/CellQuery";

const A1 = CellQuery.builder().setSheet("Alpha").columnsBetween(0, 0).rowsBetween(0, 0).build();
const B1 = CellQuery.builder().setSheet("Alpha").columnsBetween(1, 1).rowsBetween(0, 0).build();
const B2 = CellQuery.builder().setSheet("Alpha").columnsBetween(1, 1).rowsBetween(1, 1).build();
const B99 = CellQuery.builder().setSheet("Alpha").columnsBetween(1, 1).rowsBetween(98, 98).build();
const C14 = CellQuery.builder().setSheet("Alpha").columnsBetween(2, 2).rowsBetween(13, 13).build();
const A1toB1 = CellQuery.builder().setSheet("Alpha").columnsBetween(0, 1).rowsBetween(0, 0).build();
const AtoB = CellQuery.builder()
  .setSheet("Alpha")
  .columnsBetween(0, 1)
  .openRowsStartingAtZero()
  .build();

describe("CellQuery.intersects", function () {
  it("should be able to find intersections", function () {
    assert.isFalse(A1.intersects(B1));
    assert.isFalse(B1.intersects(A1));
    assert.isTrue(B2.intersects(B2));
    assert.isFalse(B99.intersects(B2));
    assert.isTrue(A1toB1.intersects(A1));
    assert.isTrue(A1toB1.intersects(B1));
    assert.isTrue(AtoB.intersects(B1));
    assert.isTrue(AtoB.intersects(A1));
    assert.isTrue(AtoB.intersects(B2));
    assert.isTrue(AtoB.intersects(B99));
    assert.isFalse(AtoB.intersects(C14));
  });
});

describe("CellQuery.toBounded", function () {
  it("should bound column and row ranges", function () {
    const unboundedRows = CellQuery.builder().columnsBetween(0, 1).openRowsStartingAtZero().build();
    const expectedBoundedRows = CellQuery.builder().columnsBetween(0, 1).rowsBetween(0, 22).build();
    assert.deepEqual(unboundedRows.toBounded(10, 22), expectedBoundedRows);
    const unboundedColumns = CellQuery.builder()
      .openColumnsStartingAtZero()
      .rowsBetween(0, 20)
      .build();
    const expectedBoundedColumns = CellQuery.builder()
      .columnsBetween(0, 44)
      .rowsBetween(0, 20)
      .build();
    assert.deepEqual(unboundedColumns.toBounded(44, 30), expectedBoundedColumns);
  });

  it("should leave query unchanged when bounds too high", function () {
    const alreadyBounded = CellQuery.builder().columnsBetween(0, 1).rowsBetween(0, 22).build();
    assert.deepEqual(alreadyBounded.toBounded(100, 100), alreadyBounded);
  });

  it("should return bounded subset", function () {
    const alreadyBounded = CellQuery.builder().columnsBetween(0, 44).rowsBetween(0, 22).build();
    const subset = CellQuery.builder().columnsBetween(0, 10).rowsBetween(0, 12).build();
    assert.deepEqual(alreadyBounded.toBounded(10, 12), subset);
  });
});

describe("CellQueryBuilder.expand", function () {
  it("should expand cell query", function () {
    const expanded = CellQuery.builder(B2)
      .expand(A1)
      .expand(A1toB1)
      .expand(B99)
      .expand(C14)
      .build();
    const expected = CellQuery.builder()
      .setSheet("Alpha")
      .columnsBetween(0, 2)
      .rowsBetween(0, 98)
      .build();
    assert.deepEqual(expanded, expected);
  });

  it("should error out on different grid names", function () {
    let exception: F7Exception = null;
    try {
      CellQuery.builder(A1).expand(CellQuery.builder(A1).setSheet("DifferentGrid").build()).build();
    } catch (e) {
      exception = e;
    }
    assert.isNotNull(exception);
  });
});
