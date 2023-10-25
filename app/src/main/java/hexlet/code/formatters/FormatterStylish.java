package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class FormatterStylish {

    public static String format(Map<String, List<Object>> diffMap) {
        StringBuilder formattedDiff = new StringBuilder("{\n");

        for (Map.Entry<String, List<Object>> entry : diffMap.entrySet()) {
            Object value1 = entry.getValue().get(0);
            Object value2 = entry.getValue().get(1);
            String key = entry.getKey();

            if (value1 == null) {
                formattedDiff.append(String.format("  + %s: %s\n", key, value2));
            } else if (value2 == null) {
                formattedDiff.append(String.format("  - %s: %s\n", key, value1));
            } else if (value1.equals(value2)) {
                formattedDiff.append(String.format("    %s: %s\n", key, value1));
            } else {
                formattedDiff.append(String.format("  - %s: %s\n", key, value1));
                formattedDiff.append(String.format("  + %s: %s\n", key, value2));
            }
        }

        formattedDiff.append("}");
        return formattedDiff.toString();
    }

}
