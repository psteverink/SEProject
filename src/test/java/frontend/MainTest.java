package frontend;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @TempDir
    Path tempDir ;

    @Test
    void testReadFileNorm() throws IOException {
        final Path tempFile = Files.createFile(tempDir.resolve("test-file-1.txt"));
        String fileContent = "Abcdef\n" +
                             "12345\n" +
                             "ABCDE\n" +
                             "12345";
        Files.writeString(tempFile, fileContent);
        List<String> readContent = Main.readFile(tempFile);
        assert readContent != null;
        assertEquals(readContent.size(), 4);
        assertEquals(readContent.get(0), "Abcdef");
        assertEquals(readContent.get(1), "12345");
        assertEquals(readContent.get(2), "ABCDE");
        assertEquals(readContent.get(3), "12345");
    }

    @Test
    void testReadFileEmpty() throws IOException {
        final Path tempFile = Files.createFile(tempDir.resolve("test-file-2.txt"));
        List<String> readContent = Main.readFile(tempFile);
        assert readContent != null;
        assertEquals(readContent.size(), 0);
    }

    @Test
    void testReadFileEmptyLines() throws IOException {
        final Path tempFile = Files.createFile(tempDir.resolve("test-file-3.txt"));
        String fileContent = "\n" +
                             "  \n" +
                             "12345\n" +
                             "ABCDE\n" +
                             "12345\n" +
                             "   ";
        Files.writeString(tempFile, fileContent);
        List<String> readContent = Main.readFile(tempFile);
        assert readContent != null;
        assertEquals(readContent.size(), 6);
        assertEquals(readContent.get(0), "");
        assertTrue(readContent.get(0).isBlank());
        assertTrue(readContent.get(0).isEmpty());
        assertEquals(readContent.get(1), "  ");
        assertTrue(readContent.get(1).isBlank());
        assertFalse(readContent.get(1).isEmpty());
    }

    @Test
    void testReadFileFileNotExist() throws IOException {
        List<String> readContent = Main.readFile(tempDir.resolve("test-file-4.txt"));
        assertNull(readContent);
    }
}