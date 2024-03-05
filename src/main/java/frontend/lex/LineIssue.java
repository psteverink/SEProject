package frontend.lex;

public class LineIssue {
    private final int lineNumber;
    private final int position;
    private final String message;

    private String givenLineNumber = null;

    public LineIssue(int lineNumber, int position, String message, String givenLineNumber) {
        this.lineNumber = lineNumber;
        this.position = position;
        this.message = message;
        this.givenLineNumber = givenLineNumber;
    }
}
