/**
 * If a value is null-ish (null or undefined) convert it to an empty string, otherwise convert it to a string.
 * @param value to convert
 */
export function nullableToString(value: any): string {
  if (value === null || value === undefined) {
    return "";
  }
  return value.toString();
}

/**
 * Pull the values from an object.
 * @param object
 */
export function objectValues<T>(object: { [index: string]: T }) {
  return Object.keys(object).map((key) => object[key]);
}
