import { Grid } from "../common/Grid";
import { Node } from "./Node";
import { NodeType } from "./NodeType";

/**
 * Node that is a literal list of nodes, possibly other list nodes.
 */
export class ListNode extends Object implements Node {
  readonly type: NodeType = NodeType.List;
  readonly grid: Grid<Node>;

  constructor(grid?: Grid<Node>) {
    super();
    this.grid = grid ? grid : new Grid<Node>(0, 0);
  }

  isEmpty(): boolean {
    return this.grid.isEmpty();
  }

  toString() {
    return `{${this.grid
      .raw()
      .map((row) => row.map((node) => node.toString()).join(","))
      .join(";")}}`;
  }
}
