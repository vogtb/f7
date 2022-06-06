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

public class CONCATTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(CONCAT.SELF.apply(null, 10.0, 10.0)).isEqualTo("1010");
    assertThat(CONCAT.SELF.apply(null, "One", "Two")).isEqualTo("OneTwo");
    assertThat(CONCAT.SELF.apply(null, 44182.1, "Two")).isEqualTo("44182.1Two");
    assertThat(CONCAT.SELF.apply(null, true, false)).isEqualTo("TRUEFALSE");
    assertThat(CONCAT.SELF.apply(null, "", "")).isEqualTo("");
  }

  @Test
  public void testApply_grid() {
    assertThat(CONCAT.SELF.apply(null,
        Grid.builder().add(0, 0, "Hello").add(0, 1, "I am not invited to the concat party...").build(),
        Grid.builder().add(0, 0, "There").add(0, 1, "Neither am I....").build()
    )).isEqualTo("HelloThere");
    assertThat(CONCAT.SELF.apply(null,
        Grid.builder().add(0, 0, "Hello").build(),
        Grid.builder().add(0, 0, "There").build()
    )).isEqualTo("HelloThere");
  }

  @Test
  public void test_lookup() {
    CONCAT F = new CONCAT(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn("One");
    when(collateralLookup.apply(A1, G19_RANGE)).thenReturn("Two");
    assertThat(F.apply(A1, M22_RANGE, G19_RANGE)).isEqualTo("OneTwo");
    verify(collateralLookup).apply(A1, M22_RANGE);
    verify(collateralLookup).apply(A1, G19_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(CONCAT.SELF.apply(null, "One", new ValueException())).isEqualTo(new ValueException());
    assertThat(CONCAT.SELF.apply(null, new ValueException(), "Two")).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(CONCAT.SELF.apply(null, "Too few")).isEqualTo(new NAException());
    assertThat(CONCAT.SELF.apply(null, "A", "B", "Too many")).isEqualTo(new NAException());
  }
}
