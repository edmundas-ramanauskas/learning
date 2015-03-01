package learning.data.algorithms;

/**
 * Created by ramanaue on 23/01/2015.
 */
public class Levenshtein {

    public static int distance(String astr, String bstr) {
        astr = astr.toLowerCase();
        bstr = bstr.toLowerCase();
        // i == 0
        int [] costs = new int [bstr.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= astr.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= bstr.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), astr.charAt(i - 1) == bstr.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[bstr.length()];
    }
}
