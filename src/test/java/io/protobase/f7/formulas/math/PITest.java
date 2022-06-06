package io.protobase.f7.formulas.math;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class PITest extends TestFormula {

  @Test
  public void testApply() {
    assertThat(PI.SELF.apply(null)).isEqualTo(Math.PI);
  }

  @Test
  public void testApply_errorFromArgumentsMismatch() {
    assertThat(RAND.SELF.apply(null, "Too many")).isEqualTo(new NAException());
  }
}