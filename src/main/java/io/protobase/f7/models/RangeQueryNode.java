package io.protobase.f7.models;

import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * A range query node represents a query for cells that has multiple ranges, and possibly a named range.
 */
public class RangeQueryNode extends BaseObject implements Node {
  private List<Node> nodes;

  public RangeQueryNode(List<Node> nodes) {
    this.nodes = nodes;
  }

  /**
   * Nodes that make up this query.
   *
   * @return
   */
  public List<Node> getNodes() {
    return nodes;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("nodes", nodes)
        .toString();
  }

  @Override
  public Object[] significantAttributes() {
    return new Object[]{
        nodes
    };
  }
}
