export class MapEntry<V> {
  readonly key: string;
  readonly value: V;

  constructor(key: string, value: V) {
    this.key = key;
    this.value = value;
  }

  static of<V>(key: string, value: V) {
    return new MapEntry(key, value);
  }

  static entriesToMap<V>(entries: Array<MapEntry<V>>): { [index: string]: V } {
    const m: { [index: string]: V } = {};
    entries.forEach((entry) => {
      m[entry.key] = entry.value;
    });
    return m;
  }

  static mapToEntires<V>(map: { [index: string]: V }): Array<MapEntry<V>> {
    return Object.keys(map).map((key) => MapEntry.of(key, map[key]));
  }
}
