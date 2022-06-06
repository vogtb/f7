package io.protobase.f7.testutils;

import io.protobase.f7.models.A1Key;
import io.protobase.f7.models.CellQuery;
import io.protobase.f7.models.GridColumnRowKey;
import org.mockito.Mock;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.mockito.Mockito.mock;

public class TestFormula {
  protected static final GridColumnRowKey A1 = new GridColumnRowKey("Alpha", A1Key.fromString("A1").toColumnRowKey());
  protected static final GridColumnRowKey M22 = new GridColumnRowKey("Alpha", A1Key.fromString("M22").toColumnRowKey());
  protected static final CellQuery M22_RANGE = CellQuery.builder()
      .grid("Alpha")
      .columnsBetween(M22.getColumnIndex(), M22.getColumnIndex())
      .rowsBetween(M22.getRowIndex(), M22.getRowIndex())
      .build();

  protected static final GridColumnRowKey G19 = new GridColumnRowKey("Alpha", A1Key.fromString("G19").toColumnRowKey());
  protected static final CellQuery G19_RANGE = CellQuery.builder()
      .grid("Alpha")
      .columnsBetween(G19.getColumnIndex(), G19.getColumnIndex())
      .rowsBetween(G19.getRowIndex(), G19.getRowIndex())
      .build();

  protected static final GridColumnRowKey J6 = new GridColumnRowKey("Alpha", A1Key.fromString("J6").toColumnRowKey());
  protected static final CellQuery J6_RANGE = CellQuery.builder()
      .grid("Alpha")
      .columnsBetween(J6.getColumnIndex(), J6.getColumnIndex())
      .rowsBetween(J6.getRowIndex(), J6.getRowIndex())
      .build();

  protected static final GridColumnRowKey D1 = new GridColumnRowKey("Alpha", A1Key.fromString("D1").toColumnRowKey());
  protected static final GridColumnRowKey D3 = new GridColumnRowKey("Alpha", A1Key.fromString("D3").toColumnRowKey());
  protected static final GridColumnRowKey D5 = new GridColumnRowKey("Alpha", A1Key.fromString("D5").toColumnRowKey());
  protected static final CellQuery D1_TO_D3_RANGE = CellQuery.builder()
      .grid("Alpha")
      .columnsBetween(D1.getColumnIndex(), D3.getColumnIndex())
      .rowsBetween(D1.getRowIndex(), D3.getRowIndex())
      .build();
  protected static final CellQuery D1_TO_D5_RANGE = CellQuery.builder()
      .grid("Alpha")
      .columnsBetween(D1.getColumnIndex(), D5.getColumnIndex())
      .rowsBetween(D1.getRowIndex(), D5.getRowIndex())
      .build();

  @Mock
  protected Function<Object, Object> lookup = mock(Function.class);

  @Mock
  protected BiFunction<GridColumnRowKey, Object, Object> collateralLookup = mock(BiFunction.class);
}
