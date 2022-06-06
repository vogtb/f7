import { Hashable } from "./Hashable";

export class HashableSet<T extends Hashable> extends Set<T> implements Hashable {
  get hashKey() {
    return JSON.stringify([...this.values()].map((value) => value.hashKey).sort());
  }

  equals(other: unknown): boolean {
    return other instanceof HashableSet && this.hashKey === (other as HashableSet<T>).hashKey;
  }
}

export class HashSet<T extends Hashable> extends HashableSet<T> {
  static readonly EMPTY = Object.freeze(new HashSet());
  [Symbol.toStringTag]: "Set";
  private readonly elementMap = new Map<string, T>();

  constructor(iterable?: Iterable<T>) {
    super();
    if (iterable) {
      for (const value of iterable) {
        this.add(value);
      }
    }
  }

  get size() {
    return this.elementMap.size;
  }

  add(value: T): this {
    this.elementMap.set(value.hashKey, value);
    return this;
  }

  clear(): void {
    this.elementMap.clear();
  }

  delete(value: T): boolean {
    return this.elementMap.delete(value.hashKey);
  }

  forEach(callback: (value: T, value2: T, set: Set<T>) => void, thisArg?: any): void {
    const self = this;
    this.elementMap.forEach((value) => {
      callback.bind(thisArg)(value, value, self);
    });
  }

  has(value: T): boolean {
    return this.elementMap.has(value.hashKey);
  }

  [Symbol.iterator](): IterableIterator<T> {
    return this.values();
  }

  *entries(): IterableIterator<[T, T]> {
    for (const value of this.elementMap.values()) {
      yield [value, value];
    }
  }

  keys(): IterableIterator<T> {
    return this.elementMap.values();
  }

  values(): IterableIterator<T> {
    return this.elementMap.values();
  }
}
