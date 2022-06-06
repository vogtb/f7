package io.protobase.f7.transpiler;

import io.protobase.f7.antlr.F7BaseVisitor;
import io.protobase.f7.antlr.F7Parser;
import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.errors.ParseException;
import io.protobase.f7.errors.RefException;
import io.protobase.f7.models.BinaryOperationNode;
import io.protobase.f7.models.CellQuery;
import io.protobase.f7.models.ColumnRowKey;
import io.protobase.f7.models.ErrorNode;
import io.protobase.f7.models.FormulaNode;
import io.protobase.f7.models.ListNode;
import io.protobase.f7.models.Node;
import io.protobase.f7.models.NumberNode;
import io.protobase.f7.models.RangeNode;
import io.protobase.f7.models.RangeQueryNode;
import io.protobase.f7.models.TextNode;
import io.protobase.f7.models.UnaryMinusOperationNode;
import io.protobase.f7.models.UnaryPercentOperationNode;
import io.protobase.f7.models.UnaryPlusOperationNode;
import io.protobase.f7.models.VariableNode;
import io.protobase.f7.utils.AlphaUtils;
import io.protobase.f7.utils.Converters;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The node visitor transpiles all valid F7 code, by recursively visiting each node.
 */
public class TranspilationVisitor extends F7BaseVisitor<Node> {
  /**
   * Variable names (grid names, named ranges, etc.) have a maximum length of 255 characters. This type of limit is
   * difficult and messy to enforce in G4 grammars, so we enforce it here.
   */
  private static final int MAX_VARIABLE_NAME_LENGTH = 255;

  /**
   * Visit the unary minus expression.
   *
   * @param ctx - holding the expression that we will apply the unary operator to.
   * @return UnaryMinusOperationNode
   */
  @Override
  public Node visitUnaryMinusExpression(F7Parser.UnaryMinusExpressionContext ctx) {
    Node value = this.visit(ctx.expression());
    return new UnaryMinusOperationNode(value);
  }

  /**
   * Visit the unary plus expression.
   *
   * @param ctx - holding the expression that we will apply the unary operator to.
   * @return UnaryPlusOperationNode
   */
  @Override
  public Node visitUnaryPlusExpression(F7Parser.UnaryPlusExpressionContext ctx) {
    Node value = this.visit(ctx.expression());
    return new UnaryPlusOperationNode(value);
  }

  /**
   * Visit the unary percent expression, but ONLY if we have a single percent.
   *
   * @param ctx - holding the expression that will be percent-ed.
   * @return UnaryPercentOperationNode
   */
  @Override
  public Node visitUnaryPercentExpression(F7Parser.UnaryPercentExpressionContext ctx) {
    /*
     * PLEASE NOTE: This is the easiest and clearest way to ensure that all unary percent operations contain only a
     * single percent. I.e Something like 20%% is not allowed. This is difficult, and confusing to achieve in ANTLR G4
     * grammars, so we do it here.
     */
    if (ctx.children.size() > 2) {
      throw new ParseCancellationException();
    }
    Node value = this.visit(ctx.expression());
    return new UnaryPercentOperationNode(value);
  }

  /**
   * Visit the concatenation expression.
   *
   * @param ctx - holding the values we're concatenating.
   * @return BinaryOperationNode
   */
  @Override
  public Node visitConcatExpression(F7Parser.ConcatExpressionContext ctx) {
    Node left = this.visit(ctx.left);
    Node right = this.visit(ctx.right);
    return new BinaryOperationNode(left, "&", right);
  }

  /**
   * Visit power expression by executing the exponential function on the left and right variables.
   *
   * @param ctx - holding the left and right nodes.
   * @return NumberAtom that is the result of the power operation.
   */
  @Override
  public Node visitPowerExpression(F7Parser.PowerExpressionContext ctx) {
    Node left = this.visit(ctx.left);
    Node right = this.visit(ctx.right);
    return new BinaryOperationNode(left, "^", right);
  }

  /**
   * An expression that is just a variable is a valid expression, but it just resolves the variable.
   *
   * @param ctx - context node holding just the atom.
   * @return - Atom once visited.
   */
  @Override
  public Node visitAtomExpression(F7Parser.AtomExpressionContext ctx) {
    return this.visit(ctx.atom());
  }

  /**
   * Visit additive expression node by executing addition/subtraction operation on left and right variables.
   *
   * @param ctx - context containing the operator and left and right operands.
   * @return Double resulting from the addition/subtraction operation.
   */
  @Override
  public Node visitAdditiveExpression(F7Parser.AdditiveExpressionContext ctx) {
    Node left = this.visit(ctx.left);
    Node right = this.visit(ctx.right);
    String operand = ctx.op.getText();
    return new BinaryOperationNode(left, operand, right);
  }

  /**
   * Visit relational expression node by executing relational checking operation on left and right variables.
   *
   * @param ctx - context containing the operator and left and right operands.
   * @return Boolean resulting from the relational operation.
   */
  @Override
  public Node visitRelationalExpression(F7Parser.RelationalExpressionContext ctx) {
    Node left = this.visit(ctx.left);
    Node right = this.visit(ctx.right);
    String operand = ctx.op.getText();
    return new BinaryOperationNode(left, operand, right);
  }

  /**
   * Visit multiplication expression node by executing multiplication operation on left and right variables.
   *
   * @param ctx - context containing the operator and left and right operands.
   * @return NumberAtom resulting from the multiplication operation.
   */
  @Override
  public Node visitMultiplicationExpression(F7Parser.MultiplicationExpressionContext ctx) {
    Node left = this.visit(ctx.left);
    Node right = this.visit(ctx.right);
    String operand = ctx.op.getText();
    return new BinaryOperationNode(left, operand, right);
  }

  /**
   * Visit parenthetical expression by passing the wrapped expression to the generic visitor.
   *
   * @param ctx - context node containing expression.
   * @return Atom expression once fully executed or "resolved".
   */
  @Override
  public Node visitParentheticalAtom(F7Parser.ParentheticalAtomContext ctx) {
    return visit(ctx.expression());
  }

  /**
   * Visit number atom not to convert to double.
   *
   * @param ctx - holding the raw string that should conform to what java can parse as a double. Although many
   *            spreadsheets allow for the parsing numbers with commas and currency, it should be handled in formulas,
   *            not in the executor.
   * @return NumberAtom.
   */
  @Override
  public Node visitNumberAtom(F7Parser.NumberAtomContext ctx) {
    return new NumberNode(Converters.toDouble(Converters.first(ctx.getText())));
  }

  /**
   * Visit string atom node by parsing it into a string value that we would expect. Instead of ""This is my string"", it
   * should be "This is my string" for example.
   *
   * @param ctx - context for this atom.
   * @return String.
   */
  @Override
  public Node visitStringAtom(F7Parser.StringAtomContext ctx) {
    String stringInProgress = ctx.getText();
    // Remove the first and last character, which are quote characters, and strip quotes listAtomFrom remaining string.
    stringInProgress = stringInProgress.substring(1, stringInProgress.length() - 1).replace("\"\"", "\"");
    return new TextNode(stringInProgress);
  }

  /**
   * Visit named atom node by pulling the identifier text and creating a variable node.
   *
   * @param ctx - context for this atom holding the identifier.
   * @return variable node
   */
  @Override
  public Node visitNamedAtom(F7Parser.NamedAtomContext ctx) {
    String identifier = ctx.identifier().getText();
    if (identifier.length() > MAX_VARIABLE_NAME_LENGTH) {
      return new ErrorNode(new ParseException());
    }
    return new VariableNode(identifier);
  }

  /**
   * Visit the list atom by creating a new list, and visiting all children to build the list. A list can have two types
   * of separators: the comma (,) and the semi-colon (;).
   * COMMA: This indicates that we're adding another value to the existing list.
   * SEMI-COLON: THis indicates that we're terminating the existing list, and starting a new one.
   *
   * @param ctx - context containing all children we need to visit.
   * @return ListAtom.
   */
  @Override
  public Node visitListAtom(F7Parser.ListAtomContext ctx) {
    ListNode master = new ListNode();
    ListNode next = new ListNode();
    for (ParseTree child : ctx.children) {
      Node childResult = child.accept(this);
      if (Objects.nonNull(childResult)) {
        if (childResult instanceof ListNode) {
          next.getGrid().addGridToRight(((ListNode) childResult).getGrid());
        } else {
          next.getGrid().addOneToRight(childResult);
        }
      }
      if (child.getText().equals(";")) {
        master.getGrid().addGridToBottom(next.getGrid());
        next = new ListNode();
      }
    }
    if (!next.getGrid().isEmpty()) {
      if (master.isEmpty()) {
        return next;
      }
      master.getGrid().addGridToBottom(next.getGrid());
    }
    if (master.isEmpty()) {
      return new ErrorNode(new RefException("Range does not exist."));
    }
    return master;
  }

  /**
   * Finding an error literal returns it instead of throwing it. In most formulas, finding an error will throw it,
   * stopping the execution of a formula. But not all formulas. Some formulas check the type to serve some other
   * purpose. For example they check to see if it's NA or to execute something else.
   *
   * @param ctx - context node that is an error atom.
   * @return Error
   */
  @Override
  public Node visitErrorAtom(F7Parser.ErrorAtomContext ctx) {
    return new ErrorNode(F7Exception.fromString(ctx.getText().toUpperCase()));
  }

  /**
   * Visit a formula atom node by treating the arguments in the same way that we would a list, finally calling the
   * formula with the arguments.
   *
   * @param ctx - context node for the formula.
   * @return node representing executed formula.
   */
  @Override
  public Node visitFormulaAtom(F7Parser.FormulaAtomContext ctx) {
    String formula = ctx.name.getText();
    List<Node> arguments = new ArrayList<>();
    for (ParseTree child : ctx.arguments().expression()) {
      Node childResult = child.accept(this);
      if (Objects.nonNull(childResult)) {
        arguments.add(childResult);
      }
    }
    return new FormulaNode(formula, arguments);
  }

  /**
   * Visit a range expression by visiting all children, and gather them into a list to pass to the range. Node validity
   * will be check in executor (you can have nodes that are named ranges, and regular ranges for example, but not
   * numbers, or errors, etc).
   *
   * @param ctx - context node for range.
   * @return range query node.
   */
  @Override
  public Node visitRangeExpression(F7Parser.RangeExpressionContext ctx) {
    List<Node> nodes = new ArrayList<>();
    for (ParseTree child : ctx.children) {
      Node childResult = child.accept(this);
      if (Objects.nonNull(childResult)) {
        nodes.add(childResult);
      }
    }
    return new RangeQueryNode(nodes);
  }

  /**
   * Eg: Grid!A1:D1 or A1:D1.
   *
   * @param ctx - holds start and end rows and columns, and maybe grid name.
   * @return RangeNode.
   */
  @Override
  public Node visitBiRange(F7Parser.BiRangeContext ctx) {
    ColumnRowKey first = new ColumnRowKey(AlphaUtils.columnToInt(ctx.firstColumn.getText()), AlphaUtils.rowToInt(ctx.firstRow.getText()));
    ColumnRowKey second = new ColumnRowKey(AlphaUtils.columnToInt(ctx.lastColumn.getText()), AlphaUtils.rowToInt(ctx.lastRow.getText()));

    if (first.compareTo(second) == 1) {
      ColumnRowKey swap = first;
      first = second;
      second = swap;
    }

    CellQuery.Builder builder = CellQuery.builder()
        .columnsBetween(first.getColumnIndex(), second.getColumnIndex())
        .rowsBetween(first.getRowIndex(), second.getRowIndex());
    if (Objects.nonNull(ctx.grid)) {
      builder.grid(ctx.grid.getText());
    }
    return new RangeNode(builder.build());
  }

  /**
   * Eg: Grid!A1 or A1.
   *
   * @param ctx - holds single row and column, and maybe grid name.
   * @return RangeNode
   */
  @Override
  public Node visitUniRange(F7Parser.UniRangeContext ctx) {
    CellQuery.Builder builder = CellQuery.builder()
        .columnsBetween(ctx.firstColumn.getText(), ctx.firstColumn.getText())
        .rowsBetween(ctx.firstRow.getText(), ctx.firstRow.getText());
    if (Objects.nonNull(ctx.grid)) {
      builder.grid(ctx.grid.getText());
    }
    return new RangeNode(builder.build());
  }

  /**
   * Eg: Grid!A:D or A:D.
   *
   * @param ctx - holds start column and end column, and maybe grid name.
   * @return RangeNode
   */
  @Override
  public Node visitColumnWiseBiRange(F7Parser.ColumnWiseBiRangeContext ctx) {
    Integer firstColumn = AlphaUtils.columnToInt(ctx.firstColumn.getText());
    Integer secondColumn = AlphaUtils.columnToInt(ctx.lastColumn.getText());
    if (firstColumn.compareTo(secondColumn) >= 1) {
      Integer swap = firstColumn;
      firstColumn = secondColumn;
      secondColumn = swap;
    }
    CellQuery.Builder builder = CellQuery.builder()
        .openRowsStartingAt(0)
        .columnsBetween(firstColumn, secondColumn);
    if (Objects.nonNull(ctx.grid)) {
      builder.grid(ctx.grid.getText());
    }
    return new RangeNode(builder.build());
  }

  /**
   * Eg: Grid!A2:D or A2:D.
   *
   * @param ctx - holds start column and end column, and an offset row, and maybe grid name.
   * @return RangeNode
   */
  @Override
  public Node visitColumnWiseWithRowOffsetFirstBiRange(F7Parser.ColumnWiseWithRowOffsetFirstBiRangeContext ctx) {
    Integer firstColumn = AlphaUtils.columnToInt(ctx.firstColumn.getText());
    Integer secondColumn = AlphaUtils.columnToInt(ctx.lastColumn.getText());
    if (firstColumn.compareTo(secondColumn) >= 1) {
      Integer swap = firstColumn;
      firstColumn = secondColumn;
      secondColumn = swap;
    }
    CellQuery.Builder builder = CellQuery.builder()
        .openRowsStartingAt(ctx.firstRow.getText())
        .columnsBetween(firstColumn, secondColumn);
    if (Objects.nonNull(ctx.grid)) {
      builder.grid(ctx.grid.getText());
    }
    return new RangeNode(builder.build());
  }

  /**
   * Eg: Grid!A:D2 or A:D2.
   *
   * @param ctx - holds start and end columns, row offset, and maybe grid name.
   * @return RangeNode.
   */
  @Override
  public Node visitColumnWiseWithRowOffsetLastBiRange(F7Parser.ColumnWiseWithRowOffsetLastBiRangeContext ctx) {
    Integer firstColumn = AlphaUtils.columnToInt(ctx.firstColumn.getText());
    Integer secondColumn = AlphaUtils.columnToInt(ctx.lastColumn.getText());
    if (firstColumn.compareTo(secondColumn) >= 1) {
      Integer swap = firstColumn;
      firstColumn = secondColumn;
      secondColumn = swap;
    }
    CellQuery.Builder builder = CellQuery.builder()
        .openRowsStartingAt(ctx.lastRow.getText())
        .columnsBetween(firstColumn, secondColumn);
    if (Objects.nonNull(ctx.grid)) {
      builder.grid(ctx.grid.getText());
    }
    return new RangeNode(builder.build());
  }

  /**
   * Eg: Grid!2:4 or 2:4.
   *
   * @param ctx - holds start and end rows, maybe a grid name.
   * @return RangeNode
   */
  @Override
  public Node visitRowWiseBiRange(F7Parser.RowWiseBiRangeContext ctx) {
    Integer firstRow = AlphaUtils.rowToInt(ctx.firstRow.getText());
    Integer secondRow = AlphaUtils.rowToInt(ctx.lastRow.getText());
    if (firstRow.compareTo(secondRow) >= 1) {
      Integer swap = firstRow;
      firstRow = secondRow;
      secondRow = swap;
    }
    CellQuery.Builder builder = CellQuery.builder()
        .openColumnsStartingAtZero()
        .rowsBetween(firstRow, secondRow);
    if (Objects.nonNull(ctx.grid)) {
      builder.grid(ctx.grid.getText());
    }
    return new RangeNode(builder.build());
  }

  /**
   * Eg: Grid!A2:4 or A2:4.
   *
   * @param ctx - holds start end end rows, and a column offset, and maybe a grid name.
   * @return RangeNode
   */
  @Override
  public Node visitRowWiseWithColumnOffsetFirstBiRange(F7Parser.RowWiseWithColumnOffsetFirstBiRangeContext ctx) {
    Integer firstRow = AlphaUtils.rowToInt(ctx.firstRow.getText());
    Integer secondRow = AlphaUtils.rowToInt(ctx.lastRow.getText());
    if (firstRow.compareTo(secondRow) >= 1) {
      Integer swap = firstRow;
      firstRow = secondRow;
      secondRow = swap;
    }
    CellQuery.Builder builder = CellQuery.builder()
        .openColumnsStartingAt(ctx.firstColumn.getText())
        .rowsBetween(firstRow, secondRow);
    if (Objects.nonNull(ctx.grid)) {
      builder.grid(ctx.grid.getText());
    }
    return new RangeNode(builder.build());
  }

  /**
   * Eg: Grid!2:D4 or 2:D4.
   *
   * @param ctx - holds start and end row, column offset, and maybe a grid name.
   * @return RangeNode
   */
  @Override
  public Node visitRowWiseWithColumnOffsetLastBiRange(F7Parser.RowWiseWithColumnOffsetLastBiRangeContext ctx) {
    Integer firstRow = AlphaUtils.rowToInt(ctx.firstRow.getText());
    Integer secondRow = AlphaUtils.rowToInt(ctx.lastRow.getText());
    if (firstRow.compareTo(secondRow) >= 1) {
      Integer swap = firstRow;
      firstRow = secondRow;
      secondRow = swap;
    }
    CellQuery.Builder builder = CellQuery.builder()
        .openColumnsStartingAt(ctx.lastColumn.getText())
        .rowsBetween(firstRow, secondRow);
    if (Objects.nonNull(ctx.grid)) {
      builder.grid(ctx.grid.getText());
    }
    return new RangeNode(builder.build());
  }
}
