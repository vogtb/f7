package io.protobase.f7.formulas.logic;

import io.protobase.f7.errors.NAException;
import io.protobase.f7.testutils.TestFormula;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class FALSETest extends TestFormula {

  @Test
  public void test() {
    assertThat(FALSE.SELF.apply(null)).isEqualTo(false);
  }

  @Test
  public void test_errorFromArgumentsMismatch() {
    assertThat(FALSE.SELF.apply(null, "Nope.")).isEqualTo(new NAException());
  }
}
