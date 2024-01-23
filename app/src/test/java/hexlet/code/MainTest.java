package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MainTest {
    @Test
    public void testDiffer() throws Exception {
        String expected = "{\n"
                + "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n"
                + "}";
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";
        String actual = Differ.generate(path1, path2);
        Assertions.assertEquals(actual, expected);
    }
}
