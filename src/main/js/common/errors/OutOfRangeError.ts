import { AbstractStandardError } from "./StandardError";
import { StatusCodes } from "./StatusCodes";
import { StatusName } from "./StatusName";

export class OutOfRangeError extends AbstractStandardError {
  constructor(message = "Client specified an invalid range.") {
    super(StatusCodes.OUT_OF_RANGE, message, StatusName.OUT_OF_RANGE);
  }
}
