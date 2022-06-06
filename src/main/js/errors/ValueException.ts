import { F7Exception } from "./F7Exception";
import { F7ExceptionName } from "./F7ExceptionName";

export class ValueException extends F7Exception {
  constructor(message?: string) {
    super(F7ExceptionName.VALUE, message);
  }
}
