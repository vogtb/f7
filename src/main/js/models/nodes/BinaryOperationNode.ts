import { Node } from "./Node";
import { NodeType } from "./NodeType";

/**
 * Represents a binary operation with a left and right node, and an operator. Eg: 10 * 20.
 */
export class BinaryOperationNode extends Object implements Node {
  readonly type: NodeType = NodeType.BinaryOperation;
  readonly left: Node;
  readonly right: Node;
  readonly operator: string;

  constructor(left: Node, operator: string, right: Node) {
    super();
    this.left = left;
    this.operator = operator;
    this.right = right;
  }

  toString() {
    return `${this.left.toString()} ${this.operator} ${this.right.toString()}`;
  }
}
