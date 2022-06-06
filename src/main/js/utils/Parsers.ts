import { InvalidArgumentError } from "../common/errors/InvalidArgumentError";
import { TranspilationVisitor } from "../execution/TranspilationVisitor";
import { CellQuery } from "../models/nodes/CellQuery";
import { ErrorNode } from "../models/nodes/ErrorNode";
import { Node } from "../models/nodes/Node";
import { NodeType } from "../models/nodes/NodeType";
import { RangeNode } from "../models/nodes/RangeNode";
import { Ref } from "../spreadsheet/Ref";
import { AlphaUtils } from "./AlphaUtils";
import * as antlr4 from "antlr4";
import { F7Lexer } from "../antlr/F7Lexer";
import { F7Parser } from "../antlr/F7Parser";

export class Parsers {
  /**
   * Parse a range to node for execution.
   * Eg: Sheet1!A1:B33
   *
   * @param range - range string
   * @return range node.
   */
  static parseNamedRange(range: string): Node {
    try {
      const chars = new antlr4.InputStream(range);
      const lexer: any = new F7Lexer(chars);
      const tokens = new antlr4.CommonTokenStream(lexer);
      const parser = new F7Parser(tokens) as any;
      parser.buildParseTrees = true;
      // This is the key line that is different from what tree we use when parsing normal F7 code.
      const tree = parser.range();
      return new TranspilationVisitor().visit(tree);
    } catch (e) {
      return new ErrorNode(e);
    }
  }

  /**
   * Parse a named range query in string format to a CellQuery, throwing an invalid argument error
   * if we can't parse it.
   * @param range to parse.
   */
  static parseNamedRangeToCellQuery(range: string): CellQuery {
    const node = Parsers.parseNamedRange(range);
    if (node.type === NodeType.Range) {
      const rangeNode = <RangeNode>node;
      return rangeNode.query;
    }
    throw new InvalidArgumentError(`Invalid range: ${range}`);
  }

  /**
   * Parse formula code to a node for execution.
   * Eg: SUM({1, 2, 3}, 888.22, "99.1")
   *
   * @param input - formula string.
   * @return node
   */
  static parseFormulaCode(input: string): Node {
    try {
      const chars = new antlr4.InputStream(input);
      const lexer: any = new F7Lexer(chars);
      const tokens = new antlr4.CommonTokenStream(lexer);
      const parser = new F7Parser(tokens) as any;
      parser.buildParseTrees = true;
      const tree = parser.start().block();
      return new TranspilationVisitor().visit(tree);
    } catch (e) {
      return new ErrorNode(e);
    }
  }

  /**
   * Parse a reference pair, which is just two relative cell references separated by a semicolon.
   * Eg:  A1:A2
   * @param ref string to parse.
   */
  static parseReferencePair(ref: string): Ref {
    const REF_PATTERN = /^(\D+)(\d+):(\D+)(\d+)$/;
    const matches = ref.match(REF_PATTERN);
    if (matches) {
      return {
        startColumnIndex: AlphaUtils.columnToInt(matches[1]),
        startRowIndex: AlphaUtils.rowToInt(matches[2]),
        endColumnIndex: AlphaUtils.columnToInt(matches[3]),
        endRowIndex: AlphaUtils.rowToInt(matches[4]),
      };
    }
    throw new InvalidArgumentError(`Invalid ref: ${ref}`);
  }
}
