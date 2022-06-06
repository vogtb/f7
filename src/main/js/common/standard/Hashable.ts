export interface Hashable {
  readonly hashKey: string;

  equals(other: unknown): boolean;
}
