package learning.data.algorithms;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by edmundas on 15.6.11.
 */
public class LevenshteinTest {

    private static final String STR_1 = "home";
    private static final String STR_2 = "homa";
    private static final String STR_3 = "homas";

    @Test
    public void testDistance() {
        assertThat(Levenshtein.distance(STR_1, STR_2), equalTo(1));
        assertThat(Levenshtein.distance(STR_1, STR_3), equalTo(2));
    }
}