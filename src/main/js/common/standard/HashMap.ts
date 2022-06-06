import { Hashable } from "./Hashable";

export class HashMap<K extends Hashable, V> implements Map<K, V> {
  static readonly EMPTY = Object.freeze(new HashMap());
  [Symbol.toStringTag]: "Map";
  private readonly keyMap = new Map<string, K>();
  private readonly valueMap = new Map<string, V>();

  constructor(iterable?: Iterable<[K, V]>) {
    if (iterable) {
      for (const [key, value] of iterable) {
        this.set(key, value);
      }
    }
  }

  get size() {
    return this.valueMap.size;
  }

  clear(): void {
    this.valueMap.clear();
  }

  delete(key: K): boolean {
    this.keyMap.delete(key.hashKey);
    return this.valueMap.delete(key.hashKey);
  }

  forEach(fn: (value: V, key: K, map: Map<K, V>) => void, thisArg?: any): void {
    const self = this;
    this.valueMap.forEach((value, key) => {
      fn.bind(thisArg)(value, self.keyMap.get(key), self);
    });
  }

  get(key: K): V | undefined {
    return this.valueMap.get(key.hashKey);
  }

  has(key: K): boolean {
    return this.keyMap.has(key.hashKey);
  }

  set(key: K, value: V): this {
    this.keyMap.set(key.hashKey, key);
    this.valueMap.set(key.hashKey, value);
    return this;
  }

  [Symbol.iterator](): IterableIterator<[K, V]> {
    const entries = this.valueMap.entries();
    const keyMap = this.keyMap;
    return {
      next(): IteratorResult<[K, V]> {
        const next = entries.next();
        return {
          done: next.done,
          value: [keyMap.get(next.value[0]), next.value[1]],
        };
      },

      [Symbol.iterator](): IterableIterator<[K, V]> {
        return this;
      },
    };
  }

  *entries(): IterableIterator<[K, V]> {
    for (const [hashKey, value] of this.valueMap.entries()) {
      const key = this.keyMap.get(hashKey);
      yield [key, value];
    }
  }

  keys(): IterableIterator<K> {
    return this.keyMap.values();
  }

  values(): IterableIterator<V> {
    return this.valueMap.values();
  }
}
