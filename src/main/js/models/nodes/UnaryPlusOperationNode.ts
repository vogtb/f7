import { Node } from "./Node";
import { NodeType } from "./NodeType";

/**
 * Unary plus operation.
 */
export class UnaryPlusOperationNode extends Object implements Node {
  readonly type: NodeType = NodeType.UnaryPlusOperation;
  readonly operand: Node;

  constructor(operand: Node) {
    super();
    this.operand = operand;
  }

  toString() {
    return `+${this.operand.toString()}`;
  }
}
