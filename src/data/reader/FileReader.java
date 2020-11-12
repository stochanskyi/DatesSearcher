package data.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public List<String> getTextByLines() {
        File file = new File(FileConstants.filePath);

        try {
            Scanner scanner = new Scanner(file);
            return readLines(scanner);
        } catch (FileNotFoundException fnfe){
            return List.of();
        }
    }

    private List<String> readLines(Scanner scanner) {
        ArrayList<String> lines = new ArrayList<>();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        return lines;
    }
}
