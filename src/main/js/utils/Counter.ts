/**
 * Uses in .map to peek-count when iterating.
 */
export class Counter<T> {
  private counter = 0;

  count(t: T) {
    this.counter++;
    return t;
  }

  getCount(): number {
    return this.counter;
  }
}
