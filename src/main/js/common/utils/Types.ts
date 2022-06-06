/**
 * Is the value a function?
 * @param value
 */
import { AbstractStandardError } from "../errors/StandardError";

export function isFunction<T>(value: T): boolean {
  return typeof value === "function";
}

/**
 * Is the value undefined?
 * @param value
 */
export function isUndefined<T>(value: T): boolean {
  return value === undefined;
}

/**
 * Is the value NOT undefined? Could be null.
 * @param value - to check
 */
export function isNotUndefined<T>(value: T) {
  return value !== undefined;
}

/**
 * Are any of the values undefined?
 * @param values
 */
export function anyUndefined<T>(...values: Array<T>) {
  for (const value of values) {
    if (isUndefined(value)) {
      return true;
    }
  }
}

/**
 * Is the value null?
 * @param value
 */
export function isNull<T>(value: T): boolean {
  return value === null;
}

/**
 * Is the value NOT null?
 * @param value
 */
export function isNotNull<T>(value: T): boolean {
  return value !== null;
}

/**
 * Does the object have keys?
 * @param input object to check
 */
export function hasKeys(input: { [index: string]: any } | { [index: number]: any }) {
  return Object.keys(input).length > 0;
}

/**
 * Is the value null or undefined?
 * @param value to check.
 */
export function isNullOrUndefined<T>(value: T): boolean {
  return value === null || value === undefined;
}

/**
 * Cast value as standard error.
 * @param value - to cast.
 */
export function asStandardError(value: any): AbstractStandardError {
  return value as AbstractStandardError;
}
