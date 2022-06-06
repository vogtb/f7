package io.protobase.f7.models;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class GridTest {
  private Grid<String> grid;

  @Before
  public void setup() {
    grid = new Grid<>(4, 6);
  }

  @Test
  public void constructor() {
    assertThat(grid.getColumnSize()).isEqualTo(4);
    assertThat(grid.getRowSize()).isEqualTo(6);
  }

  @Test
  public void constructor_negativeColumns() {
    grid = null;
    try {
      grid = new Grid<>(-1, 4);
      assertThat(grid).isNull();
    } catch (IndexOutOfBoundsException exception) {
      assertThat(exception).isNotNull();
    }
  }

  @Test
  public void constructor_negativeRows() {
    grid = null;
    try {
      grid = new Grid<>(4, -1);
      assertThat(grid).isNull();
    } catch (IndexOutOfBoundsException exception) {
      assertThat(exception).isNotNull();
    }
  }

  @Test
  public void set() {
    grid.set(0, 0, "0-0");
    assertThat(grid.get(0, 0)).isEqualTo("0-0");
    grid.set(0, 1, "0-1");
    assertThat(grid.get(0, 1)).isEqualTo("0-1");
    grid.set(1, 1, "1-1");
    assertThat(grid.get(1, 1)).isEqualTo("1-1");
    grid.set(1, 0, "1-0");
    assertThat(grid.get(1, 0)).isEqualTo("1-0");
  }

  @Test
  public void set_bumpSize() {
    assertThat(grid.getColumnSize()).isEqualTo(4);
    assertThat(grid.getRowSize()).isEqualTo(6);
    grid.set(10, 21, "10-21");
    assertThat(grid.get(10, 21)).isEqualTo("10-21");
    assertThat(grid.getColumnSize()).isEqualTo(11);
    assertThat(grid.getRowSize()).isEqualTo(22);
  }

  @Test
  public void get() {
    grid.set(0, 0, "0-0");
    assertThat(grid.get(0, 0)).isEqualTo("0-0");
    assertThat(grid.get(1, 3)).isNull();
  }

  @Test
  public void iterator_default() {
    buildFullGrid();
    List<String> found = new ArrayList<>();
    for (String string : grid) {
      found.add(string);
    }
    /*
     *   | 0    1    2    3
     * --+-----------------
     * 0 | 0    1    2    3
     * 1 | 4    5    6    7
     * 2 | 8    9    10   11
     * 3 | 12   13   14   15
     * 4 | 16   17   18   19
     * 5 | 20   21   22   23
     */
    assertThat(found.size()).isEqualTo((grid.getColumnSize() * grid.getRowSize()));
    assertThat(found).isEqualTo(ImmutableList.of(
        "0-0",
        "1-0",
        "2-0",
        "3-0",
        "0-1",
        "1-1",
        "2-1",
        "3-1",
        "0-2",
        "1-2",
        "2-2",
        "3-2",
        "0-3",
        "1-3",
        "2-3",
        "3-3",
        "0-4",
        "1-4",
        "2-4",
        "3-4",
        "0-5",
        "1-5",
        "2-5",
        "3-5"
    ));
  }

  @Test
  public void iterator_indexIterator() {
    buildFullGrid();
    grid.remove(0, 0);
    grid.remove(1, 2);
    grid.remove(3, 4);
    grid.remove(2, 5);
    List<ColumnRowKey> found = new ArrayList<>();
    grid.indexIterator().forEachRemaining(found::add);
    assertThat(found.size()).isEqualTo((grid.getColumnSize() * grid.getRowSize()));
    assertThat(found).isEqualTo(ImmutableList.of(
        new ColumnRowKey(0, 0),
        new ColumnRowKey(1, 0),
        new ColumnRowKey(2, 0),
        new ColumnRowKey(3, 0),
        new ColumnRowKey(0, 1),
        new ColumnRowKey(1, 1),
        new ColumnRowKey(2, 1),
        new ColumnRowKey(3, 1),
        new ColumnRowKey(0, 2),
        new ColumnRowKey(1, 2),
        new ColumnRowKey(2, 2),
        new ColumnRowKey(3, 2),
        new ColumnRowKey(0, 3),
        new ColumnRowKey(1, 3),
        new ColumnRowKey(2, 3),
        new ColumnRowKey(3, 3),
        new ColumnRowKey(0, 4),
        new ColumnRowKey(1, 4),
        new ColumnRowKey(2, 4),
        new ColumnRowKey(3, 4),
        new ColumnRowKey(0, 5),
        new ColumnRowKey(1, 5),
        new ColumnRowKey(2, 5),
        new ColumnRowKey(3, 5)
    ));
  }

  @Test
  public void iterator_reverseIndexIterator() {
    buildFullGrid();
    grid.remove(0, 0);
    grid.remove(1, 2);
    grid.remove(3, 4);
    grid.remove(2, 5);
    List<ColumnRowKey> found = new ArrayList<>();
    grid.reverseIndexIterator().forEachRemaining(found::add);
    assertThat(found.size()).isEqualTo((grid.getColumnSize() * grid.getRowSize()));
    assertThat(found).isEqualTo(ImmutableList.of(
        new ColumnRowKey(3, 5),
        new ColumnRowKey(2, 5),
        new ColumnRowKey(1, 5),
        new ColumnRowKey(0, 5),
        new ColumnRowKey(3, 4),
        new ColumnRowKey(2, 4),
        new ColumnRowKey(1, 4),
        new ColumnRowKey(0, 4),
        new ColumnRowKey(3, 3),
        new ColumnRowKey(2, 3),
        new ColumnRowKey(1, 3),
        new ColumnRowKey(0, 3),
        new ColumnRowKey(3, 2),
        new ColumnRowKey(2, 2),
        new ColumnRowKey(1, 2),
        new ColumnRowKey(0, 2),
        new ColumnRowKey(3, 1),
        new ColumnRowKey(2, 1),
        new ColumnRowKey(1, 1),
        new ColumnRowKey(0, 1),
        new ColumnRowKey(3, 0),
        new ColumnRowKey(2, 0),
        new ColumnRowKey(1, 0),
        new ColumnRowKey(0, 0)
    ));
  }

  @Test
  public void iterator_indexIteratorWithStartAndEndIndex() {
    buildFullGrid();
    /*
     *   | 0    1    2    3
     * --+----------------
     * 0 | N    N    N    N
     * 1 | N    N    N    N
     * 2 | N    N    N    N
     * 3 | N    5    4    N
     * 4 | N    3    2    N
     * 5 | N    1    0    N
     */
    assertThat(Lists.newArrayList(grid.reverseIndexIterator(2, 1, 5, 3))).isEqualTo(ImmutableList.of(
        new ColumnRowKey(2, 5),
        new ColumnRowKey(1, 5),
        new ColumnRowKey(2, 4),
        new ColumnRowKey(1, 4),
        new ColumnRowKey(2, 3),
        new ColumnRowKey(1, 3)
    ));
    /*
     *   | 0    1    2    3
     * --+----------------
     * 0 | N    N    3    2
     * 1 | N    N    1    0
     * 2 | N    N    N    N
     * 3 | N    N    N    N
     * 4 | N    N    N    N
     * 5 | N    N    N    N
     */
    assertThat(Lists.newArrayList(grid.reverseIndexIterator(3, 2, 1, 0))).isEqualTo(ImmutableList.of(
        new ColumnRowKey(3, 1),
        new ColumnRowKey(2, 1),
        new ColumnRowKey(3, 0),
        new ColumnRowKey(2, 0)
    ));
  }

  @Test
  public void addGridToBottom() {
    Grid<String> first = new Grid<>(2, 2);
    first.set(0, 0, "A");
    first.set(0, 1, "B");
    first.set(1, 1, "C");
    first.set(1, 0, "D");
    Grid<String> second = new Grid<>(2, 2);
    second.set(0, 0, "E");
    second.set(0, 1, "F");
    second.set(1, 1, "G");
    second.set(1, 0, "H");
    first.addGridToBottom(second);
    assertThat(first.getColumnSize()).isEqualTo(2);
    assertThat(first.getRowSize()).isEqualTo(4);
    assertThat(first.get(0, 0)).isEqualTo("A");
    assertThat(first.get(0, 1)).isEqualTo("B");
    assertThat(first.get(1, 1)).isEqualTo("C");
    assertThat(first.get(1, 0)).isEqualTo("D");
    assertThat(first.get(1, 2)).isEqualTo("H");
    assertThat(first.get(1, 3)).isEqualTo("G");
    assertThat(first.get(0, 2)).isEqualTo("E");
    assertThat(first.get(0, 3)).isEqualTo("F");
  }

  @Test
  public void addGridToRight() {
    Grid<String> first = new Grid<>(2, 2);
    first.set(0, 0, "A");
    first.set(0, 1, "B");
    first.set(1, 1, "C");
    first.set(1, 0, "D");
    Grid<String> second = new Grid<>(2, 2);
    second.set(0, 0, "E");
    second.set(0, 1, "F");
    second.set(1, 1, "G");
    second.set(1, 0, "H");
    first.addGridToRight(second);
    assertThat(first.getColumnSize()).isEqualTo(4);
    assertThat(first.getRowSize()).isEqualTo(2);
    assertThat(first.get(0, 0)).isEqualTo("A");
    assertThat(first.get(0, 1)).isEqualTo("B");
    assertThat(first.get(1, 1)).isEqualTo("C");
    assertThat(first.get(1, 0)).isEqualTo("D");
    assertThat(first.get(2, 0)).isEqualTo("E");
    assertThat(first.get(3, 0)).isEqualTo("H");
    assertThat(first.get(2, 1)).isEqualTo("F");
    assertThat(first.get(3, 1)).isEqualTo("G");
  }

  private void buildFullGrid() {
    for (int column = 0; column < grid.getColumnSize(); column++) {
      for (int row = 0; row < grid.getRowSize(); row++) {
        grid.set(column, row, column + "-" + row);
      }
    }
  }
}
