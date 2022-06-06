// Generated from F7.g4 by ANTLR 4.7.2
package io.protobase.f7.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link F7Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public interface F7Visitor<T> extends ParseTreeVisitor<T> {
  /**
   * Visit a parse tree produced by {@link F7Parser#start}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitStart(F7Parser.StartContext ctx);

  /**
   * Visit a parse tree produced by {@link F7Parser#block}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitBlock(F7Parser.BlockContext ctx);

  /**
   * Visit a parse tree produced by the {@code unaryPercentExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitUnaryPercentExpression(F7Parser.UnaryPercentExpressionContext ctx);

  /**
   * Visit a parse tree produced by the {@code unaryMinusExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitUnaryMinusExpression(F7Parser.UnaryMinusExpressionContext ctx);

  /**
   * Visit a parse tree produced by the {@code powerExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitPowerExpression(F7Parser.PowerExpressionContext ctx);

  /**
   * Visit a parse tree produced by the {@code unaryPlusExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitUnaryPlusExpression(F7Parser.UnaryPlusExpressionContext ctx);

  /**
   * Visit a parse tree produced by the {@code atomExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitAtomExpression(F7Parser.AtomExpressionContext ctx);

  /**
   * Visit a parse tree produced by the {@code additiveExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitAdditiveExpression(F7Parser.AdditiveExpressionContext ctx);

  /**
   * Visit a parse tree produced by the {@code relationalExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitRelationalExpression(F7Parser.RelationalExpressionContext ctx);

  /**
   * Visit a parse tree produced by the {@code rangeExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitRangeExpression(F7Parser.RangeExpressionContext ctx);

  /**
   * Visit a parse tree produced by the {@code multiplicationExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitMultiplicationExpression(F7Parser.MultiplicationExpressionContext ctx);

  /**
   * Visit a parse tree produced by the {@code concatExpression}
   * labeled alternative in {@link F7Parser#expression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitConcatExpression(F7Parser.ConcatExpressionContext ctx);

  /**
   * Visit a parse tree produced by the {@code cellAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitCellAtom(F7Parser.CellAtomContext ctx);

  /**
   * Visit a parse tree produced by the {@code stringAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitStringAtom(F7Parser.StringAtomContext ctx);

  /**
   * Visit a parse tree produced by the {@code errorAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitErrorAtom(F7Parser.ErrorAtomContext ctx);

  /**
   * Visit a parse tree produced by the {@code numberAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitNumberAtom(F7Parser.NumberAtomContext ctx);

  /**
   * Visit a parse tree produced by the {@code parentheticalAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitParentheticalAtom(F7Parser.ParentheticalAtomContext ctx);

  /**
   * Visit a parse tree produced by the {@code formulaAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitFormulaAtom(F7Parser.FormulaAtomContext ctx);

  /**
   * Visit a parse tree produced by the {@code listAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitListAtom(F7Parser.ListAtomContext ctx);

  /**
   * Visit a parse tree produced by the {@code namedAtom}
   * labeled alternative in {@link F7Parser#atom}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitNamedAtom(F7Parser.NamedAtomContext ctx);

  /**
   * Visit a parse tree produced by the {@code biRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitBiRange(F7Parser.BiRangeContext ctx);

  /**
   * Visit a parse tree produced by the {@code uniRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitUniRange(F7Parser.UniRangeContext ctx);

  /**
   * Visit a parse tree produced by the {@code columnWiseBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitColumnWiseBiRange(F7Parser.ColumnWiseBiRangeContext ctx);

  /**
   * Visit a parse tree produced by the {@code columnWiseWithRowOffsetFirstBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitColumnWiseWithRowOffsetFirstBiRange(F7Parser.ColumnWiseWithRowOffsetFirstBiRangeContext ctx);

  /**
   * Visit a parse tree produced by the {@code columnWiseWithRowOffsetLastBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitColumnWiseWithRowOffsetLastBiRange(F7Parser.ColumnWiseWithRowOffsetLastBiRangeContext ctx);

  /**
   * Visit a parse tree produced by the {@code rowWiseBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitRowWiseBiRange(F7Parser.RowWiseBiRangeContext ctx);

  /**
   * Visit a parse tree produced by the {@code rowWiseWithColumnOffsetFirstBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitRowWiseWithColumnOffsetFirstBiRange(F7Parser.RowWiseWithColumnOffsetFirstBiRangeContext ctx);

  /**
   * Visit a parse tree produced by the {@code rowWiseWithColumnOffsetLastBiRange}
   * labeled alternative in {@link F7Parser#range}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitRowWiseWithColumnOffsetLastBiRange(F7Parser.RowWiseWithColumnOffsetLastBiRangeContext ctx);

  /**
   * Visit a parse tree produced by {@link F7Parser#arguments}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitArguments(F7Parser.ArgumentsContext ctx);

  /**
   * Visit a parse tree produced by {@link F7Parser#identifier}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitIdentifier(F7Parser.IdentifierContext ctx);

  /**
   * Visit a parse tree produced by {@link F7Parser#comparisonOperator}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitComparisonOperator(F7Parser.ComparisonOperatorContext ctx);
}