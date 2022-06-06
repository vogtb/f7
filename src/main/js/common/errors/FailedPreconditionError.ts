import { AbstractStandardError } from "./StandardError";
import { StatusCodes } from "./StatusCodes";
import { StatusName } from "./StatusName";

export class FailedPreconditionError extends AbstractStandardError {
  constructor(message = "Request can not be executed in the current system state") {
    super(StatusCodes.FAILED_PRECONDITION, message, StatusName.FAILED_PRECONDITION);
  }
}
