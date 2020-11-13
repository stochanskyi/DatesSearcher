package app;

import data.parser.Parser;
import data.reader.FileReader;
import data.searcher.DateMatcher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Executor {

    private Parser parser = new Parser();

    public void execute() {
        var text = parser.linesToText(new FileReader().getTextByLines());

        List<String> sentences = parser.parseBySentences(text);

        StringBuilder resultText = new StringBuilder();

        ArrayList<String> dates = new ArrayList<>();

        for (String sentence : sentences) {
            var sentenceDates = getDatesFromSentence(sentence);
            var stringToAdd = "";

            if (!sentenceDates.isEmpty()) {
                dates.addAll(sentenceDates);
                stringToAdd = sentence.toUpperCase();
            } else {
                stringToAdd = sentence;
            }

            resultText.append(stringToAdd);
        }

        System.out.println(resultText.toString());

        System.out.println("Dates found: ");

        System.out.println(String.join("\n", dates));

        runSecondTask();
    }


    private void runSecondTask() {
        var text = parser.linesToText(new FileReader().getTextByLines());
        var pattern = Pattern.compile("\\w[^A-z]+");

        var matcher = pattern.matcher(text);

        var resultString = new StringBuilder(text);

        while (matcher.find()) {
            var lastWordIndex = matcher.start();
            resultString.setCharAt(lastWordIndex, toUpperCase(text.charAt(lastWordIndex)));
        }

        System.out.println(resultString.toString());
    }

    private char toUpperCase(char s) {
        return Character.toUpperCase(s);
    }

    private List<String> getDatesFromSentence(String sentence) {
        var words = parser.parseByWhitespaces(sentence);

        var dateMatcher = new DateMatcher();
        ArrayList<String> dates = new ArrayList<>();

        for (String word : words) {
            if (dateMatcher.matches(word)) dates.add(word);
        }

        return dates;
    }
}