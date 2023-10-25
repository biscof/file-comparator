package hexlet.code.formatters;

import org.json.JSONObject;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;

public class FormatterJSON {

    public static String format(Map<String, List<Object>> diffMap) {
        Map<String, Map<String, Object>> finalMap = new TreeMap<>();
        finalMap.put("added", new TreeMap<>());
        finalMap.put("changed", new TreeMap<>());
        finalMap.put("deleted", new TreeMap<>());
        finalMap.put("unchanged", new TreeMap<>());

        for (Map.Entry<String, List<Object>> entry : diffMap.entrySet()) {
            Object value1 = entry.getValue().get(0);
            Object value2 = entry.getValue().get(1);

            if (value1 == null) {
                finalMap.get("added").put(entry.getKey(), value2);
            } else if (value2 == null) {
                finalMap.get("deleted").put(entry.getKey(), value1);
            } else if (value1.equals(value2)) {
                finalMap.get("unchanged").put(entry.getKey(), value1);
            } else {
                ArrayList<Object> list = new ArrayList<>();
                list.add(value1.equals("null") ? null : value1);
                list.add(value2.equals("null") ? null : value2);
                finalMap.get("changed").put(entry.getKey(), list);
            }
        }

        return new JSONObject(finalMap).toString();
    }

}
