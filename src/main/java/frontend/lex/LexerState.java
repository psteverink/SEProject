package frontend.lex;

/**
 * Valid lexer states.
 * Valid lexer states transitions are:
 * <br>
 * * GLOBAL -> IDENTIFICATION <br>
 * * IDENTIFICATION -> DATA <br>
 * * IDENTIFICATION -> PROCEDURE <br>
 * * DATA -> PROCEDURE <br>
 *
 * @author Giulia Pais
 * @version 1.0, 2023-03-20
 */
public enum LexerState {
    GLOBAL, IDENTIFICATION, DATA, PROCEDURE
}
