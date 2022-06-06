import { AbstractStandardError } from "./StandardError";
import { StatusCodes } from "./StatusCodes";
import { StatusName } from "./StatusName";

export class AbortedError extends AbstractStandardError {
  constructor(message = "Concurrency conflict") {
    super(StatusCodes.ABORTED, message, StatusName.ABORTED);
  }
}
