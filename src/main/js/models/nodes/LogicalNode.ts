import { Node } from "./Node";
import { NodeType } from "./NodeType";

/**
 * Logic node is a wrapper for a boolean.
 */
export class LogicalNode extends Object implements Node {
  public static TRUE: LogicalNode = new LogicalNode(true);
  public static FALSE: LogicalNode = new LogicalNode(false);
  readonly type: NodeType = NodeType.Logical;
  readonly value: boolean;

  constructor(value: boolean) {
    super();
    this.value = value;
  }

  toString() {
    return this.value ? "TRUE" : "FALSE";
  }
}
