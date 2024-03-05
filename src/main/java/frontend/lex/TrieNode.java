package frontend.lex;

import java.util.HashMap;

class TrieNode {
    private final char character;
    private HashMap<Character, TrieNode> children = new HashMap<>();
    private boolean isTerminal = false;

    public TrieNode(char character) {
        this.character = character;
    }

    public TrieNode(char character, boolean isTerminal) {
        this.character = character;
        this.isTerminal = isTerminal;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }

    public boolean hasChild(char c) {
        return children.containsKey(c);
    }

    public TrieNode getChild(char c) {
        return children.get(c);
    }

    public void addIfNotPresent(char c) {
        children.computeIfAbsent(c, TrieNode::new);
    }
}
