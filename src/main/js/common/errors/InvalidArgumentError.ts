import { AbstractStandardError } from "./StandardError";
import { StatusCodes } from "./StatusCodes";
import { StatusName } from "./StatusName";

export class InvalidArgumentError extends AbstractStandardError {
  constructor(message = "Client provided an invalid argument.") {
    super(StatusCodes.INVALID_ARGUMENT, message, StatusName.INVALID_ARGUMENT);
  }
}
