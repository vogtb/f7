/**
 * Errors roughly derived from https://cloud.google.com/apis/design/errors.
 */
export enum StatusName {
  /**
   *  No error.
   */
  OK = "OK",
  /**
   * Client specified an invalid argument. Check error message and error details for more information.
   */
  INVALID_ARGUMENT = "INVALID_ARGUMENT",
  /**
   * Request can not be executed in the current system state, such as deleting a non-empty directory.
   */
  FAILED_PRECONDITION = "FAILED_PRECONDITION",
  /**
   * Client specified an invalid range.
   */
  OUT_OF_RANGE = "OUT_OF_RANGE",
  /**
   * Request not authenticated due to missing, invalid, or expired OAuth token.
   */
  UNAUTHENTICATED = "UNAUTHENTICATED",
  /**
   * Either out of resource quota or reaching rate limiting.
   */
  RESOURCE_EXHAUSTED = "RESOURCE_EXHAUSTED",
  /**
   * Client does not have sufficient permission. This can happen because the OAuth token does not have the right scopes,
   * the client doesn't have permission, or the API has not been enabled for the client project.
   */
  PERMISSION_DENIED = "PERMISSION_DENIED",
  /**
   * A specified resource is not found, or the request is rejected by undisclosed reasons, such as whitelisting.
   */
  NOT_FOUND = "NOT_FOUND",
  /**
   * Concurrency conflict, such as read-modify-write conflict.
   */
  ABORTED = "ABORTED",
  /**
   * The resource that a client tried to create already exists.
   */
  ALREADY_EXISTS = "ALREADY_EXISTS",
  /**
   * Request cancelled by the client.
   */
  CANCELLED = "CANCELLED",
  /**
   * Unknown server error. Typically a server bug.
   */
  UNKNOWN = "UNKNOWN",
  /**
   * Internal server error. Typically a server bug.
   */
  INTERNAL = "INTERNAL",
  /**
   * API method not implemented by the server.
   */
  NOT_IMPLEMENTED = "NOT_IMPLEMENTED",
  /**
   * Service unavailable. Typically the server is down.
   */
  UNAVAILABLE = "UNAVAILABLE",
  /**
   * Request deadline exceeded. This will happen only if the caller sets a deadline that is shorter than the method's
   * default deadline (i.e. requested deadline is not enough for the server to process the request) and the request
   * did not finish within the deadline.
   */
  DEADLINE_EXCEEDED = "DEADLINE_EXCEEDED",
}
