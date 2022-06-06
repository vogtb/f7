package io.protobase.f7.spreadsheet;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.protobase.f7.antlr.F7Lexer;
import io.protobase.f7.antlr.F7Parser;
import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.errors.F7ExceptionName;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.NameException;
import io.protobase.f7.errors.ParseException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.formulas.FormulaCaller;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.BinaryOperationNode;
import io.protobase.f7.models.CellQuery;
import io.protobase.f7.models.ColumnRowKey;
import io.protobase.f7.models.ErrorNode;
import io.protobase.f7.models.FormulaNode;
import io.protobase.f7.models.Grid;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.models.ListNode;
import io.protobase.f7.models.LogicalNode;
import io.protobase.f7.models.Node;
import io.protobase.f7.models.NumberNode;
import io.protobase.f7.models.RangeNode;
import io.protobase.f7.models.RangeQueryNode;
import io.protobase.f7.models.TextNode;
import io.protobase.f7.models.UnaryMinusOperationNode;
import io.protobase.f7.models.UnaryPercentOperationNode;
import io.protobase.f7.models.UnaryPlusOperationNode;
import io.protobase.f7.models.VariableNode;
import io.protobase.f7.transpiler.TranspilationVisitor;
import io.protobase.f7.utils.Converters;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class F7CodeExecutor {
  /**
   * Default variables. Basically should only be TRUE and FALSE, but could be any Node depending on what we're doing
   * with the executor.
   */
  private static final ImmutableMap<String, Node> DEFAULT_VARIABLES = ImmutableMap.of(
      "TRUE", LogicalNode.TRUE,
      "FALSE", LogicalNode.FALSE
  );
  /**
   * Collateral lookup is how we access values in a grid relative to the cell that contains the code we're currently
   * running.
   */
  private BiFunction<GridColumnRowKey, Object, Object> collateralLookup;
  /**
   * Look up values from the spreadsheet.
   */
  private Function<Object, Object> lookup;
  /**
   * All bound formulas.
   */
  private FormulaCaller formulaCaller;
  /**
   * Variables accessible.
   */
  private Map<String, Node> variables;
  /**
   * The key where this cell is running.
   */
  private GridColumnRowKey origin;
  /**
   * Depth of formula.
   */
  private Integer depth = 0;

  public F7CodeExecutor(Map<String, Node> variables, Function<Object, Object> lookup,
      BiFunction<GridColumnRowKey, Object, Object> collateralLookup, FormulaCaller formulaCaller) {
    this.variables = variables;
    this.lookup = lookup;
    this.collateralLookup = collateralLookup;
    this.formulaCaller = formulaCaller;

    ImmutableMap.Builder<String, Node> variablesBuilder = ImmutableMap.builder();
    variablesBuilder.putAll(variables);
    variablesBuilder.putAll(DEFAULT_VARIABLES);
    this.variables = variablesBuilder.build();
  }

  /**
   * Utility to convert a string input to tokens that the parser can read.
   *
   * @param input - F7 code.
   * @return token stream.
   * @throws IOException - if there's a problem reading the stream.
   */
  private static CommonTokenStream stringToTokens(String input) throws IOException {
    return new CommonTokenStream(new F7Lexer(CharStreams.fromStream(
        new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8)));
  }

  /**
   * Some raw values are cast as types. Right now:
   * 1) All Strings that are error literals are cast as errors.
   *
   * @param rawValue - raw value object.
   * @return cast value or unchanged raw value.
   */
  private static Object rawValueOverrides(Object rawValue) {
    if (rawValue instanceof String) {
      String rawValueString = Converters.castAsString(rawValue);
      F7ExceptionName possibleErrorLiteralString = F7ExceptionName.fromString(rawValueString);
      if (Objects.nonNull(possibleErrorLiteralString)) {
        return F7Exception.fromString(rawValueString);
      }
    }
    return rawValue;
  }

  /**
   * Execute the given code with an origin.
   *
   * @param origin - the cell key where this code is running.
   * @param code   - to execute.
   * @return - computed value.
   */
  public Object execute(GridColumnRowKey origin, String code) {
    this.origin = origin;
    if (!code.startsWith("=")) {
      return rawValueOverrides(code);
    }
    String formulaCode = code.substring(1);
    return visit(parse(formulaCode));
  }

  /**
   * Visit error node by returning the error value it holds.
   *
   * @param node - error node.
   * @return error value.
   */
  private Object visitError(ErrorNode node) {
    return node.getValue();
  }

  /**
   * Visit number node by returning the number value it holds.
   *
   * @param node - number node.
   * @return number value.
   */
  private Object visitNumber(NumberNode node) {
    return node.getValue();
  }

  /**
   * Visit logical node by returning the boolean value it holds.
   *
   * @param node - logical node.
   * @return - boolean/logical value.
   */
  private Object visitLogical(LogicalNode node) {
    return node.getValue();
  }

  /**
   * Visit text node by returning the text value it holds.
   *
   * @param node - text node.
   * @return text value.
   */
  private Object visitText(TextNode node) {
    return node.getValue();
  }

  /**
   * Visit and execute a binary operation by visiting the left and right nodes - in that order -  and then applying the
   * operator.
   *
   * @param node- binary operation node.
   * @return value or error
   */
  private Object visitBinaryOperation(BinaryOperationNode node) {
    Object left = visit(node.getLeft());
    Object right = visit(node.getRight());
    switch (node.getOperator()) {
      case "+":
        return formulaCaller.call(origin, FormulaName.ADD, left, right);
      case "-":
        return formulaCaller.call(origin, FormulaName.MINUS, left, right);
      case "*":
        return formulaCaller.call(origin, FormulaName.MULTIPLY, left, right);
      case "/":
        return formulaCaller.call(origin, FormulaName.DIVIDE, left, right);
      case "^":
        return formulaCaller.call(origin, FormulaName.POW, left, right);
      case "&":
        return formulaCaller.call(origin, FormulaName.CONCAT, left, right);
      case "=":
        return formulaCaller.call(origin, FormulaName.EQ, left, right);
      case "<>":
        return formulaCaller.call(origin, FormulaName.NE, left, right);
      case "<":
        return formulaCaller.call(origin, FormulaName.LT, left, right);
      case "<=":
        return formulaCaller.call(origin, FormulaName.LTE, left, right);
      case ">":
        return formulaCaller.call(origin, FormulaName.GT, left, right);
      case ">=":
        return formulaCaller.call(origin, FormulaName.GTE, left, right);
      default:
        throw new UnsupportedOperationException(String.format("Binary operation %s is not supported.",
            node.getOperator()));
    }
  }

  /**
   * Visit and execute a unary operation by visiting the operand, and then applying the unary operator.
   *
   * @param node - unary minus node
   * @return value or error.
   */
  private Object visitUnaryMinusOperation(UnaryMinusOperationNode node) {
    Object value = visit(node.getOperand());
    return formulaCaller.call(origin, FormulaName.UMINUS, value);
  }

  /**
   * Visit and execute a unary operation by visiting the operand, and then applying the unary operator.
   *
   * @param node - unary plus node
   * @return value or error.
   */
  private Object visitUnaryPlusOperation(UnaryPlusOperationNode node) {
    Object value = visit(node.getOperand());
    return formulaCaller.call(origin, FormulaName.UPLUS, value);
  }

  /**
   * Visit and execute a unary percentage operation by visiting the operand and then applying the unary operator.
   *
   * @param node - unary percent node
   * @return value or error.
   */
  private Object visitUnaryPercentOperation(UnaryPercentOperationNode node) {
    Object value = Converters.first(visit(node.getOperand()));
    return formulaCaller.call(null, FormulaName.UNARY_PERCENT, value);
  }

  /**
   * Visit any node. Pass-through to typed visitors.
   *
   * @param node of any type.
   * @return value after execution.
   */
  private Object visit(Node node) {
    depth++;
    Object returnObject = new ParseException("Execution error.");
    if (node instanceof FormulaNode) {
      returnObject = visitFormula(((FormulaNode) node));
    }
    if (node instanceof NumberNode) {
      returnObject = visitNumber(((NumberNode) node));
    }
    if (node instanceof LogicalNode) {
      returnObject = visitLogical(((LogicalNode) node));
    }
    if (node instanceof ListNode) {
      returnObject = visitList(((ListNode) node));
    }
    if (node instanceof ErrorNode) {
      returnObject = visitError(((ErrorNode) node));
    }
    if (node instanceof RangeQueryNode) {
      returnObject = visitRangeQuery(((RangeQueryNode) node));
    }
    if (node instanceof TextNode) {
      returnObject = visitText(((TextNode) node));
    }
    if (node instanceof VariableNode) {
      returnObject = visitVariable(((VariableNode) node));
    }
    if (node instanceof BinaryOperationNode) {
      returnObject = visitBinaryOperation(((BinaryOperationNode) node));
    }
    if (node instanceof UnaryMinusOperationNode) {
      returnObject = visitUnaryMinusOperation(((UnaryMinusOperationNode) node));
    }
    if (node instanceof UnaryPlusOperationNode) {
      returnObject = visitUnaryPlusOperation(((UnaryPlusOperationNode) node));
    }
    if (node instanceof UnaryPercentOperationNode) {
      returnObject = visitUnaryPercentOperation(((UnaryPercentOperationNode) node));
    }
    if (node instanceof RangeNode) {
      returnObject = visitRange((RangeNode) node);
    }
    depth--;
    if (depth == 0 && returnObject instanceof CellQuery) {
      return this.collateralLookup.apply(origin, returnObject);
    }
    return returnObject;
  }

  /**
   * Visit a variable by accessing whatever node is stored under that variable name.
   *
   * @param node - to visit.
   * @return value
   */
  private Object visitVariable(VariableNode node) {
    String variableNameUpperCase = node.getName().toUpperCase();
    if (variables.containsKey(variableNameUpperCase)) {
      Node variableValueNode = variables.get(variableNameUpperCase);
      if (variableValueNode instanceof RangeNode) {
        return Converters.castAsDataGrid(lookup.apply(((RangeNode) variableValueNode).getCellQuery()));
      }
      return visit(variableValueNode);
    }
    return new NameException(String.format("Unknown range name: '%s'.", variableNameUpperCase));
  }

  /**
   * Visit a range query by iterating over all values in the range, combining them into a single query. All inner node
   * values must be a query as well or else it fails with #N/A error.
   *
   * @param node - node to build single query from.
   * @return built query.
   */
  private Object visitRangeQuery(RangeQueryNode node) {
    CellQuery.Builder query = CellQuery.builder();
    for (Node inner : node.getNodes()) {
      if (inner instanceof RangeNode) {
        try {
          CellQuery innerQuery = ((RangeNode) inner).getCellQuery();
          if (!innerQuery.getGrid().isPresent()) {
            innerQuery = CellQuery.builder(innerQuery).grid(origin.getGridIndex()).build();
          }
          query = query.expand(innerQuery);
        } catch (ValueException error) {
          return error;
        }
      } else {
        return new NAException("Argument must be a range.");
      }
    }
    return query.build();
  }

  /**
   * Visit and execute a formula node by visiting all arguments for the node, and then calling the formula.
   *
   * @param node - formula node.
   * @return value or error.
   */
  private Object visitFormula(FormulaNode node) {
    ImmutableList.Builder<Object> inProgress = ImmutableList.builder();
    for (Node value : node.getArguments()) {
      inProgress.add(visit(value));
    }
    FormulaName formulaName;
    // Try to convert the name of the formula to a valid enum, but if it fails, return NameException.
    try {
      formulaName = FormulaName.valueOf(node.getName().toUpperCase());
    } catch (IllegalArgumentException exception) {
      return new NameException(String.format("Unknown formula: '%s'.", node.getName()));
    }
    return formulaCaller.call(null, formulaName, inProgress.build().toArray(new Object[0]));
  }

  /**
   * Visit and execute a list node by visiting all values in the list, and returning the grid.
   *
   * @param node - list node.
   * @return value or error.
   */
  private Object visitList(ListNode node) {
    Iterator<ColumnRowKey> iterator = node.getGrid().indexIterator();
    Grid<Object> returnGrid = new Grid<>(0, 0);
    while (iterator.hasNext()) {
      ColumnRowKey key = iterator.next();
      Node gridChildNode = node.getGrid().get(key);
      if (Objects.nonNull(gridChildNode)) {
        Object value = visit(gridChildNode);
        if (value instanceof Grid) {
          Grid<Object> valueGrid = Converters.castAsDataGrid(value);
          if (returnGrid.getRowSize() > valueGrid.getRowSize() && returnGrid.getRowSize() != 0) {
            return new ValueException("Encountered a grid literal that was missing values for one or more rows or columns.");
          }
          returnGrid.addGridToRight(valueGrid);
        } else if (value instanceof CellQuery) {
          Grid<Object> foundGrid = Converters.castAsDataGrid(lookup.apply(value));
          if (returnGrid.getRowSize() > foundGrid.getRowSize() && returnGrid.getRowSize() != 0) {
            return new ValueException("Encountered a grid literal that was missing values for one or more rows or columns.");
          }
          returnGrid.addGridToRight(foundGrid);
        } else {
          returnGrid.set(key.getColumnIndex(), key.getRowIndex(), value);
        }
      }
    }
    if (!returnGrid.isComplete()) {
      return new ValueException("Encountered a grid literal that was missing values for one or more rows or columns.");
    }
    return returnGrid;
  }

  /**
   * Visit a reference.
   *
   * @param node - range node.
   * @return value resolved from reference
   */
  private Object visitRange(RangeNode node) {
    CellQuery cellQuery = node.getCellQuery();
    if (cellQuery.getGrid().isPresent()) {
      return node.getCellQuery();
    }
    return CellQuery.builder(cellQuery).grid(origin.getGridIndex()).build();
  }

  /**
   * Parse a string value to a node for execution.
   *
   * @param value - formula string.
   * @return node
   */
  public Node parse(String value) {
    try {
      CommonTokenStream tokens = stringToTokens(value);
      try {
        F7Parser parser = new F7Parser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new ParseErrorListener());
        return new TranspilationVisitor().visit(parser.start().block());
      } catch (ParseCancellationException parseException) {
        return new ErrorNode(new ParseException("Parse error"));
      } catch (F7Exception f7Exception) {
        return new ErrorNode(f7Exception);
      }
    } catch (IOException io) {
      return new ErrorNode(new ParseException("Parse error"));
    }
  }
}
