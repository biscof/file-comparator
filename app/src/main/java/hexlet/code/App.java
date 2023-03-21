package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows the difference.")
public class App implements Callable<String> {
    @Parameters(paramLabel = "filepath1",
            description = "path to the first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2",
            description = "path to the second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    @Override
    public String call() throws Exception {
        String diff = Differ.generate(filepath1, filepath2);
        System.out.println(diff);
        return "";
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
