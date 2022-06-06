/**
 * All nodes have a type.
 */
export enum NodeType {
  Number = "Number",
  Text = "Text",
  Logical = "Logical",
  Error = "Error",
  Variable = "Variable",
  Formula = "Formula",
  List = "List",
  Range = "Range",
  MultiRange = "RangeQuery",
  UnaryMinusOperation = "UnaryMinusOperation",
  UnaryPlusOperation = "UnaryPlusOperation",
  UnaryPercentOperation = "UnaryPercentOperation",
  BinaryOperation = "BinaryOperation",
}
