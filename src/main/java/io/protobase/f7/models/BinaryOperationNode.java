package io.protobase.f7.models;

import com.google.common.base.MoreObjects;

/**
 * Represents a binary operation with a left and right node, and an operator. Eg: 10 * 20.
 */
public class BinaryOperationNode extends BaseObject implements Node {
  private Node left;
  private Node right;
  private String operator;

  public BinaryOperationNode(Node left, String operator, Node right) {
    this.left = left;
    this.operator = operator;
    this.right = right;
  }

  /**
   * Get left node.
   *
   * @return left node.
   */
  public Node getLeft() {
    return left;
  }

  /**
   * Get right node.
   *
   * @return right node.
   */
  public Node getRight() {
    return right;
  }

  /**
   * Operator is one of the traditional arithmetic / computational operators. Eg: + - * / ^ % & %
   *
   * @return operator
   */
  public String getOperator() {
    return operator;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("left", left)
        .add("operator", operator)
        .add("right", right)
        .toString();
  }

  @Override
  public Object[] significantAttributes() {
    return new Object[]{
        left,
        operator,
        right
    };
  }
}
