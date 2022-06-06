package io.protobase.f7.testutils;

import io.protobase.f7.formulas.FormulaCaller;
import io.protobase.f7.models.A1Key;
import io.protobase.f7.models.CellQuery;
import io.protobase.f7.models.ColumnRowKey;
import io.protobase.f7.models.GridColumnRowKey;
import io.protobase.f7.spreadsheet.F7CodeExecutor;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.mockito.Mockito.mock;

public class TestF7CodeExecutor {
  protected static final GridColumnRowKey A1 = new GridColumnRowKey("Alpha", A1Key.fromString("A1").toColumnRowKey());
  protected static final ColumnRowKey M44 = A1Key.fromString("M44").toColumnRowKey();
  protected static final CellQuery M44_RANGE = CellQuery.builder()
      .grid("Alpha")
      .columnsBetween(M44.getColumnIndex(), M44.getColumnIndex())
      .rowsBetween(M44.getRowIndex(), M44.getRowIndex())
      .build();
  protected static final ColumnRowKey B1 = A1Key.fromString("B1").toColumnRowKey();
  protected static final ColumnRowKey B4 = A1Key.fromString("B4").toColumnRowKey();
  protected static final CellQuery B1_B4_RANGE = CellQuery.builder()
      .grid("Alpha")
      .columnsBetween(B1.getColumnIndex(), B4.getColumnIndex())
      .rowsBetween(B1.getRowIndex(), B4.getRowIndex())
      .build();

  @Mock
  protected Function<Object, Object> lookup = mock(Function.class);

  @Mock
  protected BiFunction<GridColumnRowKey, Object, Object> collateralLookup = mock(BiFunction.class);

  /**
   * Run a test at a given cell.
   *
   * @param originGrid - grid of origin cell.
   * @param originKey  - key of origin cell
   * @param code       - code to execute.
   * @return result of executed code.
   */
  public Object run(String originGrid, String originKey, String code) {
    F7CodeExecutor executor = new F7CodeExecutor(new HashMap<>(), lookup, (origin, value) -> value,
        new FormulaCaller(lookup, (origin, value) -> value));
    GridColumnRowKey origin = new GridColumnRowKey(originGrid, A1Key.fromString(originKey).toColumnRowKey());
    return executor.execute(origin, code);
  }

  /**
   * Run a test with the lookup instead of pass-through.
   *
   * @param code -  to execute.
   * @return result of executed code.
   */
  public Object runWithLookup(String code) {
    F7CodeExecutor executor = new F7CodeExecutor(new HashMap<>(), lookup, collateralLookup,
        new FormulaCaller(lookup, collateralLookup));
    GridColumnRowKey origin = new GridColumnRowKey("Alpha", A1Key.fromString("A1").toColumnRowKey());
    return executor.execute(origin, code);
  }

  /**
   * Run the code.
   *
   * @param code - to execute.
   * @return
   */
  public Object run(String code) {
    F7CodeExecutor executor = new F7CodeExecutor(new HashMap<>(), value -> value, (origin, value) -> value,
        new FormulaCaller(value -> value, (origin, value) -> value));
    GridColumnRowKey origin = new GridColumnRowKey("Alpha", A1Key.fromString("A1").toColumnRowKey());
    return executor.execute(origin, code);
  }
}
