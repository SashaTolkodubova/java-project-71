package hexlet.code;

public class Main {
    public static void main(String[] args) throws Exception {
        String path1 = "app/src/test/resources/file1.json";
        String path2 = "app/src/test/resources/file1.json";
        String actual = Differ.generate(path1, path2);

    }
}
