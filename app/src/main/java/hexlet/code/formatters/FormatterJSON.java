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
            if (entry.getValue().get(0) == null) {
                finalMap.get("added").put(entry.getKey(), entry.getValue().get(1));
            } else if (entry.getValue().get(1) == null) {
                finalMap.get("deleted").put(entry.getKey(), entry.getValue().get(0));
            } else if (entry.getValue().get(0).equals(entry.getValue().get(1))) {
                finalMap.get("unchanged").put(entry.getKey(), entry.getValue().get(0));
            } else {
                finalMap.get("changed").put(entry.getKey(), new ArrayList<>());
                ArrayList<Object> list = (ArrayList) finalMap.get("changed").get(entry.getKey());
                if (entry.getValue().get(0).equals("null")) {
                    list.add(null);
                } else {
                    list.add(entry.getValue().get(0));
                }
                if (entry.getValue().get(1).equals("null")) {
                    list.add(null);
                } else {
                    list.add(entry.getValue().get(1));
                }
            }
        }
        JSONObject json = new JSONObject(finalMap);

        return json.toString();
    }
}
