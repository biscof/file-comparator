package hexlet.code.formatters;

import org.apache.commons.lang3.ClassUtils;

import java.util.List;
import java.util.Map;

public class FormatterPlain {

    public static String format(Map<String, List<Object>> diffMap) {
        StringBuilder diffStr = new StringBuilder();

        for (Map.Entry<String, List<Object>> entry : diffMap.entrySet()) {
            Object value1 = entry.getValue().get(0);
            Object value2 = entry.getValue().get(1);

            if (value1 != null && value1.equals(value2)) {
                continue;
            }

            String formattedValue1 = formatValue(value1);
            String formattedValue2 = formatValue(value2);
            diffStr.append(getPrettyDiff(formattedValue1, formattedValue2, entry.getKey()));
        }

        return diffStr.toString().trim();
    }

    private static String formatValue(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.equals("null") || ClassUtils.isPrimitiveOrWrapper(obj.getClass())) {
            return String.format("%s", obj);
        } else if (obj instanceof String) {
            return String.format("'%s'", obj);
        } else {
            return "[complex value]";
        }
    }

    private static String getPrettyDiff(String value1, String value2, String propertyName) {
        if (value1 == null) {
            return String.format("Property '%s' was added with value: %s\n", propertyName, value2);
        } else if (value2 == null) {
            return (String.format("Property '%s' was removed\n", propertyName));
        } else {
            return (String.format("Property '%s' was updated. From %s to %s\n", propertyName, value1, value2));
        }
    }
}
