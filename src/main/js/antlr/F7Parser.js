// Generated from F7.g4 by ANTLR 4.7.2
// jshint ignore: start
var antlr4 = require("antlr4/index");
var F7Listener = require("./F7Listener").F7Listener;
var F7Visitor = require("./F7Visitor").F7Visitor;

var grammarFileName = "F7.g4";

var serializedATN = [
  "\u0003\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964",
  "\u0003!\u0121\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t",
  "\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004",
  "\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004\f\t\f\u0004",
  "\r\t\r\u0004\u000e\t\u000e\u0004\u000f\t\u000f\u0004\u0010\t\u0010\u0004",
  "\u0011\t\u0011\u0004\u0012\t\u0012\u0003\u0002\u0003\u0002\u0003\u0002",
  "\u0003\u0003\u0003\u0003\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004",
  "\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0006\u0004",
  "3\n\u0004\r\u0004\u000e\u00044\u0005\u00047\n\u0004\u0003\u0004\u0003",
  "\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003",
  "\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003",
  "\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0006\u0004K",
  "\n\u0004\r\u0004\u000e\u0004L\u0007\u0004O\n\u0004\f\u0004\u000e\u0004",
  "R\u000b\u0004\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003",
  "\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003",
  "\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003",
  "\u0005\u0003\u0005\u0007\u0005f\n\u0005\f\u0005\u000e\u0005i\u000b\u0005",
  "\u0005\u0005k\n\u0005\u0003\u0005\u0003\u0005\u0005\u0005o\n\u0005\u0003",
  "\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003",
  "\u0006\u0003\u0006\u0005\u0006y\n\u0006\u0003\u0007\u0003\u0007\u0003",
  "\u0007\u0005\u0007~\n\u0007\u0003\u0007\u0005\u0007\u0081\n\u0007\u0003",
  "\u0007\u0003\u0007\u0005\u0007\u0085\n\u0007\u0003\u0007\u0003\u0007",
  "\u0003\u0007\u0005\u0007\u008a\n\u0007\u0003\u0007\u0003\u0007\u0005",
  "\u0007\u008e\n\u0007\u0003\u0007\u0003\u0007\u0003\b\u0003\b\u0003\b",
  "\u0005\b\u0095\n\b\u0003\b\u0005\b\u0098\n\b\u0003\b\u0003\b\u0005\b",
  "\u009c\n\b\u0003\b\u0003\b\u0003\t\u0003\t\u0003\t\u0005\t\u00a3\n\t",
  "\u0003\t\u0005\t\u00a6\n\t\u0003\t\u0003\t\u0003\t\u0005\t\u00ab\n\t",
  "\u0003\t\u0003\t\u0003\n\u0003\n\u0003\n\u0005\n\u00b2\n\n\u0003\n\u0005",
  "\n\u00b5\n\n\u0003\n\u0003\n\u0005\n\u00b9\n\n\u0003\n\u0003\n\u0003",
  "\n\u0005\n\u00be\n\n\u0003\n\u0003\n\u0003\u000b\u0003\u000b\u0003\u000b",
  "\u0005\u000b\u00c5\n\u000b\u0003\u000b\u0005\u000b\u00c8\n\u000b\u0003",
  "\u000b\u0003\u000b\u0003\u000b\u0005\u000b\u00cd\n\u000b\u0003\u000b",
  "\u0003\u000b\u0005\u000b\u00d1\n\u000b\u0003\u000b\u0003\u000b\u0003",
  "\f\u0003\f\u0003\f\u0005\f\u00d8\n\f\u0003\f\u0005\f\u00db\n\f\u0003",
  "\f\u0003\f\u0003\f\u0005\f\u00e0\n\f\u0003\f\u0003\f\u0003\r\u0003\r",
  "\u0003\r\u0005\r\u00e7\n\r\u0003\r\u0005\r\u00ea\n\r\u0003\r\u0003\r",
  "\u0005\r\u00ee\n\r\u0003\r\u0003\r\u0003\r\u0005\r\u00f3\n\r\u0003\r",
  "\u0003\r\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000e\u00fa\n\u000e",
  "\u0003\u000e\u0005\u000e\u00fd\n\u000e\u0003\u000e\u0003\u000e\u0003",
  "\u000e\u0005\u000e\u0102\n\u000e\u0003\u000e\u0003\u000e\u0005\u000e",
  "\u0106\n\u000e\u0003\u000e\u0003\u000e\u0003\u000f\u0003\u000f\u0003",
  "\u000f\u0007\u000f\u010d\n\u000f\f\u000f\u000e\u000f\u0110\u000b\u000f",
  "\u0005\u000f\u0112\n\u000f\u0003\u0010\u0003\u0010\u0005\u0010\u0116",
  "\n\u0010\u0003\u0011\u0003\u0011\u0007\u0011\u011a\n\u0011\f\u0011\u000e",
  "\u0011\u011d\u000b\u0011\u0003\u0012\u0003\u0012\u0003\u0012\u0002\u0003",
  "\u0006\u0013\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018",
  '\u001a\u001c\u001e "\u0002\u0007\u0003\u0002\u0016\u0017\u0003\u0002',
  "\u0012\u0013\u0004\u0002\u000e\u000e\u0010\u0010\u0004\u0002\u0003\u0005",
  "  \u0003\u0002\u0018\u001d\u0002\u014d\u0002$\u0003\u0002\u0002\u0002",
  "\u0004'\u0003\u0002\u0002\u0002\u00066\u0003\u0002\u0002\u0002\bn\u0003",
  "\u0002\u0002\u0002\nx\u0003\u0002\u0002\u0002\f}\u0003\u0002\u0002\u0002",
  "\u000e\u0094\u0003\u0002\u0002\u0002\u0010\u00a2\u0003\u0002\u0002\u0002",
  "\u0012\u00b1\u0003\u0002\u0002\u0002\u0014\u00c4\u0003\u0002\u0002\u0002",
  "\u0016\u00d7\u0003\u0002\u0002\u0002\u0018\u00e6\u0003\u0002\u0002\u0002",
  "\u001a\u00f9\u0003\u0002\u0002\u0002\u001c\u0111\u0003\u0002\u0002\u0002",
  "\u001e\u0115\u0003\u0002\u0002\u0002 \u0117\u0003\u0002\u0002\u0002",
  '"\u011e\u0003\u0002\u0002\u0002$%\u0005\u0004\u0003\u0002%&\u0007\u0002',
  "\u0002\u0003&\u0003\u0003\u0002\u0002\u0002'(\u0005\u0006\u0004\u0002",
  "(\u0005\u0003\u0002\u0002\u0002)*\b\u0004\u0001\u0002*+\u0007\u0013",
  "\u0002\u0002+7\u0005\u0006\u0004\f,-\u0007\u0012\u0002\u0002-7\u0005",
  "\u0006\u0004\u000b.7\u0005\b\u0005\u0002/2\u0005\b\u0005\u000201\u0007",
  "\u000f\u0002\u000213\u0005\b\u0005\u000220\u0003\u0002\u0002\u00023",
  "4\u0003\u0002\u0002\u000242\u0003\u0002\u0002\u000245\u0003\u0002\u0002",
  "\u000257\u0003\u0002\u0002\u00026)\u0003\u0002\u0002\u00026,\u0003\u0002",
  "\u0002\u00026.\u0003\u0002\u0002\u00026/\u0003\u0002\u0002\u00027P\u0003",
  "\u0002\u0002\u000289\f\t\u0002\u00029:\u0007\u0015\u0002\u0002:O\u0005",
  "\u0006\u0004\n;<\f\b\u0002\u0002<=\t\u0002\u0002\u0002=O\u0005\u0006",
  "\u0004\t>?\f\u0007\u0002\u0002?@\t\u0003\u0002\u0002@O\u0005\u0006\u0004",
  '\bAB\f\u0006\u0002\u0002BC\u0005"\u0012\u0002CD\u0005\u0006\u0004\u0007',
  "DO\u0003\u0002\u0002\u0002EF\f\u0005\u0002\u0002FG\u0007\u001e\u0002",
  "\u0002GO\u0005\u0006\u0004\u0006HJ\f\n\u0002\u0002IK\u0007\u0014\u0002",
  "\u0002JI\u0003\u0002\u0002\u0002KL\u0003\u0002\u0002\u0002LJ\u0003\u0002",
  "\u0002\u0002LM\u0003\u0002\u0002\u0002MO\u0003\u0002\u0002\u0002N8\u0003",
  "\u0002\u0002\u0002N;\u0003\u0002\u0002\u0002N>\u0003\u0002\u0002\u0002",
  "NA\u0003\u0002\u0002\u0002NE\u0003\u0002\u0002\u0002NH\u0003\u0002\u0002",
  "\u0002OR\u0003\u0002\u0002\u0002PN\u0003\u0002\u0002\u0002PQ\u0003\u0002",
  "\u0002\u0002Q\u0007\u0003\u0002\u0002\u0002RP\u0003\u0002\u0002\u0002",
  "So\u0005\n\u0006\u0002To\u0007\b\u0002\u0002Uo\u0007\t\u0002\u0002V",
  "o\u0007\u0005\u0002\u0002Wo\u0007\u0006\u0002\u0002XY\u0007\n\u0002",
  "\u0002YZ\u0005\u0006\u0004\u0002Z[\u0007\u000b\u0002\u0002[o\u0003\u0002",
  "\u0002\u0002\\]\u0005 \u0011\u0002]^\u0007\n\u0002\u0002^_\u0005\u001c",
  "\u000f\u0002_`\u0007\u000b\u0002\u0002`o\u0003\u0002\u0002\u0002aj\u0007",
  "\f\u0002\u0002bg\u0005\u0006\u0004\u0002cd\t\u0004\u0002\u0002df\u0005",
  "\u0006\u0004\u0002ec\u0003\u0002\u0002\u0002fi\u0003\u0002\u0002\u0002",
  "ge\u0003\u0002\u0002\u0002gh\u0003\u0002\u0002\u0002hk\u0003\u0002\u0002",
  "\u0002ig\u0003\u0002\u0002\u0002jb\u0003\u0002\u0002\u0002jk\u0003\u0002",
  "\u0002\u0002kl\u0003\u0002\u0002\u0002lo\u0007\r\u0002\u0002mo\u0005",
  " \u0011\u0002nS\u0003\u0002\u0002\u0002nT\u0003\u0002\u0002\u0002nU",
  "\u0003\u0002\u0002\u0002nV\u0003\u0002\u0002\u0002nW\u0003\u0002\u0002",
  "\u0002nX\u0003\u0002\u0002\u0002n\\\u0003\u0002\u0002\u0002na\u0003",
  "\u0002\u0002\u0002nm\u0003\u0002\u0002\u0002o\t\u0003\u0002\u0002\u0002",
  "py\u0005\f\u0007\u0002qy\u0005\u000e\b\u0002ry\u0005\u0010\t\u0002s",
  "y\u0005\u0012\n\u0002ty\u0005\u0014\u000b\u0002uy\u0005\u0016\f\u0002",
  "vy\u0005\u0018\r\u0002wy\u0005\u001a\u000e\u0002xp\u0003\u0002\u0002",
  "\u0002xq\u0003\u0002\u0002\u0002xr\u0003\u0002\u0002\u0002xs\u0003\u0002",
  "\u0002\u0002xt\u0003\u0002\u0002\u0002xu\u0003\u0002\u0002\u0002xv\u0003",
  "\u0002\u0002\u0002xw\u0003\u0002\u0002\u0002y\u000b\u0003\u0002\u0002",
  "\u0002z{\u0005\u001e\u0010\u0002{|\u0007\u0011\u0002\u0002|~\u0003\u0002",
  "\u0002\u0002}z\u0003\u0002\u0002\u0002}~\u0003\u0002\u0002\u0002~\u0080",
  "\u0003\u0002\u0002\u0002\u007f\u0081\u0007\u001f\u0002\u0002\u0080\u007f",
  "\u0003\u0002\u0002\u0002\u0080\u0081\u0003\u0002\u0002\u0002\u0081\u0082",
  "\u0003\u0002\u0002\u0002\u0082\u0084\u0007\u0003\u0002\u0002\u0083\u0085",
  "\u0007\u001f\u0002\u0002\u0084\u0083\u0003\u0002\u0002\u0002\u0084\u0085",
  "\u0003\u0002\u0002\u0002\u0085\u0086\u0003\u0002\u0002\u0002\u0086\u0087",
  "\u0007\u0005\u0002\u0002\u0087\u0089\u0007\u000f\u0002\u0002\u0088\u008a",
  "\u0007\u001f\u0002\u0002\u0089\u0088\u0003\u0002\u0002\u0002\u0089\u008a",
  "\u0003\u0002\u0002\u0002\u008a\u008b\u0003\u0002\u0002\u0002\u008b\u008d",
  "\u0007\u0003\u0002\u0002\u008c\u008e\u0007\u001f\u0002\u0002\u008d\u008c",
  "\u0003\u0002\u0002\u0002\u008d\u008e\u0003\u0002\u0002\u0002\u008e\u008f",
  "\u0003\u0002\u0002\u0002\u008f\u0090\u0007\u0005\u0002\u0002\u0090\r",
  "\u0003\u0002\u0002\u0002\u0091\u0092\u0005\u001e\u0010\u0002\u0092\u0093",
  "\u0007\u0011\u0002\u0002\u0093\u0095\u0003\u0002\u0002\u0002\u0094\u0091",
  "\u0003\u0002\u0002\u0002\u0094\u0095\u0003\u0002\u0002\u0002\u0095\u0097",
  "\u0003\u0002\u0002\u0002\u0096\u0098\u0007\u001f\u0002\u0002\u0097\u0096",
  "\u0003\u0002\u0002\u0002\u0097\u0098\u0003\u0002\u0002\u0002\u0098\u0099",
  "\u0003\u0002\u0002\u0002\u0099\u009b\u0007\u0003\u0002\u0002\u009a\u009c",
  "\u0007\u001f\u0002\u0002\u009b\u009a\u0003\u0002\u0002\u0002\u009b\u009c",
  "\u0003\u0002\u0002\u0002\u009c\u009d\u0003\u0002\u0002\u0002\u009d\u009e",
  "\u0007\u0005\u0002\u0002\u009e\u000f\u0003\u0002\u0002\u0002\u009f\u00a0",
  "\u0005\u001e\u0010\u0002\u00a0\u00a1\u0007\u0011\u0002\u0002\u00a1\u00a3",
  "\u0003\u0002\u0002\u0002\u00a2\u009f\u0003\u0002\u0002\u0002\u00a2\u00a3",
  "\u0003\u0002\u0002\u0002\u00a3\u00a5\u0003\u0002\u0002\u0002\u00a4\u00a6",
  "\u0007\u001f\u0002\u0002\u00a5\u00a4\u0003\u0002\u0002\u0002\u00a5\u00a6",
  "\u0003\u0002\u0002\u0002\u00a6\u00a7\u0003\u0002\u0002\u0002\u00a7\u00a8",
  "\u0007\u0003\u0002\u0002\u00a8\u00aa\u0007\u000f\u0002\u0002\u00a9\u00ab",
  "\u0007\u001f\u0002\u0002\u00aa\u00a9\u0003\u0002\u0002\u0002\u00aa\u00ab",
  "\u0003\u0002\u0002\u0002\u00ab\u00ac\u0003\u0002\u0002\u0002\u00ac\u00ad",
  "\u0007\u0003\u0002\u0002\u00ad\u0011\u0003\u0002\u0002\u0002\u00ae\u00af",
  "\u0005\u001e\u0010\u0002\u00af\u00b0\u0007\u0011\u0002\u0002\u00b0\u00b2",
  "\u0003\u0002\u0002\u0002\u00b1\u00ae\u0003\u0002\u0002\u0002\u00b1\u00b2",
  "\u0003\u0002\u0002\u0002\u00b2\u00b4\u0003\u0002\u0002\u0002\u00b3\u00b5",
  "\u0007\u001f\u0002\u0002\u00b4\u00b3\u0003\u0002\u0002\u0002\u00b4\u00b5",
  "\u0003\u0002\u0002\u0002\u00b5\u00b6\u0003\u0002\u0002\u0002\u00b6\u00b8",
  "\u0007\u0003\u0002\u0002\u00b7\u00b9\u0007\u001f\u0002\u0002\u00b8\u00b7",
  "\u0003\u0002\u0002\u0002\u00b8\u00b9\u0003\u0002\u0002\u0002\u00b9\u00ba",
  "\u0003\u0002\u0002\u0002\u00ba\u00bb\u0007\u0005\u0002\u0002\u00bb\u00bd",
  "\u0007\u000f\u0002\u0002\u00bc\u00be\u0007\u001f\u0002\u0002\u00bd\u00bc",
  "\u0003\u0002\u0002\u0002\u00bd\u00be\u0003\u0002\u0002\u0002\u00be\u00bf",
  "\u0003\u0002\u0002\u0002\u00bf\u00c0\u0007\u0003\u0002\u0002\u00c0\u0013",
  "\u0003\u0002\u0002\u0002\u00c1\u00c2\u0005\u001e\u0010\u0002\u00c2\u00c3",
  "\u0007\u0011\u0002\u0002\u00c3\u00c5\u0003\u0002\u0002\u0002\u00c4\u00c1",
  "\u0003\u0002\u0002\u0002\u00c4\u00c5\u0003\u0002\u0002\u0002\u00c5\u00c7",
  "\u0003\u0002\u0002\u0002\u00c6\u00c8\u0007\u001f\u0002\u0002\u00c7\u00c6",
  "\u0003\u0002\u0002\u0002\u00c7\u00c8\u0003\u0002\u0002\u0002\u00c8\u00c9",
  "\u0003\u0002\u0002\u0002\u00c9\u00ca\u0007\u0003\u0002\u0002\u00ca\u00cc",
  "\u0007\u000f\u0002\u0002\u00cb\u00cd\u0007\u001f\u0002\u0002\u00cc\u00cb",
  "\u0003\u0002\u0002\u0002\u00cc\u00cd\u0003\u0002\u0002\u0002\u00cd\u00ce",
  "\u0003\u0002\u0002\u0002\u00ce\u00d0\u0007\u0003\u0002\u0002\u00cf\u00d1",
  "\u0007\u001f\u0002\u0002\u00d0\u00cf\u0003\u0002\u0002\u0002\u00d0\u00d1",
  "\u0003\u0002\u0002\u0002\u00d1\u00d2\u0003\u0002\u0002\u0002\u00d2\u00d3",
  "\u0007\u0005\u0002\u0002\u00d3\u0015\u0003\u0002\u0002\u0002\u00d4\u00d5",
  "\u0005\u001e\u0010\u0002\u00d5\u00d6\u0007\u0011\u0002\u0002\u00d6\u00d8",
  "\u0003\u0002\u0002\u0002\u00d7\u00d4\u0003\u0002\u0002\u0002\u00d7\u00d8",
  "\u0003\u0002\u0002\u0002\u00d8\u00da\u0003\u0002\u0002\u0002\u00d9\u00db",
  "\u0007\u001f\u0002\u0002\u00da\u00d9\u0003\u0002\u0002\u0002\u00da\u00db",
  "\u0003\u0002\u0002\u0002\u00db\u00dc\u0003\u0002\u0002\u0002\u00dc\u00dd",
  "\u0007\u0005\u0002\u0002\u00dd\u00df\u0007\u000f\u0002\u0002\u00de\u00e0",
  "\u0007\u001f\u0002\u0002\u00df\u00de\u0003\u0002\u0002\u0002\u00df\u00e0",
  "\u0003\u0002\u0002\u0002\u00e0\u00e1\u0003\u0002\u0002\u0002\u00e1\u00e2",
  "\u0007\u0005\u0002\u0002\u00e2\u0017\u0003\u0002\u0002\u0002\u00e3\u00e4",
  "\u0005\u001e\u0010\u0002\u00e4\u00e5\u0007\u0011\u0002\u0002\u00e5\u00e7",
  "\u0003\u0002\u0002\u0002\u00e6\u00e3\u0003\u0002\u0002\u0002\u00e6\u00e7",
  "\u0003\u0002\u0002\u0002\u00e7\u00e9\u0003\u0002\u0002\u0002\u00e8\u00ea",
  "\u0007\u001f\u0002\u0002\u00e9\u00e8\u0003\u0002\u0002\u0002\u00e9\u00ea",
  "\u0003\u0002\u0002\u0002\u00ea\u00eb\u0003\u0002\u0002\u0002\u00eb\u00ed",
  "\u0007\u0003\u0002\u0002\u00ec\u00ee\u0007\u001f\u0002\u0002\u00ed\u00ec",
  "\u0003\u0002\u0002\u0002\u00ed\u00ee\u0003\u0002\u0002\u0002\u00ee\u00ef",
  "\u0003\u0002\u0002\u0002\u00ef\u00f0\u0007\u0005\u0002\u0002\u00f0\u00f2",
  "\u0007\u000f\u0002\u0002\u00f1\u00f3\u0007\u001f\u0002\u0002\u00f2\u00f1",
  "\u0003\u0002\u0002\u0002\u00f2\u00f3\u0003\u0002\u0002\u0002\u00f3\u00f4",
  "\u0003\u0002\u0002\u0002\u00f4\u00f5\u0007\u0005\u0002\u0002\u00f5\u0019",
  "\u0003\u0002\u0002\u0002\u00f6\u00f7\u0005\u001e\u0010\u0002\u00f7\u00f8",
  "\u0007\u0011\u0002\u0002\u00f8\u00fa\u0003\u0002\u0002\u0002\u00f9\u00f6",
  "\u0003\u0002\u0002\u0002\u00f9\u00fa\u0003\u0002\u0002\u0002\u00fa\u00fc",
  "\u0003\u0002\u0002\u0002\u00fb\u00fd\u0007\u001f\u0002\u0002\u00fc\u00fb",
  "\u0003\u0002\u0002\u0002\u00fc\u00fd\u0003\u0002\u0002\u0002\u00fd\u00fe",
  "\u0003\u0002\u0002\u0002\u00fe\u00ff\u0007\u0005\u0002\u0002\u00ff\u0101",
  "\u0007\u000f\u0002\u0002\u0100\u0102\u0007\u001f\u0002\u0002\u0101\u0100",
  "\u0003\u0002\u0002\u0002\u0101\u0102\u0003\u0002\u0002\u0002\u0102\u0103",
  "\u0003\u0002\u0002\u0002\u0103\u0105\u0007\u0003\u0002\u0002\u0104\u0106",
  "\u0007\u001f\u0002\u0002\u0105\u0104\u0003\u0002\u0002\u0002\u0105\u0106",
  "\u0003\u0002\u0002\u0002\u0106\u0107\u0003\u0002\u0002\u0002\u0107\u0108",
  "\u0007\u0005\u0002\u0002\u0108\u001b\u0003\u0002\u0002\u0002\u0109\u010e",
  "\u0005\u0006\u0004\u0002\u010a\u010b\u0007\u000e\u0002\u0002\u010b\u010d",
  "\u0005\u0006\u0004\u0002\u010c\u010a\u0003\u0002\u0002\u0002\u010d\u0110",
  "\u0003\u0002\u0002\u0002\u010e\u010c\u0003\u0002\u0002\u0002\u010e\u010f",
  "\u0003\u0002\u0002\u0002\u010f\u0112\u0003\u0002\u0002\u0002\u0110\u010e",
  "\u0003\u0002\u0002\u0002\u0111\u0109\u0003\u0002\u0002\u0002\u0111\u0112",
  "\u0003\u0002\u0002\u0002\u0112\u001d\u0003\u0002\u0002\u0002\u0113\u0116",
  "\u0007\u0007\u0002\u0002\u0114\u0116\u0005 \u0011\u0002\u0115\u0113",
  "\u0003\u0002\u0002\u0002\u0115\u0114\u0003\u0002\u0002\u0002\u0116\u001f",
  "\u0003\u0002\u0002\u0002\u0117\u011b\u0007\u0003\u0002\u0002\u0118\u011a",
  "\t\u0005\u0002\u0002\u0119\u0118\u0003\u0002\u0002\u0002\u011a\u011d",
  "\u0003\u0002\u0002\u0002\u011b\u0119\u0003\u0002\u0002\u0002\u011b\u011c",
  "\u0003\u0002\u0002\u0002\u011c!\u0003\u0002\u0002\u0002\u011d\u011b",
  "\u0003\u0002\u0002\u0002\u011e\u011f\t\u0006\u0002\u0002\u011f#\u0003",
  "\u0002\u0002\u0002-46LNPgjnx}\u0080\u0084\u0089\u008d\u0094\u0097\u009b",
  "\u00a2\u00a5\u00aa\u00b1\u00b4\u00b8\u00bd\u00c4\u00c7\u00cc\u00d0\u00d7",
  "\u00da\u00df\u00e6\u00e9\u00ed\u00f2\u00f9\u00fc\u0101\u0105\u010e\u0111",
  "\u0115\u011b",
].join("");

var atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

var decisionsToDFA = atn.decisionToState.map(function (ds, index) {
  return new antlr4.dfa.DFA(ds, index);
});

var sharedContextCache = new antlr4.PredictionContextCache();

var literalNames = [null, null, "'.'"];

var symbolicNames = [
  null,
  "NCharacters",
  "Dot",
  "Int",
  "Number",
  "SingleQuoteString",
  "String",
  "Error",
  "LeftParen",
  "RightParen",
  "LeftBrace",
  "RightBrace",
  "Comma",
  "Colon",
  "SemiColon",
  "Bang",
  "Plus",
  "Minus",
  "Percent",
  "Power",
  "Divide",
  "Multiply",
  "GreaterThan",
  "GreaterThanOrEqualTo",
  "LessThan",
  "LessThanOrEqualTO",
  "Equal",
  "NotEqual",
  "Ampersand",
  "Dollar",
  "Underscore",
  "WS",
];

var ruleNames = [
  "start",
  "block",
  "expression",
  "atom",
  "range",
  "biRange",
  "uniRange",
  "columnWiseBiRange",
  "columnWiseWithRowOffsetFirstBiRange",
  "columnWiseWithRowOffsetLastBiRange",
  "rowWiseBiRange",
  "rowWiseWithColumnOffsetFirstBiRange",
  "rowWiseWithColumnOffsetLastBiRange",
  "arguments",
  "gridName",
  "identifier",
  "comparisonOperator",
];

function F7Parser(input) {
  antlr4.Parser.call(this, input);
  this._interp = new antlr4.atn.ParserATNSimulator(this, atn, decisionsToDFA, sharedContextCache);
  this.ruleNames = ruleNames;
  this.literalNames = literalNames;
  this.symbolicNames = symbolicNames;
  return this;
}

F7Parser.prototype = Object.create(antlr4.Parser.prototype);
F7Parser.prototype.constructor = F7Parser;

Object.defineProperty(F7Parser.prototype, "atn", {
  get: function () {
    return atn;
  },
});

F7Parser.EOF = antlr4.Token.EOF;
F7Parser.NCharacters = 1;
F7Parser.Dot = 2;
F7Parser.Int = 3;
F7Parser.Number = 4;
F7Parser.SingleQuoteString = 5;
F7Parser.String = 6;
F7Parser.Error = 7;
F7Parser.LeftParen = 8;
F7Parser.RightParen = 9;
F7Parser.LeftBrace = 10;
F7Parser.RightBrace = 11;
F7Parser.Comma = 12;
F7Parser.Colon = 13;
F7Parser.SemiColon = 14;
F7Parser.Bang = 15;
F7Parser.Plus = 16;
F7Parser.Minus = 17;
F7Parser.Percent = 18;
F7Parser.Power = 19;
F7Parser.Divide = 20;
F7Parser.Multiply = 21;
F7Parser.GreaterThan = 22;
F7Parser.GreaterThanOrEqualTo = 23;
F7Parser.LessThan = 24;
F7Parser.LessThanOrEqualTO = 25;
F7Parser.Equal = 26;
F7Parser.NotEqual = 27;
F7Parser.Ampersand = 28;
F7Parser.Dollar = 29;
F7Parser.Underscore = 30;
F7Parser.WS = 31;

F7Parser.RULE_start = 0;
F7Parser.RULE_block = 1;
F7Parser.RULE_expression = 2;
F7Parser.RULE_atom = 3;
F7Parser.RULE_range = 4;
F7Parser.RULE_biRange = 5;
F7Parser.RULE_uniRange = 6;
F7Parser.RULE_columnWiseBiRange = 7;
F7Parser.RULE_columnWiseWithRowOffsetFirstBiRange = 8;
F7Parser.RULE_columnWiseWithRowOffsetLastBiRange = 9;
F7Parser.RULE_rowWiseBiRange = 10;
F7Parser.RULE_rowWiseWithColumnOffsetFirstBiRange = 11;
F7Parser.RULE_rowWiseWithColumnOffsetLastBiRange = 12;
F7Parser.RULE_arguments = 13;
F7Parser.RULE_gridName = 14;
F7Parser.RULE_identifier = 15;
F7Parser.RULE_comparisonOperator = 16;

function StartContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_start;
  return this;
}

StartContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
StartContext.prototype.constructor = StartContext;

StartContext.prototype.block = function () {
  return this.getTypedRuleContext(BlockContext, 0);
};

StartContext.prototype.EOF = function () {
  return this.getToken(F7Parser.EOF, 0);
};

StartContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterStart(this);
  }
};

StartContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitStart(this);
  }
};

StartContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitStart(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.StartContext = StartContext;

F7Parser.prototype.start = function () {
  var localctx = new StartContext(this, this._ctx, this.state);
  this.enterRule(localctx, 0, F7Parser.RULE_start);
  try {
    this.enterOuterAlt(localctx, 1);
    this.state = 34;
    this.block();
    this.state = 35;
    this.match(F7Parser.EOF);
  } catch (re) {
    if (re instanceof antlr4.error.RecognitionException) {
      localctx.exception = re;
      this._errHandler.reportError(this, re);
      this._errHandler.recover(this, re);
    } else {
      throw re;
    }
  } finally {
    this.exitRule();
  }
  return localctx;
};

function BlockContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_block;
  return this;
}

BlockContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
BlockContext.prototype.constructor = BlockContext;

BlockContext.prototype.expression = function () {
  return this.getTypedRuleContext(ExpressionContext, 0);
};

BlockContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterBlock(this);
  }
};

BlockContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitBlock(this);
  }
};

BlockContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitBlock(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.BlockContext = BlockContext;

F7Parser.prototype.block = function () {
  var localctx = new BlockContext(this, this._ctx, this.state);
  this.enterRule(localctx, 2, F7Parser.RULE_block);
  try {
    this.enterOuterAlt(localctx, 1);
    this.state = 37;
    this.expression(0);
  } catch (re) {
    if (re instanceof antlr4.error.RecognitionException) {
      localctx.exception = re;
      this._errHandler.reportError(this, re);
      this._errHandler.recover(this, re);
    } else {
      throw re;
    }
  } finally {
    this.exitRule();
  }
  return localctx;
};

function ExpressionContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_expression;
  return this;
}

ExpressionContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ExpressionContext.prototype.constructor = ExpressionContext;

ExpressionContext.prototype.copyFrom = function (ctx) {
  antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};

function UnaryPercentExpressionContext(parser, ctx) {
  ExpressionContext.call(this, parser);
  ExpressionContext.prototype.copyFrom.call(this, ctx);
  return this;
}

UnaryPercentExpressionContext.prototype = Object.create(ExpressionContext.prototype);
UnaryPercentExpressionContext.prototype.constructor = UnaryPercentExpressionContext;

F7Parser.UnaryPercentExpressionContext = UnaryPercentExpressionContext;

UnaryPercentExpressionContext.prototype.expression = function () {
  return this.getTypedRuleContext(ExpressionContext, 0);
};

UnaryPercentExpressionContext.prototype.Percent = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Percent);
  } else {
    return this.getToken(F7Parser.Percent, i);
  }
};

UnaryPercentExpressionContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterUnaryPercentExpression(this);
  }
};

UnaryPercentExpressionContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitUnaryPercentExpression(this);
  }
};

UnaryPercentExpressionContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitUnaryPercentExpression(this);
  } else {
    return visitor.visitChildren(this);
  }
};

function UnaryMinusExpressionContext(parser, ctx) {
  ExpressionContext.call(this, parser);
  ExpressionContext.prototype.copyFrom.call(this, ctx);
  return this;
}

UnaryMinusExpressionContext.prototype = Object.create(ExpressionContext.prototype);
UnaryMinusExpressionContext.prototype.constructor = UnaryMinusExpressionContext;

F7Parser.UnaryMinusExpressionContext = UnaryMinusExpressionContext;

UnaryMinusExpressionContext.prototype.Minus = function () {
  return this.getToken(F7Parser.Minus, 0);
};

UnaryMinusExpressionContext.prototype.expression = function () {
  return this.getTypedRuleContext(ExpressionContext, 0);
};
UnaryMinusExpressionContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterUnaryMinusExpression(this);
  }
};

UnaryMinusExpressionContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitUnaryMinusExpression(this);
  }
};

UnaryMinusExpressionContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitUnaryMinusExpression(this);
  } else {
    return visitor.visitChildren(this);
  }
};

function PowerExpressionContext(parser, ctx) {
  ExpressionContext.call(this, parser);
  this.left = null; // ExpressionContext;
  this.op = null; // Token;
  this.right = null; // ExpressionContext;
  ExpressionContext.prototype.copyFrom.call(this, ctx);
  return this;
}

PowerExpressionContext.prototype = Object.create(ExpressionContext.prototype);
PowerExpressionContext.prototype.constructor = PowerExpressionContext;

F7Parser.PowerExpressionContext = PowerExpressionContext;

PowerExpressionContext.prototype.expression = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTypedRuleContexts(ExpressionContext);
  } else {
    return this.getTypedRuleContext(ExpressionContext, i);
  }
};

PowerExpressionContext.prototype.Power = function () {
  return this.getToken(F7Parser.Power, 0);
};
PowerExpressionContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterPowerExpression(this);
  }
};

PowerExpressionContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitPowerExpression(this);
  }
};

PowerExpressionContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitPowerExpression(this);
  } else {
    return visitor.visitChildren(this);
  }
};

function UnaryPlusExpressionContext(parser, ctx) {
  ExpressionContext.call(this, parser);
  ExpressionContext.prototype.copyFrom.call(this, ctx);
  return this;
}

UnaryPlusExpressionContext.prototype = Object.create(ExpressionContext.prototype);
UnaryPlusExpressionContext.prototype.constructor = UnaryPlusExpressionContext;

F7Parser.UnaryPlusExpressionContext = UnaryPlusExpressionContext;

UnaryPlusExpressionContext.prototype.Plus = function () {
  return this.getToken(F7Parser.Plus, 0);
};

UnaryPlusExpressionContext.prototype.expression = function () {
  return this.getTypedRuleContext(ExpressionContext, 0);
};
UnaryPlusExpressionContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterUnaryPlusExpression(this);
  }
};

UnaryPlusExpressionContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitUnaryPlusExpression(this);
  }
};

UnaryPlusExpressionContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitUnaryPlusExpression(this);
  } else {
    return visitor.visitChildren(this);
  }
};

function AtomExpressionContext(parser, ctx) {
  ExpressionContext.call(this, parser);
  ExpressionContext.prototype.copyFrom.call(this, ctx);
  return this;
}

AtomExpressionContext.prototype = Object.create(ExpressionContext.prototype);
AtomExpressionContext.prototype.constructor = AtomExpressionContext;

F7Parser.AtomExpressionContext = AtomExpressionContext;

AtomExpressionContext.prototype.atom = function () {
  return this.getTypedRuleContext(AtomContext, 0);
};
AtomExpressionContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterAtomExpression(this);
  }
};

AtomExpressionContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitAtomExpression(this);
  }
};

AtomExpressionContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitAtomExpression(this);
  } else {
    return visitor.visitChildren(this);
  }
};

function AdditiveExpressionContext(parser, ctx) {
  ExpressionContext.call(this, parser);
  this.left = null; // ExpressionContext;
  this.op = null; // Token;
  this.right = null; // ExpressionContext;
  ExpressionContext.prototype.copyFrom.call(this, ctx);
  return this;
}

AdditiveExpressionContext.prototype = Object.create(ExpressionContext.prototype);
AdditiveExpressionContext.prototype.constructor = AdditiveExpressionContext;

F7Parser.AdditiveExpressionContext = AdditiveExpressionContext;

AdditiveExpressionContext.prototype.expression = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTypedRuleContexts(ExpressionContext);
  } else {
    return this.getTypedRuleContext(ExpressionContext, i);
  }
};

AdditiveExpressionContext.prototype.Plus = function () {
  return this.getToken(F7Parser.Plus, 0);
};

AdditiveExpressionContext.prototype.Minus = function () {
  return this.getToken(F7Parser.Minus, 0);
};
AdditiveExpressionContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterAdditiveExpression(this);
  }
};

AdditiveExpressionContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitAdditiveExpression(this);
  }
};

AdditiveExpressionContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitAdditiveExpression(this);
  } else {
    return visitor.visitChildren(this);
  }
};

function RelationalExpressionContext(parser, ctx) {
  ExpressionContext.call(this, parser);
  this.left = null; // ExpressionContext;
  this.op = null; // ComparisonOperatorContext;
  this.right = null; // ExpressionContext;
  ExpressionContext.prototype.copyFrom.call(this, ctx);
  return this;
}

RelationalExpressionContext.prototype = Object.create(ExpressionContext.prototype);
RelationalExpressionContext.prototype.constructor = RelationalExpressionContext;

F7Parser.RelationalExpressionContext = RelationalExpressionContext;

RelationalExpressionContext.prototype.expression = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTypedRuleContexts(ExpressionContext);
  } else {
    return this.getTypedRuleContext(ExpressionContext, i);
  }
};

RelationalExpressionContext.prototype.comparisonOperator = function () {
  return this.getTypedRuleContext(ComparisonOperatorContext, 0);
};
RelationalExpressionContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterRelationalExpression(this);
  }
};

RelationalExpressionContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitRelationalExpression(this);
  }
};

RelationalExpressionContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitRelationalExpression(this);
  } else {
    return visitor.visitChildren(this);
  }
};

function RangeExpressionContext(parser, ctx) {
  ExpressionContext.call(this, parser);
  this.separator = null; // Token;
  ExpressionContext.prototype.copyFrom.call(this, ctx);
  return this;
}

RangeExpressionContext.prototype = Object.create(ExpressionContext.prototype);
RangeExpressionContext.prototype.constructor = RangeExpressionContext;

F7Parser.RangeExpressionContext = RangeExpressionContext;

RangeExpressionContext.prototype.atom = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTypedRuleContexts(AtomContext);
  } else {
    return this.getTypedRuleContext(AtomContext, i);
  }
};

RangeExpressionContext.prototype.Colon = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Colon);
  } else {
    return this.getToken(F7Parser.Colon, i);
  }
};

RangeExpressionContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterRangeExpression(this);
  }
};

RangeExpressionContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitRangeExpression(this);
  }
};

RangeExpressionContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitRangeExpression(this);
  } else {
    return visitor.visitChildren(this);
  }
};

function MultiplicationExpressionContext(parser, ctx) {
  ExpressionContext.call(this, parser);
  this.left = null; // ExpressionContext;
  this.op = null; // Token;
  this.right = null; // ExpressionContext;
  ExpressionContext.prototype.copyFrom.call(this, ctx);
  return this;
}

MultiplicationExpressionContext.prototype = Object.create(ExpressionContext.prototype);
MultiplicationExpressionContext.prototype.constructor = MultiplicationExpressionContext;

F7Parser.MultiplicationExpressionContext = MultiplicationExpressionContext;

MultiplicationExpressionContext.prototype.expression = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTypedRuleContexts(ExpressionContext);
  } else {
    return this.getTypedRuleContext(ExpressionContext, i);
  }
};

MultiplicationExpressionContext.prototype.Multiply = function () {
  return this.getToken(F7Parser.Multiply, 0);
};

MultiplicationExpressionContext.prototype.Divide = function () {
  return this.getToken(F7Parser.Divide, 0);
};
MultiplicationExpressionContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterMultiplicationExpression(this);
  }
};

MultiplicationExpressionContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitMultiplicationExpression(this);
  }
};

MultiplicationExpressionContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitMultiplicationExpression(this);
  } else {
    return visitor.visitChildren(this);
  }
};

function ConcatExpressionContext(parser, ctx) {
  ExpressionContext.call(this, parser);
  this.left = null; // ExpressionContext;
  this.op = null; // Token;
  this.right = null; // ExpressionContext;
  ExpressionContext.prototype.copyFrom.call(this, ctx);
  return this;
}

ConcatExpressionContext.prototype = Object.create(ExpressionContext.prototype);
ConcatExpressionContext.prototype.constructor = ConcatExpressionContext;

F7Parser.ConcatExpressionContext = ConcatExpressionContext;

ConcatExpressionContext.prototype.expression = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTypedRuleContexts(ExpressionContext);
  } else {
    return this.getTypedRuleContext(ExpressionContext, i);
  }
};

ConcatExpressionContext.prototype.Ampersand = function () {
  return this.getToken(F7Parser.Ampersand, 0);
};
ConcatExpressionContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterConcatExpression(this);
  }
};

ConcatExpressionContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitConcatExpression(this);
  }
};

ConcatExpressionContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitConcatExpression(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.prototype.expression = function (_p) {
  if (_p === undefined) {
    _p = 0;
  }
  var _parentctx = this._ctx;
  var _parentState = this.state;
  var localctx = new ExpressionContext(this, this._ctx, _parentState);
  var _prevctx = localctx;
  var _startState = 4;
  this.enterRecursionRule(localctx, 4, F7Parser.RULE_expression, _p);
  var _la = 0; // Token type
  try {
    this.enterOuterAlt(localctx, 1);
    this.state = 52;
    this._errHandler.sync(this);
    var la_ = this._interp.adaptivePredict(this._input, 1, this._ctx);
    switch (la_) {
      case 1:
        localctx = new UnaryMinusExpressionContext(this, localctx);
        this._ctx = localctx;
        _prevctx = localctx;

        this.state = 40;
        this.match(F7Parser.Minus);
        this.state = 41;
        this.expression(10);
        break;

      case 2:
        localctx = new UnaryPlusExpressionContext(this, localctx);
        this._ctx = localctx;
        _prevctx = localctx;
        this.state = 42;
        this.match(F7Parser.Plus);
        this.state = 43;
        this.expression(9);
        break;

      case 3:
        localctx = new AtomExpressionContext(this, localctx);
        this._ctx = localctx;
        _prevctx = localctx;
        this.state = 44;
        this.atom();
        break;

      case 4:
        localctx = new RangeExpressionContext(this, localctx);
        this._ctx = localctx;
        _prevctx = localctx;
        this.state = 45;
        this.atom();
        this.state = 48;
        this._errHandler.sync(this);
        var _alt = 1;
        do {
          switch (_alt) {
            case 1:
              this.state = 46;
              localctx.separator = this.match(F7Parser.Colon);
              this.state = 47;
              this.atom();
              break;
            default:
              throw new antlr4.error.NoViableAltException(this);
          }
          this.state = 50;
          this._errHandler.sync(this);
          _alt = this._interp.adaptivePredict(this._input, 0, this._ctx);
        } while (_alt != 2 && _alt != antlr4.atn.ATN.INVALID_ALT_NUMBER);
        break;
    }
    this._ctx.stop = this._input.LT(-1);
    this.state = 78;
    this._errHandler.sync(this);
    var _alt = this._interp.adaptivePredict(this._input, 4, this._ctx);
    while (_alt != 2 && _alt != antlr4.atn.ATN.INVALID_ALT_NUMBER) {
      if (_alt === 1) {
        if (this._parseListeners !== null) {
          this.triggerExitRuleEvent();
        }
        _prevctx = localctx;
        this.state = 76;
        this._errHandler.sync(this);
        var la_ = this._interp.adaptivePredict(this._input, 3, this._ctx);
        switch (la_) {
          case 1:
            localctx = new PowerExpressionContext(
              this,
              new ExpressionContext(this, _parentctx, _parentState)
            );
            localctx.left = _prevctx;
            this.pushNewRecursionContext(localctx, _startState, F7Parser.RULE_expression);
            this.state = 54;
            if (!this.precpred(this._ctx, 7)) {
              throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 7)");
            }
            this.state = 55;
            localctx.op = this.match(F7Parser.Power);
            this.state = 56;
            localctx.right = this.expression(8);
            break;

          case 2:
            localctx = new MultiplicationExpressionContext(
              this,
              new ExpressionContext(this, _parentctx, _parentState)
            );
            localctx.left = _prevctx;
            this.pushNewRecursionContext(localctx, _startState, F7Parser.RULE_expression);
            this.state = 57;
            if (!this.precpred(this._ctx, 6)) {
              throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 6)");
            }
            this.state = 58;
            localctx.op = this._input.LT(1);
            _la = this._input.LA(1);
            if (!(_la === F7Parser.Divide || _la === F7Parser.Multiply)) {
              localctx.op = this._errHandler.recoverInline(this);
            } else {
              this._errHandler.reportMatch(this);
              this.consume();
            }
            this.state = 59;
            localctx.right = this.expression(7);
            break;

          case 3:
            localctx = new AdditiveExpressionContext(
              this,
              new ExpressionContext(this, _parentctx, _parentState)
            );
            localctx.left = _prevctx;
            this.pushNewRecursionContext(localctx, _startState, F7Parser.RULE_expression);
            this.state = 60;
            if (!this.precpred(this._ctx, 5)) {
              throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 5)");
            }
            this.state = 61;
            localctx.op = this._input.LT(1);
            _la = this._input.LA(1);
            if (!(_la === F7Parser.Plus || _la === F7Parser.Minus)) {
              localctx.op = this._errHandler.recoverInline(this);
            } else {
              this._errHandler.reportMatch(this);
              this.consume();
            }
            this.state = 62;
            localctx.right = this.expression(6);
            break;

          case 4:
            localctx = new RelationalExpressionContext(
              this,
              new ExpressionContext(this, _parentctx, _parentState)
            );
            localctx.left = _prevctx;
            this.pushNewRecursionContext(localctx, _startState, F7Parser.RULE_expression);
            this.state = 63;
            if (!this.precpred(this._ctx, 4)) {
              throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 4)");
            }
            this.state = 64;
            localctx.op = this.comparisonOperator();
            this.state = 65;
            localctx.right = this.expression(5);
            break;

          case 5:
            localctx = new ConcatExpressionContext(
              this,
              new ExpressionContext(this, _parentctx, _parentState)
            );
            localctx.left = _prevctx;
            this.pushNewRecursionContext(localctx, _startState, F7Parser.RULE_expression);
            this.state = 67;
            if (!this.precpred(this._ctx, 3)) {
              throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 3)");
            }
            this.state = 68;
            localctx.op = this.match(F7Parser.Ampersand);
            this.state = 69;
            localctx.right = this.expression(4);
            break;

          case 6:
            localctx = new UnaryPercentExpressionContext(
              this,
              new ExpressionContext(this, _parentctx, _parentState)
            );
            this.pushNewRecursionContext(localctx, _startState, F7Parser.RULE_expression);
            this.state = 70;
            if (!this.precpred(this._ctx, 8)) {
              throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 8)");
            }
            this.state = 72;
            this._errHandler.sync(this);
            var _alt = 1;
            do {
              switch (_alt) {
                case 1:
                  this.state = 71;
                  this.match(F7Parser.Percent);
                  break;
                default:
                  throw new antlr4.error.NoViableAltException(this);
              }
              this.state = 74;
              this._errHandler.sync(this);
              _alt = this._interp.adaptivePredict(this._input, 2, this._ctx);
            } while (_alt != 2 && _alt != antlr4.atn.ATN.INVALID_ALT_NUMBER);
            break;
        }
      }
      this.state = 80;
      this._errHandler.sync(this);
      _alt = this._interp.adaptivePredict(this._input, 4, this._ctx);
    }
  } catch (error) {
    if (error instanceof antlr4.error.RecognitionException) {
      localctx.exception = error;
      this._errHandler.reportError(this, error);
      this._errHandler.recover(this, error);
    } else {
      throw error;
    }
  } finally {
    this.unrollRecursionContexts(_parentctx);
  }
  return localctx;
};

function AtomContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_atom;
  return this;
}

AtomContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
AtomContext.prototype.constructor = AtomContext;

AtomContext.prototype.copyFrom = function (ctx) {
  antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};

function NamedAtomContext(parser, ctx) {
  AtomContext.call(this, parser);
  AtomContext.prototype.copyFrom.call(this, ctx);
  return this;
}

NamedAtomContext.prototype = Object.create(AtomContext.prototype);
NamedAtomContext.prototype.constructor = NamedAtomContext;

F7Parser.NamedAtomContext = NamedAtomContext;

NamedAtomContext.prototype.identifier = function () {
  return this.getTypedRuleContext(IdentifierContext, 0);
};
NamedAtomContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterNamedAtom(this);
  }
};

NamedAtomContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitNamedAtom(this);
  }
};

NamedAtomContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitNamedAtom(this);
  } else {
    return visitor.visitChildren(this);
  }
};

function ErrorAtomContext(parser, ctx) {
  AtomContext.call(this, parser);
  AtomContext.prototype.copyFrom.call(this, ctx);
  return this;
}

ErrorAtomContext.prototype = Object.create(AtomContext.prototype);
ErrorAtomContext.prototype.constructor = ErrorAtomContext;

F7Parser.ErrorAtomContext = ErrorAtomContext;

ErrorAtomContext.prototype.Error = function () {
  return this.getToken(F7Parser.Error, 0);
};
ErrorAtomContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterErrorAtom(this);
  }
};

ErrorAtomContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitErrorAtom(this);
  }
};

ErrorAtomContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitErrorAtom(this);
  } else {
    return visitor.visitChildren(this);
  }
};

function ListAtomContext(parser, ctx) {
  AtomContext.call(this, parser);
  this.separator = null; // Token;
  AtomContext.prototype.copyFrom.call(this, ctx);
  return this;
}

ListAtomContext.prototype = Object.create(AtomContext.prototype);
ListAtomContext.prototype.constructor = ListAtomContext;

F7Parser.ListAtomContext = ListAtomContext;

ListAtomContext.prototype.LeftBrace = function () {
  return this.getToken(F7Parser.LeftBrace, 0);
};

ListAtomContext.prototype.RightBrace = function () {
  return this.getToken(F7Parser.RightBrace, 0);
};

ListAtomContext.prototype.expression = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTypedRuleContexts(ExpressionContext);
  } else {
    return this.getTypedRuleContext(ExpressionContext, i);
  }
};

ListAtomContext.prototype.Comma = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Comma);
  } else {
    return this.getToken(F7Parser.Comma, i);
  }
};

ListAtomContext.prototype.SemiColon = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.SemiColon);
  } else {
    return this.getToken(F7Parser.SemiColon, i);
  }
};

ListAtomContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterListAtom(this);
  }
};

ListAtomContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitListAtom(this);
  }
};

ListAtomContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitListAtom(this);
  } else {
    return visitor.visitChildren(this);
  }
};

function ParentheticalAtomContext(parser, ctx) {
  AtomContext.call(this, parser);
  AtomContext.prototype.copyFrom.call(this, ctx);
  return this;
}

ParentheticalAtomContext.prototype = Object.create(AtomContext.prototype);
ParentheticalAtomContext.prototype.constructor = ParentheticalAtomContext;

F7Parser.ParentheticalAtomContext = ParentheticalAtomContext;

ParentheticalAtomContext.prototype.LeftParen = function () {
  return this.getToken(F7Parser.LeftParen, 0);
};

ParentheticalAtomContext.prototype.expression = function () {
  return this.getTypedRuleContext(ExpressionContext, 0);
};

ParentheticalAtomContext.prototype.RightParen = function () {
  return this.getToken(F7Parser.RightParen, 0);
};
ParentheticalAtomContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterParentheticalAtom(this);
  }
};

ParentheticalAtomContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitParentheticalAtom(this);
  }
};

ParentheticalAtomContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitParentheticalAtom(this);
  } else {
    return visitor.visitChildren(this);
  }
};

function CellAtomContext(parser, ctx) {
  AtomContext.call(this, parser);
  AtomContext.prototype.copyFrom.call(this, ctx);
  return this;
}

CellAtomContext.prototype = Object.create(AtomContext.prototype);
CellAtomContext.prototype.constructor = CellAtomContext;

F7Parser.CellAtomContext = CellAtomContext;

CellAtomContext.prototype.range = function () {
  return this.getTypedRuleContext(RangeContext, 0);
};
CellAtomContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterCellAtom(this);
  }
};

CellAtomContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitCellAtom(this);
  }
};

CellAtomContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitCellAtom(this);
  } else {
    return visitor.visitChildren(this);
  }
};

function StringAtomContext(parser, ctx) {
  AtomContext.call(this, parser);
  AtomContext.prototype.copyFrom.call(this, ctx);
  return this;
}

StringAtomContext.prototype = Object.create(AtomContext.prototype);
StringAtomContext.prototype.constructor = StringAtomContext;

F7Parser.StringAtomContext = StringAtomContext;

StringAtomContext.prototype.String = function () {
  return this.getToken(F7Parser.String, 0);
};
StringAtomContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterStringAtom(this);
  }
};

StringAtomContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitStringAtom(this);
  }
};

StringAtomContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitStringAtom(this);
  } else {
    return visitor.visitChildren(this);
  }
};

function FormulaAtomContext(parser, ctx) {
  AtomContext.call(this, parser);
  this.name = null; // IdentifierContext;
  AtomContext.prototype.copyFrom.call(this, ctx);
  return this;
}

FormulaAtomContext.prototype = Object.create(AtomContext.prototype);
FormulaAtomContext.prototype.constructor = FormulaAtomContext;

F7Parser.FormulaAtomContext = FormulaAtomContext;

FormulaAtomContext.prototype.LeftParen = function () {
  return this.getToken(F7Parser.LeftParen, 0);
};

FormulaAtomContext.prototype.arguments = function () {
  return this.getTypedRuleContext(ArgumentsContext, 0);
};

FormulaAtomContext.prototype.RightParen = function () {
  return this.getToken(F7Parser.RightParen, 0);
};

FormulaAtomContext.prototype.identifier = function () {
  return this.getTypedRuleContext(IdentifierContext, 0);
};
FormulaAtomContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterFormulaAtom(this);
  }
};

FormulaAtomContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitFormulaAtom(this);
  }
};

FormulaAtomContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitFormulaAtom(this);
  } else {
    return visitor.visitChildren(this);
  }
};

function NumberAtomContext(parser, ctx) {
  AtomContext.call(this, parser);
  AtomContext.prototype.copyFrom.call(this, ctx);
  return this;
}

NumberAtomContext.prototype = Object.create(AtomContext.prototype);
NumberAtomContext.prototype.constructor = NumberAtomContext;

F7Parser.NumberAtomContext = NumberAtomContext;

NumberAtomContext.prototype.Int = function () {
  return this.getToken(F7Parser.Int, 0);
};

NumberAtomContext.prototype.Number = function () {
  return this.getToken(F7Parser.Number, 0);
};
NumberAtomContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterNumberAtom(this);
  }
};

NumberAtomContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitNumberAtom(this);
  }
};

NumberAtomContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitNumberAtom(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.AtomContext = AtomContext;

F7Parser.prototype.atom = function () {
  var localctx = new AtomContext(this, this._ctx, this.state);
  this.enterRule(localctx, 6, F7Parser.RULE_atom);
  var _la = 0; // Token type
  try {
    this.state = 108;
    this._errHandler.sync(this);
    var la_ = this._interp.adaptivePredict(this._input, 7, this._ctx);
    switch (la_) {
      case 1:
        localctx = new CellAtomContext(this, localctx);
        this.enterOuterAlt(localctx, 1);
        this.state = 81;
        this.range();
        break;

      case 2:
        localctx = new StringAtomContext(this, localctx);
        this.enterOuterAlt(localctx, 2);
        this.state = 82;
        this.match(F7Parser.String);
        break;

      case 3:
        localctx = new ErrorAtomContext(this, localctx);
        this.enterOuterAlt(localctx, 3);
        this.state = 83;
        this.match(F7Parser.Error);
        break;

      case 4:
        localctx = new NumberAtomContext(this, localctx);
        this.enterOuterAlt(localctx, 4);
        this.state = 84;
        this.match(F7Parser.Int);
        break;

      case 5:
        localctx = new NumberAtomContext(this, localctx);
        this.enterOuterAlt(localctx, 5);
        this.state = 85;
        this.match(F7Parser.Number);
        break;

      case 6:
        localctx = new ParentheticalAtomContext(this, localctx);
        this.enterOuterAlt(localctx, 6);
        this.state = 86;
        this.match(F7Parser.LeftParen);
        this.state = 87;
        this.expression(0);
        this.state = 88;
        this.match(F7Parser.RightParen);
        break;

      case 7:
        localctx = new FormulaAtomContext(this, localctx);
        this.enterOuterAlt(localctx, 7);
        this.state = 90;
        localctx.name = this.identifier();
        this.state = 91;
        this.match(F7Parser.LeftParen);
        this.state = 92;
        this.arguments();
        this.state = 93;
        this.match(F7Parser.RightParen);
        break;

      case 8:
        localctx = new ListAtomContext(this, localctx);
        this.enterOuterAlt(localctx, 8);
        this.state = 95;
        this.match(F7Parser.LeftBrace);
        this.state = 104;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        if (
          (_la & ~0x1f) == 0 &&
          ((1 << _la) &
            ((1 << F7Parser.NCharacters) |
              (1 << F7Parser.Int) |
              (1 << F7Parser.Number) |
              (1 << F7Parser.SingleQuoteString) |
              (1 << F7Parser.String) |
              (1 << F7Parser.Error) |
              (1 << F7Parser.LeftParen) |
              (1 << F7Parser.LeftBrace) |
              (1 << F7Parser.Plus) |
              (1 << F7Parser.Minus) |
              (1 << F7Parser.Dollar))) !==
            0
        ) {
          this.state = 96;
          this.expression(0);
          this.state = 101;
          this._errHandler.sync(this);
          _la = this._input.LA(1);
          while (_la === F7Parser.Comma || _la === F7Parser.SemiColon) {
            this.state = 97;
            localctx.separator = this._input.LT(1);
            _la = this._input.LA(1);
            if (!(_la === F7Parser.Comma || _la === F7Parser.SemiColon)) {
              localctx.separator = this._errHandler.recoverInline(this);
            } else {
              this._errHandler.reportMatch(this);
              this.consume();
            }
            this.state = 98;
            this.expression(0);
            this.state = 103;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
          }
        }

        this.state = 106;
        this.match(F7Parser.RightBrace);
        break;

      case 9:
        localctx = new NamedAtomContext(this, localctx);
        this.enterOuterAlt(localctx, 9);
        this.state = 107;
        this.identifier();
        break;
    }
  } catch (re) {
    if (re instanceof antlr4.error.RecognitionException) {
      localctx.exception = re;
      this._errHandler.reportError(this, re);
      this._errHandler.recover(this, re);
    } else {
      throw re;
    }
  } finally {
    this.exitRule();
  }
  return localctx;
};

function RangeContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_range;
  return this;
}

RangeContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
RangeContext.prototype.constructor = RangeContext;

RangeContext.prototype.biRange = function () {
  return this.getTypedRuleContext(BiRangeContext, 0);
};

RangeContext.prototype.uniRange = function () {
  return this.getTypedRuleContext(UniRangeContext, 0);
};

RangeContext.prototype.columnWiseBiRange = function () {
  return this.getTypedRuleContext(ColumnWiseBiRangeContext, 0);
};

RangeContext.prototype.columnWiseWithRowOffsetFirstBiRange = function () {
  return this.getTypedRuleContext(ColumnWiseWithRowOffsetFirstBiRangeContext, 0);
};

RangeContext.prototype.columnWiseWithRowOffsetLastBiRange = function () {
  return this.getTypedRuleContext(ColumnWiseWithRowOffsetLastBiRangeContext, 0);
};

RangeContext.prototype.rowWiseBiRange = function () {
  return this.getTypedRuleContext(RowWiseBiRangeContext, 0);
};

RangeContext.prototype.rowWiseWithColumnOffsetFirstBiRange = function () {
  return this.getTypedRuleContext(RowWiseWithColumnOffsetFirstBiRangeContext, 0);
};

RangeContext.prototype.rowWiseWithColumnOffsetLastBiRange = function () {
  return this.getTypedRuleContext(RowWiseWithColumnOffsetLastBiRangeContext, 0);
};

RangeContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterRange(this);
  }
};

RangeContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitRange(this);
  }
};

RangeContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitRange(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.RangeContext = RangeContext;

F7Parser.prototype.range = function () {
  var localctx = new RangeContext(this, this._ctx, this.state);
  this.enterRule(localctx, 8, F7Parser.RULE_range);
  try {
    this.state = 118;
    this._errHandler.sync(this);
    var la_ = this._interp.adaptivePredict(this._input, 8, this._ctx);
    switch (la_) {
      case 1:
        this.enterOuterAlt(localctx, 1);
        this.state = 110;
        this.biRange();
        break;

      case 2:
        this.enterOuterAlt(localctx, 2);
        this.state = 111;
        this.uniRange();
        break;

      case 3:
        this.enterOuterAlt(localctx, 3);
        this.state = 112;
        this.columnWiseBiRange();
        break;

      case 4:
        this.enterOuterAlt(localctx, 4);
        this.state = 113;
        this.columnWiseWithRowOffsetFirstBiRange();
        break;

      case 5:
        this.enterOuterAlt(localctx, 5);
        this.state = 114;
        this.columnWiseWithRowOffsetLastBiRange();
        break;

      case 6:
        this.enterOuterAlt(localctx, 6);
        this.state = 115;
        this.rowWiseBiRange();
        break;

      case 7:
        this.enterOuterAlt(localctx, 7);
        this.state = 116;
        this.rowWiseWithColumnOffsetFirstBiRange();
        break;

      case 8:
        this.enterOuterAlt(localctx, 8);
        this.state = 117;
        this.rowWiseWithColumnOffsetLastBiRange();
        break;
    }
  } catch (re) {
    if (re instanceof antlr4.error.RecognitionException) {
      localctx.exception = re;
      this._errHandler.reportError(this, re);
      this._errHandler.recover(this, re);
    } else {
      throw re;
    }
  } finally {
    this.exitRule();
  }
  return localctx;
};

function BiRangeContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_biRange;
  this.grid = null; // GridNameContext
  this.absoFirstColumn = null; // Token
  this.firstColumn = null; // Token
  this.absoRow = null; // Token
  this.firstRow = null; // Token
  this.absoLastColumn = null; // Token
  this.lastColumn = null; // Token
  this.absoLastRow = null; // Token
  this.lastRow = null; // Token
  return this;
}

BiRangeContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
BiRangeContext.prototype.constructor = BiRangeContext;

BiRangeContext.prototype.Colon = function () {
  return this.getToken(F7Parser.Colon, 0);
};

BiRangeContext.prototype.NCharacters = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.NCharacters);
  } else {
    return this.getToken(F7Parser.NCharacters, i);
  }
};

BiRangeContext.prototype.Int = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Int);
  } else {
    return this.getToken(F7Parser.Int, i);
  }
};

BiRangeContext.prototype.Bang = function () {
  return this.getToken(F7Parser.Bang, 0);
};

BiRangeContext.prototype.gridName = function () {
  return this.getTypedRuleContext(GridNameContext, 0);
};

BiRangeContext.prototype.Dollar = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Dollar);
  } else {
    return this.getToken(F7Parser.Dollar, i);
  }
};

BiRangeContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterBiRange(this);
  }
};

BiRangeContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitBiRange(this);
  }
};

BiRangeContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitBiRange(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.BiRangeContext = BiRangeContext;

F7Parser.prototype.biRange = function () {
  var localctx = new BiRangeContext(this, this._ctx, this.state);
  this.enterRule(localctx, 10, F7Parser.RULE_biRange);
  var _la = 0; // Token type
  try {
    this.enterOuterAlt(localctx, 1);
    this.state = 123;
    this._errHandler.sync(this);
    var la_ = this._interp.adaptivePredict(this._input, 9, this._ctx);
    if (la_ === 1) {
      this.state = 120;
      localctx.grid = this.gridName();
      this.state = 121;
      this.match(F7Parser.Bang);
    }
    this.state = 126;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 125;
      localctx.absoFirstColumn = this.match(F7Parser.Dollar);
    }

    this.state = 128;
    localctx.firstColumn = this.match(F7Parser.NCharacters);
    this.state = 130;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 129;
      localctx.absoRow = this.match(F7Parser.Dollar);
    }

    this.state = 132;
    localctx.firstRow = this.match(F7Parser.Int);
    this.state = 133;
    this.match(F7Parser.Colon);
    this.state = 135;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 134;
      localctx.absoLastColumn = this.match(F7Parser.Dollar);
    }

    this.state = 137;
    localctx.lastColumn = this.match(F7Parser.NCharacters);
    this.state = 139;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 138;
      localctx.absoLastRow = this.match(F7Parser.Dollar);
    }

    this.state = 141;
    localctx.lastRow = this.match(F7Parser.Int);
  } catch (re) {
    if (re instanceof antlr4.error.RecognitionException) {
      localctx.exception = re;
      this._errHandler.reportError(this, re);
      this._errHandler.recover(this, re);
    } else {
      throw re;
    }
  } finally {
    this.exitRule();
  }
  return localctx;
};

function UniRangeContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_uniRange;
  this.grid = null; // GridNameContext
  this.absoFirstColumn = null; // Token
  this.firstColumn = null; // Token
  this.absoFirstRow = null; // Token
  this.firstRow = null; // Token
  return this;
}

UniRangeContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
UniRangeContext.prototype.constructor = UniRangeContext;

UniRangeContext.prototype.NCharacters = function () {
  return this.getToken(F7Parser.NCharacters, 0);
};

UniRangeContext.prototype.Int = function () {
  return this.getToken(F7Parser.Int, 0);
};

UniRangeContext.prototype.Bang = function () {
  return this.getToken(F7Parser.Bang, 0);
};

UniRangeContext.prototype.gridName = function () {
  return this.getTypedRuleContext(GridNameContext, 0);
};

UniRangeContext.prototype.Dollar = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Dollar);
  } else {
    return this.getToken(F7Parser.Dollar, i);
  }
};

UniRangeContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterUniRange(this);
  }
};

UniRangeContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitUniRange(this);
  }
};

UniRangeContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitUniRange(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.UniRangeContext = UniRangeContext;

F7Parser.prototype.uniRange = function () {
  var localctx = new UniRangeContext(this, this._ctx, this.state);
  this.enterRule(localctx, 12, F7Parser.RULE_uniRange);
  var _la = 0; // Token type
  try {
    this.enterOuterAlt(localctx, 1);
    this.state = 146;
    this._errHandler.sync(this);
    var la_ = this._interp.adaptivePredict(this._input, 14, this._ctx);
    if (la_ === 1) {
      this.state = 143;
      localctx.grid = this.gridName();
      this.state = 144;
      this.match(F7Parser.Bang);
    }
    this.state = 149;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 148;
      localctx.absoFirstColumn = this.match(F7Parser.Dollar);
    }

    this.state = 151;
    localctx.firstColumn = this.match(F7Parser.NCharacters);
    this.state = 153;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 152;
      localctx.absoFirstRow = this.match(F7Parser.Dollar);
    }

    this.state = 155;
    localctx.firstRow = this.match(F7Parser.Int);
  } catch (re) {
    if (re instanceof antlr4.error.RecognitionException) {
      localctx.exception = re;
      this._errHandler.reportError(this, re);
      this._errHandler.recover(this, re);
    } else {
      throw re;
    }
  } finally {
    this.exitRule();
  }
  return localctx;
};

function ColumnWiseBiRangeContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_columnWiseBiRange;
  this.grid = null; // GridNameContext
  this.absoFirstColumn = null; // Token
  this.firstColumn = null; // Token
  this.absoLastColumn = null; // Token
  this.lastColumn = null; // Token
  return this;
}

ColumnWiseBiRangeContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ColumnWiseBiRangeContext.prototype.constructor = ColumnWiseBiRangeContext;

ColumnWiseBiRangeContext.prototype.Colon = function () {
  return this.getToken(F7Parser.Colon, 0);
};

ColumnWiseBiRangeContext.prototype.NCharacters = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.NCharacters);
  } else {
    return this.getToken(F7Parser.NCharacters, i);
  }
};

ColumnWiseBiRangeContext.prototype.Bang = function () {
  return this.getToken(F7Parser.Bang, 0);
};

ColumnWiseBiRangeContext.prototype.gridName = function () {
  return this.getTypedRuleContext(GridNameContext, 0);
};

ColumnWiseBiRangeContext.prototype.Dollar = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Dollar);
  } else {
    return this.getToken(F7Parser.Dollar, i);
  }
};

ColumnWiseBiRangeContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterColumnWiseBiRange(this);
  }
};

ColumnWiseBiRangeContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitColumnWiseBiRange(this);
  }
};

ColumnWiseBiRangeContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitColumnWiseBiRange(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.ColumnWiseBiRangeContext = ColumnWiseBiRangeContext;

F7Parser.prototype.columnWiseBiRange = function () {
  var localctx = new ColumnWiseBiRangeContext(this, this._ctx, this.state);
  this.enterRule(localctx, 14, F7Parser.RULE_columnWiseBiRange);
  var _la = 0; // Token type
  try {
    this.enterOuterAlt(localctx, 1);
    this.state = 160;
    this._errHandler.sync(this);
    var la_ = this._interp.adaptivePredict(this._input, 17, this._ctx);
    if (la_ === 1) {
      this.state = 157;
      localctx.grid = this.gridName();
      this.state = 158;
      this.match(F7Parser.Bang);
    }
    this.state = 163;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 162;
      localctx.absoFirstColumn = this.match(F7Parser.Dollar);
    }

    this.state = 165;
    localctx.firstColumn = this.match(F7Parser.NCharacters);
    this.state = 166;
    this.match(F7Parser.Colon);
    this.state = 168;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 167;
      localctx.absoLastColumn = this.match(F7Parser.Dollar);
    }

    this.state = 170;
    localctx.lastColumn = this.match(F7Parser.NCharacters);
  } catch (re) {
    if (re instanceof antlr4.error.RecognitionException) {
      localctx.exception = re;
      this._errHandler.reportError(this, re);
      this._errHandler.recover(this, re);
    } else {
      throw re;
    }
  } finally {
    this.exitRule();
  }
  return localctx;
};

function ColumnWiseWithRowOffsetFirstBiRangeContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_columnWiseWithRowOffsetFirstBiRange;
  this.grid = null; // GridNameContext
  this.absoFirstColumn = null; // Token
  this.firstColumn = null; // Token
  this.absoFirstRow = null; // Token
  this.firstRow = null; // Token
  this.absoLastColumn = null; // Token
  this.lastColumn = null; // Token
  return this;
}

ColumnWiseWithRowOffsetFirstBiRangeContext.prototype = Object.create(
  antlr4.ParserRuleContext.prototype
);
ColumnWiseWithRowOffsetFirstBiRangeContext.prototype.constructor =
  ColumnWiseWithRowOffsetFirstBiRangeContext;

ColumnWiseWithRowOffsetFirstBiRangeContext.prototype.Colon = function () {
  return this.getToken(F7Parser.Colon, 0);
};

ColumnWiseWithRowOffsetFirstBiRangeContext.prototype.NCharacters = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.NCharacters);
  } else {
    return this.getToken(F7Parser.NCharacters, i);
  }
};

ColumnWiseWithRowOffsetFirstBiRangeContext.prototype.Int = function () {
  return this.getToken(F7Parser.Int, 0);
};

ColumnWiseWithRowOffsetFirstBiRangeContext.prototype.Bang = function () {
  return this.getToken(F7Parser.Bang, 0);
};

ColumnWiseWithRowOffsetFirstBiRangeContext.prototype.gridName = function () {
  return this.getTypedRuleContext(GridNameContext, 0);
};

ColumnWiseWithRowOffsetFirstBiRangeContext.prototype.Dollar = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Dollar);
  } else {
    return this.getToken(F7Parser.Dollar, i);
  }
};

ColumnWiseWithRowOffsetFirstBiRangeContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterColumnWiseWithRowOffsetFirstBiRange(this);
  }
};

ColumnWiseWithRowOffsetFirstBiRangeContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitColumnWiseWithRowOffsetFirstBiRange(this);
  }
};

ColumnWiseWithRowOffsetFirstBiRangeContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitColumnWiseWithRowOffsetFirstBiRange(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.ColumnWiseWithRowOffsetFirstBiRangeContext = ColumnWiseWithRowOffsetFirstBiRangeContext;

F7Parser.prototype.columnWiseWithRowOffsetFirstBiRange = function () {
  var localctx = new ColumnWiseWithRowOffsetFirstBiRangeContext(this, this._ctx, this.state);
  this.enterRule(localctx, 16, F7Parser.RULE_columnWiseWithRowOffsetFirstBiRange);
  var _la = 0; // Token type
  try {
    this.enterOuterAlt(localctx, 1);
    this.state = 175;
    this._errHandler.sync(this);
    var la_ = this._interp.adaptivePredict(this._input, 20, this._ctx);
    if (la_ === 1) {
      this.state = 172;
      localctx.grid = this.gridName();
      this.state = 173;
      this.match(F7Parser.Bang);
    }
    this.state = 178;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 177;
      localctx.absoFirstColumn = this.match(F7Parser.Dollar);
    }

    this.state = 180;
    localctx.firstColumn = this.match(F7Parser.NCharacters);
    this.state = 182;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 181;
      localctx.absoFirstRow = this.match(F7Parser.Dollar);
    }

    this.state = 184;
    localctx.firstRow = this.match(F7Parser.Int);
    this.state = 185;
    this.match(F7Parser.Colon);
    this.state = 187;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 186;
      localctx.absoLastColumn = this.match(F7Parser.Dollar);
    }

    this.state = 189;
    localctx.lastColumn = this.match(F7Parser.NCharacters);
  } catch (re) {
    if (re instanceof antlr4.error.RecognitionException) {
      localctx.exception = re;
      this._errHandler.reportError(this, re);
      this._errHandler.recover(this, re);
    } else {
      throw re;
    }
  } finally {
    this.exitRule();
  }
  return localctx;
};

function ColumnWiseWithRowOffsetLastBiRangeContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_columnWiseWithRowOffsetLastBiRange;
  this.grid = null; // GridNameContext
  this.absoFirstColumn = null; // Token
  this.firstColumn = null; // Token
  this.absoLastColumn = null; // Token
  this.lastColumn = null; // Token
  this.absoLastRow = null; // Token
  this.lastRow = null; // Token
  return this;
}

ColumnWiseWithRowOffsetLastBiRangeContext.prototype = Object.create(
  antlr4.ParserRuleContext.prototype
);
ColumnWiseWithRowOffsetLastBiRangeContext.prototype.constructor =
  ColumnWiseWithRowOffsetLastBiRangeContext;

ColumnWiseWithRowOffsetLastBiRangeContext.prototype.Colon = function () {
  return this.getToken(F7Parser.Colon, 0);
};

ColumnWiseWithRowOffsetLastBiRangeContext.prototype.NCharacters = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.NCharacters);
  } else {
    return this.getToken(F7Parser.NCharacters, i);
  }
};

ColumnWiseWithRowOffsetLastBiRangeContext.prototype.Int = function () {
  return this.getToken(F7Parser.Int, 0);
};

ColumnWiseWithRowOffsetLastBiRangeContext.prototype.Bang = function () {
  return this.getToken(F7Parser.Bang, 0);
};

ColumnWiseWithRowOffsetLastBiRangeContext.prototype.gridName = function () {
  return this.getTypedRuleContext(GridNameContext, 0);
};

ColumnWiseWithRowOffsetLastBiRangeContext.prototype.Dollar = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Dollar);
  } else {
    return this.getToken(F7Parser.Dollar, i);
  }
};

ColumnWiseWithRowOffsetLastBiRangeContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterColumnWiseWithRowOffsetLastBiRange(this);
  }
};

ColumnWiseWithRowOffsetLastBiRangeContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitColumnWiseWithRowOffsetLastBiRange(this);
  }
};

ColumnWiseWithRowOffsetLastBiRangeContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitColumnWiseWithRowOffsetLastBiRange(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.ColumnWiseWithRowOffsetLastBiRangeContext = ColumnWiseWithRowOffsetLastBiRangeContext;

F7Parser.prototype.columnWiseWithRowOffsetLastBiRange = function () {
  var localctx = new ColumnWiseWithRowOffsetLastBiRangeContext(this, this._ctx, this.state);
  this.enterRule(localctx, 18, F7Parser.RULE_columnWiseWithRowOffsetLastBiRange);
  var _la = 0; // Token type
  try {
    this.enterOuterAlt(localctx, 1);
    this.state = 194;
    this._errHandler.sync(this);
    var la_ = this._interp.adaptivePredict(this._input, 24, this._ctx);
    if (la_ === 1) {
      this.state = 191;
      localctx.grid = this.gridName();
      this.state = 192;
      this.match(F7Parser.Bang);
    }
    this.state = 197;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 196;
      localctx.absoFirstColumn = this.match(F7Parser.Dollar);
    }

    this.state = 199;
    localctx.firstColumn = this.match(F7Parser.NCharacters);
    this.state = 200;
    this.match(F7Parser.Colon);
    this.state = 202;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 201;
      localctx.absoLastColumn = this.match(F7Parser.Dollar);
    }

    this.state = 204;
    localctx.lastColumn = this.match(F7Parser.NCharacters);
    this.state = 206;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 205;
      localctx.absoLastRow = this.match(F7Parser.Dollar);
    }

    this.state = 208;
    localctx.lastRow = this.match(F7Parser.Int);
  } catch (re) {
    if (re instanceof antlr4.error.RecognitionException) {
      localctx.exception = re;
      this._errHandler.reportError(this, re);
      this._errHandler.recover(this, re);
    } else {
      throw re;
    }
  } finally {
    this.exitRule();
  }
  return localctx;
};

function RowWiseBiRangeContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_rowWiseBiRange;
  this.grid = null; // GridNameContext
  this.absoFirstRow = null; // Token
  this.firstRow = null; // Token
  this.absoLastRow = null; // Token
  this.lastRow = null; // Token
  return this;
}

RowWiseBiRangeContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
RowWiseBiRangeContext.prototype.constructor = RowWiseBiRangeContext;

RowWiseBiRangeContext.prototype.Colon = function () {
  return this.getToken(F7Parser.Colon, 0);
};

RowWiseBiRangeContext.prototype.Int = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Int);
  } else {
    return this.getToken(F7Parser.Int, i);
  }
};

RowWiseBiRangeContext.prototype.Bang = function () {
  return this.getToken(F7Parser.Bang, 0);
};

RowWiseBiRangeContext.prototype.gridName = function () {
  return this.getTypedRuleContext(GridNameContext, 0);
};

RowWiseBiRangeContext.prototype.Dollar = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Dollar);
  } else {
    return this.getToken(F7Parser.Dollar, i);
  }
};

RowWiseBiRangeContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterRowWiseBiRange(this);
  }
};

RowWiseBiRangeContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitRowWiseBiRange(this);
  }
};

RowWiseBiRangeContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitRowWiseBiRange(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.RowWiseBiRangeContext = RowWiseBiRangeContext;

F7Parser.prototype.rowWiseBiRange = function () {
  var localctx = new RowWiseBiRangeContext(this, this._ctx, this.state);
  this.enterRule(localctx, 20, F7Parser.RULE_rowWiseBiRange);
  var _la = 0; // Token type
  try {
    this.enterOuterAlt(localctx, 1);
    this.state = 213;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.NCharacters || _la === F7Parser.SingleQuoteString) {
      this.state = 210;
      localctx.grid = this.gridName();
      this.state = 211;
      this.match(F7Parser.Bang);
    }

    this.state = 216;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 215;
      localctx.absoFirstRow = this.match(F7Parser.Dollar);
    }

    this.state = 218;
    localctx.firstRow = this.match(F7Parser.Int);
    this.state = 219;
    this.match(F7Parser.Colon);
    this.state = 221;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 220;
      localctx.absoLastRow = this.match(F7Parser.Dollar);
    }

    this.state = 223;
    localctx.lastRow = this.match(F7Parser.Int);
  } catch (re) {
    if (re instanceof antlr4.error.RecognitionException) {
      localctx.exception = re;
      this._errHandler.reportError(this, re);
      this._errHandler.recover(this, re);
    } else {
      throw re;
    }
  } finally {
    this.exitRule();
  }
  return localctx;
};

function RowWiseWithColumnOffsetFirstBiRangeContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_rowWiseWithColumnOffsetFirstBiRange;
  this.grid = null; // GridNameContext
  this.absoFirstColumn = null; // Token
  this.firstColumn = null; // Token
  this.absoFirstRow = null; // Token
  this.firstRow = null; // Token
  this.absoLastRow = null; // Token
  this.lastRow = null; // Token
  return this;
}

RowWiseWithColumnOffsetFirstBiRangeContext.prototype = Object.create(
  antlr4.ParserRuleContext.prototype
);
RowWiseWithColumnOffsetFirstBiRangeContext.prototype.constructor =
  RowWiseWithColumnOffsetFirstBiRangeContext;

RowWiseWithColumnOffsetFirstBiRangeContext.prototype.Colon = function () {
  return this.getToken(F7Parser.Colon, 0);
};

RowWiseWithColumnOffsetFirstBiRangeContext.prototype.NCharacters = function () {
  return this.getToken(F7Parser.NCharacters, 0);
};

RowWiseWithColumnOffsetFirstBiRangeContext.prototype.Int = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Int);
  } else {
    return this.getToken(F7Parser.Int, i);
  }
};

RowWiseWithColumnOffsetFirstBiRangeContext.prototype.Bang = function () {
  return this.getToken(F7Parser.Bang, 0);
};

RowWiseWithColumnOffsetFirstBiRangeContext.prototype.gridName = function () {
  return this.getTypedRuleContext(GridNameContext, 0);
};

RowWiseWithColumnOffsetFirstBiRangeContext.prototype.Dollar = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Dollar);
  } else {
    return this.getToken(F7Parser.Dollar, i);
  }
};

RowWiseWithColumnOffsetFirstBiRangeContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterRowWiseWithColumnOffsetFirstBiRange(this);
  }
};

RowWiseWithColumnOffsetFirstBiRangeContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitRowWiseWithColumnOffsetFirstBiRange(this);
  }
};

RowWiseWithColumnOffsetFirstBiRangeContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitRowWiseWithColumnOffsetFirstBiRange(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.RowWiseWithColumnOffsetFirstBiRangeContext = RowWiseWithColumnOffsetFirstBiRangeContext;

F7Parser.prototype.rowWiseWithColumnOffsetFirstBiRange = function () {
  var localctx = new RowWiseWithColumnOffsetFirstBiRangeContext(this, this._ctx, this.state);
  this.enterRule(localctx, 22, F7Parser.RULE_rowWiseWithColumnOffsetFirstBiRange);
  var _la = 0; // Token type
  try {
    this.enterOuterAlt(localctx, 1);
    this.state = 228;
    this._errHandler.sync(this);
    var la_ = this._interp.adaptivePredict(this._input, 31, this._ctx);
    if (la_ === 1) {
      this.state = 225;
      localctx.grid = this.gridName();
      this.state = 226;
      this.match(F7Parser.Bang);
    }
    this.state = 231;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 230;
      localctx.absoFirstColumn = this.match(F7Parser.Dollar);
    }

    this.state = 233;
    localctx.firstColumn = this.match(F7Parser.NCharacters);
    this.state = 235;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 234;
      localctx.absoFirstRow = this.match(F7Parser.Dollar);
    }

    this.state = 237;
    localctx.firstRow = this.match(F7Parser.Int);
    this.state = 238;
    this.match(F7Parser.Colon);
    this.state = 240;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 239;
      localctx.absoLastRow = this.match(F7Parser.Dollar);
    }

    this.state = 242;
    localctx.lastRow = this.match(F7Parser.Int);
  } catch (re) {
    if (re instanceof antlr4.error.RecognitionException) {
      localctx.exception = re;
      this._errHandler.reportError(this, re);
      this._errHandler.recover(this, re);
    } else {
      throw re;
    }
  } finally {
    this.exitRule();
  }
  return localctx;
};

function RowWiseWithColumnOffsetLastBiRangeContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_rowWiseWithColumnOffsetLastBiRange;
  this.grid = null; // GridNameContext
  this.absoFirstRow = null; // Token
  this.firstRow = null; // Token
  this.absLastColumn = null; // Token
  this.lastColumn = null; // Token
  this.absoLastRow = null; // Token
  this.lastRow = null; // Token
  return this;
}

RowWiseWithColumnOffsetLastBiRangeContext.prototype = Object.create(
  antlr4.ParserRuleContext.prototype
);
RowWiseWithColumnOffsetLastBiRangeContext.prototype.constructor =
  RowWiseWithColumnOffsetLastBiRangeContext;

RowWiseWithColumnOffsetLastBiRangeContext.prototype.Colon = function () {
  return this.getToken(F7Parser.Colon, 0);
};

RowWiseWithColumnOffsetLastBiRangeContext.prototype.Int = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Int);
  } else {
    return this.getToken(F7Parser.Int, i);
  }
};

RowWiseWithColumnOffsetLastBiRangeContext.prototype.NCharacters = function () {
  return this.getToken(F7Parser.NCharacters, 0);
};

RowWiseWithColumnOffsetLastBiRangeContext.prototype.Bang = function () {
  return this.getToken(F7Parser.Bang, 0);
};

RowWiseWithColumnOffsetLastBiRangeContext.prototype.gridName = function () {
  return this.getTypedRuleContext(GridNameContext, 0);
};

RowWiseWithColumnOffsetLastBiRangeContext.prototype.Dollar = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Dollar);
  } else {
    return this.getToken(F7Parser.Dollar, i);
  }
};

RowWiseWithColumnOffsetLastBiRangeContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterRowWiseWithColumnOffsetLastBiRange(this);
  }
};

RowWiseWithColumnOffsetLastBiRangeContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitRowWiseWithColumnOffsetLastBiRange(this);
  }
};

RowWiseWithColumnOffsetLastBiRangeContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitRowWiseWithColumnOffsetLastBiRange(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.RowWiseWithColumnOffsetLastBiRangeContext = RowWiseWithColumnOffsetLastBiRangeContext;

F7Parser.prototype.rowWiseWithColumnOffsetLastBiRange = function () {
  var localctx = new RowWiseWithColumnOffsetLastBiRangeContext(this, this._ctx, this.state);
  this.enterRule(localctx, 24, F7Parser.RULE_rowWiseWithColumnOffsetLastBiRange);
  var _la = 0; // Token type
  try {
    this.enterOuterAlt(localctx, 1);
    this.state = 247;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.NCharacters || _la === F7Parser.SingleQuoteString) {
      this.state = 244;
      localctx.grid = this.gridName();
      this.state = 245;
      this.match(F7Parser.Bang);
    }

    this.state = 250;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 249;
      localctx.absoFirstRow = this.match(F7Parser.Dollar);
    }

    this.state = 252;
    localctx.firstRow = this.match(F7Parser.Int);
    this.state = 253;
    this.match(F7Parser.Colon);
    this.state = 255;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 254;
      localctx.absLastColumn = this.match(F7Parser.Dollar);
    }

    this.state = 257;
    localctx.lastColumn = this.match(F7Parser.NCharacters);
    this.state = 259;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (_la === F7Parser.Dollar) {
      this.state = 258;
      localctx.absoLastRow = this.match(F7Parser.Dollar);
    }

    this.state = 261;
    localctx.lastRow = this.match(F7Parser.Int);
  } catch (re) {
    if (re instanceof antlr4.error.RecognitionException) {
      localctx.exception = re;
      this._errHandler.reportError(this, re);
      this._errHandler.recover(this, re);
    } else {
      throw re;
    }
  } finally {
    this.exitRule();
  }
  return localctx;
};

function ArgumentsContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_arguments;
  return this;
}

ArgumentsContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ArgumentsContext.prototype.constructor = ArgumentsContext;

ArgumentsContext.prototype.expression = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTypedRuleContexts(ExpressionContext);
  } else {
    return this.getTypedRuleContext(ExpressionContext, i);
  }
};

ArgumentsContext.prototype.Comma = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Comma);
  } else {
    return this.getToken(F7Parser.Comma, i);
  }
};

ArgumentsContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterArguments(this);
  }
};

ArgumentsContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitArguments(this);
  }
};

ArgumentsContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitArguments(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.ArgumentsContext = ArgumentsContext;

F7Parser.prototype.arguments = function () {
  var localctx = new ArgumentsContext(this, this._ctx, this.state);
  this.enterRule(localctx, 26, F7Parser.RULE_arguments);
  var _la = 0; // Token type
  try {
    this.enterOuterAlt(localctx, 1);
    this.state = 271;
    this._errHandler.sync(this);
    _la = this._input.LA(1);
    if (
      (_la & ~0x1f) == 0 &&
      ((1 << _la) &
        ((1 << F7Parser.NCharacters) |
          (1 << F7Parser.Int) |
          (1 << F7Parser.Number) |
          (1 << F7Parser.SingleQuoteString) |
          (1 << F7Parser.String) |
          (1 << F7Parser.Error) |
          (1 << F7Parser.LeftParen) |
          (1 << F7Parser.LeftBrace) |
          (1 << F7Parser.Plus) |
          (1 << F7Parser.Minus) |
          (1 << F7Parser.Dollar))) !==
        0
    ) {
      this.state = 263;
      this.expression(0);
      this.state = 268;
      this._errHandler.sync(this);
      _la = this._input.LA(1);
      while (_la === F7Parser.Comma) {
        this.state = 264;
        this.match(F7Parser.Comma);
        this.state = 265;
        this.expression(0);
        this.state = 270;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
      }
    }
  } catch (re) {
    if (re instanceof antlr4.error.RecognitionException) {
      localctx.exception = re;
      this._errHandler.reportError(this, re);
      this._errHandler.recover(this, re);
    } else {
      throw re;
    }
  } finally {
    this.exitRule();
  }
  return localctx;
};

function GridNameContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_gridName;
  return this;
}

GridNameContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
GridNameContext.prototype.constructor = GridNameContext;

GridNameContext.prototype.SingleQuoteString = function () {
  return this.getToken(F7Parser.SingleQuoteString, 0);
};

GridNameContext.prototype.identifier = function () {
  return this.getTypedRuleContext(IdentifierContext, 0);
};

GridNameContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterGridName(this);
  }
};

GridNameContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitGridName(this);
  }
};

GridNameContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitGridName(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.GridNameContext = GridNameContext;

F7Parser.prototype.gridName = function () {
  var localctx = new GridNameContext(this, this._ctx, this.state);
  this.enterRule(localctx, 28, F7Parser.RULE_gridName);
  try {
    this.state = 275;
    this._errHandler.sync(this);
    switch (this._input.LA(1)) {
      case F7Parser.SingleQuoteString:
        this.enterOuterAlt(localctx, 1);
        this.state = 273;
        this.match(F7Parser.SingleQuoteString);
        break;
      case F7Parser.NCharacters:
        this.enterOuterAlt(localctx, 2);
        this.state = 274;
        this.identifier();
        break;
      default:
        throw new antlr4.error.NoViableAltException(this);
    }
  } catch (re) {
    if (re instanceof antlr4.error.RecognitionException) {
      localctx.exception = re;
      this._errHandler.reportError(this, re);
      this._errHandler.recover(this, re);
    } else {
      throw re;
    }
  } finally {
    this.exitRule();
  }
  return localctx;
};

function IdentifierContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_identifier;
  return this;
}

IdentifierContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
IdentifierContext.prototype.constructor = IdentifierContext;

IdentifierContext.prototype.NCharacters = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.NCharacters);
  } else {
    return this.getToken(F7Parser.NCharacters, i);
  }
};

IdentifierContext.prototype.Dot = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Dot);
  } else {
    return this.getToken(F7Parser.Dot, i);
  }
};

IdentifierContext.prototype.Underscore = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Underscore);
  } else {
    return this.getToken(F7Parser.Underscore, i);
  }
};

IdentifierContext.prototype.Int = function (i) {
  if (i === undefined) {
    i = null;
  }
  if (i === null) {
    return this.getTokens(F7Parser.Int);
  } else {
    return this.getToken(F7Parser.Int, i);
  }
};

IdentifierContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterIdentifier(this);
  }
};

IdentifierContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitIdentifier(this);
  }
};

IdentifierContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitIdentifier(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.IdentifierContext = IdentifierContext;

F7Parser.prototype.identifier = function () {
  var localctx = new IdentifierContext(this, this._ctx, this.state);
  this.enterRule(localctx, 30, F7Parser.RULE_identifier);
  var _la = 0; // Token type
  try {
    this.enterOuterAlt(localctx, 1);
    this.state = 277;
    this.match(F7Parser.NCharacters);
    this.state = 281;
    this._errHandler.sync(this);
    var _alt = this._interp.adaptivePredict(this._input, 42, this._ctx);
    while (_alt != 2 && _alt != antlr4.atn.ATN.INVALID_ALT_NUMBER) {
      if (_alt === 1) {
        this.state = 278;
        _la = this._input.LA(1);
        if (
          !(
            (_la & ~0x1f) == 0 &&
            ((1 << _la) &
              ((1 << F7Parser.NCharacters) |
                (1 << F7Parser.Dot) |
                (1 << F7Parser.Int) |
                (1 << F7Parser.Underscore))) !==
              0
          )
        ) {
          this._errHandler.recoverInline(this);
        } else {
          this._errHandler.reportMatch(this);
          this.consume();
        }
      }
      this.state = 283;
      this._errHandler.sync(this);
      _alt = this._interp.adaptivePredict(this._input, 42, this._ctx);
    }
  } catch (re) {
    if (re instanceof antlr4.error.RecognitionException) {
      localctx.exception = re;
      this._errHandler.reportError(this, re);
      this._errHandler.recover(this, re);
    } else {
      throw re;
    }
  } finally {
    this.exitRule();
  }
  return localctx;
};

function ComparisonOperatorContext(parser, parent, invokingState) {
  if (parent === undefined) {
    parent = null;
  }
  if (invokingState === undefined || invokingState === null) {
    invokingState = -1;
  }
  antlr4.ParserRuleContext.call(this, parent, invokingState);
  this.parser = parser;
  this.ruleIndex = F7Parser.RULE_comparisonOperator;
  return this;
}

ComparisonOperatorContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ComparisonOperatorContext.prototype.constructor = ComparisonOperatorContext;

ComparisonOperatorContext.prototype.LessThanOrEqualTO = function () {
  return this.getToken(F7Parser.LessThanOrEqualTO, 0);
};

ComparisonOperatorContext.prototype.GreaterThanOrEqualTo = function () {
  return this.getToken(F7Parser.GreaterThanOrEqualTo, 0);
};

ComparisonOperatorContext.prototype.LessThan = function () {
  return this.getToken(F7Parser.LessThan, 0);
};

ComparisonOperatorContext.prototype.GreaterThan = function () {
  return this.getToken(F7Parser.GreaterThan, 0);
};

ComparisonOperatorContext.prototype.Equal = function () {
  return this.getToken(F7Parser.Equal, 0);
};

ComparisonOperatorContext.prototype.NotEqual = function () {
  return this.getToken(F7Parser.NotEqual, 0);
};

ComparisonOperatorContext.prototype.enterRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.enterComparisonOperator(this);
  }
};

ComparisonOperatorContext.prototype.exitRule = function (listener) {
  if (listener instanceof F7Listener) {
    listener.exitComparisonOperator(this);
  }
};

ComparisonOperatorContext.prototype.accept = function (visitor) {
  if (visitor instanceof F7Visitor) {
    return visitor.visitComparisonOperator(this);
  } else {
    return visitor.visitChildren(this);
  }
};

F7Parser.ComparisonOperatorContext = ComparisonOperatorContext;

F7Parser.prototype.comparisonOperator = function () {
  var localctx = new ComparisonOperatorContext(this, this._ctx, this.state);
  this.enterRule(localctx, 32, F7Parser.RULE_comparisonOperator);
  var _la = 0; // Token type
  try {
    this.enterOuterAlt(localctx, 1);
    this.state = 284;
    _la = this._input.LA(1);
    if (
      !(
        (_la & ~0x1f) == 0 &&
        ((1 << _la) &
          ((1 << F7Parser.GreaterThan) |
            (1 << F7Parser.GreaterThanOrEqualTo) |
            (1 << F7Parser.LessThan) |
            (1 << F7Parser.LessThanOrEqualTO) |
            (1 << F7Parser.Equal) |
            (1 << F7Parser.NotEqual))) !==
          0
      )
    ) {
      this._errHandler.recoverInline(this);
    } else {
      this._errHandler.reportMatch(this);
      this.consume();
    }
  } catch (re) {
    if (re instanceof antlr4.error.RecognitionException) {
      localctx.exception = re;
      this._errHandler.reportError(this, re);
      this._errHandler.recover(this, re);
    } else {
      throw re;
    }
  } finally {
    this.exitRule();
  }
  return localctx;
};

F7Parser.prototype.sempred = function (localctx, ruleIndex, predIndex) {
  switch (ruleIndex) {
    case 2:
      return this.expression_sempred(localctx, predIndex);
    default:
      throw "No predicate with index:" + ruleIndex;
  }
};

F7Parser.prototype.expression_sempred = function (localctx, predIndex) {
  switch (predIndex) {
    case 0:
      return this.precpred(this._ctx, 7);
    case 1:
      return this.precpred(this._ctx, 6);
    case 2:
      return this.precpred(this._ctx, 5);
    case 3:
      return this.precpred(this._ctx, 4);
    case 4:
      return this.precpred(this._ctx, 3);
    case 5:
      return this.precpred(this._ctx, 8);
    default:
      throw "No predicate with index:" + predIndex;
  }
};

exports.F7Parser = F7Parser;
