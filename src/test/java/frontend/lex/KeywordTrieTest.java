package frontend.lex;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class KeywordTrieTest {
    @Test
    void testKeywordTrieDepth() throws NoSuchFieldException, IllegalAccessException {
        KeywordTrie trie = KeywordTrie.getInstance();
        Class<?> trieClass = KeywordTrie.class;
        Field keywords = trieClass.getDeclaredField("keywords");
        keywords.setAccessible(true);
        String[] keywordsArray = (String[]) keywords.get(trie);

        String shortest = Arrays.stream(keywordsArray)
                .min(Comparator.comparing(String::length))
                .orElse(null);

        String longest = Arrays.stream(keywordsArray)
                .max(Comparator.comparing(String::length))
                .orElse(null);

        int maxDepth = trie.getMaxDepth();
        int minDepth = trie.getMinDepth();
        assertTrue(maxDepth > minDepth);
        assert shortest != null;
        assertEquals(minDepth, shortest.length());
        assertEquals(maxDepth, longest.length());
    }

    @Test
    void testIsKeyword() {
        KeywordTrie trie = KeywordTrie.getInstance();
        assertTrue(trie.isKeyword("IDENTIFICATIONDIVISION"));
        assertFalse(trie.isKeyword("IDENTIFICATION"));
        assertFalse(trie.isKeyword("IDENTIFICATIONDIVISIONS"));
        assertFalse(trie.isKeyword("N"));
    }

    @Test
    void testSingleCharTraversal() {
        KeywordTrie trie = KeywordTrie.getInstance();
        // Using ADD
        assertEquals(0, trie.traverse('A'));
        assertEquals(0, trie.traverse('D'));
        assertEquals(1, trie.traverse('D'));
        assertFalse(trie.isReset());
        trie.reset();
        assertTrue(trie.isReset());

        // Non-keywords
        assertEquals(-1, trie.traverse('F'));
        assertTrue(trie.isReset());

        assertEquals(0, trie.traverse('A'));
        assertFalse(trie.isReset());
        assertEquals(-1, trie.traverse('X'));
        assertTrue(trie.isReset());
    }
}