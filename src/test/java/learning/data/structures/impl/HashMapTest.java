package learning.data.structures.impl;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ramanaue on 11/06/2015.
 */
public class HashMapTest {

    private static final String KEY_1 = "key1";
    private static final String KEY_2 = "key2";
    private static final String VALUE_1 = "value1";
    private static final String VALUE_2 = "value2";

    private Map<String, String> map;

    @Before
    public void before() {
        map = new HashMap();
    }

    @Test
    public void testConstructor() {
        map = new HashMap(5);
        map.put(KEY_1, VALUE_1);
        map.put(KEY_2, VALUE_2);
        assertThat(map.get(KEY_1), equalTo(VALUE_1));
        assertThat(map.get(KEY_2), equalTo(VALUE_2));
    }

    @Test
    public void testIsEmpty() {
        assertThat(map.isEmpty(), equalTo(true));
        map.put(KEY_1, VALUE_1);
        assertThat(map.isEmpty(), equalTo(false));
    }

    @Test
    public void testSize() {
        assertThat(map.size(), equalTo(0));
        map.put(KEY_1, VALUE_1);
        assertThat(map.size(), equalTo(1));
    }

    @Test
    public void testContainsKey() {
        assertThat(map.containsKey(KEY_1), equalTo(false));
        map.put(KEY_1, VALUE_1);
        assertThat(map.containsKey(KEY_1), equalTo(true));
    }

    @Test
    public void testContainsValue() {
        assertThat(map.containsValue(VALUE_1), equalTo(false));
        map.put(KEY_1, VALUE_1);
        assertThat(map.containsValue(VALUE_1), equalTo(true));
    }

    @Test
    public void testPut() {
        assertThat(map.put(KEY_1, VALUE_1), equalTo(null));
        assertThat(map.put(KEY_1, VALUE_2), equalTo(VALUE_1));
        assertThat(map.put(null, VALUE_1), equalTo(null));
        assertThat(map.put(null, VALUE_2), equalTo(VALUE_1));
    }

    @Test
    public void testPutAll() {
        Map<String, String> temp = new java.util.HashMap<String, String>();
        temp.put(KEY_1, VALUE_1);
        temp.put(KEY_2, VALUE_2);

        map.putAll(temp);
        assertThat(map.size(), equalTo(2));
        assertThat(map.isEmpty(), equalTo(false));
        assertThat(map.get(KEY_1), equalTo(VALUE_1));
        assertThat(map.get(KEY_2), equalTo(VALUE_2));
    }

    @Test
    public void testGet() {
        map.put(KEY_1, VALUE_1);
        map.put(null, VALUE_2);
        assertThat(map.get(KEY_1), equalTo(VALUE_1));
        assertThat(map.get(null), equalTo(VALUE_2));
    }

    @Test // edge case: hash collision
    public void testGetWithCollision() {
        // this will give hash collision
        String key = new StringBuilder(KEY_1).reverse().toString();

        map.put(KEY_1, VALUE_1);
        map.put(key, VALUE_2);

        assertThat(map.get(KEY_1), equalTo(VALUE_1));
        assertThat(map.get(key), equalTo(VALUE_2));
    }

    @Test
    public void testValues() {
//        assertThat(map.values(), emptyCollectionOf(String.class));

        map.put(KEY_1, VALUE_1);
        map.put(KEY_2, VALUE_2);
        Collection<String> values = map.values();

        assertThat(values.size(), equalTo(2));
        assertThat(values.contains(VALUE_1), equalTo(true));
        assertThat(values.contains(VALUE_2), equalTo(true));
    }

    @Test
    public void testKeySet() {
//        assertThat(map.keySet(), emptyCollectionOf(String.class));

        map.put(KEY_1, VALUE_1);
        map.put(null, VALUE_2);
        Set<String> values = map.keySet();

        assertThat(values.size(), equalTo(2));
        assertThat(values.contains(KEY_1), equalTo(true));
        assertThat(values.contains(null), equalTo(true));
    }

    @Test
    public void testEntrySet() {
        map.put(KEY_1, VALUE_1);
        Set<Map.Entry<String, String>> entries = map.entrySet();
        assertThat(entries.size(), equalTo(1));

        Map.Entry<String, String> entry = (Map.Entry<String, String>) entries.toArray()[0];
        assertThat(entry.getKey(), equalTo(KEY_1));
        assertThat(entry.getValue(), equalTo(VALUE_1));
    }

    @Test
    public void testRemove() {
        map.put(KEY_1, VALUE_1);
        map.put(null, VALUE_2);

        assertThat(map.remove(KEY_1), equalTo(VALUE_1));
        assertThat(map.size(), equalTo(1));
        assertThat(map.containsKey(KEY_1), equalTo(false));
        assertThat(map.containsKey(null), equalTo(true));

        assertThat(map.remove(null), equalTo(VALUE_2));
        assertThat(map.size(), equalTo(0));
        assertThat(map.containsKey(KEY_1), equalTo(false));
        assertThat(map.containsKey(null), equalTo(false));
    }

    @Test
    public void testClear() {
        map.put(KEY_1, VALUE_1);
        map.put(KEY_2, VALUE_2);
        map.clear();

        assertThat(map.size(), equalTo(0));
        assertThat(map.isEmpty(), equalTo(true));
        assertThat(map.values().size(), equalTo(0));
        assertThat(map.containsKey(KEY_1), equalTo(false));
        assertThat(map.containsKey(KEY_2), equalTo(false));
    }
}