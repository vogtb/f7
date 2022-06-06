package io.protobase.f7.errors;

public enum F7ExceptionName {
  NULL("#NULL!", 1),
  DIV("#DIV/0!", 2),
  VALUE("#VALUE!", 3),
  REF("#REF!", 4),
  NAME("#NAME?", 5),
  NUM("#NUM!", 6),
  NA("#N/A", 7),
  PARSE("#ERROR!", 8);

  private String name;
  private int code;

  F7ExceptionName(String name, int code) {
    this.name = name;
    this.code = code;
  }

  public static F7ExceptionName fromString(String string) {
    for (F7ExceptionName name : F7ExceptionName.values()) {
      if (name.getName().equalsIgnoreCase(string))
        return name;
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public int getCode() {
    return code;
  }

  @Override
  public String toString() {
    return name;
  }
}
