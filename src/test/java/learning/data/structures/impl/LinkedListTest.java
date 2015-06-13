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
}
