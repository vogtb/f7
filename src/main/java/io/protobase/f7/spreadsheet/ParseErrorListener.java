package io.protobase.f7.spreadsheet;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

/**
 * Listens for syntax errors when parsing, throwing a parse cancellation exception.
 */
public class ParseErrorListener extends BaseErrorListener {

  /**
   * If we encounter a syntax error, we can't do anything with it, so we stop parsing by throwing parse cancelation
   * exception.
   */
  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
      String msg, RecognitionException e)
      throws ParseCancellationException {
    throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
  }
}
