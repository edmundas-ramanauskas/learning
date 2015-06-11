package learning.data.structures.impl;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.equalTo;

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
    public void testGet() {
        map.put(KEY_1, VALUE_1);
        assertThat(map.get(KEY_1), equalTo(VALUE_1));
        map.put(null, VALUE_2);
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
        assertThat(map.values(), emptyCollectionOf(String.class));

        map.put(KEY_1, VALUE_1);
        Collection<String> values = map.values();

        assertThat(values.size(), equalTo(1));
        assertThat(values, contains(VALUE_1));
    }
}