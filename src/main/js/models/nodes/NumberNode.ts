import { Node } from "./Node";
import { NodeType } from "./NodeType";

/**
 * Number node is a wrapper for a number.
 */
export class NumberNode extends Object implements Node {
  readonly type: NodeType = NodeType.Number;
  readonly value: number;

  constructor(value: number) {
    super();
    this.value = value;
  }

  toString() {
    return this.value.toString();
  }
}
