package io.protobase.f7.formulas;

/**
 * Enumeration of formula names.
 */
public enum FormulaName {
  /**
   * Math.
   */
  ABS("ABS"),
  ACOS("ACOS"),
  ACOSH("ACOSH"),
  ACOT("ACOT"),
  ACOTH("ACOTH"),
  ADD("ADD"),
  ASIN("ASIN"),
  ASINH("ASINH"),
  ATAN("ATAN"),
  ATAN2("ATAN2"),
  ATANH("ATANH"),
  COS("COS"),
  COSH("COSH"),
  COT("COT"),
  DIVIDE("DIVIDE"),
  EVEN("EVEN"),
  INT("INT"),
  ISEVEN("ISEVEN"),
  ISODD("ISODD"),
  LN("LN"),
  LOG("LOG"),
  LOG10("LOG10"),
  MINUS("MINUS"),
  MOD("MOD"),
  MULTIPLY("MULTIPLY"),
  ODD("ODD"),
  PI("PI"),
  POW("POW"),
  POWER("POWER"),
  PRODUCT("PRODUCT"),
  QUOTIENT("QUOTIENT"),
  RAND("RAND"),
  RANDBETWEEN("RANDBETWEEN"),
  ROUND("ROUND"),
  ROUNDDOWN("ROUNDDOWN"),
  ROUNDUP("ROUNDUP"),
  SIGN("SIGN"),
  SIN("SIN"),
  SINH("SINH"),
  SQRT("SQRT"),
  SQRTPI("SQRTPI"),
  SUM("SUM"),
  TAN("TAN"),
  TANH("TANH"),
  UMINUS("UMINUS"),
  UPLUS("UPLUS"),
  UNARY_PERCENT("UNARY_PERCENT"),
  /**
   * Statistical.
   */
  AVEDEV("AVEDEV"),
  AVERAGE("AVERAGE"),
  AVERAGEA("AVERAGEA"),
  COUNT("COUNT"),
  COUNTA("COUNTA"),
  COUNTBLANK("COUNTBLANK"),
  MAX("MAX"),
  MAXA("MAXA"),
  MIN("MIN"),
  MINA("MINA"),
  STDEV("STDEV"),
  /**
   * Info.
   */
  ERRORTYPE("ERRORTYPE"),
  ISBLANK("ISBLANK"),
  ISERR("ISERR"),
  ISERROR("ISERROR"),
  ISLOGICAL("ISLOGICAL"),
  ISNA("ISNA"),
  ISNONTEXT("ISNONTEXT"),
  ISNUMBER("ISNUMBER"),
  ISTEXT("ISTEXT"),
  N("N"),
  NA("NA"),
  TYPE("TYPE"),
  /**
   * Text.
   */
  CONCAT("CONCAT"),
  CONCATENATE("CONCATENATE"),
  LEN("LEN"),
  T("T"),
  /**
   * Parser.
   */
  TO_PERCENT("TO_PERCENT"),
  TO_TEXT("TO_TEXT"),
  /**
   * Lookup.
   */
  CHOOSE("CHOOSE"),
  /**
   * Logical
   */
  AND("AND"),
  EQ("EQ"),
  EXACT("EXACT"),
  FALSE("FALSE"),
  GT("GT"),
  GTE("GTE"),
  IF("IF"),
  IFERROR("IFERROR"),
  IFNA("IFNA"),
  LT("LT"),
  LTE("LTE"),
  NE("NE"),
  NOT("NOT"),
  OR("OR"),
  TRUE("TRUE"),
  XOR("XOR"),
  /**
   * Unknown. For testing only.
   */
  UNKNOWN("UNKNOWN");

  private String name;

  FormulaName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
