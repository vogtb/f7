export enum F7ExceptionName {
  DIV = "#DIV/0!",
  VALUE = "#VALUE!",
  REF = "#REF!",
  NAME = "#NAME?",
  NUM = "#NUM!",
  NULL = "#NULL!",
  NA = "#N/A",
  PARSE = "#ERROR!",
}

export const AllF7ExceptionNames = new Set([
  F7ExceptionName.DIV,
  F7ExceptionName.VALUE,
  F7ExceptionName.REF,
  F7ExceptionName.NAME,
  F7ExceptionName.NUM,
  F7ExceptionName.NULL,
  F7ExceptionName.NA,
  F7ExceptionName.PARSE,
]);

export function getExceptionCode(exception: F7ExceptionName): number {
  switch (exception) {
    case F7ExceptionName.NULL:
      return 1;
    case F7ExceptionName.DIV:
      return 2;
    case F7ExceptionName.VALUE:
      return 3;
    case F7ExceptionName.REF:
      return 4;
    case F7ExceptionName.NAME:
      return 5;
    case F7ExceptionName.NUM:
      return 6;
    case F7ExceptionName.NA:
      return 7;
    case F7ExceptionName.PARSE:
      return 8;
    default:
      return 0;
  }
}

export function getExceptionValue(exception: F7ExceptionName): number {
  switch (exception) {
    case F7ExceptionName.NULL:
      return 0;
    case F7ExceptionName.DIV:
      return 7;
    case F7ExceptionName.VALUE:
      return 15;
    case F7ExceptionName.REF:
      return 23;
    case F7ExceptionName.NAME:
      return 29;
    case F7ExceptionName.NUM:
      return 36;
    case F7ExceptionName.NA:
      return 42;
    case F7ExceptionName.PARSE:
      return 0;
    default:
      return 0;
  }
}
