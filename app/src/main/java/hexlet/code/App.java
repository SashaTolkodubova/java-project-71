package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    public static void main(String[] args) {

        new CommandLine(new App()).execute("files/file1.json", "files/file2.json");
//        new CommandLine(new App()).execute(args);

    }

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String file1Path;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String file2Path;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", paramLabel = "format")
    String format;

    @Option(names = {"-h", "--Help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean help;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionHelp;


    @Override
    public Integer call() throws Exception {
        String result = Differ.generate(file1Path, file2Path);
        System.out.println(result);
        return 0;
    }
}
