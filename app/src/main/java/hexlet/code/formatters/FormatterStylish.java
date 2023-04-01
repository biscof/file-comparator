package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class FormatterStylish {
    public static String format(Map<String, List<Object>> diffMap) {
        String diffStr = "{\n";

        for (Map.Entry<String, List<Object>> entry : diffMap.entrySet()) {
            if (entry.getValue().get(0) == null) {
                diffStr = diffStr.concat(String.format("  + %s: %s\n", entry.getKey(), entry.getValue().get(1)));
            } else if (entry.getValue().get(1) == null) {
                diffStr = diffStr.concat(String.format("  - %s: %s\n", entry.getKey(), entry.getValue().get(0)));
            } else if (entry.getValue().get(0).equals(entry.getValue().get(1))) {
                diffStr = diffStr.concat(String.format("    %s: %s\n", entry.getKey(), entry.getValue().get(0)));
            } else {
                diffStr = diffStr.concat(String.format("  - %s: %s\n", entry.getKey(), entry.getValue().get(0)));
                diffStr = diffStr.concat(String.format("  + %s: %s\n", entry.getKey(), entry.getValue().get(1)));
            }
        }

        diffStr = diffStr.concat("}");
        return diffStr;
    }
}
