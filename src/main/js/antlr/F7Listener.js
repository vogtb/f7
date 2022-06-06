// Generated from F7.g4 by ANTLR 4.7.2
// jshint ignore: start
var antlr4 = require("antlr4/index");

// This class defines a complete listener for a parse tree produced by F7Parser.
function F7Listener() {
  antlr4.tree.ParseTreeListener.call(this);
  return this;
}

F7Listener.prototype = Object.create(antlr4.tree.ParseTreeListener.prototype);
F7Listener.prototype.constructor = F7Listener;

// Enter a parse tree produced by F7Parser#start.
F7Listener.prototype.enterStart = function (ctx) {};

// Exit a parse tree produced by F7Parser#start.
F7Listener.prototype.exitStart = function (ctx) {};

// Enter a parse tree produced by F7Parser#block.
F7Listener.prototype.enterBlock = function (ctx) {};

// Exit a parse tree produced by F7Parser#block.
F7Listener.prototype.exitBlock = function (ctx) {};

// Enter a parse tree produced by F7Parser#unaryPercentExpression.
F7Listener.prototype.enterUnaryPercentExpression = function (ctx) {};

// Exit a parse tree produced by F7Parser#unaryPercentExpression.
F7Listener.prototype.exitUnaryPercentExpression = function (ctx) {};

// Enter a parse tree produced by F7Parser#unaryMinusExpression.
F7Listener.prototype.enterUnaryMinusExpression = function (ctx) {};

// Exit a parse tree produced by F7Parser#unaryMinusExpression.
F7Listener.prototype.exitUnaryMinusExpression = function (ctx) {};

// Enter a parse tree produced by F7Parser#powerExpression.
F7Listener.prototype.enterPowerExpression = function (ctx) {};

// Exit a parse tree produced by F7Parser#powerExpression.
F7Listener.prototype.exitPowerExpression = function (ctx) {};

// Enter a parse tree produced by F7Parser#unaryPlusExpression.
F7Listener.prototype.enterUnaryPlusExpression = function (ctx) {};

// Exit a parse tree produced by F7Parser#unaryPlusExpression.
F7Listener.prototype.exitUnaryPlusExpression = function (ctx) {};

// Enter a parse tree produced by F7Parser#atomExpression.
F7Listener.prototype.enterAtomExpression = function (ctx) {};

// Exit a parse tree produced by F7Parser#atomExpression.
F7Listener.prototype.exitAtomExpression = function (ctx) {};

// Enter a parse tree produced by F7Parser#additiveExpression.
F7Listener.prototype.enterAdditiveExpression = function (ctx) {};

// Exit a parse tree produced by F7Parser#additiveExpression.
F7Listener.prototype.exitAdditiveExpression = function (ctx) {};

// Enter a parse tree produced by F7Parser#relationalExpression.
F7Listener.prototype.enterRelationalExpression = function (ctx) {};

// Exit a parse tree produced by F7Parser#relationalExpression.
F7Listener.prototype.exitRelationalExpression = function (ctx) {};

// Enter a parse tree produced by F7Parser#rangeExpression.
F7Listener.prototype.enterRangeExpression = function (ctx) {};

// Exit a parse tree produced by F7Parser#rangeExpression.
F7Listener.prototype.exitRangeExpression = function (ctx) {};

// Enter a parse tree produced by F7Parser#multiplicationExpression.
F7Listener.prototype.enterMultiplicationExpression = function (ctx) {};

// Exit a parse tree produced by F7Parser#multiplicationExpression.
F7Listener.prototype.exitMultiplicationExpression = function (ctx) {};

// Enter a parse tree produced by F7Parser#concatExpression.
F7Listener.prototype.enterConcatExpression = function (ctx) {};

// Exit a parse tree produced by F7Parser#concatExpression.
F7Listener.prototype.exitConcatExpression = function (ctx) {};

// Enter a parse tree produced by F7Parser#cellAtom.
F7Listener.prototype.enterCellAtom = function (ctx) {};

// Exit a parse tree produced by F7Parser#cellAtom.
F7Listener.prototype.exitCellAtom = function (ctx) {};

// Enter a parse tree produced by F7Parser#stringAtom.
F7Listener.prototype.enterStringAtom = function (ctx) {};

// Exit a parse tree produced by F7Parser#stringAtom.
F7Listener.prototype.exitStringAtom = function (ctx) {};

// Enter a parse tree produced by F7Parser#errorAtom.
F7Listener.prototype.enterErrorAtom = function (ctx) {};

// Exit a parse tree produced by F7Parser#errorAtom.
F7Listener.prototype.exitErrorAtom = function (ctx) {};

// Enter a parse tree produced by F7Parser#numberAtom.
F7Listener.prototype.enterNumberAtom = function (ctx) {};

// Exit a parse tree produced by F7Parser#numberAtom.
F7Listener.prototype.exitNumberAtom = function (ctx) {};

// Enter a parse tree produced by F7Parser#parentheticalAtom.
F7Listener.prototype.enterParentheticalAtom = function (ctx) {};

// Exit a parse tree produced by F7Parser#parentheticalAtom.
F7Listener.prototype.exitParentheticalAtom = function (ctx) {};

// Enter a parse tree produced by F7Parser#formulaAtom.
F7Listener.prototype.enterFormulaAtom = function (ctx) {};

// Exit a parse tree produced by F7Parser#formulaAtom.
F7Listener.prototype.exitFormulaAtom = function (ctx) {};

// Enter a parse tree produced by F7Parser#listAtom.
F7Listener.prototype.enterListAtom = function (ctx) {};

// Exit a parse tree produced by F7Parser#listAtom.
F7Listener.prototype.exitListAtom = function (ctx) {};

// Enter a parse tree produced by F7Parser#namedAtom.
F7Listener.prototype.enterNamedAtom = function (ctx) {};

// Exit a parse tree produced by F7Parser#namedAtom.
F7Listener.prototype.exitNamedAtom = function (ctx) {};

// Enter a parse tree produced by F7Parser#range.
F7Listener.prototype.enterRange = function (ctx) {};

// Exit a parse tree produced by F7Parser#range.
F7Listener.prototype.exitRange = function (ctx) {};

// Enter a parse tree produced by F7Parser#biRange.
F7Listener.prototype.enterBiRange = function (ctx) {};

// Exit a parse tree produced by F7Parser#biRange.
F7Listener.prototype.exitBiRange = function (ctx) {};

// Enter a parse tree produced by F7Parser#uniRange.
F7Listener.prototype.enterUniRange = function (ctx) {};

// Exit a parse tree produced by F7Parser#uniRange.
F7Listener.prototype.exitUniRange = function (ctx) {};

// Enter a parse tree produced by F7Parser#columnWiseBiRange.
F7Listener.prototype.enterColumnWiseBiRange = function (ctx) {};

// Exit a parse tree produced by F7Parser#columnWiseBiRange.
F7Listener.prototype.exitColumnWiseBiRange = function (ctx) {};

// Enter a parse tree produced by F7Parser#columnWiseWithRowOffsetFirstBiRange.
F7Listener.prototype.enterColumnWiseWithRowOffsetFirstBiRange = function (ctx) {};

// Exit a parse tree produced by F7Parser#columnWiseWithRowOffsetFirstBiRange.
F7Listener.prototype.exitColumnWiseWithRowOffsetFirstBiRange = function (ctx) {};

// Enter a parse tree produced by F7Parser#columnWiseWithRowOffsetLastBiRange.
F7Listener.prototype.enterColumnWiseWithRowOffsetLastBiRange = function (ctx) {};

// Exit a parse tree produced by F7Parser#columnWiseWithRowOffsetLastBiRange.
F7Listener.prototype.exitColumnWiseWithRowOffsetLastBiRange = function (ctx) {};

// Enter a parse tree produced by F7Parser#rowWiseBiRange.
F7Listener.prototype.enterRowWiseBiRange = function (ctx) {};

// Exit a parse tree produced by F7Parser#rowWiseBiRange.
F7Listener.prototype.exitRowWiseBiRange = function (ctx) {};

// Enter a parse tree produced by F7Parser#rowWiseWithColumnOffsetFirstBiRange.
F7Listener.prototype.enterRowWiseWithColumnOffsetFirstBiRange = function (ctx) {};

// Exit a parse tree produced by F7Parser#rowWiseWithColumnOffsetFirstBiRange.
F7Listener.prototype.exitRowWiseWithColumnOffsetFirstBiRange = function (ctx) {};

// Enter a parse tree produced by F7Parser#rowWiseWithColumnOffsetLastBiRange.
F7Listener.prototype.enterRowWiseWithColumnOffsetLastBiRange = function (ctx) {};

// Exit a parse tree produced by F7Parser#rowWiseWithColumnOffsetLastBiRange.
F7Listener.prototype.exitRowWiseWithColumnOffsetLastBiRange = function (ctx) {};

// Enter a parse tree produced by F7Parser#arguments.
F7Listener.prototype.enterArguments = function (ctx) {};

// Exit a parse tree produced by F7Parser#arguments.
F7Listener.prototype.exitArguments = function (ctx) {};

// Enter a parse tree produced by F7Parser#gridName.
F7Listener.prototype.enterGridName = function (ctx) {};

// Exit a parse tree produced by F7Parser#gridName.
F7Listener.prototype.exitGridName = function (ctx) {};

// Enter a parse tree produced by F7Parser#identifier.
F7Listener.prototype.enterIdentifier = function (ctx) {};

// Exit a parse tree produced by F7Parser#identifier.
F7Listener.prototype.exitIdentifier = function (ctx) {};

// Enter a parse tree produced by F7Parser#comparisonOperator.
F7Listener.prototype.enterComparisonOperator = function (ctx) {};

// Exit a parse tree produced by F7Parser#comparisonOperator.
F7Listener.prototype.exitComparisonOperator = function (ctx) {};

exports.F7Listener = F7Listener;
