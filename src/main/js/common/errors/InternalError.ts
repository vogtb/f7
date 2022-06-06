import { AbstractStandardError } from "./StandardError";
import { StatusCodes } from "./StatusCodes";
import { StatusName } from "./StatusName";

export class InternalError extends AbstractStandardError {
  constructor(message = "Internal server error.") {
    super(StatusCodes.INTERNAL, message, StatusName.INTERNAL);
  }
}
