import { AbstractStandardError } from "./StandardError";
import { StatusCodes } from "./StatusCodes";
import { StatusName } from "./StatusName";

export class NotImplementedError extends AbstractStandardError {
  constructor(message = "Method not implemented.") {
    super(StatusCodes.NOT_IMPLEMENTED, message, StatusName.NOT_IMPLEMENTED);
  }
}
