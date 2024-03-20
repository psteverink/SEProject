package frontend.lex;

import java.util.List;

/**
 * Produces valid tokens from a pre-processed source code on demand.
 * Production of tokens is dependent on the state of the lexer (according to the current division).
 *
 * @author Giulia Pais
 * @version 1.0, 2023-03-20
 */
public class StatefulLexer {
    private PreProcessor preProcessor;
    private final KeywordTrie keywordTrie = KeywordTrie.getInstance();

    private boolean preProcessed = false;

    private LexerState lexerState = LexerState.GLOBAL;

    private int currentLine = 0;
    private int startPosInLine = 0;
    private int endPosInLine = 0;
    private char curChar;

    public StatefulLexer() {}
    public StatefulLexer(List<String> fullText) {
        this.preProcessor = new PreProcessor(fullText);
    }

    public void setLexerState(LexerState lexerState) {
        this.lexerState = lexerState;
    }

    public void initPreProcessor(List<String> fullText) {
        this.preProcessor = new PreProcessor(fullText);
    }

    /**
     * Produces the next valid token from the source code.
     * This method should be called from the parser.
     *
     * @return An object of type {@link frontend.lex.Token}
     */
    public Token nextToken() {
        ensurePreprocessed();
        return scanToken();
    }

    private void ensurePreprocessed() {
        if (!preProcessed) {
            preProcessor.preProcess();
            preProcessed = true;
        }
    }

    /**
     * Scans the current code line character by character and returns the appropriate token.
     * Specialized methods are called according to the state of the lexer.
     *
     * @return An object of type {@link frontend.lex.Token}
     */
    private Token scanToken() {
        if (currentLine == -1) {
            return new Token(TokenType.EOF, null);
        }
        if (endPosInLine == startPosInLine) {
            advance();
            return scanToken();
        }
        if (endPosInLine > startPosInLine & Character.isSpaceChar(curChar)) {
            startPosInLine++;
            advance();
            return scanToken();
        }
        if (curChar == '.') {
            advance();
            startPosInLine++;
            return new Token(TokenType.DOT, ".");
        }
        if (lexerState == LexerState.GLOBAL || lexerState == LexerState.IDENTIFICATION) {
            // Anything in the global or identification division is an identifier
            return identifierGlobal();
        }
        if (lexerState == LexerState.DATA) {
            // TODO: implement
            return null;
        }
        return switch (Character.valueOf(curChar)) {
            case '"' -> literal();
            case ',' -> {
                Token token = new Token(TokenType.COMMA, ",");
                startPosInLine = endPosInLine;
                yield token;
            }
            case Character c when Character.isDigit(c) -> number();
            case Character c when Character.isLetter(c) -> identifier();
            default -> throw new IllegalStateException("Unexpected value: " + curChar); //TODO: change this
        };
    }

    /**
     * Advances one character in the current code line.
     * If the line ends, the line is changed, if no more lines exist, the current line is set to -1.
     * Blank spaces at the beginning of new lines are automatically skipped.
     * Comment lines or blank lines are automatically skipped.
     */
    private void advance() {
        // Does the line exist?
        if (currentLine == -1 || currentLine >= preProcessor.getPreProcessedLines().size()) {
            currentLine = -1;
            return;
        }
        // Is at the line end? -> change line
        if (endPosInLine >= preProcessor.getCodeLine(currentLine).length()) {
            // skip empty lines
            do {
                currentLine++;
                startPosInLine = 0;
                endPosInLine = 0;
                if (currentLine >= preProcessor.getPreProcessedLines().size()) {
                    currentLine = -1;
                    return;
                }
            } while (preProcessor.getLine(currentLine).isBlank() ||
                    preProcessor.getLine(currentLine).isComment());
        }
        // Retrieve next char
        curChar = preProcessor.getCodeLine(currentLine).charAt(endPosInLine);
        endPosInLine++;
    }

    /**
     * Peeks the next character without advancing (lookahead).
     * @return The character at endPosInLine or null if no more characters in the line
     */
    private Character peek() {
        if (endPosInLine < preProcessor.getCodeLine(currentLine).length()) {
            return preProcessor.getCodeLine(currentLine).charAt(endPosInLine);
        }
        return null;
    }

    private Token identifierGlobal() {
        String lexeme;
        while (endPosInLine < preProcessor.getCodeLine(currentLine).length() && curChar != '.') {
            if (Character.isSpaceChar(curChar)) {
                advance();
                continue;
            }
            if ((startPosInLine == endPosInLine - 1 & Character.isLetter(curChar)
                    && Character.isUpperCase(curChar)) || !keywordTrie.isReset()) {
                Token token = keyword();
                if (token != null) {
                    return token;
                }
            }
            advance();
        }
        if (curChar == '.') {
            lexeme = preProcessor.getCodeLine(currentLine).substring(startPosInLine, endPosInLine - 1);
            startPosInLine = endPosInLine - 1;
        } else {
            lexeme = preProcessor.getCodeLine(currentLine).substring(startPosInLine, endPosInLine);
            startPosInLine = endPosInLine;
        }
        keywordTrie.reset();
        return new Token(TokenType.IDENTIFIER, lexeme.trim());
    }

    private Token keyword() {
        if (keywordTrie.traverse(curChar) == 1) {
            String lexeme =  preProcessor.getCodeLine(currentLine).substring(startPosInLine, endPosInLine);
            String division = KeywordTrie.getDivision(lexeme);
            Token token;
            if (division != null) {
                TokenType divType = TokenType.valueOf(division);
                token = new Token(divType, lexeme);
            } else if (KeywordTrie.isOption(lexeme)) {
                token = new Token(TokenType.OPTION, lexeme);
            } else {
                token = new Token(TokenType.KEYWORD, lexeme);
            }
            startPosInLine = endPosInLine;
            keywordTrie.reset();
            return token;
        }
        return null;
    }

    private Token literal() {
        do {
            advance();
        } while(endPosInLine < preProcessor.getCodeLine(currentLine).length() && curChar != '"');
        String lexeme =  preProcessor.getCodeLine(currentLine).substring(startPosInLine, endPosInLine);
        startPosInLine = endPosInLine;
        return new Token(TokenType.STRING_LITERAL, lexeme);
    }

    private Token number() {
        String lexeme;
        do {
            if (endPosInLine < preProcessor.getCodeLine(currentLine).length()) {
                advance();
            } else {
                lexeme =  preProcessor.getCodeLine(currentLine).substring(startPosInLine, endPosInLine);
                startPosInLine = endPosInLine;
                break;
            }
            if (Character.isDigit(curChar)) {
                continue;
            }
            if (curChar == '.' || curChar == ',') {
                Character nextChar = peek();
                if (nextChar != null && Character.isDigit(nextChar)) {
                    continue;
                }
            }
            lexeme =  preProcessor.getCodeLine(currentLine).substring(startPosInLine, endPosInLine - 1);
            startPosInLine = endPosInLine - 1;
            break;
        } while(true);
        return new Token(TokenType.NUMBER_LITERAL, lexeme.trim());
    }

    private Token identifier() {
        return null;
    }

}
