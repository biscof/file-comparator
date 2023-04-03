package hexlet.code;

import hexlet.code.formatters.FormatterJSON;
import hexlet.code.formatters.FormatterPlain;
import hexlet.code.formatters.FormatterStylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(Map<String, List<Object>> diffMap, String format) {
        switch (format) {
            case "stylish" -> {
                return FormatterStylish.format(diffMap);
            }
            case "plain" -> {
                return FormatterPlain.format(diffMap);
            }
            case "json" -> {
                String formatStr = FormatterJSON.format(diffMap);
                Parser.convertToJSONFile(formatStr, "differences.json");
                return formatStr;
            }
            default -> throw new IllegalArgumentException("Invalid format.");
        }
    }
}
