package hexlet.code;

import hexlet.code.exception.ExtensionNotSupportedException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class DiffGenerator {

    public static Map<String, List<Object>> getDiffMap(String filepath1, String filepath2)
            throws ExtensionNotSupportedException {
        Map<String, Object> map1 = Parser.extractMap(filepath1);
        Map<String, Object> map2 = Parser.extractMap(filepath2);
        Set<String> allKeys = new HashSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());
        Map<String, List<Object>> diffMap = new TreeMap<>();

        for (String key : allKeys) {
            Object value1 = map1.get(key) == null ? "null" : map1.get(key);
            Object value2 = map2.get(key) == null ? "null" : map2.get(key);

            if (!map1.containsKey(key)) {
                value1 = null;
            } else if (!map2.containsKey(key)) {
                value2 = null;
            }

            List<Object> list = new ArrayList<>();
            list.add(value1);
            list.add(value2);
            diffMap.put(key, list);
        }
        return diffMap;
    }

}
