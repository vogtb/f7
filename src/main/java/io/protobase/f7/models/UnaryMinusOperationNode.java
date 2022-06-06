package io.protobase.f7.models;

import com.google.common.base.MoreObjects;

/**
 * Unary minus operation is the highest precedence operation, and is given a special node. While we could achieve this
 * by creating a formula-node that is functionally the same thing, I like the idea of 1) getting specific with out
 * node sub-classes, and 2) having something we can render as valid F7 code without mutating the user's input.
 */
public class UnaryMinusOperationNode extends BaseObject implements Node {
  private Node operand;

  /**
   * Create new node with an operand.
   *
   * @param operand value
   */
  public UnaryMinusOperationNode(Node operand) {
    this.operand = operand;
  }

  /**
   * Get the operand node.
   *
   * @return node.
   */
  public Node getOperand() {
    return operand;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("operand", operand)
        .toString();
  }

  @Override
  public Object[] significantAttributes() {
    return new Object[]{
        operand
    };
  }
}
