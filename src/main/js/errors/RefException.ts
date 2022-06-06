import { F7Exception } from "./F7Exception";
import { F7ExceptionName } from "./F7ExceptionName";

export class RefException extends F7Exception {
  constructor(message?: string) {
    super(F7ExceptionName.REF, message);
  }
}
