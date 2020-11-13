package data.searcher;

import java.util.List;
import java.util.Map;

public class DateMatcher {

    private static final Map<String, ConcreteDateMatcher> matcherMap = Map.of(
            "dd.MM.yyyy", new ConcreteDateMatcher('.', List.of(2, 2, 4)),
            "dd-MM-yyyy", new ConcreteDateMatcher('-', List.of(2, 2, 4)),
            "dd/MM/yyyy", new ConcreteDateMatcher('/', List.of(2, 2, 4))
    );

    /**
     *
     * @param stringValue string to check if is date
     * @return pattern if is date or @null if is not date
     */
    public String matches(String stringValue) {


        for (String key : matcherMap.keySet()) {
            if (matcherMap.get(key).matches(stringValue)) {
                return key;
            }
        }
        return null;
    }
}
