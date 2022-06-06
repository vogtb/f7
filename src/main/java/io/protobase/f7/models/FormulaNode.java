package io.protobase.f7.models;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * A formula node is a named formula with 0 or more arguments that are other nodes.
 */
public class FormulaNode extends BaseObject implements Node {
  private String name;
  private List<Node> arguments;


  public FormulaNode(String name, List<Node> arguments) {
    this.name = name;
    this.arguments = arguments;
  }

  public FormulaNode(String name) {
    this.name = name;
    this.arguments = new ArrayList<>();
  }


  public static Builder builder() {
    return new Builder();
  }

  public boolean isEmpty() {
    return arguments.isEmpty();
  }

  /**
   * Get formula name.
   *
   * @return formula name.
   */
  public String getName() {
    return name;
  }

  public List<Node> getArguments() {
    return arguments;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("name", name)
        .add("arguments", arguments)
        .toString();
  }

  @Override
  public Object[] significantAttributes() {
    return new Object[]{
        name,
        arguments
    };
  }

  public static class Builder {
    private String name;
    private List<Node> arguments = new ArrayList<>();

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder value(Node value) {
      arguments.add(value);
      return this;
    }

    public FormulaNode build() {
      return new FormulaNode(name, arguments);
    }
  }
}
