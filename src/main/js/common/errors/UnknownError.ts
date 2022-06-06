import { AbstractStandardError } from "./StandardError";
import { StatusCodes } from "./StatusCodes";
import { StatusName } from "./StatusName";

export class UnknownError extends AbstractStandardError {
  constructor(message = "Unknown server error.") {
    super(StatusCodes.UNKNOWN, message, StatusName.UNKNOWN);
  }
}
