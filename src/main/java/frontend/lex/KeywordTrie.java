package frontend.lex;

import java.util.*;
import java.util.regex.Pattern;

/**
 * A trie structure for keywords that can be traversed one character at time during scanning.
 * Can also check and return if a given keyword is a division or an option clause.
 *
 * @author Giulia Pais
 * @version 1.0, 2023-03-20
 */
public class KeywordTrie {

    private static KeywordTrie instance;
    private final TrieNode root;
    private TrieNode pointer;
    private int minDepth = Integer.MAX_VALUE;
    private int maxDepth = 0;

    private static final Pattern idDivPattern = Pattern.compile("IDENTIFICATION\s*DIVISION");
    private static final Pattern dataDivPattern = Pattern.compile("DATA\s*DIVISION");
    private static final Pattern procDivPattern = Pattern.compile("PROCEDURE\s*DIVISION");

    private static final Set<String> options = new HashSet<>(
            Arrays.asList(
                 "TO", "BY", "DELIMITEDBY", "WITHNOADVANCING"
            )
    );

    private static final Set<String> reservedWords = new HashSet<>(
            Arrays.asList(
                "DISPLAY", "STOP", "ADD"
            )
    );

    private static final List<String> keywords = new ArrayList<>();

    private KeywordTrie() {
        this.root = new TrieNode(' ');
        this.pointer = this.root;
        // Init keywords
        keywords.add("IDENTIFICATIONDIVISION");
        keywords.add("DATADIVISION");
        keywords.add("PROCEDUREDIVISION");
        keywords.addAll(reservedWords);
        keywords.addAll(options);
        keywords.sort(Comparator.naturalOrder());
        for (String keyword : keywords) {
            insertKeyword(keyword);
        }
    }

    public static KeywordTrie getInstance() {
        if (instance == null) {
            instance = new KeywordTrie();
        }
        return instance;
    }

    private void insertKeyword(String keyword) {
        TrieNode node = root;
        int depth = 0;
        for (char c : keyword.toCharArray()) {
            node.addIfNotPresent(c);
            node = node.getChild(c);
            depth++;
        }
        node.setTerminal(true);

        this.minDepth = Math.min(this.minDepth, depth);
        this.maxDepth = Math.max(this.maxDepth, depth);
    }

    public boolean isKeyword(String keyword) {
        TrieNode node = root;
        for (char c : keyword.toCharArray()) {
            if (!node.hasChild(c)) {
                return false;
            }
            node = node.getChild(c);
        }
        return node.isTerminal();
    }

    public int traverse(char c) {
        if (!pointer.hasChild(c)) {
            reset();
            return -1;
        }
        pointer = pointer.getChild(c);
        return pointer.isTerminal() ? 1 : 0;
    }

    public void reset() {
        pointer = root;
    }

    public int getMinDepth() {
        return this.minDepth;
    }

    public int getMaxDepth() {
        return this.maxDepth;
    }

    boolean isReset() {
        return pointer == root;
    }

    public static String getDivision(String keyword) {
        if (idDivPattern.matcher(keyword).matches()) {
            return "IDENTIFICATION_DIVISION";
        }
        if (dataDivPattern.matcher(keyword).matches()) {
            return "DATA_DIVISION";
        }
        if (procDivPattern.matcher(keyword).matches()) {
            return "PROCEDURE_DIVISION";
        }
        return null;
    }

    public static boolean isOption(String keyword) {
        return options.contains(keyword.replaceAll("\\s", ""));
    }
}
