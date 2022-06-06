import { AbstractStandardError } from "./StandardError";
import { StatusCodes } from "./StatusCodes";
import { StatusName } from "./StatusName";

export class ForbiddenError extends AbstractStandardError {
  constructor(
    message = "You are not permitted to access this resource or perform this operation."
  ) {
    super(StatusCodes.PERMISSION_DENIED, message, StatusName.PERMISSION_DENIED);
  }
}
