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

public class LENTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(LEN.SELF.apply(null, 10.0)).isEqualTo(2.0);
    assertThat(LEN.SELF.apply(null, 0.0)).isEqualTo(1.0);
    assertThat(LEN.SELF.apply(null, 0.1)).isEqualTo(3.0);
    assertThat(LEN.SELF.apply(null, -13283210.0)).isEqualTo(9.0);
    assertThat(LEN.SELF.apply(null, 172398713981.23971923712)).isEqualTo(21.0);
    assertThat(LEN.SELF.apply(null, true)).isEqualTo(4.0);
    assertThat(LEN.SELF.apply(null, false)).isEqualTo(5.0);
    assertThat(LEN.SELF.apply(null, "CountMe")).isEqualTo(7.0);
  }

  @Test
  public void testApply_grid() {
    assertThat(LEN.SELF.apply(null,
        Grid.builder().add(0, 0, "Hello").add(0, 1, "I am ignored.").build()
    )).isEqualTo(5.0);
  }

  @Test
  public void test_lookup() {
    LEN F = new LEN(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn("One");
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo(3.0);
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_errorsPassThrough() {
    assertThat(LEN.SELF.apply(null, new ValueException())).isEqualTo(new ValueException());
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(LEN.SELF.apply(null)).isEqualTo(new NAException());
    assertThat(LEN.SELF.apply(null, "A", "B")).isEqualTo(new NAException());
  }
}
