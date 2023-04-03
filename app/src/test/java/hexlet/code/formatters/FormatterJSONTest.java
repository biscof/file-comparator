package hexlet.code.formatters;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatterJSONTest {

    @Test
    void formatTest() {
        Map<String, List<Object>> initMap = new TreeMap<>();
        initMap.put("name", new ArrayList<>() { { add(null); add("John"); } });
        initMap.put("age", List.of(0, 2));
        initMap.put("sex", new ArrayList<>() { { add("male"); add(null); } });
        initMap.put("status", List.of("null", "user"));
        initMap.put("registered", List.of(true, "null"));
        initMap.put("score", List.of(1, 1));

        Map<String, Map<String, Object>> expectedMap = new TreeMap<>();
        expectedMap.put("added", Map.of("name", "John"));
        expectedMap.put("changed", Map.of(
                "age", List.of(0, 2),
                "registered", new ArrayList<>() { { add(true); add(null); } },
                "status", new ArrayList<>() { { add(null); add("user"); } }
                )
        );
        expectedMap.put("deleted", Map.of("sex", "male"));
        expectedMap.put("unchanged", Map.of("score", 1));
        JSONObject expectedJSON = new JSONObject(expectedMap);

        String actualStr = FormatterJSON.format(initMap);

        assertEquals(expectedJSON.toString(), actualStr);
    }
}
