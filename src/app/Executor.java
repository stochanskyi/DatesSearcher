package app;

import data.parser.Parser;
import data.reader.FileReader;
import data.searcher.DateMatcher;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Executor {

    private Parser parser = new Parser();

    public void execute() {
        var text = parser.linesToText(new FileReader().getTextByLines());

        List<String> sentences = parser.parseBySentences(text);

        StringBuilder resultText = new StringBuilder();

        ArrayList<DateResult> dates = new ArrayList<>();

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

        printDatesRange(dates);

        System.out.println("Dates found: ");

        System.out.println(dates.stream().map((a) -> a.original).collect(Collectors.joining("\n")));

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

    private List<DateResult> getDatesFromSentence(String sentence) {
        var words = parser.parseByWhitespaces(sentence);

        var dateMatcher = new DateMatcher();
        ArrayList<DateResult> dates = new ArrayList<>();


        for (String word : words) {
            var datePattern =  dateMatcher.matches(word);

            if (datePattern != null) {
                try {
                    var date = new SimpleDateFormat(datePattern).parse(word);
                    dates.add(new DateResult(word, date));
                } catch (ParseException ignored) {}
            }
        }

        return dates;
    }

    private static class DateResult implements Comparable<DateResult> {

        private String original;
        private Date date;

        public DateResult(String original, Date date) {
            this.original = original;
            this.date = date;
        }

        public String getOriginal() {
            return original;
        }

        public Date getDate() {
            return date;
        }

        @Override
        public int compareTo(DateResult o) {
            return this.date.compareTo(o.date);
        }
    }

    private void printDatesRange(List<DateResult> dates) {
        var startDate = getMinDate(dates);
        var endDate = getMaxDate(dates);

        System.out.println("Dates range: " + startDate.original + " - " + endDate.original);
    }

    private DateResult getMinDate(List<DateResult> dates) {
        return Collections.min(dates);
    }

    private DateResult getMaxDate(List<DateResult> dates) {
        return Collections.max(dates);
    }
}