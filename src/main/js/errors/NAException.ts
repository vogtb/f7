import { F7Exception } from "./F7Exception";
import { F7ExceptionName } from "./F7ExceptionName";

export class NAException extends F7Exception {
  constructor(message?: string) {
    super(F7ExceptionName.NA, message);
  }
}
