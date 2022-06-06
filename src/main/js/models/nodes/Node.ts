import { NodeType } from "./NodeType";

/**
 * When we parse F7 code to a tree, all nodes on the tree extend this node.
 */
export interface Node extends Object {
  type: NodeType;
}
