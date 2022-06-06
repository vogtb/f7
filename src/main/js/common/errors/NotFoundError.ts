import { AbstractStandardError } from "./StandardError";
import { StatusCodes } from "./StatusCodes";
import { StatusName } from "./StatusName";

export class NotFoundError extends AbstractStandardError {
  constructor(
    message = "Specified resource is not found, or the request is rejected by undisclosed reasons."
  ) {
    super(StatusCodes.NOT_FOUND, message, StatusName.NOT_FOUND);
  }
}
