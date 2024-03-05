package frontend.lex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Preprocesses the lines in the source code. It removes empty lines at the
 * beginning and at the end of the file and examines line number columns,
 * line indicator and extracts code from the remaining line.
 *
 * @author Giulia Pais
 * @version 1.0, 2023-03-05
 */
public class PreProcessor {
    /* File format */
    /* Sections are reported as arrays with 2 elements, first is inclusive, second is exclusive */
    /* Columns 1-6 -> line numbers or empty (skip) */
    static final int[] COLNUMS = {0, 6};
    /* Column 7 -> indicator */
    static final int INDICATOR_COL = 6;
    /* Columns 8-11 -> Area A (divisions, sections, paragraphs) */
    static final int[] A_AREA = {7, 11};
    /* Columns 12-72 -> Area B (statements) */
    static final int[] B_AREA = {11, 72};
    /* From 72 to 80 indicator area */
    private ArrayList<String> fullText;
    private List<Line> preProcessedLines;
    private HashMap<Integer, List<LineIssue>> lineIssues;
    private int lineCounter = 0;

    public PreProcessor(List<String> fullText) {
        this.fullText = new ArrayList<>(fullText);
        this.lineIssues = new HashMap<>();
    }

    public void preProcess() {
        trimLines();
        for (String line: fullText) {
            Line newLine = new Line(line, lineCounter);
            processLineNo(line, newLine);
            checkStatus(line, newLine);
            if (!newLine.isComment()) {
                extractCode(line, newLine);
            }
            preProcessedLines.add(newLine);
            lineCounter++;
        }
    }

    /**
     * Removes empty lines at the beginning and end of the file
     */
    private void trimLines() {
        while (fullText.get(0).isBlank()) {
            fullText.remove(0);
        }
        while (fullText.get(fullText.size() - 1).isBlank()) {
            fullText.remove(fullText.size() - 1);
        }
    }

    /**
     * Extracts the first 6 columns and checks if a manual line number was provided.
     * If it's a parseable integer it sets the given line number (in string format)
     * in the Line object
     *
     * @param originalLine - original line
     * @param newLine - new line created in preProcess()
     */
    private void processLineNo(String originalLine, Line newLine) {
        String firstCols = originalLine.substring(COLNUMS[0], COLNUMS[1]);
        if (firstCols.isBlank()) {
            // Line numbers can be empty
            return;
        }
        try {
            // If they are not, they should be parsable integers
            Integer.parseInt(firstCols.trim());
            newLine.setGivenLineNo(firstCols.trim());
        } catch (NumberFormatException e) {
            LineIssue issue = new LineIssue(newLine.getLineNumber(), COLNUMS[0],
                    "Invalid line number - must be a parseable integer",
                    null);
            lineIssues.computeIfAbsent(newLine.getLineNumber(), n -> new ArrayList<>());
            lineIssues.get(newLine.getLineNumber()).add(issue);
        }
    }

    /**
     * Checks the indicator column and sets if required either the comment flag or the
     * continuation flag on the Line object. If the indicator is not a valid character
     * (either empty, '*' or '-') a LineIssue is created and added to the lineIssues map.
     * @param originalLine - original line
     * @param newLine - new line created in preProcess()
     */
    private void checkStatus(String originalLine, Line newLine) {
        String statusCol = Character.toString(originalLine.charAt(INDICATOR_COL));
        if (!statusCol.isBlank() && !(statusCol.equals("*") || statusCol.equals("-"))) {
            LineIssue issue = new LineIssue(
                    newLine.getLineNumber(),
                    INDICATOR_COL,
                    "Invalid line status character - must be either empty, '*' or '-'",
                    newLine.getGivenLineNo()
            );
            lineIssues.computeIfAbsent(newLine.getLineNumber(), n -> new ArrayList<>());
            lineIssues.get(newLine.getLineNumber()).add(issue);
            return;
        }
        if (statusCol.equals("*")) {
            newLine.setComment(true);
            return;
        }
        if (statusCol.equals("-")) {
            newLine.setContinuation(true);
        }
    }

    /**
     * Extracts the portion of the line representing code, starting from first character
     * of area A until the last character of area B. If the code portion is not empty
     * it sets the code start position in the Line object.
     *
     * @param originalLine - original line
     * @param newLine - new line created in preProcess()
     */
    private void extractCode(String originalLine, Line newLine) {
        String codePortion = originalLine.substring(A_AREA[0], B_AREA[1]);
        if (codePortion.isBlank()) {
            return;
        }
        int codeStart = 0;
        for (int i = 0; i < codePortion.length(); i++) {
            if (!Character.isSpaceChar(codePortion.charAt(i))) {
                codeStart = i;
                break;
            }
        }
        newLine.setCodeStart(codeStart + A_AREA[0]);
        newLine.setCodeLine(codePortion.trim());
    }

}
