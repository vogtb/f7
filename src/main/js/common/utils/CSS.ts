import { isUndefined } from "./Types";

/**
 * Join class names together.
 * @param values - to join together.
 */
export function classNames(...values: Array<string>): string {
  return values
    .filter(function (value: string) {
      return value != "" && !isUndefined(value);
    })
    .join(" ");
}

/**
 * Return the class if the value to check is true
 * @param className
 * @param value
 */
export function includeClassIf(className: string, value: boolean) {
  if (value) {
    return className;
  }
  return "";
}
