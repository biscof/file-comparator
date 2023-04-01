package hexlet.code;

import hexlet.code.formatters.FormatterPlain;
import hexlet.code.formatters.FormatterStylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(Map<String, List<Object>> diffMap, String format) {
        if (format.equals("stylish")) {
            return FormatterStylish.format(diffMap);
        } else if (format.equals("plain")) {
            return FormatterPlain.format(diffMap);
        } else {
            throw new IllegalArgumentException("Invalid format.");
        }
    }
}
