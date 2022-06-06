package io.protobase.f7.transpiler;

import com.google.common.collect.ImmutableList;
import io.protobase.f7.antlr.F7Lexer;
import io.protobase.f7.antlr.F7Parser;
import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.F7Exception;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.NameException;
import io.protobase.f7.errors.NullException;
import io.protobase.f7.errors.NumException;
import io.protobase.f7.errors.ParseException;
import io.protobase.f7.errors.RefException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.formulas.FormulaName;
import io.protobase.f7.models.BinaryOperationNode;
import io.protobase.f7.models.CellQuery;
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
import io.protobase.f7.models.VariableNode;
import io.protobase.f7.spreadsheet.ParseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.google.common.truth.Truth.assertThat;

public class TranspilationVisitorTest {
  private static final TranspilationVisitor visitor = new TranspilationVisitor();

  @Test
  public void numberNode() {
    run("10.0e2", new NumberNode(10.0e2));
    run("10.0e-2", new NumberNode(10.0e-2));
    run("81739821", new NumberNode(81739821.0));
    run("1.00000000001", new NumberNode(1.00000000001));
    run("199", new NumberNode(199.0));
    run("201", new NumberNode(201.0));
    run("9187312.222", new NumberNode(9187312.222));
  }

  @Test
  public void textNode() {
    run("\"Hello Friend!\"", new TextNode("Hello Friend!"));
    run("\"\"", new TextNode(""));
  }

  @Test
  public void listNode() {
    run("{1.1}", ListNode.builder()
        .value(0, 0, new NumberNode(1.1))
        .build());
    run("{1.1, 4.4, \"Third\"}", ListNode.builder()
        .value(0, 0, new NumberNode(1.1))
        .value(1, 0, new NumberNode(4.4))
        .value(2, 0, new TextNode("Third"))
        .build());
  }

  @Test
  public void listNode_nested() {
    run("{1, {2}, {{3}}}", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .value(1, 0, new NumberNode(2.0))
        .value(2, 0, new NumberNode(3.0))
        .build());
    run("{1, {2}, {{3}}, {4}, {{{{{5}}}}}}", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .value(1, 0, new NumberNode(2.0))
        .value(2, 0, new NumberNode(3.0))
        .value(3, 0, new NumberNode(4.0))
        .value(4, 0, new NumberNode(5.0))
        .build());
    run("{}", new ErrorNode(new RefException()));
    run("{1, {}}", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .value(1, 0, new ErrorNode(new RefException()))
        .build());
    run("{1, {}, {{}}}", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .value(1, 0, new ErrorNode(new RefException()))
        .value(2, 0, new ErrorNode(new RefException()))
        .build());
    run("{1, {2}, {{3}}, {{{4, 5, 6, {}, {8}}}}}", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .value(1, 0, new NumberNode(2.0))
        .value(2, 0, new NumberNode(3.0))
        .value(3, 0, new NumberNode(4.0))
        .value(4, 0, new NumberNode(5.0))
        .value(5, 0, new NumberNode(6.0))
        .value(6, 0, new ErrorNode(new RefException()))
        .value(7, 0, new NumberNode(8.0))
        .build());
    run("{{{{{{1}}}}}}", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .build());
    run("{{10, {}, {{}}}}", ListNode.builder()
        .value(0, 0, new NumberNode(10.0))
        .value(1, 0, new ErrorNode(new RefException()))
        .value(2, 0, new ErrorNode(new RefException()))
        .build());
  }

  @Test
  public void listNode_multiDimensional() {
    run("{1,2,3}", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .value(1, 0, new NumberNode(2.0))
        .value(2, 0, new NumberNode(3.0))
        .build());

    run("{{1;2;3},{4;5;6}}", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .value(0, 1, new NumberNode(2.0))
        .value(0, 2, new NumberNode(3.0))
        .value(1, 0, new NumberNode(4.0))
        .value(1, 1, new NumberNode(5.0))
        .value(1, 2, new NumberNode(6.0))
        .build());

    run("{{1,2,3};{4,5,6}}", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .value(1, 0, new NumberNode(2.0))
        .value(2, 0, new NumberNode(3.0))
        .value(0, 1, new NumberNode(4.0))
        .value(1, 1, new NumberNode(5.0))
        .value(2, 1, new NumberNode(6.0))
        .build());

    run("{{1,2,3},{4,5,6}}", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .value(1, 0, new NumberNode(2.0))
        .value(2, 0, new NumberNode(3.0))
        .value(3, 0, new NumberNode(4.0))
        .value(4, 0, new NumberNode(5.0))
        .value(5, 0, new NumberNode(6.0))
        .build());

    run("{{1;2;3}}", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .value(0, 1, new NumberNode(2.0))
        .value(0, 2, new NumberNode(3.0))
        .build());

    run("{{1,2;3,4}}", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .value(1, 0, new NumberNode(2.0))
        .value(0, 1, new NumberNode(3.0))
        .value(1, 1, new NumberNode(4.0))
        .build());

    run("{1,2;3,4}", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .value(1, 0, new NumberNode(2.0))
        .value(0, 1, new NumberNode(3.0))
        .value(1, 1, new NumberNode(4.0))
        .build());

    run("{1;{2;3;4}}", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .value(0, 1, new NumberNode(2.0))
        .value(0, 2, new NumberNode(3.0))
        .value(0, 3, new NumberNode(4.0))
        .build());

    run("{{{1.1, 2.1, 3.1};{1.2, 2.2, 3.2}};{{1.3, 2.3, 3.3};{1.4, 2.4, 3.4}};{{1.5, 2.5, 3.5};{1.6, 2.6, 3.6}}}", ListNode.builder()
        .value(0, 0, new NumberNode(1.1))
        .value(1, 0, new NumberNode(2.1))
        .value(2, 0, new NumberNode(3.1))
        .value(0, 1, new NumberNode(1.2))
        .value(1, 1, new NumberNode(2.2))
        .value(2, 1, new NumberNode(3.2))
        .value(0, 2, new NumberNode(1.3))
        .value(1, 2, new NumberNode(2.3))
        .value(2, 2, new NumberNode(3.3))
        .value(0, 3, new NumberNode(1.4))
        .value(1, 3, new NumberNode(2.4))
        .value(2, 3, new NumberNode(3.4))
        .value(0, 4, new NumberNode(1.5))
        .value(1, 4, new NumberNode(2.5))
        .value(2, 4, new NumberNode(3.5))
        .value(0, 5, new NumberNode(1.6))
        .value(1, 5, new NumberNode(2.6))
        .value(2, 5, new NumberNode(3.6))
        .build());
  }

  @Test
  public void listNode_multiDimensionalAndMissingValues() {
    run("{1,2;3;4;5;6}", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .value(1, 0, new NumberNode(2.0))
        .value(0, 1, new NumberNode(3.0))
        .value(0, 2, new NumberNode(4.0))
        .value(0, 3, new NumberNode(5.0))
        .value(0, 4, new NumberNode(6.0))
        .build());
  }

  @Test
  public void formulaNode_empty() {
    run("RAND()", new FormulaNode(FormulaName.RAND.toString()));
    run("TRUE()", new FormulaNode(FormulaName.TRUE.toString()));
    run("FALSE()", new FormulaNode(FormulaName.FALSE.toString()));
  }

  @Test
  public void formulaNode() {
    run("POW(2, 6)", FormulaNode.builder()
        .name(FormulaName.POW.toString())
        .value(new NumberNode(2.0))
        .value(new NumberNode(6.0))
        .build());
    run("SUM(1.829173, {8.12983271})", FormulaNode.builder()
        .name(FormulaName.SUM.toString())
        .value(new NumberNode(1.829173))
        .value(ListNode.builder().value(0, 0, new NumberNode(8.12983271)).build())
        .build());
  }

  @Test
  public void variableNode() {
    run("TRUE", new VariableNode("TRUE"));
    run("True", new VariableNode("True"));
    run("tRuE", new VariableNode("tRuE"));
    run("FALSE", new VariableNode("FALSE"));
    run("False", new VariableNode("False"));
    run("fAlSe", new VariableNode("fAlSe"));
    run("false", new VariableNode("false"));
    run("My_Special_Variable_That_Might_Exist", new VariableNode("My_Special_Variable_That_Might_Exist"));
    run("Variable_That_Is_Longer_Than_Two_Hundred_And_Fifty_Five_Characters_Old_McDonald_Had_A_Farm_Old_McDonald_Had_A_Farm_Old_McDonald_Had_A_Farm_Old_McDonald_Had_A_Farm_Old_McDonald_Had_A_Farm_Old_McDonald_Had_A_Farm_Old_McDonald_Had_A_Farm_Old_McDonald_Had_A_Fa",
        new ErrorNode(new ParseException()));
  }

  @Test
  public void errorNode() {
    run("#NUM!", new ErrorNode(new NumException()));
    run("#DIV/0!", new ErrorNode(new DivException()));
    run("#VALUE!", new ErrorNode(new ValueException()));
    run("#REF!", new ErrorNode(new RefException()));
    run("#NAME?", new ErrorNode(new NameException()));
    run("#NUM!", new ErrorNode(new NumException()));
    run("#N/A", new ErrorNode(new NAException()));
    run("#ERROR!", new ErrorNode(new ParseException()));
  }

  @Test
  public void testAddition() {
    run("1 + 1", new BinaryOperationNode(new NumberNode(1.0), "+", new NumberNode(1.0)));
    run("1 + 0", new BinaryOperationNode(new NumberNode(1.0), "+", new NumberNode(0.0)));
    run("1 + 2.12121", new BinaryOperationNode(new NumberNode(1.0), "+", new NumberNode(2.12121)));
    run("1e10 + 2.12121", new BinaryOperationNode(new NumberNode(1e10), "+", new NumberNode(2.12121)));
  }

  @Test
  public void testAddition_withList() {
    ListNode singleOne = ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .build();
    run("1 + {1}", new BinaryOperationNode(new NumberNode(1.0), "+", singleOne));
    run("1 + {1, 44}", new BinaryOperationNode(new NumberNode(1.0), "+", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .value(1, 0, new NumberNode(44.0))
        .build()));
    run("{1} + {1}", new BinaryOperationNode(singleOne, "+", singleOne));
  }

  @Test
  public void testAddition_withErrors() {
    run("3 + #VALUE!", new BinaryOperationNode(new NumberNode(3.0), "+", new ErrorNode(new ValueException())));
    run("3 + #DIV/0!", new BinaryOperationNode(new NumberNode(3.0), "+", new ErrorNode(new DivException())));
    run("3 + #NUM!", new BinaryOperationNode(new NumberNode(3.0), "+", new ErrorNode(new NumException())));
    run("3 + #NAME?", new BinaryOperationNode(new NumberNode(3.0), "+", new ErrorNode(new NameException())));
    run("3 + #NULL!", new BinaryOperationNode(new NumberNode(3.0), "+", new ErrorNode(new NullException())));
    run("3 + #N/A", new BinaryOperationNode(new NumberNode(3.0), "+", new ErrorNode(new NAException())));
    run("3 + #REF!", new BinaryOperationNode(new NumberNode(3.0), "+", new ErrorNode(new RefException())));
    run("3 + #ERROR!", new BinaryOperationNode(new NumberNode(3.0), "+", new ErrorNode(new ParseException())));
  }

  @Test
  public void testSubtraction() {
    run("1 - 1", new BinaryOperationNode(new NumberNode(1.0), "-", new NumberNode(1.0)));
    run("3 - 1", new BinaryOperationNode(new NumberNode(3.0), "-", new NumberNode(1.0)));
    run("3.1e3 - 4.2e10", new BinaryOperationNode(new NumberNode(3.1e3), "-", new NumberNode(4.2e10)));
  }

  @Test
  public void testSubtraction_withList() {
    ListNode singleOne = ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .build();
    run("1 - {1}", new BinaryOperationNode(new NumberNode(1.0), "-", singleOne));
    run("1 - {1, 44}", new BinaryOperationNode(new NumberNode(1.0), "-", ListNode.builder()
        .value(0, 0, new NumberNode(1.0))
        .value(1, 0, new NumberNode(44.0))
        .build()));
    run("{1} - {1}", new BinaryOperationNode(singleOne, "-", singleOne));
  }

  @Test
  public void testSubtraction_withErrors() {
    run("3 - #VALUE!", new BinaryOperationNode(new NumberNode(3.0), "-", new ErrorNode(new ValueException())));
    run("3 - #DIV/0!", new BinaryOperationNode(new NumberNode(3.0), "-", new ErrorNode(new DivException())));
    run("3 - #NUM!", new BinaryOperationNode(new NumberNode(3.0), "-", new ErrorNode(new NumException())));
    run("3 - #NAME?", new BinaryOperationNode(new NumberNode(3.0), "-", new ErrorNode(new NameException())));
    run("3 - #NULL!", new BinaryOperationNode(new NumberNode(3.0), "-", new ErrorNode(new NullException())));
    run("3 - #N/A", new BinaryOperationNode(new NumberNode(3.0), "-", new ErrorNode(new NAException())));
    run("3 - #REF!", new BinaryOperationNode(new NumberNode(3.0), "-", new ErrorNode(new RefException())));
    run("3 - #ERROR!", new BinaryOperationNode(new NumberNode(3.0), "-", new ErrorNode(new ParseException())));
  }

  @Test
  public void testMultiplication() {
    run("3 * 4", new BinaryOperationNode(new NumberNode(3.0), "*", new NumberNode(4.0)));
    run("3 * 1", new BinaryOperationNode(new NumberNode(3.0), "*", new NumberNode(1.0)));
    run("3.1e3 * 2", new BinaryOperationNode(new NumberNode(3.1e3), "*", new NumberNode(2.0)));
    run("3.1e3 * 0", new BinaryOperationNode(new NumberNode(3.1e3), "*", new NumberNode(0.0)));
  }

  @Test
  public void testMultiplication_withList() {
    run("3 * {4}", new BinaryOperationNode(new NumberNode(3.0), "*", ListNode.builder()
        .value(0, 0, new NumberNode(4.0))
        .build()));
    run("3 * {4, TRUE}", new BinaryOperationNode(new NumberNode(3.0), "*", ListNode.builder()
        .value(0, 0, new NumberNode(4.0))
        .value(1, 0, new VariableNode("TRUE"))
        .build()));
  }

  @Test
  public void testMultiplication_withErrors() {
    run("3 * #VALUE!", new BinaryOperationNode(new NumberNode(3.0), "*", new ErrorNode(new ValueException())));
    run("3 * #DIV/0!", new BinaryOperationNode(new NumberNode(3.0), "*", new ErrorNode(new DivException())));
    run("3 * #NUM!", new BinaryOperationNode(new NumberNode(3.0), "*", new ErrorNode(new NumException())));
    run("3 * #NAME?", new BinaryOperationNode(new NumberNode(3.0), "*", new ErrorNode(new NameException())));
    run("3 * #NULL!", new BinaryOperationNode(new NumberNode(3.0), "*", new ErrorNode(new NullException())));
    run("3 * #N/A", new BinaryOperationNode(new NumberNode(3.0), "*", new ErrorNode(new NAException())));
    run("3 * #REF!", new BinaryOperationNode(new NumberNode(3.0), "*", new ErrorNode(new RefException())));
    run("3 * #ERROR!", new BinaryOperationNode(new NumberNode(3.0), "*", new ErrorNode(new ParseException())));
  }

  @Test
  public void testDivision() {
    run("3 / 4", new BinaryOperationNode(new NumberNode(3.0), "/", new NumberNode(4.0)));
    run("4 / 3", new BinaryOperationNode(new NumberNode(4.0), "/", new NumberNode(3.0)));
    run("3e3 / 2", new BinaryOperationNode(new NumberNode(3e3), "/", new NumberNode(2.0)));
    run("10 / 0", new BinaryOperationNode(new NumberNode(10.0), "/", new NumberNode(0.0)));
  }

  @Test
  public void testDivision_withList() {
    run("3 / {4}", new BinaryOperationNode(new NumberNode(3.0), "/", ListNode.builder()
        .value(0, 0, new NumberNode(4.0))
        .build()));
    run("3 / {4, TRUE}", new BinaryOperationNode(new NumberNode(3.0), "/", ListNode.builder()
        .value(0, 0, new NumberNode(4.0))
        .value(1, 0, new VariableNode("TRUE"))
        .build()));
  }

  @Test
  public void testDivision_withErrors() {
    run("3 / #VALUE!", new BinaryOperationNode(new NumberNode(3.0), "/", new ErrorNode(new ValueException())));
    run("3 / #DIV/0!", new BinaryOperationNode(new NumberNode(3.0), "/", new ErrorNode(new DivException())));
    run("3 / #NUM!", new BinaryOperationNode(new NumberNode(3.0), "/", new ErrorNode(new NumException())));
    run("3 / #NAME?", new BinaryOperationNode(new NumberNode(3.0), "/", new ErrorNode(new NameException())));
    run("3 / #NULL!", new BinaryOperationNode(new NumberNode(3.0), "/", new ErrorNode(new NullException())));
    run("3 / #N/A", new BinaryOperationNode(new NumberNode(3.0), "/", new ErrorNode(new NAException())));
    run("3 / #REF!", new BinaryOperationNode(new NumberNode(3.0), "/", new ErrorNode(new RefException())));
    run("3 / #ERROR!", new BinaryOperationNode(new NumberNode(3.0), "/", new ErrorNode(new ParseException())));
  }

  @Test
  public void testPower() {
    run("-2^3", new BinaryOperationNode(new UnaryMinusOperationNode(new NumberNode(2.0)), "^", new NumberNode(3.0)));
    run("(-2)^3", new BinaryOperationNode(new UnaryMinusOperationNode(new NumberNode(2.0)), "^", new NumberNode(3.0)));
    run("-(2)^3", new BinaryOperationNode(new UnaryMinusOperationNode(new NumberNode(2.0)), "^", new NumberNode(3.0)));
    run("--(-2)^3", new BinaryOperationNode(new UnaryMinusOperationNode(new UnaryMinusOperationNode(new UnaryMinusOperationNode(new NumberNode(2.0)))), "^", new NumberNode(3.0)));
    run("11 -2^3", new BinaryOperationNode(new NumberNode(11.0), "-", new BinaryOperationNode(new NumberNode(2.0), "^", new NumberNode(3.0))));
    run("(-2) ^ 3", new BinaryOperationNode(new UnaryMinusOperationNode(new NumberNode(2.0)), "^", new NumberNode(3.0)));
    run("(-3) ^ 2", new BinaryOperationNode(new UnaryMinusOperationNode(new NumberNode(3.0)), "^", new NumberNode(2.0)));
  }

  @Test
  public void testConcatOperation() {
    run("1 & 1", new BinaryOperationNode(new NumberNode(1.0), "&", new NumberNode(1.0)));
    run("\"One\" & 0", new BinaryOperationNode(new TextNode("One"), "&", new NumberNode(0.0)));
  }


  @Test
  public void testConcatOperation_withErrors() {
    run("1 & #DIV/0!", new BinaryOperationNode(new NumberNode(1.0), "&", new ErrorNode(new DivException())));
  }

  @Test
  public void testUnaryMinusOperation() {
    run("-22", new UnaryMinusOperationNode(new NumberNode(22.0)));
    run("-\"Thing\"", new UnaryMinusOperationNode(new TextNode("Thing")));
    run("--22", new UnaryMinusOperationNode(new UnaryMinusOperationNode(new NumberNode(22.0))));
    run("---22", new UnaryMinusOperationNode(new UnaryMinusOperationNode(new UnaryMinusOperationNode(new NumberNode(22.0)))));
    run("-(2 * 4)", new UnaryMinusOperationNode(new BinaryOperationNode(new NumberNode(2.0), "*", new NumberNode(4.0))));
  }

  @Test
  public void testUnaryPercentOperation() {
    run("88%", new UnaryPercentOperationNode(new NumberNode(88.0)));
    run("(8 * 8)%", new UnaryPercentOperationNode(new BinaryOperationNode(new NumberNode(8.0), "*", new NumberNode(8.0))));
  }

  @Test
  public void testUnaryPercentOperation_parseErrorBecauseOfRepetitivePercent() {
    run("88%%", new ErrorNode(new ParseException()));
    run("88%%%", new ErrorNode(new ParseException()));
    run("88% % % %", new ErrorNode(new ParseException()));
  }

  @Test
  public void testCellRange_singleCell() {
    run("A4", new RangeNode(CellQuery.builder()
        .columnsBetween(0, 0)
        .rowsBetween(3, 3)
        .build()));
  }

  @Test
  public void testCellRange_biCell() {
    run("A1:B2", new RangeNode(CellQuery.builder()
        .columnsBetween(0, 1)
        .rowsBetween(0, 1)
        .build()));
    run("B2:A1", new RangeNode(CellQuery.builder()
        .columnsBetween(0, 1)
        .rowsBetween(0, 1)
        .build()));
    run("B2:D4", new RangeNode(CellQuery.builder()
        .columnsBetween(1, 3)
        .rowsBetween(1, 3)
        .build()));
    run("B4:B4", new RangeNode(CellQuery.builder()
        .columnsBetween(1, 1)
        .rowsBetween(3, 3)
        .build()));
    run("B3:B4", new RangeNode(CellQuery.builder()
        .columnsBetween(1, 1)
        .rowsBetween(2, 3)
        .build()));
    run("B4:B3", new RangeNode(CellQuery.builder()
        .columnsBetween(1, 1)
        .rowsBetween(2, 3)
        .build()));
  }

  @Test
  public void testCellRange_multiColumn() {
    run("A:C", new RangeNode(CellQuery.builder()
        .columnsBetween(0, 2)
        .openRowsStartingAtZero()
        .build()));
    run("C:A", new RangeNode(CellQuery.builder()
        .columnsBetween(0, 2)
        .openRowsStartingAtZero()
        .build()));
    run("B:M", new RangeNode(CellQuery.builder()
        .columnsBetween(1, 12)
        .openRowsStartingAtZero()
        .build()));
    run("M:B", new RangeNode(CellQuery.builder()
        .columnsBetween(1, 12)
        .openRowsStartingAtZero()
        .build()));
    run("M:M", new RangeNode(CellQuery.builder()
        .columnsBetween(12, 12)
        .openRowsStartingAtZero()
        .build()));
  }

  @Test
  public void testCellRange_multiRow() {
    run("3:7", new RangeNode(CellQuery.builder()
        .rowsBetween(2, 6)
        .openColumnsStartingAtZero()
        .build()));
    run("1:100", new RangeNode(CellQuery.builder()
        .rowsBetween(0, 99)
        .openColumnsStartingAtZero()
        .build()));
    run("7:3", new RangeNode(CellQuery.builder()
        .rowsBetween(2, 6)
        .openColumnsStartingAtZero()
        .build()));
    run("100:1", new RangeNode(CellQuery.builder()
        .rowsBetween(0, 99)
        .openColumnsStartingAtZero()
        .build()));
  }

  @Test
  public void testCellRange_multiRowStartColumn() {
    run("D3:7", new RangeNode(CellQuery.builder()
        .rowsBetween(2, 6)
        .openColumnsStartingAt("D")
        .build()));
    run("D7:3", new RangeNode(CellQuery.builder()
        .rowsBetween(2, 6)
        .openColumnsStartingAt("D")
        .build()));
    run("E5:10", new RangeNode(CellQuery.builder()
        .rowsBetween(4, 9)
        .openColumnsStartingAt(4)
        .build()));
    run("D7:7", new RangeNode(CellQuery.builder()
        .rowsBetween(6, 6)
        .openColumnsStartingAt("D")
        .build()));
  }

  @Test
  public void testCellRange_multiColumnStartRow() {
    run("B3:D", new RangeNode(CellQuery.builder()
        .columnsBetween(1, 3)
        .openRowsStartingAt(2)
        .build()));
    run("D3:B", new RangeNode(CellQuery.builder()
        .columnsBetween(1, 3)
        .openRowsStartingAt(2)
        .build()));
    run("B33:B", new RangeNode(CellQuery.builder()
        .columnsBetween(1, 1)
        .openRowsStartingAt(32)
        .build()));
  }

  @Test
  public void testCellRange_multiCell() {
    run("C5:B4:D2:G7:L11", new RangeQueryNode(ImmutableList.of(
        new RangeNode(CellQuery.builder()
            .columnsBetween(1, 2)
            .rowsBetween(3, 4)
            .build()),
        new RangeNode(CellQuery.builder()
            .columnsBetween(3, 6)
            .rowsBetween(1, 6)
            .build()),
        new RangeNode(CellQuery.builder()
            .columnsBetween(11, 11)
            .rowsBetween(10, 10)
            .build())
    )));
    run("A4:B5:C6:D7:E8:F9", new RangeQueryNode(ImmutableList.of(
        new RangeNode(CellQuery.builder()
            .columnsBetween(0, 1)
            .rowsBetween(3, 4)
            .build()),
        new RangeNode(CellQuery.builder()
            .columnsBetween(2, 3)
            .rowsBetween(5, 6)
            .build()),
        new RangeNode(CellQuery.builder()
            .columnsBetween(4, 5)
            .rowsBetween(7, 8)
            .build())
    )));
    run("SUM(A1:B2:C3:D4:E5:F6)", new FormulaNode(FormulaName.SUM.toString(), ImmutableList.of(new RangeQueryNode(ImmutableList.of(
        new RangeNode(CellQuery.builder()
            .columnsBetween(0, 1)
            .rowsBetween(0, 1)
            .build()),
        new RangeNode(CellQuery.builder()
            .columnsBetween(2, 3)
            .rowsBetween(2, 3)
            .build()),
        new RangeNode(CellQuery.builder()
            .columnsBetween(4, 5)
            .rowsBetween(4, 5)
            .build())
    )))));
  }

  private Node transpile(String value) {
    try {
      CommonTokenStream tokens = new CommonTokenStream(new F7Lexer(CharStreams.fromStream(
          new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8)));
      try {
        F7Parser parser = new F7Parser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new ParseErrorListener());
        return visitor.visit(parser.start().block());
      } catch (ParseCancellationException parseException) {
        return new ErrorNode(new ParseException("Parse error"));
      } catch (F7Exception f7Exception) {
        return new ErrorNode(f7Exception);
      }
    } catch (IOException io) {
      return new ErrorNode(new ParseException("Parse error"));
    }
  }

  protected void run(String in, Node out) {
    assertThat(transpile(in)).isEqualTo(out);
  }
}
