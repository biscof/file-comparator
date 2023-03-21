package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    public static String generate(String filepath1, String filepath2) {
        Map<String, Object> map1 = new TreeMap<>();
        Map<String, Object> map2 = new TreeMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        // Get normalised paths
        Path readFile1Path = Paths.get(filepath1).toAbsolutePath().normalize();
        Path readFile2Path = Paths.get(filepath2).toAbsolutePath().normalize();

        try {
            map1 = objectMapper.readValue(new File(readFile1Path.toString()), TreeMap.class);
            map2 = objectMapper.readValue(new File(readFile2Path.toString()), TreeMap.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
                    }})
                .forEach(list1::add);

        // Convert the list of differences into a string, with the natural order preserved.
        // Changes in map1 shown first, if the value has been changed
        String listAsStr = list1.stream()
                .sorted((e1, e2) -> {
                    if (e1.substring(4, 5).equals(e2.substring(4, 5))) {
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
