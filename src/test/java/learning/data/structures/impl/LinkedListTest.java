package learning.data.structures.impl;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by edmundas on 15.6.13.
 */
public class LinkedListTest {

    private static final String VALUE_1 = "value1";
    private static final String VALUE_2 = "value2";
    private static final String VALUE_3 = "value3";

    private List<String> list;

    @Before
    public void before() {
        list = new LinkedList<String>();
    }

    @Test
    public void testAdd() {
        assertThat(list.add(VALUE_1), equalTo(true));
        assertThat(list.add(VALUE_2), equalTo(true));
    }

    @Test
    public void testAddWithIndex() {
        list.add(0, VALUE_1);
        list.add(VALUE_2);
        list.add(0, VALUE_3);

        assertThat(list.get(0), equalTo(VALUE_3));
        assertThat(list.get(1), equalTo(VALUE_1));
        assertThat(list.get(2), equalTo(VALUE_2));
    }

    @Test
    public void testGet() {
        list.add(VALUE_1);
        list.add(VALUE_2);
        list.add(VALUE_3);

        assertThat(list.get(0), equalTo(VALUE_1));
        assertThat(list.get(1), equalTo(VALUE_2));
        assertThat(list.get(2), equalTo(VALUE_3));
    }

    @Test
    public void testSize() {
        assertThat(list.size(), equalTo(0));
        list.add(VALUE_1);
        assertThat(list.size(), equalTo(1));
    }

    @Test
    public void testIsEmpty() {
        assertThat(list.isEmpty(), equalTo(true));
        list.add(VALUE_1);
        assertThat(list.isEmpty(), equalTo(false));
    }

    @Test
    public void testContains() {
        assertThat(list.contains(VALUE_1), equalTo(false));
        list.add(VALUE_1);
        assertThat(list.contains(VALUE_1), equalTo(true));
    }

    @Test
    public void testIndexOf() {
        assertThat(list.indexOf(VALUE_1), equalTo(-1));
        list.add(VALUE_1);
        assertThat(list.indexOf(VALUE_1), equalTo(0));
        list.add(VALUE_1);
        assertThat(list.indexOf(VALUE_1), equalTo(0));
    }

    @Test
    public void testLastIndexOf() {
        assertThat(list.lastIndexOf(VALUE_1), equalTo(-1));
        list.add(VALUE_1);
        assertThat(list.lastIndexOf(VALUE_1), equalTo(0));
        list.add(VALUE_1);
        assertThat(list.lastIndexOf(VALUE_1), equalTo(1));
    }
}
