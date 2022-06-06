package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class RANDTest extends TestFormula {

  @Test
  public void testApply() {
    for (int i = 0; i < 100000; i++) {
      Double one = ((Double) RAND.SELF.apply(null));
      assertThat(one).isAtLeast(0.0);
      assertThat(one).isAtMost(1.0);
    }
  }

  @Test
  public void testApply_errorFromArgumentsMismatch() {
    assertThat(RAND.SELF.apply(null, "Too many")).isEqualTo(new NAException());
  }
}