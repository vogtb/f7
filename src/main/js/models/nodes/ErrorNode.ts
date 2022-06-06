import { F7Exception } from "../../errors/F7Exception";
import { Node } from "./Node";
import { NodeType } from "./NodeType";

/**
 * Error node is a literal representation of an F7 error. For now, this wraps an {@link F7Exception}.
 */
export class ErrorNode extends Object implements Node {
  readonly type: NodeType = NodeType.Error;
  readonly value: F7Exception;

  constructor(value: F7Exception) {
    super();
    this.value = value;
  }

  toString() {
    return this.value.name.toString();
  }
}
