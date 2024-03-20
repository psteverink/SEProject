package frontend.parse;

import frontend.lex.StatefulLexer;
import frontend.lex.Token;

public class Parser {
    private final StatefulLexer statefulLexer;
    private Token currentToken;

    public Parser(StatefulLexer statefulLexer) {
        this.statefulLexer = statefulLexer;
    }

    public void parse() {
        // TODO: implement
    }


}
