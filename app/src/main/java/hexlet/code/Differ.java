package hexlet.code;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {
    public static String generate(String filepath1, String filepath2) {
        Map<String, Object> map1 = Parser.convertToMap(filepath1);
        Map<String, Object> map2 = Parser.convertToMap(filepath2);

        return compareMaps(map1, map2);
    }

    private static String compareMaps(Map<String, Object> map1, Map<String, Object> map2) {
        String difference = "{";
        List<String> diffList = new ArrayList<>();

        // Find deleted nodes - present in map1, not present in map2
        List<String> list1 = map1.entrySet().stream()
                .filter(e -> !map2.containsKey(e.getKey()))
                .map(e -> String.format("\n - %s: %s", e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        // Find added nodes - not present in map1, present in map2
        map2.entrySet().stream()
                .filter(e -> !map1.containsKey(e.getKey()))
                .map(e -> String.format("\n + %s: %s", e.getKey(), e.getValue()))
                .forEach(list1::add);

        // Find differences in nodes, if any - present in both map1 and map2
        map1.entrySet().stream()
                .filter(e -> map2.containsKey(e.getKey()))
                .flatMap(e -> {
                    // If values in both entries equal
                    if (e.getValue().equals(map2.get(e.getKey()))) {
                        return Stream.of(String.format("\n   %s: %s", e.getKey(), e.getValue()));
                    } else {
                        // If values in both entries differ
                        return Stream.of(
                                String.format("\n - %s: %s", e.getKey(), e.getValue()),
                                String.format("\n + %s: %s", e.getKey(), map2.get(e.getKey())));
                    }
                })
                .forEach(list1::add);

        // Convert the list of differences into a string, with the natural order preserved.
        // Changes in map1 shown first, if the value has been changed
        String listAsStr = list1.stream()
                .sorted((e1, e2) -> {
                    if (e1.substring(4, e1.indexOf(":")).equals(e2.substring(4, e2.indexOf(":")))) {
                        // Get reverse order: "-" before "+"
                        return -(e1.substring(2, 3).compareTo(e2.substring(2, 3)));
                    }
                    return e1.substring(4).compareTo(e2.substring(4));
                })
                .collect(Collectors.joining());

        difference = difference.concat(listAsStr);
        return difference.concat("\n}");
    }
}
