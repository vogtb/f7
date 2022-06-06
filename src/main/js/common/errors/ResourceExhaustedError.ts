import { AbstractStandardError } from "./StandardError";
import { StatusCodes } from "./StatusCodes";
import { StatusName } from "./StatusName";

export class ResourceExhaustedError extends AbstractStandardError {
  constructor(message = "Either out of resource quota or reaching rate limiting.") {
    super(StatusCodes.RESOURCE_EXHAUSTED, message, StatusName.RESOURCE_EXHAUSTED);
  }
}
