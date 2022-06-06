import { isNotNull, isNotUndefined } from "../utils/Other";
import { f7ExceptionFromString } from "../errors/ExceptionHelpers";
import { ParseException } from "../errors/ParseException";
import { RefException } from "../errors/RefException";
import { ColumnRowKey } from "../models/common/ColumnRowKey";
import { BinaryOperationNode } from "../models/nodes/BinaryOperationNode";
import { CellQuery } from "../models/nodes/CellQuery";
import { ErrorNode } from "../models/nodes/ErrorNode";
import { FormulaNode } from "../models/nodes/FormulaNode";
import { ListNode } from "../models/nodes/ListNode";
import { LogicalNode } from "../models/nodes/LogicalNode";
import { MultiRangeNode } from "../models/nodes/MultiRangeNode";
import { Node } from "../models/nodes/Node";
import { NumberNode } from "../models/nodes/NumberNode";
import { RangeNode } from "../models/nodes/RangeNode";
import { TextNode } from "../models/nodes/TextNode";
import { UnaryMinusOperationNode } from "../models/nodes/UnaryMinusOperationNode";
import { UnaryPercentOperationNode } from "../models/nodes/UnaryPercentOperationNode";
import { UnaryPlusOperationNode } from "../models/nodes/UnaryPlusOperationNode";
import { VariableNode } from "../models/nodes/VariableNode";
import { AlphaUtils } from "../utils/AlphaUtils";
import { Compare } from "../utils/Compare";
import { Numbers } from "../utils/Numbers";
import { F7Visitor } from "../antlr/F7Visitor";

/**
 * The node visitor transpiles all valid F7 code, by recursively visiting each node.
 */
export class TranspilationVisitor extends F7Visitor {
  /**
   * Variable names (grid names, named ranges, etc.) have a maximum length of 255 characters. This type of limit is
   * difficult and messy to enforce in G4 grammars, so we enforce it here.
   */
  private static MAX_VARIABLE_NAME_LENGTH = 255;

  /**
   * Visit parenthetical expression by passing the wrapped expression to the generic visitor.
   *
   * @param ctx - context node containing expression.
   * @return Atom expression once fully executed or "resolved".
   */
  visitParentheticalAtom(ctx: any) {
    return this.visit(ctx.expression());
  }

  /**
   * Visit the unary minus expression.
   *
   * @param ctx - holding the expression that we will apply the unary operator to.
   * @return UnaryMinusOperationNode
   */
  visitUnaryMinusExpression(ctx: any) {
    const value = this.visit(ctx.expression());
    return new UnaryMinusOperationNode(value);
  }

  /**
   * Visit the unary plus expression.
   *
   * @param ctx - holding the expression that we will apply the unary operator to.
   * @return UnaryPlusOperationNode
   */
  visitUnaryPlusExpression(ctx: any) {
    const value = this.visit(ctx.expression());
    return new UnaryPlusOperationNode(value);
  }

  /**
   * Visit the unary percent expression, but ONLY if we have a single percent.
   *
   * @param ctx - holding the expression that will be percent-ed.
   * @return UnaryPercentOperationNode
   */
  visitUnaryPercentExpression(ctx: any) {
    if (ctx.children.length > 2) {
      throw new ParseException();
    }
    const value = this.visit(ctx.expression());
    return new UnaryPercentOperationNode(value);
  }

  /**
   * Visit number atom not to convert to double.
   *
   * @param ctx - holding the raw string that should conform to what java can parse as a double. Although many
   *            spreadsheets allow for the parsing numbers with commas and currency, it should be handled in formulas,
   *            not in the executor.
   * @return NumberAtom.
   */
  visitNumberAtom(ctx: any) {
    return new NumberNode(Numbers.toNumberOrNull(ctx.getText()));
  }

  /**
   * Finding an error literal returns it instead of throwing it. In most formulas, finding an error will throw it,
   * stopping the execution of a formula. But not all formulas. Some formulas check the type to serve some other
   * purpose. For example they check to see if it's NA or to execute something else.
   *
   * @param ctx - context node that is an error atom.
   * @return Error
   */
  visitErrorAtom(ctx: any) {
    return new ErrorNode(f7ExceptionFromString(ctx.getText().toString().toUpperCase()));
  }

  /**
   * Visit the concatenation expression.
   *
   * @param ctx - holding the values we're concatenating.
   * @return BinaryOperationNode
   */
  visitConcatExpression(ctx: any) {
    return new BinaryOperationNode(this.visit(ctx.left), ctx.op.text, this.visit(ctx.right));
  }

  /**
   * Visit additive expression node by executing addition/subtraction operation on left and right variables.
   *
   * @param ctx - context containing the operator and left and right operands.
   * @return Double resulting from the addition/subtraction operation.
   */
  visitAdditiveExpression(ctx: any) {
    return new BinaryOperationNode(this.visit(ctx.left), ctx.op.text, this.visit(ctx.right));
  }

  /**
   * Visit multiplication expression node by executing multiplication operation on left and right variables.
   *
   * @param ctx - context containing the operator and left and right operands.
   * @return NumberAtom resulting from the multiplication operation.
   */
  visitMultiplicationExpression(ctx: any) {
    return new BinaryOperationNode(this.visit(ctx.left), ctx.op.text, this.visit(ctx.right));
  }

  /**
   * Visit relational expression node by executing relational checking operation on left and right variables.
   *
   * @param ctx - context containing the operator and left and right operands.
   * @return Boolean resulting from the relational operation.
   */
  visitRelationalExpression(ctx: any) {
    return new BinaryOperationNode(this.visit(ctx.left), ctx.op.getText(), this.visit(ctx.right));
  }

  /**
   * Visit power expression by executing the exponential function on the left and right variables.
   *
   * @param ctx - holding the left and right nodes.
   * @return NumberAtom that is the result of the power operation.
   */
  visitPowerExpression(ctx: any) {
    return new BinaryOperationNode(this.visit(ctx.left), ctx.op.text, this.visit(ctx.right));
  }

  /**
   * Visit string atom node by parsing it into a string value that we would expect. Instead of ""This is my string"", it
   * should be "This is my string" for example.
   *
   * @param ctx - context for this atom.
   * @return String.
   */
  visitStringAtom(ctx: any) {
    let stringInProgress: string = ctx.getText();
    // Remove the first and last character, which are quote characters, and strip quotes listAtomFrom remaining string.
    stringInProgress = stringInProgress
      .substring(1, stringInProgress.length - 1)
      .replace('""', '"');
    return new TextNode(stringInProgress);
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
  visitListAtom(ctx: any) {
    const master = new ListNode();
    let next = new ListNode();
    for (let i = 0; i < ctx.children.length; i++) {
      const child = ctx.children[i];
      const childResult = child.accept(this);
      if (isNotNull(childResult) && isNotUndefined(childResult)) {
        if (childResult instanceof ListNode) {
          next.grid.addGridToRight((childResult as ListNode).grid);
        } else {
          next.grid.addOneToRight(childResult);
        }
      }
      if (child.getText() === ";") {
        master.grid.addGridToBottom(next.grid);
        next = new ListNode();
      }
    }
    if (!next.grid.isEmpty()) {
      if (master.isEmpty()) {
        return next;
      }
      master.grid.addGridToBottom(next.grid);
    }

    if (master.grid.getColumns() === 0 && master.grid.getRows() === 0) {
      return new ErrorNode(new RefException("Range does not exist."));
    }
    return master;
  }

  /**
   * An expression that is just a variable is a valid expression, but it just resolves the variable.
   *
   * @param ctx - context node holding just the atom.
   * @return - Atom once visited.
   */
  visitAtomExpression(ctx: any) {
    return this.visit(ctx.atom());
  }

  /**
   * Visit named atom node by pulling the identifier text and creating a variable node.
   *
   * @param ctx - context for this atom holding the identifier.
   * @return variable node
   */
  visitNamedAtom(ctx: any) {
    const identifier: string = ctx.identifier().getText();
    if (identifier.length > TranspilationVisitor.MAX_VARIABLE_NAME_LENGTH) {
      return new ErrorNode(new ParseException());
    }
    // If it's case insensitively true or false, return logical node.
    const upper = identifier.toUpperCase();
    if (upper === "TRUE" || upper === "FALSE") {
      return new LogicalNode(upper === "TRUE");
    }
    return new VariableNode(identifier);
  }

  /**
   * Visit a formula atom node by treating the arguments in the same way that we would a list, finally calling the
   * formula with the arguments.
   *
   * @param ctx - context node for the formula.
   * @return node representing executed formula.
   */
  visitFormulaAtom(ctx: any): Node {
    const formulaString: string = ctx.name.getText();
    const args: Array<Node> = [];
    ctx
      .arguments()
      .expression()
      .forEach((child: any) => {
        const childResult = child.accept(this);
        args.push(childResult);
      });
    return new FormulaNode(formulaString, args);
  }

  /**
   * Visit a range expression by visiting all children, and gather them into a list to pass to the range. Node validity
   * will be check in executor (you can have nodes that are named ranges, and regular ranges for example, but not
   * numbers, or errors, etc).
   * @param ctx - context node for range.
   */
  visitRangeExpression(ctx: any) {
    const nodes: Array<Node> = [];
    ctx.children.forEach((child: any) => {
      const childResult = child.accept(this);
      if (isNotNull(childResult) && isNotUndefined(childResult)) {
        nodes.push(childResult);
      }
    });
    return new MultiRangeNode(nodes);
  }

  /**
   * Eg: Grid!A1:D1 or A1:D1.
   *
   * @param ctx - holds start and end rows and columns, and maybe grid name.
   * @return RangeNode.
   */
  visitBiRange(ctx: any): Node {
    let first = new ColumnRowKey(
      AlphaUtils.columnToInt(ctx.firstColumn.text.toUpperCase()),
      AlphaUtils.rowToInt(ctx.firstRow.text)
    );
    let second = new ColumnRowKey(
      AlphaUtils.columnToInt(ctx.lastColumn.text.toUpperCase()),
      AlphaUtils.rowToInt(ctx.lastRow.text)
    );

    if (first.compareTo(second) === 1) {
      const swap = first;
      first = second;
      second = swap;
    }

    const builder = CellQuery.builder()
      .columnsBetween(first.column, second.column)
      .rowsBetween(first.row, second.row);
    if (isNotNull(ctx.grid)) {
      builder.setSheet(ctx.grid.getText());
    }
    return new RangeNode(builder.build());
  }

  /**
   * Eg: Grid!A1 or A1.
   *
   * @param ctx - holds single row and column, and maybe grid name.
   * @return RangeNode
   */
  visitUniRange(ctx: any): Node {
    const builder = CellQuery.builder()
      .columnsBetween(
        AlphaUtils.columnToInt(ctx.firstColumn.text.toUpperCase()),
        AlphaUtils.columnToInt(ctx.firstColumn.text.toUpperCase())
      )
      .rowsBetween(AlphaUtils.rowToInt(ctx.firstRow.text), AlphaUtils.rowToInt(ctx.firstRow.text));
    if (isNotNull(ctx.grid)) {
      builder.setSheet(ctx.grid.getText());
    }
    return new RangeNode(builder.build());
  }

  /**
   * Eg: Grid!A:D or A:D.
   *
   * @param ctx - holds start column and end column, and maybe grid name.
   * @return RangeNode
   */
  visitColumnWiseBiRange(ctx: any): Node {
    let firstColumn = AlphaUtils.columnToInt(ctx.firstColumn.text.toUpperCase());
    let secondColumn = AlphaUtils.columnToInt(ctx.lastColumn.text.toUpperCase());
    if (Compare.numberComparison(firstColumn, secondColumn) >= 1) {
      const swap = firstColumn;
      firstColumn = secondColumn;
      secondColumn = swap;
    }
    const builder = CellQuery.builder()
      .openRowsStartingAtZero()
      .columnsBetween(firstColumn, secondColumn);
    if (isNotNull(ctx.grid)) {
      builder.setSheet(ctx.grid.getText());
    }
    return new RangeNode(builder.build());
  }

  /**
   * Eg: Grid!A2:D or A2:D.
   *
   * @param ctx - holds start column and end column, and an offset row, and maybe grid name.
   * @return RangeNode
   */
  visitColumnWiseWithRowOffsetFirstBiRange(ctx: any): Node {
    let firstColumn = AlphaUtils.columnToInt(ctx.firstColumn.text.toUpperCase());
    let secondColumn = AlphaUtils.columnToInt(ctx.lastColumn.text.toUpperCase());
    if (Compare.numberComparison(firstColumn, secondColumn) >= 1) {
      const swap = firstColumn;
      firstColumn = secondColumn;
      secondColumn = swap;
    }
    const builder = CellQuery.builder()
      .openRowsStartingAt(ctx.firstRow.text)
      .columnsBetween(firstColumn, secondColumn);
    if (isNotNull(ctx.grid)) {
      builder.setSheet(ctx.grid.getText());
    }
    return new RangeNode(builder.build());
  }

  /**
   * Eg: Grid!A:D2 or A:D2.
   *
   * @param ctx - holds start and end columns, row offset, and maybe grid name.
   * @return RangeNode.
   */
  visitColumnWiseWithRowOffsetLastBiRange(ctx: any): Node {
    let firstColumn = AlphaUtils.columnToInt(ctx.firstColumn.text.toUpperCase());
    let secondColumn = AlphaUtils.columnToInt(ctx.lastColumn.text.toUpperCase());
    if (Compare.numberComparison(firstColumn, secondColumn) >= 1) {
      const swap = firstColumn;
      firstColumn = secondColumn;
      secondColumn = swap;
    }
    const builder = CellQuery.builder()
      .openRowsStartingAt(ctx.lastRow.text)
      .columnsBetween(firstColumn, secondColumn);
    if (isNotNull(ctx.grid)) {
      builder.setSheet(ctx.grid.getText());
    }
    return new RangeNode(builder.build());
  }

  /**
   * Eg: Grid!2:4 or 2:4.
   *
   * @param ctx - holds start and end rows, maybe a grid name.
   * @return RangeNode
   */
  visitRowWiseBiRange(ctx: any): Node {
    let firstRow = AlphaUtils.rowToInt(ctx.firstRow.text);
    let secondRow = AlphaUtils.rowToInt(ctx.lastRow.text);
    if (Compare.numberComparison(firstRow, secondRow) >= 1) {
      const swap = firstRow;
      firstRow = secondRow;
      secondRow = swap;
    }
    const builder = CellQuery.builder()
      .openColumnsStartingAtZero()
      .rowsBetween(firstRow, secondRow);
    if (isNotNull(ctx.grid)) {
      builder.setSheet(ctx.grid.getText());
    }
    return new RangeNode(builder.build());
  }

  /**
   * Eg: Grid!A2:4 or A2:4.
   *
   * @param ctx - holds start end end rows, and a column offset, and maybe a grid name.
   * @return RangeNode
   */
  visitRowWiseWithColumnOffsetFirstBiRange(ctx: any): Node {
    let firstRow = AlphaUtils.rowToInt(ctx.firstRow.text);
    let secondRow = AlphaUtils.rowToInt(ctx.lastRow.text);
    if (Compare.numberComparison(firstRow, secondRow) >= 1) {
      const swap = firstRow;
      firstRow = secondRow;
      secondRow = swap;
    }
    const builder = CellQuery.builder()
      .openColumnsStartingAt(ctx.firstColumn.text.toUpperCase())
      .rowsBetween(firstRow, secondRow);
    if (isNotNull(ctx.grid)) {
      builder.setSheet(ctx.grid.getText());
    }
    return new RangeNode(builder.build());
  }

  /**
   * Eg: Grid!2:D4 or 2:D4.
   *
   * @param ctx - holds start and end row, column offset, and maybe a grid name.
   * @return RangeNode
   */
  visitRowWiseWithColumnOffsetLastBiRange(ctx: any): Node {
    let firstRow = AlphaUtils.rowToInt(ctx.firstRow.text);
    let secondRow = AlphaUtils.rowToInt(ctx.lastRow.text);
    if (Compare.numberComparison(firstRow, secondRow) >= 1) {
      const swap = firstRow;
      firstRow = secondRow;
      secondRow = swap;
    }
    const builder = CellQuery.builder()
      .openColumnsStartingAt(ctx.lastColumn.text.toUpperCase())
      .rowsBetween(firstRow, secondRow);
    if (isNotNull(ctx.grid)) {
      builder.setSheet(ctx.grid.getText());
    }
    return new RangeNode(builder.build());
  }

  /**
   * Recursively visit children.
   * @param ctx - context node containing any and all children.
   */
  visitChildren(ctx: any) {
    if (!ctx) {
      return;
    }

    if (ctx.children && ctx.children.length > 0) {
      let result = this.defaultResult();
      for (let i = 0; i < ctx.children.length; i++) {
        const childResult = ctx.children[i].accept(this);
        result = this.aggregateResult(result, childResult);
      }
      return result;
    }
  }

  visit(tree: any) {
    return tree.accept(this);
  }

  protected aggregateResult(aggregate: Node, nextResult: Node): Node {
    return nextResult;
  }

  protected defaultResult(): Node {
    return undefined;
  }
}
