package learning;

import learning.problems.spellcheck.impl.SimpleSpellChecker;
import learning.problems.spellcheck.SpellChecker;
import learning.problems.spellcheck.impl.TrieBasedSpellChecker;
import learning.util.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by edmundas on 23/01/2015.
 */
public class Main {

    public static void main(String[] args) {
        problem1();
    }

    private static void problem1() {
        Set<String> dictionary = new LinkedHashSet<String>();
        try {
            for(String word : FileUtil.readLines(new File("C:/Development/words"))) {
                dictionary.add(word.toLowerCase());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();
        long curr = 0;

        curr = getUsedMemory();
        start = problem1Solution1(dictionary);
        print(String.format("Finished in %d ms", System.currentTimeMillis()-start));
        print(String.format("%d of memory used", getUsedMemory()-curr));

        curr = getUsedMemory() - curr;
        start = problem1Solution2(dictionary);
        print(String.format("Finished in %d ms", System.currentTimeMillis()-start));
        print(String.format("%d of memory used", getUsedMemory()-curr));
    }

    private static long problem1Solution1(Set<String> dictionary) {
        SpellChecker spellChecker = new SimpleSpellChecker(dictionary);
        final long start = System.currentTimeMillis();
        boolean exists = spellChecker.exists("computer");
        print(String.format("Word exists in dictionary - %b", exists));
        return start;
    }

    private static long problem1Solution2(Set<String> dictionary) {
        SpellChecker spellChecker = new TrieBasedSpellChecker(dictionary);
        final long start = System.currentTimeMillis();
        boolean exists = spellChecker.exists("computer");
        print(String.format("Word exists in dictionary - %b", exists));
        return start;
    }

    private static void print(Object line) {
        System.out.println(line);
    }

    private static void print(Collection lines) {
        for(Object line : lines) {
            print(line);
        }
    }

    private static long getUsedMemory() {
        return (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1024;
    }
}