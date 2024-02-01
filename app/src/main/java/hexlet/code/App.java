package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private static String file1Path;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private static String file2Path;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]",
            paramLabel = "format")
    private static String format = "stylish";

    @Option(names = {"-h", "--Help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean help;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionHelp;


    @Override
    public Integer call() throws Exception {
        String result = Differ.generate(file1Path, file2Path, format);
        System.out.println(result);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        CommandLine commandLine = new CommandLine(new App());
        commandLine.execute("files/file1.json", "files/file2.json");


    }
}
