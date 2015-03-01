package learning.data.structures;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by edmundas on 23/01/2015.
 */
public class TrieNode {

    private boolean word = false;
    private Map<Integer, TrieNode> children;

    public TrieNode() {
        children = new HashMap<Integer, TrieNode>();
    }

    public void insert(String word) {
        Integer key = Integer.valueOf(word.charAt(0));

        if (!children.containsKey(key)) {
            children.put(key, new TrieNode());
        }

        if (word.length() > 1) {
            children.get(key).insert(word.substring(1));
        } else {
            children.get(key).word = true;
        }
    }

    /**
     * @param word
     * @return
     */
    public boolean exists(String word) {
        Integer key = Integer.valueOf(word.charAt(0));
        if(word.length() == 1) {
            return children.get(key).word;
        }
        // recursive method call
        return children.get(key).exists(word.substring(1));
    }
}