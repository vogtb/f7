/***************************************************************************************************
 F7 This is the main grammar file for F7 code. The goal is to get **near feature-parity** with most
 major spreadsheet applications. In cases of conflicting syntax, we choose the simpler of the two,
 or the most compatible, which is mostly a judgment call. In cases where features are grand-fathered
 in from older versions of the major spreadsheet applications we may choose to ignore those features
 altogether.
 
 Comments are collapsed into the main block of each section below. If we write comments for each
 rule (and believe me, we could) it would end up being difficult to read. We therefore condense them
 down to general comments in the main block.
 ***************************************************************************************************
 */
grammar F7;

/***************************************************************************************************
 PARSER RULES These are rules that the parser will use. Some of them are explicitly named (def: ...
 ; ... ;) and some are named with the # character at the end of the individual rule.
 
 start - The starting block is the main entry point for parsing, but not for compilation or the
 logic of F7 code. It basically serves as a way to captutre the code and the EOF, so we can jump
 straight to the block.
 
 block - All F7 code starts with a single expression.
 
 expression - An expression is a section of code.
 
 atom - An atom is a single variable that itself requires no execution or reduction, but may contain
 other atoms and expressions that do require execution and reduction.
 
 identifier - An identifier is a set of characters and numbers between 1 and N in length, starting
 with an alphabetical character. It may contain periods and underscores as long as they are not in
 beginning the string.
 
 comparisionOperator - Short hand for all comparision operators.
 ***************************************************************************************************
 */
start: block EOF;
block: expression;
expression:
	Minus expression		# unaryMinusExpression
	| Plus expression		# unaryPlusExpression
	| expression Percent+	# unaryPercentExpression
	// TODO:HACK - <assoc=left> vs <assoc=right> differ in Excel and Sheets, respectively. Weird.
	| <assoc = left> left = expression op = Power right = expression	# powerExpression
	| left = expression op = (Multiply | Divide) right = expression		# multiplicationExpression
	| left = expression op = (Plus | Minus) right = expression			# additiveExpression
	| left = expression op = comparisonOperator right = expression		# relationalExpression
	| left = expression op = Ampersand right = expression				# concatExpression
	| atom																# atomExpression
	| atom (separator = Colon atom)+									# rangeExpression;
atom:
	range												# cellAtom
	| String											# stringAtom
	| Error												# errorAtom
	| Int												# numberAtom
	| Number											# numberAtom
	| LeftParen expression RightParen					# parentheticalAtom
	| name = identifier LeftParen arguments RightParen	# formulaAtom
	| LeftBrace (
		expression (separator = (Comma | SemiColon) expression)*
	)? RightBrace	# listAtom
	| identifier	# namedAtom;
range:
	biRange
	| uniRange
	| columnWiseBiRange
	| columnWiseWithRowOffsetFirstBiRange
	| columnWiseWithRowOffsetLastBiRange
	| rowWiseBiRange
	| rowWiseWithColumnOffsetFirstBiRange
	| rowWiseWithColumnOffsetLastBiRange;
biRange: (grid = gridName Bang)? absoFirstColumn = Dollar? firstColumn = NCharacters absoRow =
		Dollar? firstRow = Int Colon absoLastColumn = Dollar? lastColumn = NCharacters absoLastRow =
		Dollar? lastRow = Int;
uniRange: (grid = gridName Bang)? absoFirstColumn = Dollar? firstColumn = NCharacters absoFirstRow =
		Dollar? firstRow = Int;
columnWiseBiRange: (grid = gridName Bang)? absoFirstColumn = Dollar? firstColumn = NCharacters Colon
		absoLastColumn = Dollar? lastColumn = NCharacters;
columnWiseWithRowOffsetFirstBiRange: (grid = gridName Bang)? absoFirstColumn = Dollar? firstColumn =
		NCharacters absoFirstRow = Dollar? firstRow = Int Colon absoLastColumn = Dollar? lastColumn
		= NCharacters;
columnWiseWithRowOffsetLastBiRange: (grid = gridName Bang)? absoFirstColumn = Dollar? firstColumn =
		NCharacters Colon absoLastColumn = Dollar? lastColumn = NCharacters absoLastRow = Dollar?
		lastRow = Int;
rowWiseBiRange: (grid = gridName Bang)? absoFirstRow = Dollar? firstRow = Int Colon absoLastRow =
		Dollar? lastRow = Int;
rowWiseWithColumnOffsetFirstBiRange: (grid = gridName Bang)? absoFirstColumn = Dollar? firstColumn =
		NCharacters absoFirstRow = Dollar? firstRow = Int Colon absoLastRow = Dollar? lastRow = Int;
rowWiseWithColumnOffsetLastBiRange: (grid = gridName Bang)? absoFirstRow = Dollar? firstRow = Int
		Colon absLastColumn = Dollar? lastColumn = NCharacters absoLastRow = Dollar? lastRow = Int;
arguments: (expression (Comma expression)*)?;
gridName: SingleQuoteString | identifier;
identifier: NCharacters (Dot | Underscore | NCharacters | Int)*;
comparisonOperator:
	LessThanOrEqualTO
	| GreaterThanOrEqualTo
	| LessThan
	| GreaterThan
	| Equal
	| NotEqual;

/***************************************************************************************************
 LEXER RULES These are rules that the lexer will use. They SHOULD BE NON-CONFLICTING, and as
 small/big as they need to be.
 ***************************************************************************************************
 */
NCharacters: CHARACTER+;
Dot: '.';
Int: DIGIT+;
Number:
	DIGIT+ (Dot DIGIT+)? (E ('+' | '-')? DIGIT+ (Dot DIGIT+)?)?;
SingleQuoteString: SINGLE_QUOTE_STRING;
String: STRING_LITERAL;
Error: ERROR_LITERAL;
LeftParen: LPAREN;
RightParen: RPAREN;
LeftBrace: LBRACE;
RightBrace: RBRACE;
Comma: COMMA;
Colon: COLON;
SemiColon: SEMICOLON;
Bang: BANG;
Plus: PLUS;
Minus: MINUS;
Percent: PERCENT;
Power: POW;
Divide: DIV;
Multiply: MULT;
GreaterThan: GT;
GreaterThanOrEqualTo: GTEQ;
LessThan: LT;
LessThanOrEqualTO: LTEQ;
Equal: EQ;
NotEqual: NEQ;
Ampersand: AMPERSAND;
Dollar: DOLLAR;
Underscore: UNDERSCORE;

/***************************************************************************************************
 LEXER FRAGMENTS Rules that the lexer will use, but we don't need to NAME.
 
 Fragments A-Z let us to do case-insensitivity when it comes to literally named things like errors.


 Error literals look weird because we're using the case-insensitive alphabet fragments.
 ***************************************************************************************************
 */
fragment A: [aA];
fragment B: [bB];
fragment C: [cC];
fragment D: [dD];
fragment E: [eE];
fragment F: [fF];
fragment G: [gG];
fragment H: [hH];
fragment I: [iI];
fragment J: [jJ];
fragment K: [kK];
fragment L: [lL];
fragment M: [mM];
fragment N: [nN];
fragment O: [oO];
fragment P: [pP];
fragment Q: [qQ];
fragment R: [rR];
fragment S: [sS];
fragment T: [tT];
fragment U: [uU];
fragment V: [vV];
fragment W: [wW];
fragment X: [xX];
fragment Y: [yY];
fragment Z: [zZ];
fragment IDENTIFIER: CHARACTER+ ('_' | CHARACTER)*;
fragment DIGIT: ('0' ..'9');
fragment CHARACTER: [a-zA-Z];
fragment ERROR_LITERAL:
	HASH N U L L BANG // #NULL!
	| HASH D I V DIV '0' BANG // #DIV/0!
	| HASH V A L U E BANG // #VALUE!
	| HASH R E F BANG // #REF!
	| HASH N A M E QUESTION // #NAME?
	| HASH N U M BANG // #NUM!
	| HASH N DIV A // #N/A
	| HASH E R R O R BANG; // #ERROR!
fragment SINGLE_QUOTE_STRING: (
		'\'' SINGLE_STRING_CHARACTER* '\''
	);
fragment SINGLE_STRING_CHARACTER: ~['\\\r\n];
fragment STRING_LITERAL: ('"' DOUBLE_STRING_CHARACTER* '"');
fragment DOUBLE_STRING_CHARACTER: ~["\\\r\n];
fragment EQ: '=';
fragment NEQ: '<>';
fragment GT: '>';
fragment LT: '<';
fragment GTEQ: '>=';
fragment LTEQ: '<=';
fragment PLUS: '+';
fragment MINUS: '-';
fragment MULT: '*';
fragment DIV: '/';
fragment PERCENT: '%';
fragment HASH: '#';
fragment POW: '^';
fragment AMPERSAND: '&';
fragment LPAREN: '(';
fragment RPAREN: ')';
fragment COMMA: ',';
fragment SEMICOLON: ';';
fragment LBRACE: '{';
fragment RBRACE: '}';
fragment BANG: '!';
fragment QUESTION: '?';
fragment DOLLAR: '$';
fragment COLON: ':';
fragment UNDERSCORE: '_';

/***************************************************************************************************
 UTIL/COMMON/ETC.
 ***************************************************************************************************
 */
// Skip whitespaces in between tokens.
// Allows us to match on whitespace inside strings, but ignore them otherwise.
WS: [ \r\n\t]+ -> skip;
