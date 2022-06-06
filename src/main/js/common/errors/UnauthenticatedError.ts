import { AbstractStandardError } from "./StandardError";
import { StatusCodes } from "./StatusCodes";
import { StatusName } from "./StatusName";

export class UnauthenticatedError extends AbstractStandardError {
  constructor(message = "Request not authenticated.") {
    super(StatusCodes.UNAUTHENTICATED, message, StatusName.UNAUTHENTICATED);
  }
}
