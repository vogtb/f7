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

public class TTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(T.SELF.apply(null, 10.0)).isEqualTo("");
    assertThat(T.SELF.apply(null, true)).isEqualTo("");
    assertThat(T.SELF.apply(null, false)).isEqualTo("");
    assertThat(T.SELF.apply(null, "ReturnMe")).isEqualTo("ReturnMe");
  }

  @Test
  public void testApply_grid() {
    assertThat(T.SELF.apply(null,
        Grid.builder().add(0, 0, "Hello").add(0, 1, "I am ignored.").build()
    )).isEqualTo("Hello");
  }

  @Test
  public void test_lookup() {
    T F = new T(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn("Hello");
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo("Hello");
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(T.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(T.SELF.apply(null)).isEqualTo(new NAException());
    assertThat(T.SELF.apply(null, "A", "B")).isEqualTo(new NAException());
  }
}
