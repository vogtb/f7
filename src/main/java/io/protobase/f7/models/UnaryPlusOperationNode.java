package io.protobase.f7.models;

import com.google.common.base.MoreObjects;

/**
 * Unary plus operation.
 */
public class UnaryPlusOperationNode extends BaseObject implements Node {
  private Node operand;

  /**
   * Create new node with an operand.
   *
   * @param operand value
   */
  public UnaryPlusOperationNode(Node operand) {
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
