export function isNotNull<T>(a: T): boolean {
  return a !== null;
}

export function isNull<T>(a: T): boolean {
  return a === null;
}

export function isNotUndefined<T>(a: T): boolean {
  return a !== undefined;
}

export function isUndefined<T>(a: T): boolean {
  return a === undefined;
}
