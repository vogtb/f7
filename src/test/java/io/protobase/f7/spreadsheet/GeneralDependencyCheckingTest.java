package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.RefException;
import io.protobase.f7.testutils.TestExecution;
import org.junit.Test;

public class GeneralDependencyCheckingTest extends TestExecution {
  @Test
  public void test_CycleFromSelfReferenceLoop() {
    runner()
        .addCell("Alpha", "J2", "= J2 + 10")
        .addExpectedValue("Alpha", "J2", new RefException())
        .run();
  }

  @Test
  public void test_CycleFromDirectLoopback() {
    runner()
        .addCell("Alpha", "A1", "= J2 + 1")
        .addCell("Alpha", "J2", "= A1 + 1")
        .addExpectedValue("Alpha", "A1", new RefException())
        .addExpectedValue("Alpha", "J2", new RefException())
        .run();
  }

  @Test
  public void test_CycleFromIndirectLoopback() {
    runner()
        .addCell("Alpha", "A1", "= J2 + 1")
        .addCell("Alpha", "J2", "= D8 + 1")
        .addCell("Alpha", "D8", "= A1 + 1")
        .addExpectedValue("Alpha", "A1", new RefException())
        .addExpectedValue("Alpha", "J2", new RefException())
        .addExpectedValue("Alpha", "D8", new RefException())
        .run();
    runner()
        .addCell("Alpha", "D4", "= A1 + 1")
        .addCell("Alpha", "A2", "= D4 + 1")
        .addCell("Alpha", "A1", "= A2 + 1")
        .addExpectedValue("Alpha", "D4", new RefException())
        .addExpectedValue("Alpha", "A2", new RefException())
        .addExpectedValue("Alpha", "A1", new RefException())
        .run();
  }

  @Test
  public void test_CycleFromDirectLoopbackWithNamedGrid() {
    runner()
        .addCell("Alpha", "A1", "= Beta!J2 + 1")
        .addCell("Beta", "J2", "= Alpha!A1 + 1")
        .addExpectedValue("Alpha", "A1", new RefException())
        .addExpectedValue("Beta", "J2", new RefException())
        .run();
  }

  @Test
  public void test_CycleFromIndirectLoopbackWithNamedGrid() {
    runner()
        .addCell("Alpha", "A1", "= Beta!J2 + 1")
        .addCell("Beta", "J2", "= Charlie!D8 + 1")
        .addCell("Charlie", "D8", "= Alpha!A1 + 1")
        .addExpectedValue("Alpha", "A1", new RefException())
        .addExpectedValue("Beta", "J2", new RefException())
        .addExpectedValue("Charlie", "D8", new RefException())
        .run();
  }

  @Test
  public void test_CycleFromRangeThatContainsSelf() {
    runner()
        .addCell("Alpha", "A4", "= {A1:A8}")
        .addExpectedValue("Alpha", "A4", new RefException())
        .run();
  }

  @Test
  public void test_CycleFromRangeThatContainsIndirectDependency() {
    runner()
        .addCell("Alpha", "A1", "= D10")
        .addCell("Alpha", "D10", "= {A1:A2}")
        .addExpectedValue("Alpha", "A1", new RefException())
        .addExpectedValue("Alpha", "D10", new RefException())
        .run();
  }
}
