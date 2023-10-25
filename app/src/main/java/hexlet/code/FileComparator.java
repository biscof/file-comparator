package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "compare",
        mixinStandardHelpOptions = true,
        version = "compare 1.0",
        description = "Compares two configuration files and shows the difference.")
public class FileComparator implements Callable<String> {

    @Parameters(paramLabel = "filepath1",
            description = "Path to file 1.")
    private String filepath1;

    @Parameters(paramLabel = "filepath2",
            description = "Path to file 2.")
    private String filepath2;

    @Option(names = {"-f", "--format"},
            defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    @Override
    public final String call() {
        String diffFormatted = Formatter.format(filepath1, filepath2, format);
        System.out.println(diffFormatted);
        return "";
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new FileComparator()).execute(args);
        System.exit(exitCode);
    }
}
