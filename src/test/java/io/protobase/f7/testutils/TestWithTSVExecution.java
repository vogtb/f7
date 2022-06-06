package io.protobase.f7.testutils;

import com.google.common.io.Resources;
import io.protobase.f7.models.ColumnRowKey;
import io.protobase.f7.spreadsheet.SpreadsheetExecutor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.google.common.truth.Truth.assertThat;

public class TestWithTSVExecution {
  private static final CSVFormat FORMAT = CSVFormat.TDF.withNullString("");

  private static Map<ColumnRowKey, String> loadGrid(String file) throws IOException {
    Reader in = new FileReader(Resources.getResource(file).getFile());
    Iterable<CSVRecord> records = FORMAT.parse(in);
    Map<ColumnRowKey, String> grid = new HashMap<>();
    for (CSVRecord record : records) {
      int row = new Long(record.getRecordNumber()).intValue() - 1;
      int column = 0;
      for (String value : record) {
        if (Objects.nonNull(value)) {
          grid.put(new ColumnRowKey(column, row), value);
        }
        column++;
      }
    }
    return grid;
  }

  protected static void runTest(String testName) throws IOException {
    Map<ColumnRowKey, String> values = loadGrid("sheets/" + testName + "_in.tsv");
    SpreadsheetExecutor executor = SpreadsheetExecutor.builder()
        .addGrid("Main", values)
        .build();
    executor.execute();
    Map<ColumnRowKey, String> expected = loadGrid("sheets/" + testName + "_out.tsv");
    for (Map.Entry<ColumnRowKey, String> entry : expected.entrySet()) {
      assertThat(executor.getCell("Main", entry.getKey()).get().getComputedValue().toString()).isEqualTo(entry.getValue());
    }
  }
}
