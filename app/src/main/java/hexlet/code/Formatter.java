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
                Map<String, Map<String, Object>> jsonDiffMap = FormatterJSON.format(diffMap);
                Parser.convertToJSONFile(jsonDiffMap, "differences.json");
                return jsonDiffMap.toString();
            }
            default -> throw new IllegalArgumentException("Invalid format.");
        }
    }
}
