package learning.util;

/**
 * Created by edmundas on 14.5.17.
 */
public class SortUtil {
    public static int compare(Comparable o1, Comparable o2, boolean reverse) {
        return (reverse) ? o2.compareTo(o1) : o1.compareTo(o2);
    }
}