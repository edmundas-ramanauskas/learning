package learning.data.algorithms.sorting;

/**
 * Created by edmundas on 14.5.17.
 */
public interface SortStrategy<T extends Comparable<? super T>> {
    void sort(T[] array);
    void sort(T[] array, boolean reverse);
}