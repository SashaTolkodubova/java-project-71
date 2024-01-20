package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.SortedMap;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    public static void main(String[] args) {
        new CommandLine(new App()).execute("-h");
    }

    @Option(names = {"-h", "--Help"}
            , usageHelp = true
            , description = "Show this help message and exit.")
    boolean help;

    @Option(names = {"-V", "--version"}
            , versionHelp = true
            , description = "Print version information and exit.")
    boolean versionHelp;

    @Override
    public void run() {
        System.out.println("Hello");
    }
}
