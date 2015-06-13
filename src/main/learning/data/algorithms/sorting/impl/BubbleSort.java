package learning.data.algorithms.sorting.impl;

import learning.data.algorithms.sorting.SortStrategy;
import static learning.util.SortUtil.compare;

/**
 * Created by edmundas on 14.5.17.
 */
public class BubbleSort implements SortStrategy {

    @Override
    public void sort(Comparable[] array) {
        sort(array, false);
    }

    @Override
    public void sort(Comparable[] array, boolean reverse) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int n = 0; n < i; n++) {
                if (compare(array[n], array[n + 1], reverse) > 0) {
                    Comparable item = array[n];
                    array[n] = array[n+1];
                    array[n+1] = item;
                }
            }
        }
    }
}