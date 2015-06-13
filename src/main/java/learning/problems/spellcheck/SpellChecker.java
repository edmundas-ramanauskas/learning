package learning.problems.spellcheck;

import java.util.Set;

/**
 * Created by edmundas on 23/01/2015.
 */
public interface SpellChecker {
    public boolean exists(String search);
    public Set<String> findSimilar(String search);
    public Set<String> find(String search, int diff);
}
