package frontend.lex;

import java.util.Arrays;

public class KeywordTrie {

    private static KeywordTrie instance;
    private final TrieNode root;
    private TrieNode pointer;
    private int minDepth = Integer.MAX_VALUE;
    private int maxDepth = 0;

    private static final String[] keywords = {
            // division names
            "IDENTIFICATIONDIVISION", "DATADIVISION", "PROCEDUREDIVISION",
            // reserved words
            "DISPLAY", "STOP", "ADD"
            // TODO: add more keywords
    };

    private KeywordTrie() {
        this.root = new TrieNode(' ');
        this.pointer = this.root;
        Arrays.sort(keywords);
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


}
