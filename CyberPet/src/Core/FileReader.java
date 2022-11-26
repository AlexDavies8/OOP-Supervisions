package Core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public static List<String> ReadLines(String path) throws IOException {
        var lines = new ArrayList<String>();
        try (var br = new BufferedReader(new java.io.FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
        }
        return lines;
    }
}
