package frontend.lex;

import java.util.List;

public class Scanner {

    private final List<String> fullText;
    private final PreProcessor preProcessor;

    public Scanner(List<String> fullText) {
        this.fullText = fullText;
        this.preProcessor = new PreProcessor(fullText);
    }
}
