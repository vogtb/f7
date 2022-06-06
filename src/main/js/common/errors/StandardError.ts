import { StatusName } from "./StatusName";

export interface StandardError {
  code: number;
  status: number;
  message: string;
  name: StatusName;
}

/**
 * Abstract constructor to help with creation of all other errors.
 */
export abstract class AbstractStandardError extends Error implements StandardError {
  code: number;
  status: number;
  message: string;
  name: StatusName;

  protected constructor(code: number, message: string, name: StatusName) {
    super(message);
    this.message = message;
    this.code = code;
    this.status = code;
    this.name = name;
  }
}
