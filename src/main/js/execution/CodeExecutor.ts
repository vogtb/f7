import { f7ExceptionFromString } from "../errors/ExceptionHelpers";
import { AllF7ExceptionNames, F7ExceptionName } from "../errors/F7ExceptionName";
import { NAException } from "../errors/NAException";
import { NameException } from "../errors/NameException";
import { ParseException } from "../errors/ParseException";
import { ValueException } from "../errors/ValueException";
import { FormulaCaller } from "../formulas/FormulaCaller";
import { FormulaName } from "../formulas/FormulaName";
import { Grid } from "../models/common/Grid";
import { SheetColumnRowKey } from "../models/common/SheetColumnRowKey";
import { CollateralLookupFunction, Complex, LookupFunction } from "../models/common/Types";
import { BinaryOperationNode } from "../models/nodes/BinaryOperationNode";
import { CellQuery } from "../models/nodes/CellQuery";
import { ErrorNode } from "../models/nodes/ErrorNode";
import { FormulaNode } from "../models/nodes/FormulaNode";
import { ListNode } from "../models/nodes/ListNode";
import { LogicalNode } from "../models/nodes/LogicalNode";
import { MultiRangeNode } from "../models/nodes/MultiRangeNode";
import { Node } from "../models/nodes/Node";
import { NodeType } from "../models/nodes/NodeType";
import { NumberNode } from "../models/nodes/NumberNode";
import { RangeNode } from "../models/nodes/RangeNode";
import { TextNode } from "../models/nodes/TextNode";
import { UnaryMinusOperationNode } from "../models/nodes/UnaryMinusOperationNode";
import { UnaryPercentOperationNode } from "../models/nodes/UnaryPercentOperationNode";
import { UnaryPlusOperationNode } from "../models/nodes/UnaryPlusOperationNode";
import { VariableNode } from "../models/nodes/VariableNode";
import { CellObject } from "../spreadsheet/CellObject";
import { Converters } from "../utils/Converters";
import { Parsers } from "../utils/Parsers";
import { isNotNull, isNotUndefined, isNull, isUndefined } from "../utils/Other";

export class CodeExecutor {
  /**
   * Look up values from the spreadsheet.
   */
  readonly lookup: LookupFunction;
  /**
   * Collateral lookup is how we access values in a grid relative to the cell that contains the code we're currently
   * running.
   */
  readonly collateralLookup: CollateralLookupFunction;
  /**
   * Collateral lookup is how we access values in a grid relative to the cell that contains the code we're currently
   * running.
   */
  private formulaCaller: FormulaCaller;
  /**
   * Variables accessible.
   */
  private readonly variables: { [index: string]: Node };
  /**
   * Origin of this code.
   */
  private origin: SheetColumnRowKey;
  /**
   * Depth of formula.
   */
  private depth = 0;

  constructor(
    variables: { [index: string]: Node },
    lookup: LookupFunction,
    collateralLookup: CollateralLookupFunction,
    formulaCaller: FormulaCaller
  ) {
    this.variables = variables;
    this.lookup = lookup;
    this.collateralLookup = collateralLookup;
    this.formulaCaller = formulaCaller;
  }

  /**
   * Initialize a simple CodeExecutor, with lookups that pass through original value.
   */
  public static simple(): CodeExecutor {
    const LOOKUP: LookupFunction = (value) => value;
    const COLLATERAL_LOOKUP: CollateralLookupFunction = (origin, value) => value;
    return new CodeExecutor(
      {},
      LOOKUP,
      COLLATERAL_LOOKUP,
      new FormulaCaller(LOOKUP, COLLATERAL_LOOKUP)
    );
  }

  /**
   * Some raw values are cast as types. Right now:
   * 1) All Strings that are error literals are cast as errors.
   *
   * @param rawValue - raw value object.
   * @return cast value or unchanged raw value.
   */
  private static rawOverrides(rawValue: any) {
    if (typeof rawValue === "string") {
      const rawString = Converters.castAsString(rawValue);
      if (AllF7ExceptionNames.has(rawString as F7ExceptionName)) {
        return f7ExceptionFromString(rawString);
      }
    }
    return rawValue;
  }

  /**
   * Visit primitive node by returning the primitive value it holds.
   *
   * @param node - node.
   * @return primitive value.
   */
  private static visitPrimitiveNode(node: NumberNode | TextNode | ErrorNode | LogicalNode) {
    return node.value;
  }

  /**
   * Execute the given code with an origin.
   *
   * @param origin - the cell key where this code is running.
   * @param cell   - to execute.
   * @return - computed value.
   */
  execute(origin: SheetColumnRowKey, cell: CellObject): Complex {
    this.origin = origin;
    if (cell.f) {
      const start: Node = Parsers.parseFormulaCode(cell.f);
      return this.visit(start);
    }
    return CodeExecutor.rawOverrides(cell.f);
  }

  /**
   * Visit any node. Pass-through to typed visitors.
   *
   * @param node of any type.
   * @return value after execution.
   */
  private visit(node: Node): Complex {
    this.depth++;
    let returnObject: any = new ParseException("Execution error.");
    if (
      node.type == NodeType.Number ||
      node.type == NodeType.Text ||
      node.type == NodeType.Error ||
      node.type == NodeType.Logical
    ) {
      returnObject = CodeExecutor.visitPrimitiveNode(node as NumberNode);
    } else if (node.type == NodeType.UnaryMinusOperation) {
      returnObject = this.visitUnaryMinusOperation(node as UnaryMinusOperationNode);
    } else if (node.type == NodeType.UnaryPlusOperation) {
      returnObject = this.visitUnaryPlusOperation(node as UnaryPlusOperationNode);
    } else if (node.type == NodeType.UnaryPercentOperation) {
      returnObject = this.visitUnaryPercentOperation(node as UnaryPercentOperationNode);
    } else if (node.type == NodeType.BinaryOperation) {
      returnObject = this.visitBinaryOperation(node as BinaryOperationNode);
    } else if (node.type == NodeType.Formula) {
      returnObject = this.visitFormula(node as FormulaNode);
    } else if (node.type == NodeType.Variable) {
      returnObject = this.visitVariable(node as VariableNode);
    } else if (node.type == NodeType.List) {
      returnObject = this.visitList(node as ListNode);
    } else if (node.type == NodeType.Range) {
      returnObject = this.visitRange(node as RangeNode);
    } else if (node.type == NodeType.MultiRange) {
      returnObject = this.visitRangeQuery(node as MultiRangeNode);
    }
    this.depth--;
    if (this.depth == 0 && returnObject instanceof CellQuery) {
      return this.collateralLookup(this.origin, returnObject);
    }
    return returnObject;
  }

  /**
   * Visit a range query by iterating over all values in the range, combining them into a single query. All inner node
   * values must be a query as well or else it fails with #N/A error.
   *
   * @param node - node to build single query from.
   * @return built query.
   */
  private visitRangeQuery(node: MultiRangeNode) {
    let query = CellQuery.builder();
    for (const inner of node.nodes) {
      if (inner.type === NodeType.Range) {
        try {
          let innerQuery = (inner as RangeNode).query;
          if (
            isNull(innerQuery.getFormattedSheetName()) ||
            isUndefined(innerQuery.getFormattedSheetName())
          ) {
            innerQuery = CellQuery.builder(innerQuery).setSheet(this.origin.sheet).build();
          }
          query = query.expand(innerQuery);
        } catch (error) {
          return error;
        }
      } else {
        return new NAException("Argument must be a range.");
      }
    }
    return query.build();
  }

  /**
   * Visit a reference. When the structure depth is 0, it means that the
   *
   * @param node - range node.
   * @return value resolved from reference
   */
  private visitRange(node: RangeNode) {
    const cellQuery = node.query;
    if (
      isNotNull(cellQuery.getFormattedSheetName()) &&
      isNotUndefined(cellQuery.getFormattedSheetName())
    ) {
      return node.query;
    }
    return CellQuery.builder(cellQuery).setSheet(this.origin.sheet).build();
  }

  /**
   * Visit and execute a list node by visiting all values in the list, and returning the grid.
   *
   * @param node - list node.
   * @return value or error.
   */
  private visitList(node: ListNode) {
    const returnGrid = new Grid(0, 0);
    for (let row = 0; row < node.grid.getRows(); row++) {
      for (let column = 0; column < node.grid.getColumns(); column++) {
        const gridChildNode = node.grid.get(column, row);
        if (isNotNull(gridChildNode) && isNotUndefined(gridChildNode)) {
          const value = this.visit(gridChildNode);
          if (value instanceof Grid) {
            const valueGrid = Converters.castAsGrid(value);
            if (returnGrid.getRows() > valueGrid.getRows() && returnGrid.getRows() != 0) {
              return new ValueException(
                "Encountered a grid literal that was missing values for one or more rows or columns."
              );
            }
            returnGrid.addGridToRight(valueGrid);
          } else if (value instanceof CellQuery) {
            const foundGrid = Converters.castAsGrid(this.lookup(value));
            if (returnGrid.getRows() > foundGrid.getRows() && returnGrid.getRows() != 0) {
              return new ValueException(
                "Encountered a grid literal that was missing values for one or more rows or columns."
              );
            }
            returnGrid.addGridToRight(foundGrid);
          } else {
            returnGrid.set(column, row, value);
          }
        }
      }
    }
    if (returnGrid.hasDimensionsButIsAllUndefined()) {
      return new ValueException(
        "Encountered a grid literal that was missing values for one or more rows or columns."
      );
    }
    return returnGrid;
  }

  /**
   * Visit a variable by accessing whatever node is stored under that variable name.
   *
   * @param node - to visit.
   * @return value
   */
  private visitVariable(node: VariableNode) {
    const name = node.name;
    if (name in this.variables) {
      const node: Node = this.variables[name];
      return this.visit(node);
    }
    return new NameException(`Unknown range name: '${name}'.`);
  }

  /**
   * Visit and execute a formula node by visiting all arguments for the node, and then calling the formula.
   *
   * @param node - formula node.
   * @return value or error.
   */
  private visitFormula(node: FormulaNode) {
    const formulaArguments: Array<any> = [];
    for (const argument of node.values) {
      formulaArguments.push(this.visit(argument));
    }
    let name: FormulaName = null;
    if (this.formulaCaller.hasFormulaBound(node.name)) {
      name = node.name as FormulaName;
    } else {
      return new NameException(`Unknown formula '${node.name}'`);
    }
    return this.formulaCaller.call(this.origin, name, ...formulaArguments);
  }

  /**
   * Visit and execute a binary operation by visiting the left and right nodes - in that order -  and then applying the
   * operator.
   *
   * @param node- binary operation node.
   * @return value or error
   */
  private visitBinaryOperation(node: BinaryOperationNode) {
    const left: any = this.visit(node.left);
    const right: any = this.visit(node.right);
    const operator: string = node.operator;
    switch (operator) {
      case "+":
        return this.formulaCaller.call(this.origin, FormulaName.ADD, left, right);
      case "-":
        return this.formulaCaller.call(this.origin, FormulaName.MINUS, left, right);
      case "*":
        return this.formulaCaller.call(this.origin, FormulaName.MULTIPLY, left, right);
      case "/":
        return this.formulaCaller.call(this.origin, FormulaName.DIVIDE, left, right);
      case "^":
        return this.formulaCaller.call(this.origin, FormulaName.POW, left, right);
      case "&":
        return this.formulaCaller.call(this.origin, FormulaName.CONCAT, left, right);
      case "=":
        return this.formulaCaller.call(this.origin, FormulaName.EQ, left, right);
      case "<>":
        return this.formulaCaller.call(this.origin, FormulaName.NE, left, right);
      case ">":
        return this.formulaCaller.call(this.origin, FormulaName.GT, left, right);
      case ">=":
        return this.formulaCaller.call(this.origin, FormulaName.GTE, left, right);
      case "<":
        return this.formulaCaller.call(this.origin, FormulaName.LT, left, right);
      case "<=":
        return this.formulaCaller.call(this.origin, FormulaName.LTE, left, right);
    }
  }

  /**
   * Visit and execute a unary operation by visiting the operand, and then applying the unary operator.
   *
   * @param node - unary minus node
   * @return value or error.
   */
  private visitUnaryMinusOperation(node: UnaryMinusOperationNode) {
    return this.formulaCaller.call(this.origin, FormulaName.UMINUS, this.visit(node.operand));
  }

  /**
   * Visit and execute a unary operation by visiting the operand, and then applying the unary operator.
   *
   * @param node - unary plus node
   * @return value or error.
   */
  private visitUnaryPlusOperation(node: UnaryPlusOperationNode) {
    return this.formulaCaller.call(this.origin, FormulaName.UPLUS, this.visit(node.operand));
  }

  /**
   * Visit and execute a unary percentage operation by visiting the operand and then applying the unary operator.
   *
   * @param node - unary percent node
   * @return value or error.
   */
  private visitUnaryPercentOperation(node: UnaryPercentOperationNode) {
    return this.formulaCaller.call(
      this.origin,
      FormulaName.UNARY_PERCENT,
      this.visit(node.operand)
    );
  }
}
