package learning.data.algorithms.sorting.impl;

import learning.data.algorithms.sorting.SortStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by edmundas on 15.6.11.
 */
public class MergeSortTest {

    private SortStrategy sortStrategy;

    @Before
    public void before() {
        sortStrategy = new MergeSort();
    }

    @Test
    public void testSort() {
        Integer[] array = { 3, 5, 1, 4, 2};
        sortStrategy.sort(array);

        assertThat(array[0], equalTo(1));
        assertThat(array[2], equalTo(3));
        assertThat(array[4], equalTo(5));
    }
}