import { DivException } from "./DivException";
import { AllF7ExceptionNames, F7ExceptionName } from "./F7ExceptionName";
import { NAException } from "./NAException";
import { NameException } from "./NameException";
import { NullException } from "./NullException";
import { NumException } from "./NumException";
import { ParseException } from "./ParseException";
import { RefException } from "./RefException";
import { ValueException } from "./ValueException";

export function f7ExceptionFromString(name: string) {
  if (AllF7ExceptionNames.has(name as F7ExceptionName)) {
    switch (name) {
      case "#NULL!":
        return new NullException("");
      case "#DIV/0!":
        return new DivException("");
      case "#VALUE!":
        return new ValueException("");
      case "#REF!":
        return new RefException("");
      case "#NAME?":
        return new NameException("");
      case "#NUM!":
        return new NumException("");
      case "#N/A":
        return new NAException("");
      case "#ERROR!":
        return new ParseException("");
      default:
        throw new ParseException("");
    }
  }
}
