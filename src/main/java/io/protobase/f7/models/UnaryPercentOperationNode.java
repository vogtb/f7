package io.protobase.f7.models;

import com.google.common.base.MoreObjects;

/**
 * Unary percent operation is essentially division by 100.
 */
public class UnaryPercentOperationNode extends BaseObject implements Node {
  private Node operand;

  /**
   * Create new node with an operand.
   *
   * @param operand value
   */
  public UnaryPercentOperationNode(Node operand) {
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
