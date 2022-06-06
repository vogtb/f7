import { Node } from "./Node";
import { NodeType } from "./NodeType";

/**
 * A range query node represents a query for cells that has multiple ranges, and possibly a named range.
 */
export class MultiRangeNode extends Object implements Node {
  readonly type: NodeType = NodeType.MultiRange;
  readonly nodes: Array<Node>;

  constructor(nodes: Array<Node>) {
    super();
    this.nodes = nodes;
  }

  toString() {
    return this.nodes.map((node) => node.toString()).join(":");
  }
}
