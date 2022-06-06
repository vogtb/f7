import { NameException } from "../errors/NameException";
import { SheetColumnRowKey } from "../models/common/SheetColumnRowKey";
import { CollateralLookupFunction, Complex, LookupFunction } from "../models/common/Types";
import { AbstractFormula } from "./AbstractFormula";
import { BIN2DEC } from "./engineering/BIN2DEC";
import { BIN2HEX } from "./engineering/BIN2HEX";
import { DELTA } from "./engineering/DELTA";
import { FormulaName } from "./FormulaName";
import { ERRORTYPE } from "./info/ERRORTYPE";
import { ISBLANK } from "./info/ISBLANK";
import { ISERR } from "./info/ISERR";
import { ISERROR } from "./info/ISERROR";
import { ISLOGICAL } from "./info/ISLOGICAL";
import { ISNA } from "./info/ISNA";
import { ISNONTEXT } from "./info/ISNONTEXT";
import { ISNUMBER } from "./info/ISNUMBER";
import { ISTEXT } from "./info/ISTEXT";
import { N } from "./info/N";
import { NA } from "./info/NA";
import { TYPE } from "./info/TYPE";
import { AND } from "./logic/AND";
import { EQ } from "./logic/EQ";
import { EXACT } from "./logic/EXACT";
import { FALSE } from "./logic/FALSE";
import { GT } from "./logic/GT";
import { GTE } from "./logic/GTE";
import { IF } from "./logic/IF";
import { IFERROR } from "./logic/IFERROR";
import { IFNA } from "./logic/IFNA";
import { LT } from "./logic/LT";
import { LTE } from "./logic/LTE";
import { NE } from "./logic/NE";
import { NOT } from "./logic/NOT";
import { OR } from "./logic/OR";
import { TRUE } from "./logic/TRUE";
import { XOR } from "./logic/XOR";
import { ABS } from "./math/ABS";
import { ACOS } from "./math/ACOS";
import { ACOSH } from "./math/ACOSH";
import { ACOT } from "./math/ACOT";
import { ACOTH } from "./math/ACOTH";
import { ADD } from "./math/ADD";
import { ASIN } from "./math/ASIN";
import { ASINH } from "./math/ASINH";
import { ATAN } from "./math/ATAN";
import { ATAN2 } from "./math/ATAN2";
import { ATANH } from "./math/ATANH";
import { COS } from "./math/COS";
import { COSH } from "./math/COSH";
import { COT } from "./math/COT";
import { DIVIDE } from "./math/DIVIDE";
import { EVEN } from "./math/EVEN";
import { FLOOR } from "./math/FLOOR";
import { INT } from "./math/INT";
import { ISEVEN } from "./math/ISEVEN";
import { ISODD } from "./math/ISODD";
import { LN } from "./math/LN";
import { LOG } from "./math/LOG";
import { LOG10 } from "./math/LOG10";
import { MINUS } from "./math/MINUS";
import { MOD } from "./math/MOD";
import { MULTIPLY } from "./math/MULTIPLY";
import { ODD } from "./math/ODD";
import { PI } from "./math/PI";
import { POW } from "./math/POW";
import { PRODUCT } from "./math/PRODUCT";
import { QUOTIENT } from "./math/QUOTIENT";
import { RAND } from "./math/RAND";
import { RANDBETWEEN } from "./math/RANDBETWEEN";
import { ROUND } from "./math/ROUND";
import { ROUNDDOWN } from "./math/ROUNDDOWN";
import { ROUNDUP } from "./math/ROUNDUP";
import { SIGN } from "./math/SIGN";
import { SIN } from "./math/SIN";
import { SINH } from "./math/SINH";
import { SQRT } from "./math/SQRT";
import { SQRTPI } from "./math/SQRTPI";
import { SUM } from "./math/SUM";
import { TAN } from "./math/TAN";
import { TANH } from "./math/TANH";
import { UMINUS } from "./math/UMINUS";
import { UNARY_PERCENT } from "./math/UNARY_PERCENT";
import { UPLUS } from "./math/UPLUS";
import { TO_PERCENT } from "./parser/TO_PERCENT";
import { TO_TEXT } from "./parser/TO_TEXT";
import { AVERAGE } from "./statistical/AVERAGE";
import { AVERAGEA } from "./statistical/AVERAGEA";
import { COUNT } from "./statistical/COUNT";
import { COUNTA } from "./statistical/COUNTA";
import { COUNTBLANK } from "./statistical/COUNTBLANK";
import { MAX } from "./statistical/MAX";
import { MAXA } from "./statistical/MAXA";
import { MIN } from "./statistical/MIN";
import { MINA } from "./statistical/MINA";
import { CONCAT } from "./text/CONCAT";

export class FormulaCaller {
  readonly formulas: { [index: string]: AbstractFormula };
  readonly customFormulas: { [index: string]: unknown };
  private lookup: LookupFunction;
  private collateralLookup: CollateralLookupFunction;

  constructor(
    lookup: LookupFunction,
    collateralLookup: CollateralLookupFunction,
    customFormulas?: { [index: string]: unknown }
  ) {
    this.lookup = lookup;
    this.collateralLookup = collateralLookup;
    this.formulas = {
      [FormulaName.ADD]: new ADD(lookup, collateralLookup),
      [FormulaName.MINUS]: new MINUS(lookup, collateralLookup),
      [FormulaName.DIVIDE]: new DIVIDE(lookup, collateralLookup),
      [FormulaName.MULTIPLY]: new MULTIPLY(lookup, collateralLookup),
      [FormulaName.POW]: new POW(lookup, collateralLookup),
      [FormulaName.POWER]: new POW(lookup, collateralLookup),
      [FormulaName.EQ]: new EQ(lookup, collateralLookup),
      [FormulaName.NE]: new NE(lookup, collateralLookup),
      [FormulaName.GT]: new GT(lookup, collateralLookup),
      [FormulaName.GTE]: new GTE(lookup, collateralLookup),
      [FormulaName.LT]: new LT(lookup, collateralLookup),
      [FormulaName.LTE]: new LTE(lookup, collateralLookup),
      [FormulaName.UMINUS]: new UMINUS(lookup, collateralLookup),
      [FormulaName.UPLUS]: new UPLUS(lookup, collateralLookup),
      [FormulaName.UNARY_PERCENT]: new UNARY_PERCENT(lookup, collateralLookup),
      [FormulaName.CONCAT]: new CONCAT(lookup, collateralLookup),
      [FormulaName.TRUE]: TRUE.SELF,
      [FormulaName.FALSE]: FALSE.SELF,
      [FormulaName.SUM]: new SUM(lookup, collateralLookup),
      [FormulaName.ERRORTYPE]: new ERRORTYPE(lookup, collateralLookup),
      [FormulaName.ISBLANK]: new ISBLANK(lookup, collateralLookup),
      [FormulaName.ISERR]: new ISERR(lookup, collateralLookup),
      [FormulaName.ISERROR]: new ISERROR(lookup, collateralLookup),
      [FormulaName.ISLOGICAL]: new ISLOGICAL(lookup, collateralLookup),
      [FormulaName.IF]: new IF(lookup, collateralLookup),
      [FormulaName.ISNA]: new ISNA(lookup, collateralLookup),
      [FormulaName.ISNONTEXT]: new ISNONTEXT(lookup, collateralLookup),
      [FormulaName.ISNUMBER]: new ISNUMBER(lookup, collateralLookup),
      [FormulaName.ISTEXT]: new ISTEXT(lookup, collateralLookup),
      [FormulaName.N]: new N(lookup, collateralLookup),
      [FormulaName.NA]: new NA(lookup, collateralLookup),
      [FormulaName.TYPE]: new TYPE(lookup, collateralLookup),
      [FormulaName.AND]: new AND(lookup, collateralLookup),
      [FormulaName.EXACT]: new EXACT(lookup, collateralLookup),
      [FormulaName.IFERROR]: new IFERROR(lookup, collateralLookup),
      [FormulaName.IFNA]: new IFNA(lookup, collateralLookup),
      [FormulaName.NOT]: new NOT(lookup, collateralLookup),
      [FormulaName.OR]: new OR(lookup, collateralLookup),
      [FormulaName.XOR]: new XOR(lookup, collateralLookup),
      [FormulaName.ABS]: new ABS(lookup, collateralLookup),
      [FormulaName.ACOS]: new ACOS(lookup, collateralLookup),
      [FormulaName.ACOSH]: new ACOSH(lookup, collateralLookup),
      [FormulaName.ACOT]: new ACOT(lookup, collateralLookup),
      [FormulaName.ACOTH]: new ACOTH(lookup, collateralLookup),
      [FormulaName.ASIN]: new ASIN(lookup, collateralLookup),
      [FormulaName.ASINH]: new ASINH(lookup, collateralLookup),
      [FormulaName.ATAN]: new ATAN(lookup, collateralLookup),
      [FormulaName.ATAN2]: new ATAN2(lookup, collateralLookup),
      [FormulaName.ATANH]: new ATANH(lookup, collateralLookup),
      [FormulaName.COS]: new COS(lookup, collateralLookup),
      [FormulaName.COSH]: new COSH(lookup, collateralLookup),
      [FormulaName.COT]: new COT(lookup, collateralLookup),
      [FormulaName.EVEN]: new EVEN(lookup, collateralLookup),
      [FormulaName.INT]: new INT(lookup, collateralLookup),
      [FormulaName.ROUNDDOWN]: new ROUNDDOWN(lookup, collateralLookup),
      [FormulaName.ROUNDUP]: new ROUNDUP(lookup, collateralLookup),
      [FormulaName.ROUND]: new ROUND(lookup, collateralLookup),
      [FormulaName.ISEVEN]: new ISEVEN(lookup, collateralLookup),
      [FormulaName.ISODD]: new ISODD(lookup, collateralLookup),
      [FormulaName.LN]: new LN(lookup, collateralLookup),
      [FormulaName.LOG]: new LOG(lookup, collateralLookup),
      [FormulaName.LOG10]: new LOG10(lookup, collateralLookup),
      [FormulaName.MOD]: new MOD(lookup, collateralLookup),
      [FormulaName.ODD]: new ODD(lookup, collateralLookup),
      [FormulaName.PI]: PI.SELF,
      [FormulaName.PRODUCT]: new PRODUCT(lookup, collateralLookup),
      [FormulaName.QUOTIENT]: new QUOTIENT(lookup, collateralLookup),
      [FormulaName.RAND]: RAND.SELF,
      [FormulaName.RANDBETWEEN]: new RANDBETWEEN(lookup, collateralLookup),
      [FormulaName.SIGN]: new SIGN(lookup, collateralLookup),
      [FormulaName.SIN]: new SIN(lookup, collateralLookup),
      [FormulaName.SINH]: new SINH(lookup, collateralLookup),
      [FormulaName.SQRT]: new SQRT(lookup, collateralLookup),
      [FormulaName.SQRTPI]: new SQRTPI(lookup, collateralLookup),
      [FormulaName.TAN]: new TAN(lookup, collateralLookup),
      [FormulaName.TANH]: new TANH(lookup, collateralLookup),
      [FormulaName.TO_TEXT]: new TO_TEXT(lookup, collateralLookup),
      [FormulaName.TO_PERCENT]: new TO_PERCENT(lookup, collateralLookup),
      [FormulaName.BIN2DEC]: new BIN2DEC(lookup, collateralLookup),
      [FormulaName.BIN2HEX]: new BIN2HEX(lookup, collateralLookup),
      [FormulaName.AVERAGE]: new AVERAGE(lookup, collateralLookup),
      [FormulaName.AVERAGEA]: new AVERAGEA(lookup, collateralLookup),
      [FormulaName.FLOOR]: new FLOOR(lookup, collateralLookup),
      [FormulaName.DELTA]: new DELTA(lookup, collateralLookup),
      [FormulaName.COUNT]: new COUNT(lookup, collateralLookup),
      [FormulaName.COUNTA]: new COUNTA(lookup, collateralLookup),
      [FormulaName.COUNTBLANK]: new COUNTBLANK(lookup, collateralLookup),
      [FormulaName.MIN]: new MIN(lookup, collateralLookup),
      [FormulaName.MINA]: new MINA(lookup, collateralLookup),
      [FormulaName.MAX]: new MAX(lookup, collateralLookup),
      [FormulaName.MAXA]: new MAXA(lookup, collateralLookup),
    };
    this.customFormulas = customFormulas || {};
  }

  hasFormulaBound(name: FormulaName | string) {
    return name in this.formulas || name in this.customFormulas;
  }

  call(origin: SheetColumnRowKey, name: FormulaName | string, ...args: Array<Complex>): unknown {
    if (name in this.formulas) {
      return this.formulas[name].run(origin, ...args);
    }
    if (name in this.customFormulas) {
      return (this.customFormulas[name] as any)(...args);
    }
    return new NameException(`Unknown formula: '${name.toString()}.`);
  }
}
