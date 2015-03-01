package learning.problems.spellcheck.impl;

import learning.data.algorithms.Levenshtein;
import learning.problems.spellcheck.SpellChecker;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by edmundas on 23/01/2015.
 */
public class SimpleSpellChecker implements SpellChecker {

    private static final int SIMILAR = 2;

    private final Set<String> dictionary;
    private final int similar;

    public SimpleSpellChecker(Set<String> dictionary) {
        this(dictionary, SIMILAR);
    }

    public SimpleSpellChecker(Set<String> dictionary, int similar) {
        this.dictionary = dictionary;
        this.similar = similar;
    }

    @Override
    public boolean exists(String search) {
//        return check(search) == 0;
        return dictionary.contains(search);
    }

    private int check(String search) {
        int result = search.length();
        for(String word : dictionary) {
            int diff = Levenshtein.distance(search, word);
            if(diff < result) result = diff;
            if(result < 1) return 0;
        }
        return result;
    }

    @Override
    public Set<String> findSimilar(String search) {
        return find(search, similar);
    }

    @Override
    public Set<String> find(String search, int diff) {
        Set<String> matches = new LinkedHashSet<String>();
        int min = search.length() - diff;
        int max = search.length() + diff;
        for(String word : dictionary) {
            if(word.length() > max || word.length() < min) continue;
            if(Levenshtein.distance(search, word) <= diff) {
                matches.add(word);
            }
        }
        return matches;
    }
}