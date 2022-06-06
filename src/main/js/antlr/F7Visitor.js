// Generated from F7.g4 by ANTLR 4.7.2
// jshint ignore: start
var antlr4 = require("antlr4/index");

// This class defines a complete generic visitor for a parse tree produced by F7Parser.

function F7Visitor() {
  antlr4.tree.ParseTreeVisitor.call(this);
  return this;
}

F7Visitor.prototype = Object.create(antlr4.tree.ParseTreeVisitor.prototype);
F7Visitor.prototype.constructor = F7Visitor;

// Visit a parse tree produced by F7Parser#start.
F7Visitor.prototype.visitStart = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#block.
F7Visitor.prototype.visitBlock = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#unaryPercentExpression.
F7Visitor.prototype.visitUnaryPercentExpression = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#unaryMinusExpression.
F7Visitor.prototype.visitUnaryMinusExpression = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#powerExpression.
F7Visitor.prototype.visitPowerExpression = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#unaryPlusExpression.
F7Visitor.prototype.visitUnaryPlusExpression = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#atomExpression.
F7Visitor.prototype.visitAtomExpression = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#additiveExpression.
F7Visitor.prototype.visitAdditiveExpression = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#relationalExpression.
F7Visitor.prototype.visitRelationalExpression = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#rangeExpression.
F7Visitor.prototype.visitRangeExpression = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#multiplicationExpression.
F7Visitor.prototype.visitMultiplicationExpression = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#concatExpression.
F7Visitor.prototype.visitConcatExpression = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#cellAtom.
F7Visitor.prototype.visitCellAtom = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#stringAtom.
F7Visitor.prototype.visitStringAtom = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#errorAtom.
F7Visitor.prototype.visitErrorAtom = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#numberAtom.
F7Visitor.prototype.visitNumberAtom = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#parentheticalAtom.
F7Visitor.prototype.visitParentheticalAtom = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#formulaAtom.
F7Visitor.prototype.visitFormulaAtom = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#listAtom.
F7Visitor.prototype.visitListAtom = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#namedAtom.
F7Visitor.prototype.visitNamedAtom = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#range.
F7Visitor.prototype.visitRange = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#biRange.
F7Visitor.prototype.visitBiRange = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#uniRange.
F7Visitor.prototype.visitUniRange = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#columnWiseBiRange.
F7Visitor.prototype.visitColumnWiseBiRange = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#columnWiseWithRowOffsetFirstBiRange.
F7Visitor.prototype.visitColumnWiseWithRowOffsetFirstBiRange = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#columnWiseWithRowOffsetLastBiRange.
F7Visitor.prototype.visitColumnWiseWithRowOffsetLastBiRange = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#rowWiseBiRange.
F7Visitor.prototype.visitRowWiseBiRange = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#rowWiseWithColumnOffsetFirstBiRange.
F7Visitor.prototype.visitRowWiseWithColumnOffsetFirstBiRange = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#rowWiseWithColumnOffsetLastBiRange.
F7Visitor.prototype.visitRowWiseWithColumnOffsetLastBiRange = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#arguments.
F7Visitor.prototype.visitArguments = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#gridName.
F7Visitor.prototype.visitGridName = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#identifier.
F7Visitor.prototype.visitIdentifier = function (ctx) {
  return this.visitChildren(ctx);
};

// Visit a parse tree produced by F7Parser#comparisonOperator.
F7Visitor.prototype.visitComparisonOperator = function (ctx) {
  return this.visitChildren(ctx);
};

exports.F7Visitor = F7Visitor;
