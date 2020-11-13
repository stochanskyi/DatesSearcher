package data.searcher;

import java.util.List;

public class DateMatcher {

    private static List<ConcreteDateMatcher> matchers = List.of(
            new ConcreteDateMatcher('.', List.of(2, 2, 4))
    );

    public boolean matches(String value) {

        for(ConcreteDateMatcher matcher : matchers) {
            if (matcher.matches(value)) return true;
        }

        return false;
    }
}
