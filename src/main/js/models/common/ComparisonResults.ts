/**
 * Easier way to compare objects.
 */
export enum ComparisonResult {
  EQUAL = "EQUAL",
  LESS_THAN = "LESS_THAN",
  GREATER_THAN = "GREATER_THAN",
}

export function comparisonResultFromNumber(comparison: number) {
  if (comparison < 0) {
    return ComparisonResult.LESS_THAN;
  }
  if (comparison > 0) {
    return ComparisonResult.GREATER_THAN;
  }
  return ComparisonResult.EQUAL;
}
