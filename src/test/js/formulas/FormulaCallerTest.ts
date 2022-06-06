import { assert } from "chai";
import { it, describe } from "../testutils/TestUtils";
import { DivException } from "../../../main/js/errors/DivException";
import { F7Exception } from "../../../main/js/errors/F7Exception";
import { F7ExceptionName } from "../../../main/js/errors/F7ExceptionName";
import { NAException } from "../../../main/js/errors/NAException";
import { RefException } from "../../../main/js/errors/RefException";
import { FormulaCaller } from "../../../main/js/formulas/FormulaCaller";
import { FormulaName } from "../../../main/js/formulas/FormulaName";
import { Converters } from "../../../main/js/utils/Converters";

describe("FormulaCaller", function () {
  const CALLER = new FormulaCaller(
    (value) => value,
    (origin, value) => value
  );

  it("should return NAME error if formula not found", function () {
    assert.equal(
      (CALLER.call(null, "nope" as FormulaName) as F7Exception).name,
      F7ExceptionName.NAME
    );
  });

  it("should run info formulas properly", function () {
    assert.deepEqual(CALLER.call(null, FormulaName.ERRORTYPE, new DivException()), 2);
    assert.deepEqual(CALLER.call(null, FormulaName.ISBLANK, null), true);
    assert.deepEqual(CALLER.call(null, FormulaName.ISERR, 10), false);
    assert.deepEqual(CALLER.call(null, FormulaName.ISERROR, new DivException()), true);
    assert.deepEqual(CALLER.call(null, FormulaName.ISLOGICAL, false), true);
    assert.deepEqual(CALLER.call(null, FormulaName.ISNA, new NAException()), true);
    assert.deepEqual(CALLER.call(null, FormulaName.ISNONTEXT, 9), true);
    assert.deepEqual(CALLER.call(null, FormulaName.ISNUMBER, 3), true);
    assert.deepEqual(CALLER.call(null, FormulaName.ISTEXT, 3), false);
    assert.deepEqual(CALLER.call(null, FormulaName.N, "Zero."), 0);
    assert.deepEqual(
      Converters.castAsF7Exception(CALLER.call(null, FormulaName.NA)).name,
      F7ExceptionName.NA
    );
    assert.deepEqual(CALLER.call(null, FormulaName.TYPE, 10), 1);
  });

  it("should run logic formulas properly", function () {
    assert.deepEqual(CALLER.call(null, FormulaName.AND, true, true), true);
    assert.deepEqual(CALLER.call(null, FormulaName.EQ, 10, 99.2), false);
    assert.deepEqual(CALLER.call(null, FormulaName.EXACT, "A", "A"), true);
    assert.deepEqual(CALLER.call(null, FormulaName.FALSE), false);
    assert.deepEqual(CALLER.call(null, FormulaName.GT, 10, 5), true);
    assert.deepEqual(CALLER.call(null, FormulaName.GTE, 10, 0.1), true);
    assert.deepEqual(CALLER.call(null, FormulaName.IF, true, 10, -3872), 10);
    assert.deepEqual(CALLER.call(null, FormulaName.IFERROR, new DivException(), 10), 10);
    assert.deepEqual(CALLER.call(null, FormulaName.IFNA, new NAException(), 10), 10);
    assert.deepEqual(CALLER.call(null, FormulaName.LTE, 10, 2.22222), false);
    assert.deepEqual(CALLER.call(null, FormulaName.LT, 1, 2), true);
    assert.deepEqual(CALLER.call(null, FormulaName.NE, 10, 7), true);
    assert.deepEqual(CALLER.call(null, FormulaName.NOT, true), false);
    assert.deepEqual(CALLER.call(null, FormulaName.OR, true, false), true);
    assert.deepEqual(CALLER.call(null, FormulaName.XOR, true, false), true);
    assert.deepEqual(CALLER.call(null, FormulaName.TRUE), true);
  });

  it("should run math formulas properly", function () {
    assert.deepEqual(CALLER.call(null, FormulaName.ABS, -2), 2);
    assert.deepEqual(CALLER.call(null, FormulaName.ACOS, 0), Math.acos(0));
    assert.deepEqual(CALLER.call(null, FormulaName.ACOSH, 10), 2.993222846126381);
    assert.deepEqual(CALLER.call(null, FormulaName.ACOT, 10), 0.09966865249116204);
    assert.deepEqual(CALLER.call(null, FormulaName.ACOTH, 10), 0.10033534773107562);
    assert.deepEqual(CALLER.call(null, FormulaName.ASIN, 0.1), Math.asin(0.1));
    assert.deepEqual(CALLER.call(null, FormulaName.ASINH, 10), 2.99822295029797);
    assert.deepEqual(CALLER.call(null, FormulaName.ATAN, 10), Math.atan(10));
    assert.deepEqual(CALLER.call(null, FormulaName.ATAN2, 10, 4), Math.atan2(10, 4));
    assert.deepEqual(CALLER.call(null, FormulaName.ATANH, 0.489733), 0.5357090350574656);
    assert.deepEqual(CALLER.call(null, FormulaName.COS, 0.489733), Math.cos(0.489733));
    assert.deepEqual(CALLER.call(null, FormulaName.COSH, 0.489733), Math["cosh"](0.489733));
    assert.deepEqual(CALLER.call(null, FormulaName.COT, 1), 0.6420926159343306);
    assert.deepEqual(CALLER.call(null, FormulaName.ADD, 2, 3), 5);
    assert.deepEqual(CALLER.call(null, FormulaName.DIVIDE, 10, 5), 2);
    assert.deepEqual(CALLER.call(null, FormulaName.EVEN, 1.1), 2);
    assert.deepEqual(CALLER.call(null, FormulaName.FLOOR, 1.1), 1);
    assert.deepEqual(CALLER.call(null, FormulaName.INT, 1.1), 1);
    assert.deepEqual(CALLER.call(null, FormulaName.ISEVEN, 2), true);
    assert.deepEqual(CALLER.call(null, FormulaName.ISODD, 2), false);
    assert.deepEqual(CALLER.call(null, FormulaName.LN, 2), 0.6931471805599453);
    assert.deepEqual(CALLER.call(null, FormulaName.LOG, 10, 3), 2.095903274289385);
    assert.deepEqual(CALLER.call(null, FormulaName.LOG10, 128), Math["log10"](128));
    assert.deepEqual(CALLER.call(null, FormulaName.MINUS, 10, 2), 8);
    assert.deepEqual(CALLER.call(null, FormulaName.MOD, 10, 3), 1);
    assert.deepEqual(CALLER.call(null, FormulaName.MULTIPLY, 10, 5), 50);
    assert.deepEqual(CALLER.call(null, FormulaName.ODD, 10), 11);
    assert.deepEqual(CALLER.call(null, FormulaName.PI), Math.PI);
    assert.deepEqual(CALLER.call(null, FormulaName.POW, 2, 5), 32);
    assert.deepEqual(CALLER.call(null, FormulaName.POWER, 2, 5), 32);
    assert.deepEqual(CALLER.call(null, FormulaName.PRODUCT, 2, 5, 2), 20);
    assert.deepEqual(CALLER.call(null, FormulaName.QUOTIENT, 10, 3), 3);
    assert.isNotNull(CALLER.call(null, FormulaName.RAND));
    assert.isNotNull(CALLER.call(null, FormulaName.RANDBETWEEN, 1, 20));
    assert.deepEqual(CALLER.call(null, FormulaName.ROUNDDOWN, 2.11), 2);
    assert.deepEqual(CALLER.call(null, FormulaName.ROUNDUP, 2.11), 3);
    assert.deepEqual(CALLER.call(null, FormulaName.ROUND, 2.11), 2);
    assert.deepEqual(CALLER.call(null, FormulaName.SIGN, 100), 1);
    assert.deepEqual(CALLER.call(null, FormulaName.SIN, 2), 0.9092974268256817);
    assert.deepEqual(CALLER.call(null, FormulaName.SINH, 2), Math["sinh"](2));
    assert.deepEqual(CALLER.call(null, FormulaName.SQRT, 2), Math.sqrt(2));
    assert.deepEqual(CALLER.call(null, FormulaName.SQRTPI, 2), 2.5066282746310002);
    assert.deepEqual(CALLER.call(null, FormulaName.TAN, 2), Math.tan(2));
    assert.deepEqual(CALLER.call(null, FormulaName.TANH, 2), Math["tanh"](2));
    assert.deepEqual(CALLER.call(null, FormulaName.UNARY_PERCENT, 22), 0.22);
    assert.deepEqual(CALLER.call(null, FormulaName.UMINUS, 22), -22);
    assert.deepEqual(CALLER.call(null, FormulaName.UPLUS, "22"), 22);
  });

  it("should run parser formulas properly", function () {
    assert.deepEqual(CALLER.call(null, FormulaName.TO_TEXT, 2.111), "2.111");
    assert.deepEqual(CALLER.call(null, FormulaName.TO_PERCENT, 2.111), 2.111);
  });

  it("should run engineering formulas properly", function () {
    assert.deepEqual(CALLER.call(null, FormulaName.BIN2HEX, "1010101010"), "FFFFFFFEAA");
    assert.deepEqual(CALLER.call(null, FormulaName.BIN2DEC, "1010101010"), -342);
    assert.deepEqual(CALLER.call(null, FormulaName.DELTA, 10, 20), 0);
  });

  it("should run statistical formulas properly", function () {
    assert.deepEqual(CALLER.call(null, FormulaName.AVERAGE, 1, 2, 3, 4), 2.5);
    assert.deepEqual(CALLER.call(null, FormulaName.AVERAGEA, 1, 2, 3, 4), 2.5);
    assert.deepEqual(CALLER.call(null, FormulaName.COUNT, 1, 2, 3, 4), 4);
    assert.deepEqual(CALLER.call(null, FormulaName.COUNTA, 1, 2, new RefException(), 3), 3);
    assert.deepEqual(CALLER.call(null, FormulaName.COUNTBLANK, 1, 2, 3), 0);
    assert.deepEqual(CALLER.call(null, FormulaName.MIN, 1, 2, 3), 1);
    assert.deepEqual(CALLER.call(null, FormulaName.MINA, 1, 2, 3), 1);
    assert.deepEqual(CALLER.call(null, FormulaName.MAX, 1, 2, 3), 3);
    assert.deepEqual(CALLER.call(null, FormulaName.MAXA, 1, 2, 3), 3);
  });
});
