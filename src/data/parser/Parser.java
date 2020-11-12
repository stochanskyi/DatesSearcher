package data.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    public String linesToText(List<String> lines) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String line : lines) {
            stringBuilder.append(line);
            stringBuilder.append(' ');
        }

        return stringBuilder.toString().trim();
    }

    public List<String> parseBySentences(String text) {
        ArrayList<String> sentences = new ArrayList<>();

        StringBuilder sentence = new StringBuilder();
        for (char s : text.toCharArray()) {
            sentence.append(s);

            if (isDot(s)) {
                sentences.add(sentence.toString().trim());
                sentence = new StringBuilder();
            }
        }

        return sentences;
    }

    public List<String> parseByWhitespaces(String text) {
        ArrayList<String> words = new ArrayList<>();

        StringBuilder word = new StringBuilder();

        for (char s : text.toCharArray()) {
            if (isWhitespace(s) && !word.toString().isEmpty()) {
                words.add(word.toString().trim());
                word = new StringBuilder();
            } else {
                word.append(s);
            }
        }

        if (!word.toString().isEmpty()) {
            words.add(word.toString().trim());
        }

        return words.stream().map(this::trimSpacialChars).collect(Collectors.toList());
    }

    private String trimSpacialChars(String orig) {
        var result = new StringBuilder(orig);

        int intex = 0;

        while (isSpacialChar(result.toString().toCharArray()[0])) {
            result.deleteCharAt(0);
            if (result.toString().isEmpty()) return "";
        }

        while (isSpacialChar(result.toString().toCharArray()[result.toString().length() - 1])) {
            result.deleteCharAt(result.length() - 1);
            if (result.toString().isEmpty()) return "";
        }

        return result.toString().trim();
    }

    private boolean isSpacialChar(char s) {
        return !Character.isLetterOrDigit(s);
    }

    private boolean isDot(char s) {
        return s == '.';
    }

    private boolean isWhitespace(char s) {
        return Character.isWhitespace(s);
    }
}
