import { Compare } from "../../utils/Compare";

/**
 * Range is essentially Google Guava's Range: https://github.com/google/guava/wiki/RangesExplained.
 */
export class Range {
  readonly lower: number | null;
  readonly upper: number | null;

  constructor(lower: number | null, upper: number | null) {
    this.lower = lower;
    this.upper = upper;
  }

  /**
   * Create a closed range.
   * @param lower - lower bound.
   * @param upper - upper bound.
   */
  static closed(lower: number, upper: number): Range {
    return new Range(lower, upper);
  }

  /**
   * Create a range with a lower bound, but no upper bound.
   * @param lower - lower bound.
   */
  static atLeast(lower: number): Range {
    return new Range(lower, null);
  }

  /**
   * Returns the minimal range that encloses both this range and other. If the ranges are both connected, this is their
   * union.
   * @param other - other range
   */
  span(other: Range): Range {
    const lowerCmp = Compare.numberComparison(this.lower, other.lower);
    const upperCmp = Compare.numberComparison(this.upper, other.upper);
    if (lowerCmp <= 0 && upperCmp >= 0) {
      return this;
    } else if (lowerCmp >= 0 && upperCmp <= 0) {
      return other;
    } else {
      const newLower = lowerCmp <= 0 ? this.lower : other.lower;
      const newUpper = upperCmp >= 0 ? this.upper : other.upper;
      return new Range(newLower, newUpper);
    }
  }

  /**
   * Tests if these ranges are connected
   * @param other - other range.
   */
  isConnected(other: Range): boolean {
    return (
      Compare.numberComparison(this.lower, other.upper === null ? Infinity : other.upper) <= 0 &&
      Compare.numberComparison(other.lower, this.upper === null ? Infinity : this.upper) <= 0
    );
  }

  /**
   * Returns the maximal range enclosed by both this range and other (which exists iff these ranges are connected).
   * @param connectedRange - connected range.
   */
  intersection(connectedRange: Range): Range {
    const lowerCmp = Compare.numberComparison(this.lower, connectedRange.lower);
    const upperCmp = Compare.numberComparison(this.upper, connectedRange.upper);
    if (lowerCmp >= 0 && upperCmp <= 0) {
      return this;
    } else if (lowerCmp <= 0 && upperCmp >= 0) {
      return connectedRange;
    } else {
      const newLower = lowerCmp >= 0 ? this.lower : connectedRange.lower;
      const newUpper = upperCmp <= 0 ? this.upper : connectedRange.upper;
      return new Range(newLower, newUpper);
    }
  }

  /**
   * Returns the lower endpoint of this range.
   */
  lowerEndpoint(): number {
    return this.lower;
  }

  /**
   * Returns the upper endpoint of this range.
   */
  upperEndpoint(): number {
    return this.upper;
  }

  /**
   * Tests if this range uas an upper bound.
   */
  hasUpperBound(): boolean {
    return this.upper !== null;
  }

  /**
   * Tests if this range has a lower bound.
   */
  hasLowerBound(): boolean {
    return this.lower !== null;
  }
}
