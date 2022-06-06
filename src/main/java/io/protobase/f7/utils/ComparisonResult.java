package io.protobase.f7.utils;

public enum ComparisonResult {
  EQUAL,
  LESS_THAN,
  GREATER_THAN;

  public static ComparisonResult fromComparison(int comparison) {
    if (comparison < 0) {
      return LESS_THAN;
    }
    if (comparison > 0) {
      return GREATER_THAN;
    }
    return EQUAL;
  }
}
