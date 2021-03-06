// Generated from F7.g4 by ANTLR 4.7.2
package io.protobase.f7.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class F7Lexer extends Lexer {
  public static final int
      NCharacters = 1, Dot = 2, Int = 3, Number = 4, String = 5, Error = 6, LeftParen = 7,
      RightParen = 8, LeftBrace = 9, RightBrace = 10, Comma = 11, Colon = 12, SemiColon = 13,
      Bang = 14, Plus = 15, Minus = 16, Percent = 17, Power = 18, Divide = 19, Multiply = 20,
      GreaterThan = 21, GreaterThanOrEqualTo = 22, LessThan = 23, LessThanOrEqualTO = 24,
      Equal = 25, NotEqual = 26, Ampersand = 27, Dollar = 28, Underscore = 29, WS = 30;
  public static final String[] ruleNames = makeRuleNames();
  /**
   * @deprecated Use {@link #VOCABULARY} instead.
   */
  @Deprecated
  public static final String[] tokenNames;
  public static final String _serializedATN =
      "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2 \u01d3\b\1\4\2\t" +
          "\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
          "\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
          "\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
          "\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
          "\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4" +
          ",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t" +
          "\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t=" +
          "\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I" +
          "\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT" +
          "\4U\tU\4V\tV\4W\tW\4X\tX\3\2\6\2\u00b3\n\2\r\2\16\2\u00b4\3\3\3\3\3\4" +
          "\6\4\u00ba\n\4\r\4\16\4\u00bb\3\5\6\5\u00bf\n\5\r\5\16\5\u00c0\3\5\3\5" +
          "\6\5\u00c5\n\5\r\5\16\5\u00c6\5\5\u00c9\n\5\3\5\3\5\5\5\u00cd\n\5\3\5" +
          "\6\5\u00d0\n\5\r\5\16\5\u00d1\3\5\3\5\6\5\u00d6\n\5\r\5\16\5\u00d7\5\5" +
          "\u00da\n\5\5\5\u00dc\n\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13" +
          "\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22" +
          "\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31" +
          "\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!" +
          "\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3" +
          ",\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3" +
          "\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\69\u0145\n9\r9\169\u0146\3" +
          "9\39\79\u014b\n9\f9\169\u014e\139\3:\3:\3;\3;\3<\3<\3<\3<\3<\3<\3<\3<" +
          "\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<" +
          "\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<" +
          "\3<\5<\u018b\n<\3=\3=\7=\u018f\n=\f=\16=\u0192\13=\3=\3=\3>\3>\3?\3?\3" +
          "@\3@\3@\3A\3A\3B\3B\3C\3C\3C\3D\3D\3D\3E\3E\3F\3F\3G\3G\3H\3H\3I\3I\3" +
          "J\3J\3K\3K\3L\3L\3M\3M\3N\3N\3O\3O\3P\3P\3Q\3Q\3R\3R\3S\3S\3T\3T\3U\3" +
          "U\3V\3V\3W\3W\3X\6X\u01ce\nX\rX\16X\u01cf\3X\3X\2\2Y\3\3\5\4\7\5\t\6\13" +
          "\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'" +
          "\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37=\2?\2A\2C\2E\2G\2" +
          "I\2K\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\2_\2a\2c\2e\2g\2i\2k\2m\2o\2q\2s\2u\2" +
          "w\2y\2{\2}\2\177\2\u0081\2\u0083\2\u0085\2\u0087\2\u0089\2\u008b\2\u008d" +
          "\2\u008f\2\u0091\2\u0093\2\u0095\2\u0097\2\u0099\2\u009b\2\u009d\2\u009f" +
          "\2\u00a1\2\u00a3\2\u00a5\2\u00a7\2\u00a9\2\u00ab\2\u00ad\2\u00af \3\2" +
          " \4\2--//\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2" +
          "JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4" +
          "\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{" +
          "{\4\2\\\\||\4\2C\\c|\6\2\f\f\17\17$$^^\5\2\13\f\17\17\"\"\2\u01af\2\3" +
          "\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2" +
          "\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31" +
          "\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2" +
          "\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2" +
          "\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2" +
          "\2\2\u00af\3\2\2\2\3\u00b2\3\2\2\2\5\u00b6\3\2\2\2\7\u00b9\3\2\2\2\t\u00be" +
          "\3\2\2\2\13\u00dd\3\2\2\2\r\u00df\3\2\2\2\17\u00e1\3\2\2\2\21\u00e3\3" +
          "\2\2\2\23\u00e5\3\2\2\2\25\u00e7\3\2\2\2\27\u00e9\3\2\2\2\31\u00eb\3\2" +
          "\2\2\33\u00ed\3\2\2\2\35\u00ef\3\2\2\2\37\u00f1\3\2\2\2!\u00f3\3\2\2\2" +
          "#\u00f5\3\2\2\2%\u00f7\3\2\2\2\'\u00f9\3\2\2\2)\u00fb\3\2\2\2+\u00fd\3" +
          "\2\2\2-\u00ff\3\2\2\2/\u0101\3\2\2\2\61\u0103\3\2\2\2\63\u0105\3\2\2\2" +
          "\65\u0107\3\2\2\2\67\u0109\3\2\2\29\u010b\3\2\2\2;\u010d\3\2\2\2=\u010f" +
          "\3\2\2\2?\u0111\3\2\2\2A\u0113\3\2\2\2C\u0115\3\2\2\2E\u0117\3\2\2\2G" +
          "\u0119\3\2\2\2I\u011b\3\2\2\2K\u011d\3\2\2\2M\u011f\3\2\2\2O\u0121\3\2" +
          "\2\2Q\u0123\3\2\2\2S\u0125\3\2\2\2U\u0127\3\2\2\2W\u0129\3\2\2\2Y\u012b" +
          "\3\2\2\2[\u012d\3\2\2\2]\u012f\3\2\2\2_\u0131\3\2\2\2a\u0133\3\2\2\2c" +
          "\u0135\3\2\2\2e\u0137\3\2\2\2g\u0139\3\2\2\2i\u013b\3\2\2\2k\u013d\3\2" +
          "\2\2m\u013f\3\2\2\2o\u0141\3\2\2\2q\u0144\3\2\2\2s\u014f\3\2\2\2u\u0151" +
          "\3\2\2\2w\u018a\3\2\2\2y\u018c\3\2\2\2{\u0195\3\2\2\2}\u0197\3\2\2\2\177" +
          "\u0199\3\2\2\2\u0081\u019c\3\2\2\2\u0083\u019e\3\2\2\2\u0085\u01a0\3\2" +
          "\2\2\u0087\u01a3\3\2\2\2\u0089\u01a6\3\2\2\2\u008b\u01a8\3\2\2\2\u008d" +
          "\u01aa\3\2\2\2\u008f\u01ac\3\2\2\2\u0091\u01ae\3\2\2\2\u0093\u01b0\3\2" +
          "\2\2\u0095\u01b2\3\2\2\2\u0097\u01b4\3\2\2\2\u0099\u01b6\3\2\2\2\u009b" +
          "\u01b8\3\2\2\2\u009d\u01ba\3\2\2\2\u009f\u01bc\3\2\2\2\u00a1\u01be\3\2" +
          "\2\2\u00a3\u01c0\3\2\2\2\u00a5\u01c2\3\2\2\2\u00a7\u01c4\3\2\2\2\u00a9" +
          "\u01c6\3\2\2\2\u00ab\u01c8\3\2\2\2\u00ad\u01ca\3\2\2\2\u00af\u01cd\3\2" +
          "\2\2\u00b1\u00b3\5u;\2\u00b2\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b2" +
          "\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\4\3\2\2\2\u00b6\u00b7\7\60\2\2\u00b7" +
          "\6\3\2\2\2\u00b8\u00ba\5s:\2\u00b9\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb" +
          "\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\b\3\2\2\2\u00bd\u00bf\5s:\2\u00be" +
          "\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2" +
          "\2\2\u00c1\u00c8\3\2\2\2\u00c2\u00c4\5\5\3\2\u00c3\u00c5\5s:\2\u00c4\u00c3" +
          "\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7" +
          "\u00c9\3\2\2\2\u00c8\u00c2\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00db\3\2" +
          "\2\2\u00ca\u00cc\5E#\2\u00cb\u00cd\t\2\2\2\u00cc\u00cb\3\2\2\2\u00cc\u00cd" +
          "\3\2\2\2\u00cd\u00cf\3\2\2\2\u00ce\u00d0\5s:\2\u00cf\u00ce\3\2\2\2\u00d0" +
          "\u00d1\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d9\3\2" +
          "\2\2\u00d3\u00d5\5\5\3\2\u00d4\u00d6\5s:\2\u00d5\u00d4\3\2\2\2\u00d6\u00d7" +
          "\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00da\3\2\2\2\u00d9" +
          "\u00d3\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00dc\3\2\2\2\u00db\u00ca\3\2" +
          "\2\2\u00db\u00dc\3\2\2\2\u00dc\n\3\2\2\2\u00dd\u00de\5y=\2\u00de\f\3\2" +
          "\2\2\u00df\u00e0\5w<\2\u00e0\16\3\2\2\2\u00e1\u00e2\5\u0099M\2\u00e2\20" +
          "\3\2\2\2\u00e3\u00e4\5\u009bN\2\u00e4\22\3\2\2\2\u00e5\u00e6\5\u00a1Q" +
          "\2\u00e6\24\3\2\2\2\u00e7\u00e8\5\u00a3R\2\u00e8\26\3\2\2\2\u00e9\u00ea" +
          "\5\u009dO\2\u00ea\30\3\2\2\2\u00eb\u00ec\5\u00abV\2\u00ec\32\3\2\2\2\u00ed" +
          "\u00ee\5\u009fP\2\u00ee\34\3\2\2\2\u00ef\u00f0\5\u00a5S\2\u00f0\36\3\2" +
          "\2\2\u00f1\u00f2\5\u0089E\2\u00f2 \3\2\2\2\u00f3\u00f4\5\u008bF\2\u00f4" +
          "\"\3\2\2\2\u00f5\u00f6\5\u0091I\2\u00f6$\3\2\2\2\u00f7\u00f8\5\u0095K" +
          "\2\u00f8&\3\2\2\2\u00f9\u00fa\5\u008fH\2\u00fa(\3\2\2\2\u00fb\u00fc\5" +
          "\u008dG\2\u00fc*\3\2\2\2\u00fd\u00fe\5\u0081A\2\u00fe,\3\2\2\2\u00ff\u0100" +
          "\5\u0085C\2\u0100.\3\2\2\2\u0101\u0102\5\u0083B\2\u0102\60\3\2\2\2\u0103" +
          "\u0104\5\u0087D\2\u0104\62\3\2\2\2\u0105\u0106\5}?\2\u0106\64\3\2\2\2" +
          "\u0107\u0108\5\177@\2\u0108\66\3\2\2\2\u0109\u010a\5\u0097L\2\u010a8\3" +
          "\2\2\2\u010b\u010c\5\u00a9U\2\u010c:\3\2\2\2\u010d\u010e\5\u00adW\2\u010e" +
          "<\3\2\2\2\u010f\u0110\t\3\2\2\u0110>\3\2\2\2\u0111\u0112\t\4\2\2\u0112" +
          "@\3\2\2\2\u0113\u0114\t\5\2\2\u0114B\3\2\2\2\u0115\u0116\t\6\2\2\u0116" +
          "D\3\2\2\2\u0117\u0118\t\7\2\2\u0118F\3\2\2\2\u0119\u011a\t\b\2\2\u011a" +
          "H\3\2\2\2\u011b\u011c\t\t\2\2\u011cJ\3\2\2\2\u011d\u011e\t\n\2\2\u011e" +
          "L\3\2\2\2\u011f\u0120\t\13\2\2\u0120N\3\2\2\2\u0121\u0122\t\f\2\2\u0122" +
          "P\3\2\2\2\u0123\u0124\t\r\2\2\u0124R\3\2\2\2\u0125\u0126\t\16\2\2\u0126" +
          "T\3\2\2\2\u0127\u0128\t\17\2\2\u0128V\3\2\2\2\u0129\u012a\t\20\2\2\u012a" +
          "X\3\2\2\2\u012b\u012c\t\21\2\2\u012cZ\3\2\2\2\u012d\u012e\t\22\2\2\u012e" +
          "\\\3\2\2\2\u012f\u0130\t\23\2\2\u0130^\3\2\2\2\u0131\u0132\t\24\2\2\u0132" +
          "`\3\2\2\2\u0133\u0134\t\25\2\2\u0134b\3\2\2\2\u0135\u0136\t\26\2\2\u0136" +
          "d\3\2\2\2\u0137\u0138\t\27\2\2\u0138f\3\2\2\2\u0139\u013a\t\30\2\2\u013a" +
          "h\3\2\2\2\u013b\u013c\t\31\2\2\u013cj\3\2\2\2\u013d\u013e\t\32\2\2\u013e" +
          "l\3\2\2\2\u013f\u0140\t\33\2\2\u0140n\3\2\2\2\u0141\u0142\t\34\2\2\u0142" +
          "p\3\2\2\2\u0143\u0145\5u;\2\u0144\u0143\3\2\2\2\u0145\u0146\3\2\2\2\u0146" +
          "\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u014c\3\2\2\2\u0148\u014b\7a" +
          "\2\2\u0149\u014b\5u;\2\u014a\u0148\3\2\2\2\u014a\u0149\3\2\2\2\u014b\u014e" +
          "\3\2\2\2\u014c\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014dr\3\2\2\2\u014e" +
          "\u014c\3\2\2\2\u014f\u0150\4\62;\2\u0150t\3\2\2\2\u0151\u0152\t\35\2\2" +
          "\u0152v\3\2\2\2\u0153\u0154\5\u0093J\2\u0154\u0155\5W,\2\u0155\u0156\5" +
          "e\63\2\u0156\u0157\5S*\2\u0157\u0158\5S*\2\u0158\u0159\5\u00a5S\2\u0159" +
          "\u018b\3\2\2\2\u015a\u015b\5\u0093J\2\u015b\u015c\5C\"\2\u015c\u015d\5" +
          "M\'\2\u015d\u015e\5g\64\2\u015e\u015f\5\u008fH\2\u015f\u0160\7\62\2\2" +
          "\u0160\u0161\5\u00a5S\2\u0161\u018b\3\2\2\2\u0162\u0163\5\u0093J\2\u0163" +
          "\u0164\5g\64\2\u0164\u0165\5=\37\2\u0165\u0166\5S*\2\u0166\u0167\5e\63" +
          "\2\u0167\u0168\5E#\2\u0168\u0169\5\u00a5S\2\u0169\u018b\3\2\2\2\u016a" +
          "\u016b\5\u0093J\2\u016b\u016c\5_\60\2\u016c\u016d\5E#\2\u016d\u016e\5" +
          "G$\2\u016e\u016f\5\u00a5S\2\u016f\u018b\3\2\2\2\u0170\u0171\5\u0093J\2" +
          "\u0171\u0172\5W,\2\u0172\u0173\5=\37\2\u0173\u0174\5U+\2\u0174\u0175\5" +
          "E#\2\u0175\u0176\5\u00a7T\2\u0176\u018b\3\2\2\2\u0177\u0178\5\u0093J\2" +
          "\u0178\u0179\5W,\2\u0179\u017a\5e\63\2\u017a\u017b\5U+\2\u017b\u017c\5" +
          "\u00a5S\2\u017c\u018b\3\2\2\2\u017d\u017e\5\u0093J\2\u017e\u017f\5W,\2" +
          "\u017f\u0180\5\u008fH\2\u0180\u0181\5=\37\2\u0181\u018b\3\2\2\2\u0182" +
          "\u0183\5\u0093J\2\u0183\u0184\5E#\2\u0184\u0185\5_\60\2\u0185\u0186\5" +
          "_\60\2\u0186\u0187\5Y-\2\u0187\u0188\5_\60\2\u0188\u0189\5\u00a5S\2\u0189" +
          "\u018b\3\2\2\2\u018a\u0153\3\2\2\2\u018a\u015a\3\2\2\2\u018a\u0162\3\2" +
          "\2\2\u018a\u016a\3\2\2\2\u018a\u0170\3\2\2\2\u018a\u0177\3\2\2\2\u018a" +
          "\u017d\3\2\2\2\u018a\u0182\3\2\2\2\u018bx\3\2\2\2\u018c\u0190\7$\2\2\u018d" +
          "\u018f\5{>\2\u018e\u018d\3\2\2\2\u018f\u0192\3\2\2\2\u0190\u018e\3\2\2" +
          "\2\u0190\u0191\3\2\2\2\u0191\u0193\3\2\2\2\u0192\u0190\3\2\2\2\u0193\u0194" +
          "\7$\2\2\u0194z\3\2\2\2\u0195\u0196\n\36\2\2\u0196|\3\2\2\2\u0197\u0198" +
          "\7?\2\2\u0198~\3\2\2\2\u0199\u019a\7>\2\2\u019a\u019b\7@\2\2\u019b\u0080" +
          "\3\2\2\2\u019c\u019d\7@\2\2\u019d\u0082\3\2\2\2\u019e\u019f\7>\2\2\u019f" +
          "\u0084\3\2\2\2\u01a0\u01a1\7@\2\2\u01a1\u01a2\7?\2\2\u01a2\u0086\3\2\2" +
          "\2\u01a3\u01a4\7>\2\2\u01a4\u01a5\7?\2\2\u01a5\u0088\3\2\2\2\u01a6\u01a7" +
          "\7-\2\2\u01a7\u008a\3\2\2\2\u01a8\u01a9\7/\2\2\u01a9\u008c\3\2\2\2\u01aa" +
          "\u01ab\7,\2\2\u01ab\u008e\3\2\2\2\u01ac\u01ad\7\61\2\2\u01ad\u0090\3\2" +
          "\2\2\u01ae\u01af\7\'\2\2\u01af\u0092\3\2\2\2\u01b0\u01b1\7%\2\2\u01b1" +
          "\u0094\3\2\2\2\u01b2\u01b3\7`\2\2\u01b3\u0096\3\2\2\2\u01b4\u01b5\7(\2" +
          "\2\u01b5\u0098\3\2\2\2\u01b6\u01b7\7*\2\2\u01b7\u009a\3\2\2\2\u01b8\u01b9" +
          "\7+\2\2\u01b9\u009c\3\2\2\2\u01ba\u01bb\7.\2\2\u01bb\u009e\3\2\2\2\u01bc" +
          "\u01bd\7=\2\2\u01bd\u00a0\3\2\2\2\u01be\u01bf\7}\2\2\u01bf\u00a2\3\2\2" +
          "\2\u01c0\u01c1\7\177\2\2\u01c1\u00a4\3\2\2\2\u01c2\u01c3\7#\2\2\u01c3" +
          "\u00a6\3\2\2\2\u01c4\u01c5\7A\2\2\u01c5\u00a8\3\2\2\2\u01c6\u01c7\7&\2" +
          "\2\u01c7\u00aa\3\2\2\2\u01c8\u01c9\7<\2\2\u01c9\u00ac\3\2\2\2\u01ca\u01cb" +
          "\7a\2\2\u01cb\u00ae\3\2\2\2\u01cc\u01ce\t\37\2\2\u01cd\u01cc\3\2\2\2\u01ce" +
          "\u01cf\3\2\2\2\u01cf\u01cd\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0\u01d1\3\2" +
          "\2\2\u01d1\u01d2\bX\2\2\u01d2\u00b0\3\2\2\2\23\2\u00b4\u00bb\u00c0\u00c6" +
          "\u00c8\u00cc\u00d1\u00d7\u00d9\u00db\u0146\u014a\u014c\u018a\u0190\u01cf" +
          "\3\b\2\2";
  public static final ATN _ATN =
      new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
      new PredictionContextCache();
  private static final String[] _LITERAL_NAMES = makeLiteralNames();
  private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
  public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);
  public static String[] channelNames = {
      "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
  };
  public static String[] modeNames = {
      "DEFAULT_MODE"
  };

  static {
    RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION);
  }

  static {
    tokenNames = new String[_SYMBOLIC_NAMES.length];
    for (int i = 0; i < tokenNames.length; i++) {
      tokenNames[i] = VOCABULARY.getLiteralName(i);
      if (tokenNames[i] == null) {
        tokenNames[i] = VOCABULARY.getSymbolicName(i);
      }

      if (tokenNames[i] == null) {
        tokenNames[i] = "<INVALID>";
      }
    }
  }

  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }

  public F7Lexer(CharStream input) {
    super(input);
    _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
  }

  private static String[] makeRuleNames() {
    return new String[]{
        "NCharacters", "Dot", "Int", "Number", "String", "Error", "LeftParen",
        "RightParen", "LeftBrace", "RightBrace", "Comma", "Colon", "SemiColon",
        "Bang", "Plus", "Minus", "Percent", "Power", "Divide", "Multiply", "GreaterThan",
        "GreaterThanOrEqualTo", "LessThan", "LessThanOrEqualTO", "Equal", "NotEqual",
        "Ampersand", "Dollar", "Underscore", "A", "B", "C", "D", "E", "F", "G",
        "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
        "V", "W", "X", "Y", "Z", "IDENTIFIER", "DIGIT", "CHARACTER", "ERROR_LITERAL",
        "STRING_LITERAL", "DOUBLE_STRING_CHARACTER", "EQ", "NEQ", "GT", "LT",
        "GTEQ", "LTEQ", "PLUS", "MINUS", "MULT", "DIV", "PERCENT", "HASH", "POW",
        "AMPERSAND", "LPAREN", "RPAREN", "COMMA", "SEMICOLON", "LBRACE", "RBRACE",
        "BANG", "QUESTION", "DOLLAR", "COLON", "UNDERSCORE", "WS"
    };
  }

  private static String[] makeLiteralNames() {
    return new String[]{
        null, null, "'.'"
    };
  }

  private static String[] makeSymbolicNames() {
    return new String[]{
        null, "NCharacters", "Dot", "Int", "Number", "String", "Error", "LeftParen",
        "RightParen", "LeftBrace", "RightBrace", "Comma", "Colon", "SemiColon",
        "Bang", "Plus", "Minus", "Percent", "Power", "Divide", "Multiply", "GreaterThan",
        "GreaterThanOrEqualTo", "LessThan", "LessThanOrEqualTO", "Equal", "NotEqual",
        "Ampersand", "Dollar", "Underscore", "WS"
    };
  }

  @Override
  @Deprecated
  public String[] getTokenNames() {
    return tokenNames;
  }

  @Override

  public Vocabulary getVocabulary() {
    return VOCABULARY;
  }

  @Override
  public String getGrammarFileName() {
    return "F7.g4";
  }

  @Override
  public String[] getRuleNames() {
    return ruleNames;
  }

  @Override
  public String getSerializedATN() {
    return _serializedATN;
  }

  @Override
  public String[] getChannelNames() {
    return channelNames;
  }

  @Override
  public String[] getModeNames() {
    return modeNames;
  }

  @Override
  public ATN getATN() {
    return _ATN;
  }
}