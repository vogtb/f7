import { isUndefined } from "./Types";

/**
 * Similar to Java's Optional. Better than using null, or undefined.
 */

export class Optional<T> {
  private value: T | any = null;

  constructor(value?: T) {
    this.value = value;
  }

  /**
   * Create new Optional with the value. If the value is null will result in the same Optional as Optional.empty().
   * @param value
   */
  static of<T>(value: T): Optional<T> {
    return new Optional<T>(value);
  }

  /**
   * Create new, empty optional.
   */
  static empty<T>(): Optional<T> {
    return new Optional<T>();
  }

  /**
   * Is the value in this Optional present, and non-null, and non-undefined?
   */
  isPresent(): boolean {
    return !this.isEmpty();
  }

  /**
   * Is the value in this Optional absent or null or undefined?
   */
  isEmpty(): boolean {
    return this.value === null || isUndefined(this.value);
  }

  /**
   * Get the value in this Optional. If the value is null or undefined, will return null or undefined.
   */
  get(): T {
    return this.value;
  }

  /**
   * If the value of this Optional is empty, null, or undefined, will return a given default value.
   * @param defaultValue
   */
  getOrDefault(defaultValue: T): T {
    return this.isEmpty() ? defaultValue : this.value;
  }

  /**
   * Easy way to render what the value is, and indicate that this is an Optional.
   */
  public toString(): string {
    return `Optional[${this.isEmpty() ? "null" : this.value}]`;
  }
}
