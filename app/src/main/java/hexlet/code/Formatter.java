package hexlet.code;

import hexlet.code.exception.InvalidFormatException;
import hexlet.code.formatters.FormatterJSON;
import hexlet.code.formatters.FormatterPlain;
import hexlet.code.formatters.FormatterStylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(String filepath1, String filepath2, String format) {

        try {
            Map<String, List<Object>> diffMap = DiffGenerator.getDiffMap(filepath1, filepath2);

            switch (format) {
                case "stylish" -> {
                    return FormatterStylish.format(diffMap);
                }
                case "plain" -> {
                    return FormatterPlain.format(diffMap);
                }
                case "json" -> {
                    String formatStr = FormatterJSON.format(diffMap);
                    Parser.saveAsJSON(formatStr, "differences.json");
                    return formatStr;
                }
                default -> throw new InvalidFormatException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
