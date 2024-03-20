package frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class Main {

    static List<String> readFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            System.out.println("File not found: " + path);
            return null;
        }
        return Files.readAllLines(path, Charset.defaultCharset());
    }

    public static void main(String[] args) throws IOException {
        Path path = null;
        if (args.length > 0) {
            try {
                path = Path.of(args[0]);
            } catch (InvalidPathException e) {
                System.out.println("Invalid path provided: " + args[0]);
                System.exit(1);
            }
        } else {
            System.out.print("Path to file: ");
            String inputPath;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                inputPath = reader.readLine();
                path = Path.of(inputPath);
            } catch (IOException io) {
                System.exit(1);
            }
        }
        List<String> fullText = readFile(path);
        // TODO: pass to scanner
    }
}
