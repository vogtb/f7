package io.protobase.f7.formulas;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.NameException;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static com.google.common.truth.Truth.assertThat;

public class FormulaCallerTest extends TestFormula {
  private static final Function<Object, Object> PASS = value -> value;
  private static final BiFunction<GridColumnRowKey, Object, Object> COLLATERAL_PASS = (origin, value) -> value;
  private static final FormulaCaller CALLER = new FormulaCaller(PASS, COLLATERAL_PASS);
  private static final String NULL = null;

  @Test
  public void testCall_formulaNotFound() {
    assertThat(CALLER.call(null, FormulaName.UNKNOWN, 10.0, 10.0)).isEqualTo(new NameException());
  }

  @Test
  public void testCall_info() {
    assertThat(CALLER.call(null, FormulaName.ERRORTYPE, new DivException())).isEqualTo(2.0);
    assertThat(CALLER.call(null, FormulaName.ISERR, new DivException())).isEqualTo(true);
    assertThat(CALLER.call(null, FormulaName.ISERROR, new NAException())).isEqualTo(true);
    assertThat(CALLER.call(null, FormulaName.ISNA, new NAException())).isEqualTo(true);
    assertThat(CALLER.call(null, FormulaName.ISLOGICAL, 10.0)).isEqualTo(false);
    assertThat(CALLER.call(null, FormulaName.ISNONTEXT, 10.0)).isEqualTo(true);
    assertThat(CALLER.call(null, FormulaName.ISNUMBER, 10.0)).isEqualTo(true);
    assertThat(CALLER.call(null, FormulaName.ISTEXT, 10.0)).isEqualTo(false);
    assertThat(CALLER.call(null, FormulaName.ISBLANK, NULL)).isEqualTo(true);
  }

  @Test
  public void testCall_logic() {
    assertThat(CALLER.call(null, FormulaName.AND, true, true)).isEqualTo(true);
    assertThat(CALLER.call(null, FormulaName.OR, false, true)).isEqualTo(true);
    assertThat(CALLER.call(null, FormulaName.XOR, false, true)).isEqualTo(true);
    assertThat(CALLER.call(null, FormulaName.NOT, false)).isEqualTo(true);
    assertThat(CALLER.call(null, FormulaName.EQ, 10.0, 99.2)).isEqualTo(false);
    assertThat(CALLER.call(null, FormulaName.EXACT, "One", "Two")).isEqualTo(false);
    assertThat(CALLER.call(null, FormulaName.FALSE)).isEqualTo(false);
    assertThat(CALLER.call(null, FormulaName.GTE, 10.0, 0.1)).isEqualTo(true);
    assertThat(CALLER.call(null, FormulaName.GT, 10.0, 5.0)).isEqualTo(true);
    assertThat(CALLER.call(null, FormulaName.LTE, 10.0, 2.22222)).isEqualTo(false);
    assertThat(CALLER.call(null, FormulaName.LT, 1.0, 2.0)).isEqualTo(true);
    assertThat(CALLER.call(null, FormulaName.NE, 10.0, 7.0)).isEqualTo(true);
    assertThat(CALLER.call(null, FormulaName.TRUE)).isEqualTo(true);
    assertThat(CALLER.call(null, FormulaName.IF, true, 10.0, -3872.1)).isEqualTo(10.0);
    assertThat(CALLER.call(null, FormulaName.IFERROR, "Hello", 10.0)).isEqualTo("Hello");
    assertThat(CALLER.call(null, FormulaName.IFNA, new NAException(), 10.0)).isEqualTo(10.0);
  }

  @Test
  public void testCall_math() {
    assertThat(CALLER.call(null, FormulaName.ABS, -2.0)).isEqualTo(2.0);
    assertThat(CALLER.call(null, FormulaName.ADD, 2.0, 3.0)).isEqualTo(5.0);
    assertThat(CALLER.call(null, FormulaName.DIVIDE, 10.0, 5.0)).isEqualTo(2.0);
    assertThat(CALLER.call(null, FormulaName.QUOTIENT, 10.0, 6.0)).isEqualTo(1.0);
    assertThat(CALLER.call(null, FormulaName.MINUS, 10.0, 2.0)).isEqualTo(8.0);
    assertThat(CALLER.call(null, FormulaName.MULTIPLY, 10.0, 5.0)).isEqualTo(50.0);
    assertThat(CALLER.call(null, FormulaName.ISODD, 9.0)).isEqualTo(true);
    assertThat(CALLER.call(null, FormulaName.ISEVEN, 9.0)).isEqualTo(false);
    assertThat(CALLER.call(null, FormulaName.PI)).isEqualTo(Math.PI);
    assertThat(CALLER.call(null, FormulaName.POW, 2.0, 5.0)).isEqualTo(32.0);
    assertThat(CALLER.call(null, FormulaName.POWER, 2.0, 5.0)).isEqualTo(32.0);
    assertThat(CALLER.call(null, FormulaName.PRODUCT, 2.0, 5.0, 6.0)).isEqualTo(60.0);
    assertThat(CALLER.call(null, FormulaName.RANDBETWEEN, 10.0, 100.0)).isNotEqualTo(new NameException());
    assertThat(CALLER.call(null, FormulaName.RAND)).isNotEqualTo(new NameException());
    assertThat(CALLER.call(null, FormulaName.ROUND, 18.19)).isEqualTo(18.0);
    assertThat(CALLER.call(null, FormulaName.ROUNDUP, 18.19)).isEqualTo(19.0);
    assertThat(CALLER.call(null, FormulaName.ROUNDDOWN, 18.999999)).isEqualTo(18.0);
    assertThat(CALLER.call(null, FormulaName.SUM, 1.0, 2.0, 3.0)).isEqualTo(6.0);
    assertThat(CALLER.call(null, FormulaName.ASIN, 1.0)).isEqualTo(Math.asin(1.0));
    assertThat(CALLER.call(null, FormulaName.ASINH, 1.0)).isEqualTo(0.8813735870195429);
    assertThat(CALLER.call(null, FormulaName.SIN, 1.0)).isEqualTo(Math.sin(1.0));
    assertThat(CALLER.call(null, FormulaName.SINH, 1.0)).isEqualTo(Math.sinh(1.0));
    assertThat(CALLER.call(null, FormulaName.ACOS, 1.1111)).isEqualTo(Math.acos(1.1111));
    assertThat(CALLER.call(null, FormulaName.ACOSH, 1.1111)).isEqualTo(0.4671223659261196);
    assertThat(CALLER.call(null, FormulaName.ACOT, 1.1111)).isEqualTo(0.732820074189669);
    assertThat(CALLER.call(null, FormulaName.ACOTH, 1.1111)).isEqualTo(1.4722668604975144);
    assertThat(CALLER.call(null, FormulaName.COS, 1.1111)).isEqualTo(Math.cos(1.1111));
    assertThat(CALLER.call(null, FormulaName.COSH, 1.1111)).isEqualTo(Math.cosh(1.1111));
    assertThat(CALLER.call(null, FormulaName.COT, 1.1111)).isEqualTo(1 / Math.tan(1.1111));
    assertThat(CALLER.call(null, FormulaName.ATAN, 1.1111)).isEqualTo(Math.atan(1.1111));
    assertThat(CALLER.call(null, FormulaName.ATANH, 0.489733)).isEqualTo(0.5357090350574656);
    assertThat(CALLER.call(null, FormulaName.ATAN2, 4.0, 3.0)).isEqualTo(Math.atan2(4, 3));
    assertThat(CALLER.call(null, FormulaName.TAN, 1.1111)).isEqualTo(Math.tan(1.1111));
    assertThat(CALLER.call(null, FormulaName.TANH, 1.1111)).isEqualTo(Math.tanh(1.1111));
    assertThat(CALLER.call(null, FormulaName.SQRT, 1.1111)).isEqualTo(Math.sqrt(1.1111));
    assertThat(CALLER.call(null, FormulaName.SQRTPI, 4.0)).isEqualTo(3.5449077018110318);
    assertThat(CALLER.call(null, FormulaName.UNARY_PERCENT, 22.0)).isEqualTo(0.22);
    assertThat(CALLER.call(null, FormulaName.UMINUS, 22.0)).isEqualTo(-22.0);
    assertThat(CALLER.call(null, FormulaName.LN, 128.0)).isEqualTo(Math.log(128.0));
    assertThat(CALLER.call(null, FormulaName.LOG, 128.0, 2.0)).isEqualTo(7.0);
    assertThat(CALLER.call(null, FormulaName.LOG10, 128.0)).isEqualTo(Math.log10(128.0));
    assertThat(CALLER.call(null, FormulaName.MOD, 128.0, 44.0)).isEqualTo(40.0);
    assertThat(CALLER.call(null, FormulaName.SIGN, 1.1)).isEqualTo(1.0);
    assertThat(CALLER.call(null, FormulaName.INT, 15.1)).isEqualTo(15.0);
    assertThat(CALLER.call(null, FormulaName.ODD, 2.0)).isEqualTo(3.0);
    assertThat(CALLER.call(null, FormulaName.EVEN, 3.0)).isEqualTo(4.0);
  }

  @Test
  public void testCall_statistical() {
    assertThat(CALLER.call(null, FormulaName.AVEDEV, 1.0, 3.0, 5.0, 7.0, 11.0)).isEqualTo(2.88);
    assertThat(CALLER.call(null, FormulaName.AVERAGE, 1.0, 3.0, 5.0, 7.0, 11.0)).isEqualTo(5.4);
    assertThat(CALLER.call(null, FormulaName.AVERAGEA, 1.0, 3.0, 5.0, 7.0, 11.0)).isEqualTo(5.4);
    assertThat(CALLER.call(null, FormulaName.COUNTA, "x")).isEqualTo(1.0);
    assertThat(CALLER.call(null, FormulaName.COUNTBLANK, "x")).isEqualTo(0.0);
    assertThat(CALLER.call(null, FormulaName.COUNT, "x")).isEqualTo(0.0);
    assertThat(CALLER.call(null, FormulaName.MAX, 1.0, 2.0)).isEqualTo(2.0);
    assertThat(CALLER.call(null, FormulaName.MAXA, 1.0, 2.0)).isEqualTo(2.0);
    assertThat(CALLER.call(null, FormulaName.MIN, 1.0, 2.0)).isEqualTo(1.0);
    assertThat(CALLER.call(null, FormulaName.MINA, 1.0, 2.0)).isEqualTo(1.0);
    assertThat(CALLER.call(null, FormulaName.STDEV, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)).isEqualTo(2.449489742783178);
  }

  @Test
  public void testCall_text() {
    assertThat(CALLER.call(null, FormulaName.CONCAT, "x", "y")).isEqualTo("xy");
    assertThat(CALLER.call(null, FormulaName.CONCATENATE, "x", "y")).isEqualTo("xy");
    assertThat(CALLER.call(null, FormulaName.T, "x")).isEqualTo("x");
    assertThat(CALLER.call(null, FormulaName.LEN, "x")).isEqualTo(1.0);
  }

  @Test
  public void testCall_parser() {
    assertThat(CALLER.call(null, FormulaName.TO_PERCENT, 10.11)).isEqualTo(10.11);
    assertThat(CALLER.call(null, FormulaName.TO_TEXT, 10.11)).isEqualTo("10.11");
  }

  @Test
  public void testCall_lookup() {
    assertThat(CALLER.call(null, FormulaName.CHOOSE, 1.0, "A", "B")).isEqualTo("A");
  }
}
