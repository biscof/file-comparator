package hexlet.code.formatters;

import org.apache.commons.lang3.ClassUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FormatterPlain {
    public static String format(Map<String, List<Object>> diffMap) {
        Map<String, List<String>> diffMapStr = new TreeMap<>();

        for (Map.Entry<String, List<Object>> entry : diffMap.entrySet()) {
            if (entry.getValue().get(0) != null
                    && entry.getValue().get(0) != null
                    && entry.getValue().get(0).equals(entry.getValue().get(1))) {
                continue;
            }
            diffMapStr.put(entry.getKey(), new ArrayList<>());
            for (Object o : entry.getValue()) {
                if (o == null) {
                    diffMapStr.get(entry.getKey()).add(null);
                } else if (o.equals("null")
                        || ClassUtils.isPrimitiveOrWrapper(o.getClass())) {
                    diffMapStr.get(entry.getKey()).add(String.format("%s", o));
                } else if (o.getClass().isInstance("")) {
                    diffMapStr.get(entry.getKey()).add(String.format("'%s'", o));
                } else {
                    diffMapStr.get(entry.getKey()).add("[complex value]");
                }
            }
        }

        String diffStr = "";

        for (Map.Entry<String, List<String>> entry : diffMapStr.entrySet()) {
            if (entry.getValue().get(0) == null) {
                diffStr = diffStr.concat(
                        String.format(
                                "Property '%s' was added with value: %s\n",
                                entry.getKey(),
                                entry.getValue().get(1)
                        )
                );
            } else if (entry.getValue().get(1) == null) {
                diffStr = diffStr.concat(
                        String.format(
                                "Property '%s' was removed\n",
                                entry.getKey()
                        )
                );
            } else {
                diffStr = diffStr.concat(
                        String.format(
                                "Property '%s' was updated. From %s to %s\n",
                                entry.getKey(),
                                entry.getValue().get(0),
                                entry.getValue().get(1)
                        )
                );
            }
        }

        return diffStr.trim();
    }
}
