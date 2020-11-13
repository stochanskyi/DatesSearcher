package data.searcher;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DateMatcherTest {

    @Test
    public void testMatch() {
        var input = "21-03-2020";

        var matcher = new ConcreteDateMatcher('-', List.of(2, 2, 4));
        var actualResult = matcher.matches(input);

        assertTrue(actualResult);

    }

    @Test
    public void testNotMatch() {
        var input = "21-03-2020";

        var matcher = new ConcreteDateMatcher('-', List.of(2, 2, 2));
        var actualResult = matcher.matches(input);

        assertFalse(actualResult);
    }

}