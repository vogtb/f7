import { F7ExceptionName } from "./F7ExceptionName";

export abstract class F7Exception implements Error {
  readonly name: F7ExceptionName;
  readonly message: string = "";

  protected constructor(name: F7ExceptionName, message = "") {
    this.name = name;
    this.message = message;
  }
}
