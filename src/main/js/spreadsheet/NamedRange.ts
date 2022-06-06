import { Length } from "class-validator";

export interface INamedRange {
  name: string;
  ref: string;
}

/**
 * A named range.
 */
export class NamedRange implements INamedRange {
  /**
   * The name of the named range.
   */
  @Length(1, 255)
  name: string;
  /**
   * The ref of the named range that it represents.
   */
  @Length(1, 255)
  ref: string;

  constructor(name: string, ref: string) {
    this.name = name;
    this.ref = ref;
  }
}
