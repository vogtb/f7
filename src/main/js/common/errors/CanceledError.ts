import { AbstractStandardError } from "./StandardError";
import { StatusCodes } from "./StatusCodes";
import { StatusName } from "./StatusName";

export class CanceledError extends AbstractStandardError {
  constructor(message = "Request cancelled by the client.") {
    super(StatusCodes.CANCELLED, message, StatusName.CANCELLED);
  }
}
