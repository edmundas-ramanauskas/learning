package learning.data.algorithms.sorting.impl;

import learning.data.algorithms.sorting.SortStrategy;
import static learning.util.SortUtil.compare;

import java.util.Arrays;

/**
 * Created by edmundas on 14.5.17.
 */
public class MergeSort implements SortStrategy {

    @Override
    public void sort(Comparable[] array) {
        sort(array, false);
    }

    @Override
    public void sort(Comparable[] array, boolean reverse) {
        if (array.length > 1) {
            int half = array.length / 2;

            Comparable[] leftArray = Arrays.copyOfRange(array, 0, half);
            Comparable[] rightArray = Arrays.copyOfRange(array, half, array.length);

            sort(leftArray, reverse);
            sort(rightArray, reverse);

            merge(array, leftArray, rightArray, reverse);
        }
    }

    private static void merge(Comparable[] array, Comparable[] left, Comparable[] right, boolean reverse) {
        int total = left.length + right.length;
        int i,li,ri;
        i = li = ri = 0;
        while( i < total) {
            if((li < left.length) && (ri<right.length)) {
                if(compare(left[li], right[ri], reverse) < 0) {
                    array[i] = left[li];
                    i++;
                    li++;
                } else {
                    array[i] = right[ri];
                    i++;
                    ri++;
                }
            } else {
                if(li >= left.length) {
                    while(ri < right.length) {
                        array[i] = right[ri];
                        i++;
                        ri++;
                    }
                }
                if(ri >= right.length) {
                    while(li < left.length) {
                        array[i] = left[li];
                        li++;
                        i++;
                    }
                }
            }
        }
    }
}
