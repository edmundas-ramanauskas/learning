package learning.problems.spellcheck.impl;

import learning.data.structures.TrieNode;
import learning.problems.spellcheck.SpellChecker;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by edmundas on 23/01/2015.
 */
public class TrieBasedSpellChecker implements SpellChecker {

    private static final int SIMILAR = 2;

    private final TrieNode trie;
    private final int similar;

    public TrieBasedSpellChecker(Set<String> dictionary) {
        this(dictionary, SIMILAR);
    }

    public TrieBasedSpellChecker(Set<String> dictionary, int similar) {
        this.similar = similar;
        this.trie = new TrieNode();
        for(String word : dictionary) {
            this.trie.insert(word);
        }
    }

    @Override
    public boolean exists(String search) {
        return trie.exists(search);
    }

    @Override
    public Set<String> findSimilar(String search) {
        return find(search, similar);
    }

    @Override
    public Set<String> find(String search, int diff) {
        Set<String> matches = new LinkedHashSet<String>();

        return matches;
    }
}