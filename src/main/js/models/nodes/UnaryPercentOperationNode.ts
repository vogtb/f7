import { Node } from "./Node";
import { NodeType } from "./NodeType";

/**
 * Unary percent operation is essentially division by 100.
 */
export class UnaryPercentOperationNode extends Object implements Node {
  readonly type: NodeType = NodeType.UnaryPercentOperation;
  readonly operand: Node;

  constructor(operand: Node) {
    super();
    this.operand = operand;
  }

  toString() {
    return `${this.operand.toString()}%`;
  }
}
