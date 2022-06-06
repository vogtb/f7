import { AbstractStandardError } from "./StandardError";
import { StatusCodes } from "./StatusCodes";
import { StatusName } from "./StatusName";

export class UnavailableError extends AbstractStandardError {
  constructor(message = "Service unavailable.") {
    super(StatusCodes.UNAVAILABLE, message, StatusName.UNAVAILABLE);
  }
}
