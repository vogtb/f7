import { AbstractStandardError } from "./StandardError";
import { StatusCodes } from "./StatusCodes";
import { StatusName } from "./StatusName";

export class AlreadyExistsError extends AbstractStandardError {
  constructor(message = "This resource already exists") {
    super(StatusCodes.ALREADY_EXISTS, message, StatusName.ALREADY_EXISTS);
  }
}
