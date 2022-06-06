package io.protobase.f7.formulas.parser;

import io.protobase.f7.errors.DivException;
import io.protobase.f7.errors.NAException;
import io.protobase.f7.errors.NumException;
import io.protobase.f7.models.Grid;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class TO_TEXTTest extends TestFormula {
  @Test
  public void testApply() {
    assertThat(TO_TEXT.SELF.apply(null, 0.99)).isEqualTo("0.99");
    assertThat(TO_TEXT.SELF.apply(null, 0.489733)).isEqualTo("0.489733");
    assertThat(TO_TEXT.SELF.apply(null, 0.0)).isEqualTo("0");
    assertThat(TO_TEXT.SELF.apply(null, -0.66152156111)).isEqualTo("-0.66152156111");
    assertThat(TO_TEXT.SELF.apply(null, -0.88)).isEqualTo("-0.88");
    assertThat(TO_TEXT.SELF.apply(null, "Already Text")).isEqualTo("Already Text");
    assertThat(TO_TEXT.SELF.apply(null, "")).isEqualTo("");
    assertThat(TO_TEXT.SELF.apply(null, true)).isEqualTo("TRUE");
    assertThat(TO_TEXT.SELF.apply(null, false)).isEqualTo("FALSE");
  }

  @Test
  public void testApply_exceptions() {
    assertThat(TO_TEXT.SELF.apply(null, new DivException())).isEqualTo(new DivException());
    assertThat(TO_TEXT.SELF.apply(null, new NumException())).isEqualTo(new NumException());
  }

  @Test
  public void testApply_grid() {
    assertThat(TO_TEXT.SELF.apply(null,
        Grid.builder().add(0, 0, 19873218.11).add(0, 1, "Don't mind me.").build()
    )).isEqualTo("1.987321811E7");
    assertThat(TO_TEXT.SELF.apply(null,
        Grid.builder().add(0, 0, true).add(0, 1, "Don't mind me.").build()
    )).isEqualTo("TRUE");
  }

  @Test
  public void test_lookup() {
    TO_TEXT F = new TO_TEXT(lookup, collateralLookup);
    when(collateralLookup.apply(A1, M22_RANGE)).thenReturn(10.0);
    assertThat(F.apply(A1, M22_RANGE)).isEqualTo("10");
    verify(collateralLookup).apply(A1, M22_RANGE);
    verifyNoMoreInteractions(collateralLookup);
  }

  @Test
  public void testApply_argumentsMismatch() {
    assertThat(TO_TEXT.SELF.apply(null, "A", "Too many")).isEqualTo(new NAException());
  }
}
