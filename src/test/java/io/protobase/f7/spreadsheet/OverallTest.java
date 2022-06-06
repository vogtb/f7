package io.protobase.f7.spreadsheet;

import io.protobase.f7.testutils.TestWithTSVExecution;
import org.junit.Test;

import java.io.IOException;

public class OverallTest extends TestWithTSVExecution {
  @Test
  public void simple() throws IOException {
    runTest("simple");
  }

  @Test
  public void lineItem() throws IOException {
    runTest("line_item");
  }
}
