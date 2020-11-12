package app;

import data.parser.Parser;
import data.reader.FileReader;

import java.io.File;

public class Executor {

    private Parser parser = new Parser();

    public void execute() {
        var text = new FileReader().getTextByLines();
        parser.parseBySentences(parser.linesToText(text));
    }
}
