package io.protobase.f7.utils;

public class NumberUtils {
  public static boolean isZero(double value) {
    return value == 0 || value == -0;
  }
}
