import { assert } from "chai";
import { it, describe } from "../testutils/TestUtils";
import { DivException } from "../../../main/js/errors/DivException";
import { NAException } from "../../../main/js/errors/NAException";
import { NameException } from "../../../main/js/errors/NameException";
import { NullException } from "../../../main/js/errors/NullException";
import { NumException } from "../../../main/js/errors/NumException";
import { ParseException } from "../../../main/js/errors/ParseException";
import { RefException } from "../../../main/js/errors/RefException";
import { ValueException } from "../../../main/js/errors/ValueException";
import { TranspilationVisitor } from "../../../main/js/execution/TranspilationVisitor";
import { FormulaName } from "../../../main/js/formulas/FormulaName";
import { BinaryOperationNode } from "../../../main/js/models/nodes/BinaryOperationNode";
import { CellQuery } from "../../../main/js/models/nodes/CellQuery";
import { ErrorNode } from "../../../main/js/models/nodes/ErrorNode";
import { FormulaNode } from "../../../main/js/models/nodes/FormulaNode";
import { LogicalNode } from "../../../main/js/models/nodes/LogicalNode";
import { MultiRangeNode } from "../../../main/js/models/nodes/MultiRangeNode";
import { Node } from "../../../main/js/models/nodes/Node";
import { NumberNode } from "../../../main/js/models/nodes/NumberNode";
import { RangeNode } from "../../../main/js/models/nodes/RangeNode";
import { TextNode } from "../../../main/js/models/nodes/TextNode";
import { UnaryMinusOperationNode } from "../../../main/js/models/nodes/UnaryMinusOperationNode";
import { UnaryPercentOperationNode } from "../../../main/js/models/nodes/UnaryPercentOperationNode";
import { UnaryPlusOperationNode } from "../../../main/js/models/nodes/UnaryPlusOperationNode";
import { VariableNode } from "../../../main/js/models/nodes/VariableNode";
import * as antlr4 from "antlr4";
import { F7Lexer } from "../../../main/js/antlr/F7Lexer";
import { F7Parser } from "../../../main/js/antlr/F7Parser";

describe("TranspilationVisitor", function () {
  it("should transpile numbers", function () {
    run("10.0e2", new NumberNode(10.0e2));
    run("10.0e-2", new NumberNode(10.0e-2));
    run("81739821", new NumberNode(81739821));
    run("1.00000000001", new NumberNode(1.00000000001));
    run("199", new NumberNode(199));
    run("201", new NumberNode(201));
    run("9187312.222", new NumberNode(9187312.222));
  });

  it("should transpile text", function () {
    run('"Hello Friend!"', new TextNode("Hello Friend!"));
    run('""', new TextNode(""));
  });

  it("should transpile formulas without arguments", function () {
    run("RAND()", new FormulaNode(FormulaName.RAND.toString()));
    run("TRUE()", new FormulaNode(FormulaName.TRUE.toString()));
    run("FALSE()", new FormulaNode(FormulaName.FALSE.toString()));
  });

  it("should transpile formulas with arguments", function () {
    run(
      "POW(2, 6)",
      new FormulaNode(FormulaName.POW.toString(), [new NumberNode(2), new NumberNode(6)])
    );
    run("SUM(1.829173)", new FormulaNode(FormulaName.SUM.toString(), [new NumberNode(1.829173)]));
  });

  it("should transpile variables", function () {
    run(
      "My_Special_Variable_That_Might_Exist",
      new VariableNode("My_Special_Variable_That_Might_Exist")
    );
    run(
      "VARIABLE_THAT_IS_LONGER_THAN_TWO_HUNDRED_AND_FIFTY_FIVE_CHARACTERS_OLD_MCDONALD_HAD_A_FARM_OLD_MCDONALD_HAD_A_FARM_OLD_MCDONALD_HAD_A_FARM_OLD_MCDONALD_HAD_A_FARM_OLD_MCDONALD_HAD_A_FARM_OLD_MCDONALD_HAD_A_FARM_OLD_MCDONALD_HAD_A_FARM_OLD_MCDONALD_HAD_A_FA",
      new ErrorNode(new ParseException())
    );
  });

  it("should transpile logical nodes", function () {
    run("TRUE", new LogicalNode(true));
    run("True", new LogicalNode(true));
    run("tRuE", new LogicalNode(true));
    run("FALSE", new LogicalNode(false));
    run("False", new LogicalNode(false));
    run("fAlSe", new LogicalNode(false));
    run("false", new LogicalNode(false));
  });

  it("should transpile errors", function () {
    run("#NUM!", new ErrorNode(new NumException()));
    run("#DIV/0!", new ErrorNode(new DivException()));
    run("#VALUE!", new ErrorNode(new ValueException()));
    run("#REF!", new ErrorNode(new RefException()));
    run("#NAME?", new ErrorNode(new NameException()));
    run("#NUM!", new ErrorNode(new NumException()));
    run("#NULL!", new ErrorNode(new NullException()));
    run("#N/A", new ErrorNode(new NAException()));
    run("#ERROR!", new ErrorNode(new ParseException()));
  });

  it("should transpile addition operations", function () {
    run("1 + 1", new BinaryOperationNode(new NumberNode(1), "+", new NumberNode(1)));
    run("1 + 0", new BinaryOperationNode(new NumberNode(1), "+", new NumberNode(0)));
    run("1 + 2.12121", new BinaryOperationNode(new NumberNode(1), "+", new NumberNode(2.12121)));
    run(
      "1e10 + 2.12121",
      new BinaryOperationNode(new NumberNode(1e10), "+", new NumberNode(2.12121))
    );
  });

  it("should transpile addition operations with error literals", function () {
    run(
      "3 + #VALUE!",
      new BinaryOperationNode(new NumberNode(3), "+", new ErrorNode(new ValueException()))
    );
    run(
      "3 + #DIV/0!",
      new BinaryOperationNode(new NumberNode(3), "+", new ErrorNode(new DivException()))
    );
    run(
      "3 + #NUM!",
      new BinaryOperationNode(new NumberNode(3), "+", new ErrorNode(new NumException()))
    );
    run(
      "3 + #NAME?",
      new BinaryOperationNode(new NumberNode(3), "+", new ErrorNode(new NameException()))
    );
    run(
      "3 + #NULL!",
      new BinaryOperationNode(new NumberNode(3), "+", new ErrorNode(new NullException()))
    );
    run(
      "3 + #N/A",
      new BinaryOperationNode(new NumberNode(3), "+", new ErrorNode(new NAException()))
    );
    run(
      "3 + #REF!",
      new BinaryOperationNode(new NumberNode(3), "+", new ErrorNode(new RefException()))
    );
    run(
      "3 + #ERROR!",
      new BinaryOperationNode(new NumberNode(3), "+", new ErrorNode(new ParseException()))
    );
  });

  it("should transpile subtraction operations", function () {
    run("1 - 1", new BinaryOperationNode(new NumberNode(1), "-", new NumberNode(1)));
    run("3 - 1", new BinaryOperationNode(new NumberNode(3), "-", new NumberNode(1)));
    run(
      "3.1e3 - 4.2e10",
      new BinaryOperationNode(new NumberNode(3.1e3), "-", new NumberNode(4.2e10))
    );
  });

  it("should transpile subtraction operations with error literals", function () {
    run(
      "3 - #VALUE!",
      new BinaryOperationNode(new NumberNode(3), "-", new ErrorNode(new ValueException()))
    );
    run(
      "3 - #DIV/0!",
      new BinaryOperationNode(new NumberNode(3), "-", new ErrorNode(new DivException()))
    );
    run(
      "3 - #NUM!",
      new BinaryOperationNode(new NumberNode(3), "-", new ErrorNode(new NumException()))
    );
    run(
      "3 - #NAME?",
      new BinaryOperationNode(new NumberNode(3), "-", new ErrorNode(new NameException()))
    );
    run(
      "3 - #NULL!",
      new BinaryOperationNode(new NumberNode(3), "-", new ErrorNode(new NullException()))
    );
    run(
      "3 - #N/A",
      new BinaryOperationNode(new NumberNode(3), "-", new ErrorNode(new NAException()))
    );
    run(
      "3 - #REF!",
      new BinaryOperationNode(new NumberNode(3), "-", new ErrorNode(new RefException()))
    );
    run(
      "3 - #ERROR!",
      new BinaryOperationNode(new NumberNode(3), "-", new ErrorNode(new ParseException()))
    );
  });

  it("should transpile multiplication operations", function () {
    run("3 * 4", new BinaryOperationNode(new NumberNode(3), "*", new NumberNode(4)));
    run("3 * 1", new BinaryOperationNode(new NumberNode(3), "*", new NumberNode(1)));
    run("3.1e3 * 2", new BinaryOperationNode(new NumberNode(3.1e3), "*", new NumberNode(2)));
    run("3.1e3 * 0", new BinaryOperationNode(new NumberNode(3.1e3), "*", new NumberNode(0)));
  });

  it("should transpile multiplication operations with error literals", function () {
    run(
      "3 * #VALUE!",
      new BinaryOperationNode(new NumberNode(3), "*", new ErrorNode(new ValueException()))
    );
    run(
      "3 * #DIV/0!",
      new BinaryOperationNode(new NumberNode(3), "*", new ErrorNode(new DivException()))
    );
    run(
      "3 * #NUM!",
      new BinaryOperationNode(new NumberNode(3), "*", new ErrorNode(new NumException()))
    );
    run(
      "3 * #NAME?",
      new BinaryOperationNode(new NumberNode(3), "*", new ErrorNode(new NameException()))
    );
    run(
      "3 * #NULL!",
      new BinaryOperationNode(new NumberNode(3), "*", new ErrorNode(new NullException()))
    );
    run(
      "3 * #N/A",
      new BinaryOperationNode(new NumberNode(3), "*", new ErrorNode(new NAException()))
    );
    run(
      "3 * #REF!",
      new BinaryOperationNode(new NumberNode(3), "*", new ErrorNode(new RefException()))
    );
    run(
      "3 * #ERROR!",
      new BinaryOperationNode(new NumberNode(3), "*", new ErrorNode(new ParseException()))
    );
  });

  it("should transpile division operations", function () {
    run("3 / 4", new BinaryOperationNode(new NumberNode(3), "/", new NumberNode(4)));
    run("4 / 3", new BinaryOperationNode(new NumberNode(4), "/", new NumberNode(3)));
    run("3e3 / 2", new BinaryOperationNode(new NumberNode(3e3), "/", new NumberNode(2)));
    run("10 / 0", new BinaryOperationNode(new NumberNode(10), "/", new NumberNode(0)));
  });

  it("should transpile division operations with error literals", function () {
    run(
      "3 / #VALUE!",
      new BinaryOperationNode(new NumberNode(3), "/", new ErrorNode(new ValueException()))
    );
    run(
      "3 / #DIV/0!",
      new BinaryOperationNode(new NumberNode(3), "/", new ErrorNode(new DivException()))
    );
    run(
      "3 / #NUM!",
      new BinaryOperationNode(new NumberNode(3), "/", new ErrorNode(new NumException()))
    );
    run(
      "3 / #NAME?",
      new BinaryOperationNode(new NumberNode(3), "/", new ErrorNode(new NameException()))
    );
    run(
      "3 / #NULL!",
      new BinaryOperationNode(new NumberNode(3), "/", new ErrorNode(new NullException()))
    );
    run(
      "3 / #N/A",
      new BinaryOperationNode(new NumberNode(3), "/", new ErrorNode(new NAException()))
    );
    run(
      "3 / #REF!",
      new BinaryOperationNode(new NumberNode(3), "/", new ErrorNode(new RefException()))
    );
    run(
      "3 / #ERROR!",
      new BinaryOperationNode(new NumberNode(3), "/", new ErrorNode(new ParseException()))
    );
  });

  it("should transpile exponent operations", function () {
    run(
      "-2^3",
      new BinaryOperationNode(
        new UnaryMinusOperationNode(new NumberNode(2)),
        "^",
        new NumberNode(3)
      )
    );
    run(
      "(-2)^3",
      new BinaryOperationNode(
        new UnaryMinusOperationNode(new NumberNode(2)),
        "^",
        new NumberNode(3)
      )
    );
    run(
      "-(2)^3",
      new BinaryOperationNode(
        new UnaryMinusOperationNode(new NumberNode(2)),
        "^",
        new NumberNode(3)
      )
    );
    run(
      "--(-2)^3",
      new BinaryOperationNode(
        new UnaryMinusOperationNode(
          new UnaryMinusOperationNode(new UnaryMinusOperationNode(new NumberNode(2)))
        ),
        "^",
        new NumberNode(3)
      )
    );
    run(
      "11 -2^3",
      new BinaryOperationNode(
        new NumberNode(11),
        "-",
        new BinaryOperationNode(new NumberNode(2), "^", new NumberNode(3))
      )
    );
    run(
      "(-2) ^ 3",
      new BinaryOperationNode(
        new UnaryMinusOperationNode(new NumberNode(2)),
        "^",
        new NumberNode(3)
      )
    );
    run(
      "(-3) ^ 2",
      new BinaryOperationNode(
        new UnaryMinusOperationNode(new NumberNode(3)),
        "^",
        new NumberNode(2)
      )
    );
  });

  it("should transpile concat operations", function () {
    run("1 & 1", new BinaryOperationNode(new NumberNode(1), "&", new NumberNode(1)));
    run('"One" & 0', new BinaryOperationNode(new TextNode("One"), "&", new NumberNode(0)));
  });

  it("should transpile concat operations with error literals", function () {
    run(
      "1 & #DIV/0!",
      new BinaryOperationNode(new NumberNode(1), "&", new ErrorNode(new DivException()))
    );
  });

  it("should transpile unary minus operations", function () {
    run("-22", new UnaryMinusOperationNode(new NumberNode(22)));
    run('-"Thing"', new UnaryMinusOperationNode(new TextNode("Thing")));
    run("--22", new UnaryMinusOperationNode(new UnaryMinusOperationNode(new NumberNode(22))));
    run(
      "---22",
      new UnaryMinusOperationNode(
        new UnaryMinusOperationNode(new UnaryMinusOperationNode(new NumberNode(22)))
      )
    );
    run(
      "-(2 * 4)",
      new UnaryMinusOperationNode(
        new BinaryOperationNode(new NumberNode(2), "*", new NumberNode(4))
      )
    );
  });

  it("should transpile unary percent operations", function () {
    run("88%", new UnaryPercentOperationNode(new NumberNode(88)));
    run(
      "(8 * 8)%",
      new UnaryPercentOperationNode(
        new BinaryOperationNode(new NumberNode(8), "*", new NumberNode(8))
      )
    );
  });

  it("should transpile to error when unary percent operation contains more than one percent sign", function () {
    run("88%%", new ErrorNode(new ParseException()));
    run("88%%%", new ErrorNode(new ParseException()));
    run("88% % % %", new ErrorNode(new ParseException()));
  });

  it("should transpile unary plus operations", function () {
    run("+22", new UnaryPlusOperationNode(new NumberNode(22)));
  });

  it("should transpile ranges with single-quoted sheet names", function () {
    run(
      "'Sheet Name'!D11",
      new RangeNode(
        CellQuery.builder()
          .setSheet("'Sheet Name'")
          .columnsBetween(3, 3)
          .rowsBetween(10, 10)
          .build()
      )
    );
    run(
      "'Sheet Name'!A1:A2",
      new RangeNode(
        CellQuery.builder().setSheet("'Sheet Name'").columnsBetween(0, 0).rowsBetween(0, 1).build()
      )
    );
    run(
      "'Sheet Name'!3:7",
      new RangeNode(
        CellQuery.builder()
          .setSheet("'Sheet Name'")
          .rowsBetween(2, 6)
          .openColumnsStartingAtZero()
          .build()
      )
    );
    run(
      "'Sheet Name'!C:A",
      new RangeNode(
        CellQuery.builder()
          .setSheet("'Sheet Name'")
          .columnsBetween(0, 2)
          .openRowsStartingAtZero()
          .build()
      )
    );
    run(
      "'Sheet Name'!D3:7",
      new RangeNode(
        CellQuery.builder()
          .setSheet("'Sheet Name'")
          .rowsBetween(2, 6)
          .openColumnsStartingAt("D")
          .build()
      )
    );
  });

  it("should transpile cell range with single cell", function () {
    run("A4", new RangeNode(CellQuery.builder().columnsBetween(0, 0).rowsBetween(3, 3).build()));
    run("D11", new RangeNode(CellQuery.builder().columnsBetween(3, 3).rowsBetween(10, 10).build()));
  });

  it("should transpile cell range with bi-cell (beginning and end)", function () {
    run("A1:B2", new RangeNode(CellQuery.builder().columnsBetween(0, 1).rowsBetween(0, 1).build()));
    run("B2:A1", new RangeNode(CellQuery.builder().columnsBetween(0, 1).rowsBetween(0, 1).build()));
    run("B2:D4", new RangeNode(CellQuery.builder().columnsBetween(1, 3).rowsBetween(1, 3).build()));
    run("B4:B4", new RangeNode(CellQuery.builder().columnsBetween(1, 1).rowsBetween(3, 3).build()));
    run("B3:B4", new RangeNode(CellQuery.builder().columnsBetween(1, 1).rowsBetween(2, 3).build()));
    run("B4:B3", new RangeNode(CellQuery.builder().columnsBetween(1, 1).rowsBetween(2, 3).build()));
  });

  it("should transpile cell ranges that are column-wise (eg: A:D, M:M)", function () {
    run(
      "A:C",
      new RangeNode(CellQuery.builder().columnsBetween(0, 2).openRowsStartingAtZero().build())
    );
    run(
      "C:A",
      new RangeNode(CellQuery.builder().columnsBetween(0, 2).openRowsStartingAtZero().build())
    );
    run(
      "B:M",
      new RangeNode(CellQuery.builder().columnsBetween(1, 12).openRowsStartingAtZero().build())
    );
    run(
      "M:B",
      new RangeNode(CellQuery.builder().columnsBetween(1, 12).openRowsStartingAtZero().build())
    );
    run(
      "M:M",
      new RangeNode(CellQuery.builder().columnsBetween(12, 12).openRowsStartingAtZero().build())
    );
  });

  it("should transpile cell ranges that are row-wise (eg: 1:8, 4:4)", function () {
    run(
      "3:7",
      new RangeNode(CellQuery.builder().rowsBetween(2, 6).openColumnsStartingAtZero().build())
    );
    run(
      "1:100",
      new RangeNode(CellQuery.builder().rowsBetween(0, 99).openColumnsStartingAtZero().build())
    );
    run(
      "7:3",
      new RangeNode(CellQuery.builder().rowsBetween(2, 6).openColumnsStartingAtZero().build())
    );
    run(
      "100:1",
      new RangeNode(CellQuery.builder().rowsBetween(0, 99).openColumnsStartingAtZero().build())
    );
  });

  it("should transpile cell ranges that are row-wise with column offset (eg: A2:9)", function () {
    run(
      "D3:7",
      new RangeNode(CellQuery.builder().rowsBetween(2, 6).openColumnsStartingAt("D").build())
    );
    run(
      "D7:3",
      new RangeNode(CellQuery.builder().rowsBetween(2, 6).openColumnsStartingAt("D").build())
    );
    run(
      "E5:10",
      new RangeNode(CellQuery.builder().rowsBetween(4, 9).openColumnsStartingAtNumber(4).build())
    );
    run(
      "D7:7",
      new RangeNode(CellQuery.builder().rowsBetween(6, 6).openColumnsStartingAt("D").build())
    );
  });

  it("should transpile cell ranges that are column-wise with row offset (eg: D1:M)", function () {
    run(
      "B3:D",
      new RangeNode(CellQuery.builder().columnsBetween(1, 3).openRowsStartingAtNumber(2).build())
    );
    run(
      "D3:B",
      new RangeNode(CellQuery.builder().columnsBetween(1, 3).openRowsStartingAtNumber(2).build())
    );
    run(
      "B33:B",
      new RangeNode(CellQuery.builder().columnsBetween(1, 1).openRowsStartingAtNumber(32).build())
    );
  });

  it("should transpile cell ranges that are multi-cell", function () {
    run(
      "C5:B4:D2:G7:L11",
      new MultiRangeNode([
        new RangeNode(CellQuery.builder().columnsBetween(1, 2).rowsBetween(3, 4).build()),
        new RangeNode(CellQuery.builder().columnsBetween(3, 6).rowsBetween(1, 6).build()),
        new RangeNode(CellQuery.builder().columnsBetween(11, 11).rowsBetween(10, 10).build()),
      ])
    );
    run(
      "A4:B5:C6:D7:E8:F9",
      new MultiRangeNode([
        new RangeNode(CellQuery.builder().columnsBetween(0, 1).rowsBetween(3, 4).build()),
        new RangeNode(CellQuery.builder().columnsBetween(2, 3).rowsBetween(5, 6).build()),
        new RangeNode(CellQuery.builder().columnsBetween(4, 5).rowsBetween(7, 8).build()),
      ])
    );
    run(
      "SUM(A1:B2:C3:D4:E5:F6)",
      new FormulaNode(FormulaName.SUM.toString(), [
        new MultiRangeNode([
          new RangeNode(CellQuery.builder().columnsBetween(0, 1).rowsBetween(0, 1).build()),
          new RangeNode(CellQuery.builder().columnsBetween(2, 3).rowsBetween(2, 3).build()),
          new RangeNode(CellQuery.builder().columnsBetween(4, 5).rowsBetween(4, 5).build()),
        ]),
      ])
    );
  });

  /**
   * Just run the test, asserting that the result is equal to the expected node.
   * @param input - input string.
   * @param expected  - expected node.
   */
  function run(input: string, expected: Node) {
    try {
      const chars = new antlr4.InputStream(input);
      const lexer: any = new F7Lexer(chars);
      const tokens = new antlr4.CommonTokenStream(lexer);
      const parser: any = new F7Parser(tokens);
      parser.buildParseTrees = true;
      const tree = parser.start().block();
      assert.deepEqual(new TranspilationVisitor().visit(tree), expected);
    } catch (e) {
      assert.deepEqual(new ErrorNode(e), expected);
    }
  }
});
