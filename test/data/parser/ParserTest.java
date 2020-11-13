package data.parser;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {


    @Test
    public void testParseBySentences() {
        var input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis in tortor erat. " +
                "Duis interdum consectetur velit a viverra. Morbi eu massa in.";

        var expectedResult = List.of(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                " Duis in tortor erat.",
                " Duis interdum consectetur velit a viverra.",
                " Morbi eu massa in."
        );

        var actualResult = new Parser().parseBySentences(input);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testParseByWords() {
        var input = "Lorem 13.09.2019 ipsum 21/19/2020 adipiscing elit.";

        var expectedResult = List.of(
                "Lorem",
                "13.09.2019",
                "ipsum",
                "21/19/2020",
                "adipiscing",
                "elit"
        );

        var actualResult = new Parser().parseByWhitespaces(input);

        assertEquals(expectedResult, actualResult);
    }
}