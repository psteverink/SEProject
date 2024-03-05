package frontend.lex;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a (pre-)processed single line in the source code.
 *
 * @author Giulia Pais
 * @version 1.0, 2023-03-05
 */
public class Line {
    /**
     * The original line as read from the source code
     */
    private String originalLine;
    /**
     * Line number assigned manually and parsed from the first 6 columns, if present
     */
    private String givenLineNo = null;
    /**
     * The line number as assigned by the pre-processor. Note: line that are not processed,
     * like comments, will still have a line number
     */
    private int lineNumber;
    /**
     * The position in the string where the code line starts:
     *  can be from position 8 onwards counting non-whitespace characters
     *  after the indicator column
     */
    private int codeStart = -1;
    /**
     * The portion of the string from the code start to char 72 (exclusive)
     */
    private String codeLine;
    private boolean isComment = false;
    private boolean isContinuation = false;

    public Line(String originalLine, int lineNumber) {
        this.originalLine = originalLine;
        this.lineNumber = lineNumber;
    }

    public String getOriginalLine() {
        return originalLine;
    }

    public void setOriginalLine(String originalLine) {
        this.originalLine = originalLine;
    }

    public String getGivenLineNo() {
        return givenLineNo;
    }

    public void setGivenLineNo(String givenLineNo) {
        this.givenLineNo = givenLineNo;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getCodeStart() {
        return codeStart;
    }

    public void setCodeStart(int codeStart) {
        this.codeStart = codeStart;
    }

    public String getCodeLine() {
        return codeLine;
    }

    public void setCodeLine(String codeLine) {
        this.codeLine = codeLine;
    }

    public boolean isComment() {
        return isComment;
    }

    public void setComment(boolean comment) {
        isComment = comment;
    }

    public boolean isContinuation() {
        return isContinuation;
    }

    public void setContinuation(boolean continuation) {
        isContinuation = continuation;
    }

    public boolean isBlank() {
        if (originalLine.isBlank()) {
            return true;
        }
        if (codeStart == -1) {
            return true;
        }
        return codeLine == null;
    }
}
