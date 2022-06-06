package io.protobase.f7.spreadsheet;

import io.protobase.f7.errors.RefException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestF7CodeExecutor;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class F7CodeExecutorGeneralArrayLiteralTest extends TestF7CodeExecutor {
  @Test
  public void test_EmptyArrayLiteralCausesRefError() {
    assertThat(run("= {}")).isEqualTo(new RefException());
  }

  @Test
  public void test_SingleValue() {
    assertThat(run("= {1}")).isEqualTo(Grid.builder().add(0, 0, 1.0).build());
  }

  @Test
  public void test_SingleValue_Nested() {
    assertThat(run("= {{{{{{1}}}}}}")).isEqualTo(Grid.builder().add(0, 0, 1.0).build());
  }

  @Test
  public void test_ColumnWise_ProjectsIntoNextColumns() {
    assertThat(run("= {1, 2, 3, 4}")).isEqualTo(Grid.builder()
        .add(0, 0, 1.0)
        .add(1, 0, 2.0)
        .add(2, 0, 3.0)
        .add(3, 0, 4.0)
        .build());
  }

  @Test
  public void test_ColumnWise_Nested() {
    assertThat(run("= {1, {2, {3, {4}}}}")).isEqualTo(Grid.builder()
        .add(0, 0, 1.0)
        .add(1, 0, 2.0)
        .add(2, 0, 3.0)
        .add(3, 0, 4.0)
        .build());
  }

  @Test
  public void test_RowWise_ProjectsIntoNextRows() {
    assertThat(run("= {1; 2; 3; 4}")).isEqualTo(Grid.builder()
        .add(0, 0, 1.0)
        .add(0, 1, 2.0)
        .add(0, 2, 3.0)
        .add(0, 3, 4.0)
        .build());
    assertThat(run("= {11;9;5;3;1}")).isEqualTo(Grid.builder()
        .add(0, 0, 11.0)
        .add(0, 1, 9.0)
        .add(0, 2, 5.0)
        .add(0, 3, 3.0)
        .add(0, 4, 1.0)
        .build());
  }

  @Test
  public void test_RowWise_Nested() {
    assertThat(run("= {1; {2; {3; {4}}}}")).isEqualTo(Grid.builder()
        .add(0, 0, 1.0)
        .add(0, 1, 2.0)
        .add(0, 2, 3.0)
        .add(0, 3, 4.0)
        .build());
    assertThat(run("= {1;{2;{3;4}};{5}}")).isEqualTo(Grid.builder()
        .add(0, 0, 1.0)
        .add(0, 1, 2.0)
        .add(0, 2, 3.0)
        .add(0, 3, 4.0)
        .add(0, 4, 5.0)
        .build());
  }

  @Test
  public void test_ColumnAndRow_Row() {
    assertThat(run("= {1, 2; 3, 4}")).isEqualTo(Grid.builder()
        .add(0, 0, 1.0)
        .add(1, 0, 2.0)
        .add(0, 1, 3.0)
        .add(1, 1, 4.0)
        .build());
    assertThat(run("= {1, 2, 3;4, 5, 6}")).isEqualTo(Grid.builder()
        .add(0, 0, 1.0)
        .add(1, 0, 2.0)
        .add(2, 0, 3.0)
        .add(0, 1, 4.0)
        .add(1, 1, 5.0)
        .add(2, 1, 6.0)
        .build());
  }

  @Test
  public void test_ColumnAndRow_Column() {
    assertThat(run("= {{1; 2},{3; 4}}")).isEqualTo(Grid.builder()
        .add(0, 0, 1.0)
        .add(1, 0, 3.0)
        .add(0, 1, 2.0)
        .add(1, 1, 4.0)
        .build());
    assertThat(run("= {{{}; {}},{{}; {}}}")).isEqualTo(Grid.builder()
        .add(0, 0, new RefException())
        .add(1, 0, new RefException())
        .add(0, 1, new RefException())
        .add(1, 1, new RefException())
        .build());
    assertThat(run("= {{{0.0, 1.0, 2.0};{0.1, 1.1, 2.1}};{{0.2, 1.2, 2.2};{0.3, 1.3, 2.3}};{{0.4, 1.4, 2.4};{0.5, 1.5, 2.5}}}")).isEqualTo(Grid.builder()
        .add(0, 0, 0.0)
        .add(0, 1, 0.1)
        .add(0, 2, 0.2)
        .add(0, 3, 0.3)
        .add(0, 4, 0.4)
        .add(0, 5, 0.5)
        .add(1, 0, 1.0)
        .add(1, 1, 1.1)
        .add(1, 2, 1.2)
        .add(1, 3, 1.3)
        .add(1, 4, 1.4)
        .add(1, 5, 1.5)
        .add(2, 0, 2.0)
        .add(2, 1, 2.1)
        .add(2, 2, 2.2)
        .add(2, 3, 2.3)
        .add(2, 4, 2.4)
        .add(2, 5, 2.5)
        .build());
  }

  @Test
  public void test_ColumnAndRow_ErrorFromArrayDimensionMismatch() {
    assertThat(run("= {1,2;3}")).isEqualTo(new ValueException());
    assertThat(run("= {1,2;3,4,5}")).isEqualTo(new ValueException());
    assertThat(run("= {11;9;5;3;1,2}")).isEqualTo(new ValueException());
    assertThat(run("= {{};{};{},{}}")).isEqualTo(new ValueException());
  }
}
