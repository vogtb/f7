package io.protobase.f7.formulas;

import com.google.common.collect.ImmutableMap;
import io.protobase.f7.errors.NameException;
import io.protobase.f7.formulas.info.ERRORTYPE;
import io.protobase.f7.formulas.info.ISBLANK;
import io.protobase.f7.formulas.info.ISERR;
import io.protobase.f7.formulas.info.ISERROR;
import io.protobase.f7.formulas.info.ISLOGICAL;
import io.protobase.f7.formulas.info.ISNA;
import io.protobase.f7.formulas.info.ISNONTEXT;
import io.protobase.f7.formulas.info.ISNUMBER;
import io.protobase.f7.formulas.info.ISTEXT;
import io.protobase.f7.formulas.info.N;
import io.protobase.f7.formulas.info.NA;
import io.protobase.f7.formulas.info.TYPE;
import io.protobase.f7.formulas.logic.AND;
import io.protobase.f7.formulas.logic.EQ;
import io.protobase.f7.formulas.logic.EXACT;
import io.protobase.f7.formulas.logic.FALSE;
import io.protobase.f7.formulas.logic.GT;
import io.protobase.f7.formulas.logic.GTE;
import io.protobase.f7.formulas.logic.IF;
import io.protobase.f7.formulas.logic.IFERROR;
import io.protobase.f7.formulas.logic.IFNA;
import io.protobase.f7.formulas.logic.LT;
import io.protobase.f7.formulas.logic.LTE;
import io.protobase.f7.formulas.logic.NE;
import io.protobase.f7.formulas.logic.NOT;
import io.protobase.f7.formulas.logic.OR;
import io.protobase.f7.formulas.logic.TRUE;
import io.protobase.f7.formulas.lookup.CHOOSE;
import io.protobase.f7.formulas.math.ABS;
import io.protobase.f7.formulas.math.ACOS;
import io.protobase.f7.formulas.math.ACOSH;
import io.protobase.f7.formulas.math.ACOT;
import io.protobase.f7.formulas.math.ACOTH;
import io.protobase.f7.formulas.math.ADD;
import io.protobase.f7.formulas.math.ASIN;
import io.protobase.f7.formulas.math.ASINH;
import io.protobase.f7.formulas.math.ATAN;
import io.protobase.f7.formulas.math.ATAN2;
import io.protobase.f7.formulas.math.ATANH;
import io.protobase.f7.formulas.math.COS;
import io.protobase.f7.formulas.math.COSH;
import io.protobase.f7.formulas.math.COT;
import io.protobase.f7.formulas.math.DIVIDE;
import io.protobase.f7.formulas.math.EVEN;
import io.protobase.f7.formulas.math.INT;
import io.protobase.f7.formulas.math.ISEVEN;
import io.protobase.f7.formulas.math.ISODD;
import io.protobase.f7.formulas.math.LN;
import io.protobase.f7.formulas.math.LOG;
import io.protobase.f7.formulas.math.LOG10;
import io.protobase.f7.formulas.math.MINUS;
import io.protobase.f7.formulas.math.MOD;
import io.protobase.f7.formulas.math.MULTIPLY;
import io.protobase.f7.formulas.math.ODD;
import io.protobase.f7.formulas.math.PI;
import io.protobase.f7.formulas.math.POW;
import io.protobase.f7.formulas.math.PRODUCT;
import io.protobase.f7.formulas.math.QUOTIENT;
import io.protobase.f7.formulas.math.RAND;
import io.protobase.f7.formulas.math.RANDBETWEEN;
import io.protobase.f7.formulas.math.ROUND;
import io.protobase.f7.formulas.math.ROUNDDOWN;
import io.protobase.f7.formulas.math.ROUNDUP;
import io.protobase.f7.formulas.math.SIGN;
import io.protobase.f7.formulas.math.SIN;
import io.protobase.f7.formulas.math.SINH;
import io.protobase.f7.formulas.math.SQRT;
import io.protobase.f7.formulas.math.SQRTPI;
import io.protobase.f7.formulas.math.SUM;
import io.protobase.f7.formulas.math.TAN;
import io.protobase.f7.formulas.math.TANH;
import io.protobase.f7.formulas.math.UMINUS;
import io.protobase.f7.formulas.math.UNARY_PERCENT;
import io.protobase.f7.formulas.math.UPLUS;
import io.protobase.f7.formulas.parser.TO_PERCENT;
import io.protobase.f7.formulas.parser.TO_TEXT;
import io.protobase.f7.formulas.statistical.AVEDEV;
import io.protobase.f7.formulas.statistical.AVERAGE;
import io.protobase.f7.formulas.statistical.AVERAGEA;
import io.protobase.f7.formulas.statistical.COUNT;
import io.protobase.f7.formulas.statistical.COUNTA;
import io.protobase.f7.formulas.statistical.COUNTBLANK;
import io.protobase.f7.formulas.statistical.MAX;
import io.protobase.f7.formulas.statistical.MAXA;
import io.protobase.f7.formulas.statistical.MIN;
import io.protobase.f7.formulas.statistical.MINA;
import io.protobase.f7.formulas.statistical.STDEV;
import io.protobase.f7.formulas.text.CONCAT;
import io.protobase.f7.formulas.text.CONCATENATE;
import io.protobase.f7.formulas.text.LEN;
import io.protobase.f7.formulas.text.T;
import io.protobase.f7.models.GridColumnRowKey;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Static class for calling formulas by name.
 */
public class FormulaCaller {
  private ImmutableMap<FormulaName, AbstractFormula> formulas;

  public FormulaCaller(Function<Object, Object> lookup, BiFunction<GridColumnRowKey, Object, Object> collateralLookup) {
    formulas = ImmutableMap.<FormulaName, AbstractFormula>builder()
        .put(FormulaName.ERRORTYPE, new ERRORTYPE(lookup, collateralLookup))
        .put(FormulaName.ISERR, new ISERR(lookup, collateralLookup))
        .put(FormulaName.ISERROR, new ISERROR(lookup, collateralLookup))
        .put(FormulaName.ISNA, new ISNA(lookup, collateralLookup))
        .put(FormulaName.ISLOGICAL, new ISLOGICAL(lookup, collateralLookup))
        .put(FormulaName.ISNONTEXT, new ISNONTEXT(lookup, collateralLookup))
        .put(FormulaName.ISNUMBER, new ISNUMBER(lookup, collateralLookup))
        .put(FormulaName.ISTEXT, new ISTEXT(lookup, collateralLookup))
        .put(FormulaName.ISBLANK, new ISBLANK(lookup, collateralLookup))
        .put(FormulaName.N, new N(lookup, collateralLookup))
        .put(FormulaName.NA, new NA(lookup, collateralLookup))
        .put(FormulaName.TYPE, new TYPE(lookup, collateralLookup))
        .put(FormulaName.AND, new AND(lookup, collateralLookup))
        .put(FormulaName.EQ, new EQ(lookup, collateralLookup))
        .put(FormulaName.EXACT, new EXACT(lookup, collateralLookup))
        .put(FormulaName.FALSE, FALSE.SELF)
        .put(FormulaName.GT, new GT(lookup, collateralLookup))
        .put(FormulaName.GTE, new GTE(lookup, collateralLookup))
        .put(FormulaName.LT, new LT(lookup, collateralLookup))
        .put(FormulaName.LTE, new LTE(lookup, collateralLookup))
        .put(FormulaName.NE, new NE(lookup, collateralLookup))
        .put(FormulaName.OR, new OR(lookup, collateralLookup))
        .put(FormulaName.XOR, new OR(lookup, collateralLookup))
        .put(FormulaName.NOT, new NOT(lookup, collateralLookup))
        .put(FormulaName.IF, new IF(lookup, collateralLookup))
        .put(FormulaName.IFERROR, new IFERROR(lookup, collateralLookup))
        .put(FormulaName.IFNA, new IFNA(lookup, collateralLookup))
        .put(FormulaName.TRUE, TRUE.SELF)
        .put(FormulaName.DIVIDE, new DIVIDE(lookup, collateralLookup))
        .put(FormulaName.QUOTIENT, new QUOTIENT(lookup, collateralLookup))
        .put(FormulaName.MINUS, new MINUS(lookup, collateralLookup))
        .put(FormulaName.MULTIPLY, new MULTIPLY(lookup, collateralLookup))
        .put(FormulaName.MOD, new MOD(lookup, collateralLookup))
        .put(FormulaName.INT, new INT(lookup, collateralLookup))
        .put(FormulaName.ODD, new ODD(lookup, collateralLookup))
        .put(FormulaName.EVEN, new EVEN(lookup, collateralLookup))
        .put(FormulaName.SIGN, new SIGN(lookup, collateralLookup))
        .put(FormulaName.PI, new PI(lookup, collateralLookup))
        .put(FormulaName.ISODD, new ISODD(lookup, collateralLookup))
        .put(FormulaName.ISEVEN, new ISEVEN(lookup, collateralLookup))
        .put(FormulaName.POW, new POW(lookup, collateralLookup))
        .put(FormulaName.POWER, new POW(lookup, collateralLookup))
        .put(FormulaName.PRODUCT, new PRODUCT(lookup, collateralLookup))
        .put(FormulaName.ADD, new ADD(lookup, collateralLookup))
        .put(FormulaName.UNARY_PERCENT, new UNARY_PERCENT(lookup, collateralLookup))
        .put(FormulaName.UMINUS, new UMINUS(lookup, collateralLookup))
        .put(FormulaName.UPLUS, new UPLUS(lookup, collateralLookup))
        .put(FormulaName.ABS, new ABS(lookup, collateralLookup))
        .put(FormulaName.LN, new LN(lookup, collateralLookup))
        .put(FormulaName.LOG, new LOG(lookup, collateralLookup))
        .put(FormulaName.LOG10, new LOG10(lookup, collateralLookup))
        .put(FormulaName.RAND, RAND.SELF)
        .put(FormulaName.RANDBETWEEN, new RANDBETWEEN(lookup, collateralLookup))
        .put(FormulaName.ROUND, new ROUND(lookup, collateralLookup))
        .put(FormulaName.ROUNDUP, new ROUNDUP(lookup, collateralLookup))
        .put(FormulaName.ROUNDDOWN, new ROUNDDOWN(lookup, collateralLookup))
        .put(FormulaName.SUM, new SUM(lookup, collateralLookup))
        .put(FormulaName.ASIN, new ASIN(lookup, collateralLookup))
        .put(FormulaName.ASINH, new ASINH(lookup, collateralLookup))
        .put(FormulaName.SIN, new SIN(lookup, collateralLookup))
        .put(FormulaName.SINH, new SINH(lookup, collateralLookup))
        .put(FormulaName.ACOS, new ACOS(lookup, collateralLookup))
        .put(FormulaName.ACOSH, new ACOSH(lookup, collateralLookup))
        .put(FormulaName.ACOT, new ACOT(lookup, collateralLookup))
        .put(FormulaName.ACOTH, new ACOTH(lookup, collateralLookup))
        .put(FormulaName.COS, new COS(lookup, collateralLookup))
        .put(FormulaName.COSH, new COSH(lookup, collateralLookup))
        .put(FormulaName.COT, new COT(lookup, collateralLookup))
        .put(FormulaName.ATAN, new ATAN(lookup, collateralLookup))
        .put(FormulaName.ATANH, new ATANH(lookup, collateralLookup))
        .put(FormulaName.ATAN2, new ATAN2(lookup, collateralLookup))
        .put(FormulaName.TAN, new TAN(lookup, collateralLookup))
        .put(FormulaName.TANH, new TANH(lookup, collateralLookup))
        .put(FormulaName.SQRT, new SQRT(lookup, collateralLookup))
        .put(FormulaName.SQRTPI, new SQRTPI(lookup, collateralLookup))
        .put(FormulaName.AVEDEV, new AVEDEV(lookup, collateralLookup))
        .put(FormulaName.AVERAGE, new AVERAGE(lookup, collateralLookup))
        .put(FormulaName.AVERAGEA, new AVERAGEA(lookup, collateralLookup))
        .put(FormulaName.COUNT, new COUNT(lookup, collateralLookup))
        .put(FormulaName.COUNTA, new COUNTA(lookup, collateralLookup))
        .put(FormulaName.COUNTBLANK, new COUNTBLANK(lookup, collateralLookup))
        .put(FormulaName.MAX, new MAX(lookup, collateralLookup))
        .put(FormulaName.MAXA, new MAXA(lookup, collateralLookup))
        .put(FormulaName.MIN, new MIN(lookup, collateralLookup))
        .put(FormulaName.MINA, new MINA(lookup, collateralLookup))
        .put(FormulaName.STDEV, new STDEV(lookup, collateralLookup))
        .put(FormulaName.CONCAT, new CONCAT(lookup, collateralLookup))
        .put(FormulaName.CONCATENATE, new CONCATENATE(lookup, collateralLookup))
        .put(FormulaName.T, new T(lookup, collateralLookup))
        .put(FormulaName.LEN, new LEN(lookup, collateralLookup))
        .put(FormulaName.TO_PERCENT, new TO_PERCENT(lookup, collateralLookup))
        .put(FormulaName.TO_TEXT, new TO_TEXT(lookup, collateralLookup))
        .put(FormulaName.CHOOSE, new CHOOSE(lookup, collateralLookup))
        .build();
  }

  /**
   * Call a formula by name.
   *
   * @param origin    - origin cell that is calling this formula.
   * @param name      - of formula.
   * @param arguments - arguments to pass to formula.
   * @return result of formula or return error.
   */
  public Object call(GridColumnRowKey origin, FormulaName name, Object... arguments) {
    if (formulas.containsKey(name)) {
      return formulas.get(name).apply(origin, arguments);
    }
    return new NameException(String.format("Unknown formula: '%s'.", name.getName()));
  }
}
