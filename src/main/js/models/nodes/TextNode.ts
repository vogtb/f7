import { Node } from "./Node";
import { NodeType } from "./NodeType";

/**
 * Text node is just a wrapper for String.
 */
export class TextNode extends Object implements Node {
  readonly type: NodeType = NodeType.Text;
  readonly value: string;

  constructor(value: string) {
    super();
    this.value = value;
  }

  toString() {
    return `"this.value"`;
  }
}
