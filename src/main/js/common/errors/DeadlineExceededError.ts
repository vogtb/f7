import { AbstractStandardError } from "./StandardError";
import { StatusCodes } from "./StatusCodes";
import { StatusName } from "./StatusName";

export class DeadlineExceededError extends AbstractStandardError {
  constructor(message = "Request deadline exceeded.") {
    super(StatusCodes.DEADLINE_EXCEEDED, message, StatusName.DEADLINE_EXCEEDED);
  }
}
