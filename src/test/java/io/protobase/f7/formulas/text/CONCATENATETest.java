package io.protobase.f7.formulas.text;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.ValueException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class CONCATENATETest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(CONCATENATE.SELF.apply(null, 10.0, 10.0)).isEqualTo("1010");
    assertThat(CONCATENATE.SELF.apply(null, "One", "Two")).isEqualTo("OneTwo");
    assertThat(CONCATENATE.SELF.apply(null, 44182.1, "Two")).isEqualTo("44182.1Two");
    assertThat(CONCATENATE.SELF.apply(null, true, false)).isEqualTo("TRUEFALSE");
    assertThat(CONCATENATE.SELF.apply(null, "", "")).isEqualTo("");
    assertThat(CONCATENATE.SELF.apply(null, "a", "b", true, "c", 10.1, "d", 11.1111)).isEqualTo("abTRUEc10.1d11.1111");
  }

  @Test
  public void testApply_grid() {
    assertThat(CONCATENATE.SELF.apply(null,
        Grid.builder().add(0, 0, "Hello").add(0, 1, "One").build(),
        Grid.builder().add(0, 0, "There").add(0, 1, "Two").build()
    )).isEqualTo("HelloOneThereTwo");
    assertThat(CONCATENATE.SELF.apply(null,
        Grid.builder().add(0, 0, "Hello").build(),
        Grid.builder().add(0, 0, "There").build()
    )).isEqualTo("HelloThere");
  }

  @Test
  public void test_lookup() {
    Grid grid = Grid.builder()
        .add(0, 0, 1.0)
        .add(0, 1, 2.0)
        .add(0, 2, 3.0)
        .add(0, 3, 4.0)
        .add(0, 4, 5.0)
        .build();
    CONCATENATE F = new CONCATENATE(lookup, collateralLookup);
    when(lookup.apply(D1_TO_D5_RANGE)).thenReturn(grid);
    assertThat(F.apply(A1, D1_TO_D5_RANGE)).isEqualTo("12345");
    verify(lookup).apply(D1_TO_D5_RANGE);
    verifyNoMoreInteractions(lookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(CONCATENATE.SELF.apply(null, "One", new ValueException())).isEqualTo(new ValueException());
    assertThat(CONCATENATE.SELF.apply(null, new ValueException(), "Two")).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(CONCATENATE.SELF.apply(null)).isEqualTo(new NAException());
  }
}
