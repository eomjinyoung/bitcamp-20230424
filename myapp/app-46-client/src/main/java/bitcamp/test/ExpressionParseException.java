package bitcamp.test;

public class ExpressionParseException extends Exception {
  private static final long serialVersionUID = 1L;

  public ExpressionParseException() {
    super();
  }

  public ExpressionParseException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ExpressionParseException(String message, Throwable cause) {
    super(message, cause);
  }

  public ExpressionParseException(String message) {
    super(message);
  }

  public ExpressionParseException(Throwable cause) {
    super(cause);
  }
}
