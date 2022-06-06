// Generated from F7.g4 by ANTLR 4.7.2
package io.protobase.f7.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link F7Parser}.
 */
public interface F7Listener extends ParseTreeListener {
  /**
   * Enter a parse tree produced by {@link F7Parser#start}.
   *
   * @param ctx the parse tree
   */
  void enterStart(F7Parser.StartContext ctx);

  /**
   * Exit a parse tree produced by {@link F7Parser#start}.
   *
   * @param ctx the parse tree
   */
  void exitStart(F7Parser.StartContext ctx);

  /**
   * Enter a parse tree produced by {@link F7Parser#block}.
   *
   * @param ctx the parse tree
   */
  void enterBlock(F7Parser.BlockContext ctx);

  /**
   * Exit a parse tree produced by {@link F7Parser#block}.
   *
   * @param ctx the parse tree
   */
  void exitBlock(F7Parser.BlockContext ctx);

  /**
   * Enter a parse tree produced by the {@code unaryPercentExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterUnaryPercentExpression(F7Parser.UnaryPercentExpressionContext ctx);

  /**
   * Exit a parse tree produced by the {@code unaryPercentExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitUnaryPercentExpression(F7Parser.UnaryPercentExpressionContext ctx);

  /**
   * Enter a parse tree produced by the {@code unaryMinusExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterUnaryMinusExpression(F7Parser.UnaryMinusExpressionContext ctx);

  /**
   * Exit a parse tree produced by the {@code unaryMinusExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitUnaryMinusExpression(F7Parser.UnaryMinusExpressionContext ctx);

  /**
   * Enter a parse tree produced by the {@code powerExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterPowerExpression(F7Parser.PowerExpressionContext ctx);

  /**
   * Exit a parse tree produced by the {@code powerExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitPowerExpression(F7Parser.PowerExpressionContext ctx);

  /**
   * Enter a parse tree produced by the {@code unaryPlusExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterUnaryPlusExpression(F7Parser.UnaryPlusExpressionContext ctx);

  /**
   * Exit a parse tree produced by the {@code unaryPlusExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitUnaryPlusExpression(F7Parser.UnaryPlusExpressionContext ctx);

  /**
   * Enter a parse tree produced by the {@code atomExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterAtomExpression(F7Parser.AtomExpressionContext ctx);

  /**
   * Exit a parse tree produced by the {@code atomExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitAtomExpression(F7Parser.AtomExpressionContext ctx);

  /**
   * Enter a parse tree produced by the {@code additiveExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterAdditiveExpression(F7Parser.AdditiveExpressionContext ctx);

  /**
   * Exit a parse tree produced by the {@code additiveExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitAdditiveExpression(F7Parser.AdditiveExpressionContext ctx);

  /**
   * Enter a parse tree produced by the {@code relationalExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterRelationalExpression(F7Parser.RelationalExpressionContext ctx);

  /**
   * Exit a parse tree produced by the {@code relationalExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitRelationalExpression(F7Parser.RelationalExpressionContext ctx);

  /**
   * Enter a parse tree produced by the {@code rangeExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterRangeExpression(F7Parser.RangeExpressionContext ctx);

  /**
   * Exit a parse tree produced by the {@code rangeExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitRangeExpression(F7Parser.RangeExpressionContext ctx);

  /**
   * Enter a parse tree produced by the {@code multiplicationExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterMultiplicationExpression(F7Parser.MultiplicationExpressionContext ctx);

  /**
   * Exit a parse tree produced by the {@code multiplicationExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitMultiplicationExpression(F7Parser.MultiplicationExpressionContext ctx);

  /**
   * Enter a parse tree produced by the {@code concatExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterConcatExpression(F7Parser.ConcatExpressionContext ctx);

  /**
   * Exit a parse tree produced by the {@code concatExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitConcatExpression(F7Parser.ConcatExpressionContext ctx);

  /**
   * Enter a parse tree produced by the {@code cellAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   */
  void enterCellAtom(F7Parser.CellAtomContext ctx);

  /**
   * Exit a parse tree produced by the {@code cellAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   */
  void exitCellAtom(F7Parser.CellAtomContext ctx);

  /**
   * Enter a parse tree produced by the {@code stringAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   */
  void enterStringAtom(F7Parser.StringAtomContext ctx);

  /**
   * Exit a parse tree produced by the {@code stringAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   */
  void exitStringAtom(F7Parser.StringAtomContext ctx);

  /**
   * Enter a parse tree produced by the {@code errorAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   */
  void enterErrorAtom(F7Parser.ErrorAtomContext ctx);

  /**
   * Exit a parse tree produced by the {@code errorAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   */
  void exitErrorAtom(F7Parser.ErrorAtomContext ctx);

  /**
   * Enter a parse tree produced by the {@code numberAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   */
  void enterNumberAtom(F7Parser.NumberAtomContext ctx);

  /**
   * Exit a parse tree produced by the {@code numberAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   */
  void exitNumberAtom(F7Parser.NumberAtomContext ctx);

  /**
   * Enter a parse tree produced by the {@code parentheticalAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   */
  void enterParentheticalAtom(F7Parser.ParentheticalAtomContext ctx);

  /**
   * Exit a parse tree produced by the {@code parentheticalAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   */
  void exitParentheticalAtom(F7Parser.ParentheticalAtomContext ctx);

  /**
   * Enter a parse tree produced by the {@code formulaAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   */
  void enterFormulaAtom(F7Parser.FormulaAtomContext ctx);

  /**
   * Exit a parse tree produced by the {@code formulaAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   */
  void exitFormulaAtom(F7Parser.FormulaAtomContext ctx);

  /**
   * Enter a parse tree produced by the {@code listAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   */
  void enterListAtom(F7Parser.ListAtomContext ctx);

  /**
   * Exit a parse tree produced by the {@code listAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   */
  void exitListAtom(F7Parser.ListAtomContext ctx);

  /**
   * Enter a parse tree produced by the {@code namedAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   */
  void enterNamedAtom(F7Parser.NamedAtomContext ctx);

  /**
   * Exit a parse tree produced by the {@code namedAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   */
  void exitNamedAtom(F7Parser.NamedAtomContext ctx);

  /**
   * Enter a parse tree produced by the {@code biRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   */
  void enterBiRange(F7Parser.BiRangeContext ctx);

  /**
   * Exit a parse tree produced by the {@code biRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   */
  void exitBiRange(F7Parser.BiRangeContext ctx);

  /**
   * Enter a parse tree produced by the {@code uniRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   */
  void enterUniRange(F7Parser.UniRangeContext ctx);

  /**
   * Exit a parse tree produced by the {@code uniRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   */
  void exitUniRange(F7Parser.UniRangeContext ctx);

  /**
   * Enter a parse tree produced by the {@code columnWiseBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   */
  void enterColumnWiseBiRange(F7Parser.ColumnWiseBiRangeContext ctx);

  /**
   * Exit a parse tree produced by the {@code columnWiseBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   */
  void exitColumnWiseBiRange(F7Parser.ColumnWiseBiRangeContext ctx);

  /**
   * Enter a parse tree produced by the {@code columnWiseWithRowOffsetFirstBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   */
  void enterColumnWiseWithRowOffsetFirstBiRange(F7Parser.ColumnWiseWithRowOffsetFirstBiRangeContext ctx);

  /**
   * Exit a parse tree produced by the {@code columnWiseWithRowOffsetFirstBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   */
  void exitColumnWiseWithRowOffsetFirstBiRange(F7Parser.ColumnWiseWithRowOffsetFirstBiRangeContext ctx);

  /**
   * Enter a parse tree produced by the {@code columnWiseWithRowOffsetLastBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   */
  void enterColumnWiseWithRowOffsetLastBiRange(F7Parser.ColumnWiseWithRowOffsetLastBiRangeContext ctx);

  /**
   * Exit a parse tree produced by the {@code columnWiseWithRowOffsetLastBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   */
  void exitColumnWiseWithRowOffsetLastBiRange(F7Parser.ColumnWiseWithRowOffsetLastBiRangeContext ctx);

  /**
   * Enter a parse tree produced by the {@code rowWiseBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   */
  void enterRowWiseBiRange(F7Parser.RowWiseBiRangeContext ctx);

  /**
   * Exit a parse tree produced by the {@code rowWiseBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   */
  void exitRowWiseBiRange(F7Parser.RowWiseBiRangeContext ctx);

  /**
   * Enter a parse tree produced by the {@code rowWiseWithColumnOffsetFirstBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   */
  void enterRowWiseWithColumnOffsetFirstBiRange(F7Parser.RowWiseWithColumnOffsetFirstBiRangeContext ctx);

  /**
   * Exit a parse tree produced by the {@code rowWiseWithColumnOffsetFirstBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   */
  void exitRowWiseWithColumnOffsetFirstBiRange(F7Parser.RowWiseWithColumnOffsetFirstBiRangeContext ctx);

  /**
   * Enter a parse tree produced by the {@code rowWiseWithColumnOffsetLastBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   */
  void enterRowWiseWithColumnOffsetLastBiRange(F7Parser.RowWiseWithColumnOffsetLastBiRangeContext ctx);

  /**
   * Exit a parse tree produced by the {@code rowWiseWithColumnOffsetLastBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   */
  void exitRowWiseWithColumnOffsetLastBiRange(F7Parser.RowWiseWithColumnOffsetLastBiRangeContext ctx);

  /**
   * Enter a parse tree produced by {@link F7Parser#arguments}.
   *
   * @param ctx the parse tree
   */
  void enterArguments(F7Parser.ArgumentsContext ctx);

  /**
   * Exit a parse tree produced by {@link F7Parser#arguments}.
   *
   * @param ctx the parse tree
   */
  void exitArguments(F7Parser.ArgumentsContext ctx);

  /**
   * Enter a parse tree produced by {@link F7Parser#identifier}.
   *
   * @param ctx the parse tree
   */
  void enterIdentifier(F7Parser.IdentifierContext ctx);

  /**
   * Exit a parse tree produced by {@link F7Parser#identifier}.
   *
   * @param ctx the parse tree
   */
  void exitIdentifier(F7Parser.IdentifierContext ctx);

  /**
   * Enter a parse tree produced by {@link F7Parser#comparisonOperator}.
   *
   * @param ctx the parse tree
   */
  void enterComparisonOperator(F7Parser.ComparisonOperatorContext ctx);

  /**
   * Exit a parse tree produced by {@link F7Parser#comparisonOperator}.
   *
   * @param ctx the parse tree
   */
  void exitComparisonOperator(F7Parser.ComparisonOperatorContext ctx);
}