import { Node } from "./Node";
import { NodeType } from "./NodeType";

/**
 * Unary minus operation is the highest precedence operation, and is given a special node. While we could achieve this
 * by creating a formula-node that is functionally the same thing, I like the idea of 1) getting specific with out
 * node sub-classes, and 2) having something we can render as valid F7 code without mutating the user's input.
 */
export class UnaryMinusOperationNode extends Object implements Node {
  readonly type: NodeType = NodeType.UnaryMinusOperation;
  readonly operand: Node;

  constructor(operand: Node) {
    super();
    this.operand = operand;
  }

  toString() {
    return `-${this.operand.toString()}`;
  }
}
