// Generated from F7.g4 by ANTLR 4.7.2
package io.protobase.f7.antlr;

import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class F7Parser extends Parser {
  public static final int
      NCharacters = 1, Dot = 2, Int = 3, Number = 4, String = 5, Error = 6, LeftParen = 7,
      RightParen = 8, LeftBrace = 9, RightBrace = 10, Comma = 11, Colon = 12, SemiColon = 13,
      Bang = 14, Plus = 15, Minus = 16, Percent = 17, Power = 18, Divide = 19, Multiply = 20,
      GreaterThan = 21, GreaterThanOrEqualTo = 22, LessThan = 23, LessThanOrEqualTO = 24,
      Equal = 25, NotEqual = 26, Ampersand = 27, Dollar = 28, Underscore = 29, WS = 30;
  public static final int
      RULE_start = 0, RULE_block = 1, RULE_expression = 2, RULE_atom = 3, RULE_range = 4,
      RULE_arguments = 5, RULE_identifier = 6, RULE_comparisonOperator = 7;
  public static final String[] ruleNames = makeRuleNames();
  /**
   * @deprecated Use {@link #VOCABULARY} instead.
   */
  @Deprecated
  public static final String[] tokenNames;
  public static final String _serializedATN =
      "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u00fb\4\2\t\2\4" +
          "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\3" +
          "\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\6\4!\n\4\r\4\16\4\"\5\4%\n\4" +
          "\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3" +
          "\4\6\49\n\4\r\4\16\4:\7\4=\n\4\f\4\16\4@\13\4\3\5\3\5\3\5\3\5\3\5\3\5" +
          "\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5T\n\5\f\5\16\5W\13" +
          "\5\5\5Y\n\5\3\5\3\5\5\5]\n\5\3\6\3\6\3\6\5\6b\n\6\3\6\5\6e\n\6\3\6\3\6" +
          "\5\6i\n\6\3\6\3\6\3\6\5\6n\n\6\3\6\3\6\5\6r\n\6\3\6\3\6\3\6\3\6\5\6x\n" +
          "\6\3\6\5\6{\n\6\3\6\3\6\5\6\177\n\6\3\6\3\6\3\6\3\6\5\6\u0085\n\6\3\6" +
          "\5\6\u0088\n\6\3\6\3\6\3\6\5\6\u008d\n\6\3\6\3\6\3\6\3\6\5\6\u0093\n\6" +
          "\3\6\5\6\u0096\n\6\3\6\3\6\5\6\u009a\n\6\3\6\3\6\3\6\5\6\u009f\n\6\3\6" +
          "\3\6\3\6\3\6\5\6\u00a5\n\6\3\6\5\6\u00a8\n\6\3\6\3\6\3\6\5\6\u00ad\n\6" +
          "\3\6\3\6\5\6\u00b1\n\6\3\6\3\6\3\6\3\6\5\6\u00b7\n\6\3\6\5\6\u00ba\n\6" +
          "\3\6\3\6\3\6\5\6\u00bf\n\6\3\6\3\6\3\6\3\6\5\6\u00c5\n\6\3\6\5\6\u00c8" +
          "\n\6\3\6\3\6\5\6\u00cc\n\6\3\6\3\6\3\6\5\6\u00d1\n\6\3\6\3\6\3\6\3\6\5" +
          "\6\u00d7\n\6\3\6\5\6\u00da\n\6\3\6\3\6\3\6\5\6\u00df\n\6\3\6\3\6\5\6\u00e3" +
          "\n\6\3\6\5\6\u00e6\n\6\3\7\3\7\3\7\7\7\u00eb\n\7\f\7\16\7\u00ee\13\7\5" +
          "\7\u00f0\n\7\3\b\3\b\7\b\u00f4\n\b\f\b\16\b\u00f7\13\b\3\t\3\t\3\t\2\3" +
          "\6\n\2\4\6\b\n\f\16\20\2\7\3\2\25\26\3\2\21\22\4\2\r\r\17\17\4\2\3\5\37" +
          "\37\3\2\27\34\2\u012f\2\22\3\2\2\2\4\25\3\2\2\2\6$\3\2\2\2\b\\\3\2\2\2" +
          "\n\u00e5\3\2\2\2\f\u00ef\3\2\2\2\16\u00f1\3\2\2\2\20\u00f8\3\2\2\2\22" +
          "\23\5\4\3\2\23\24\7\2\2\3\24\3\3\2\2\2\25\26\5\6\4\2\26\5\3\2\2\2\27\30" +
          "\b\4\1\2\30\31\7\22\2\2\31%\5\6\4\f\32\33\7\21\2\2\33%\5\6\4\13\34%\5" +
          "\b\5\2\35 \5\b\5\2\36\37\7\16\2\2\37!\5\b\5\2 \36\3\2\2\2!\"\3\2\2\2\"" +
          " \3\2\2\2\"#\3\2\2\2#%\3\2\2\2$\27\3\2\2\2$\32\3\2\2\2$\34\3\2\2\2$\35" +
          "\3\2\2\2%>\3\2\2\2&\'\f\t\2\2\'(\7\24\2\2(=\5\6\4\n)*\f\b\2\2*+\t\2\2" +
          "\2+=\5\6\4\t,-\f\7\2\2-.\t\3\2\2.=\5\6\4\b/\60\f\6\2\2\60\61\5\20\t\2" +
          "\61\62\5\6\4\7\62=\3\2\2\2\63\64\f\5\2\2\64\65\7\35\2\2\65=\5\6\4\6\66" +
          "8\f\n\2\2\679\7\23\2\28\67\3\2\2\29:\3\2\2\2:8\3\2\2\2:;\3\2\2\2;=\3\2" +
          "\2\2<&\3\2\2\2<)\3\2\2\2<,\3\2\2\2</\3\2\2\2<\63\3\2\2\2<\66\3\2\2\2=" +
          "@\3\2\2\2><\3\2\2\2>?\3\2\2\2?\7\3\2\2\2@>\3\2\2\2A]\5\n\6\2B]\7\7\2\2" +
          "C]\7\b\2\2D]\7\5\2\2E]\7\6\2\2FG\7\t\2\2GH\5\6\4\2HI\7\n\2\2I]\3\2\2\2" +
          "JK\5\16\b\2KL\7\t\2\2LM\5\f\7\2MN\7\n\2\2N]\3\2\2\2OX\7\13\2\2PU\5\6\4" +
          "\2QR\t\4\2\2RT\5\6\4\2SQ\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2VY\3\2\2" +
          "\2WU\3\2\2\2XP\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z]\7\f\2\2[]\5\16\b\2\\A\3\2" +
          "\2\2\\B\3\2\2\2\\C\3\2\2\2\\D\3\2\2\2\\E\3\2\2\2\\F\3\2\2\2\\J\3\2\2\2" +
          "\\O\3\2\2\2\\[\3\2\2\2]\t\3\2\2\2^_\5\16\b\2_`\7\20\2\2`b\3\2\2\2a^\3" +
          "\2\2\2ab\3\2\2\2bd\3\2\2\2ce\7\36\2\2dc\3\2\2\2de\3\2\2\2ef\3\2\2\2fh" +
          "\7\3\2\2gi\7\36\2\2hg\3\2\2\2hi\3\2\2\2ij\3\2\2\2jk\7\5\2\2km\7\16\2\2" +
          "ln\7\36\2\2ml\3\2\2\2mn\3\2\2\2no\3\2\2\2oq\7\3\2\2pr\7\36\2\2qp\3\2\2" +
          "\2qr\3\2\2\2rs\3\2\2\2s\u00e6\7\5\2\2tu\5\16\b\2uv\7\20\2\2vx\3\2\2\2" +
          "wt\3\2\2\2wx\3\2\2\2xz\3\2\2\2y{\7\36\2\2zy\3\2\2\2z{\3\2\2\2{|\3\2\2" +
          "\2|~\7\3\2\2}\177\7\36\2\2~}\3\2\2\2~\177\3\2\2\2\177\u0080\3\2\2\2\u0080" +
          "\u00e6\7\5\2\2\u0081\u0082\5\16\b\2\u0082\u0083\7\20\2\2\u0083\u0085\3" +
          "\2\2\2\u0084\u0081\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0087\3\2\2\2\u0086" +
          "\u0088\7\36\2\2\u0087\u0086\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3" +
          "\2\2\2\u0089\u008a\7\3\2\2\u008a\u008c\7\16\2\2\u008b\u008d\7\36\2\2\u008c" +
          "\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u00e6\7\3" +
          "\2\2\u008f\u0090\5\16\b\2\u0090\u0091\7\20\2\2\u0091\u0093\3\2\2\2\u0092" +
          "\u008f\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2\2\2\u0094\u0096\7\36" +
          "\2\2\u0095\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097" +
          "\u0099\7\3\2\2\u0098\u009a\7\36\2\2\u0099\u0098\3\2\2\2\u0099\u009a\3" +
          "\2\2\2\u009a\u009b\3\2\2\2\u009b\u009c\7\5\2\2\u009c\u009e\7\16\2\2\u009d" +
          "\u009f\7\36\2\2\u009e\u009d\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\3" +
          "\2\2\2\u00a0\u00e6\7\3\2\2\u00a1\u00a2\5\16\b\2\u00a2\u00a3\7\20\2\2\u00a3" +
          "\u00a5\3\2\2\2\u00a4\u00a1\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a7\3\2" +
          "\2\2\u00a6\u00a8\7\36\2\2\u00a7\u00a6\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8" +
          "\u00a9\3\2\2\2\u00a9\u00aa\7\3\2\2\u00aa\u00ac\7\16\2\2\u00ab\u00ad\7" +
          "\36\2\2\u00ac\u00ab\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae" +
          "\u00b0\7\3\2\2\u00af\u00b1\7\36\2\2\u00b0\u00af\3\2\2\2\u00b0\u00b1\3" +
          "\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00e6\7\5\2\2\u00b3\u00b4\5\16\b\2\u00b4" +
          "\u00b5\7\20\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b3\3\2\2\2\u00b6\u00b7\3" +
          "\2\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00ba\7\36\2\2\u00b9\u00b8\3\2\2\2\u00b9" +
          "\u00ba\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\7\5\2\2\u00bc\u00be\7\16" +
          "\2\2\u00bd\u00bf\7\36\2\2\u00be\u00bd\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf" +
          "\u00c0\3\2\2\2\u00c0\u00e6\7\5\2\2\u00c1\u00c2\5\16\b\2\u00c2\u00c3\7" +
          "\20\2\2\u00c3\u00c5\3\2\2\2\u00c4\u00c1\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5" +
          "\u00c7\3\2\2\2\u00c6\u00c8\7\36\2\2\u00c7\u00c6\3\2\2\2\u00c7\u00c8\3" +
          "\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00cb\7\3\2\2\u00ca\u00cc\7\36\2\2\u00cb" +
          "\u00ca\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\7\5" +
          "\2\2\u00ce\u00d0\7\16\2\2\u00cf\u00d1\7\36\2\2\u00d0\u00cf\3\2\2\2\u00d0" +
          "\u00d1\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00e6\7\5\2\2\u00d3\u00d4\5\16" +
          "\b\2\u00d4\u00d5\7\20\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00d3\3\2\2\2\u00d6" +
          "\u00d7\3\2\2\2\u00d7\u00d9\3\2\2\2\u00d8\u00da\7\36\2\2\u00d9\u00d8\3" +
          "\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00dc\7\5\2\2\u00dc" +
          "\u00de\7\16\2\2\u00dd\u00df\7\36\2\2\u00de\u00dd\3\2\2\2\u00de\u00df\3" +
          "\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e2\7\3\2\2\u00e1\u00e3\7\36\2\2\u00e2" +
          "\u00e1\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e6\7\5" +
          "\2\2\u00e5a\3\2\2\2\u00e5w\3\2\2\2\u00e5\u0084\3\2\2\2\u00e5\u0092\3\2" +
          "\2\2\u00e5\u00a4\3\2\2\2\u00e5\u00b6\3\2\2\2\u00e5\u00c4\3\2\2\2\u00e5" +
          "\u00d6\3\2\2\2\u00e6\13\3\2\2\2\u00e7\u00ec\5\6\4\2\u00e8\u00e9\7\r\2" +
          "\2\u00e9\u00eb\5\6\4\2\u00ea\u00e8\3\2\2\2\u00eb\u00ee\3\2\2\2\u00ec\u00ea" +
          "\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ef" +
          "\u00e7\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\r\3\2\2\2\u00f1\u00f5\7\3\2\2" +
          "\u00f2\u00f4\t\5\2\2\u00f3\u00f2\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3" +
          "\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\17\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8" +
          "\u00f9\t\6\2\2\u00f9\21\3\2\2\2,\"$:<>UX\\adhmqwz~\u0084\u0087\u008c\u0092" +
          "\u0095\u0099\u009e\u00a4\u00a7\u00ac\u00b0\u00b6\u00b9\u00be\u00c4\u00c7" +
          "\u00cb\u00d0\u00d6\u00d9\u00de\u00e2\u00e5\u00ec\u00ef\u00f5";
  public static final ATN _ATN =
      new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
      new PredictionContextCache();
  private static final String[] _LITERAL_NAMES = makeLiteralNames();
  private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
  public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

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

  public F7Parser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
  }

  private static String[] makeRuleNames() {
    return new String[]{
        "start", "block", "expression", "atom", "range", "arguments", "identifier",
        "comparisonOperator"
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
  public ATN getATN() {
    return _ATN;
  }

  public final StartContext start() throws RecognitionException {
    StartContext _localctx = new StartContext(_ctx, getState());
    enterRule(_localctx, 0, RULE_start);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(16);
        block();
        setState(17);
        match(EOF);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final BlockContext block() throws RecognitionException {
    BlockContext _localctx = new BlockContext(_ctx, getState());
    enterRule(_localctx, 2, RULE_block);
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(19);
        expression(0);
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final ExpressionContext expression() throws RecognitionException {
    return expression(0);
  }

  private ExpressionContext expression(int _p) throws RecognitionException {
    ParserRuleContext _parentctx = _ctx;
    int _parentState = getState();
    ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
    ExpressionContext _prevctx = _localctx;
    int _startState = 4;
    enterRecursionRule(_localctx, 4, RULE_expression, _p);
    int _la;
    try {
      int _alt;
      enterOuterAlt(_localctx, 1);
      {
        setState(34);
        _errHandler.sync(this);
        switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
          case 1: {
            _localctx = new UnaryMinusExpressionContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;

            setState(22);
            match(Minus);
            setState(23);
            expression(10);
          }
          break;
          case 2: {
            _localctx = new UnaryPlusExpressionContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(24);
            match(Plus);
            setState(25);
            expression(9);
          }
          break;
          case 3: {
            _localctx = new AtomExpressionContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(26);
            atom();
          }
          break;
          case 4: {
            _localctx = new RangeExpressionContext(_localctx);
            _ctx = _localctx;
            _prevctx = _localctx;
            setState(27);
            atom();
            setState(30);
            _errHandler.sync(this);
            _alt = 1;
            do {
              switch (_alt) {
                case 1: {
                  {
                    setState(28);
                    ((RangeExpressionContext) _localctx).separator = match(Colon);
                    setState(29);
                    atom();
                  }
                }
                break;
                default:
                  throw new NoViableAltException(this);
              }
              setState(32);
              _errHandler.sync(this);
              _alt = getInterpreter().adaptivePredict(_input, 0, _ctx);
            } while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
          }
          break;
        }
        _ctx.stop = _input.LT(-1);
        setState(60);
        _errHandler.sync(this);
        _alt = getInterpreter().adaptivePredict(_input, 4, _ctx);
        while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
          if (_alt == 1) {
            if (_parseListeners != null) triggerExitRuleEvent();
            _prevctx = _localctx;
            {
              setState(58);
              _errHandler.sync(this);
              switch (getInterpreter().adaptivePredict(_input, 3, _ctx)) {
                case 1: {
                  _localctx = new PowerExpressionContext(new ExpressionContext(_parentctx, _parentState));
                  ((PowerExpressionContext) _localctx).left = _prevctx;
                  pushNewRecursionContext(_localctx, _startState, RULE_expression);
                  setState(36);
                  if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
                  setState(37);
                  ((PowerExpressionContext) _localctx).op = match(Power);
                  setState(38);
                  ((PowerExpressionContext) _localctx).right = expression(8);
                }
                break;
                case 2: {
                  _localctx = new MultiplicationExpressionContext(new ExpressionContext(_parentctx, _parentState));
                  ((MultiplicationExpressionContext) _localctx).left = _prevctx;
                  pushNewRecursionContext(_localctx, _startState, RULE_expression);
                  setState(39);
                  if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
                  setState(40);
                  ((MultiplicationExpressionContext) _localctx).op = _input.LT(1);
                  _la = _input.LA(1);
                  if (!(_la == Divide || _la == Multiply)) {
                    ((MultiplicationExpressionContext) _localctx).op = (Token) _errHandler.recoverInline(this);
                  } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                  }
                  setState(41);
                  ((MultiplicationExpressionContext) _localctx).right = expression(7);
                }
                break;
                case 3: {
                  _localctx = new AdditiveExpressionContext(new ExpressionContext(_parentctx, _parentState));
                  ((AdditiveExpressionContext) _localctx).left = _prevctx;
                  pushNewRecursionContext(_localctx, _startState, RULE_expression);
                  setState(42);
                  if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
                  setState(43);
                  ((AdditiveExpressionContext) _localctx).op = _input.LT(1);
                  _la = _input.LA(1);
                  if (!(_la == Plus || _la == Minus)) {
                    ((AdditiveExpressionContext) _localctx).op = (Token) _errHandler.recoverInline(this);
                  } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                  }
                  setState(44);
                  ((AdditiveExpressionContext) _localctx).right = expression(6);
                }
                break;
                case 4: {
                  _localctx = new RelationalExpressionContext(new ExpressionContext(_parentctx, _parentState));
                  ((RelationalExpressionContext) _localctx).left = _prevctx;
                  pushNewRecursionContext(_localctx, _startState, RULE_expression);
                  setState(45);
                  if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
                  setState(46);
                  ((RelationalExpressionContext) _localctx).op = comparisonOperator();
                  setState(47);
                  ((RelationalExpressionContext) _localctx).right = expression(5);
                }
                break;
                case 5: {
                  _localctx = new ConcatExpressionContext(new ExpressionContext(_parentctx, _parentState));
                  ((ConcatExpressionContext) _localctx).left = _prevctx;
                  pushNewRecursionContext(_localctx, _startState, RULE_expression);
                  setState(49);
                  if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
                  setState(50);
                  ((ConcatExpressionContext) _localctx).op = match(Ampersand);
                  setState(51);
                  ((ConcatExpressionContext) _localctx).right = expression(4);
                }
                break;
                case 6: {
                  _localctx = new UnaryPercentExpressionContext(new ExpressionContext(_parentctx, _parentState));
                  pushNewRecursionContext(_localctx, _startState, RULE_expression);
                  setState(52);
                  if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
                  setState(54);
                  _errHandler.sync(this);
                  _alt = 1;
                  do {
                    switch (_alt) {
                      case 1: {
                        {
                          setState(53);
                          match(Percent);
                        }
                      }
                      break;
                      default:
                        throw new NoViableAltException(this);
                    }
                    setState(56);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 2, _ctx);
                  } while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
                }
                break;
              }
            }
          }
          setState(62);
          _errHandler.sync(this);
          _alt = getInterpreter().adaptivePredict(_input, 4, _ctx);
        }
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      unrollRecursionContexts(_parentctx);
    }
    return _localctx;
  }

  public final AtomContext atom() throws RecognitionException {
    AtomContext _localctx = new AtomContext(_ctx, getState());
    enterRule(_localctx, 6, RULE_atom);
    int _la;
    try {
      setState(90);
      _errHandler.sync(this);
      switch (getInterpreter().adaptivePredict(_input, 7, _ctx)) {
        case 1:
          _localctx = new CellAtomContext(_localctx);
          enterOuterAlt(_localctx, 1);
        {
          setState(63);
          range();
        }
        break;
        case 2:
          _localctx = new StringAtomContext(_localctx);
          enterOuterAlt(_localctx, 2);
        {
          setState(64);
          match(String);
        }
        break;
        case 3:
          _localctx = new ErrorAtomContext(_localctx);
          enterOuterAlt(_localctx, 3);
        {
          setState(65);
          match(Error);
        }
        break;
        case 4:
          _localctx = new NumberAtomContext(_localctx);
          enterOuterAlt(_localctx, 4);
        {
          setState(66);
          match(Int);
        }
        break;
        case 5:
          _localctx = new NumberAtomContext(_localctx);
          enterOuterAlt(_localctx, 5);
        {
          setState(67);
          match(Number);
        }
        break;
        case 6:
          _localctx = new ParentheticalAtomContext(_localctx);
          enterOuterAlt(_localctx, 6);
        {
          setState(68);
          match(LeftParen);
          setState(69);
          expression(0);
          setState(70);
          match(RightParen);
        }
        break;
        case 7:
          _localctx = new FormulaAtomContext(_localctx);
          enterOuterAlt(_localctx, 7);
        {
          setState(72);
          ((FormulaAtomContext) _localctx).name = identifier();
          setState(73);
          match(LeftParen);
          setState(74);
          arguments();
          setState(75);
          match(RightParen);
        }
        break;
        case 8:
          _localctx = new ListAtomContext(_localctx);
          enterOuterAlt(_localctx, 8);
        {
          setState(77);
          match(LeftBrace);
          setState(86);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NCharacters) | (1L << Int) | (1L << Number) | (1L << String) | (1L << Error) | (1L << LeftParen) | (1L << LeftBrace) | (1L << Plus) | (1L << Minus) | (1L << Dollar))) != 0)) {
            {
              setState(78);
              expression(0);
              setState(83);
              _errHandler.sync(this);
              _la = _input.LA(1);
              while (_la == Comma || _la == SemiColon) {
                {
                  {
                    setState(79);
                    ((ListAtomContext) _localctx).separator = _input.LT(1);
                    _la = _input.LA(1);
                    if (!(_la == Comma || _la == SemiColon)) {
                      ((ListAtomContext) _localctx).separator = (Token) _errHandler.recoverInline(this);
                    } else {
                      if (_input.LA(1) == Token.EOF) matchedEOF = true;
                      _errHandler.reportMatch(this);
                      consume();
                    }
                    setState(80);
                    expression(0);
                  }
                }
                setState(85);
                _errHandler.sync(this);
                _la = _input.LA(1);
              }
            }
          }

          setState(88);
          match(RightBrace);
        }
        break;
        case 9:
          _localctx = new NamedAtomContext(_localctx);
          enterOuterAlt(_localctx, 9);
        {
          setState(89);
          identifier();
        }
        break;
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final RangeContext range() throws RecognitionException {
    RangeContext _localctx = new RangeContext(_ctx, getState());
    enterRule(_localctx, 8, RULE_range);
    int _la;
    try {
      setState(227);
      _errHandler.sync(this);
      switch (getInterpreter().adaptivePredict(_input, 38, _ctx)) {
        case 1:
          _localctx = new BiRangeContext(_localctx);
          enterOuterAlt(_localctx, 1);
        {
          setState(95);
          _errHandler.sync(this);
          switch (getInterpreter().adaptivePredict(_input, 8, _ctx)) {
            case 1: {
              setState(92);
              ((BiRangeContext) _localctx).grid = identifier();
              setState(93);
              match(Bang);
            }
            break;
          }
          setState(98);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(97);
              ((BiRangeContext) _localctx).absoFirstColumn = match(Dollar);
            }
          }

          setState(100);
          ((BiRangeContext) _localctx).firstColumn = match(NCharacters);
          setState(102);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(101);
              ((BiRangeContext) _localctx).absoRow = match(Dollar);
            }
          }

          setState(104);
          ((BiRangeContext) _localctx).firstRow = match(Int);
          setState(105);
          match(Colon);
          setState(107);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(106);
              ((BiRangeContext) _localctx).absoLastColumn = match(Dollar);
            }
          }

          setState(109);
          ((BiRangeContext) _localctx).lastColumn = match(NCharacters);
          setState(111);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(110);
              ((BiRangeContext) _localctx).absoLastRow = match(Dollar);
            }
          }

          setState(113);
          ((BiRangeContext) _localctx).lastRow = match(Int);
        }
        break;
        case 2:
          _localctx = new UniRangeContext(_localctx);
          enterOuterAlt(_localctx, 2);
        {
          setState(117);
          _errHandler.sync(this);
          switch (getInterpreter().adaptivePredict(_input, 13, _ctx)) {
            case 1: {
              setState(114);
              ((UniRangeContext) _localctx).grid = identifier();
              setState(115);
              match(Bang);
            }
            break;
          }
          setState(120);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(119);
              ((UniRangeContext) _localctx).absoFirstColumn = match(Dollar);
            }
          }

          setState(122);
          ((UniRangeContext) _localctx).firstColumn = match(NCharacters);
          setState(124);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(123);
              ((UniRangeContext) _localctx).absoFirstRow = match(Dollar);
            }
          }

          setState(126);
          ((UniRangeContext) _localctx).firstRow = match(Int);
        }
        break;
        case 3:
          _localctx = new ColumnWiseBiRangeContext(_localctx);
          enterOuterAlt(_localctx, 3);
        {
          setState(130);
          _errHandler.sync(this);
          switch (getInterpreter().adaptivePredict(_input, 16, _ctx)) {
            case 1: {
              setState(127);
              ((ColumnWiseBiRangeContext) _localctx).grid = identifier();
              setState(128);
              match(Bang);
            }
            break;
          }
          setState(133);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(132);
              ((ColumnWiseBiRangeContext) _localctx).absoFirstColumn = match(Dollar);
            }
          }

          setState(135);
          ((ColumnWiseBiRangeContext) _localctx).firstColumn = match(NCharacters);
          setState(136);
          match(Colon);
          setState(138);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(137);
              ((ColumnWiseBiRangeContext) _localctx).absoLastColumn = match(Dollar);
            }
          }

          setState(140);
          ((ColumnWiseBiRangeContext) _localctx).lastColumn = match(NCharacters);
        }
        break;
        case 4:
          _localctx = new ColumnWiseWithRowOffsetFirstBiRangeContext(_localctx);
          enterOuterAlt(_localctx, 4);
        {
          setState(144);
          _errHandler.sync(this);
          switch (getInterpreter().adaptivePredict(_input, 19, _ctx)) {
            case 1: {
              setState(141);
              ((ColumnWiseWithRowOffsetFirstBiRangeContext) _localctx).grid = identifier();
              setState(142);
              match(Bang);
            }
            break;
          }
          setState(147);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(146);
              ((ColumnWiseWithRowOffsetFirstBiRangeContext) _localctx).absoFirstColumn = match(Dollar);
            }
          }

          setState(149);
          ((ColumnWiseWithRowOffsetFirstBiRangeContext) _localctx).firstColumn = match(NCharacters);
          setState(151);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(150);
              ((ColumnWiseWithRowOffsetFirstBiRangeContext) _localctx).absoFirstRow = match(Dollar);
            }
          }

          setState(153);
          ((ColumnWiseWithRowOffsetFirstBiRangeContext) _localctx).firstRow = match(Int);
          setState(154);
          match(Colon);
          setState(156);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(155);
              ((ColumnWiseWithRowOffsetFirstBiRangeContext) _localctx).absoLastColumn = match(Dollar);
            }
          }

          setState(158);
          ((ColumnWiseWithRowOffsetFirstBiRangeContext) _localctx).lastColumn = match(NCharacters);
        }
        break;
        case 5:
          _localctx = new ColumnWiseWithRowOffsetLastBiRangeContext(_localctx);
          enterOuterAlt(_localctx, 5);
        {
          setState(162);
          _errHandler.sync(this);
          switch (getInterpreter().adaptivePredict(_input, 23, _ctx)) {
            case 1: {
              setState(159);
              ((ColumnWiseWithRowOffsetLastBiRangeContext) _localctx).grid = identifier();
              setState(160);
              match(Bang);
            }
            break;
          }
          setState(165);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(164);
              ((ColumnWiseWithRowOffsetLastBiRangeContext) _localctx).absoFirstColumn = match(Dollar);
            }
          }

          setState(167);
          ((ColumnWiseWithRowOffsetLastBiRangeContext) _localctx).firstColumn = match(NCharacters);
          setState(168);
          match(Colon);
          setState(170);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(169);
              ((ColumnWiseWithRowOffsetLastBiRangeContext) _localctx).absoLastColumn = match(Dollar);
            }
          }

          setState(172);
          ((ColumnWiseWithRowOffsetLastBiRangeContext) _localctx).lastColumn = match(NCharacters);
          setState(174);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(173);
              ((ColumnWiseWithRowOffsetLastBiRangeContext) _localctx).absoLastRow = match(Dollar);
            }
          }

          setState(176);
          ((ColumnWiseWithRowOffsetLastBiRangeContext) _localctx).lastRow = match(Int);
        }
        break;
        case 6:
          _localctx = new RowWiseBiRangeContext(_localctx);
          enterOuterAlt(_localctx, 6);
        {
          setState(180);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == NCharacters) {
            {
              setState(177);
              ((RowWiseBiRangeContext) _localctx).grid = identifier();
              setState(178);
              match(Bang);
            }
          }

          setState(183);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(182);
              ((RowWiseBiRangeContext) _localctx).absoFirstRow = match(Dollar);
            }
          }

          setState(185);
          ((RowWiseBiRangeContext) _localctx).firstRow = match(Int);
          setState(186);
          match(Colon);
          setState(188);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(187);
              ((RowWiseBiRangeContext) _localctx).absoLastRow = match(Dollar);
            }
          }

          setState(190);
          ((RowWiseBiRangeContext) _localctx).lastRow = match(Int);
        }
        break;
        case 7:
          _localctx = new RowWiseWithColumnOffsetFirstBiRangeContext(_localctx);
          enterOuterAlt(_localctx, 7);
        {
          setState(194);
          _errHandler.sync(this);
          switch (getInterpreter().adaptivePredict(_input, 30, _ctx)) {
            case 1: {
              setState(191);
              ((RowWiseWithColumnOffsetFirstBiRangeContext) _localctx).grid = identifier();
              setState(192);
              match(Bang);
            }
            break;
          }
          setState(197);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(196);
              ((RowWiseWithColumnOffsetFirstBiRangeContext) _localctx).absoFirstColumn = match(Dollar);
            }
          }

          setState(199);
          ((RowWiseWithColumnOffsetFirstBiRangeContext) _localctx).firstColumn = match(NCharacters);
          setState(201);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(200);
              ((RowWiseWithColumnOffsetFirstBiRangeContext) _localctx).absoFirstRow = match(Dollar);
            }
          }

          setState(203);
          ((RowWiseWithColumnOffsetFirstBiRangeContext) _localctx).firstRow = match(Int);
          setState(204);
          match(Colon);
          setState(206);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(205);
              ((RowWiseWithColumnOffsetFirstBiRangeContext) _localctx).absoLastRow = match(Dollar);
            }
          }

          setState(208);
          ((RowWiseWithColumnOffsetFirstBiRangeContext) _localctx).lastRow = match(Int);
        }
        break;
        case 8:
          _localctx = new RowWiseWithColumnOffsetLastBiRangeContext(_localctx);
          enterOuterAlt(_localctx, 8);
        {
          setState(212);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == NCharacters) {
            {
              setState(209);
              ((RowWiseWithColumnOffsetLastBiRangeContext) _localctx).grid = identifier();
              setState(210);
              match(Bang);
            }
          }

          setState(215);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(214);
              ((RowWiseWithColumnOffsetLastBiRangeContext) _localctx).absoFirstRow = match(Dollar);
            }
          }

          setState(217);
          ((RowWiseWithColumnOffsetLastBiRangeContext) _localctx).firstRow = match(Int);
          setState(218);
          match(Colon);
          setState(220);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(219);
              ((RowWiseWithColumnOffsetLastBiRangeContext) _localctx).absLastColumn = match(Dollar);
            }
          }

          setState(222);
          ((RowWiseWithColumnOffsetLastBiRangeContext) _localctx).lastColumn = match(NCharacters);
          setState(224);
          _errHandler.sync(this);
          _la = _input.LA(1);
          if (_la == Dollar) {
            {
              setState(223);
              ((RowWiseWithColumnOffsetLastBiRangeContext) _localctx).absoLastRow = match(Dollar);
            }
          }

          setState(226);
          ((RowWiseWithColumnOffsetLastBiRangeContext) _localctx).lastRow = match(Int);
        }
        break;
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final ArgumentsContext arguments() throws RecognitionException {
    ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
    enterRule(_localctx, 10, RULE_arguments);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(237);
        _errHandler.sync(this);
        _la = _input.LA(1);
        if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NCharacters) | (1L << Int) | (1L << Number) | (1L << String) | (1L << Error) | (1L << LeftParen) | (1L << LeftBrace) | (1L << Plus) | (1L << Minus) | (1L << Dollar))) != 0)) {
          {
            setState(229);
            expression(0);
            setState(234);
            _errHandler.sync(this);
            _la = _input.LA(1);
            while (_la == Comma) {
              {
                {
                  setState(230);
                  match(Comma);
                  setState(231);
                  expression(0);
                }
              }
              setState(236);
              _errHandler.sync(this);
              _la = _input.LA(1);
            }
          }
        }

      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final IdentifierContext identifier() throws RecognitionException {
    IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
    enterRule(_localctx, 12, RULE_identifier);
    int _la;
    try {
      int _alt;
      enterOuterAlt(_localctx, 1);
      {
        setState(239);
        match(NCharacters);
        setState(243);
        _errHandler.sync(this);
        _alt = getInterpreter().adaptivePredict(_input, 41, _ctx);
        while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
          if (_alt == 1) {
            {
              {
                setState(240);
                _la = _input.LA(1);
                if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NCharacters) | (1L << Dot) | (1L << Int) | (1L << Underscore))) != 0))) {
                  _errHandler.recoverInline(this);
                } else {
                  if (_input.LA(1) == Token.EOF) matchedEOF = true;
                  _errHandler.reportMatch(this);
                  consume();
                }
              }
            }
          }
          setState(245);
          _errHandler.sync(this);
          _alt = getInterpreter().adaptivePredict(_input, 41, _ctx);
        }
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
    ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
    enterRule(_localctx, 14, RULE_comparisonOperator);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
        setState(246);
        _la = _input.LA(1);
        if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GreaterThan) | (1L << GreaterThanOrEqualTo) | (1L << LessThan) | (1L << LessThanOrEqualTO) | (1L << Equal) | (1L << NotEqual))) != 0))) {
          _errHandler.recoverInline(this);
        } else {
          if (_input.LA(1) == Token.EOF) matchedEOF = true;
          _errHandler.reportMatch(this);
          consume();
        }
      }
    } catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return _localctx;
  }

  public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
    switch (ruleIndex) {
      case 2:
        return expression_sempred((ExpressionContext) _localctx, predIndex);
    }
    return true;
  }

  private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
    switch (predIndex) {
      case 0:
        return precpred(_ctx, 7);
      case 1:
        return precpred(_ctx, 6);
      case 2:
        return precpred(_ctx, 5);
      case 3:
        return precpred(_ctx, 4);
      case 4:
        return precpred(_ctx, 3);
      case 5:
        return precpred(_ctx, 8);
    }
    return true;
  }

  public static class StartContext extends ParserRuleContext {
    public StartContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public BlockContext block() {
      return getRuleContext(BlockContext.class, 0);
    }

    public TerminalNode EOF() {
      return getToken(F7Parser.EOF, 0);
    }

    @Override public int getRuleIndex() {
      return RULE_start;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterStart(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitStart(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitStart(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class BlockContext extends ParserRuleContext {
    public BlockContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public ExpressionContext expression() {
      return getRuleContext(ExpressionContext.class, 0);
    }

    @Override public int getRuleIndex() {
      return RULE_block;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterBlock(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitBlock(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitBlock(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class ExpressionContext extends ParserRuleContext {
    public ExpressionContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public ExpressionContext() {
    }

    @Override public int getRuleIndex() {
      return RULE_expression;
    }

    public void copyFrom(ExpressionContext ctx) {
      super.copyFrom(ctx);
    }
  }

  public static class UnaryPercentExpressionContext extends ExpressionContext {
    public UnaryPercentExpressionContext(ExpressionContext ctx) {
      copyFrom(ctx);
    }

    public ExpressionContext expression() {
      return getRuleContext(ExpressionContext.class, 0);
    }

    public List<TerminalNode> Percent() {
      return getTokens(F7Parser.Percent);
    }

    public TerminalNode Percent(int i) {
      return getToken(F7Parser.Percent, i);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterUnaryPercentExpression(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitUnaryPercentExpression(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitUnaryPercentExpression(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class UnaryMinusExpressionContext extends ExpressionContext {
    public UnaryMinusExpressionContext(ExpressionContext ctx) {
      copyFrom(ctx);
    }

    public TerminalNode Minus() {
      return getToken(F7Parser.Minus, 0);
    }

    public ExpressionContext expression() {
      return getRuleContext(ExpressionContext.class, 0);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterUnaryMinusExpression(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitUnaryMinusExpression(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitUnaryMinusExpression(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class PowerExpressionContext extends ExpressionContext {
    public ExpressionContext left;
    public Token op;
    public ExpressionContext right;

    public PowerExpressionContext(ExpressionContext ctx) {
      copyFrom(ctx);
    }

    public List<ExpressionContext> expression() {
      return getRuleContexts(ExpressionContext.class);
    }

    public ExpressionContext expression(int i) {
      return getRuleContext(ExpressionContext.class, i);
    }

    public TerminalNode Power() {
      return getToken(F7Parser.Power, 0);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterPowerExpression(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitPowerExpression(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitPowerExpression(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class UnaryPlusExpressionContext extends ExpressionContext {
    public UnaryPlusExpressionContext(ExpressionContext ctx) {
      copyFrom(ctx);
    }

    public TerminalNode Plus() {
      return getToken(F7Parser.Plus, 0);
    }

    public ExpressionContext expression() {
      return getRuleContext(ExpressionContext.class, 0);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterUnaryPlusExpression(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitUnaryPlusExpression(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitUnaryPlusExpression(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class AtomExpressionContext extends ExpressionContext {
    public AtomExpressionContext(ExpressionContext ctx) {
      copyFrom(ctx);
    }

    public AtomContext atom() {
      return getRuleContext(AtomContext.class, 0);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterAtomExpression(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitAtomExpression(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitAtomExpression(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class AdditiveExpressionContext extends ExpressionContext {
    public ExpressionContext left;
    public Token op;
    public ExpressionContext right;

    public AdditiveExpressionContext(ExpressionContext ctx) {
      copyFrom(ctx);
    }

    public List<ExpressionContext> expression() {
      return getRuleContexts(ExpressionContext.class);
    }

    public ExpressionContext expression(int i) {
      return getRuleContext(ExpressionContext.class, i);
    }

    public TerminalNode Plus() {
      return getToken(F7Parser.Plus, 0);
    }

    public TerminalNode Minus() {
      return getToken(F7Parser.Minus, 0);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterAdditiveExpression(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitAdditiveExpression(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitAdditiveExpression(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class RelationalExpressionContext extends ExpressionContext {
    public ExpressionContext left;
    public ComparisonOperatorContext op;
    public ExpressionContext right;

    public RelationalExpressionContext(ExpressionContext ctx) {
      copyFrom(ctx);
    }

    public List<ExpressionContext> expression() {
      return getRuleContexts(ExpressionContext.class);
    }

    public ExpressionContext expression(int i) {
      return getRuleContext(ExpressionContext.class, i);
    }

    public ComparisonOperatorContext comparisonOperator() {
      return getRuleContext(ComparisonOperatorContext.class, 0);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterRelationalExpression(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitRelationalExpression(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitRelationalExpression(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class RangeExpressionContext extends ExpressionContext {
    public Token separator;

    public RangeExpressionContext(ExpressionContext ctx) {
      copyFrom(ctx);
    }

    public List<AtomContext> atom() {
      return getRuleContexts(AtomContext.class);
    }

    public AtomContext atom(int i) {
      return getRuleContext(AtomContext.class, i);
    }

    public List<TerminalNode> Colon() {
      return getTokens(F7Parser.Colon);
    }

    public TerminalNode Colon(int i) {
      return getToken(F7Parser.Colon, i);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterRangeExpression(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitRangeExpression(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitRangeExpression(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class MultiplicationExpressionContext extends ExpressionContext {
    public ExpressionContext left;
    public Token op;
    public ExpressionContext right;

    public MultiplicationExpressionContext(ExpressionContext ctx) {
      copyFrom(ctx);
    }

    public List<ExpressionContext> expression() {
      return getRuleContexts(ExpressionContext.class);
    }

    public ExpressionContext expression(int i) {
      return getRuleContext(ExpressionContext.class, i);
    }

    public TerminalNode Multiply() {
      return getToken(F7Parser.Multiply, 0);
    }

    public TerminalNode Divide() {
      return getToken(F7Parser.Divide, 0);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterMultiplicationExpression(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitMultiplicationExpression(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitMultiplicationExpression(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class ConcatExpressionContext extends ExpressionContext {
    public ExpressionContext left;
    public Token op;
    public ExpressionContext right;

    public ConcatExpressionContext(ExpressionContext ctx) {
      copyFrom(ctx);
    }

    public List<ExpressionContext> expression() {
      return getRuleContexts(ExpressionContext.class);
    }

    public ExpressionContext expression(int i) {
      return getRuleContext(ExpressionContext.class, i);
    }

    public TerminalNode Ampersand() {
      return getToken(F7Parser.Ampersand, 0);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterConcatExpression(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitConcatExpression(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitConcatExpression(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class AtomContext extends ParserRuleContext {
    public AtomContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public AtomContext() {
    }

    @Override public int getRuleIndex() {
      return RULE_atom;
    }

    public void copyFrom(AtomContext ctx) {
      super.copyFrom(ctx);
    }
  }

  public static class NamedAtomContext extends AtomContext {
    public NamedAtomContext(AtomContext ctx) {
      copyFrom(ctx);
    }

    public IdentifierContext identifier() {
      return getRuleContext(IdentifierContext.class, 0);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterNamedAtom(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitNamedAtom(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitNamedAtom(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class ErrorAtomContext extends AtomContext {
    public ErrorAtomContext(AtomContext ctx) {
      copyFrom(ctx);
    }

    public TerminalNode Error() {
      return getToken(F7Parser.Error, 0);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterErrorAtom(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitErrorAtom(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitErrorAtom(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class ListAtomContext extends AtomContext {
    public Token separator;

    public ListAtomContext(AtomContext ctx) {
      copyFrom(ctx);
    }

    public TerminalNode LeftBrace() {
      return getToken(F7Parser.LeftBrace, 0);
    }

    public TerminalNode RightBrace() {
      return getToken(F7Parser.RightBrace, 0);
    }

    public List<ExpressionContext> expression() {
      return getRuleContexts(ExpressionContext.class);
    }

    public ExpressionContext expression(int i) {
      return getRuleContext(ExpressionContext.class, i);
    }

    public List<TerminalNode> Comma() {
      return getTokens(F7Parser.Comma);
    }

    public TerminalNode Comma(int i) {
      return getToken(F7Parser.Comma, i);
    }

    public List<TerminalNode> SemiColon() {
      return getTokens(F7Parser.SemiColon);
    }

    public TerminalNode SemiColon(int i) {
      return getToken(F7Parser.SemiColon, i);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterListAtom(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitListAtom(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitListAtom(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class ParentheticalAtomContext extends AtomContext {
    public ParentheticalAtomContext(AtomContext ctx) {
      copyFrom(ctx);
    }

    public TerminalNode LeftParen() {
      return getToken(F7Parser.LeftParen, 0);
    }

    public ExpressionContext expression() {
      return getRuleContext(ExpressionContext.class, 0);
    }

    public TerminalNode RightParen() {
      return getToken(F7Parser.RightParen, 0);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterParentheticalAtom(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitParentheticalAtom(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitParentheticalAtom(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class CellAtomContext extends AtomContext {
    public CellAtomContext(AtomContext ctx) {
      copyFrom(ctx);
    }

    public RangeContext range() {
      return getRuleContext(RangeContext.class, 0);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterCellAtom(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitCellAtom(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitCellAtom(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class StringAtomContext extends AtomContext {
    public StringAtomContext(AtomContext ctx) {
      copyFrom(ctx);
    }

    public TerminalNode String() {
      return getToken(F7Parser.String, 0);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterStringAtom(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitStringAtom(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitStringAtom(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class FormulaAtomContext extends AtomContext {
    public IdentifierContext name;

    public FormulaAtomContext(AtomContext ctx) {
      copyFrom(ctx);
    }

    public TerminalNode LeftParen() {
      return getToken(F7Parser.LeftParen, 0);
    }

    public ArgumentsContext arguments() {
      return getRuleContext(ArgumentsContext.class, 0);
    }

    public TerminalNode RightParen() {
      return getToken(F7Parser.RightParen, 0);
    }

    public IdentifierContext identifier() {
      return getRuleContext(IdentifierContext.class, 0);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterFormulaAtom(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitFormulaAtom(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitFormulaAtom(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class NumberAtomContext extends AtomContext {
    public NumberAtomContext(AtomContext ctx) {
      copyFrom(ctx);
    }

    public TerminalNode Int() {
      return getToken(F7Parser.Int, 0);
    }

    public TerminalNode Number() {
      return getToken(F7Parser.Number, 0);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterNumberAtom(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitNumberAtom(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitNumberAtom(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class RangeContext extends ParserRuleContext {
    public RangeContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public RangeContext() {
    }

    @Override public int getRuleIndex() {
      return RULE_range;
    }

    public void copyFrom(RangeContext ctx) {
      super.copyFrom(ctx);
    }
  }

  public static class ColumnWiseWithRowOffsetFirstBiRangeContext extends RangeContext {
    public IdentifierContext grid;
    public Token absoFirstColumn;
    public Token firstColumn;
    public Token absoFirstRow;
    public Token firstRow;
    public Token absoLastColumn;
    public Token lastColumn;

    public ColumnWiseWithRowOffsetFirstBiRangeContext(RangeContext ctx) {
      copyFrom(ctx);
    }

    public TerminalNode Colon() {
      return getToken(F7Parser.Colon, 0);
    }

    public List<TerminalNode> NCharacters() {
      return getTokens(F7Parser.NCharacters);
    }

    public TerminalNode NCharacters(int i) {
      return getToken(F7Parser.NCharacters, i);
    }

    public TerminalNode Int() {
      return getToken(F7Parser.Int, 0);
    }

    public TerminalNode Bang() {
      return getToken(F7Parser.Bang, 0);
    }

    public IdentifierContext identifier() {
      return getRuleContext(IdentifierContext.class, 0);
    }

    public List<TerminalNode> Dollar() {
      return getTokens(F7Parser.Dollar);
    }

    public TerminalNode Dollar(int i) {
      return getToken(F7Parser.Dollar, i);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterColumnWiseWithRowOffsetFirstBiRange(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitColumnWiseWithRowOffsetFirstBiRange(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor)
        return ((F7Visitor<? extends T>) visitor).visitColumnWiseWithRowOffsetFirstBiRange(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class ColumnWiseWithRowOffsetLastBiRangeContext extends RangeContext {
    public IdentifierContext grid;
    public Token absoFirstColumn;
    public Token firstColumn;
    public Token absoLastColumn;
    public Token lastColumn;
    public Token absoLastRow;
    public Token lastRow;

    public ColumnWiseWithRowOffsetLastBiRangeContext(RangeContext ctx) {
      copyFrom(ctx);
    }

    public TerminalNode Colon() {
      return getToken(F7Parser.Colon, 0);
    }

    public List<TerminalNode> NCharacters() {
      return getTokens(F7Parser.NCharacters);
    }

    public TerminalNode NCharacters(int i) {
      return getToken(F7Parser.NCharacters, i);
    }

    public TerminalNode Int() {
      return getToken(F7Parser.Int, 0);
    }

    public TerminalNode Bang() {
      return getToken(F7Parser.Bang, 0);
    }

    public IdentifierContext identifier() {
      return getRuleContext(IdentifierContext.class, 0);
    }

    public List<TerminalNode> Dollar() {
      return getTokens(F7Parser.Dollar);
    }

    public TerminalNode Dollar(int i) {
      return getToken(F7Parser.Dollar, i);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterColumnWiseWithRowOffsetLastBiRange(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitColumnWiseWithRowOffsetLastBiRange(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor)
        return ((F7Visitor<? extends T>) visitor).visitColumnWiseWithRowOffsetLastBiRange(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class RowWiseBiRangeContext extends RangeContext {
    public IdentifierContext grid;
    public Token absoFirstRow;
    public Token firstRow;
    public Token absoLastRow;
    public Token lastRow;

    public RowWiseBiRangeContext(RangeContext ctx) {
      copyFrom(ctx);
    }

    public TerminalNode Colon() {
      return getToken(F7Parser.Colon, 0);
    }

    public List<TerminalNode> Int() {
      return getTokens(F7Parser.Int);
    }

    public TerminalNode Int(int i) {
      return getToken(F7Parser.Int, i);
    }

    public TerminalNode Bang() {
      return getToken(F7Parser.Bang, 0);
    }

    public IdentifierContext identifier() {
      return getRuleContext(IdentifierContext.class, 0);
    }

    public List<TerminalNode> Dollar() {
      return getTokens(F7Parser.Dollar);
    }

    public TerminalNode Dollar(int i) {
      return getToken(F7Parser.Dollar, i);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterRowWiseBiRange(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitRowWiseBiRange(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitRowWiseBiRange(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class BiRangeContext extends RangeContext {
    public IdentifierContext grid;
    public Token absoFirstColumn;
    public Token firstColumn;
    public Token absoRow;
    public Token firstRow;
    public Token absoLastColumn;
    public Token lastColumn;
    public Token absoLastRow;
    public Token lastRow;

    public BiRangeContext(RangeContext ctx) {
      copyFrom(ctx);
    }

    public TerminalNode Colon() {
      return getToken(F7Parser.Colon, 0);
    }

    public List<TerminalNode> NCharacters() {
      return getTokens(F7Parser.NCharacters);
    }

    public TerminalNode NCharacters(int i) {
      return getToken(F7Parser.NCharacters, i);
    }

    public List<TerminalNode> Int() {
      return getTokens(F7Parser.Int);
    }

    public TerminalNode Int(int i) {
      return getToken(F7Parser.Int, i);
    }

    public TerminalNode Bang() {
      return getToken(F7Parser.Bang, 0);
    }

    public IdentifierContext identifier() {
      return getRuleContext(IdentifierContext.class, 0);
    }

    public List<TerminalNode> Dollar() {
      return getTokens(F7Parser.Dollar);
    }

    public TerminalNode Dollar(int i) {
      return getToken(F7Parser.Dollar, i);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterBiRange(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitBiRange(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitBiRange(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class UniRangeContext extends RangeContext {
    public IdentifierContext grid;
    public Token absoFirstColumn;
    public Token firstColumn;
    public Token absoFirstRow;
    public Token firstRow;

    public UniRangeContext(RangeContext ctx) {
      copyFrom(ctx);
    }

    public TerminalNode NCharacters() {
      return getToken(F7Parser.NCharacters, 0);
    }

    public TerminalNode Int() {
      return getToken(F7Parser.Int, 0);
    }

    public TerminalNode Bang() {
      return getToken(F7Parser.Bang, 0);
    }

    public IdentifierContext identifier() {
      return getRuleContext(IdentifierContext.class, 0);
    }

    public List<TerminalNode> Dollar() {
      return getTokens(F7Parser.Dollar);
    }

    public TerminalNode Dollar(int i) {
      return getToken(F7Parser.Dollar, i);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterUniRange(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitUniRange(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitUniRange(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class ColumnWiseBiRangeContext extends RangeContext {
    public IdentifierContext grid;
    public Token absoFirstColumn;
    public Token firstColumn;
    public Token absoLastColumn;
    public Token lastColumn;

    public ColumnWiseBiRangeContext(RangeContext ctx) {
      copyFrom(ctx);
    }

    public TerminalNode Colon() {
      return getToken(F7Parser.Colon, 0);
    }

    public List<TerminalNode> NCharacters() {
      return getTokens(F7Parser.NCharacters);
    }

    public TerminalNode NCharacters(int i) {
      return getToken(F7Parser.NCharacters, i);
    }

    public TerminalNode Bang() {
      return getToken(F7Parser.Bang, 0);
    }

    public IdentifierContext identifier() {
      return getRuleContext(IdentifierContext.class, 0);
    }

    public List<TerminalNode> Dollar() {
      return getTokens(F7Parser.Dollar);
    }

    public TerminalNode Dollar(int i) {
      return getToken(F7Parser.Dollar, i);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterColumnWiseBiRange(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitColumnWiseBiRange(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitColumnWiseBiRange(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class RowWiseWithColumnOffsetFirstBiRangeContext extends RangeContext {
    public IdentifierContext grid;
    public Token absoFirstColumn;
    public Token firstColumn;
    public Token absoFirstRow;
    public Token firstRow;
    public Token absoLastRow;
    public Token lastRow;

    public RowWiseWithColumnOffsetFirstBiRangeContext(RangeContext ctx) {
      copyFrom(ctx);
    }

    public TerminalNode Colon() {
      return getToken(F7Parser.Colon, 0);
    }

    public TerminalNode NCharacters() {
      return getToken(F7Parser.NCharacters, 0);
    }

    public List<TerminalNode> Int() {
      return getTokens(F7Parser.Int);
    }

    public TerminalNode Int(int i) {
      return getToken(F7Parser.Int, i);
    }

    public TerminalNode Bang() {
      return getToken(F7Parser.Bang, 0);
    }

    public IdentifierContext identifier() {
      return getRuleContext(IdentifierContext.class, 0);
    }

    public List<TerminalNode> Dollar() {
      return getTokens(F7Parser.Dollar);
    }

    public TerminalNode Dollar(int i) {
      return getToken(F7Parser.Dollar, i);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterRowWiseWithColumnOffsetFirstBiRange(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitRowWiseWithColumnOffsetFirstBiRange(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor)
        return ((F7Visitor<? extends T>) visitor).visitRowWiseWithColumnOffsetFirstBiRange(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class RowWiseWithColumnOffsetLastBiRangeContext extends RangeContext {
    public IdentifierContext grid;
    public Token absoFirstRow;
    public Token firstRow;
    public Token absLastColumn;
    public Token lastColumn;
    public Token absoLastRow;
    public Token lastRow;

    public RowWiseWithColumnOffsetLastBiRangeContext(RangeContext ctx) {
      copyFrom(ctx);
    }

    public TerminalNode Colon() {
      return getToken(F7Parser.Colon, 0);
    }

    public List<TerminalNode> Int() {
      return getTokens(F7Parser.Int);
    }

    public TerminalNode Int(int i) {
      return getToken(F7Parser.Int, i);
    }

    public TerminalNode NCharacters() {
      return getToken(F7Parser.NCharacters, 0);
    }

    public TerminalNode Bang() {
      return getToken(F7Parser.Bang, 0);
    }

    public IdentifierContext identifier() {
      return getRuleContext(IdentifierContext.class, 0);
    }

    public List<TerminalNode> Dollar() {
      return getTokens(F7Parser.Dollar);
    }

    public TerminalNode Dollar(int i) {
      return getToken(F7Parser.Dollar, i);
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterRowWiseWithColumnOffsetLastBiRange(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitRowWiseWithColumnOffsetLastBiRange(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor)
        return ((F7Visitor<? extends T>) visitor).visitRowWiseWithColumnOffsetLastBiRange(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class ArgumentsContext extends ParserRuleContext {
    public ArgumentsContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public List<ExpressionContext> expression() {
      return getRuleContexts(ExpressionContext.class);
    }

    public ExpressionContext expression(int i) {
      return getRuleContext(ExpressionContext.class, i);
    }

    public List<TerminalNode> Comma() {
      return getTokens(F7Parser.Comma);
    }

    public TerminalNode Comma(int i) {
      return getToken(F7Parser.Comma, i);
    }

    @Override public int getRuleIndex() {
      return RULE_arguments;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterArguments(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitArguments(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitArguments(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class IdentifierContext extends ParserRuleContext {
    public IdentifierContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public List<TerminalNode> NCharacters() {
      return getTokens(F7Parser.NCharacters);
    }

    public TerminalNode NCharacters(int i) {
      return getToken(F7Parser.NCharacters, i);
    }

    public List<TerminalNode> Dot() {
      return getTokens(F7Parser.Dot);
    }

    public TerminalNode Dot(int i) {
      return getToken(F7Parser.Dot, i);
    }

    public List<TerminalNode> Underscore() {
      return getTokens(F7Parser.Underscore);
    }

    public TerminalNode Underscore(int i) {
      return getToken(F7Parser.Underscore, i);
    }

    public List<TerminalNode> Int() {
      return getTokens(F7Parser.Int);
    }

    public TerminalNode Int(int i) {
      return getToken(F7Parser.Int, i);
    }

    @Override public int getRuleIndex() {
      return RULE_identifier;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterIdentifier(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitIdentifier(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitIdentifier(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class ComparisonOperatorContext extends ParserRuleContext {
    public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public TerminalNode LessThanOrEqualTO() {
      return getToken(F7Parser.LessThanOrEqualTO, 0);
    }

    public TerminalNode GreaterThanOrEqualTo() {
      return getToken(F7Parser.GreaterThanOrEqualTo, 0);
    }

    public TerminalNode LessThan() {
      return getToken(F7Parser.LessThan, 0);
    }

    public TerminalNode GreaterThan() {
      return getToken(F7Parser.GreaterThan, 0);
    }

    public TerminalNode Equal() {
      return getToken(F7Parser.Equal, 0);
    }

    public TerminalNode NotEqual() {
      return getToken(F7Parser.NotEqual, 0);
    }

    @Override public int getRuleIndex() {
      return RULE_comparisonOperator;
    }

    @Override
    public void enterRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).enterComparisonOperator(this);
    }

    @Override
    public void exitRule(ParseTreeListener listener) {
      if (listener instanceof F7Listener) ((F7Listener) listener).exitComparisonOperator(this);
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof F7Visitor) return ((F7Visitor<? extends T>) visitor).visitComparisonOperator(this);
      else return visitor.visitChildren(this);
    }
  }
}