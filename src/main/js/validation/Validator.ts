import { validateSync } from "class-validator";
import { InvalidArgumentError } from "../common/errors/InvalidArgumentError";

export class Validator {
  static validate<T>(request: T): T {
    const errors = validateSync(request);
    if (errors.length > 0) {
      throw new InvalidArgumentError(errors[0].toString());
    }
    return request;
  }
}
