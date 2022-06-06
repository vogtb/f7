import { Node } from "./Node";
import { NodeType } from "./NodeType";

/**
 * Variable node is a named variable, like TRUE, FALSE, or MY_SUPER_NAMED_RANGE. It is resolved to an actual object at
 * run-time.
 */
export class VariableNode extends Object implements Node {
  readonly type: NodeType = NodeType.Variable;
  readonly name: string;

  constructor(name: string) {
    super();
    this.name = name;
  }

  toString() {
    return this.name;
  }
}
