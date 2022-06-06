/**
 * Errors roughly derived from https://cloud.google.com/apis/design/errors.
 */
export namespace StatusCodes {
  export const OK = 200;
  export const INVALID_ARGUMENT = 400;
  export const FAILED_PRECONDITION = 400;
  export const OUT_OF_RANGE = 400;
  export const UNAUTHENTICATED = 401;
  export const PERMISSION_DENIED = 403;
  export const NOT_FOUND = 404;
  export const ABORTED = 409;
  export const ALREADY_EXISTS = 409;
  export const RESOURCE_EXHAUSTED = 429;
  export const CANCELLED = 499;
  export const UNKNOWN = 500;
  export const INTERNAL = 500;
  export const NOT_IMPLEMENTED = 501;
  export const UNAVAILABLE = 503;
  export const DEADLINE_EXCEEDED = 504;

  export const ALL_BAD_CODES = new Set([
    INVALID_ARGUMENT,
    FAILED_PRECONDITION,
    OUT_OF_RANGE,
    UNAUTHENTICATED,
    PERMISSION_DENIED,
    NOT_FOUND,
    ABORTED,
    ALREADY_EXISTS,
    RESOURCE_EXHAUSTED,
    CANCELLED,
    UNKNOWN,
    INTERNAL,
    NOT_IMPLEMENTED,
    UNAVAILABLE,
    DEADLINE_EXCEEDED,
  ]);
}
