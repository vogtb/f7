package io.protobase.f7.testutils;

import io.protobase.f7.models.A1Key;
import io.protobase.f7.models.Cell;
import io.protobase.f7.models.ColumnRowKey;
import io.protobase.f7.models.Grid;
import io.protobase.f7.spreadsheet.SpreadsheetExecutor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

/**
 * Extendable test execution class to make it easier to load values into grids, and load the grids into a sheet,
 * and execute the sheet, querying the resulting grids for expected values.
 */
public class TestExecution {
  protected static Runner runner() {
    return new Runner();
  }

  /**
   * Runner class lets us build tests dynamically, by adding individual cells with out a ton of boiler plating.
   */
  public static class Runner {
    Map<String, Grid<Cell>> grids = new HashMap<>();
    Map<String, Map<String, Object>> expectedGridValues = new HashMap<>();
    Map<String, Set<String>> expectedEmptyKeys = new HashMap<>();
    Map<String, Set<String>> expectedEmptyComputedValueKeys = new HashMap<>();
    Map<String, String> namedRanges = new HashMap<>();

    public Runner addGrid(String gridName, Map<String, String> values) {
      Grid<Cell> grid = new Grid<>();
      values.forEach((key, value) -> grid.set(A1Key.fromString(key).toColumnRowKey(), new Cell(value)));
      grids.put(gridName, grid);
      return this;
    }

    public Runner addCell(String gridName, String a1Key, String value) {
      grids.putIfAbsent(gridName, new Grid<>());
      grids.get(gridName).set(A1Key.fromString(a1Key).toColumnRowKey(), new Cell(value));
      return this;
    }

    public Runner addExpectedValue(String gridName, String a1Key, Object exepctedValue) {
      expectedGridValues.putIfAbsent(gridName, new HashMap<>());
      expectedGridValues.get(gridName).put(a1Key, exepctedValue);
      return this;
    }

    public Runner addExpectedEmptyValues(String gridName, Set<String> keys) {
      expectedEmptyKeys.put(gridName, keys);
      return this;
    }

    public Runner addExpectedEmptyValue(String gridName, String key) {
      expectedEmptyKeys.putIfAbsent(gridName, new HashSet<>());
      expectedEmptyKeys.getOrDefault(gridName, new HashSet<>()).add(key);
      return this;
    }

    public Runner addExpectedEmptyComputedValue(String gridName, String key) {
      expectedEmptyComputedValueKeys.putIfAbsent(gridName, new HashSet<>());
      expectedEmptyComputedValueKeys.getOrDefault(gridName, new HashSet<>()).add(key);
      return this;
    }

    public Runner addNamedRange(String name, String range) {
      namedRanges.put(name, range);
      return this;
    }

    /**
     * Run the tests in several steps:
     * 1) Build sheet executor and run it.
     * 2) For each expected entry, expect result.
     * 3) For each empty value, ensure it's empty.
     * 4) For each computed empty value, ensure it's empty.
     */
    public void run() {
      // 1) Build sheet executor and run it.
      SpreadsheetExecutor sheet = SpreadsheetExecutor.builder().addGrids(grids).addNamedRanges(namedRanges).build();
      sheet.execute();

      // 2) For each expected entry, expect result.
      for (Map.Entry<String, Map<String, Object>> expectedEntry : expectedGridValues.entrySet()) {
        String gridName = expectedEntry.getKey();
        Map<String, Object> expectedEntryValues = expectedEntry.getValue();
        for (Map.Entry<String, Object> expected : expectedEntryValues.entrySet()) {
          ColumnRowKey key = A1Key.fromString(expected.getKey()).toColumnRowKey();
          Object value = expected.getValue();
          assertThat(sheet.getCell(gridName, key).get().getComputedValue()).isEqualTo(value);
        }
      }

      //3) For each empty value, ensure it's empty.
      for (Map.Entry<String, Set<String>> expectedGridsToHaveNullValues : expectedEmptyKeys.entrySet()) {
        String grid = expectedGridsToHaveNullValues.getKey();
        Set<String> keys = expectedGridsToHaveNullValues.getValue();
        for (String keyString : keys) {
          ColumnRowKey key = A1Key.fromString(keyString).toColumnRowKey();
          Optional<Cell> foundCell = sheet.getCell(grid, key);
          if (foundCell.isPresent()) {
            assertThat(foundCell.get().getComputedValue()).isNull();
          } else {
            assertThat(foundCell.isPresent()).isFalse();
          }
        }
      }

      // 4) For each computed empty value, ensure it's empty.
      for (Map.Entry<String, Set<String>> expectedGridsToHaveNullValues : expectedEmptyComputedValueKeys.entrySet()) {
        String grid = expectedGridsToHaveNullValues.getKey();
        Set<String> keys = expectedGridsToHaveNullValues.getValue();
        for (String keyString : keys) {
          ColumnRowKey key = A1Key.fromString(keyString).toColumnRowKey();
          assertThat(sheet.getCell(grid, key).get().getComputedValue()).isNull();
        }
      }
    }
  }
}
