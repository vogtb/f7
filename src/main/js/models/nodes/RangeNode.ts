import { CellQuery } from "./CellQuery";
import { Node } from "./Node";
import { NodeType } from "./NodeType";

/**
 * A range node represents a range of cells with a starting cell and ending cell, possibly by inferring that the start
 * or end cells are the max-cell or min-cell in the column or row.
 */
export class RangeNode extends Object implements Node {
  readonly type: NodeType = NodeType.Range;
  readonly query: CellQuery;

  constructor(query: CellQuery) {
    super();
    this.query = query;
  }

  toString() {
    return this.query.toString();
  }
}
