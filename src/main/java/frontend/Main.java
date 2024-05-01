package frontend;


import frontend.parse.BabyCobolGrammar;
import frontend.parse.BabyCobolGrammarBaseListener;
import frontend.parse.BabyCobolLexer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class Main {

    static List<String> readFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            System.out.println("File not found: " + path);
            throw new IOException();
        }
        return Files.readAllLines(path, Charset.defaultCharset());
    }

    public static void main(String[] args) throws IOException {
        Path path = null;
        if (args.length > 0) {
            try {
                path = Path.of(args[0]);
            } catch (InvalidPathException e) {
                System.out.println("Invalid path provided: " + args[0]);
                System.exit(1);
            }
        } else {
            System.out.print("Path to file: ");
            String inputPath;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                inputPath = reader.readLine();
                path = Path.of(inputPath);
            } catch (IOException io) {
                System.exit(1);
            }
        }
        List<String> fullTextList = readFile(path);
        String fullText = String.join("\n",fullTextList);
        CharStream charStream = CharStreams.fromString(fullText);
        BabyCobolLexer lexer = new BabyCobolLexer(charStream);
        Token token;

/*        while ((token = lexer.nextToken()).getType() != Token.EOF) {
            System.out.println(token);
        }*/

        TokenStream tokenStream = new CommonTokenStream(lexer);
        BabyCobolGrammar parser = new BabyCobolGrammar(tokenStream);

        ParseTree parseTree = parser.program();
        BabyCobolGrammarBaseListener listener = new BabyCobolGrammarBaseListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, parseTree);
    }

}
