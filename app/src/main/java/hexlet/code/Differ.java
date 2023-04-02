package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.TreeMap;


public class Differ {
    public static String generate(String filepath1, String filepath2, String format) {
        Map<String, Object> map1 = Parser.convertToMap(filepath1);
        Map<String, Object> map2 = Parser.convertToMap(filepath2);
        Map<String, List<Object>> diffMap;
        diffMap = generateDiff(map1, map2);

        return Formatter.format(diffMap, format);
    }

    private static Map<String, List<Object>> generateDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        Map<String, List<Object>> diffMap = new TreeMap<>();
        for (String key : allKeys) {
            diffMap.put(key, new ArrayList<>(2));
        }

        for (Map.Entry<String, List<Object>> entry : diffMap.entrySet()) {
            if (map1.containsKey(entry.getKey())) {
                Object o = map1.get(entry.getKey()) == null ? "null" : map1.get(entry.getKey());
                entry.getValue().add(o);
            } else {
                entry.getValue().add(null);
            }

            if (map2.containsKey(entry.getKey())) {
                Object o = map2.get(entry.getKey()) == null ? "null" : map2.get(entry.getKey());
                entry.getValue().add(o);
            } else {
                entry.getValue().add(null);
            }
        }

        return diffMap;
    }
}
