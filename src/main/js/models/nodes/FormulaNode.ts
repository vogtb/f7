import { Node } from "./Node";
import { NodeType } from "./NodeType";

/**
 * A formula node is a named formula with 0 or more arguments that are other nodes.
 */
export class FormulaNode extends Object implements Node {
  readonly type: NodeType = NodeType.Formula;
  readonly name: string;
  readonly values: Array<Node>;

  constructor(name: string, values?: Array<Node>) {
    super();
    this.name = name;
    this.values = values ? values : [];
  }

  toString() {
    return `${this.name}(${this.values.map((value) => value.toString()).join(", ")})`;
  }
}
